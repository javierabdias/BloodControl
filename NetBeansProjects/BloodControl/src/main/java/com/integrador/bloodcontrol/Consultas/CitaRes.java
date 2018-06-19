/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.integrador.bloodcontrol.Consultas;

import com.integrador.bloodcontrol.POJO.Citas;
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
public class CitaRes extends Task <List <Object>>{
    
    int id;

    public CitaRes(int id) {
        this.id = id;
    }
    
    

    @Override
    protected List <Object> call() throws Exception {
        EntityManager em = EManagerFactory.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        Citas cita = em.find(Citas.class, id);
        Query query = em.createQuery("SELECT A.pacCe FROM Paciente A, Citas B WHERE A.pacId=B.pacId AND B.citId=:id");
        query.setParameter("id", id);
        
        String correo = (String) query.getSingleResult();
        
        List <Object> lista = new ArrayList <>();
        
        lista.add(cita);
        lista.add(correo);
        
        em.getTransaction().commit();
        em.close();
        return lista;
    }
    
}
