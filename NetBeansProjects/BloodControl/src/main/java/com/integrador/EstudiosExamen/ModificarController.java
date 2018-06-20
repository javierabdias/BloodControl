/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.integrador.EstudiosExamen;

import com.integrador.POJOLista.Estudio;
import com.integrador.bloodcontrol.Alertas;
import com.integrador.bloodcontrol.Funciones.Funciones;
import com.integrador.bloodcontrol.MainSceneController;
import com.integrador.bloodcontrol.POJO.Estudios;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javax.persistence.EntityManager;

/**
 * FXML Controller class
 *
 * @author abdias
 */
public class ModificarController extends Funciones implements Initializable {

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

    /**
     * Initializes the controller class.
     */
    
    ObservableList <String> elementos = FXCollections.observableArrayList("Unidades","%","U/L","mg/dL","g/dl","Minutos");
    @FXML
    private AnchorPane anchor;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        magnitud.setItems(elementos);
        validacion();
        llenarCasillas();
        accionBotones();
    }    
    
    public void llenarCasillas(){
        est.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, (WorkerStateEvent event) -> {
            nom_estudio.setText(est.getValue().getEstNombre());
            min.setText(String.valueOf(est.getValue().getEstMin()));
            max.setText(String.valueOf(est.getValue().getEstMax()));
            magnitud.setValue(est.getValue().getEstMagnit());
        });
        new Thread (est).start();
    }
    
    public void accionBotones(){
        btn_aceptar.setOnAction(e -> {
            
            if(validar()){
                Alertas.error("Error", "Casillas vacías", "Existen casillas vacías en el formulario, verifique");
            } else if(magnitud.getSelectionModel().isEmpty()){
                Alertas.error("Error", "Magnitud necesaria", "Seleccione una magnitud para continuar.");
            }else {
            guardar.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, (WorkerStateEvent event) -> {
                anchor.getScene().getWindow().hide();
            });
            new Thread (guardar).start();}
            
        });
        
        btn_cancelar.setOnAction(e -> {
            anchor.getScene().getWindow().hide();
        });
        
    }
    
    Task <Estudios> est = new Task<Estudios>(){
        @Override
        protected Estudios call() throws Exception {
            EntityManager em = EManagerFactory.getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();
            Estudios estu = em.find(Estudios.class, MainSceneController.getEstudio());
            em.getTransaction().commit();
            em.close();
            return estu;
        }
    
    };
    
    Task <Void> guardar = new Task <Void> (){
        @Override
        protected Void call() throws Exception {
          EntityManager em = EManagerFactory.getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();
            Estudios estu = em.find(Estudios.class, MainSceneController.getEstudio());
            estu.setEstNombre(nom_estudio.getText());
            estu.setEstMin(Double.valueOf(min.getText()));
            estu.setEstMax(Double.valueOf(max.getText()));
            estu.setEstMagnit(magnitud.getValue());
            em.persist(estu);
            em.getTransaction().commit();
            em.close();
           return null;
        }
    };
    
    private void validacion(){
        TextFieldDouble(max);
        TextFieldDouble(min);
        TextFieldLetras(nom_estudio);
        setTextFieldLimit(max, 5);
        setTextFieldLimit(min, 5);
        setTextFieldLimit(nom_estudio, 20);
    }
    
    public Boolean validar (){
    
        if(nom_estudio.getText().equals("") || min.getText().equals("") || max.getText().equals("")) {
            return true;
        }       
    return false;
    }
    
    
}
