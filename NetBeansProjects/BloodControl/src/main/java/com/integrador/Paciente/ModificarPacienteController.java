/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.integrador.Paciente;

import com.integrador.bloodcontrol.Alertas;
import com.integrador.bloodcontrol.Funciones.CorreoTexto;
import com.integrador.bloodcontrol.Funciones.Funciones;
import com.integrador.bloodcontrol.MainSceneController;
import com.integrador.bloodcontrol.POJO.EstadoRegistro;
import com.integrador.bloodcontrol.POJO.Paciente;
import com.integrador.bloodcontrol.POJO.Persona;
import com.integrador.bloodcontrol.persistence.EManagerFactory;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.AnchorPane;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * FXML Controller class
 *
 * @author abdias
 */
public class ModificarPacienteController extends Funciones implements Initializable {

    @FXML
    private AnchorPane anchor;
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
    private ProgressIndicator progress;
    
    List <JFXTextField> textfields = new ArrayList <>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setData();
        validaciones();
        accionBotones();
        textfields.add(nom);
        textfields.add(apePat);
        textfields.add(apeMat);
        textfields.add(mail);
    }    
    
    private void setData(){
        nom.setText(MainSceneController.getPaciente().getNombre());
        apePat.setText(MainSceneController.getPaciente().getApePat());
        apeMat.setText(MainSceneController.getPaciente().getApeMat());
        mail.setText(MainSceneController.getPaciente().getCorreo());
        mail.setEditable(false);
        
        Date in = (Date) MainSceneController.getPaciente().getFecha();
        LocalDate date = in.toLocalDate();
        fnac.setValue(date);
        
        tel.setText(MainSceneController.getPaciente().getTel());
        cel.setText(MainSceneController.getPaciente().getCel());
    }
    
    public void accionBotones() {
        btn_aceptar.setOnAction(e -> {

            if (!casillasVacias(textfields)) {
                Alertas.error("Error", "Casillas vacías", "Existen casillas vacías en el formulario, verifique");
            } else if (!validarEmailFuerte(mail.getText())) {
                Alertas.error("Error", "Correo Inválido", "Verificar correo electrónico de paciente");
            } else if (!validarFechas(DatePickerParser(fnac))) {
                Alertas.error("Error", "Fecha de nacimiento inválida", "Verificar fecha de nacimiento de paciente");
            } else {
                progress.setVisible(true);
                new Thread(task).start();
                anchor.getScene().getWindow().hide();
            }
        });

        btn_cancelar.setOnAction(e -> {
            anchor.getScene().getWindow().hide();
        });

    }
    
     private void validaciones (){
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
            
            Query query = em.createQuery("SELECT p FROM Paciente p WHERE p.pacCe = :pacCe");
            query.setParameter("pacCe", MainSceneController.getPaciente().getCorreo());
            
            Paciente paciente = (Paciente) query.getSingleResult();
            
            Persona persona = em.find(Persona.class, paciente.getPerId().getPerId());
            
            persona.setPerNombre(nom.getText());
            persona.setPerAp(apePat.getText());
            persona.setPerAm(apeMat.getText());
            persona.setPerFn(DatePickerParser(fnac));
            
            if(!tel.getText().isEmpty()){
                paciente.setPacTel(tel.getText());
            } else {
                paciente.setPacTel("0000000000");
            }
            
            if(!cel.getText().isEmpty()){
                paciente.setPacCel(cel.getText());
            } else {
                paciente.setPacCel("0000000000");
            }

            em.persist(persona); 
            em.persist(paciente);
            em.getTransaction().commit();
            em.close();
            
            return null;
        }

    };
    
    
}
