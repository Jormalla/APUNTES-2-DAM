import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Ejercicio3
 * @author Jorge Martín Llaneli 2º DAM | ESCUELA ARTE GRANADA
 */
public class Ejercicio3 {

    /**
     * a) Lista la carpeta actual del hijo, que arranca en la carpeta
     * personal gracias a directory().
     */
    public static void listarCarpetaPersonal(boolean esWindows) {
        ProcessBuilder pb;
        if (esWindows) {
            pb = new ProcessBuilder("cmd", "/c", "dir", "/b");
        } else {
            pb = new ProcessBuilder("ls");
        }

        // Se cambia la carpeta en la que arranca el hijo
        pb.directory(new File(System.getProperty("user.home")));

        // Mostramos los atributos antes de lanzar el proceso
        System.out.println("Comando: " + pb.command());
        System.out.println("Directorio de trabajo: " + pb.directory());

        try {
            // Lanzamos el proceso
            Process proceso = pb.start();
            int elementos = 0;

            // Cuenta los elementos
            try (BufferedReader lector = new BufferedReader(new InputStreamReader(proceso.getInputStream()))) {
                String linea;
                while ((linea = lector.readLine()) != null) {
                    System.out.println(linea);
                    elementos++;
                }
            }

            proceso.waitFor();
            System.out.println("Hay " + elementos + " elementos en la carpeta personal");

        } catch (IOException e) {
            System.err.println("No se pudo lanzar el proceso: " + e.getMessage());
        } catch (InterruptedException e) {
            System.err.println("Interrumpido mientras se esperaba al proceso.");
        }
    }

    /**
     * b) Lanza un hijo que muestra la variable SALUDO, que solo existe
     * en el entorno del hijo.
     */
    public static void mostrarVariableEntorno(boolean esWindows) {
        final String VALOR_SALUDO = "Hola desde una variable de entorno";

        ProcessBuilder pb;
        if (esWindows) {
            pb = new ProcessBuilder("cmd", "/c", "echo %SALUDO%");
        } else {
            pb = new ProcessBuilder("sh", "-c", "echo $SALUDO");
        }

        // Añado la variable solo al entorno del hijo
        pb.environment().put("SALUDO", VALOR_SALUDO);

        try {
            // Se lanza el proceso al hijo que es el contenido de la variable SALUDO
            Process proceso = pb.start();

            // Lo que ve el padre
            System.out.println("El padre ve SALUDO = " + System.getenv("SALUDO"));

            try (BufferedReader lector = new BufferedReader(new InputStreamReader(proceso.getInputStream()))) {
                String linea;
                // Lo que ve el hijo
                while ((linea = lector.readLine()) != null) {
                    System.out.println("El hijo ve SALUDO = " + linea);
                }
            }

            proceso.waitFor();

        } catch (IOException e) {
            System.err.println("No se pudo lanzar el proceso: " + e.getMessage());
        } catch (InterruptedException e) {
            System.err.println("Interrumpido mientras se esperaba al proceso.");
        }
    }

    public static void main(String[] args) {
        // COMPROBAMOS EL SISTEMA OPERATIVO
        boolean esWindows = System.getProperty("os.name").contains("Windows");

        // APARTADO A
        System.out.println("--- a) Directorio de trabajo ---");
        listarCarpetaPersonal(esWindows);

        System.out.println();

        // APARTADO B
        System.out.println("--- b) Variable de entorno ---");
        mostrarVariableEntorno(esWindows);

        /**
         * ¿Por qué son distintos los valores de SALUDO?
         * Porque cada proceso tiene su propia copia del entorno. environment().put()
         * modifica solo el entorno del hijo, y el del padre no cambia (por eso ve
         * null).
         */
    }
}