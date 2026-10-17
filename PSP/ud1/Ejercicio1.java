import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Ejercicio1 {
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
     */
    public static String pedirCadenaNoVacia(String mensajeInput, String mensajeError, Scanner scanner) {
        String cadenaIntroducida = "";
        // Petición de datos
        do {
            System.out.print(mensajeInput);
            cadenaIntroducida = scanner.nextLine();
            // Mensaje del control de errores | No son errores del sistem, por eso no se
            // hace un System.err
            if (cadenaIntroducida.trim().isEmpty()) {
                System.out.println(mensajeError);
            }
        } while (cadenaIntroducida.trim().isEmpty());

        return cadenaIntroducida;
    }

    public static void main(String[] args) {
        // Se inicializa el teclado por el que el cliente escribira los comandos
        Scanner teclado = new Scanner(System.in);
        boolean sigueEscribiendo = true;
        final String MENSAJE_PETICION_COMANDO = "Comando (o <<salir>>): ";
        final String MENSAJE_ERROR_CADENA_VACIA = "Debes escribir algo.";
        final String SALIR = "salir";
        final String MENSAJE_CODIGO_SALIDA = "Código de salida: ";

        while (sigueEscribiendo) {
            String comandoEscrito = pedirCadenaNoVacia(MENSAJE_PETICION_COMANDO, MENSAJE_ERROR_CADENA_VACIA, teclado);
            // ESCRIBE SALIR
            if (comandoEscrito.equalsIgnoreCase(SALIR)) {
                sigueEscribiendo = false;
                // ESCRIBE UN COMANDO (VÁLIDO O NO)
            } else {
                // Dividimos el comando en partes por espacios e inicializamos el ProcessBuilder
                // con las partes del comando
                String[] partesDelComando = comandoEscrito.trim().split("\\s");
                ProcessBuilder pb = new ProcessBuilder(partesDelComando);

                try {
                    Process proceso = pb.start();
                    // Leemos la salida del hijo
                    try (BufferedReader lectorHijo = new BufferedReader(
                            new InputStreamReader(proceso.getInputStream()))) {
                        String salida;
                        int indiceProceso = 1;
                        while ((salida = lectorHijo.readLine()) != null) {
                            System.out.println(indiceProceso + ": " + salida);
                            indiceProceso++;
                        }
                    }

                    // Ahora esperamos al hijo y mostramos su codigo de salida (error o exito)
                    int codigoProceso = proceso.waitFor();
                    if (codigoProceso == 0) {
                        System.out.println(MENSAJE_CODIGO_SALIDA + codigoProceso + " (bien)");
                    } else {
                        System.err.println(MENSAJE_CODIGO_SALIDA + codigoProceso + " (error)");
                    }

                    // EL COMANDO NO EXISTE O DIO ERROR POR INTERRUPCIÓN
                } catch (IOException e) {
                    System.err.println("No se pudo lanzar [" + comandoEscrito + "]: " + e.getMessage());
                } catch (InterruptedException e) {
                    System.err.println("Interrumpido mientras se esperaba al proceso.");
                }

            }

        }
        System.out.println("GRACIAS POR USAR ESTE SCRIPT");
        teclado.close();
    }

    /*
     * PREGUNTA FINAL DEL EJERCICIO: en Windows, ¿por qué falla «dir» y funciona
     * «cmd /c dir»?
     *
     * Porque dir es un comando interno de cmd.exe y no un ejecutable.
     * ProcessBuilder solo lanza programas reales, así que start() lanza
     * IOException.
     * Con 'cmd /c dir' si se lanza cmd.exe el cual sí es un programa, y este
     * interpreta 'dir', /c hace que cmd termine al acabar el comando.
     */
}
