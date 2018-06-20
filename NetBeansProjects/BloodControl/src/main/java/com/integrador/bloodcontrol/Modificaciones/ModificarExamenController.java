/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.integrador.bloodcontrol.Modificaciones;

import com.integrador.bloodcontrol.Alertas;
import com.integrador.bloodcontrol.Funciones.Funciones;
import com.integrador.bloodcontrol.MainSceneController;
import com.integrador.bloodcontrol.POJO.Examen;
import com.integrador.bloodcontrol.persistence.EManagerFactory;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javax.persistence.EntityManager;

/**
 * FXML Controller class
 *
 * @author abdias
 */
public class ModificarExamenController extends Funciones implements Initializable {

    @FXML
    private JFXTextField nom_examen;
    @FXML
    private JFXTextField precio;
    @FXML
    private JFXButton btn_aceptar;
    @FXML
    private JFXButton btn_cancelar;
    @FXML
    private AnchorPane anchor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        validar();
        campos();
        accionBotones();
    }    
    
    private void accionBotones() {

        btn_aceptar.setOnAction(e -> {
            if (nom_examen.getText().equals("") || precio.getText().equals("")) {
                Alertas.error("Error", "Casillas vacías", "Existen casillas vacías en el formulario, verifique");
            } else {
                guardar.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, (WorkerStateEvent event) -> {
                    anchor.getScene().getWindow().hide();
                });
                new Thread (guardar).start();
            }
        });
        
        btn_cancelar.setOnAction(e -> {
            anchor.getScene().getWindow().hide();
        });
    }
    
    private void campos(){
        buscar.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, (WorkerStateEvent event) -> {
            nom_examen.setText(buscar.getValue().getExaNom());
            precio.setText(String.valueOf(buscar.getValue().getExaPrecio()));
        });
        new Thread(buscar).start();
    }
    
    Task <Examen> buscar = new Task <Examen> (){
        @Override
        protected Examen call() throws Exception {
            EntityManager em = EManagerFactory.getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();
            Examen exa = em.find(Examen.class, MainSceneController.getEstudio());
            em.getTransaction().commit();
            em.close();
            return exa;
        }
    };
    
    Task<Void> guardar = new Task <Void> (){
        @Override
        protected Void call() throws Exception {
            EntityManager em = EManagerFactory.getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();
            Examen exa = em.find(Examen.class, MainSceneController.getEstudio());
            exa.setExaNom(nom_examen.getText());
            exa.setExaPrecio(Double.valueOf(precio.getText()));
            em.getTransaction().commit();
            em.close();
            
            return null;
        }
    
    };
    
     private void validar(){
        TextFieldLetras(nom_examen);
        TextFieldDouble(precio);
        setTextFieldLimit(nom_examen, 20);
        setTextFieldLimit(precio, 5);
    }
    
}
