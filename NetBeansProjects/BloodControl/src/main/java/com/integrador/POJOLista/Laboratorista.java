/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.integrador.POJOLista;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author abdias
 */
public class Laboratorista {
    
    private StringProperty nombre;
    private StringProperty apePat;
    private StringProperty apeMat;
    private StringProperty correo;
    
    public String getNombre() {
        return nombre.get();
    }
    
     public String getApePat() {
        return apePat.get();
    }
     
     public String getApeMat() {
        return apeMat.get();
    }
     
     public String getCorreo(){
         return correo.get();
    }

    public Laboratorista(String nombre, String apePat, String apeMat, String correo) {
        this.nombre = new SimpleStringProperty(nombre);
        this.apePat= new SimpleStringProperty(apePat);
        this.apeMat= new SimpleStringProperty(apeMat);
        this.correo= new SimpleStringProperty(correo);
    }
    
    
     
     
}
