import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class ImportarProductosXml {
    public static void main(String[] args) {
        List<Producto> productosActuales = new ArrayList<>();
        productosActuales.add(new Producto(99, "Producto existente", 10.0, 1));

        Path correcto = Path.of("datos", "productos_correctos.xml");
        Path erroneo = Path.of("datos", "productos_erroneos.xml");
        for (Path archivo : List.of(correcto, erroneo)) {
            System.out.println("\nImportando " + archivo + "...");
            try {
                List<Producto> temporales = importar(archivo);
                productosActuales = temporales; // Solo se cambia tras validar TODO.
                System.out.println("Importación correcta.");
            } catch (Exception e) {
                System.out.println("Importación rechazada: " + e.getMessage());
            }
            System.out.println("Lista en memoria:");
            productosActuales.forEach(System.out::println);
        }
    }

    private static List<Producto> importar(Path archivo) throws Exception {
        if (!Files.exists(archivo)) {
            throw new IllegalArgumentException("No existe el archivo: " + archivo);
        }
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
        factory.setFeature("http://xml.org/sax/features/external-general-entities", false);
        factory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
        factory.setXIncludeAware(false);
        factory.setExpandEntityReferences(false);
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        factory.setAttribute(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
        Document documento = factory.newDocumentBuilder().parse(archivo.toFile());
        Element raiz = documento.getDocumentElement();
        if (raiz == null || !"productos".equals(raiz.getTagName())) {
            throw new IllegalArgumentException("La raíz debe ser <productos>.");
        }

        List<Producto> temporales = new ArrayList<>();
        Set<Integer> ids = new HashSet<>();
        NodeList nodos = raiz.getChildNodes();
        for (int i = 0; i < nodos.getLength(); i++) {
            if (!(nodos.item(i) instanceof Element elemento)) {
                continue;
            }
            if (!"producto".equals(elemento.getTagName())) {
                throw new IllegalArgumentException("Etiqueta inesperada en productos.");
            }
            int id = Integer.parseInt(elemento.getAttribute("id"));
            String nombre = texto(elemento, "nombre").trim();
            double precio = Double.parseDouble(texto(elemento, "precio"));
            int stock = Integer.parseInt(texto(elemento, "stock"));
            if (id <= 0 || nombre.isEmpty() || !Double.isFinite(precio)
                    || precio < 0 || stock < 0 || !ids.add(id)) {
                throw new IllegalArgumentException("Datos inválidos o ID repetido: " + id);
            }
            temporales.add(new Producto(id, nombre, precio, stock));
        }
        return temporales;
    }

    private static String texto(Element padre, String etiqueta) {
        NodeList nodos = padre.getElementsByTagName(etiqueta);
        if (nodos.getLength() != 1) {
            throw new IllegalArgumentException("Debe existir un único <" + etiqueta + ">.");
        }
        return nodos.item(0).getTextContent();
    }
}
