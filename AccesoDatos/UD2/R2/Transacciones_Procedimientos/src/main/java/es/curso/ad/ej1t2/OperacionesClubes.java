package es.curso.ad.ej1t2;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

public class OperacionesClubes {
    public void transferir(int origenId, int destinoId,
            BigDecimal importe) throws SQLException {
        if (origenId == destinoId || importe == null ||
                importe.signum() <= 0) {
            throw new IllegalArgumentException("Transferencia no válida");
        }

        try (Connection conexion = Conexion.abrir()) {
            conexion.setAutoCommit(false);
            try {
                String descuento = "UPDATE club SET presupuesto = presupuesto - ? " +
                        "WHERE id = ? AND presupuesto >= ?";
                try (PreparedStatement sentencia = conexion.prepareStatement(descuento)) {
                    sentencia.setBigDecimal(1, importe);
                    sentencia.setInt(2, origenId);
                    sentencia.setBigDecimal(3, importe);
                    if (sentencia.executeUpdate() != 1) {
                        throw new SQLException("Origen inexistente o saldo insuficiente");
                    }
                }

                String abono = "UPDATE club SET presupuesto = presupuesto + ? WHERE id = ?";
                try (PreparedStatement sentencia = conexion.prepareStatement(abono)) {
                    sentencia.setBigDecimal(1, importe);
                    sentencia.setInt(2, destinoId);
                    if (sentencia.executeUpdate() != 1) {
                        throw new SQLException("El club destino no existe");
                    }
                }

                String registrar = "INSERT INTO movimiento(" +
                        "club_origen_id, club_destino_id, importe) VALUES (?, ?, ?)";
                try (PreparedStatement sentencia = conexion.prepareStatement(registrar)) {
                    sentencia.setInt(1, origenId);
                    sentencia.setInt(2, destinoId);
                    sentencia.setBigDecimal(3, importe);
                    sentencia.executeUpdate();
                }
                conexion.commit();
            } catch (SQLException | RuntimeException e) {
                try {
                    conexion.rollback();
                } catch (SQLException errorRollback) {
                    e.addSuppressed(errorRollback);
                }
                throw e;
            }
        }
    }

    public int contarJugadores(int clubId) throws SQLException {
        try (Connection conexion = Conexion.abrir();
                CallableStatement sentencia = conexion.prepareCall(
                        "{call contar_jugadores(?, ?)}")) {
            sentencia.setInt(1, clubId);
            sentencia.registerOutParameter(2, Types.INTEGER);
            sentencia.execute();
            return sentencia.getInt(2);
        }
    }

    public BigDecimal obtenerPresupuesto(int clubId) throws SQLException {
        try (Connection conexion = Conexion.abrir();
                PreparedStatement sentencia = conexion.prepareStatement(
                        "SELECT presupuesto FROM club WHERE id = ?")) {
            sentencia.setInt(1, clubId);
            try (ResultSet filas = sentencia.executeQuery()) {
                if (filas.next())
                    return filas.getBigDecimal(1);
                throw new SQLException("No existe el club " + clubId);
            }
        }
    }

    public boolean deshacerMovimiento(long idMovimiento) throws SQLException {
        try (Connection conexion = Conexion.abrir()) {
            String consulta = "SELECT * FROM movimiento WHERE id = ?";
            try (PreparedStatement sentencia = conexion.prepareStatement(consulta)) {
                sentencia.setLong(1, idMovimiento);
                try (ResultSet filas = sentencia.executeQuery()) {
                    if (filas.next()) {
                        // el movimiento existe
                        BigDecimal importe = filas.getBigDecimal("importe");
                        int origenClub = filas.getInt("club_origen_id");
                        int destinoClub = filas.getInt("club_destino_id");
                        conexion.setAutoCommit(false);

                        // Se le quita el dinero a lo que era destino
                        String quitarADestino = "UPDATE club SET presupuesto = presupuesto - ? " +
                                "WHERE id = ? AND presupuesto >= ?";
                        try (PreparedStatement sentenciaQuitar = conexion.prepareStatement(quitarADestino)) {
                            sentenciaQuitar.setBigDecimal(1, importe);
                            sentenciaQuitar.setInt(2, destinoClub);
                            sentenciaQuitar.setBigDecimal(3, importe);
                            if (sentenciaQuitar.executeUpdate() != 1) {
                                throw new SQLException("Destino inexistente o saldo insuficiente");
                            }
                        }

                        // Se le suma el dinero a lo que era el origen
                        String sumaOrigen = "UPDATE club SET presupuesto = presupuesto + ? WHERE id = ?";
                        try (PreparedStatement sentenciaSumar = conexion.prepareStatement(sumaOrigen)) {
                            sentenciaSumar.setBigDecimal(1, importe);
                            sentenciaSumar.setInt(2, origenClub);
                            if (sentenciaSumar.executeUpdate() != 1) {
                                throw new SQLException("El club origen no existe");
                            }
                        }

                        // Eliminar movimiento
                        String eliminaMovimiento = "DELETE FROM movimiento WHERE id = ?";
                        try (PreparedStatement sentenciaEliminar = conexion.prepareStatement(eliminaMovimiento)) {
                            sentenciaEliminar.setLong(1, idMovimiento);
                            if (sentenciaEliminar.executeUpdate() != 1) {
                                throw new SQLException("fallo al eliminar el movimiento");
                            }
                        }
                        conexion.commit();
                        return true;

                    } else {
                        // no existe
                        return false;
                    }
                } catch (SQLException | RuntimeException e) {
                    try {
                        conexion.rollback();
                    } catch (SQLException errorRollback) {
                        e.addSuppressed(errorRollback);
                    }
                    throw e;
                }
            }
        }
    }

    public void ficharJugador(int idJugador, BigDecimal coste, int idClub) throws SQLException  {
        if (coste.signum() <= 0) {
            throw new IllegalArgumentException("Fichaje no válido: el coste debe ser > 0");
        }

        try (Connection conexion = Conexion.abrir()) {
            try {
                // Comprueba que existe el jugador
                String sleccionaJugador = "SELECT 1 FROM jugador WHERE id = ?";
                try (PreparedStatement sentencia = conexion.prepareStatement(sleccionaJugador)){
                    sentencia.setInt(1, idJugador);
                    try (ResultSet filas = sentencia.executeQuery()){
                        if (!filas.next()) {
                            throw new IllegalArgumentException("Fichaje no válido: No existe el jugador");
                        }
                    }
                }
                conexion.setAutoCommit(false);
                // Comprueba que el club existe y el saldo es suficiente + resta el coste
                String quitaCoste = "UPDATE club SET presupuesto = presupuesto - ? " +
                        "WHERE id = ? AND presupuesto >= ?";
                try (PreparedStatement sentencia = conexion.prepareStatement(quitaCoste)) {
                    sentencia.setBigDecimal(1, coste);
                    sentencia.setInt(2, idClub);
                    sentencia.setBigDecimal(3, coste);
                    if (sentencia.executeUpdate() != 1) {
                        throw new SQLException("Club inexistente o saldo insuficiente");
                    }
                }

                // Aplica el fichaje
                String cambiaClub = "UPDATE jugador SET club_id = ?";
                try (PreparedStatement sentencia = conexion.prepareStatement(cambiaClub)) {
                    sentencia.setInt(1, idClub);
                    sentencia.executeUpdate();
                }

                // Registra el fichaje
                String registrarFichaje = "INSERT INTO fichaje(" +
                        "jugador_id, coste, club_id) VALUES (?, ?, ?)";
                try (PreparedStatement sentencia = conexion.prepareStatement(registrarFichaje)) {
                    sentencia.setInt(1, idJugador);
                    sentencia.setBigDecimal(2, coste);
                    sentencia.setInt(3, idClub);
                    sentencia.executeUpdate();
                }

                conexion.commit();
            } catch (SQLException | RuntimeException e) {
                try {
                    conexion.rollback();
                } catch (SQLException errorRollback) {
                    e.addSuppressed(errorRollback);
                }
                throw e;
            }
        }
    }
}
