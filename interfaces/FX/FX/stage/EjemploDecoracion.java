package stage;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
public class EjemploDecoracion extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        VBox vbox=new VBox();
        Scene scene=new Scene(vbox);
        primaryStage.setTitle("No me veo!!");
        primaryStage.setScene(scene);
        //primaryStage.initStyle(StageStyle.DECORATED); //Normal
        primaryStage.initStyle(StageStyle.UNDECORATED); //Sin botones ni título, NI SE PUEDE MOVER
        //primaryStage.setOpacity(0.7); //TRANSPARENTE
        //primaryStage.initStyle(StageStyle.UTILITY); //Solo con cierre
        primaryStage.show();
    }
}