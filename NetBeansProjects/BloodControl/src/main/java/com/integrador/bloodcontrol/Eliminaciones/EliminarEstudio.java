/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.integrador.bloodcontrol.Eliminaciones;

import com.integrador.bloodcontrol.POJO.EstadoRegistro;
import com.integrador.bloodcontrol.POJO.Estudios;
import com.integrador.bloodcontrol.persistence.EManagerFactory;
import javafx.concurrent.Task;
import javax.persistence.EntityManager;

/**
 *
 * @author abdias
 */
public class EliminarEstudio extends Task<Void>{

    
    int i;

    public EliminarEstudio(int i) {
        this.i = i;
    }
    
    @Override
    protected Void call() throws Exception {
        
        EntityManager em = EManagerFactory.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        
        Estudios estudios = em.find(Estudios.class, i);
        EstadoRegistro er = em.find(EstadoRegistro.class, "E");
        
        estudios.setErId(er);
        em.persist(estudios);
       
        em.getTransaction().commit();
        em.close();
        
        return null;
    }
    
}
