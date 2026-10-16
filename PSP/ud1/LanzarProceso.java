import java.io.IOException;


public class LanzarProceso {
    public static void main(String[] args) {
        String proceso = "calc.exe";
        ProcessBuilder caclculadora = new ProcessBuilder(proceso);
        try {
            Process procesoInicio = caclculadora.start();
            int retorno = procesoInicio.waitFor();
            System.out.println("La ejecución de " + proceso + " devuelve " + retorno);  
        } catch (IOException e) {
            System.err.println("¡ERROR!¡Excepción de Entrada/Ssalida!");
            System.exit(-1);
        } catch (InterruptedException e){
            System.err.println("¡ERROR! ¡El proceso hijo finalizó de forma incorrecta!");
            System.exit(-1);
        }
    }
}
