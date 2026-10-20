package AccesoDatos.UD1.R3.Ejercicio3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import Bibliotecas.Ficheros;
import Bibliotecas.MisUtiles;

public class Ejercicio3 {
    public static void main(String[] args) throws ParserConfigurationException, TransformerException {

        // VARIABLES
        Path rutaProductos = Path.of("AccesoDatos", "UD1", "R3", "Ejercicio3", "datos", "productos.xml");
        // MENSAJES
        final String MENSAJE_CREACION_RUTA = "El directorio 'datos/' no existe.\n Creando ruta y archivo '/productos.xml' ...";
        final String MENSAJE_CREACION_ARCHIVO = "El archivo '/productos.xml' no existe. Creando archivo ...";

        try {
            // CREA LA RUTA Y ARCHIVO SI NO EXISTE
            if (!Ficheros.validarRuta(rutaProductos, MENSAJE_CREACION_RUTA)
                    || !Ficheros.validarArchivo(rutaProductos, MENSAJE_CREACION_ARCHIVO)) {
                Files.createDirectories(rutaProductos.getParent());
                Files.createFile(rutaProductos);
            }
        } catch (IOException e) {
            MisUtiles.error("¡ERROR! No se pudo crear la ruta o el archivo\n" + e.getMessage());
        }

        // CREACIÓN DE ETIQUETA RAÍZ
        List<Producto> productos = new ArrayList<>();
        Document documento = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        Element raiz = documento.createElement("productos");
        documento.appendChild(raiz);

        // Se crean los productos
        productos.add(new Producto(1, "PC MSI", 1200.20, 10));
        productos.add(new Producto(2, "Ratón logitech", 45.50, 130));
        productos.add(new Producto(3, "Monitor DELL", 120.99, 100));

        // Crea las etiquetas de los productos
        for (Producto producto : productos) {
            Element etiquetaProducto = documento.createElement("producto");
            etiquetaProducto.setAttribute("id", producto.getId() + "");
            Element nombre = documento.createElement("nombre");
            nombre.setTextContent(producto.getNombre());
            Element precio = documento.createElement("precio");
            precio.setTextContent(producto.getPrecio() + "");
            Element stock = documento.createElement("stock");
            precio.setTextContent(producto.getStock() + "");
            etiquetaProducto.appendChild(nombre);
            etiquetaProducto.appendChild(precio);
            etiquetaProducto.appendChild(stock);
            raiz.appendChild(etiquetaProducto);
        }

        // SE GUARDAN LOS DATOS EN EL XML
        Transformer transformador = TransformerFactory.newInstance().newTransformer();
        transformador.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformador.setOutputProperty(OutputKeys.INDENT, "yes");
        transformador.transform(new DOMSource(documento), new StreamResult(rutaProductos.toFile()));
        MisUtiles.consoleLn("XML guardado en " + rutaProductos.toAbsolutePath());

    }

}
