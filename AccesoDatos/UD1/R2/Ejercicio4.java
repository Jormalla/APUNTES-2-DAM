package AccesoDatos.UD1.R2;

import java.nio.file.Path;

import Bibliotecas.Ficheros;

public class Ejercicio4 {
    public static void main(String[] args) {
        Path rutaReservas = Path.of("AccesoDatos", "UD1", "R2", "reservas.csv");

        // VALIDACIÓN DE EXISTENCIA DE ARCHIVO Y RUTA
        try {
            if ((Ficheros.validarRuta(rutaReservas, "¡ERROR! La ruta del archivo reservas.csv No existe")
                    && Ficheros.validarArchivo(rutaReservas, "¡ERROR! El archivo reservas.csv No existe"))) {
                
                // MUESTRA EL ARCHIVO Y PETICIÓN DE ID
                Ficheros.mostrarArchivo(rutaReservas);
            }
            
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
