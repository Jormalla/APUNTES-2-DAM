package AccesoDatos.UD1.R1;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class PrepararCarpetas {

    // Método para mostrar el nombre de los archivos de un directorio y su tamaño
    public static String mostrarArchivosEnDirectorio(Path rutaDirectorio) {
        String mensajeAMostrar = "";

        // Cadena de nombre y peso de archivos en lista
        try (DirectoryStream<Path> elementos = Files.newDirectoryStream(rutaDirectorio)) {
            for (Path elemento : elementos) {
                if (Files.isRegularFile(elemento)) {
                    mensajeAMostrar += "-> " + elemento.getFileName() + " - " + Files.size(elemento) + " bytes";
                }
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        return mensajeAMostrar;
    }

    // =================== MAIN =================== //
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
        // Primero en directorio datos y luego la de copias
        System.out.println("[ARCHIVOS EN " + datos.toAbsolutePath() + "]" + "\n" + mostrarArchivosEnDirectorio(datos));
        System.out.println("\n-------------------------\n");
        System.out.println("ARCHIVOS EN " + copias.toAbsolutePath() + "]" + "\n" + mostrarArchivosEnDirectorio(copias));
    }

}
