/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.integrador.Paciente;

import com.integrador.POJOLista.CitaExamen;
import com.integrador.POJOLista.SigleCita;
import com.integrador.bloodcontrol.Alertas;
import com.integrador.bloodcontrol.Funciones.CorreoTexto;
import com.integrador.bloodcontrol.Funciones.Funciones;
import com.integrador.bloodcontrol.Funciones.Usuarios;
import com.integrador.bloodcontrol.POJO.Cita_Examen;
import com.integrador.bloodcontrol.POJO.Citas;
import com.integrador.bloodcontrol.POJO.EstadoRegistro;
import com.integrador.bloodcontrol.POJO.Examen;
import com.integrador.bloodcontrol.POJO.Paciente;
import com.integrador.bloodcontrol.POJO.Pagos;
import com.integrador.bloodcontrol.POJO.Recepcionista;
import com.integrador.bloodcontrol.POJO.StatusExa;
import com.integrador.bloodcontrol.POJO.StatusPag;
import com.integrador.bloodcontrol.persistence.EManagerFactory;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class PagoController extends Funciones implements Initializable {

    @FXML
    private JFXButton btn_aceptar;
    @FXML
    private JFXButton btn_cancelar;
    @FXML
    private Label resta;
    @FXML
    private JFXTextField pago;
    @FXML
    private Label importe;
    @FXML
    private JFXTextField correo;
    
    Double suma=0.0;
    Double res=0.0;
    @FXML
    private AnchorPane anchor;
    
    SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
    SimpleDateFormat ttt = new SimpleDateFormat("yyyy-MM-dd");
    @FXML
    private JFXTextArea preliminar;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        acciones();
    }    
    
    
    private void acciones(){
        pago.setOnKeyReleased(e -> {
            
            if(pago.getText().equals("")){
                res= suma;
                resta.setText("$ 0.00");
            } else {
            
            res = 0.0;    
            res= suma - Double.valueOf(pago.getText());
            resta.setText("$ " + res+"0");
            }});
        
        btn_cancelar.setOnAction(e -> {
            anchor.getScene().getWindow().hide();
        });
        
        btn_aceptar.setOnAction(e -> {
            
            if(res > 0){
                Alertas.error("Error", "Pago cubierto parcialmente.", "Se debe cubrir la totalidad del monto \npara proceder.");
            } else {
                añadir.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, (WorkerStateEvent event) -> {
                    anchor.getScene().getWindow().hide();
                });
               new Thread (añadir).start();
            } 
           
        });
    }
    
    Task<Void> añadir = new Task<Void>() {
        @Override
        protected Void call() throws Exception {

            EntityManager em = EManagerFactory.getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();
            Date date = new Date();
            
            Pagos pagos = new Pagos();
            pagos.setCitId(SigleCita.getCita());
            pagos.setPagMonto(SigleCita.getTotal());
            pagos.setPagTiempo(date);
            
            Recepcionista rec = em.find(Recepcionista.class, Usuarios.getId());
            pagos.setRecId(rec);
            
            em.persist(pagos);
            em.getTransaction().commit();
            em.close();
            
           
            return null;
        }

    };

}
