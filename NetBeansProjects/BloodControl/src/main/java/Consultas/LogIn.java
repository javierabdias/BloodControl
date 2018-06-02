/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Consultas;

import com.integrador.POJO.Laboratorista;
import com.integrador.persistence.EManagerFactory;
import javafx.concurrent.Task;
import javax.persistence.EntityManager;

/**
 *
 * @author abdias
 */
public class LogIn extends Task <Boolean>{
    
    String usuario, password;

    public LogIn(String usuario, String password) {
        this.usuario = usuario;
        this.password = password;
    }

    @Override
    protected Boolean call() throws Exception {
       
        EntityManager em= EManagerFactory.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        Laboratorista lab = em.find(Laboratorista.class, Integer.valueOf(usuario));
        em.getTransaction().commit();
        em.close();
        
        if(lab!= null){
            
            if(lab.getLabContra().equals(password)&& lab.getErId().getErId().equals("A")){
                return true;
             
            } else {
                
                return false;
            }
        }
        return false;
    }
    
}
