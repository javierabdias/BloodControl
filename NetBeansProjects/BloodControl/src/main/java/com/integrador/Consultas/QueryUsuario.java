/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.integrador.Consultas;

import com.integrador.POJO.Usuarios;
import com.integrador.bloodcontrol.LogInController;
import com.integrador.persistence.EManagerFactory;
import java.util.ArrayList;
import java.util.List;
import javafx.concurrent.Task;
import javax.persistence.EntityManager;

/**
 *
 * @author abdias
 */
public class QueryUsuario extends Task<List> {

    @Override
    protected List call() throws Exception {
        
        List <String> usuario= new ArrayList<>();
        EntityManager em = EManagerFactory.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        Usuarios u = em.find(Usuarios.class, LogInController.id_usuario);
        em.getTransaction().commit();
        em.close();
        
        usuario.add(u.getUsuNombre()+" "+u.getUsuAp()+" "+u.getUsuAm());
        usuario.add(u.getUsuCe());
        
        return usuario;
    }
    
}
