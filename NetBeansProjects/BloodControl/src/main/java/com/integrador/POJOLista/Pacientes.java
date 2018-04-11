/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.integrador.POJOLista;

import java.sql.Time;
import java.util.Date;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Pacientes {
    
    private IntegerProperty id;
    private StringProperty nombre;
    private StringProperty apePat;
    private StringProperty apeMat;
    private StringProperty status;
    private Date hora;
    private StringProperty examen;

        
    public Integer getId() {
        return id.get();
    }

    public void setId(Integer idUsuario) {
        this.id = new SimpleIntegerProperty(idUsuario);
    }
    
    public String getNombre() {
        return nombre.get();
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }
    
    

    public void setNombre(String nombre) {
        this.nombre= new SimpleStringProperty(nombre);
    }
    
    public String getExamen() {
        return examen.get();
    }

    public void setExamen(String examen) {
        this.examen= new SimpleStringProperty(examen);
    }
    
    public String getApePat() {
        return apePat.get();
    }

    public void setApePat(String apePat) {
        this.apePat= new SimpleStringProperty(apePat);
    }
    
    public String getApeMat() {
        return apeMat.get();
    }

    public void setApeMat(String apeMat) {
        this.apeMat= new SimpleStringProperty(apeMat);
    }
    
    public String getStatus() {
        return status.get();
    }

    public void setStatus(String nombre) {
        this.status= new SimpleStringProperty(nombre);
    }

    public Pacientes(Integer id, String nombre, String apePat, String apeMat ,String status) {
        this.id = new SimpleIntegerProperty(id);
        this.nombre = new SimpleStringProperty(nombre);
        this.apePat= new SimpleStringProperty(apePat);
        this.apeMat= new SimpleStringProperty(apeMat);
        this.status = new SimpleStringProperty(status);
    }

    public Pacientes(Date hora, String nombre, String apePat, String apeMat, String status) {
        this.nombre = new SimpleStringProperty(nombre);
        this.apePat= new SimpleStringProperty(apePat);
        this.apeMat= new SimpleStringProperty(apeMat);
        this.hora= hora;
        this.status = new SimpleStringProperty(status);
    }
    
    public Pacientes(Integer id, String examen){
        this.id= new SimpleIntegerProperty(id);
        this.examen= new SimpleStringProperty(examen);
    }    
    
}
