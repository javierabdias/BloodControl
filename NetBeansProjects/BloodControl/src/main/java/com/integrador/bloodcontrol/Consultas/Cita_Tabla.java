/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.integrador.bloodcontrol.Consultas;

import com.integrador.POJOLista.Cita;
import com.integrador.bloodcontrol.persistence.EManagerFactory;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author abdias
 */
public class Cita_Tabla extends Task <ObservableList<Cita>> {

    @Override
    protected ObservableList<Cita> call() throws Exception {
       
        ObservableList<Cita> citas = FXCollections.observableArrayList();
        Date fecha= new Date();
        EntityManager em = EManagerFactory.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT NEW com.integrador.POJOLista.Cita (C.citId,B.perNombre,B.perAp,B.perAm,C.citFecha,C.citHora,E.status,D.status) FROM Paciente A, Persona B, Citas C, StatusExa D, StatusPag E "
                + "WHERE A.perId=B.perId AND A.pacId=C.pacId AND E.stapId=C.stapId AND C.staeId=D.staeId AND A.erId='A' AND C.erId='A' AND C.citFecha>=:fecha");
        query.setParameter("fecha", fecha);
        citas= FXCollections.observableArrayList(query.getResultList());
        em.getTransaction().commit();
        em.close();
        
       return citas;
    }
    
}
