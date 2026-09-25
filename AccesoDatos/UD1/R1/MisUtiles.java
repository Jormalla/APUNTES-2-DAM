package AccesoDatos.UD1.R1;

import java.util.ArrayList;
import java.util.Scanner;

public class MisUtiles {
    /**
     * FUNCIÓN PARA IMPRIMIR POR PANTALLA UN MENSAJE DE SEGUIDO
     * 
     * @param mensajeAMostrar Mensaje que se imprime por pantalla
     */
    public static void console(String mensajeAMostrar) {
        System.out.print(mensajeAMostrar);
    }

    /**
     * FUNCIÓN PARA IMPRIMIR POR PANTALLA EN LA SIGUIENTE LINEA
     * 
     * @param mensajeAMostrar Mensaje que se imprime por pantalla
     */
    public static void consoleLn(String mensajeAMostrar) {
        System.out.println(mensajeAMostrar);
    }

    /**
     * FUNCIÓN PARA PEDIR UN NÚMERO ENTERO Y COMPROBAR QUE LO INTRODUCIDO SEA UN
     * NÚMERO ENTERO
     * 
     * @param mensajeInput Mensaje de petición
     * @param mensajeError Mensaje que se lanza en caso de que no haya introducido
     *                     correctamente lo que se pide
     * @param scanner      Objeto scanner para leer lo introducido
     * @return Devuelve el número entero ingresado.
     */
    public static int pideNumeroEntero(String mensajeInput,
            String mensajeError, Scanner scanner) {
        boolean noEsEntero = true;
        int numeroEntero = 0;
        do {
            try {
                noEsEntero = false;
                console(mensajeInput);
                numeroEntero = Integer.parseInt(scanner.nextLine());

            } catch (Exception e) {
                noEsEntero = true;
                consoleLn(mensajeError);
            }
        } while (noEsEntero);
        return numeroEntero;
    }

    /**
     * FUNCIÓN PARA PEDIR UN NÚMERO DOUBLE Y COMPROBAR QUE LO INTRODUCIDO SEA UN
     * NÚMERO DOUBLE
     * 
     * @param mensajeInput Mensaje de petición
     * @param mensajeError Mensaje que se lanza en caso de que no haya introducido
     *                     correctamente lo que se pide
     * @param scanner      Objeto scanner para leer lo introducido
     * @return Devuelve el número double ingresado.
     */
    public static double pideNumeroDouble(String mensajeInput,
            String mensajeError, Scanner scanner) {
        boolean noEsDouble = true;
        double numeroDouble = 0;
        do {
            try {
                noEsDouble = false;
                console(mensajeInput);
                numeroDouble = Double.parseDouble(scanner.nextLine());

            } catch (Exception e) {
                noEsDouble = true;
                consoleLn(mensajeError);
            }
        } while (noEsDouble);
        return numeroDouble;
    }

    /**
     * FUNCIÓN QUE COMPRUEBA SI UN NÚMERO ENTERO ESTÁ EN UN RANGO NUMÉRICO
     * 
     * @param valorMinimo  Valor mínimo que debe tener el número entero
     * @param valorMaximo  Valor máximo que debe tener el número entero
     * @param mensajeInput Mensaje de petición del número
     * @param mensajeError Mensaje de error del número en caso de que no cumpla las
     *                     condiciones
     * @param scanner      Objeto de tipo scanner
     * @return Devuelve el número entero ingresado
     */
    public static int pideNumeroEnteroEnRango(int valorMinimo, int valorMaximo,
            String mensajeInput, String mensajeError, Scanner scanner) {
        int numeroAComprobar;
        do {
            numeroAComprobar = pideNumeroEntero(mensajeInput, mensajeError, scanner);
            if (numeroAComprobar < valorMinimo || numeroAComprobar > valorMaximo) {
                consoleLn(mensajeError);
            }
        } while (numeroAComprobar < valorMinimo || numeroAComprobar > valorMaximo);

        return numeroAComprobar;
    }

    /**
     * MÉTODO NUMERO POSITIVO ENTERO
     * Este método permite hacer una petición de un número int y hará un control de
     * errores que repetirá hasta que el número ingresado sea positivo.
     * 
     * @param mensajeInput Mensaje que le aparecerá al usuario para la petición.
     * @param mensajeError Mensaje de error en caso de introducir incorrectamente
     *                     los datos.
     * @param scanner      Objeto de tipo scanner que registrará lo introducido por
     *                     el usuario.
     * @return Devuelve el número entero positivo
     */
    public static int pideNumeroEnteroPositivo(String mensajeInput, String mensajeError, Scanner scanner) {
        int numeroAComprobar;
        do {
            numeroAComprobar = pideNumeroEntero(mensajeInput, mensajeError, scanner);
            if (numeroAComprobar <= 0) {
                consoleLn(mensajeError);
            }
        } while (numeroAComprobar <= 0);

        return numeroAComprobar;
    }

    /**
     * FUNCIÓN QUE COMPRUEBA SI UN NÚMERO DOUBLE ESTÁ EN UN RANGO NUMÉRICO
     * 
     * @param minNumero    Valor mínimo que debe tener el número double
     * @param maxNumero    Valor máximo que debe tener el número double
     * @param mensajeInput Mensaje de petición del número
     * @param mensajeError Mensaje de error del número en caso de que no cumpla las
     *                     condiciones
     * @param scanner      Objeto de tipo scanner
     * @return Devuelve el número double ingresado
     */
    public static double pideNumeroDoubleEnRango(double minNumero, double maxNumero,
            String mensajeInput, String mensajeError, Scanner scanner) {
        double numeroAComprobar;
        do {
            numeroAComprobar = pideNumeroDouble(mensajeInput, mensajeError, scanner);
            if (numeroAComprobar < minNumero || numeroAComprobar > maxNumero) {
                consoleLn(mensajeError);
            }
        } while (numeroAComprobar < minNumero || numeroAComprobar > maxNumero);

        return numeroAComprobar;
    }

    /**
     * AÑADE A UNA LISTA DE ENTEROS SI EL ELEMENTO NO ESTABA PREVIAMENTE
     * 
     * @param elemento     elemento que vamos a añadir
     * @param listaEnteros lista a la que vamos a añadir el entero
     * @return devuelve true si se pudo añadir, false si ya estaba en la lista.
     */
    public static boolean aniadeALista(int elemento, ArrayList<Integer> listaEnteros) {
        boolean aniadidoConExito;
        if (listaEnteros.contains(elemento)) {
            aniadidoConExito = false;
        } else {
            listaEnteros.add(elemento);
            aniadidoConExito = true;
        }
        return aniadidoConExito;
    }

    /**
     * AÑADE A UNA LISTA DE CADENAS SI EL ELEMENTO NO ESTABA PREVIAMENTE
     * 
     * @param elemento     elemento que vamos a añadir
     * @param listaCadenas lista a la que vamos a añadir la cadena
     * @return devuelve true si se pudo añadir, false si ya estaba en la lista.
     */
    public static boolean aniadeALista(String elemento, ArrayList<String> listaCadenas) {
        boolean aniadidoConExito;
        if (listaCadenas.contains(elemento)) {
            aniadidoConExito = false;
        } else {
            listaCadenas.add(elemento);
            aniadidoConExito = true;
        }
        return aniadidoConExito;
    }

    /**
     * FUNCIÓN PARA VISUALIZAR DE FORMA AMIGABLE UN ARRAY DE ENTEROS
     *
     * @param separador    El separador por el cual vamos "separar" cada elemento de
     *                     la cadena de numeros
     * @param listaEnteros Array de tipo Integer que queremos mostrar de forma
     *                     amigable
     * @return devuelve el array pero en forma de cadena, amigable a nivel de
     *         usuario
     */
    public static String joinInt(String separador, ArrayList<Integer> listaEnteros) {
        String listaNumerosEnCadena = "";
        for (int numero : listaEnteros) {
            listaNumerosEnCadena += numero + separador;
        }
        listaNumerosEnCadena = listaNumerosEnCadena.substring(0, listaNumerosEnCadena.length() - separador.length());
        return listaNumerosEnCadena;
    }

    /**
     * FUNCIÓN PARA VISUALIZAR DE FORMA AMIGABLE UN ARRAY DOUBLE
     *
     * @param separador   El separador por el cual vamos "separar" cada elemento de
     *                    la cadena de numeros double
     * @param listaDouble Array de tipo Double que queremos mostrar de forma
     *                    amigable
     * @return devuelve el array pero en forma de cadena, amigable a nivel de
     *         usuario
     */
    public static String joinDouble(String separador, ArrayList<Double> listaDouble) {
        String listaDoubleEnCadena = "";
        for (double numero : listaDouble) {
            listaDoubleEnCadena += numero + separador;
        }
        listaDoubleEnCadena = listaDoubleEnCadena.substring(0, listaDoubleEnCadena.length() - separador.length());
        return listaDoubleEnCadena;
    }

    /**
     * FUNCIÓN PARA CONTROL ERRORES CADENA VACÍA
     * 
     * Esta función permite hacer una petición de nombres o títulos y comprobar si
     * el usuario ha introducido algo. Si no introduce nada le envía un mensaje de
     * ERROR
     * 
     * 
     * @param mensajeInput Mensaje de petición que se mostrará al usuario.
     * @param scanner      Objeto de tipo scanner que servirá para detectar lo
     *                     introducido por el usuario.
     * @return Devuelve la cadena con el control de errores ya hecho.
     * 
     * 
     *         La función {@link #console(String)} y {@link #consoleLn(String)}
     *         permiten mostrar por pantalla el contenido introducido en formato
     *         string.
     * 
     */
    public static String validarCadenaNoVacia(String mensajeInput, String mensajeError, Scanner scanner) {
        String cadenaIntroducida = "";
        // Petición de datos
        do {
            console(mensajeInput);
            cadenaIntroducida = scanner.nextLine();
            // Mensaje del control de errores
            if (cadenaIntroducida.trim().isEmpty()) {
                consoleLn(mensajeError);
            }
        } while (cadenaIntroducida.trim().isEmpty());

        return cadenaIntroducida;
    }

    /**
     * MÉTODO PEDIR SELECCIÓN
     * Método que hace la petición de la selección de un menú, validando que lo
     * introducido sea posible.
     * 
     * @param interfaz         Referencia a la interfaz que se le muestra al
     *                         usuario.
     * @param scanner          Referencia al objeto scanner para la petición de
     *                         datos.
     * @param opcionesPosibles Almacena las posibles opciones en un ArrayList de
     *                         String.
     * @param mensajeError     Mensaje que se mostrará al usuario si lo introducido
     *                         es no válido.
     * @return
     */
    public static String pideSeleccion(String interfaz, Scanner scanner, ArrayList<String> opcionesPosibles,
            String mensajeError) {
        String seleccion = "";
        do {
            // Muestra la interfaz y almacena lo introducido por el usuario.
            consoleLn(interfaz);
            seleccion = scanner.nextLine();

            // Mensaje de error Menu
            if (!opcionesPosibles.contains(seleccion)) {
                consoleLn(mensajeError);
            }

        } while (!opcionesPosibles.contains(seleccion));

        return seleccion;
    }

    /**
     * MÉTODO PAR
     * Este método indica si un número es o no es par.
     * 
     * @param numero Número del que se desea saber si es par.
     * @return Devuelve true (par) o false (impar).
     */
    public static boolean esPar(int numero) {
        boolean numeroPar = false;
        // El número es par
        if (numero % 2 == 0) {
            numeroPar = true;
        }
        return numeroPar;
    }

    /**
     * MÉTODO PARA OBTENER UN NÚMERO ALEATORIO ENTERO DENTRO DE UN RANGO
     * Este método permite obtener un número de forma aleatoria dentro de un rango
     * delimitado por el mínimo y el máximo introducidos como parámetros.
     * 
     * @param minimo Número mínimo al que puede aspirar el número randomizado.
     * @param maximo Número máximo al que puede aspirar el número randomizado.
     * @return Devuelve un número aleatorio dentro del rango estipulado.
     */
    public static int numeroAleatorio(int minimo, int maximo) {
        return (int) (Math.random() * (maximo - minimo + 1) + minimo);
    }

    /**
     * MÉTODO PARA CONFIRMAR ACCIONES DELICADAS
     * Este método está enfocado en la aparición de un mensaje de confirmación para
     * evitar acciones desafortunadas.
     * 
     * @param mensajeInputConfirmacion Mensaje que se le va a mostar al usuario
     * @param scanner                  Objeto de tipo scanner que leera lo
     *                                 introducido por el usuario
     * @return Devuelve la confirmación o denegación del usuario.
     */
    public static boolean confirmarAccion(String mensajeInputConfirmacion, Scanner scanner) {
        boolean confirma = true;
        boolean inputValido = false;
        String respuesta = "";

        do {
            inputValido = true;
            // Muestra mensaje de confirmación
            console(mensajeInputConfirmacion + " -- [S/N]: ");
            respuesta = scanner.nextLine();
            // No manda una opción posible
            if (!respuesta.toUpperCase().equals("N") && !respuesta.toUpperCase().equals("S")) {
                consoleLn("¡ERROR! Introduce S (Sí) o N (No)");
                inputValido = false;
            }
        } while (!inputValido);

        // Deniega la confirmación
        if (respuesta.toUpperCase().equals("N")) {
            confirma = false;
        }

        return confirma;

    }
}
