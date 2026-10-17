import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Ejercicio5
 * 
 * @author Jorge Martín Llaneli 2º DAM | ESCUELA ARTE GRANADA
 */
public class Ejercicio5 {

    public static void main(String[] args) {
        // CREA EL PROCESS BUILDER DE DISTINTA FORMA DEPENDIENDO DEL SO
        boolean esWindows = System.getProperty("os.name").contains("Windows");
        ProcessBuilder pb;
        if (esWindows) {
            pb = new ProcessBuilder("ping", "-t", "localhost");
        } else {
            pb = new ProcessBuilder("ping", "localhost");
        }

        try {
            // Lanza el proceso y pregunta por si esta o no vivo
            Process proceso = pb.start();
            System.out.println("Lanzado " + pb.command() + ". ¿Está vivo? " + proceso.isAlive());

            // Esperamos como máximo 3 segundos y comprueba si ha terminado
            boolean terminado = proceso.waitFor(3, TimeUnit.SECONDS);
            System.out.println("¿Ha terminado solo en 3 s? " + terminado);

            // Si no ha terminado se termina con destroy()
            if (!terminado) {
                System.out.println("El padre decide terminarlo con destroy()");
                proceso.destroy();
                proceso.waitFor();
                // Volvemos a preguntar si esta o no vivo, debería salir true
                System.out.println("¿Está vivo? " + proceso.isAlive());
                System.out.println("Código de salida: " + proceso.exitValue());
            }

        } catch (IOException e) {
            System.err.println("No se pudo lanzar el proceso: " + e.getMessage());
        } catch (InterruptedException e) {
            System.err.println("Interrumpido mientras se esperaba al proceso.");
        }

        /*
         * ¿qué pasaría con el ping si el padre terminara sin llamar a destroy()?
         * El ping seguiría ejecutándose, porque el hijo es un proceso independiente del
         * sistema operativo y no muere cuando termina el padre. Quedaría huérfano,
         * gastando recursos hasta que alguien lo terminase a mano (kill o Administrador
         * de tareas).
         */
    }
}
