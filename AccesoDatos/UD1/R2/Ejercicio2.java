package AccesoDatos.UD1.R2;

import java.nio.file.Files;
import java.nio.file.Path;

import Bibliotecas.Ficheros;

public class Ejercicio2 {
    public static void main(String[] args) {
        Path rutaAlumnos =  Path.of("AccesoDatos", "UD1", "R2", "alumnos.csv");

        try {
           if (Ficheros.comprobarRuta(rutaAlumnos, "¡ERROR! El archivo no existe en la ruta: " + rutaAlumnos)) {
            
           }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }   
}
