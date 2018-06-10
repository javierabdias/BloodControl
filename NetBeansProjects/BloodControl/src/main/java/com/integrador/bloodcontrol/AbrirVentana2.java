package com.integrador.bloodcontrol;

import com.integrador.persistence.EManagerFactory;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

/**
 *
 * @author abdias
 */
public class AbrirVentana2 extends Task <Void>{
    
    String ruta, nomVentana;

    public AbrirVentana2(String ruta, String nomVentana) {
        this.ruta = ruta;
        this.nomVentana = nomVentana;
    }

    @Override
    protected Void call() throws Exception {
        Platform.runLater(() -> {
            Parent root;
            try {
                root = (Parent) FXMLLoader.load(MainApp.class.getResource(ruta));
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                scene.setFill(Color.TRANSPARENT);
                stage.initStyle(StageStyle.TRANSPARENT);
                stage.setTitle(nomVentana);
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.getIcons().add(new Image(MainApp.class.getResourceAsStream("/MainScene/logo8x8-255.png")));
                valCerrar(stage);
                stage = null;
                System.gc();
            } catch (IOException ex) {
                Logger.getLogger(AbrirVentana2.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        return null;
    }
    public void valCerrar(Stage sta){
        sta.setOnCloseRequest((WindowEvent event) -> {
            EManagerFactory.getEntityManagerFactory().close();
        });
}
}