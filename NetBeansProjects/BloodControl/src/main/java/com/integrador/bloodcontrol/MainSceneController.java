package com.integrador.bloodcontrol;

import com.integrador.POJO.Paciente;
import com.integrador.POJO.Usuarios;
import com.integrador.POJOLista.Pacientes;
import com.integrador.bloodcontrol.Funciones.LoadingFrame;
import com.integrador.bloodcontrol.Funciones.Reloj;
import com.integrador.persistence.EManagerFactory;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
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

    @FXML
    private Label reloj;
    @FXML
    private Label id;
    @FXML
    private Label correo;
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

        ObservableList<Pacientes> tabla = FXCollections.observableArrayList();
        
        @Override  
        protected ObservableList<Pacientes> call() throws Exception {
            
            EntityManager em = EManagerFactory.getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT NEW com.integrador.POJOLista.Pacientes(A.pacId,A.pacNombre,A.pacAp,A.pacAm,B.status) "
                    + "FROM Paciente A, Cita B WHERE A.pacId=B.pacId");
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
    }
    
}
