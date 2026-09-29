package AccesoDatos.UD1.R2;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Ejercicio1 {
    public static void main(String[] args) {
        Path rutaVidejojuegos = Path.of("AccesoDatos", "UD1", "R2", "videojuegos.csv");
        String nombre, plataforma;
        String[] columnas;
        try {
            // Comprueba que exista el archivo
            // Mensaje de ERROR NO EXISTE
            if (Files.notExists(rutaVidejojuegos)) {
                System.out.println("No existe el archivo en AccesoDatos/UD1/R2/.");
                return;
            }

            // Hace resguardo del contenido
            List<String> texto = Files.readAllLines(
                    rutaVidejojuegos, StandardCharsets.UTF_8);

            // Recorre todas las lineas del texto
            for (int i = 1; i < texto.size(); i++) {
                columnas = texto.get(i).split(";", -1);

                // Comprueba que tenga 3 columnas e imprime el texto.
                if (columnas.length == 3) {
                    try {
                        int id = Integer.parseInt(columnas[0]);
                        nombre = columnas[1];
                        plataforma = columnas[2];
                        System.out.println("[" + id + "]" + " -> " + nombre + " - " + plataforma);

                    // NO TIENE UN NÚMERO VÁLIDO
                    } catch (NumberFormatException e) {
                        System.out.println("El ID no contiene un número válido.");
                    }
                // NO SIGUE EL FORMATO DE 3 COLUMNAS
                } else {
                    System.out.println("¡VAYA! Esta linea del archivo parece que no sigue el formato de 3 columnas (ID, NOMBRE, PLATAFORMA)");
                }
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
