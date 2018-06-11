/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.integrador.Consultas;

import com.integrador.POJOLista.Laboratorista;
import com.integrador.bloodcontrol.Funciones.Usuarios;
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
    EntityManager em= EManagerFactory.getEntityManagerFactory().createEntityManager();
    
    String tipo;
    int id;

    public Usuario(String tipo, int id) {
        this.tipo = tipo;
        this.id = id;
    }
    
    @Override
    protected List<Laboratorista> call() throws Exception {

        String queries = "";

        switch (tipo) {

            case "Administrador":
                queries = "SELECT NEW com.integrador.POJOLista.Laboratorista (B.perNombre,B.perAp,B.perAm,A.admCe) "
                        + "FROM Administrador A, Persona B WHERE A.perId=B.perId AND A.admId=:id";
                break;

            case "Recepcionista":
                queries = "SELECT NEW com.integrador.POJOLista.Laboratorista (B.perNombre,B.perAp,B.perAm,A.recCe) "
                        + "FROM Recepcionista A, Persona B WHERE A.perId=B.perId AND A.recId=:id";
                break;

            case "Laboratorista":
                queries = "SELECT NEW com.integrador.POJOLista.Laboratorista (B.perNombre,B.perAp,B.perAm,A.labCe) "
                        + "FROM Laboratorista A, Persona B WHERE A.perId=B.perId AND A.labId=:id";
                break;
        }

        em.getTransaction().begin();
        Query query = em.createQuery(queries);
        query.setParameter("id", id);
        lista = query.getResultList();
        em.getTransaction().commit();
        em.close();

        if (!lista.isEmpty()) {
            return lista;
        } else {
            return null;
        }
    }

}
