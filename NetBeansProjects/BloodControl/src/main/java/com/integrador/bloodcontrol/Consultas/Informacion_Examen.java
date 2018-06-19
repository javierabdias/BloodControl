/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.integrador.bloodcontrol.Consultas;

import com.integrador.bloodcontrol.persistence.EManagerFactory;
import java.util.List;
import javafx.concurrent.Task;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author abdias
 */
public class Informacion_Examen extends Task <List<Object>> {

    int id;

    public Informacion_Examen(int id) {
        this.id = id;
    }
    
    @Override
    protected List<Object> call() throws Exception {
        
        EntityManager em = EManagerFactory.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT A.estNombre FROM Estudios A join A.exaId B WHERE B.exaId=:id");
        query.setParameter("id", id);
        List <Object> lista= query.getResultList();
        em.getTransaction().commit();
        em.close();
        
        return lista;
    }
    
}
