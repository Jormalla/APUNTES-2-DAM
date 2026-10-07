package Bibliotecas;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Ficheros {
    /**
     * MÉTODO PARA COMPROBAR SI UNA RUTA EXISTE CON UN MENSAJE DE ERROR SI NO EXISTE
     * Se hace uso de la biblioteca MisUtiles para mostrar por consola el mensaje de
     * error
     * 
     * @param ruta         ruta que se quiere comprobar
     * @param mensajeError contiene el mensaje de error que lanzará por consola si
     *                     la ruta no existe
     * @return devuelve true o false dependiendo de si existe o no la ruta
     * 
     * @see MisUtiles
     */
    public static boolean validarRuta(Path ruta, String mensajeError) {
        boolean existe = Files.exists(ruta);
        if (!existe) {
            MisUtiles.consoleLn(mensajeError);
        }

        return existe;
    }

    /**
     * MÉTODO PARA COMPROBAR SI UN ARCHIVO EXISTE CON UN MENSAJE DE ERROR SI NO
     * EXISTE
     * Se hace uso de la biblioteca MisUtiles para mostrar por consola el mensaje de
     * error
     * 
     * @param archivo      archivo que se quiere comprobar
     * @param mensajeError contiene el mensaje de error que lanzará por consola si
     *                     el archivo no existe
     * @return devuelve true o false dependiendo de si existe o no la el archivo
     * 
     * @see MisUtiles
     */
    public static boolean validarArchivo(Path archivo, String mensajeError) {
        boolean existe = Files.isRegularFile(archivo);
        if (!existe) {
            MisUtiles.consoleLn(mensajeError);
        }
        return existe;
    }

    /**
     * MÉTODO PARA MOSTRAR EL CONTENIDO DE UN ARCHIVO POR CONSOLA
     * 
     * @param archivo Ruta del archivo que se va a revelar
     * @throws IOException Lanza una excepción en caso de error
     * 
     * @see MisUtiles
     */
    public static void mostrarArchivo(Path archivo) throws IOException {
        //
        try (BufferedReader entrada = Files.newBufferedReader(
                archivo, StandardCharsets.UTF_8)) {
            String linea;
            while ((linea = entrada.readLine()) != null) {
                MisUtiles.consoleLn(linea);
            }
        }
    }

    /**
     * MÉTODO PARA GUARDAR EN UNA LISTA TODOS LOS CAMPOS DE UNA COLUMNA DE UN CSV
     * No almacena la cabecera
     * 
     * @param lista             Lista donde se almacenarán todos los campos de la
     *                          columna
     * @param rutaArchivo       Ruta donde se ubica el archivo
     * @param columnaLocalizada Columna que se quiere guardar
     * @throws IOException Lanza una excepción si no se tiene permisos en el archivo
     */
    public static void guardarColumnaCsv(ArrayList<String> lista, Path rutaArchivo, int columnaLocalizada)
            throws IOException {
        List<String> contenidoArchivo = Files.readAllLines(rutaArchivo, StandardCharsets.UTF_8);
        String[] columnas;
        // Recorre el csv y guarda las ids
        for (int i = 1; i < contenidoArchivo.size(); i++) { // Salta la cabecera
            columnas = contenidoArchivo.get(i).split(";", -1);
            lista.add(columnas[columnaLocalizada - 1]);
        }
    }

    /**
     * METODO PARA ORDENAR UN CSV POR ID
     * 
     * @param rutaCsv ruta del archivo csv
     * @param columnaId columna donde se ubica la id
     * @throws IOException Lanza una excepción si no se tiene permisos en el archivo
     * @throws NumberFormatException Lanza un error si ubica una id como un no número
     */
    public static void ordenarCsvPorId(Path rutaCsv, int columnaId) {
        try {
            //Lee todas las líneas del archivo
            List<String> lineas = Files.readAllLines(rutaCsv);

            // Archivo vacío (puede que tenga la cabecera)
            if (lineas.size() <= 1) {
                return; 
            }

            // Separa la cabecera y pasa los datos a una lista
            String cabecera = lineas.get(0);
            List<String> datos = new ArrayList<>();
            for (int i = 1; i < lineas.size(); i++) {
                datos.add(lineas.get(i));
            }

            // Ordena el csv
            for (int i = 0; i < datos.size() - 1; i++) {
                for (int j = 0; j < datos.size() - i - 1; j++) {
                    // Obtiene el ID numérico de la fila actual 
                    String[] columnasActual = datos.get(j).split(";");
                    int idActual = Integer.parseInt(columnasActual[columnaId - 1].trim());

                    // Obtiene el ID numérico de la siguiente fila
                    String[] columnasSiguiente = datos.get(j + 1).split(";");
                    int idSiguiente = Integer.parseInt(columnasSiguiente[columnaId - 1].trim());

                    // Compara las id, si es mayor la actual se cambian las posiciones
                    if (idActual > idSiguiente) {
                        String temporal = datos.get(j);
                        datos.set(j, datos.get(j + 1));
                        datos.set(j + 1, temporal);
                    }
                }
            }

            // Reconstruye la lista completa colocando la cabecera al principio
            List<String> resultadoFinal = new ArrayList<>();
            resultadoFinal.add(cabecera);
            resultadoFinal.addAll(datos);

            // Guarda los cambios en el archivo
            Files.write(rutaCsv, resultadoFinal, StandardCharsets.UTF_8);

        } catch (IOException e) {
            System.err.println("¡ERROR! Hubo un problema al leer o escribir: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("¡ERROR! La columna id no tiene un número entero válido.");
        } 
    }
}
