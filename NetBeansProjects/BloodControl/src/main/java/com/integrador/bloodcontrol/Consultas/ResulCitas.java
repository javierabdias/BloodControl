/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.integrador.bloodcontrol.Consultas;

import com.integrador.POJOLista.Resul;
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

public class ResulCitas extends Task<ObservableList<Resul>>{

    int cita;

    public ResulCitas(int cita) {
        this.cita = cita;
    }
    
    @Override
    protected ObservableList<Resul> call() throws Exception {
        ObservableList <Resul> lista = FXCollections.observableArrayList();
        
        EntityManager em = EManagerFactory.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        
        Query query = em.createQuery("SELECT NEW com.integrador.POJOLista.Resul (C.estId,C.estNombre) FROM Citas A, Examen B, Estudios C, Cita_Examen D "
                + "WHERE B.exaId=C.exaId AND A.citId=D.primaryKey.cita.citId AND B.exaId=D.primaryKey.examen.exaId AND A.citId=:cita");
        query.setParameter("cita", cita);
        lista=FXCollections.observableArrayList(query.getResultList());
        em.getTransaction().commit();
        em.close();
        
        return lista;
    }
    
}
