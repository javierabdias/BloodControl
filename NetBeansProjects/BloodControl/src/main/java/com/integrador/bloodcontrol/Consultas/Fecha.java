/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.integrador.bloodcontrol.Consultas;

import com.integrador.bloodcontrol.persistence.EManagerFactory;
import java.util.Date;
import java.util.List;
import javafx.concurrent.Task;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author abdias
 */


public class Fecha extends Task<Void>{

    List <String> list;
    
    Date fecha;

    public Fecha(List<String> lista, Date fecha) {
        this.list = lista;
        this.fecha = fecha;
    }
    
    @Override
    protected Void call() throws Exception {
        EntityManager em = EManagerFactory.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT c.citHora FROM Citas c WHERE c.citFecha=:fecha AND c.erId='A'");
        query.setParameter("fecha", fecha);
        List<Date> fe = query.getResultList();
        em.getTransaction().commit();
        em.close();

        if (!fe.isEmpty()) {
            for (Date fec : fe) {

                if (list.contains(fec.toString())) {
                    list.remove(fec.toString());
                }
            }
        } 
        return null;
    }
    
}
