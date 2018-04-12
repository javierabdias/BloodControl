/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.integrador.Consultas;


import com.integrador.POJOLista.Pacientes;
import com.integrador.persistence.EManagerFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author abdias
 */
public class citaInformacion extends Task <ObservableList<ObservableList>>{

    int id_Cita;

    public citaInformacion(int idCita) {
        this.id_Cita = idCita;
    }
       
    @Override
    protected ObservableList<ObservableList> call() throws Exception {
        ObservableList<ObservableList> tablas = FXCollections.observableArrayList();
        ObservableList<Pacientes> tabla = FXCollections.observableArrayList();
        ObservableList<String> tabla2 = FXCollections.observableArrayList();
        
            EntityManager em = EManagerFactory.getEntityManagerFactory().createEntityManager();
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT NEW com.integrador.POJOLista.Pacientes(B.citHora,A.pacNombre,A.pacAp,A.pacAm,B.status) "
                    + "FROM Paciente A, Cita B WHERE A.pacId=B.pacId AND B.citId=:id");
            query.setParameter("id",id_Cita);
            Query query2 = em.createQuery("SELECT A.exaNom FROM Examen A WHERE :id in elements(A.citaCollection)");
            query2.setParameter("id",id_Cita);
            tabla = FXCollections.observableArrayList(query.getResultList());
            tabla2 = FXCollections.observableArrayList(query2.getResultList());
            em.getTransaction().commit();
            em.close();
        
            tablas.add(tabla);
            tablas.add(tabla2);
            
        return tablas;
    }
    
}
