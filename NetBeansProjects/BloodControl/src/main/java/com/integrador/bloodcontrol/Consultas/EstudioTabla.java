/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.integrador.bloodcontrol.Consultas;

import com.integrador.POJOLista.Estudio;
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
public class EstudioTabla extends Task <ObservableList<Estudio>>{

    @Override
    protected ObservableList<Estudio> call() throws Exception {
        
        EntityManager em = EManagerFactory.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT NEW com.integrador.POJOLista.Estudio (A.estId,A.estNombre,A.estMin,A.estMax,B.exaNom) FROM Estudios A, Examen B WHERE A.exaId=B.exaId AND A.erId='A'");
        ObservableList<Estudio> lista = FXCollections.observableArrayList(query.getResultList());
        em.getTransaction().commit();
        em.close();
        
        return lista;
    }
    
}
