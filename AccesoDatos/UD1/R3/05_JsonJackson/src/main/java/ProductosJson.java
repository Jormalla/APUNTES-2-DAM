import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProductosJson {
    public static void main(String[] args) {
        Path exportacion = Path.of("datos", "productos_exportados.json");
        Path valido = Path.of("datos", "productos.json");
        Path malformado = Path.of("datos", "productos_malformados.json");
        ObjectMapper mapper = new ObjectMapper();

        List<Producto> productosActuales = new ArrayList<>(List.of(
                new Producto(1, "Teclado", 59.90, 8),
                new Producto(2, "Ratón", 24.50, 15),
                new Producto(3, "Monitor", 219.00, 4),
                new Producto(4, "Auriculares", 39.95, 10)));
        try {
            Files.createDirectories(exportacion.getParent());
            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(exportacion.toFile(), productosActuales);
            System.out.println("Ejemplo exportado a: " + exportacion);
        } catch (IOException e) {
            System.out.println("No se pudo exportar: " + e.getMessage());
        }

        // Importamos DOS ficheros para demostrar que una lectura fallida
        // nunca sustituye los datos que ya habíamos cargado.
        for (Path archivo : List.of(valido, malformado)) {
            System.out.println("\nIntentando importar: " + archivo);
            try {
                if (!Files.exists(archivo)) {
                    throw new IOException("No existe el archivo");
                }
                List<Producto> temporales = mapper.readValue(
                        archivo.toFile(), new TypeReference<List<Producto>>() {});
                validar(temporales);
                productosActuales = temporales;
                System.out.println("Importación correcta.");
            } catch (IOException | IllegalArgumentException e) {
                System.out.println("Importación rechazada: " + e.getMessage());
            }
            System.out.println("Productos conservados en memoria:");
            productosActuales.forEach(System.out::println);
        }
    }

    private static void validar(List<Producto> productos) {
        if (productos == null) {
            throw new IllegalArgumentException("El JSON no contiene una lista.");
        }
        Set<Integer> ids = new HashSet<>();
        for (Producto p : productos) {
            if (p == null || p.getId() <= 0 || p.getNombre() == null
                    || p.getNombre().isBlank() || !Double.isFinite(p.getPrecio())
                    || p.getPrecio() < 0 || p.getStock() < 0 || !ids.add(p.getId())) {
                throw new IllegalArgumentException("Producto inválido o ID duplicado.");
            }
        }
    }
}
