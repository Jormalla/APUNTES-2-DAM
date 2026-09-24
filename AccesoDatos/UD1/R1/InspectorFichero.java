package AccesoDatos.UD1.R1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class InspectorFichero {
    public static void main(String[] args) {
        Path archivo = Path.of("datos", "clubes.txt");

        try{
            // El archivo existe
            if (Files.exists(archivo)) {
                System.out.println("El archivo clubes.txt tiene un tamaño de " + Files.size(archivo) + " bytes");
            } else {
                System.out.println("El archivo clubes.txt no está disponible");
            }

        // Manejo de errores
        } catch (IOException e){
            System.err.println(e.getMessage());
        }
    }
}
