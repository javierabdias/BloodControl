/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.integrador.Consultas;

import com.integrador.persistence.EManagerFactory;
import java.util.ArrayList;
import java.util.List;
import javafx.concurrent.Task;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author abdias
 */
public class Informacion_Cita extends Task <List<String>>{

    int iden;

    public Informacion_Cita(int iden) {
        this.iden = iden;
    }
    
    List <String> lista = new ArrayList <>();
    
    @Override
    protected List<String> call() throws Exception {
    
        EntityManager em = EManagerFactory.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT e.exaNom FROM Examen e join e.citasCollection r WHERE r.citId=:id");
        query.setParameter("id", iden);
        lista=query.getResultList();
        em.getTransaction().commit();
        em.close();
        return lista;
    }
    
}
