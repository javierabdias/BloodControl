package com.integrador.bloodcontrol;

import com.integrador.bloodcontrol.persistence.Conexion;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.image.Image;

// **CLASE MAIN DE PROGRAMA, HILO DE SPLASH**

public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/Splash/Splash.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        //Volver scene transparente
        scene.setFill(Color.TRANSPARENT);
        //Volver stage transparente
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
        //Agregar ícono
        stage.getIcons().add(new Image(MainApp.class.getResourceAsStream("/MainScene/logo8x8-255.png")));
        //Hilo para realizar la conexión con la Base de Datos
        Conexion con = new Conexion(stage);
        new Thread(con).start();
        stage=null;
        System.gc();
        
        new Thread (new Resultado()).start();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
