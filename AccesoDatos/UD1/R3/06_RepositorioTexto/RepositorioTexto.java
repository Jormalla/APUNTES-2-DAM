import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class RepositorioTexto implements RepositorioProductos {

    private final Path archivo;

    public RepositorioTexto(Path archivo) {
        this.archivo = archivo;
    }

    @Override
    public void guardarTodos(List<Producto> productos) throws IOException {
        if (!Files.exists(archivo)) {
            throw new IOException("La ruta " + archivo + " no existe.");
        }

        if (!Files.isRegularFile(archivo)) {
            throw new IOException(archivo + " no es un archivo.");
        }
        try (BufferedWriter escritor = Files.newBufferedWriter(archivo, StandardCharsets.UTF_8)) {
            for (Producto producto : productos) {
                escritor.write("" + producto.getId() + ";" + producto.getNombre() + ";" + producto.getPrecio() + ";"
                        + producto.getStock());
                escritor.newLine();
            }
        }
    }

    @Override
    public List<Producto> cargarTodos() throws IOException {
        List<String> productosLeidos = Files.readAllLines(archivo, StandardCharsets.UTF_8);
        List<Producto> productos = new ArrayList<>();

        final int NUMERO_CAMPOS = 4;
        int contadorLinea = 1;
        for (String linea : productosLeidos) {
            String[] campos = linea.split(";", -1);
            if (campos.length != NUMERO_CAMPOS) {
                throw new IOException("¡ERROR! en el número de campos. Se esperaban " + NUMERO_CAMPOS
                        + " columnas. [Línea " + contadorLinea + "]");
            } else {
                try {
                    int id = Integer.parseInt(campos[0]);
                    String nombre = campos[1].trim();
                    Double precio = Double.parseDouble(campos[2]);
                    int stock = Integer.parseInt(campos[3]);

                    if (nombre.isEmpty()) {
                        throw new IOException("¡ERROR! Se encontró un nombre vacío [Línea " + contadorLinea + "]");
                    }

                    Producto producto = new Producto(id, nombre, precio, stock);
                    productos.add(producto);
                } catch (NumberFormatException e) {
                    throw new IOException(
                            "ERROR en el formato de los datos numéricos del producto (revisa los campos ID, PRECIO y STOCK) [Línea "
                                    + contadorLinea + "]");
                }
                contadorLinea++;
            }
        }

        return productos;
    }
}
