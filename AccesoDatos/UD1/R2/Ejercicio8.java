package AccesoDatos.UD1.R2;

import java.io.BufferedWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import Bibliotecas.Ficheros;
import Bibliotecas.MisUtiles;

public class Ejercicio8 {
    public static void main(String[] args) {
        // VARIABLES
        Path rutaPrestamos = Path.of("AccesoDatos", "UD1", "R2", "prestamos.csv");
        Scanner input = new Scanner(System.in);
        final String CABECERA = "id;persona;material";
        boolean sigueEnMenu = true;
        String seleccionMenu = "";
        ArrayList<String> idsArchivo = new ArrayList<>();
        final ArrayList<String> OPCIONESMENU = new ArrayList<>(Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8"));
        int id;
        String nombre, material;

        // MENSAJES
        final String MENSAJE_ERROR_MENU = "¡ERROR! Debes seleccionar una opción posible";
        final String INTERFAZMENU = "===== MENU =====\n(1) Nuevo préstamo\n(2) Listar préstamos\n(3) Buscar préstamo\n(4) Cambiar material\n(5) Dar de baja un préstamo\n(6) Crear copia\n(7) Restaurar copia\n(8) Salir del programa\nIngrese una opción: ";
        final String PETICION_ID = "Ingrese la id: ";
        final String ERROR_NUMERO_NEGATIVO = "¡ERROR! Debes ingresar un número mayor o igual a 0.";
        final String ERROR_EL_ID_EXISTE = "¡ERROR! Este id ya existe.";
        final String DESEA_CONTINUAR = "¿Quieres continuar con el proceso?";
        final String PETICION_NOMBRE = "Ingrese el nombre: ";
        final String ERROR_CAMPO_VACIO = "¡ERROR! Este campo no debe quedar vacío";
        final String PETICION_MATERIAL = "Ingrese el material: ";

        try {
            // Si no existe el archivo le pregunta si quiere crearlo
            if (!Ficheros.validarRuta(rutaPrestamos, "La ruta del archivo prestamos.csv no existe.")
                    || !Ficheros.validarArchivo(rutaPrestamos, "El archivo prestamos.csv no es un archivo.")) {
                if (MisUtiles.confirmarAccion("¿Quiéres crear el archivo prestamos.csv?", input)) {
                    Files.createFile(rutaPrestamos);
                    // Le añade la cabecera
                    try (BufferedWriter salida = Files.newBufferedWriter(
                            rutaPrestamos, StandardCharsets.UTF_8,
                            StandardOpenOption.CREATE,
                            StandardOpenOption.APPEND)) {
                        salida.write(CABECERA);
                        salida.newLine();
                    }
                } else {
                    return;
                }
            }

            // GUARDAR LOS IDS
            Ficheros.guardarColumnaCsv(idsArchivo, rutaPrestamos, 1);

            // MENU DEL PROGRAMA
            while (sigueEnMenu) {
                seleccionMenu = MisUtiles.pideSeleccion(INTERFAZMENU, input, OPCIONESMENU, MENSAJE_ERROR_MENU);

                // =================== OPCIÓN 1: NUEVO PRÉSTAMO ======================= //
                if (seleccionMenu.equals("1")) {
                    // Pide el id, si está repetido le pregunta si quiere continuar
                    boolean deseaContinuar;
                    do {
                        deseaContinuar = true;
                        id = MisUtiles.pideNumeroEnteroNoNegativo(PETICION_ID, ERROR_NUMERO_NEGATIVO, input);

                        if (idsArchivo.contains(id + "")) {
                            MisUtiles.consoleLn(ERROR_EL_ID_EXISTE);
                            if (!MisUtiles.confirmarAccion(DESEA_CONTINUAR, input)) {
                                deseaContinuar = false;
                            }
                        }
                    } while (idsArchivo.contains(id + "") && deseaContinuar);

                    // DESEA CONTINUAR - pide el nombre y material - añade el registro
                    if (deseaContinuar) {
                        nombre = MisUtiles.validarCadenaNoVacia(PETICION_NOMBRE, ERROR_CAMPO_VACIO, input);
                        material = MisUtiles.validarCadenaNoVacia(PETICION_MATERIAL, ERROR_CAMPO_VACIO, input);

                        // Añade el registro
                        try (BufferedWriter salida = Files.newBufferedWriter(
                                rutaPrestamos, StandardCharsets.UTF_8,
                                StandardOpenOption.CREATE,
                                StandardOpenOption.APPEND)) {
                            salida.write(id + ";" + nombre + ";" + material);
                            salida.newLine();
                        }
                        // Ordena el csv
                        Ficheros.ordenarCsvPorId(rutaPrestamos, 1);
                    }
                } else if (seleccionMenu.equals("2")) {

                } else if (seleccionMenu.equals("3")) {

                } else if (seleccionMenu.equals("4")) {

                } else if (seleccionMenu.equals("5")) {

                } else if (seleccionMenu.equals("6")) {

                } else if (seleccionMenu.equals("7")) {

                } else {
                    MisUtiles.consoleLn("¡Hasta pronto!");
                }
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        input.close();
    }
}
