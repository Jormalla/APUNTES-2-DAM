package filechooser;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
public class EjemploFileChooserImagen extends Application {
    ImageView foto;
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("JavaFX App - Selector de Ficheros");
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("src"));
        //fileChooser.setInitialFileName("a.txt");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Imagen jpg", "*.jpg"),
                new FileChooser.ExtensionFilter("Imagen png", "*.png")
        );        
        foto = new ImageView();
        Button button = new Button("Selecciona fichero");
        button.setOnAction(e -> {
            File archivoSeleccionado = fileChooser.showOpenDialog(null);
            if (archivoSeleccionado != null) {
                String rutaArchivo = archivoSeleccionado.toURI().toString();
                Image imagen = new Image(rutaArchivo);
                int anchoPref = 200;
                int altoPref = 200;
                foto.setImage(imagen);
                foto.setFitWidth(anchoPref);
                foto.setFitHeight(altoPref);
            }
        });
        VBox vBox = new VBox(button, foto);
        Scene scene = new Scene(vBox, 500, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}