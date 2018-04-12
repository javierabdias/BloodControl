/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.integrador.Consultas;

import com.integrador.POJO.Usuarios;
import com.integrador.persistence.EManagerFactory;
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
         Usuarios u = manager.find(Usuarios.class, Integer.valueOf(usuario));
         manager.close();
         
         if(u==null){
             return false;
         } else if(u.getUsuContra().equals(contra)){
             if(u.getUsuTipo().equals("ADMINISTRADOR")||u.getUsuTipo().equals("LABORATORISTA")){
                 return true;
             }  else{
                 return false;
             }
         } else {
             return false;
         }
              
    }
    
}
