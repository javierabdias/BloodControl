/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.integrador.Consultas;

import com.integrador.POJO.Administrador;
import com.integrador.POJO.Laboratorista;
import com.integrador.POJO.Recepcionista;
import com.integrador.persistence.EManagerFactory;
import javafx.concurrent.Task;
import javax.persistence.EntityManager;

/**
 *
 * @author abdias
 */
public class LogIn extends Task<Boolean> {

    String usuario, password, tipo;
    EntityManager em = EManagerFactory.getEntityManagerFactory().createEntityManager();

    public LogIn(String usuario, String password, String tipo) {
        this.usuario = usuario;
        this.password = password;
        this.tipo = tipo;
    }

    @Override
    protected Boolean call() throws Exception {

        switch (tipo) {

            case "Administrador":
                em.getTransaction().begin();
                Administrador adm = em.find(Administrador.class, Integer.valueOf(usuario));
                em.getTransaction().commit();
                em.close();

                if (adm != null) {

                    if (adm.getAdmContra().equals(password) && adm.getErId().getErId().equals("A")) {
                        return true;
                    } else {
                        return false;
                    }
                }else{
                return false;}
                

            case "Laboratorista":
                em.getTransaction().begin();
                Laboratorista lab = em.find(Laboratorista.class, Integer.valueOf(usuario));
                em.getTransaction().commit();
                em.close();

                if (lab != null) {
                    if (lab.getLabContra().equals(password) && lab.getErId().getErId().equals("A")) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                return false;}

            case "Recepcionista":
                em.getTransaction().begin();
                Recepcionista rec = em.find(Recepcionista.class, Integer.valueOf(usuario));
                em.getTransaction().commit();
                em.close();

                if (rec != null) {
                    if (rec.getRecContra().equals(password) && rec.getErId().getErId().equals("A")) {
                        return true;
                    } else {
                        return false;
                    }
                } else{
                return false;
                }        
        }

        return false;

    }

}
