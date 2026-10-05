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
}
