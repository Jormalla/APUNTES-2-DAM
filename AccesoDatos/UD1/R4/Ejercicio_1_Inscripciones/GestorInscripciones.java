import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GestorInscripciones {
    private static final String CABECERA = "id;persona;taller;plazas";
    // Si el archivo no existe, devolver una lista vacía.
    // Si una fila es incorrecta o hay IDs duplicados, lanzar IOException.
    public static List<Inscripcion> cargar(Path archivo) throws IOException {
        // PASO 1: prepara la lista, recorre las filas del CSV y valida campos.
        List<Inscripcion> inscripciones = new ArrayList<>();
        String[] columnas;
        Set<Integer> idsDocumento = new HashSet<>();
        final int CANTIDAD_COLUMNAS = 4;
        if (Files.exists(archivo) && Files.isRegularFile(archivo)) {
            List<String> texto = Files.readAllLines(archivo);
            // LA CABECERA NO LA COMPROBAMOS
            for (int i = 1; i < texto.size(); i++) {
                columnas = texto.get(i).split(";", -1);
                // Comprobamos que tenga 4 columnas
                if (columnas.length == CANTIDAD_COLUMNAS) {
                    int id = Integer.parseInt(columnas[0]);
                    // Comrpuebo que el id no esté repetido
                    if (idsDocumento.add(id)) {
                        int plazas = Integer.parseInt(columnas[3]);
                        Inscripcion inscripcion = new Inscripcion(id, columnas[1], columnas[2], plazas);
                        // Añado la inscripción
                        inscripciones.add(inscripcion);
                    } else {
                        throw new IOException("El archivo contiene ids duplicados.");
                    }
                } else{
                    throw new IOException("El archivo no tiene las columnas necesarias: " + CANTIDAD_COLUMNAS + " columnas requeridas.");
                }
            }
        }
        return inscripciones;
    }

    public static Inscripcion buscar(List<Inscripcion> lista, int id) {
        // PASO 2: recorre la lista y compara los ID.
        Inscripcion inscripcionEncontrada = null;
        for (Inscripcion inscripcion : lista) {
            if (inscripcion.getId() == id) {
                inscripcionEncontrada = inscripcion;
            }
        }
        return inscripcionEncontrada;
    }

    // MÉTODO RESUELTO COMO EJEMPLO: no hay que modificarlo.
    public static boolean registrar(Path archivo, Inscripcion nueva) throws IOException {
        List<Inscripcion> lista = cargar(archivo);
        if (!esValida(nueva) || buscar(lista, nueva.getId()) != null) {
            return false;
        }
        lista.add(nueva);
        crearCopiaSiExiste(archivo);
        guardarTodo(archivo, lista);
        return true;
    }

    // Crear copia antes de reescribir. Si no existe o plazas < 0, devolver false.
    public static boolean actualizarPlazas(Path archivo, int id, int plazas) throws IOException {
        // PASO 3: carga, busca, valida, crea copia y guarda.
        List<Inscripcion> lista = cargar(archivo);
        Inscripcion inscripcionAModificar = buscar(lista, id);
        if (inscripcionAModificar != null && plazas >= 0) {
            crearCopiaSiExiste(archivo);
            inscripcionAModificar.setPlazas(plazas);
            guardarTodo(archivo, lista);
            return true;
        }
        return false;
    }

    // TODO 1.4 - Eliminar el ID solicitado y conservar todos los demás.
    // Si no existe, NO reescribir el archivo ni su copia.
    public static boolean eliminar(Path archivo, int id) throws IOException {
        // PASO 4: reconstruye la lista omitiendo el ID solicitado.
        return false;
    }

    // TODO 1.5 - Restaurar archivo.csv.bak sobre archivo.csv.
    // La confirmación se pide en Main, NO aquí.
    public static boolean restaurar(Path archivo) throws IOException {
        // PASO 5: localiza .bak y cópialo sobre el CSV.
        return false;
    }

    // MÉTODOS PROPORCIONADOS. No se deben completar.
    private static boolean esValida(Inscripcion item) {
        return item != null && item.getId() > 0 && item.getPlazas() >= 0
                && !item.getPersona().isBlank() && !item.getTaller().isBlank()
                && !item.getPersona().contains(";") && !item.getTaller().contains(";")
                && !item.getPersona().contains("\n") && !item.getTaller().contains("\n");
    }

    private static void guardarTodo(Path archivo, List<Inscripcion> lista) throws IOException {
        Path padre = archivo.getParent();
        if (padre != null) {
            Files.createDirectories(padre);
        }
        List<String> lineas = new ArrayList<>();
        lineas.add(CABECERA);
        for (Inscripcion item : lista) {
            lineas.add(item.getId() + ";" + item.getPersona() + ";"
                    + item.getTaller() + ";" + item.getPlazas());
        }
        Files.write(archivo, lineas, StandardCharsets.UTF_8);
    }

    private static void crearCopiaSiExiste(Path archivo) throws IOException {
        if (Files.exists(archivo)) {
            Path copia = Path.of(archivo + ".bak");
            Files.copy(archivo, copia, StandardCopyOption.REPLACE_EXISTING);
        }
    }
}
