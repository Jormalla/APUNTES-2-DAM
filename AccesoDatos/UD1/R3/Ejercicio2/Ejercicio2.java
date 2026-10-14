package AccesoDatos.UD1.R3.Ejercicio2;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Bibliotecas.MisUtiles;
import Bibliotecas.Ficheros;

public class Ejercicio2 {
    public static void main(String[] args) {
        // VARIABLES
        Path rutaProductos = Path.of("AccesoDatos", "UD1", "R3", "Ejercicio2", "datos", "productos.dat");
        List<Integer> idsProductos = new ArrayList<>();

        // MENSAJES
        final String MENSAJE_CREACION_RUTA = "El directorio 'datos/' no existe.\n Creando ruta y archivo '/productos.dat' ...";
        final String MENSAJE_CREACION_ARCHIVO = "El archivo '/productos.dat' no existe. Creando archivo ...";
        final String ARCHIVO_NO_CONTIENE_LISTA = "El archivo no contiene una lista.";
        final String DATO_INESPERADO = "Se ha encontrado un dato inesperado.";
        final String PETICION_ID = "Ingrese la id del producto: ";
        final String ERROR_PETICION_ID = "¡ERROR! No existe ningún producto con esa id.";
        final String PETICION_STOCK = "Ingrese el nuevo stock del producto: ";
        final String ERROR_STOCK = "¡ERROR! Debes ingresar un stock mayor o igual a 0.";
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

            MisUtiles.consoleLn("Productos guardados en " + rutaProductos);
        } catch (IOException e) {
            MisUtiles.error("Error al guardar: " + e.getMessage());
        }

        // MUESTRA PRODUCTOS, GUARDA LOS PRODUCTOS Y GUARDA LAS IDS
        productos = new ArrayList<>();
        try (ObjectInputStream entrada = new ObjectInputStream(
                Files.newInputStream(rutaProductos))) {
            Object dato = entrada.readObject();
            // NO HAY LISTAS DE PRODUCTOS
            if (!(dato instanceof List<?> lista)) {
                MisUtiles.consoleLn(ARCHIVO_NO_CONTIENE_LISTA);
                return;
            }
            for (Object elemento : lista) {
                if (!(elemento instanceof Producto producto)) {
                    MisUtiles.consoleLn(DATO_INESPERADO);
                    return;
                }
                MisUtiles.consoleLn(producto + "");
                // Guarda las ids de los productos para pedir la selección después y los
                // productos
                idsProductos.add(producto.getId());
                productos.add(producto);
            }
        } catch (IOException | ClassNotFoundException e) {
            MisUtiles.error("No se pudo recuperar: " + e.getMessage());
        }

        // MODIFICAR PRODUCTO
        Scanner input = new Scanner(System.in);
        boolean idCorrecta;
        int id;
        // Petición id
        do {
            idCorrecta = true;
            id = MisUtiles.pideNumeroEntero(PETICION_ID, ERROR_PETICION_ID, input);
            if (!idsProductos.contains(id)) {
                MisUtiles.consoleLn(ERROR_PETICION_ID);
                idCorrecta = false;
            }
        } while (!idCorrecta);

        // Petición Stock
        int stock = MisUtiles.pideNumeroEnteroPositivo(PETICION_STOCK, ERROR_STOCK, input);

        // Cambia el stock del producto
        for (Producto producto : productos) {
            if (producto.getId() == id) {
                producto.setStock(stock);
            }
        }

        // REESCRIBE EL .DAT
        try (ObjectOutputStream salida = new ObjectOutputStream(
                Files.newOutputStream(rutaProductos))) {
            salida.writeObject(productos);

            MisUtiles.consoleLn("Stock del producto modificado");
        } catch (IOException e) {
            MisUtiles.error("Error al guardar: " + e.getMessage());
        }

        // MUESTRA EL .DAT
        try (ObjectInputStream entrada = new ObjectInputStream(
                Files.newInputStream(rutaProductos))) {
            Object dato = entrada.readObject();
            // NO HAY LISTAS DE PRODUCTOS
            if (!(dato instanceof List<?> lista)) {
                MisUtiles.consoleLn(ARCHIVO_NO_CONTIENE_LISTA);
                return;
            }
            for (Object elemento : lista) {
                if (!(elemento instanceof Producto producto)) {
                    MisUtiles.consoleLn(DATO_INESPERADO);
                    return;
                }
                MisUtiles.consoleLn(producto + "");
            }
        } catch (IOException | ClassNotFoundException e) {
            MisUtiles.error("No se pudo recuperar: " + e.getMessage());
        }
        input.close();
    }
}