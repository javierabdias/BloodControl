/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.integrador.Paciente;

import com.integrador.POJO.EstadoRegistro;
import com.integrador.POJO.Paciente;
import com.integrador.POJO.Persona;
import com.integrador.bloodcontrol.AbrirVentana;
import com.integrador.bloodcontrol.Funciones.CorreoTexto;
import com.integrador.bloodcontrol.Funciones.Funciones;
import com.integrador.persistence.EManagerFactory;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.concurrent.Task;
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
        
        verificar.setOnAction(e -> {
            new Thread(cambio).start();
        });
      
        
        
    }    
    
    Task<Void> cambio = new Task<Void>() {
        @Override
        protected Void call() throws Exception {

            EntityManager em = EManagerFactory.getEntityManagerFactory().createEntityManager();

            em.getTransaction().begin();
            Query query = em.createQuery("SELECT p FROM Paciente p WHERE p.pacCe = :pacCe");
            query.setParameter("pacCe", ce.getText());

             if (!query.getSingleResult().equals(null)) {
                
                Platform.runLater(() -> {
                    button.setText("Reactivar");
                    button.setDisable(false);
                    status.setText("Paciente encontrado,\nreactivar cuenta.");
                    button.setOnAction(e -> {
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

                        anchor.getScene().getWindow().hide();
                    });

                });


            } else {
                Platform.runLater(() -> {
                    button.setText("Añadir");
                    button.setDisable(false);
                    status.setText("Paciente no encontrado,\ningresarlo al sistema.");
                    button.setOnAction(e -> {
                        new Thread(new AbrirVentana("/Pacientes/AgregarPacientes.fxml", "Añadir Paciente")).start();
                    em.getTransaction().commit();
                    em.close();
                    });
                });
                
                
            }

            return null;
        }
    };

}
