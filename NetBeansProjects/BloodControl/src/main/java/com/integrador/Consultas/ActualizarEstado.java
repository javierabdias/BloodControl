/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.integrador.Consultas;

import com.integrador.POJO.Cita;
import com.integrador.POJOLista.Pacientes;
import com.integrador.persistence.EManagerFactory;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javax.persistence.EntityManager;

/**
 *
 * @author abdias
 */
public class ActualizarEstado extends Task<ObservableList<Pacientes>>{
    
    int id;
    String estado;
    Task segundo;

    public ActualizarEstado(int id, String estado) {
        this.id = id;
        this.estado = estado;
    }
    
    @Override
    protected ObservableList<Pacientes> call() throws Exception {
        
        EntityManager em = EManagerFactory.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        Cita cita= em.find(Cita.class, id);
        cita.setStatus(estado);
        em.persist(cita);
        em.getTransaction().commit();
        em.close();
        return null;
    }
    
}
