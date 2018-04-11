package com.integrador.bloodcontrol.Funciones;

import com.integrador.bloodcontrol.MainApp;
import com.integrador.persistence.EManagerFactory;
import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author abdias
 */

//  **CLASE ABSTRACTA PARA MÉTODOS QUE SE UTILIZAN EN TODO EL PROGRAMA**

public abstract class Funciones {
    
    // Método para crear nuevas ventanas
    public void crearVentanas(String ruta, String nomVentana) throws IOException {
        Parent root = (Parent) FXMLLoader.load(MainApp.class.getResource(ruta));
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle(nomVentana);
        stage.setResizable(false);
        stage.initModality(Modality.APPLICATION_MODAL);   
        stage.getIcons().add(new Image(MainApp.class.getResourceAsStream("/MainScene/logo8x8-255.png")));
        valCerrar(stage);
        stage.showAndWait();  
        stage=null;
        System.gc();
    }
    
    //Método para cerrar la coneción con la base de datos
     public void valCerrar(Stage sta){
        sta.setOnCloseRequest((WindowEvent event) -> {
            EManagerFactory.getEntityManagerFactory().close();
        });
     }

}
