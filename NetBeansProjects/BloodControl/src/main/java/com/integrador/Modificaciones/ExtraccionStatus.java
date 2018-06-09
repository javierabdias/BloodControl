/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.integrador.Modificaciones;


import com.integrador.POJO.StatusExa;
import com.integrador.persistence.EManagerFactory;
import javafx.concurrent.Task;
import javax.persistence.EntityManager;

/**
 *
 * @author abdias
 */

public class ExtraccionStatus extends Task <Void>{

    int id;
    String status;

    public ExtraccionStatus(int id, String status) {
        this.id = id;
        this.status = status;
    }

    @Override
    protected Void call() throws Exception {
        
        EntityManager em = EManagerFactory.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        
        StatusExa se= em.find(StatusExa.class, status);
        //Citas cita         
        
        em.getTransaction().commit();
        em.close();
        return null;
    }
    
}
