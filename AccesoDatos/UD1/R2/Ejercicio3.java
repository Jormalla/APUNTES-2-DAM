package AccesoDatos.UD1.R2;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import AccesoDatos.UD1.R1.MisUtiles;
import Bibliotecas.Ficheros;

public class Ejercicio3 {
    public static void main(String[] args) {
        // VARIABLES Y RUTAS
        int cantidadStock, numeroColumnasNecesarias = 3;
        String id;
        Path rutaInventario = Path.of("AccesoDatos", "UD1", "R2", "inventario.csv");
        final String MENSAJE_ERROR_ID = "¡ERROR! Debes introducir un id válido",
                MENSAJE_ERROR_STOCK = "¡ERROR! Debe ser una cantidad posible";
        ArrayList<String> ids_posibles = new ArrayList<>();
        String[] columnas;
        List<String> nuevas = new ArrayList<>();
        List<String> avisos = new ArrayList<>();

        Scanner teclado = new Scanner(System.in);

        try {
            // Comprueba ruta y existencia del archivo
            if (Ficheros.validarRuta(rutaInventario, "¡ERROR! La ruta del archivo inventario.csv No existe")
                    && Ficheros.validarArchivo(rutaInventario, "¡ERROR! El archivo inventario.csv No existe")) {

                // Recorre el contenido del archivo (salta la cabecera)
                List<String> contenidoArchivo = Files.readAllLines(rutaInventario, StandardCharsets.UTF_8);
                for (int i = 1; i < contenidoArchivo.size(); i++) {
                    columnas = contenidoArchivo.get(i).split(";", -1);
                    int numeroLinea = i + 1; // línea real en el archivo (la 1 es la cabecera)

                    // Comprueba que la fila tenga 3 columnas y que el ID sea un número válido
                    if (columnas.length == numeroColumnasNecesarias) {
                        try {
                            Integer.parseInt(columnas[0]);
                            ids_posibles.add(columnas[0]);
                        } catch (NumberFormatException e) {
                            avisos.add("Línea " + numeroLinea + ": el ID '" + columnas[0]
                                    + "' no es un número válido.");
                        }
                    } else {
                        avisos.add("Línea " + numeroLinea + ": tiene " + columnas.length
                                + " columnas (se esperaban " + numeroColumnasNecesarias + ").");
                    }
                }

                // Muestra el contenido del archivo
                try {
                    Ficheros.mostrarArchivo(rutaInventario);
                } catch (IOException e) {
                    System.err.println("No se pudo leer: " + e.getMessage());
                }

                // Muestra las líneas incorrectas debajo del archivo
                if (!avisos.isEmpty()) {
                    System.out.println("\nLíneas que no cumplen el formato:");
                    for (String aviso : avisos) {
                        System.out.println(aviso);
                    }
                }

                // Petición de la id
                id = MisUtiles.pideSeleccion("Introduce la id del producto que desea modificar: ", teclado,
                        ids_posibles, MENSAJE_ERROR_ID);
                // Petición de la cantidad Stock
                cantidadStock = MisUtiles.pideNumeroEnteroPositivo("Introduce una cantidad de stock: ",
                        MENSAJE_ERROR_STOCK, teclado);

                // Recorre el archivo hasta encontrar el id y modifica su stock
                for (int i = 0; i < contenidoArchivo.size(); i++) {
                    columnas = contenidoArchivo.get(i).split(";", -1);
                    if (columnas.length == numeroColumnasNecesarias && columnas[0].equals(id)) {
                        columnas[2] = cantidadStock + "";
                        nuevas.add(String.join(";", columnas));
                    } else {
                        // Si no es la id, mete la línea al completo
                        nuevas.add(contenidoArchivo.get(i));
                    }
                }
                // Reescribe el archivo original
                Files.write(rutaInventario, nuevas, StandardCharsets.UTF_8);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        teclado.close();
    }
}
