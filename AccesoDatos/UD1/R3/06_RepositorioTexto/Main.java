import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        RepositorioProductos repositorio = new RepositorioTexto(
                Path.of("AccesoDatos", "UD1", "R3", "06_RepositorioTexto", "datos", "productos.txt"));
        List<Producto> productos = null;

        // TODO: cargar los productos a través del repositorio.
        try {
            productos = repositorio.cargarTodos();
        } catch (IOException e) {
            System.err.println("¡ERROR AL CARGAR EL ARCHIVO! " + e.getMessage());
        }

        // Muestro los productos
        for (Producto producto : productos) {
            System.out.println(producto);
        }

        // Agrego nuevo producto
        productos.add(new Producto(4, "Nuevo Producto", 10.50, 20));
        try {
            repositorio.guardarTodos(productos);
            System.out.println("Productos guardados y escritos correctamente");
        } catch (IOException e) {
            System.err.println("¡ERROR AL GUARDAR PRODUCTOS! " + e.getMessage());
        }

    }
}
