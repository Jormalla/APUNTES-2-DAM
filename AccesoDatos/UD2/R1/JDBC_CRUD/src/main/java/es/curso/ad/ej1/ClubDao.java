package es.curso.ad.ej1;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClubDao {
    public int insertar(String nombre, String ciudad,
            BigDecimal presupuesto) throws SQLException {
        String sql = "INSERT INTO club(nombre, ciudad, presupuesto) VALUES (?, ?, ?)";
        try (Connection conexion = Conexion.abrir();
                PreparedStatement sentencia = conexion.prepareStatement(
                        sql, Statement.RETURN_GENERATED_KEYS)) {
            sentencia.setString(1, nombre);
            sentencia.setString(2, ciudad);
            sentencia.setBigDecimal(3, presupuesto);
            sentencia.executeUpdate();
            try (ResultSet claves = sentencia.getGeneratedKeys()) {
                if (claves.next()) {
                    return claves.getInt(1);
                }
                throw new SQLException("No se ha generado el ID del club");
            }
        }
    }

    public List<Club> listar() throws SQLException {
        String sql = "SELECT id, nombre, ciudad, presupuesto FROM club ORDER BY id";
        List<Club> clubes = new ArrayList<>();
        try (Connection conexion = Conexion.abrir();
                PreparedStatement sentencia = conexion.prepareStatement(sql);
                ResultSet filas = sentencia.executeQuery()) {
            while (filas.next()) {
                clubes.add(leerClub(filas));
            }
        }
        return clubes;
    }

    public Optional<Club> buscar(int id) throws SQLException {
        String sql = "SELECT id, nombre, ciudad, presupuesto FROM club WHERE id = ?";
        try (Connection conexion = Conexion.abrir();
                PreparedStatement sentencia = conexion.prepareStatement(sql)) {
            sentencia.setInt(1, id);
            try (ResultSet filas = sentencia.executeQuery()) {
                if (filas.next()) {
                    return Optional.of(leerClub(filas));
                }
            }
        }
        return Optional.empty();
    }

    public boolean cambiarCiudad(int id, String ciudad) throws SQLException {
        String sql = "UPDATE club SET ciudad = ? WHERE id = ?";
        try (Connection conexion = Conexion.abrir();
                PreparedStatement sentencia = conexion.prepareStatement(sql)) {
            sentencia.setString(1, ciudad);
            sentencia.setInt(2, id);
            return sentencia.executeUpdate() == 1;
        }
    }

    public boolean borrar(int id) throws SQLException {
        String sql = "DELETE FROM club WHERE id = ?";
        try (Connection conexion = Conexion.abrir();
                PreparedStatement sentencia = conexion.prepareStatement(sql)) {
            sentencia.setInt(1, id);
            return sentencia.executeUpdate() == 1;
        }
    }

    private Club leerClub(ResultSet filas) throws SQLException {
        return new Club(
                filas.getInt("id"),
                filas.getString("nombre"),
                filas.getString("ciudad"),
                filas.getBigDecimal("presupuesto"));
    }

    // Devuelve una lista de clubes cuya ciudad coincida con el parametro
    public List<Club> listarPorCiudad(String ciudad) throws SQLException {
        String sql = "SELECT id, nombre, ciudad, presupuesto FROM club WHERE ciudad = ? ORDER BY nombre";
        List<Club> clubesDeCiudad = new ArrayList<>();

        try (Connection conexion = Conexion.abrir();
                PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            // Asignación del parametro
            sentencia.setString(1, ciudad);

            // Ejecución del la query
            try (ResultSet filas = sentencia.executeQuery()) {
                while (filas.next()) {
                    clubesDeCiudad.add(leerClub(filas));
                }
            }
        }
        return clubesDeCiudad;
    }

    // Devuelve todos los clubes con su total de jugadores (0 si no tienen)
    public List<ResumenClub> resumenClubes() throws SQLException {
        String sql = "SELECT c.id, c.nombre, COUNT(j.id) AS total_jugadores "
            + "FROM club c LEFT JOIN jugador j ON j.club_id = c.id "
            + "GROUP BY c.id, c.nombre ORDER BY c.id";
        List<ResumenClub> resumen = new ArrayList<>();
        try (Connection conexion = Conexion.abrir();
            PreparedStatement sentencia = conexion.prepareStatement(sql);
            ResultSet filas = sentencia.executeQuery()) {
            while (filas.next()) {
                resumen.add(new ResumenClub(
                    filas.getInt("id"),
                    filas.getString("nombre"),
                    filas.getLong("total_jugadores")));
            }
        }
        return resumen;
    }

    // Borra el club solo si no tiene jugadores ni movimientos.
    // Devuelve true si se borró, false si el ID no existe.
    public boolean borrarSiVacio(int id) throws SQLException {
        String sql = "DELETE FROM club WHERE id = ? AND NOT EXISTS (SELECT * FROM jugador WHERE jugador.club_id = ?);";
        try (Connection conexion = Conexion.abrir();
            PreparedStatement sentencia = conexion.prepareStatement(sql)) {
            sentencia.setInt(1, id);
            sentencia.setInt(2, id);
            return sentencia.executeUpdate() == 1;
        }
    }

    // Suma cantidad al presupuesto directamente en SQL, sin cargar la tabla.
    // Devuelve true si se actualizó el club, false si no existe.
    public boolean aumentarPresupuesto(int id, BigDecimal cantidad) throws SQLException {
        if (cantidad == null || cantidad.signum() <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser positiva");
        }
        String sql = "UPDATE club SET presupuesto = presupuesto + ? WHERE id = ?";
        try (Connection conexion = Conexion.abrir();
            PreparedStatement sentencia = conexion.prepareStatement(sql)) {
            sentencia.setBigDecimal(1, cantidad);
            sentencia.setInt(2, id);
            return sentencia.executeUpdate() == 1;
        }
    }
}
