package es.curso.ad.ej1;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JugadorDao {
    // Ingresa el jugador
    public int insertarJugador(String nombre, int dorsal, int clubId) throws SQLException {
        String sql = "INSERT INTO jugador(nombre, dorsal, club_id) VALUES (?, ?, ?)";
        try (Connection conexion = Conexion.abrir();
                PreparedStatement sentencia = conexion.prepareStatement(
                        sql, Statement.RETURN_GENERATED_KEYS)) {
            sentencia.setString(1, nombre);
            sentencia.setInt(2, dorsal);
            sentencia.setInt(3, clubId);
            sentencia.executeUpdate();
            try (ResultSet claves = sentencia.getGeneratedKeys()) {
                if (claves.next()) {
                    return claves.getInt(1);
                }
            }
            throw new SQLException("No se ha generado el ID del Jugador.");
        }
    }
}
