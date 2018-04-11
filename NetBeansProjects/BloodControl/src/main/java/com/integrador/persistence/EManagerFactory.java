/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.integrador.persistence;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author abdias
 */

//  **CLASE SINGLETON PARA CONEXIÓN A BASE DE DATOS**
public class EManagerFactory {
    
    //Se instancia EntityManagerFactory
    private static EntityManagerFactory emf;
    
    private EManagerFactory(){}
    
    
    public static EntityManagerFactory getEntityManagerFactory(){
        
        if(emf==null){
            emf=Persistence.createEntityManagerFactory("BloodControlPU");
        }
        return emf;
    }
    
}
