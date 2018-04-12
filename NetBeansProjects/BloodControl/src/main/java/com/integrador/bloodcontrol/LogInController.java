package com.integrador.bloodcontrol;

import com.integrador.Consultas.LogIn;
import com.integrador.POJO.Usuarios;
import com.integrador.bloodcontrol.Funciones.Funciones;
import com.integrador.persistence.EManagerFactory;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.stage.Stage;
import javax.persistence.EntityManager;

//  ** CLASE CONTROLLER DE LON-IN**
public class LogInController extends Funciones implements Initializable {

    static int id_usuario;
    
    @FXML
    private AnchorPane anchor;
    @FXML
    private JFXTextField txt_Usuario;
    @FXML
    private JFXPasswordField txt_Contra;
    @FXML
    private JFXButton btn_aceptar;
    @FXML
    private ImageView btn_minimizar;
    @FXML
    private ImageView btn_cerrar;
    @FXML
    private ProgressIndicator progress;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        TextFieldNumeros(txt_Usuario);
        setTextFieldLimit(txt_Usuario, 6);

        //Volver transparente AnchorPane
        anchor.setBackground(Background.EMPTY);

        //Acción botón cerrar
        btn_cerrar.setOnMouseClicked((e) -> {
            System.exit(0);
        });

        //Hilos para consultas
        btn_aceptar.setOnAction((e) -> {
            btn_aceptar.setDisable(true);
            progress.setVisible(true);
            
            LogIn l= new LogIn(txt_Usuario.getText(),txt_Contra.getText());
            l.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, (WorkerStateEvent event)->{
                boolean resultado = l.getValue();
                if(resultado){
                    mainFrame();
                } else {
                    progress.setVisible(false);
                    Alertas.error("Error", "Usuario o contraseña incorrectos..", "Verifique la información de usuario y privilegios.");
                }
            });
            new Thread(l).start();
        });

        //Acción botón minimizar        
        btn_minimizar.setOnMouseClicked((e) -> {
            Stage stage = (Stage) anchor.getScene().getWindow();
            stage.setIconified(true);
        });
    }

    //Método para abrir main frame
        public void mainFrame() {
            try {
                
                id_usuario=Integer.valueOf(txt_Usuario.getText());
                anchor.getScene().getWindow().hide();
                crearVentanas("/MainScene/MainScene.fxml", "BloodControl");
            } catch (IOException ex) {
                Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, null, ex);
                System.gc();
            }
        }
}

