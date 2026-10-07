package AccesoDatos.UD1.R2;

import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.ArrayList;
import Bibliotecas.Ficheros;
import Bibliotecas.MisUtiles;

public class Ejercicio5 {
    public static void main(String[] args) {
        // VARIABLES
        int idLibro;
        String tituloLibro, autor;
        ArrayList<String> todosLosIds = new ArrayList<>();
        ArrayList<String> todosLosTitulos = new ArrayList<>();
        Path rutaBiblioteca = Path.of("AccesoDatos", "UD1", "R2", "biblioteca.csv");
        boolean libroExiste = false;
        boolean idExiste = false;
        ArrayList<String> nuevas = new ArrayList<>();

        // MENSAJES
        final String MENSAJE_PETICION_ID = "Ingrese el id del libro: ",
                MENSAJE_PETICION_TITULO = "Ingrese el título del libro: ",
                ERROR_NUMERO_ID = "¡ERROR! Debes ingresar un número entero mayor a 0.",
                ERROR_CAMPO_VACIO = "¡ERROR! No debes dejar este campo vacío.";
        final String MENSAJE_PETICION_AUTOR = "Ingrese el nombre del autor: ";
        // VERIFICA QUE EXISTA RUTA Y ARCHIVO
        try {
            if ((Ficheros.validarRuta(rutaBiblioteca, "¡ERROR! La ruta del archivo biblioteca.csv No existe")
                    && Ficheros.validarArchivo(rutaBiblioteca, "¡ERROR! El archivo biblioteca.csv No existe"))) {
                // GUARDA LAS IDs DEL ARCHIVO y los titulos
                Ficheros.guardarColumnaCsv(todosLosIds, rutaBiblioteca, 1);
                Ficheros.guardarColumnaCsv(todosLosTitulos, rutaBiblioteca, 2);

                // PETICIÓN DE DATOS
                Scanner input = new Scanner(System.in);
                // PETICION ID
                do {
                    idLibro = MisUtiles.pideNumeroEnteroPositivo(MENSAJE_PETICION_ID, ERROR_NUMERO_ID, input);
                    // COMPRUEBA SI EXISTE LA ID
                    if (todosLosIds.contains(idLibro + "")) {
                        idExiste = true;
                        MisUtiles.consoleLn("¡VAYA!, parece que la id" + idLibro + " ya existe en el documento.");
                    }
                } while (idExiste);

                // PETICIÓN DEL TÍTULO
                do {
                    tituloLibro = MisUtiles.validarCadenaNoVacia(MENSAJE_PETICION_TITULO, ERROR_CAMPO_VACIO, input);
                    // COMPRUEBA SI EL LIBRO EXISTE
                    for (String titulo : todosLosTitulos) {
                        if (titulo.equalsIgnoreCase(tituloLibro.trim())) {
                            libroExiste = true;
                            MisUtiles.consoleLn("¡VAYA!, parece que " + tituloLibro + " ya existe en el documento.");
                        }
                    }
                } while (libroExiste);

                // PETICIÓN AUTOR
                autor = MisUtiles.validarCadenaNoVacia(MENSAJE_PETICION_AUTOR, ERROR_CAMPO_VACIO, input);

                // ESCRIBE LA NUEVA LÍNEA
                try (BufferedWriter salida = Files.newBufferedWriter(
                        rutaBiblioteca, StandardCharsets.UTF_8,
                        StandardOpenOption.CREATE,
                        StandardOpenOption.APPEND)) {
                    salida.write(idLibro +";"+ tituloLibro + ";" + autor);
                    salida.newLine();
                }
                input.close();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }
}
