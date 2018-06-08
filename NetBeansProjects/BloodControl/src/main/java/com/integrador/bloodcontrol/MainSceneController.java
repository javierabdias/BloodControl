package com.integrador.bloodcontrol;


import com.integrador.Consultas.Extraccion;
import com.integrador.Consultas.Informacion_Cita;
import com.integrador.Consultas.Usuario;
import com.integrador.Modificaciones.ExtraccionStatus;
import com.integrador.POJOLista.Laboratorista;
import com.integrador.POJOLista.Pacientes;
import com.integrador.bloodcontrol.Funciones.Funciones;
import com.integrador.bloodcontrol.Funciones.Reloj;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

public class MainSceneController extends Funciones implements Initializable {

    
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
    @FXML
    private Label hora_cita_ini1;
    @FXML
    private Label cita_nom_ini1;
    @FXML
    private JFXButton exa_agregar;
    @FXML
    private JFXButton exa_eliminar;
    @FXML
    private JFXButton exa_modificar;
    @FXML
    private JFXButton exa_actualizar;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usuario();
        Inicio();
        accionBotonesPac();
    }    
    
    private void Inicio(){
        ObservableList <String> status = FXCollections.observableArrayList("REALIZADO","NO REALIZADO");
        combo_cita_ini.setItems(status);
        Thread thread= new Thread(new Reloj(reloj));
        thread.setDaemon(true);
        thread.start();
        iniTablaCitas();
        accionBotonesIni();
        fecha(fecha);
    }
    
    private void usuario(){
        Usuario usuario = new Usuario();
        usuario.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, (WorkerStateEvent event) -> {
            id.setText(usuario.getValue().get(0).getNombre()+" "+usuario.getValue().get(0).getApePat()+" "+usuario.getValue().get(0).getApeMat());
            correo.setText(usuario.getValue().get(0).getCorreo());
        });
        new Thread(usuario).start();
    }
    
    private void iniTablaCitas(){
        Extraccion e= new Extraccion();
        e.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, (WorkerStateEvent  event)->{
            tabla_pac.setItems(e.getValue());
            id_pac.setCellValueFactory(new PropertyValueFactory<>("id"));
            nom_pac.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            ape_pat.setCellValueFactory(new PropertyValueFactory<>("apePat"));
            ape_mat.setCellValueFactory(new PropertyValueFactory<>("apeMat"));
            status.setCellValueFactory(new PropertyValueFactory<>("status"));
        });
        new Thread(e).start();
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
            System.out.println(Status(combo_cita_ini.getValue()));
            ExtraccionStatus ES= new ExtraccionStatus(id_Cita,Status(combo_cita_ini.getValue()));
            new Thread(ES).start();
            backtoBeginning();
        });
    }
       
    private void citaInformacion(){
        Informacion_Cita ic= new Informacion_Cita(id_Cita);
        
        ic.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, (WorkerStateEvent event) ->{ 
            List <Object> lista= (List) ic.getValue().get(0);
            Pacientes pac = (Pacientes) ic.getValue().get(1);
  
            for (int i=0; i<lista.size();i++){
                area_cita_ini.appendText("*     "+lista.get(i)+"\n");
            }
        
            hora_cita_ini.setText(pac.getHora().toString());
            cita_nom_ini.setText(pac.getNombre()+" "+pac.getApePat()+" "+pac.getApeMat());
            combo_cita_ini.setValue(pac.getStatus());            
        });
        new Thread(ic).start();
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
    
    private void accionBotonesPac(){
        pac_anadir.setOnAction(e->{
            new Thread (new AbrirVentana("/Pacientes/AgregarPaciente.fxml","Añadir Paciente")).start();
        });
    }
     
    private String Status(String status){
        if(status.equals("REALIZADO")){
            return "R";
        }
        return "N";
    }
    
    }
        

