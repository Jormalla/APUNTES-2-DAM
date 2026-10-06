package AccesoDatos.UD1.R2;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

import Bibliotecas.Ficheros;
import Bibliotecas.MisUtiles;

public class Ejercicio5 {
    public static void main(String[] args) {
        // VARIABLES
        int idLibro;
        String tituloLibro;
        ArrayList<String> todosLosIds = new ArrayList<>();
        ArrayList<String> todosLosTitulos = new ArrayList<>();
        Path rutaBiblioteca = Path.of("AccesoDatos", "UD1", "R2", "biblioteca.csv");

        // MENSAJES
        final String MENSAJE_PETICION_ID = "Ingrese el id del libro: ", MENSAJE_PETICION_TITULO = "Ingrese el título del libro: ", ERROR_NUMERO_ID = "¡ERROR! Debes ingresar un número entero mayor a 0.";

        // VERIFICA QUE EXISTA RUTA Y ARCHIVO
        try {
            if ((Ficheros.validarRuta(rutaBiblioteca, "¡ERROR! La ruta del archivo biblioteca.csv No existe")
                    && Ficheros.validarArchivo(rutaBiblioteca, "¡ERROR! El archivo biblioteca.csv No existe"))) {
                // GUARDA LAS IDs DEL ARCHIVO y los titulos
                Ficheros.guardarColumnaCsv(todosLosIds, rutaBiblioteca, 1);
                Ficheros.guardarColumnaCsv(todosLosTitulos, rutaBiblioteca, 2);

                // PETICIÓN DE DATOS
                Scanner input = new Scanner(System.in);
                idLibro = MisUtiles.pideNumeroEnteroPositivo(MENSAJE_PETICION_ID, ERROR_NUMERO_ID, input);

            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
