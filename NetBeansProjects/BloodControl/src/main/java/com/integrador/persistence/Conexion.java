/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.integrador.persistence;

import javafx.concurrent.Task;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author abdias
 */

//  **CLASE PARA HILO PARA REALIZAR LA CONEXIÓN CON LA BASE DE DATOS**
public class Conexion extends Task<Void>{
    
    private Stage stage;
    
    
    // Constructor para recibir un stage
    public Conexion(Stage stage) {
        this.stage = stage;
    }   

    @Override
    protected Void call() throws Exception {
        
        //Evaluación para verificar que la conexión se haya realizado correctamente
        if(getExito()){
            new Thread(new LogIn(stage)).start();
        }
        return null;
    }
    
    //Método para evaluación de la conexión con base de datos
    private boolean getExito(){
        try{
          EManagerFactory.getEntityManagerFactory();
        }catch(Exception e){
           
                    JOptionPane.showMessageDialog(null, "Imposible Conectarse Con El Servidor, Verifique Su Conexion A Internet", "Error De Conexion", JOptionPane.ERROR_MESSAGE);
                    System.exit(0);
                
            return false;
        }
        
        return true;
    }
   
}
