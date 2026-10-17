import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.List;

/**
 * Ejercicio4
 * @author Jorge Martín Llaneli 2º DAM | ESCUELA ARTE GRANADA
 */
public class Ejercicio4 {

    public static void main(String[] args) {
        // Creamos la lista nombre que contendrá los nombres
        List<String> nombres = Arrays.asList("Marta", "Carlos", "Ana", "Pedro", "Lucia", "Bea");
        System.out.println("Lista original: " + String.join(", ", nombres));

        // Iniciamos un process builder del comando sort
        ProcessBuilder pb = new ProcessBuilder("sort");

        try {
            // Se lanza el proceso
            Process proceso = pb.start();

            // Mandamos los nombres al hijo, uno por línea, y cerramos el flujo
            try (BufferedWriter escritor = new BufferedWriter(
                    new OutputStreamWriter(proceso.getOutputStream()))) {
                for (String nombre : nombres) {
                    escritor.write(nombre);
                    escritor.newLine();
                }
            } // Como uso un try-with-resources no tengo que hacer flush ni close, ya que ya
              // hace internamente el close y el close a su vez hace un flush

            // Leemos la lista ordenada
            System.out.println("Lista ordenada por el proceso hijo:");
            try (BufferedReader lector = new BufferedReader(
                    new InputStreamReader(proceso.getInputStream()))) {
                String linea;
                int indice = 1;
                while ((linea = lector.readLine()) != null) {
                    System.out.println(" " + indice + ". " + linea);
                    indice++;
                }
            }

            // Mostramos el codigo de salida
            int codigo = proceso.waitFor();
            System.out.println("Código de salida de sort: " + codigo);

        } catch (IOException e) {
            System.err.println("No se pudo lanzar el proceso: " + e.getMessage());
        } catch (InterruptedException e) {
            System.err.println("Interrumpido mientras se esperaba al proceso.");
        }

        /*
         * ¿qué pasa si no cierras el flujo de salida? ¿Por qué?
         * El programa se queda bloqueado, lo que hace que sort no empiece a ordenar ni
         * a escribir
         * Sin cerrarlo, sort sigue esperando más líneas y el padre se queda esperando
         * la salida de sort en readLine().
         */
    }
}