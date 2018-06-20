/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.integrador.EstudiosExamen;

import com.integrador.bloodcontrol.AbrirVentana;
import com.integrador.bloodcontrol.Alertas;
import com.integrador.bloodcontrol.Funciones.Funciones;
import com.integrador.bloodcontrol.POJO.EstadoRegistro;
import com.integrador.bloodcontrol.POJO.Examen;
import com.integrador.bloodcontrol.persistence.EManagerFactory;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javax.persistence.EntityManager;

/**
 * FXML Controller class
 *
 * @author abdias
 */
public class AñadirExamenController extends Funciones implements Initializable {

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
        accionBoton();
    }


    Task <Void> guardar = new Task <Void>(){
        @Override
        protected Void call() throws Exception {
            
            EntityManager em = EManagerFactory.getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();
            EstadoRegistro er = em.find(EstadoRegistro.class, "A");
            Examen e = new Examen();
            e.setExaNom(nom_examen.getText());
            e.setErId(er);
            e.setExaPrecio(Double.valueOf(precio.getText()));
            em.persist(e);
            em.getTransaction().commit();
            em.close();
            
            return null;
        }
    
    };
    
    private void accionBoton(){
        btn_aceptar.setOnAction(e -> {
            if(nom_examen.getText().equals("")||precio.getText().equals("")){
                Alertas.error("Error", "Casillas vacías", "Existen casillas vacías en el formulario, verifique");
            } else{
            guardar.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, (WorkerStateEvent event) -> {
                confirmacion();
            });
            new Thread (guardar).start();}
        });
        
        btn_cancelar.setOnAction(e -> {
            anchor.getScene().getWindow().hide();
        });
        
    }
    
    private  void confirmacion(){
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Añadir estudios");
            alert.setHeaderText("Añadir estudios a examen.");
            alert.setContentText("¿Desea añadir estudios al examen recien creado?");

           Optional<ButtonType> result = alert.showAndWait();
           if (result.get() == ButtonType.OK){
               EstuSingle.setNombre(nom_examen.getText());
               new Thread (new AbrirVentana("/Estudios/Estudios.fxml","Añadir estudios")).start();
               anchor.getScene().getWindow().hide();
           } else {
               anchor.getScene().getWindow().hide();
            }
        }
    
    private void validar(){
        TextFieldLetras(nom_examen);
        TextFieldDouble(precio);
        setTextFieldLimit(nom_examen, 20);
        setTextFieldLimit(precio, 5);
    }
    
}
