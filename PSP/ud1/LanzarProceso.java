import java.io.IOException;
import java.util.Arrays;

public class LanzarProceso {
    public static void main(String[] args) {
        ProcessBuilder caclculadora = new ProcessBuilder("calc.exe");
        try {
            Process procesoInicio = caclculadora.start();
            int retorno = procesoInicio.waitFor();
            System.out.println("La ejecución de " + Arrays.toString(args) + " devuelve " + retorno);  
        } catch (IOException e) {
            System.err.println("¡ERROR!¡Excepción de Entrada/Ssalida!");
            System.exit(-1);
        } catch (InterruptedException e){
            System.err.println("¡ERROR! ¡El proceso hijo finalizó de forma incorrecta!");
            System.exit(-1);
        }
    }
}
