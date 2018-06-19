/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.integrador.bloodcontrol.Consultas;

import com.integrador.POJOLista.Examenes;
import com.integrador.bloodcontrol.persistence.EManagerFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author abdias
 */
public class ExaTabla extends Task <ObservableList<Examenes>>{

    @Override
    protected ObservableList<Examenes> call() throws Exception {
        
        EntityManager em = EManagerFactory.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        
        Query query = em.createQuery("SELECT NEW com.integrador.POJOLista.Examenes (e.exaId,e.exaNom,e.exaPrecio) FROM Examen e");
        ObservableList <Examenes> exam = FXCollections.observableArrayList(query.getResultList());
        
        em.getTransaction().commit();
        em.close();
        
        return exam;
    }
    
}
