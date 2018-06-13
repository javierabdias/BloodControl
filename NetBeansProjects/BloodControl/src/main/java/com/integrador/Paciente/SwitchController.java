/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.integrador.Paciente;

import com.integrador.bloodcontrol.POJO.EstadoRegistro;
import com.integrador.bloodcontrol.POJO.Paciente;
import com.integrador.bloodcontrol.POJO.Persona;
import com.integrador.bloodcontrol.AbrirVentana;
import com.integrador.bloodcontrol.Funciones.CorreoTexto;
import com.integrador.bloodcontrol.Funciones.Funciones;
import com.integrador.bloodcontrol.persistence.EManagerFactory;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * FXML Controller class
 *
 * @author abdias
 */
public class SwitchController extends Funciones implements Initializable {

    @FXML
    private JFXButton verificar;
    @FXML
    private JFXTextField ce;
    @FXML
    private JFXButton button;
    @FXML
    private Label status;
    
    Boolean aux=false;
    @FXML
    private AnchorPane anchor;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        button.setDisable(true);
        
        cambio.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, (WorkerStateEvent event) -> {
           
            if(cambio.getValue()){
            
                button.setText("Reactivar");
                button.setDisable(false);
                status.setText("Paciente encontrado,\nreactivar cuenta.");
                button.setOnAction(e -> {
                    new Thread(reactivar).start();
                    anchor.getScene().getWindow().hide();
                });
            
            } else {
            
                button.setText("Añadir");
                button.setDisable(false);
                status.setText("Paciente no encontrado,\ningresarlo al sistema.");
                button.setOnAction(e -> {
                   anchor.getScene().getWindow().hide(); 
                   new Thread (new AbrirVentana("/Pacientes/AgregarPaciente.fxml", "Añadir Paciente")).start();
                });
            }
        });
        
        verificar.setOnAction(e -> {
            new Thread(cambio).start();
            verificar.setDisable(true);
        });
      
        
        
    }    
    
    Task<Boolean> cambio = new Task<Boolean>() {
        @Override
        protected Boolean call() throws Exception {

            EntityManager em = EManagerFactory.getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT p FROM Paciente p WHERE p.pacCe = :pacCe");
            query.setParameter("pacCe", ce.getText());
            List <Paciente> lista= query.getResultList();
            em.getTransaction().commit();
            em.close();
            
            
            if(lista.size()!=0){
                return true;
            } else {
                return false;
            }
        }
    };
    
    Task <Void> reactivar = new Task <Void> () {
                @Override
                protected Void call() throws Exception {
                    
                    EntityManager em = EManagerFactory.getEntityManagerFactory().createEntityManager();
                    em.getTransaction().begin();
                    Query query = em.createQuery("SELECT p FROM Paciente p WHERE p.pacCe = :pacCe");
                    query.setParameter("pacCe", ce.getText());
                    Paciente pac = (Paciente) query.getSingleResult();
                    EstadoRegistro er = em.find(EstadoRegistro.class, "A");
                    Persona persona = em.find(Persona.class, pac.getPerId().getPerId());
                    pac.setErId(er);
                    em.persist(pac);
                    em.getTransaction().commit();
                    em.close();

                    CorreoTexto correo = new CorreoTexto();
                    correo.setBienvenida("Bienvenido a BloodControl\nLa salud es siempre lo más importante.");
                    correo.setNombre("Buen día, " + persona.getPerNombre() + " " + persona.getPerAp() + " " + persona.getPerAm());
                    correo.setMensaje(" \n\n Se comparte con usted la información relevante sobre su cuenta.\n"
                            + "CORREO ELECTRÓNICO: " + pac.getPacCe() + "\n CONTRASEÑA TEMPORAL: " + pac.getPacContra()
                            + "\n\n             Muchas gracias."
                            + "\n\n\nEste es un correo de verificación de cuenta; en caso de desconocer la procedencia, hacer caso omiso.");

                    correo.setCorreo(pac.getPacCe());
                    new Thread(correo).start();

                    return null;    
                }
    };       
}
