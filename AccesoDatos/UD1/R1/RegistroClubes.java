package AccesoDatos.UD1.R1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class RegistroClubes {

    // =================== MAIN ==================
    public static void main(String[] args) {
        // VARIABLES
        String nombre, ciudad;
        final String MENSAJE_ERROR_CADENA = "¡ERROR! No debe estar vacía esta opción";
        int id;

        // RUTAS
        Path rutaClubes = Path.of("datos", "clubes.txt");

        Scanner input = new Scanner(System.in);

        // PIDE LOS INPUT
        id = MisUtiles.pideNumeroEntero("Introduce la ID del club: ", "¡ERROR! Debes introducir un número entero",
                input);
        nombre = MisUtiles.validarCadenaNoVacia("Introduce el nombre del club: ", MENSAJE_ERROR_CADENA, input);
        ciudad = MisUtiles.validarCadenaNoVacia("Introduce el nombre de la ciudad: ", MENSAJE_ERROR_CADENA, input);

        try {
            Files.createDirectories(rutaClubes.getParent());
            // CREA ARCHIVO SI NO EXISTE Y ESCRIBE
            try (BufferedWriter salida = Files.newBufferedWriter(
                    rutaClubes, StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND)) {
                salida.write(id + ";" + nombre + ";" + ciudad);
                salida.newLine();

            } catch (IOException e) {
                System.err.println("No se pudo escribir: " + e.getMessage());
            }
            // LEE EL ARCHIVO
            try (BufferedReader entrada = Files.newBufferedReader(
                    rutaClubes, StandardCharsets.UTF_8)) {
                String linea;
                while ((linea = entrada.readLine()) != null) {
                    System.out.println(linea);
                }

            } catch (IOException e) {
                System.err.println("No se pudo leer: " + e.getMessage());
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        input.close();
    }
}
