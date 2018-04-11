/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.integrador.bloodcontrol.Funciones;

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

/**
 *
 * @author abdias
 */

public class LoadingFrame extends Task<Void> {
Stage cuerpo = new Stage();
Parent pane = null;
Task QueryLogIn;

    public LoadingFrame(Task QueryLogIn) {
        this.QueryLogIn = QueryLogIn;
    }


    @Override
    protected Void call() throws Exception {
       
        
         Platform.runLater(() -> {
                try {
                    //Se despliega nuevo stage
                    pane = FXMLLoader.load(com.integrador.bloodcontrol.MainApp.class.getResource("/Loading/Loading.fxml"));
                    Scene scene = new Scene(pane);
                    cuerpo.setScene(scene);
                    cuerpo.setResizable(false);
                    scene.setFill(Color.TRANSPARENT);
                    cuerpo.initStyle(StageStyle.TRANSPARENT);
                    cuerpo.getIcons().add(new Image(MainApp.class.getResourceAsStream("/MainScene/logo8x8-255.png")));
                    cuerpo.centerOnScreen();
                    cuerpo.showAndWait();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }); 
         
             //Se ejecuta el hilo de la consulta
                Thread.sleep(500);
                new Thread(QueryLogIn).start();
         
            Platform.runLater(()->{
                cuerpo.close();
            });
            
           return null;
    }
    
}
