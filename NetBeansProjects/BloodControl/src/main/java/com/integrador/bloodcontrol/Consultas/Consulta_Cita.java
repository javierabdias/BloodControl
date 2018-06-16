/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.integrador.bloodcontrol.Consultas;

import com.integrador.bloodcontrol.POJO.Paciente;
import com.integrador.bloodcontrol.POJO.Persona;
import com.integrador.bloodcontrol.persistence.EManagerFactory;
import java.util.List;
import javafx.concurrent.Task;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author abdias
 */


public class Consulta_Cita extends Task<String> {
Paciente pac;

String correo;
EntityManager em = EManagerFactory.getEntityManagerFactory().createEntityManager();
String status;

    public Consulta_Cita(String correo) {
        this.correo = correo;
    }

    @Override
    protected String call() throws Exception {
        
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT p FROM Paciente p WHERE p.pacCe = :pacCe AND p.erId='A'");
        query.setParameter("pacCe", correo);
        List<Paciente> lista = query.getResultList();
        em.getTransaction().commit();
                
        if (lista.size() != 0) {
            pac = lista.get(0);
            em.getTransaction().begin();
            Persona pers = em.find(Persona.class, pac.getPerId().getPerId());
            em.getTransaction().commit();
            em.close();
            status= pers.getPerNombre() + " "+ pers.getPerAp() + " " + pers.getPerAm();
          
        } else {
            status="No se encuentra paciente.";
            em.close();
        }
        
        return status;
    }
}
    

