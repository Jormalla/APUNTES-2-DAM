package AccesoDatos.UD1.R2;

import java.nio.file.Files;
import java.nio.file.Path;

public class Ejercicio2 {
    public static void main(String[] args) {
        Path rutaAlumnos =  Path.of("AccesoDatos", "UD1", "R2", "alumnos.csv");

        try {
            if (Files.notExists(rutaAlumnos)) {
                System.out.println("No existe el archivo en AccesoDatos/UD1/R2/.");
                return;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }   
}
