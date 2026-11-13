package probados;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App4 extends Application {

    private TextField campoCodigo;
    private TextField campoAula;
    private TextArea campoDescripcion;
    private ComboBox<String> comboPrioridad;
    private ComboBox<String> comboEstado;
    private TextField campoTecnico;
    private TextField campoBusqueda;

    private TableView<Incidencia> tabla;
    private ObservableList<Incidencia> incidencias;

    private Label etiquetaTotal;
    private Label etiquetaAbiertas;
    private Label etiquetaEnProceso;
    private Label etiquetaResueltas;

    @Override
    public void start(Stage escenario) {

        incidencias = FXCollections.observableArrayList();

        campoCodigo = new TextField();
        campoCodigo.setPromptText("INC-001");

        campoAula = new TextField();
        campoAula.setPromptText("Ejemplo: Aula 12");

        campoDescripcion = new TextArea();
        campoDescripcion.setPromptText("Describe la incidencia");
        campoDescripcion.setPrefSize(300, 80);

        comboPrioridad = new ComboBox<>();
        comboPrioridad.getItems().addAll("Baja", "Media", "Alta");
        comboPrioridad.setPromptText("Selecciona prioridad");

        comboEstado = new ComboBox<>();
        comboEstado.getItems().addAll("Abierta", "En proceso", "Resuelta");
        comboEstado.setValue("Abierta");

        campoTecnico = new TextField();
        campoTecnico.setPromptText("Nombre del técnico");

        campoBusqueda = new TextField();
        campoBusqueda.setPromptText("Buscar por código, aula, descripción o técnico");

        Button botonAnadir = new Button("Añadir incidencia");
        Button botonActualizar = new Button("Actualizar estado");
        Button botonEliminar = new Button("Eliminar");
        Button botonFiltrar = new Button("Filtrar");
        Button botonMostrarTodo = new Button("Mostrar todo");

        HBox cajaBotonesPrincipales = new HBox(10);
        cajaBotonesPrincipales.setAlignment(Pos.CENTER);
        cajaBotonesPrincipales.getChildren().addAll(
                botonAnadir, botonActualizar, botonEliminar
        );

        HBox cajaBusqueda = new HBox(10);
        cajaBusqueda.setAlignment(Pos.CENTER);
        cajaBusqueda.getChildren().addAll(
                new Label("Buscar:"), campoBusqueda, botonFiltrar, botonMostrarTodo
        );

        crearTabla();

        etiquetaTotal = new Label("Total: 0");
        etiquetaAbiertas = new Label("Abiertas: 0");
        etiquetaEnProceso = new Label("En proceso: 0");
        etiquetaResueltas = new Label("Resueltas: 0");

        HBox cajaEstadisticas = new HBox(20);
        cajaEstadisticas.setAlignment(Pos.CENTER);
        cajaEstadisticas.getChildren().addAll(
                etiquetaTotal, etiquetaAbiertas,
                etiquetaEnProceso, etiquetaResueltas
        );

        GridPane formulario = new GridPane();
        formulario.setHgap(10);
        formulario.setVgap(10);
        formulario.setPadding(new Insets(10));

        formulario.add(new Label("Código:"), 0, 0);
        formulario.add(campoCodigo, 1, 0);

        formulario.add(new Label("Aula:"), 0, 1);
        formulario.add(campoAula, 1, 1);

        formulario.add(new Label("Descripción:"), 0, 2);
        formulario.add(campoDescripcion, 1, 2);

        formulario.add(new Label("Prioridad:"), 0, 3);
        formulario.add(comboPrioridad, 1, 3);

        formulario.add(new Label("Estado:"), 0, 4);
        formulario.add(comboEstado, 1, 4);

        formulario.add(new Label("Técnico:"), 0, 5);
        formulario.add(campoTecnico, 1, 5);

        VBox zonaSuperior = new VBox(12);
        zonaSuperior.setPadding(new Insets(15));
        zonaSuperior.setAlignment(Pos.TOP_CENTER);
        zonaSuperior.getChildren().addAll(
                new Label("GESTIÓN DE INCIDENCIAS"),
                formulario,
                cajaBotonesPrincipales,
                cajaBusqueda
        );

        BorderPane raiz = new BorderPane();
        raiz.setPadding(new Insets(10));
        raiz.setTop(zonaSuperior);
        raiz.setCenter(tabla);
        raiz.setBottom(cajaEstadisticas);

        botonAnadir.setOnAction(e -> anadirIncidencia());
        botonActualizar.setOnAction(e -> actualizarEstado());
        botonEliminar.setOnAction(e -> eliminarIncidencia());
        botonFiltrar.setOnAction(e -> filtrarIncidencias());
        botonMostrarTodo.setOnAction(e -> mostrarTodas());

        Scene escena = new Scene(raiz, 800, 600);

        escenario.setTitle("Gestión de incidencias");
        escenario.setScene(escena);
        escenario.show();

        actualizarEstadisticas();
    }

    private void crearTabla() {

        tabla = new TableView<>();

        TableColumn<Incidencia, String> columnaCodigo = new TableColumn<>("Código");
        columnaCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));

        TableColumn<Incidencia, String> columnaAula = new TableColumn<>("Aula");
        columnaAula.setCellValueFactory(new PropertyValueFactory<>("aula"));

        TableColumn<Incidencia, String> columnaDescripcion = new TableColumn<>("Descripción");
        columnaDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));

        TableColumn<Incidencia, String> columnaPrioridad = new TableColumn<>("Prioridad");
        columnaPrioridad.setCellValueFactory(new PropertyValueFactory<>("prioridad"));

        TableColumn<Incidencia, String> columnaEstado = new TableColumn<>("Estado");
        columnaEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));

        TableColumn<Incidencia, String> columnaTecnico = new TableColumn<>("Técnico");
        columnaTecnico.setCellValueFactory(new PropertyValueFactory<>("tecnico"));

        tabla.getColumns().addAll(
                columnaCodigo, columnaAula, columnaDescripcion,
                columnaPrioridad, columnaEstado, columnaTecnico
        );

        tabla.setItems(incidencias);
        tabla.setPlaceholder(new Label("No hay incidencias registradas"));
    }

    private void anadirIncidencia() {

        String codigo = campoCodigo.getText().trim();
        String aula = campoAula.getText().trim();
        String descripcion = campoDescripcion.getText().trim();
        String prioridad = comboPrioridad.getValue();
        String estado = comboEstado.getValue();
        String tecnico = campoTecnico.getText().trim();

        String error = validarFormulario(codigo, aula, descripcion, prioridad);

        if (!error.isEmpty()) {
            mostrarAlerta(Alert.AlertType.ERROR, "Error de validación", error);
            return;
        }

        boolean codigoRepetido = incidencias.stream()
                .anyMatch(i -> i.getCodigo().equalsIgnoreCase(codigo));

        if (codigoRepetido) {
            mostrarAlerta(
                    Alert.AlertType.ERROR,
                    "Código duplicado",
                    "Ya existe una incidencia con el código " + codigo + "."
            );
            return;
        }

        Incidencia incidencia = new Incidencia(
                codigo, aula, descripcion, prioridad, estado, tecnico
        );

        incidencias.add(incidencia);

        limpiarFormulario();
        actualizarEstadisticas();

        mostrarAlerta(
                Alert.AlertType.INFORMATION,
                "Incidencia guardada",
                "La incidencia " + codigo + " se ha añadido correctamente."
        );
    }

    private String validarFormulario(String codigo, String aula,
                                     String descripcion, String prioridad) {

        if (codigo.isEmpty()) {
            return "El código es obligatorio.";
        }

        if (!codigo.matches("INC-\\d{3}")) {
            return "El código debe tener el formato INC-000. Ejemplo: INC-001.";
        }

        if (aula.isEmpty()) {
            return "El aula es obligatoria.";
        }

        if (descripcion.length() < 10) {
            return "La descripción debe tener al menos 10 caracteres.";
        }

        if (prioridad == null) {
            return "Debes seleccionar una prioridad.";
        }

        return "";
    }

    private void actualizarEstado() {

        Incidencia seleccionada = tabla.getSelectionModel().getSelectedItem();

        if (seleccionada == null) {
            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "No hay selección",
                    "Selecciona una incidencia en la tabla para actualizar su estado."
            );
            return;
        }

        String nuevoEstado = comboEstado.getValue();

        if (nuevoEstado == null) {
            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Estado no seleccionado",
                    "Selecciona un estado antes de actualizar la incidencia."
            );
            return;
        }

        seleccionada.setEstado(nuevoEstado);
        tabla.refresh();
        actualizarEstadisticas();

        mostrarAlerta(
                Alert.AlertType.INFORMATION,
                "Estado actualizado",
                "La incidencia " + seleccionada.getCodigo()
                        + " ahora está en estado: " + nuevoEstado + "."
        );
    }

    private void eliminarIncidencia() {

        Incidencia seleccionada = tabla.getSelectionModel().getSelectedItem();

        if (seleccionada == null) {
            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "No hay selección",
                    "Selecciona una incidencia en la tabla para eliminarla."
            );
            return;
        }

        incidencias.remove(seleccionada);
        actualizarEstadisticas();

        mostrarAlerta(
                Alert.AlertType.INFORMATION,
                "Incidencia eliminada",
                "La incidencia " + seleccionada.getCodigo() + " ha sido eliminada."
        );
    }

    private void filtrarIncidencias() {

        String filtro = campoBusqueda.getText().trim().toLowerCase();

        if (filtro.isEmpty()) {
            mostrarTodas();
            return;
        }

        ObservableList<Incidencia> resultados = incidencias.filtered(incidencia ->
                incidencia.getCodigo().toLowerCase().contains(filtro)
                || incidencia.getAula().toLowerCase().contains(filtro)
                || incidencia.getDescripcion().toLowerCase().contains(filtro)
                || incidencia.getTecnico().toLowerCase().contains(filtro)
        );

        tabla.setItems(resultados);
    }

    private void mostrarTodas() {
        tabla.setItems(incidencias);
        campoBusqueda.clear();
    }

    private void limpiarFormulario() {
        campoCodigo.clear();
        campoAula.clear();
        campoDescripcion.clear();
        comboPrioridad.setValue(null);
        comboEstado.setValue("Abierta");
        campoTecnico.clear();
    }

    private void actualizarEstadisticas() {

        long abiertas = incidencias.stream()
                .filter(i -> i.getEstado().equals("Abierta"))
                .count();

        long enProceso = incidencias.stream()
                .filter(i -> i.getEstado().equals("En proceso"))
                .count();

        long resueltas = incidencias.stream()
                .filter(i -> i.getEstado().equals("Resuelta"))
                .count();

        etiquetaTotal.setText("Total: " + incidencias.size());
        etiquetaAbiertas.setText("Abiertas: " + abiertas);
        etiquetaEnProceso.setText("En proceso: " + enProceso);
        etiquetaResueltas.setText("Resueltas: " + resueltas);
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {

        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}