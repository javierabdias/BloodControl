/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.integrador.bloodcontrol.Modificaciones;

import com.integrador.bloodcontrol.POJO.Citas;
import com.integrador.bloodcontrol.POJO.StatusExa;
import com.integrador.bloodcontrol.persistence.EManagerFactory;
import javafx.concurrent.Task;
import javax.persistence.EntityManager;

/**
 *
 * @author abdias
 */
public class ExtraccionStatus extends Task <Void>{

    int id;
    String status;
    Thread actualizacion;

    public ExtraccionStatus(int id, String status, Thread actualizacion) {
        this.id = id;
        this.status = status;
        this.actualizacion = actualizacion;
    }

    @Override
    protected Void call() throws Exception {
        
        EntityManager em = EManagerFactory.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        
        StatusExa se= em.find(StatusExa.class, status);
        Citas cita = em.find(Citas.class, id);
        cita.setStaeId(se);
        
        em.getTransaction().commit();
        em.close();
        
        actualizacion.start();
        
        return null;
    }
    
}