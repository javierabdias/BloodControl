
package com.integrador.Consultas;

import com.integrador.POJOLista.Pacientes;
import com.integrador.persistence.EManagerFactory;
import java.util.Calendar;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author abdias
 */
public class Inicio_Tabla_Citas extends Task<ObservableList<Pacientes>> {

    @Override
    protected ObservableList<Pacientes> call() throws Exception {
        
        ObservableList<Pacientes> tabla = FXCollections.observableArrayList();
        
        EntityManager em = EManagerFactory.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        
        Calendar calendar = Calendar.getInstance(); 
        Date date = calendar.getTime();
        
        Query query = em.createQuery("SELECT NEW com.integrador.POJOLista.Pacientes(B.citId,A.pacNombre,A.pacAp,A.pacAm,B.status) "
                    + "FROM Paciente A, Cita B WHERE A.pacId=B.pacId AND B.citFecha=:fecha");
        query.setParameter("fecha", date);
        
        tabla = FXCollections.observableArrayList(query.getResultList());
        
        em.getTransaction().commit();
        em.close();
        
        return tabla;
    }
    
}
