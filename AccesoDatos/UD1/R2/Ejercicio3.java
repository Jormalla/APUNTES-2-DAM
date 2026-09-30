package AccesoDatos.UD1.R2;

import java.nio.file.Path;

import Bibliotecas.Ficheros;

public class Ejercicio3 {
    public static void main(String[] args) {
        int id, cantidadStock;
        Path rutaInventario = Path.of("AccesoDatos", "UD1", "R2", "inventario.csv");

        try {
            if (Ficheros.validarRuta(rutaInventario, "¡ERROR! La ruta del archivo inventario.csv No existe")
                    && Ficheros.validarArchivo(rutaInventario, "¡ERRO! El archivo inventario.csv No existe")) {
                
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
