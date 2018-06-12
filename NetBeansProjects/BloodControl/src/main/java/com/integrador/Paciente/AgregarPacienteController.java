/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.integrador.Paciente;

import com.integrador.POJO.EstadoRegistro;
import com.integrador.POJO.Paciente;
import com.integrador.POJO.Persona;
import com.integrador.bloodcontrol.Alertas;
import com.integrador.bloodcontrol.Funciones.CorreoTexto;
import com.integrador.bloodcontrol.Funciones.Funciones;
import com.integrador.persistence.EManagerFactory;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.AnchorPane;
import javax.persistence.EntityManager;

/**
 * FXML Controller class
 *
 * @author abdias
 */
public class AgregarPacienteController extends Funciones implements Initializable {

    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField apePat;
    @FXML
    private JFXTextField apeMat;
    @FXML
    private JFXTextField mail;
    @FXML
    private JFXDatePicker fnac;
    @FXML
    private JFXTextField tel;
    @FXML
    private JFXTextField cel;
    @FXML
    private JFXButton btn_aceptar;
    @FXML
    private JFXButton btn_cancelar;
    @FXML
    private AnchorPane anchor;
    @FXML
    private ProgressIndicator progress;

    List <JFXTextField> textfields = new ArrayList <>();
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        validaciones();
        accionBotones();
        textfields.add(nom);
        textfields.add(apePat);
        textfields.add(apeMat);
        textfields.add(mail);
    }    
    
    
    public void accionBotones(){
    
        btn_aceptar.setOnAction(e -> {
          
            if(!casillasVacias(textfields)){
                Alertas.error("Error", "Casillas vacías", "Existen casillas vacías en el formulario, verifique");  
            } else if (!validarEmailFuerte(mail.getText())) {
                Alertas.error("Error", "Correo Inválido", "Verificar correo electrónico de paciente");  
            } else if (!validarFechas(DatePickerParser(fnac))){
                Alertas.error("Error", "Fecha de nacimiento inválida", "Verificar fecha de nacimiento de paciente");
            } 
            
            else{
                progress.setVisible(true);
                new Thread(task).start();
                anchor.getScene().getWindow().hide();
            }
        });     
        
        btn_cancelar.setOnAction(e -> {
            anchor.getScene().getWindow().hide();
        });     
    
        }
    
    
        public void validaciones (){
            TextFieldNumeros(cel);
            TextFieldNumeros(tel);
            TextFieldLetras(nom);
            TextFieldLetras(apePat);
            TextFieldLetras(apeMat);
            setTextFieldLimit(cel,10);
            setTextFieldLimit(tel,10);
            setTextFieldLimit(nom,20);
            setTextFieldLimit(apePat,20);
            setTextFieldLimit(apeMat,20);
        }
    
        
        Task<Void> task = new Task<Void>() {
        @Override
        protected Void call() throws Exception {
            
            EntityManager em = EManagerFactory.getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();
            
            Persona persona = new Persona();
            persona.setPerNombre(nom.getText());
            persona.setPerAp(apePat.getText());
            persona.setPerAm(apeMat.getText());
            persona.setPerFn(DatePickerParser(fnac));
            
            EstadoRegistro estado = em.find(EstadoRegistro.class, "A");
            
            Paciente paciente = new Paciente();
            
            paciente.setPerId(persona);
            paciente.setErId(estado);
            paciente.setPacContra("1a2b3c4d");
            
            if(!tel.getText().isEmpty()){
                paciente.setPacTel(tel.getText());
            }
            
            if(!cel.getText().isEmpty()){
                paciente.setPacCel(cel.getText());
            }
            
            paciente.setPacCe(mail.getText());
            
            em.persist(persona); 
            em.persist(paciente);
            em.getTransaction().commit();
            em.close();
            
            CorreoTexto correo = new CorreoTexto();
            correo.setBienvenida("Bienvenido a BloodControl\nLa salud es siempre lo más importante.");
            correo.setNombre("Buen día, "+persona.getPerNombre()+" "+persona.getPerAp()+" "+persona.getPerAm());
            correo.setMensaje(" \n\n Se comparte con usted la información relevante sobre su cuenta.\n"
                    + "CORREO ELECTRÓNICO: "+ paciente.getPacCe() +"\n CONTRASEÑA TEMPORAL: " + paciente.getPacContra()+ 
                    "\n\n             Muchas gracias."
                    + "\n\n\nEste es un correo de verificación de cuenta; en caso de desconocer la procedencia, hacer caso omiso.");
            
            correo.setCorreo(paciente.getPacCe());
            new Thread (correo).start();
            
            progress.setVisible(false);
            
            
            return null;
        }

    };
    
}
