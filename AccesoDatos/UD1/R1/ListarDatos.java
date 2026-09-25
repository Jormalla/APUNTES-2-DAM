package AccesoDatos.UD1.R1;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class ListarDatos {
    public static void main(String[] args) {
        // Ruta que se comprobará
        Path datos = Path.of("datos");
        int contadorDatos = 0;
        // NO EXISTE LA RUTA
        if (!Files.exists(datos)) {
            System.out.println("La carpeta `datos` no existe.");
        // EXISTE LA RUTA
        } else {
            try (DirectoryStream<Path> elementos = Files.newDirectoryStream(datos)) {
                System.out.println("ARCHIVOS EN DATOS:");
                // Recorre cada elemento del directorio y cuenta los archivos
                for (Path elemento : elementos) {
                    if (Files.isRegularFile(elemento)) {
                        System.out.println("- " + elemento.getFileName());
                        contadorDatos++;
                    }
                }

                System.out.println("Archivos encontrados: " + contadorDatos);
            } catch (IOException e) {
                System.out.println("Error al acceder a la carpeta `datos`:");
                System.out.println(e.getMessage());
            }
        }

    }
}
