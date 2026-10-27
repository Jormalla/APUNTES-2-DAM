import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.XMLConstants;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class RepositorioEquipos {
    // 2.1 - Recuperar la lista serializada con ObjectInputStream.
    // Si no existe el .dat, devolver lista vacía. Comprobar tipos, IDs y
    // duplicados.
    public static List<Equipo> cargar(Path archivo) throws IOException {
        // PASO 1: si el fichero falta, devuelve una lista vacía.
        // Si existe, lee un Object y valida su contenido.
        List<Equipo> equipos = new ArrayList<>();
        HashSet<Integer> idsEquipos = new HashSet<>();
        HashSet<String> nombresEquipos = new HashSet<>();
        if (!Files.exists(archivo) || !Files.isRegularFile(archivo)) {
            return equipos;
        }
        try (ObjectInputStream entrada = new ObjectInputStream(
                Files.newInputStream(archivo))) {
            Object dato = entrada.readObject();
            if (!(dato instanceof List<?> lista)) {
                throw new IOException("El archivo no contiene una lista.");
            }
            for (Object elemento : lista) {
                if (!(elemento instanceof Equipo equipo)) {
                    throw new IOException("Se ha encontrado un dato inesperado.");
                }
                // Comprueba si el id está duplicado
                if (!idsEquipos.add(equipo.getId())) {
                    throw new IOException("Se ha encontrado al menos una id duplicada.");
                }
                // Comprueba si el nombre del equipo está duplicado
                if (!nombresEquipos.add(equipo.getNombre().strip().toUpperCase())) {
                    throw new IOException("Se ha encontrado al menos un nombre de equipo duplicado.");
                }
                equipos.add(equipo);
            }
        } catch (ClassNotFoundException e) {
            throw new IOException("No se pudo recuperar: " + e.getMessage());
        }
        return equipos;
    }

    // Crear carpeta y guardar la LISTA COMPLETA con ObjectOutputStream.
    // No usar APPEND en ficheros serializados.
    public static void guardar(Path archivo, List<Equipo> lista) throws IOException {
        // PASO 2: crea la carpeta y usa ObjectOutputStream.
        // El método es void: no necesita devolver nada.
        Files.createDirectories(archivo.getParent());
        try (ObjectOutputStream salida = new ObjectOutputStream(Files.newOutputStream(archivo))) {
            salida.writeObject(lista);
        }
    }

    // MÉTODO DE APOYO COMPLETO: busca por ID sin leer archivos.
    public static Equipo buscar(List<Equipo> lista, int id) {
        for (Equipo equipo : lista) {
            if (equipo.getId() == id) {
                return equipo;
            }
        }
        return null;
    }

    // Modificar SOLO en memoria. Devolver false si ID desconocido
    // o unidades < 0. NO guardar aquí: lo hará Main en opción 4.
    public static boolean cambiarUnidades(List<Equipo> lista, int id, int unidades) {
        // PASO 3: localiza el equipo y cambia sus unidades.
        Equipo equipo = buscar(lista, id);
        if (equipo != null && unidades >= 0) {
            equipo.setUnidades(unidades);
            return true;
        }
        return false;
    }

    // Exportar DOM XML. Crear raíz <equipos> y cada <equipo id="...">.
    // Dentro, <nombre> y <unidades>. No concatenar cadenas XML manualmente.
    // Los errores de XML se convierten en IOException para simplificar Main.
    public static void exportarXml(Path archivo, List<Equipo> lista) throws IOException, ParserConfigurationException, TransformerException {
        // PASO 4: crea un Document XML, elementos y Transformer.
        Document documento = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
        Element raiz = documento.createElement("equipos");
        documento.appendChild(raiz);

        for (Equipo equipo : lista) {
            // <equipo id="...">.
            Element etiquetaEquipo = documento.createElement("equipo");
            etiquetaEquipo.setAttribute("id", equipo.getId() + "");
            // <nombre/>
            Element nombre = documento.createElement("nombre");
            nombre.setTextContent(equipo.getNombre());
            // <unidades/>
            Element unidades = documento.createElement("unidades");
            unidades.setTextContent(equipo.getUnidades() + "");

            // <equipo id="...">.
            //      <nombre/>
            //      <unidades/>
            // </equipo>
            etiquetaEquipo.appendChild(nombre);
            etiquetaEquipo.appendChild(unidades);
            raiz.appendChild(etiquetaEquipo);
        }

         // SE GUARDAN LOS DATOS EN EL XML
        Transformer transformador = TransformerFactory.newInstance().newTransformer();
        transformador.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformador.setOutputProperty(OutputKeys.INDENT, "yes");
        transformador.transform(new DOMSource(documento), new StreamResult(archivo.toFile()));
    }
}
