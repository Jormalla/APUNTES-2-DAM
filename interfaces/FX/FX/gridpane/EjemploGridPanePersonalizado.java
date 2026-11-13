package gridpane; //Modificar al package correcto
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class EjemploGridPanePersonalizado extends Application {
    @Override
    public void start(Stage primaryStage) {
        GridPane gridPane = new GridPane();
        ObservableList<String> seriesLista = FXCollections.observableArrayList(
                "Breaking Bad",
                "The Expanse",
                "Stranger Things",
                "Last Of Us",
                "The Mandalorian",
                "Friends",
                "The IT"
        );
        //Se rellena el array
        int i = 0;
        int numfilas = 3;
        for (int fila = 0; fila < numfilas; fila++) {
            for (int columna = 0; columna < 3; columna++) {
                i = fila * numfilas + columna;
                if (i < seriesLista.size()) {//Para no colarnos de los datos que hay
                    System.out.println(i);
                    seriesLista.get(i);
                    StackPane p = insertaPane(seriesLista.get(i));
                    gridPane.add(p, columna, fila);
                }
            }
        }
        //Espaciado horizontal y vertical y además un borde de 20px
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(30));
        Scene scene = new Scene(gridPane, 380, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("GridView Interactivo");
        primaryStage.show();
    }
    //Se devuelve un StackPane porque lo centra todo automáticamente
    private StackPane insertaPane(String serie) {
        StackPane p = new StackPane();
        p.setPrefSize(200, 200);
        p.setStyle("-fx-background-color: lightblue");
        Button b = new Button("DATOS");
        Label l = new Label(serie);
        VBox vbox = new VBox(10, l, b);
        vbox.setAlignment(Pos.CENTER);
        p.getChildren().add(vbox);
        //Agregar un evento de clic a la imagen
        b.setOnMouseClicked(event -> {
            System.out.println("Click en " + serie);
        });
        //Agregar un evento de mouse hover y exit (ratón sobre o fuera de la Foto)
        p.setOnMouseEntered(event -> {
            p.setOpacity(0.8);
        });
        p.setOnMouseExited(event -> {
            p.setOpacity(1.0);
        });
        return p;
    }
    public static void main(String[] args) {
        launch(args);
    }
}