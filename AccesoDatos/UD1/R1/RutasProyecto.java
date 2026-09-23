package AccesoDatos.UD1.R1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class RutasProyecto {
    public static void main(String[] args) {
        // Creo la ruta principal y la subruta para las copias
        Path ruta = Path.of("datos");
        Path subruta = ruta.resolve("copias");
        Path archivo = ruta.resolve("clubes.txt");

        // Creo las carpetas y el archivo si no existen
        try {
            Files.createDirectories(ruta);
            Files.createDirectories(subruta);

            if (Files.notExists(archivo)) {
                Files.createFile(archivo);
            }

            // Muestro las rutas relativas
            System.out.println("RUTAS RELATIVAS:");
            System.out.println("Ruta: " + ruta);
            System.out.println("Subruta: " + subruta);
            System.out.println("Archivo: " + archivo);

            // Muestro las rutas absolutas
            System.out.println("RUTAS ABSOLUTAS: ");
            System.out.println("Ruta: " + ruta.toAbsolutePath());
            System.out.println("Subruta: " + subruta.toAbsolutePath());
            System.out.println("Archivo: " + archivo.toAbsolutePath());
        } catch (IOException e) {
            System.err.println("No se pudo acceder al archivo: "
                    + e.getMessage());
        }

    }
}
