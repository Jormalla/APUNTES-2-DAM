package AccesoDatos.UD1.R1;

import java.io.BufferedReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class LeerClubes {
    public static void main(String[] args) {
        Path rutaClubes = Path.of("datos", "clubes.txt");

        // COMPRUEBA SI EXISTE
        if (Files.notExists(rutaClubes)) {
            System.out.println("El archivo clubes.txt NO EXISTE");
            return;
        }

        // LEE EL TEXTO
        try (BufferedReader entrada = Files.newBufferedReader(
                rutaClubes, StandardCharsets.UTF_8)) {
            String linea;
            while ((linea = entrada.readLine()) != null) {
                System.out.println(linea);
            }

            // MANEJO DE ERRORES
        } catch (Exception e) {
            System.err.println("No se pudo acceder al archivo clubes.txt\n" + e.getMessage());
        }
    }
}
