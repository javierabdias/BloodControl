package com.integrador.bloodcontrol;


import com.integrador.Consultas.ActualizarEstado;
import com.integrador.Consultas.Inicio_Tabla_Citas;
import com.integrador.Consultas.QueryUsuario;
import com.integrador.Consultas.citaInformacion;
import com.integrador.POJOLista.Pacientes;
import com.integrador.bloodcontrol.Funciones.Reloj;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.WorkerStateEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author abdias
 */

//  **CLASE DE MAIN CONTROLLER**

public class MainSceneController implements Initializable {

    
    // ID'S MAIN CONTROLLER 
    @FXML
    private Label reloj;
    @FXML
    private Label id;
    @FXML
    private Label correo;
    @FXML
    private Label fecha;
    
    //  ID's INICIO
    int id_Cita;
    @FXML
    private TableColumn<Pacientes, String> nom_pac;
    @FXML
    private TableColumn<Pacientes, String> status;
    @FXML
    private TableColumn<Pacientes, Integer> id_pac;
    @FXML
    private TableView<Pacientes> tabla_pac;
    @FXML
    private TableColumn<Pacientes, String> ape_pat;
    @FXML
    private TableColumn<Pacientes, String> ape_mat;
    @FXML
    private JFXButton ini_actualizar;
    @FXML
    private Label hora_cita_ini;
    @FXML
    private JFXTextArea area_cita_ini;
    @FXML
    private JFXCheckBox cita_check_ini;
    @FXML
    private JFXButton ace_cita_ini;
    @FXML
    private JFXComboBox<String> combo_cita_ini;
    @FXML
    private Label cita_nom_ini;
   
    //  CITAS
    @FXML
    private JFXButton cit_agregar;
    @FXML
    private JFXButton cit_actualizar;
    @FXML
    private JFXButton cit_modificar;
    @FXML
    private JFXButton cit_eliminar;
    
    //  PACIENTES
    @FXML
    private JFXButton pac_anadir;
    @FXML
    private JFXButton pac_editar;
    @FXML
    private JFXButton pac_eliminar;
    @FXML
    private JFXButton pac_actualizar;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usuario();
        Inicio();
    }    
    
    private void Inicio(){
        ObservableList <String> status = FXCollections.observableArrayList("REALIZADO","SIN REALIZAR");
        combo_cita_ini.setItems(status);
        Thread thread= new Thread(new Reloj(reloj));
        thread.setDaemon(true);
        thread.start();
        iniTablaCitas();
        accionBotonesIni();
        fecha(fecha);
    }
    
    private void usuario(){
        QueryUsuario qu= new QueryUsuario();
        qu.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, (WorkerStateEvent event) -> {
            id.setText(qu.getValue().get(0).toString());
            correo.setText(qu.getValue().get(1).toString());
        });
        new Thread(qu).start();
    }
    
    private void iniTablaCitas(){
        
        Inicio_Tabla_Citas itc= new  Inicio_Tabla_Citas();
        
        itc.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, (WorkerStateEvent event) -> {
        ObservableList<Pacientes> tabla = FXCollections.observableArrayList(itc.getValue());
        
        tabla_pac.setItems(tabla);
        id_pac.setCellValueFactory(new PropertyValueFactory<>("id"));
        nom_pac.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        ape_pat.setCellValueFactory(new PropertyValueFactory<>("apePat"));
        ape_mat.setCellValueFactory(new PropertyValueFactory<>("apeMat"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        });
        
        new Thread(itc).start();
    }
    
    private void accionBotonesIni(){        
        ini_actualizar.setOnAction(e->{
           iniTablaCitas();
           backtoBeginning();
        });
        
        tabla_pac.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) ->{
            if (!tabla_pac.getSelectionModel().isEmpty()){ 
            area_cita_ini.setText("");
            id_Cita=tabla_pac.getSelectionModel().getSelectedItem().getId();
            combo_cita_ini.setValue(tabla_pac.getSelectionModel().getSelectedItem().getStatus());
            citaInformacion();}
        });
        
        cita_check_ini.setOnAction(e->{
            if(cita_check_ini.isSelected()){
                ace_cita_ini.setDisable(false);
                combo_cita_ini.setDisable(false);
            } else {
                ace_cita_ini.setDisable(true);
                combo_cita_ini.setDisable(true);
            }
        });
        
        ace_cita_ini.setOnAction(e-> {
            ActualizarEstado ae = new ActualizarEstado(id_Cita,combo_cita_ini.getValue());
            new Thread (ae).start();
            backtoBeginning();
        });
    }
       
    private void citaInformacion(){
            citaInformacion ci= new citaInformacion(id_Cita);
            ci.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, (WorkerStateEvent event) -> {
                
                ObservableList<Pacientes> tabla = FXCollections.observableArrayList(ci.getValue().get(0));
                ObservableList<String> tabla2 = FXCollections.observableArrayList(ci.getValue().get(1));
                
                hora_cita_ini.setText(tabla.get(0).getHora().toString());
                cita_nom_ini.setText(tabla.get(0).getNombre()+" "+tabla.get(0).getApePat()+" "+tabla.get(0).getApeMat());
                
                for (String t: tabla2){
                    area_cita_ini.appendText(t+"\n");
                }
            });       
            
            new Thread(ci).start();
        }
    
    private void backtoBeginning(){
       
        ace_cita_ini.setDisable(true);
        combo_cita_ini.setDisable(true);
        cita_check_ini.setSelected(false);
        hora_cita_ini.setText("00:00:00");
        cita_nom_ini.setText("Nombre del paciente");
        area_cita_ini.setText(""); 
    }
    
    private void fecha(Label l1){
        Date myDate = new Date();
        l1.setText(new SimpleDateFormat("dd-MM-yyyy").format(myDate));
    }
    
    }
        

