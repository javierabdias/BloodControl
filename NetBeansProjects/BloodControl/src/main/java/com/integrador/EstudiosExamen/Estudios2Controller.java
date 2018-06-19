/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.integrador.EstudiosExamen;

import com.integrador.bloodcontrol.Alertas;
import com.integrador.bloodcontrol.POJO.EstadoRegistro;
import com.integrador.bloodcontrol.POJO.Estudios;
import com.integrador.bloodcontrol.POJO.Examen;
import com.integrador.bloodcontrol.persistence.EManagerFactory;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * FXML Controller class
 *
 * @author abdias
 */
public class Estudios2Controller implements Initializable {

    @FXML
    private JFXTextField nom_estudio;
    @FXML
    private JFXTextField min;
    @FXML
    private JFXTextField max;
    @FXML
    private JFXButton btn_aceptar;
    @FXML
    private JFXButton btn_cancelar;
    @FXML
    private JFXComboBox<String> magnitud;
    @FXML
    private JFXComboBox<String> Examen;

    /**
     * Initializes the controller class.
     */
    ObservableList <String> elementos = FXCollections.observableArrayList("Unidades","%","U/L","mg/dL","g/dl","Minutos");
    @FXML
    private AnchorPane anchor;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        exame.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, (WorkerStateEvent event) -> {
            Examen.setItems(exame.getValue());
            magnitud.setItems(elementos);
            acciones();
        });
        new Thread(exame).start();
    }    
    
    private void acciones(){
        btn_aceptar.setOnAction(e -> {
        
            if(nom_estudio.getText().equals("")||min.getText().equals("")||max.getText().equals("")){
                Alertas.error("Error", "Casillas vacías", "Existen casillas vacías en el formulario, verifique");
            } else if (magnitud.getSelectionModel().isEmpty()) {
                Alertas.error("Error", "Magnitud necesaria", "Seleccione una magnitud para continuar.");
            } else if (Examen.getSelectionModel().isEmpty()) {
                Alertas.error("Error", "Examen no seleccionado", "Seleccione un examen para continuar.");
            }else {
                guardar.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, (WorkerStateEvent event) -> {
                    anchor.getScene().getWindow().hide();
                });
                new Thread(guardar).start();
            }
        });
        
        btn_cancelar.setOnAction(e -> {
            anchor.getScene().getWindow().hide();
        });
        
    }
    
    Task <Void> guardar = new Task <Void> (){
        
        @Override
        
        protected Void call() throws Exception {
            
            EntityManager em  = EManagerFactory.getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();
            
            Query query = em.createQuery("SELECT e FROM Examen e WHERE e.exaNom=:nombre");
            query.setParameter("nombre", Examen.getValue());
            
            Examen examen = (Examen) query.getSingleResult();
            EstadoRegistro er = em.find(EstadoRegistro.class, "A");
            
            Estudios estudio = new Estudios ();
            estudio.setEstMax(Double.valueOf(max.getText()));
            estudio.setEstNombre(nom_estudio.getText());
            estudio.setEstMin(Double.valueOf(min.getText()));
            estudio.setEstMagnit(magnitud.getValue());
            estudio.setExaId(examen);
            estudio.setErId(er);
            
            em.persist(estudio);
            
            em.getTransaction().commit();
            em.close();
            return null;
        }
    
    };
    
    Task<ObservableList<String>> exame = new Task<ObservableList<String>>() {
        @Override
        protected ObservableList<String> call() throws Exception {
            ObservableList<String> list = FXCollections.observableArrayList();

            EntityManager em = EManagerFactory.getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT p.exaNom FROM Examen p WHERE p.erId='A'");
            list = FXCollections.observableArrayList(query.getResultList());
            em.getTransaction().commit();
            em.close();

            return list;
        }

    };
}
