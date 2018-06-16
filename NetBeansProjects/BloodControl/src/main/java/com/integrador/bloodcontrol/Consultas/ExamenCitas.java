/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.integrador.bloodcontrol.Consultas;

import com.integrador.bloodcontrol.persistence.EManagerFactory;
import java.util.ArrayList;
import java.util.List;
import javafx.concurrent.Task;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author abdias
 */
public class ExamenCitas extends Task <List<String>>{

    String nombre;

    public ExamenCitas(String nombre) {
        this.nombre = nombre;
    }
    
    @Override
    protected List<String> call() throws Exception {
        List <String> estudios= new ArrayList <>();
        
        
        
        EntityManager em = EManagerFactory.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT b.estNombre FROM Examen a, Estudios b WHERE a.exaNom=:nombre AND a.exaId=b.exaId");
        query.setParameter("nombre", nombre);
        estudios= query.getResultList();
        em.getTransaction().commit();
        em.close();
        
        return estudios;
    }
    
}
