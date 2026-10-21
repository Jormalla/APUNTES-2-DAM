import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Path archivo = Path.of("AccesoDatos", "UD1", "R4", "Ejercicio_1_Inscripciones", "datos", "inscripciones.csv");
        try (Scanner teclado = new Scanner(System.in)) {
            int opcion = -1;
            while (opcion != 0) {
                System.out.println("\nGESTOR DE INSCRIPCIONES");
                System.out.println("1. Listar | 2. Buscar | 3. Alta | 4. Cambiar plazas");
                System.out.println("5. Eliminar | 6. Restaurar última copia | 0. Salir");
                System.out.print("Opción: ");
                try {
                    opcion = Integer.parseInt(teclado.nextLine().trim());
                    switch (opcion) {
                        case 1 -> {
                            // EJEMPLO COMPLETO: lectura y salida solo desde main.
                            List<Inscripcion> lista = GestorInscripciones.cargar(archivo);
                            if (lista.isEmpty()) {
                                System.out.println("No hay inscripciones.");
                            }
                            for (Inscripcion item : lista) {
                                System.out.println(item);
                            }
                        }
                        case 2 -> {
                            // EJEMPLO COMPLETO: consulta sin modificar el archivo.
                            System.out.print("ID que buscas: ");
                            int id = Integer.parseInt(teclado.nextLine().trim());
                            Inscripcion encontrada = GestorInscripciones.buscar(
                                    GestorInscripciones.cargar(archivo), id);
                            System.out.println(encontrada == null ? "ID no encontrado." : encontrada);
                        }
                        case 3 -> {
                            // EJEMPLO COMPLETO: alta y validación por la capa de datos.
                            System.out.print("ID: ");
                            int id = Integer.parseInt(teclado.nextLine().trim());
                            System.out.print("Persona: ");
                            String persona = teclado.nextLine().trim();
                            System.out.print("Taller: ");
                            String taller = teclado.nextLine().trim();
                            System.out.print("Plazas: ");
                            int plazas = Integer.parseInt(teclado.nextLine().trim());
                            boolean alta = GestorInscripciones.registrar(
                                    archivo, new Inscripcion(id, persona, taller, plazas));
                            System.out.println(alta ? "Inscripción añadida." : "Datos incorrectos o ID repetido.");
                        }
                        case 4 -> {
                            // y mostrar un mensaje distinto según el boolean recibido.
                            // Solicita ID, plazas y muestra el resultado.
                            System.out.print("ID de la inscripción que quieres modificar: ");
                            int id = Integer.parseInt(teclado.nextLine().trim());
                            System.err.print("Ingrese el nuevo número de plazas: ");
                            int plazas = Integer.parseInt(teclado.nextLine().trim());
                            if (GestorInscripciones.actualizarPlazas(archivo, id, plazas)) {
                                System.err.println("Plazas actualizadas:\n"
                                        + GestorInscripciones.buscar(GestorInscripciones.cargar(archivo), id));
                            } else {
                                System.out.println("La inscripción con id " + id
                                        + " NO EXISTE o el número de plazas es menor a 0");
                            }
                        }
                        case 5 -> {
                            // Solicita ID para eliminar y muestra el resultado.
                            System.out.print("ID de la inscripción que quieres eliminar: ");
                            int id = Integer.parseInt(teclado.nextLine().trim());
                            // Puede eliminarlo
                            if (GestorInscripciones.eliminar(archivo, id)) {
                                List<Inscripcion> lista = GestorInscripciones.cargar(archivo);
                                if (lista.isEmpty()) {
                                    System.out.println("La lista está vacía");
                                } else {
                                    for (Inscripcion item : lista) {
                                        System.out.println(item);
                                    }
                                }
                            } else {
                                System.err.println("La inscripción con id " + id + " no existe.");
                            }
                        }
                        case 6 -> {
                            // TODO 1.6-C - Pedir confirmación S/N antes de restaurar.
                            // Pide confirmación antes de llamar a restaurar.
                            System.out.println("TODO 1.6-C pendiente.");
                        }
                        case 0 -> System.out.println("Hasta luego.");
                        default -> System.out.println("Opción desconocida.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Introduce un número entero válido.");
                } catch (IOException e) {
                    System.out.println("Error de archivo: " + e.getMessage());
                }
            }
        }
    }
}
