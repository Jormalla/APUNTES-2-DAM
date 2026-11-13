package cargaImagenes;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.scene.control.Alert;
import javafx.scene.layout.HBox;
public class EjemploCargaImágenes extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override //ejemplo para cargar imagenes
    public void start(Stage primaryStage) throws IOException {
        //3 formas de cargar imágenes
        //desde recursos externos (fuera del proyecto)
        ImageView imageView1=cargaImageViewAssets();
        //desde URL (web)
        ImageView imageView2 = new ImageView();
        Image imagen = new Image("https://images.virgula.me/2017/04/robocop1.jpg", true);//Booleano para preservar el ratio
        imageView2.setImage(imagen);
        imageView2.setFitHeight(80);
        imageView2.setFitWidth(80);
        //desde carpeta resources dentro del proyecto
        Image icono=new Image(getClass().getClassLoader().getResourceAsStream("pcicon.png"));
        ImageView imageView3 = new ImageView(icono);
        HBox hbox=new HBox(imageView1,imageView2,imageView3);
       // hbox.setPadding(new Insets(20));
               imageView3.setOnMouseReleased((eh)->{ 
            Alert alertInfo=new Alert(Alert.AlertType.INFORMATION);
            alertInfo.setTitle("Información");
            alertInfo.setHeaderText("Esto es la cabecera");
            alertInfo.setContentText("Este es el contenido");      
                        alertInfo.showAndWait();

        });  
        imageView1.setOnMousePressed( (eh)->{ 
            Alert alertInfo=new Alert(Alert.AlertType.INFORMATION);
            alertInfo.setTitle("Información");
            alertInfo.setHeaderText("Esto es la cabecera");
            alertInfo.setContentText("Este es el contenido");    
            alertInfo.showAndWait();
        });  
        imageView2.setOnMousePressed( (eh)->{ 
            Alert alertInfo=new Alert(Alert.AlertType.INFORMATION);
            alertInfo.setTitle("Información");
            alertInfo.setHeaderText("Esto es la cabecera");
            alertInfo.setContentText("Este es el contenido");   
                        alertInfo.showAndWait();

        });
        Scene scene=new Scene(hbox);
        primaryStage.setScene(scene);
        primaryStage.setWidth(200);
        primaryStage.setHeight(130);
        primaryStage.show();

    }
    private ImageView cargaImageViewAssets() throws FileNotFoundException {
        FileInputStream input=new FileInputStream("assets/iconos/add.png");
        Image image=new Image(input);
        ImageView imageView=new ImageView(image);
        return imageView;
    }
}