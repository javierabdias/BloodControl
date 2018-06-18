/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.integrador.bloodcontrol.Consultas;

import com.integrador.POJOLista.CitaExamen;
import com.integrador.bloodcontrol.persistence.EManagerFactory;
import javafx.concurrent.Task;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author abdias
 */
public class ExaCita extends Task <CitaExamen>{

    String nombre;

    public ExaCita(String nombre) {
        this.nombre = nombre;
    }
    
    @Override
    protected CitaExamen call() throws Exception {
        EntityManager em= EManagerFactory.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT NEW com.integrador.POJOLista.CitaExamen (e.exaId,e.exaNom,e.exaPrecio) FROM Examen e WHERE e.exaNom=:nombre");
        query.setParameter("nombre", nombre);
        CitaExamen cita = (CitaExamen) query.getSingleResult();
        em.getTransaction().commit();
        em.close();
        
        return cita;
    }
    
}
