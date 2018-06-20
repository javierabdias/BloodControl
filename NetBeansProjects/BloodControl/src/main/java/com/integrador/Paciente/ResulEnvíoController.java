/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.integrador.Paciente;

import com.integrador.POJOLista.Cita;
import com.integrador.POJOLista.Lab;
import com.integrador.POJOLista.ResultadoFull;
import com.integrador.bloodcontrol.Funciones.CorreoAdjunto;
import com.integrador.bloodcontrol.Funciones.Path;
import com.integrador.bloodcontrol.Funciones.Usuarios;
import com.integrador.bloodcontrol.MainSceneController;
import com.integrador.bloodcontrol.Resultado;
import com.integrador.bloodcontrol.persistence.EManagerFactory;
import com.jfoenix.controls.JFXButton;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * FXML Controller class
 *
 * @author abdias
 */
public class ResulEnvíoController implements Initializable {

    @FXML
    private JFXButton enviar;

    /**
     * Initializes the controller class.
     */
    SimpleDateFormat sd = new SimpleDateFormat("dd-MM-yyyy");
    Cita citas = MainSceneController.getCitas();
    Lab labora ;
    String ce;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        new Thread(labo).start();
        new Thread(correo).start();
        
        res.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, (WorkerStateEvent event) -> {
            Resultado resultado = new Resultado();
            resultado.setResultado(res.getValue());
            resultado.setPersona(citas.getNombre()+" "+citas.getApePat()+" "+citas.getApeMat());
            resultado.setCita(citas.getId());
            resultado.setFe(sd.format(citas.getFecha()));
            resultado.setLaboratorista(labora.getNombre()+" "+labora.getEpePat()+ " "+labora.getApeMat());
            resultado.setCedula(labora.getCedula());
            resultado.addEventHandler(WorkerStateEvent.WORKER_STATE_SUCCEEDED, (WorkerStateEvent even) -> {
            accionBotones();
            
            });
            
            new Thread (resultado).start();
        });
        new Thread (res).start();
    }    
    
    
    Task <Lab> labo = new Task <Lab> (){
        @Override
        protected Lab call() throws Exception {
           EntityManager em = EManagerFactory.getEntityManagerFactory().createEntityManager();
           em.getTransaction().begin();
           Query query = em.createQuery("SELECT NEW com.integrador.POJOLista.Lab (a.labCed, b.perNombre,b.perAp,b.perAm) FROM Laboratorista a, Persona b WHERE a.perId=b.perId AND a.labId=:id");
           query.setParameter("id", Usuarios.getId());
           labora = (Lab) query.getSingleResult();
           em.getTransaction().commit();
           em.close();
           return labora;
        }
    };
    
    
    Task <ObservableList<ResultadoFull>> res = new Task <ObservableList<ResultadoFull>> () {
        @Override
        protected ObservableList<ResultadoFull> call() throws Exception {
            EntityManager em = EManagerFactory.getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();
            Query query= em.createQuery("SELECT NEW com.integrador.POJOLista.ResultadoFull (a.exaNom, b.estNombre, b.estMin, b.estMax, e.resultado, b.estMagnit) FROM Examen a, Estudios b, Citas c, Cita_Examen d, Resultados e WHERE "
                    + "a.exaId=b.exaId AND d.primaryKey.cita.citId=c.citId AND d.primaryKey.examen.exaId=a.exaId AND e.primaryKey.estId.estId=b.estId AND e.primaryKey.citId.citId=c.citId AND c.citId=:cita");
            query.setParameter("cita", citas.getId());
            ObservableList<ResultadoFull> full = FXCollections.observableArrayList(query.getResultList());
            em.getTransaction().commit();
            em.close();
            return full;
        }
    };
    
    Task <Void> correo = new Task <Void> (){
        @Override
        protected Void call() throws Exception {
           
            EntityManager em = EManagerFactory.getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT a.pacCe FROM Paciente a, Citas b WHERE a.pacId=b.pacId AND b.citId=:cita");
            query.setParameter("cita", citas.getId());
            ce=(String) query.getSingleResult();
            em.getTransaction().commit();
            em.close();
            System.out.println(ce);
        return null;
        }
    
    };

private void accionBotones(){

    CorreoAdjunto ca = new CorreoAdjunto();
    ca.setCorreo(ce);
    ca.setBienvenida("Buen día");
    ca.setArchivo("resultado.pdf");
    ca.setNombre(citas.getNombre()+" "+citas.getApePat()+" "+citas.getApeMat());
    ca.setMensaje("Se envían sus resultados con respecto a la cita: " + citas.getId());
    new Thread (ca).start();  
}

}
