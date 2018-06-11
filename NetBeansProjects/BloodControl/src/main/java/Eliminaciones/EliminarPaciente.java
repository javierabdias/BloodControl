/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Eliminaciones;

import com.integrador.POJO.EstadoRegistro;
import com.integrador.POJO.Paciente;
import com.integrador.persistence.EManagerFactory;
import javafx.concurrent.Task;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author abdias
 */
public class EliminarPaciente extends Task <Void>{

    String correo;
    Thread hilo;

    public EliminarPaciente(String correo, Thread hilo) {
        this.correo = correo;
        this.hilo = hilo;
    }
        
    @Override
    protected Void call() throws Exception {
        EntityManager em= EManagerFactory.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT p FROM Paciente p WHERE p.pacCe = :pacCe");
        query.setParameter("pacCe", correo);
        
        EstadoRegistro er = em.find(EstadoRegistro.class, "E");
        Paciente pac = (Paciente) query.getSingleResult();
        pac.setErId(er);
        
        em.persist(pac);
        
        System.out.println(pac.getErId().getErId());
        
        em.getTransaction().commit();
        em.close();
        
        hilo.start();
        return null;
    }
    
}
