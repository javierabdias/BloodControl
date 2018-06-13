/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.integrador.bloodcontrol.Consultas;

import com.integrador.POJOLista.Pacientes;
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
public class Paciente_Tabla extends Task <ObservableList<Pacientes>>{

    @Override
    protected ObservableList<Pacientes> call() throws Exception {
        ObservableList<Pacientes> tabla= FXCollections.observableArrayList();
        
        EntityManager em= EManagerFactory.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT NEW com.integrador.POJOLista.Pacientes (B.perNombre,B.perAp,B.perAm,A.pacCe,B.perFn,A.pacTel,A.pacCel) FROM Paciente A, Persona B"
                + " WHERE A.perId=B.perId AND A.erId='A'");
        tabla= FXCollections.observableArrayList(query.getResultList());
        em.getTransaction().commit();
        em.close();
        return tabla;
    }
    
}
