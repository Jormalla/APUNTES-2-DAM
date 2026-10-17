import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ejercicio2 {

    /**
     * Lee un flujo del hijo hasta el final y escribe cada línea con una etiqueta.
     *
     * @param flujo    Lector que esta conectado al flujo del hijo.
     * @param etiqueta Cadena de texto en forma de etiqueta que se pone delante de
     *                 cada mensaje.
     */
    public static void mostrarFlujo(BufferedReader flujo, String etiqueta) throws IOException {
        String linea;
        while ((linea = flujo.readLine()) != null) {
            System.out.println(etiqueta + " " + linea);
        }
    }

    /**
     * Lanza el proceso que lista las dos carpetas.
     *
     * @param unirFlujos true para usar redirectErrorStream(true), false para
     *                   tener salida y error por separado.
     */
    public static void lanzarProcesos(boolean unirFlujos) {
        final String CARPETA_PERSONAL = System.getProperty("user.home");
        final String CARPETA_INEXISTENTE = "carpeta-que-no-existe";
        // Este comando me sirve para saber si el script se esta ejecutando desde
        // windows o no, ya que accede al nombre del sistema operativo
        boolean esWindows = System.getProperty("os.name").contains("Windows");

        ProcessBuilder pb;
        if (esWindows) {
            pb = new ProcessBuilder("cmd", "/c", "dir", "/b", CARPETA_PERSONAL, CARPETA_INEXISTENTE);
        } else {
            pb = new ProcessBuilder("ls", CARPETA_PERSONAL, CARPETA_INEXISTENTE);
        }

        // Si es true, el error del hijo viaja mezclado por getInputStream()
        pb.redirectErrorStream(unirFlujos);

        try {
            Process proceso = pb.start();

            try (BufferedReader SALIDA = new BufferedReader(new InputStreamReader(proceso.getInputStream()));
                    BufferedReader ERROR = new BufferedReader(new InputStreamReader(proceso.getErrorStream()))) {

                // Primero SALIDA Y luego ERROR
                mostrarFlujo(SALIDA, "[SALIDA]");
                mostrarFlujo(ERROR, "[ERROR]");
            }

            int codigo = proceso.waitFor();
            System.out.println("Código de salida: " + codigo);

        } catch (IOException e) {
            System.err.println("No se pudo lanzar el proceso: " + e.getMessage());
        } catch (InterruptedException e) {
            System.err.println("Interrumpido mientras se esperaba al proceso.");
        }
    }

    public static void main(String[] args) {
        // Primero lanzamos los procesos en flujos separados
        System.out.println("--- a) Flujos separados ---");
        lanzarProcesos(false);
        System.out.println();
        // Ahora lanzamos los procesos en flujos unidos
        System.out.println("--- b) redirectErrorStream(true) ---");
        lanzarProcesos(true);

        /*
         * b) Con redirectErrorStream(true) el error llega por getInputStream()
         * (etiqueta [SALIDA]) y getErrorStream() queda vacío, es decir, no se muestra
         * la etiqueta [ERROR]
         *
         * PREGUNTA: ¿el código de salida es 0? ¿Por qué?
         * No, es 2 en Linux (ls) y 1 en Windows (dir), porque una de las dos
         * carpetas no existe. Basta un fallo para que el código sea distinto de 0,
         * y redirectErrorStream no lo cambia ya que solo mezcla los flujos de texto.
         */
    }
}
