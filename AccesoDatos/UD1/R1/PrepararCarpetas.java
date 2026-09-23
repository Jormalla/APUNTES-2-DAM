package AccesoDatos.UD1.R1;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class PrepararCarpetas {
    public static void main(String[] args) {
        // Creo las rutas
        Path datos = Path.of("datos");
        Path copias = datos.resolve("copias");
        Path clubes = datos.resolve("clubes.txt");
        Path respaldo = copias.resolve("respaldo.txt");

        // Creación de los directorios y archivos
        try {
            Files.createDirectories(datos);
            Files.createDirectories(copias);

            if (Files.notExists(clubes)) {
                Files.createFile(clubes);
            }

            if (Files.notExists(respaldo)) {
                Files.createFile(respaldo);
            }

        } catch (IOException e) {
            System.err.println("No se pudo acceder al archivo: "
                    + e.getMessage());
        }

        // Mostrar los bytes de cada archivo y si existe
        // Primero en directorio datos
        try (DirectoryStream<Path> elementosEnDatos = Files.newDirectoryStream(datos)) {
            for (Path elemento : elementosEnDatos) {
                if (Files.isRegularFile(elemento)) {
                    System.out.println(elemento.getFileName());
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }

}
