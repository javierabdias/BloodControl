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
public class LogIn extends Task <Boolean>{
    
    String usuario, contra;

    public LogIn(String usuario, String contra) {
        this.usuario = usuario;
        this.contra = contra;
    }
        
    @Override
    protected Boolean call() throws Exception {
         EntityManager manager = EManagerFactory.getEntityManagerFactory().createEntityManager();
         manager.getTransaction().begin();
         Query query= manager.createQuery("SELECT A.usuContra FROM Usuarios A WHERE A.usuId=:id").setParameter("id", Integer.valueOf(usuario));
         Query query2= manager.createQuery("SELECT A.usuTipo FROM Usuarios A WHERE A.usuId=:id").setParameter("id", Integer.valueOf(usuario));
         Object contras = query.getSingleResult();
         Object tipo= query2.getSingleResult();
         manager.close();
         
         String c= (String) contras;
         String t= (String) tipo;
         
         if(c.equals(contra)){
             if(t.equals("ADMINISTRADOR")||t.equals("LABORATORISTA")){
                 return true;
             }
         }
                  
         return false;
    }
    
}
