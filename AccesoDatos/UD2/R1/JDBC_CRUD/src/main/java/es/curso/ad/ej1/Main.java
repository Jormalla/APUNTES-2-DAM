package es.curso.ad.ej1;

import java.math.BigDecimal;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        ClubDao dao = new ClubDao();
        JugadorDao jdao = new JugadorDao();
        try {
            System.out.println("CLUBES INICIALES");
            for (Club club : dao.listar()) {
                System.out.println(club);
            }

            String nombreTemporal = "Club de prueba JDBC";
            int id = dao.insertar(nombreTemporal, "Granada", new BigDecimal("120.50"));
            System.out.println("Creado con ID " + id);
            System.out.println("Buscado: " + dao.buscar(id).orElseThrow());
            System.out.println("Actualizado: " + dao.cambiarCiudad(id, "Jaén"));
            System.out.println("Tras cambiar: " + dao.buscar(id).orElseThrow());
            System.out.println("Borrado: " + dao.borrar(id));
            System.out.println("¿Sigue existiendo? " + dao.buscar(id).isPresent());
            System.out.println(dao.listarPorCiudad("Granada"));
            //System.out.println(jdao.insertarJugador("Jorge", 7, 1));
            System.out.println(dao.resumenClubes());
            // Eliminar club
            int idClub = 1;
            if (dao.borrarSiVacio(idClub)) {
                System.out.println("Club con id: " + idClub + " eliminado.");
            } else {
                System.err.println("¡ERROR! No se pudo eliminar el club con id: " + idClub + ". Existen 1 o más jugadores integrantes.");
            }

            // Incrementar presupuesto
            BigDecimal cantidadAumentar = BigDecimal.valueOf(10);
            if (dao.aumentarPresupuesto(idClub, cantidadAumentar)) {
                System.out.println("Se aumento " + cantidadAumentar + " al club con id " + idClub);
            }
        } catch (SQLException e) {
            System.err.println("Error de base de datos: " + e.getMessage());
        }
    }
}
