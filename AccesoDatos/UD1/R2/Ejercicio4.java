package AccesoDatos.UD1.R2;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import AccesoDatos.UD1.R1.MisUtiles;
import Bibliotecas.Ficheros;

public class Ejercicio4 {

    // ===================== MAIN ====================== //
    public static void main(String[] args) {
        // VARIABLES
        String idReserva;
        final String MENSAJE_PETICION_ID = "Ingrese el id de la reserva: ",
                MENSAJE_ID_NO_VALIDA = "¡ERROR! Debes ingresar una ID válida.";
        Scanner input = new Scanner(System.in);
        ArrayList<String> todosLosIds = new ArrayList<>();
        List<String> nuevas = new ArrayList<>();
        Path rutaReservas = Path.of("AccesoDatos", "UD1", "R2", "reservas.csv");

        // VALIDACIÓN DE EXISTENCIA DE ARCHIVO Y RUTA
        try {
            if ((Ficheros.validarRuta(rutaReservas, "¡ERROR! La ruta del archivo reservas.csv No existe")
                    && Ficheros.validarArchivo(rutaReservas, "¡ERROR! El archivo reservas.csv No existe"))) {
                // GUARDA LAS IDs DEL ARCHIVO
                Ficheros.guardarColumnaCsv(todosLosIds, rutaReservas, 1);
                // MUESTRA EL ARCHIVO Y PETICIÓN DE ID
                Ficheros.mostrarArchivo(rutaReservas);
                idReserva = MisUtiles.pideSeleccion(MENSAJE_PETICION_ID, input, todosLosIds, MENSAJE_ID_NO_VALIDA);
                // Pregunta S/N sobre si desean eliminar el registro | Elimina el registro en
                // caso de True
                if (MisUtiles.confirmarAccion("¿Seguro que quieres eliminar la reserva con ID: " + idReserva + "?",
                        input)) {
                    // Recorre el archivo hasta encontrar el id, no guarda la fila del id
                    List<String> contenidoArchivo = Files.readAllLines(rutaReservas, StandardCharsets.UTF_8);
                    for (int i = 0; i < contenidoArchivo.size(); i++) {
                        String[] columnas = contenidoArchivo.get(i).split(";", -1);
                        if (!columnas[0].equals(idReserva)) {
                            nuevas.add(contenidoArchivo.get(i));
                        }
                    }

                    // Reescribe el archivo original
                    Files.write(rutaReservas, nuevas, StandardCharsets.UTF_8);
                }
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        input.close();

    }
}
