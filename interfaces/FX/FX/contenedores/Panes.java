package contenedores;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class Panes extends Application {
    /*
    En el Pane, utilizamos las propiedades setLayoutX y setLayoutY para definir la posición
    del botón dentro del Pane: se ancla a una posición FIJA referente al borde superior izquierdo (0,0)
    En el AnchorPane, utilizamos las propiedades setTopAnchor y setLeftAnchor para 
    definir la posición del botón en relación con los bordes superior e izquierdo del AnchorPane.
    Lo mismo con setBottomAnchor y setRightAnchor: se ancla con respectoa a un BORDE
     */
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Ventana 1: Pane");
        // Crear un Pane
        Pane pane=new Pane();
        Button buttonPane=new Button("Botón en Pane");
        buttonPane.setLayoutX(20);
        buttonPane.setLayoutY(20);
        pane.getChildren().add(buttonPane);
        Scene scene1=new Scene(pane, 300, 300);
        primaryStage.setScene(scene1);
        primaryStage.show();
        // Crear una segunda ventana (Stage)
        Stage secondStage=new Stage();
        secondStage.setTitle("Ventana 2: AnchorPane");
        // Crear un AnchorPane
        AnchorPane anchorPane=new AnchorPane();
        Button buttonAnchorPane=new Button("Botón en AnchorPane");
        //Restricción(nodo,distancia)
        AnchorPane.setBottomAnchor(buttonAnchorPane, 20.0);
        AnchorPane.setRightAnchor(buttonAnchorPane, 20.0);
        anchorPane.getChildren().add(buttonAnchorPane);
        // Crear una escena que contiene ambos paneles
        //Scene scene2=new Scene(new VBox(pane, anchorPane), 300, 300);
        Scene scene2=new Scene(anchorPane, 300, 300);
        secondStage.setScene(scene2);
        secondStage.show();
    }
}