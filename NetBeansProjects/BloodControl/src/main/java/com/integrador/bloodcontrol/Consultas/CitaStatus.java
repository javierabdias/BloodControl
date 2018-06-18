/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.integrador.bloodcontrol.Consultas;

import com.integrador.bloodcontrol.POJO.Resultados;
import com.integrador.bloodcontrol.persistence.EManagerFactory;
import java.util.List;
import javafx.concurrent.Task;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author abdias
 */



public class CitaStatus extends Task <Boolean>{

    int id;

    public CitaStatus(int id) {
        this.id = id;
    }
    
    
    @Override
    protected Boolean call() throws Exception {
        
        EntityManager em = EManagerFactory.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT r FROM Resultados r WHERE primaryKey.citId.citId=:id");
        query.setParameter("id", id);
        List <Resultados> lista= query.getResultList();
        
        if(lista.isEmpty()){
            return true;
        }
        return false;
        
        
    }
    
}
