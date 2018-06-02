/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Consultas;

import com.integrador.POJOLista.Laboratorista;
import com.integrador.bloodcontrol.LogInController;
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
public class Usuario extends Task <List <Laboratorista>>{
    
    List <Laboratorista> lista = new ArrayList<>();
    
    @Override
    protected List<Laboratorista> call() throws Exception {
        
        EntityManager em= EManagerFactory.getEntityManagerFactory().createEntityManager();
       
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT NEW com.integrador.POJOLista.Laboratorista (B.perNombre,B.perAp,B.perAm,A.labCe) "
                + "FROM Laboratorista A, Persona B WHERE A.perId=B.perId AND A.labId=:id");
        query.setParameter("id", LogInController.id_usuario);
        lista= query.getResultList();
        em.getTransaction().commit();
        em.close();
        
        if(!lista.isEmpty()){
            return lista;
        }
        
        else {
            return null;
        }
    }
    
}
