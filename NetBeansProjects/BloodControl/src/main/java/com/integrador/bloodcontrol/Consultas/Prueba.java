/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.integrador.bloodcontrol.Consultas;

import com.integrador.bloodcontrol.POJO.Cita_Examen;
import com.integrador.bloodcontrol.persistence.EManagerFactory;
import java.util.List;
import javafx.concurrent.Task;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author abdias
 */
public class Prueba extends Task <Void>{

    @Override
    protected Void call() throws Exception {
        EntityManager em = EManagerFactory.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT r FROM CitaExamen r");
        List <Cita_Examen> resul = query.getResultList();
        em.getTransaction().commit();
        em.close();
        
        System.out.println(resul.get(0).getExamen().getExaNom());
        
        return null;
    }
    
}
