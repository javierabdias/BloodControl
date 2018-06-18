/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.integrador.bloodcontrol.Eliminaciones;

import com.integrador.bloodcontrol.POJO.Citas;
import com.integrador.bloodcontrol.POJO.EstadoRegistro;
import com.integrador.bloodcontrol.persistence.EManagerFactory;
import javafx.concurrent.Task;
import javax.persistence.EntityManager;

/**
 *
 * @author abdias
 */
public class EliminarCita extends Task <Void>{

    Integer id;
    

    public EliminarCita(Integer id) {
        this.id = id;
    }
    
    @Override
    protected Void call() throws Exception {
       
        EntityManager em = EManagerFactory.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        Citas citas = em.find(Citas.class, id);
        EstadoRegistro status= em.find(EstadoRegistro.class, "E");
        citas.setErId(status);
        em.getTransaction().commit();
        em.close();
        return null;
    }
    
}
