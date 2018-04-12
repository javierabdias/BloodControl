package com.integrador.bloodcontrol;


import com.integrador.POJO.Examen;
import com.integrador.POJO.Usuarios;
import com.integrador.POJOLista.Pacientes;
import com.integrador.bloodcontrol.Funciones.Reloj;
import com.integrador.persistence.EManagerFactory;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.persistence.EntityManager;
import javax.persistence.Query;

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
    
    // ID's INICIO
    ObservableList<Pacientes> tabla = FXCollections.observableArrayList();
    
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

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Inicio();
    }    
    
    public void Inicio(){
        new Thread (new QueryUsuario()).start();
        Thread thread= new Thread(new Reloj(reloj));
        thread.setDaemon(true);
        thread.start();
        new Thread(new tabla()).start();
        accionBotonesIni();
    }
    
    private class QueryUsuario extends Task<Void>{
        @Override
        protected Void call() throws Exception {
            EntityManager em = EManagerFactory.getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();
            Usuarios u = em.find(Usuarios.class, LogInController.id_usuario);
            em.getTransaction().commit();
            em.close();
            Platform.runLater(()-> {
                id.setText(u.getUsuNombre()+" "+u.getUsuAp()+" "+u.getUsuAm());
                correo.setText(u.getUsuCe());
                System.gc();
            });
            return null;
        }
    }
    
    private class tabla extends Task<ObservableList<Pacientes>>{
        @Override  
        protected ObservableList<Pacientes> call() throws Exception {
            
            tabla.clear();
            EntityManager em = EManagerFactory.getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();
            Calendar calendar = Calendar.getInstance(); 
            Date date = calendar.getTime();
            Query query = em.createQuery("SELECT NEW com.integrador.POJOLista.Pacientes(B.citId,A.pacNombre,A.pacAp,A.pacAm,B.status) "
                    + "FROM Paciente A, Cita B WHERE A.pacId=B.pacId AND B.citFecha=:fecha");
            query.setParameter("fecha", date);
            tabla = FXCollections.observableArrayList(query.getResultList());
            em.getTransaction().commit();
            em.close();
            
            Platform.runLater(()->{
                tabla_pac.setItems(tabla);
                id_pac.setCellValueFactory(new PropertyValueFactory<>("id"));
                nom_pac.setCellValueFactory(new PropertyValueFactory<>("nombre"));
                ape_pat.setCellValueFactory(new PropertyValueFactory<>("apePat"));
                ape_mat.setCellValueFactory(new PropertyValueFactory<>("apeMat"));
                status.setCellValueFactory(new PropertyValueFactory<>("status"));
            });
            return tabla;   
        }
    }
    
    private void accionBotonesIni(){
        ini_actualizar.setOnAction(e->{
            new Thread(new tabla()).start();
        });
        
        tabla_pac.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) ->{
            if (!tabla_pac.getSelectionModel().isEmpty()){ 
            id_Cita=tabla_pac.getSelectionModel().getSelectedItem().getId();
            new Thread (new citaInformacion ()).start();}
        });
    }
    
    private class citaInformacion extends Task<Void>{
        
        ObservableList<Pacientes> tabla = FXCollections.observableArrayList();
        ObservableList<Examen> tabla2 = FXCollections.observableArrayList();
        @Override
        protected Void call() throws Exception {
            
            EntityManager em = EManagerFactory.getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT NEW com.integrador.POJOLista.Pacientes(B.citHora,A.pacNombre,A.pacAp,A.pacAm,B.status) "
                    + "FROM Paciente A, Cita B WHERE A.pacId=B.pacId AND B.citId=:id");
            query.setParameter("id",id_Cita);
            Query query2 = em.createQuery("SELECT e FROM Examen e WHERE e.exaId = :exaId");
            query2.setParameter("exaId",id_Cita);
            tabla = FXCollections.observableArrayList(query.getResultList());
            tabla2 = FXCollections.observableArrayList(query2.getResultList());
            em.getTransaction().commit();
            em.close();
            
            Platform.runLater(()->{
                hora_cita_ini.setText(tabla.get(0).getHora().toString());
                cita_nom_ini.setText(tabla.get(0).getNombre()+" "+tabla.get(0).getApePat()+" "+tabla.get(0).getApeMat());
                
                for (Examen tabla21 : tabla2) {
                    System.out.println(tabla2.size());
                    area_cita_ini.setText(tabla21.getExaNom()+"\n");
                }
            });
                      
            return null;
        }
    
    
    }
        
}
