/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.integrador.bloodcontrol.persistence;

import com.integrador.bloodcontrol.MainApp;
import java.io.IOException;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

/**
 *
 * @author abdias
 */

//  *** HILO PARA CERRAR SPLASH Y ABRIR LOG-IN ***

public class LogIn extends Task<Void>{
    
    private Stage s;

    //Recibe un stage
    public LogIn(Stage s) {
        this.s = s;
    }
    
    @Override
    protected Void call() throws Exception {
        
        Platform.runLater(()->{
            //Se cierra el stage
            s.close();
            //Se libera memoria
            System.gc();
            //Se abre el Lon-In
            AbrirNueva("LogIn.fxml");
        });
        
        
        return null;
    }
    
    //Método para abrir un nuevo stage
    
     public void AbrirNueva(String fxml){
      
          Stage cuerpo= new Stage();
          Parent pane = null;
          
          try {
              pane = FXMLLoader.load(com.integrador.bloodcontrol.MainApp.class.getResource("/LogIn/"+fxml));
              Scene scene = new Scene(pane);
              cuerpo.setScene(scene);
              cuerpo.setResizable(false);
              scene.setFill(Color.TRANSPARENT);
              cuerpo.initStyle(StageStyle.TRANSPARENT);
              cuerpo.getIcons().add(new Image(MainApp.class.getResourceAsStream("/MainScene/logo8x8-255.png")));
              cuerpo.centerOnScreen();
              valCerrar(cuerpo);
              cuerpo.show();
          } catch (IOException ex) {
              ex.printStackTrace();
          }
          cuerpo=null;
      }
     
       // Método para cerrar conexión con BD
     
       public void valCerrar(Stage sta){
        sta.setOnCloseRequest((WindowEvent event) -> {
            EManagerFactory.getEntityManagerFactory().close();
        });
     }
}
