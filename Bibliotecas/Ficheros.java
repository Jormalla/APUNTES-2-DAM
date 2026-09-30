package Bibliotecas;

import java.nio.file.Files;
import java.nio.file.Path;

public class Ficheros {
    /**
     * MÉTODO PARA COMPROBAR SI UNA RUTA EXISTE CON UN MENSAJE DE ERROR SI NO EXISTE
     * Se hace uso de la biblioteca MisUtiles para mostrar por consola el mensaje de error
     * @param ruta ruta que se quiere comprobar
     * @param mensajeError contiene el mensaje de error que lanzará por consola si la ruta no existe
     * @return devuelve true o false dependiendo de si existe o no la ruta
     * 
     * @see MisUtiles
     */
    public static boolean validarRuta(Path ruta, String mensajeError){
        boolean existe = Files.exists(ruta);
        if (!existe) {
            MisUtiles.consoleLn(mensajeError);
        }

        return existe;
    }
    
}
