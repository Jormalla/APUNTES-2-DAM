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
        int cantidadStock, numeroColumnasNecesarias = 3, idDeFila;
        String id, nombreProducto = "";
        Path rutaInventario = Path.of("AccesoDatos", "UD1", "R2", "inventario.csv");
        final String MENSAJE_ERROR_ID = "¡ERROR! Debes introducir un id válido",
                MENSAJE_ERROR_STOCK = "¡ERROR! Debe ser una cantidad posible";
        ArrayList<String> ids_posibles = new ArrayList<>();
        String[] columnas;
        List<String> nuevas = new ArrayList<>();

        Scanner teclado = new Scanner(System.in);

        try {
            // Comprueba ruta y existencia del archivo
            if (Ficheros.validarRuta(rutaInventario, "¡ERROR! La ruta del archivo inventario.csv No existe")
                    && Ficheros.validarArchivo(rutaInventario, "¡ERROR! El archivo inventario.csv No existe")) {

                // Recorre el contenido del archivo
                List<String> contenidoArchivo = Files.readAllLines(rutaInventario, StandardCharsets.UTF_8);
                for (int i = 1; i < contenidoArchivo.size(); i++) {
                    columnas = contenidoArchivo.get(i).split(";", -1);

                    // Comprueba que el archivo tenga 3 columnas y que la columna ID tenga un número
                    // válido
                    if (columnas.length == numeroColumnasNecesarias) {
                        try {
                            nombreProducto = columnas[1];
                            idDeFila = Integer.parseInt(columnas[0]);

                            // Guardo el contenido de cada primera columna en las opciones posibles (La id)
                            ids_posibles.add(columnas[0]);

                            // NO TIENE UN NÚMERO VÁLIDO
                        } catch (NumberFormatException e) {
                            System.err.println("El ID de " + nombreProducto + " no contiene un número válido.");
                        }
                    } else {
                        MisUtiles.consoleLn("¡VAYA! La fila " + (i - 1) + " parece que no contiene "
                                + numeroColumnasNecesarias + " columnas.");
                    }
                }

                // Muestra el contenido del archivo
                try {
                    Ficheros.mostrarArchivo(rutaInventario);
                } catch (IOException e) {
                    System.err.println("No se pudo leer: " + e.getMessage());
                }

                // Petición de la id
                id = MisUtiles.pideSeleccion("Introduce la id del producto que desea modificar: ", teclado, ids_posibles, MENSAJE_ERROR_ID);
                // Petición de la cantidad Stock
                cantidadStock = MisUtiles.pideNumeroEnteroPositivo("Introduce una cantidad de stock: ",
                        MENSAJE_ERROR_STOCK, teclado);

                // Recorre el archivo hasta encontrar el id y modifica su stock
                for (int i = 0; i < contenidoArchivo.size(); i++) {
                    columnas = contenidoArchivo.get(i).split(";", -1);
                    if (columnas[0].equals(id)) {
                        columnas[2] = cantidadStock + "";
                        nuevas.add(String.join(";", columnas));
                    // Si no es la id, mete la linea al completo
                    }else{
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
