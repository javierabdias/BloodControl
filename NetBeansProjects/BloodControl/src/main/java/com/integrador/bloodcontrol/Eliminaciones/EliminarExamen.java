/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.integrador.bloodcontrol.Eliminaciones;

import com.integrador.bloodcontrol.POJO.EstadoRegistro;
import com.integrador.bloodcontrol.POJO.Examen;
import com.integrador.bloodcontrol.persistence.EManagerFactory;
import javafx.concurrent.Task;
import javax.persistence.EntityManager;

/**
 *
 * @author abdias
 */
public class EliminarExamen extends Task <Void>{

    int i;

    public EliminarExamen(int i) {
        this.i = i;
    }
    
    @Override
    protected Void call() throws Exception {
      EntityManager em = EManagerFactory.getEntityManagerFactory().createEntityManager();
      em.getTransaction().begin();
      
      EstadoRegistro er = em.find(EstadoRegistro.class, "E");
      
      Examen e = em.find(Examen.class, i);
      e.setErId(er);
      
      em.persist(e);
      em.getTransaction().commit();
      em.close();
        return null;
    }
    
}
