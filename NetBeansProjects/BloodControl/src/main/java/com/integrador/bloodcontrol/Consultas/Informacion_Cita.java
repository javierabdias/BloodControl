/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.integrador.bloodcontrol.Consultas;

import com.integrador.POJOLista.Pacientes;
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
public class Informacion_Cita extends Task <List<Object>>{

    int iden;

    public Informacion_Cita(int iden) {
        this.iden = iden;
    }
    
    List <Object> lista = new ArrayList <>();
    List <Object> general = new ArrayList <>();
    
    @Override
    protected List<Object> call() throws Exception {
    
        EntityManager em = EManagerFactory.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        
        Query query = em.createQuery("SELECT e.exaNom FROM Examen e join e.citasCollection r WHERE r.citId=:id");
        query.setParameter("id", iden);
        lista=query.getResultList();
        
        Query query2= em.createQuery("SELECT NEW com.integrador.POJOLista.Pacientes (A.citHora, B.perNombre, B.perAp, B.perAm,C.status) FROM Citas A, Persona B, StatusExa C, Paciente D "
                + "WHERE C.staeId=A.staeId AND A.citId=:id AND D.perId=B.perId AND A.pacId=D.pacId");
        query2.setParameter("id", iden);
        Pacientes pac= (Pacientes) query2.getSingleResult();
        
        em.getTransaction().commit();
        em.close();
        
        general.add(lista);
        general.add(pac);
        
        return general;
    }
    
}
