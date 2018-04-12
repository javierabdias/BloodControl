package com.integrador.bloodcontrol;

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
            progress.setVisible(true);
            new Thread(new QueryLogIn()).start();
        });

        //Acción botón minimizar        
        btn_minimizar.setOnMouseClicked((e) -> {
            Stage stage = (Stage) anchor.getScene().getWindow();
            stage.setIconified(true);
        });
    }

//  **HILO DE CONSULTA Y ACCESO A PROGRAMA**
    private class QueryLogIn extends Task<Void> {

        @Override
        protected Void call() throws Exception {
            
            //Se realiza la consulta
            EntityManager manager = EManagerFactory.getEntityManagerFactory().createEntityManager();
            manager.getTransaction().begin();
            Usuarios u = manager.find(Usuarios.class, Integer.valueOf(txt_Usuario.getText()));
            manager.getTransaction().commit();
            manager.close();
            
            Platform.runLater(() -> {
                //Comprobación de usuario
                if (u == null) {
                    progress.setVisible(false);
                    Alertas.error("Usuario inexistente", "No se encontró usuario.", "Verifique que el ID esté escrito correctamente.");
                }
                //Verificación de contraseña
                else if (u.getUsuContra().equals(txt_Contra.getText())) {
                    //Verificación de tipo de usuario
                    switch (u.getUsuTipo()) {
                        case "LABORATORISTA":
                            mainFrame();
                            break;
                        case "RECEPCIONISTA":
                            progress.setVisible(false);
                            Alertas.error("Error de privilegios", "Usuario sin privilegios.", "El usuario ingresado no tiene derechos \npara utilizar el sistema.");
                            break;
                        case "ADMINISTRADOR":

                            mainFrame();
                            break;
                    }
                } else {
                    progress.setVisible(false);
                    Alertas.error("Contraseña incorrecta", "Contraseña errónea.", "Verifique que la contraseña esté escrita \ncorrectamente.");
                }
                //Se libera memoria
                System.gc();
            });

            return null;
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

}
