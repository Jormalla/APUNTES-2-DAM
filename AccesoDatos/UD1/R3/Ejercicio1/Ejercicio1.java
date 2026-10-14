package AccesoDatos.UD1.R3.Ejercicio1;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import Bibliotecas.Ficheros;

public class Ejercicio1 {
    public static void main(String[] args) {
        // VARIABLES
        Path rutaProductos = Path.of("AccesoDatos", "UD1", "R3", "Ejercicio1", "datos", "productos.dat");

        // MENSAJES
        final String MENSAJE_CREACION_RUTA = "El directorio 'datos/' no existe.\n Creando ruta y archivo '/productos.dat' ...";
        final String MENSAJE_CREACION_ARCHIVO = "El archivo '/productos.dat' no existe. Creando archivo ...";
        try {
            // CREA LA RUTA Y ARCHIVO SI NO EXISTE
            if (!Ficheros.validarRuta(rutaProductos, MENSAJE_CREACION_RUTA)
                    || !Ficheros.validarArchivo(rutaProductos, MENSAJE_CREACION_ARCHIVO)) {
                Files.createDirectories(rutaProductos.getParent());
                Files.createFile(rutaProductos);
            }
        } catch (IOException e) {
            System.err.println("¡ERROR! No se pudo crear la ruta o el archivo" + e.getMessage());
        }

        // Creación de los productos
        List<Producto> productos = new ArrayList<>();
        productos.add(new Producto(1, "PC MSI", 1200.20, 10));
        productos.add(new Producto(2, "Ratón logitech", 45.50, 130));
        productos.add(new Producto(3, "Monitor DELL", 120.99, 100));

        // Ingreso de datos en el archivo
        try (ObjectOutputStream salida = new ObjectOutputStream(
                Files.newOutputStream(rutaProductos))) {
            salida.writeObject(productos);

            System.out.println("Productos guardados en " + rutaProductos);
        } catch (IOException e) {
            System.err.println("Error al guardar: " + e.getMessage());
        }

    }
}
