package com.integrador.bloodcontrol;


import com.integrador.POJOLista.Cita;
import com.integrador.bloodcontrol.Eliminaciones.EliminarPaciente;
import com.integrador.bloodcontrol.Consultas.Extraccion;
import com.integrador.bloodcontrol.Consultas.Informacion_Cita;
import com.integrador.bloodcontrol.Consultas.Paciente_Tabla;
import com.integrador.bloodcontrol.Consultas.Usuario;
import com.integrador.bloodcontrol.Modificaciones.ExtraccionStatus;
import com.integrador.POJOLista.Pacientes;
import com.integrador.bloodcontrol.Consultas.Cita_Tabla;
import com.integrador.bloodcontrol.Eliminaciones.EliminarCita;
import com.integrador.bloodcontrol.Funciones.Funciones;
import com.integrador.bloodcontrol.Funciones.Reloj;
import com.integrador.bloodcontrol.Funciones.Usuarios;
import com.integrador.bloodcontrol.POJO.Citas;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.concurrent.WorkerStateEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

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
    @FXML
    private JFXButton logout;
    @FXML
    private AnchorPane anchor;

    
    //TABS PERMISOS
    @FXML
    private JFXTabPane tabPane;
    @FXML
    private Tab inicio_lab;
    @FXML
    private Tab inicio_rec;
    @FXML
    private Tab pacientes;
    @FXML
    private Tab citas;
    @FXML
    private Tab estudios;
    @FXML
    private Tab examenes;
    @FXML
    private Tab usuarios;
    
    
    
    //  ID's INICIO LABORATORISTA
    
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
    
    
    
    //  ID'S INICIO RECEPCIONISTA
    
    @FXML
    private Label iniRec_NomExam;
    @FXML
    private JFXTextArea iniRec_estudios;
    @FXML
    private Label iniRec_costo;
    @FXML
    private JFXTextField iniRec_correo;
    @FXML
    private JFXButton iniRec_Buscar;
    @FXML
    private Label iniRec_Paciente;
    @FXML
    private JFXDatePicker iniRec_fecha;
    @FXML
    private JFXTimePicker iniRec_Hora;
    @FXML
    private JFXComboBox<?> iniRec_Examen;
    @FXML
    private JFXTextArea iniRec_Preliminar;
    @FXML
    private Label iniRec_total;
    @FXML
    private JFXButton btnIniRec_aceptar;
    @FXML
    private JFXButton btnIniRec_cancelar;
    @FXML
    private JFXButton btnIniRec_pagar;
    @FXML
    private JFXButton btnIniRec_agregar;
    
   
    
    //  CITAS
    @FXML
    private JFXButton cit_agregar;
    @FXML
    private JFXButton cit_actualizar;
    @FXML
    private JFXButton cit_modificar;
    @FXML
    private JFXButton cit_eliminar;

    @FXML
    private TableView<Cita> cit_tabla;
    @FXML
    private TableColumn<Cita, String> cit_Nom;
    @FXML
    private TableColumn<Cita, String> cit_ApePat;
    @FXML
    private TableColumn<Cita, String> cit_ApeMat;
    @FXML
    private TableColumn<Cita, Date> cit_fecha;
    @FXML
    private TableColumn<Cita, Date> cit_hora;
    @FXML
    private TableColumn<Cita, String> cit_pago;
    @FXML
    private TableColumn<Cita, String> cit_extraccion;
    @FXML
    private TableColumn<Cita, Integer> cit_id;
    @FXML
    private JFXComboBox<String> tipo;
    
    ObservableList <String> cita_status = FXCollections.observableArrayList("MOSTRAR TODO","REALIZADO","NO REALIZADO","PAGADO","SIN PAGAR");

    
    
    //  PACIENTES
    
    private static Pacientes paciente;
    
    @FXML
    private JFXButton pac_anadir;
    @FXML
    private JFXButton pac_editar;
    @FXML
    private JFXButton pac_eliminar;
    @FXML
    private JFXButton pac_actualizar;
   
    @FXML
    private TableView<Pacientes> pac_tabla;
    @FXML
    private TableColumn<Pacientes, String> pac_nombre;
    @FXML
    private TableColumn<Pacientes, String> pac_ap;
    @FXML
    private TableColumn<Pacientes, String> pac_am;
    @FXML
    private TableColumn<Pacientes, String> pac_correo;
    @FXML
    private TableColumn<Pacientes, Date> pac_fn;
    @FXML
    private TableColumn<Pacientes, String> pac_tel;
    @FXML
    private TableColumn<Pacientes, String> pac_cel;
    @FXML
    private JFXTextField pac_buscar;
    
    
    //  RESULTADOS
    
    private static Cita Citas;
    
    public static Cita getCitas() {
        return Citas;
    }
    
    @FXML
    private JFXButton btn_resultados;
    
    
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
    
    @FXML
    private JFXButton pac_anadir2;
    @FXML
    private JFXButton pac_editar1;
    @FXML
    private JFXButton pac_anadir21;
    @FXML
    private Label hora_cita_ini11;
    
    @FXML
    private JFXButton pac_anadir1;
    
    @FXML
    private Label hora_cita_ini12;
    @FXML
    private Label cita_nom_ini11;
    @FXML
    private JFXButton pac_anadir11;
    @FXML
    private JFXButton exa_agregar1;
    @FXML
    private JFXButton exa_eliminar1;
    @FXML
    private JFXButton exa_modificar1;
    @FXML
    private JFXButton exa_actualizar1;

    public static Pacientes getPaciente() {
        return paciente;
    }
       
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Inicio();
        
        switch (Usuarios.getTipo()){
            case "Administrador":
                Administrador();
                break;
            
            case "Recepcionista":
                Recepcionista();
                break;
                
            case "Laboratorista":
                Laboratorista();
                break;
        }
    }    
    
    
    //// -- *** Métodos de tipo de usuario 
        
    private void Administrador(){
    
    }
    
    private void Laboratorista(){
        
        /// PERMISOS
        
        tabPane.getTabs().remove(usuarios);
        tabPane.getTabs().remove(inicio_rec);
        
        /// INICIO
        
        ObservableList <String> status = FXCollections.observableArrayList("REALIZADO","NO REALIZADO");
        combo_cita_ini.setItems(status);
        iniTablaCitas();
        accionBotonesIni();
        
        /// PACIENTES
        pacienteTabla();
        accionBotonesPac();
        
        // CITAS
        citaTabla();
        accionBotonesCit();
        
        //RESULTADOS
        resultados();
    
    }
    
    private void Recepcionista(){
        
        /// PERMISOS
        
        tabPane.getTabs().remove(inicio_lab);
        tabPane.getTabs().remove(estudios);
        tabPane.getTabs().remove(examenes);
        tabPane.getTabs().remove(usuarios);
        
        /// PACIENTES
        pacienteTabla();
        accionBotonesPac();
        
        // CITAS
        citaTabla();
        accionBotonesCit();
    }
    
    
    
   //// -- *** Métodos de Inicio Laboratorista
  
    private void Inicio(){
        Thread thread= new Thread(new Reloj(reloj));
        thread.setDaemon(true);
        thread.start();
        fecha(fecha);
        usuario();
        
        tipo.setItems(cita_status);
        
        logout.setOnAction(e ->{
            anchor.getScene().getWindow().hide();
            AbrirVentana2 av= new AbrirVentana2("/LogIn/LogIn.fxml","LogIn");
            new Thread(av).start();
        });
        
    }
    
    private void usuario(){
        Usuario usuario = new Usuario(Usuarios.getTipo(),Usuarios.getId());
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
            Extraccion ex= new Extraccion();
            ex.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, (WorkerStateEvent  event)->{
            tabla_pac.setItems(ex.getValue());
            id_pac.setCellValueFactory(new PropertyValueFactory<>("id"));
            nom_pac.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            ape_pat.setCellValueFactory(new PropertyValueFactory<>("apePat"));
            ape_mat.setCellValueFactory(new PropertyValueFactory<>("apeMat"));
            status.setCellValueFactory(new PropertyValueFactory<>("status"));
        });
            ExtraccionStatus ES= new ExtraccionStatus(id_Cita,Status(combo_cita_ini.getValue()),new Thread(ex));
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
     
    private String Status(String status){
        if(status.equals("REALIZADO")){
            return "R";
        }
        return "N";
    }
    
    
   //// -- *** Métodos de Inicio Recepcionista
    
   
    
   //// -- *** Métodos de Paciente
    
    private void pacienteTabla() {
        Paciente_Tabla pt = new Paciente_Tabla();
        pt.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, (WorkerStateEvent event) -> {
            pac_tabla.setItems(pt.getValue());
            pac_nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            pac_ap.setCellValueFactory(new PropertyValueFactory<>("apePat"));
            pac_am.setCellValueFactory(new PropertyValueFactory<>("apeMat"));
            pac_correo.setCellValueFactory(new PropertyValueFactory<>("correo"));
            pac_fn.setCellValueFactory(new PropertyValueFactory<>("fecha"));
            pac_tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
            pac_cel.setCellValueFactory(new PropertyValueFactory<>("cel"));

            FilteredList<Pacientes> datos = new FilteredList<>(pt.getValue(), a -> true);

            pac_buscar.setOnKeyReleased(e -> {
                pac_buscar.textProperty().addListener((observableValue, oldValue, newValue) -> {
                    datos.setPredicate((Predicate<? super Pacientes>) paciente -> {

                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        String lower = newValue.toLowerCase();

                        if (paciente.getApePat().toLowerCase().contains(lower)) {
                            return true;
                        } else if (paciente.getCorreo().toLowerCase().contains(lower)) {
                            return true;
                        }
                        return false;
                    });
                });
            });
            SortedList<Pacientes> datosCambio = new SortedList<>(datos);
            datosCambio.comparatorProperty().bind(pac_tabla.comparatorProperty());
            pac_tabla.setItems(datosCambio);
        });
        new Thread (pt).start();
    }
    
    private void accionBotonesPac() {
        pac_anadir.setOnAction(e -> {
            new Thread(new AbrirVentana("/Pacientes/Switch.fxml", "Añadir Paciente")).start();
        });
        
        pac_actualizar.setOnAction(e-> { 
            pacienteTabla();
        });
        
        pac_eliminar.setOnAction(e -> {
            Paciente_Tabla pt = new Paciente_Tabla();
            pt.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, (WorkerStateEvent event) -> {
            pac_tabla.setItems(pt.getValue());
            pac_nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            pac_ap.setCellValueFactory(new PropertyValueFactory<>("apePat"));
            pac_am.setCellValueFactory(new PropertyValueFactory<>("apeMat"));
            pac_correo.setCellValueFactory(new PropertyValueFactory<>("correo"));
            pac_fn.setCellValueFactory(new PropertyValueFactory<>("fecha"));
            pac_tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
            pac_cel.setCellValueFactory(new PropertyValueFactory<>("cel"));

            FilteredList<Pacientes> datos = new FilteredList<>(pt.getValue(), a -> true);

            pac_buscar.setOnKeyReleased(d -> {
                pac_buscar.textProperty().addListener((observableValue, oldValue, newValue) -> {
                    datos.setPredicate((Predicate<? super Pacientes>) paciente -> {

                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }
                        String lower = newValue.toLowerCase();

                        if (paciente.getApePat().toLowerCase().contains(lower)) {
                            return true;
                        } else if (paciente.getCorreo().toLowerCase().contains(lower)) {
                            return true;
                        }
                        return false;
                    });
                });
            });
            SortedList<Pacientes> datosCambio = new SortedList<>(datos);
            datosCambio.comparatorProperty().bind(pac_tabla.comparatorProperty());
            pac_tabla.setItems(datosCambio);
        });
            if(!pac_tabla.getSelectionModel().isEmpty()){
            String correo= pac_tabla.getSelectionModel().getSelectedItem().getCorreo();
            new Thread (new EliminarPaciente(correo,new Thread(pt))).start();
            } else {
                Alertas.warning("Sin selección.", "Datos no seleccionados.", "Seleccionar datos de la tabla para proceder.");
            }
        });
        
        pac_editar.setOnAction( e -> {
            if(!pac_tabla.getSelectionModel().isEmpty()){
            paciente=pac_tabla.getSelectionModel().getSelectedItem();
            new Thread(new AbrirVentana("/Pacientes/ModificarPaciente.fxml", "Modificar Paciente")).start();
            } else {
                Alertas.warning("Sin selección.", "Datos no seleccionados.", "Seleccionar datos de la tabla para proceder.");
            }
        });
    }
    
    
    
   //// -- *** Métodos de Citas
    
    private void citaTabla(){
        Cita_Tabla pt = new Cita_Tabla ();
        pt.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, (WorkerStateEvent event) -> {
            cit_tabla.setItems(pt.getValue());
            
            cit_id.setCellValueFactory(new PropertyValueFactory<>("id"));
            cit_Nom.setCellValueFactory(new PropertyValueFactory<>("nombre"));
            cit_ApePat.setCellValueFactory(new PropertyValueFactory<>("apePat"));
            cit_ApeMat.setCellValueFactory(new PropertyValueFactory<>("apeMat"));
            cit_fecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
            cit_hora.setCellValueFactory(new PropertyValueFactory<>("hora"));
            cit_pago.setCellValueFactory(new PropertyValueFactory<>("pago"));
            cit_extraccion.setCellValueFactory(new PropertyValueFactory<>("extraccion"));
          
            FilteredList<Cita> datos = new FilteredList<>(pt.getValue(), a -> true);
            tipo.setOnAction(e -> { 
                datos.setPredicate((Predicate<? super Cita>) citas -> {
                
                    if(tipo.getSelectionModel().isEmpty()){
                        return true;
                    }
                    
                    if(tipo.getValue().equals("MOSTRAR TODO")){
                        return true;
                    }
                    
                    if(tipo.getValue().equals(citas.getExtraccion())){
                        return true;
                    }
                    
                    if(tipo.getValue().equals(citas.getPago())){
                        return true;
                    }
                    
                    return false;
                });
            });
            SortedList<Cita> datosCambio = new SortedList<>(datos);
            datosCambio.comparatorProperty().bind(cit_tabla.comparatorProperty());
            cit_tabla.setItems(datosCambio);
        });
        new Thread (pt).start();
    }
    
    private void accionBotonesCit(){
        
        cit_actualizar.setOnAction(e -> {
            citaTabla();
        });
        
        cit_agregar.setOnAction(e -> {
            new Thread(new AbrirVentana("/Cita/AgregarCita.fxml", "Crear nueva cita")).start();
        });
        
        cit_eliminar.setOnAction(e -> {

            tipo.setValue(null);
            Cita_Tabla pt = new Cita_Tabla();
            pt.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, (WorkerStateEvent event) -> {
                cit_tabla.setItems(pt.getValue());

                cit_id.setCellValueFactory(new PropertyValueFactory<>("id"));
                cit_Nom.setCellValueFactory(new PropertyValueFactory<>("nombre"));
                cit_ApePat.setCellValueFactory(new PropertyValueFactory<>("apePat"));
                cit_ApeMat.setCellValueFactory(new PropertyValueFactory<>("apeMat"));
                cit_fecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
                cit_hora.setCellValueFactory(new PropertyValueFactory<>("hora"));
                cit_pago.setCellValueFactory(new PropertyValueFactory<>("pago"));
                cit_extraccion.setCellValueFactory(new PropertyValueFactory<>("extraccion"));

                FilteredList<Cita> datos = new FilteredList<>(pt.getValue(), a -> true);
                tipo.setOnAction(t -> {
                    datos.setPredicate((Predicate<? super Cita>) citas -> {

                        if (tipo.getSelectionModel().isEmpty()) {
                            return true;
                        }

                        if (tipo.getValue().equals("MOSTRAR TODO")) {
                            return true;
                        }

                        if (tipo.getValue().equals(citas.getExtraccion())) {
                            return true;
                        }

                        if (tipo.getValue().equals(citas.getPago())) {
                            return true;
                        }

                        return false;
                    });
                });
                SortedList<Cita> datosCambio = new SortedList<>(datos);
                datosCambio.comparatorProperty().bind(cit_tabla.comparatorProperty());
                cit_tabla.setItems(datosCambio);
            });

            if (!cit_tabla.getSelectionModel().isEmpty()) {
                Integer id = cit_tabla.getSelectionModel().getSelectedItem().getId();
                new Thread(new EliminarCita(id, new Thread(pt))).start();
            } else {
                Alertas.warning("Sin selección.", "Datos no seleccionados.", "Seleccionar datos de la tabla para proceder.");
            }
        });

    }
    
    
    /// -- *** Resultados
    private void resultados(){
        btn_resultados.setOnAction(e -> {
            Citas = cit_tabla.getSelectionModel().getSelectedItem();
            new Thread(new AbrirVentana("/Resultados/Resultados.fxml", "Resultados")).start();
        });
       
    }

}
