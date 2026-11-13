package gridpane; //Modificar al package correcto
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
public class EjemploGridPane extends Application {
    @Override
    public void start(Stage primaryStage) {
        GridPane gridPane=new GridPane();
        //Se rellena el array
        for (int fila=0; fila < 3; fila++) {
            for (int columna=0; columna < 3; columna++) {
                //Estamos creando imagen con funcionamiento personalizado
                ImageView imageView=insertaFoto("https://images.virgula.me/2017/04/robocop1.jpg");
                imageView.setFitHeight(100);
                imageView.setFitWidth(100);
                gridPane.add(imageView, columna, fila);
            }
        }
        //Espaciado horizontal y vertical y además un borde de 20px
        gridPane.setHgap(10); 
        gridPane.setVgap(10); 
        gridPane.setPadding(new Insets(30)); 
        Scene scene=new Scene(gridPane, 380, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("GridView Interactivo");
        primaryStage.show();
    }
    private ImageView insertaFoto(String imageURL) {
        Image image=new Image(imageURL);
        ImageView imageView=new ImageView(image);
        //Agregar un evento de clic a la imagen
        //Pero NO puedo saber fila/columna
        imageView.setOnMouseClicked(event -> {
            System.out.println("Click en" + imageURL);
        });
        //Agregar un evento de mouse hover y exit (ratón sobre o fuera de la Foto)
        imageView.setOnMouseEntered(event -> {
            imageView.setOpacity(0.8); 
        });
        imageView.setOnMouseExited(event -> {
            imageView.setOpacity(1.0); 
        });
        return imageView;
    }
    public static void main(String[] args) {
        launch(args);
    }
}

/*

public class ej5 extends Application {

    private GridPane gridPane;  // Definimos el GridPane como variable de instancia
    private ObservableList<Cerveza> cervezas;  // Definimos la lista de cervezas como variable de instancia
    public static void main(String[] args) {
        launch(args);
    }
    // Modelo de datos: Cerveza
    class Cerveza {
        private String imagenUrl;
        private String nombre;
        private String nacionalidad;
        private double grados;
        
        public Cerveza(String imagenUrl, String nombre, String nacionalidad, double grados) {
            this.imagenUrl = imagenUrl;
            this.nombre = nombre;
            this.nacionalidad = nacionalidad;
            this.grados = grados;
        }
        public String getImagenUrl() {
            return imagenUrl;
        }
        public String getNombre() {
            return nombre;
        }
        public String getNacionalidad() {
            return nacionalidad;
        }
        public double getGrados() {
            return grados;
        }
    }
    @Override
    public void start(Stage stage) {
        stage.setTitle("Cervezas GridPane");
        // Lista de cervezas
        cervezas = FXCollections.observableArrayList(
            new Cerveza("https://media.gettyimages.com/id/173907032/es/foto/botella-de-cerveza-en-el-hielo-cubierto.jpg?s=2048x2048&w=gi&k=20&c=75Ktn1No2VpxkXRFSKlTAbJkdIFoPrdhEVUjs2sVG98=", "Cerveza 1", "Alemania", 5.5),
            new Cerveza("https://media.gettyimages.com/id/173907032/es/foto/botella-de-cerveza-en-el-hielo-cubierto.jpg?s=2048x2048&w=gi&k=20&c=75Ktn1No2VpxkXRFSKlTAbJkdIFoPrdhEVUjs2sVG98=", "Cerveza 2", "Bélgica", 6.0),
            new Cerveza("https://media.gettyimages.com/id/173907032/es/foto/botella-de-cerveza-en-el-hielo-cubierto.jpg?s=2048x2048&w=gi&k=20&c=75Ktn1No2VpxkXRFSKlTAbJkdIFoPrdhEVUjs2sVG98=", "Cerveza 3", "México", 4.8),
            new Cerveza("https://media.gettyimages.com/id/173907032/es/foto/botella-de-cerveza-en-el-hielo-cubierto.jpg?s=2048x2048&w=gi&k=20&c=75Ktn1No2VpxkXRFSKlTAbJkdIFoPrdhEVUjs2sVG98=", "Cerveza 4", "España", 5.2),
            new Cerveza("https://media.gettyimages.com/id/173907032/es/foto/botella-de-cerveza-en-el-hielo-cubierto.jpg?s=2048x2048&w=gi&k=20&c=75Ktn1No2VpxkXRFSKlTAbJkdIFoPrdhEVUjs2sVG98=", "Cerveza 5", "EEUU", 7.0),
            new Cerveza("https://media.gettyimages.com/id/173907032/es/foto/botella-de-cerveza-en-el-hielo-cubierto.jpg?s=2048x2048&w=gi&k=20&c=75Ktn1No2VpxkXRFSKlTAbJkdIFoPrdhEVUjs2sVG98=", "Cerveza 15", "Holanda", 5.6),
            new Cerveza("https://media.gettyimages.com/id/173907032/es/foto/botella-de-cerveza-en-el-hielo-cubierto.jpg?s=2048x2048&w=gi&k=20&c=75Ktn1No2VpxkXRFSKlTAbJkdIFoPrdhEVUjs2sVG98=", "Cerveza 16", "Irlanda", 4.3)
        );
        // Crear GridPane
        gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        // Inicialmente llenamos el GridPane
        actualizarGridPane();
         // Crear un ScrollPane para envolver el GridPane
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(gridPane);  // Añadir el GridPane al ScrollPane
        scrollPane.setFitToWidth(true);   // Ajustar el ancho del contenido al ancho del ScrollPane
        // Escena
        Scene scene = new Scene(scrollPane, 600, 600);
        stage.setScene(scene);
        stage.show();
    }
    // Método para crear un panel personalizado para cada cerveza
    private Pane crearPaneCerveza(Cerveza cerveza) {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));
        vbox.setStyle("-fx-border-color: black; -fx-border-width: 2px;");
        // Mostrar imagen (opcional, ya que sólo se muestra la URL en el Alert)
        ImageView imageView = new ImageView(new Image(cerveza.getImagenUrl(), 100, 100, false, false));
        Label nombreLabel = new Label("Nombre: " + cerveza.getNombre());
        Label nacionalidadLabel = new Label("Nacionalidad: " + cerveza.getNacionalidad());
        Label gradosLabel = new Label("Grados: " + cerveza.getGrados());
        Button infoButton = new Button("Ver Detalles");
        infoButton.setOnAction(event -> {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Detalles de la Cerveza");
            alert.setHeaderText(cerveza.getNombre());
            alert.setContentText("Nombre: " + cerveza.getNombre() + "\n"
                                + "Nacionalidad: " + cerveza.getNacionalidad() + "\n"
                                + "Grados: " + cerveza.getGrados() + "\n"
                                + "Imagen URL: " + cerveza.getImagenUrl());
            alert.showAndWait();
        });
        // Botón para eliminar cerveza
        Button eliminarButton = new Button("Eliminar");
        eliminarButton.setOnAction(event -> {
            cervezas.remove(cerveza);  // Elimina la cerveza del ObservableList
            actualizarGridPane();  // Actualizamos el GridPane
        });
        vbox.getChildren().addAll(imageView, nombreLabel, nacionalidadLabel, gradosLabel, infoButton, eliminarButton);
        vbox.setAlignment(Pos.CENTER);
        return vbox;
    }
    // Método para actualizar el GridPane después de eliminar una cerveza
    private void actualizarGridPane() {
        gridPane.getChildren().clear();  // Limpiamos el GridPane
        int row = 0;
        int col = 0;
        // Volvemos a añadir los paneles al GridPane
        for (Cerveza cerveza : cervezas) {
            Pane cervezaPane = crearPaneCerveza(cerveza);
            gridPane.add(cervezaPane, col, row);
            col++;
            if (col > 3) {
                col = 0;
                row++;
            }
        }
    }
}*/