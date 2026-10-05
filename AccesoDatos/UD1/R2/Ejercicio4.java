package AccesoDatos.UD1.R2;

import java.io.IOException;
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
        final String MENSAJE_PETICION_ID = "Ingrese el id de la reserva: ", MENSAJE_ID_NO_VALIDA = "¡ERROR! Debes ingresar una ID válida.";
        Scanner input = new Scanner(System.in);
        ArrayList<String> todosLosIds = new ArrayList<>();


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
            }
            
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }


    }
}
