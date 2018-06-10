package com.integrador.bloodcontrol;


import com.integrador.Consultas.LogIn;
import com.integrador.bloodcontrol.Funciones.Funciones;
import com.integrador.bloodcontrol.Funciones.Usuarios;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.WorkerStateEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.stage.Stage;

//  ** CLASE CONTROLLER DE LOG-IN**
public class LogInController extends Funciones implements Initializable {

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
    @FXML
    private JFXRadioButton Admin;
    @FXML
    private JFXRadioButton Recep;
    @FXML
    private JFXRadioButton Lab;
    
    public String tipo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        TextFieldNumeros(txt_Usuario);
        setTextFieldLimit(txt_Usuario, 6);
        
        ToggleGroup group = new ToggleGroup();
        Admin.setToggleGroup(group);
        Recep.setToggleGroup(group);
        Lab.setToggleGroup(group);

        //Volver transparente AnchorPane
        anchor.setBackground(Background.EMPTY);

        //Acción botón cerrar
        btn_cerrar.setOnMouseClicked((e) -> {
            System.exit(0);
        });

        //Hilos para consultas
        btn_aceptar.setOnAction((e) -> {

            if (txt_Usuario.getText().length() == 0 || txt_Contra.getText().length() == 0) {
                Alertas.error("Error", "Casillas vacías", "Existen casillas vacías en el formulario, verifique");
            } else if (!Admin.isSelected()&&!Recep.isSelected()&&!Lab.isSelected()){
                 Alertas.error("Error", "Sin selección de usurio", "Elija un tipo de usuario para continuar.");
            } 
            else{

                btn_aceptar.setDisable(true);
                progress.setVisible(true);  
                
                if(Admin.isSelected()){
                    tipo= Admin.getText();
                    Validacion();
                }   else if(Recep.isSelected()){
                    tipo= Recep.getText();
                    Validacion();
                }   else if (Lab.isSelected()){
                    tipo= (Lab.getText());
                    Validacion();
                }
            }
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
            Usuarios.setId(Integer.valueOf(txt_Usuario.getText()));
            Usuarios.setTipo(tipo);
            anchor.getScene().getWindow().hide();
            crearVentanas("/MainScene/MainScene.fxml", "BloodControl");
        } catch (IOException ex) {
            Logger.getLogger(LogInController.class.getName()).log(Level.SEVERE, null, ex);
            System.gc();
        }
    }
    
    
    
    
    public void Validacion() {
        LogIn l = new LogIn(txt_Usuario.getText(), txt_Contra.getText(), tipo);
        l.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, (WorkerStateEvent event) -> {

            boolean resultado = l.getValue();
            if (resultado) {
                mainFrame();
            } else {
                progress.setVisible(false);
                btn_aceptar.setDisable(false);
                Alertas.error("Error", "Usuario o contraseña incorrectos.", "Verifique los datos de usuario y privilegios.");
                System.gc();
            }
        });

        new Thread(l).start();
    }
    
}
