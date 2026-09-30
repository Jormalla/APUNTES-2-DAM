package AccesoDatos.UD1.R2;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

import AccesoDatos.UD1.R1.MisUtiles;
import Bibliotecas.Ficheros;

public class Ejercicio2 {
    public static void main(String[] args) {
        // =============== DECLARACIÓN DE VARIABLES ================== //
        Path rutaAlumnos = Path.of("AccesoDatos", "UD1", "R2", "alumnos.csv");
        int id;
        final String MENSAJE_PETICION_ID = "Introduzca la id del alumno: ",
                MENSAJE_ERROR_NO_ES_NUMERO = "¡ERROR! Debes introducir un número entero para la id.";
        Scanner teclado = new Scanner(System.in);
        boolean alumnoExiste = false;
        String[] columnas;

        try {
            // COMPRUEBA EXISTENCIA DEL ARCHIVO
            if (Ficheros.validarRuta(rutaAlumnos, "¡ERROR! El archivo no existe en la ruta: " + rutaAlumnos)
                    && Ficheros.validarArchivo(rutaAlumnos, "¡ERROR! El archivo alumnos.csv no existe")) {
                // Introducción de id
                id = MisUtiles.pideNumeroEntero(MENSAJE_PETICION_ID, MENSAJE_ERROR_NO_ES_NUMERO, teclado);

                // Lee y recorre el archivo csv
                List<String> texto = Files.readAllLines(rutaAlumnos, StandardCharsets.UTF_8);
                for (int i = 1; i < texto.size() && alumnoExiste == false; i++) {
                    columnas = texto.get(i).split(";", -1);

                    // Si el alumno existe muestra los datos
                    if (columnas[0].equals("" + id)) {
                        alumnoExiste = true;
                        MisUtiles.consoleLn("Alumno: " + columnas[1] + " | Grupo: " + columnas[2]);
                    }
                }

                // El alumno no existe
                if (!alumnoExiste) {
                    MisUtiles.consoleLn("¡VAYA! Parece que el alumno con ID: " + id + " no existe ...");
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        teclado.close();
    }
}
