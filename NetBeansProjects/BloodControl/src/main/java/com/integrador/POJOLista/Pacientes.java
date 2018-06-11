/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.integrador.POJOLista;


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
    private StringProperty correo;
    private StringProperty status;
    private Date fecha;
    private Date hora;
    private StringProperty examen;
    private StringProperty tel;
     private StringProperty cel;
    

        
    public Integer getId() {
        return id.get();
    }

    public void setId(Integer idUsuario) {
        this.id = new SimpleIntegerProperty(idUsuario);
    }
    
    public String getNombre() {
        return nombre.get();
    }
    
    public String getCorreo() {
        return correo.get();
    }
    
    public String getTel() {
        return tel.get();
    }
    
    public String getCel() {
        return cel.get();
    }
     
    public Date getFecha(){
        return fecha;
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
    
    public void setCorreo(String correo) {
        this.correo= new SimpleStringProperty(correo);
    }
    
    public void setTel(String tel) {
        this.tel= new SimpleStringProperty(tel);
    }
    
    public void setCel(String cel) {
        this.cel= new SimpleStringProperty(cel);
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
    
    public Pacientes(String nombre, String apePat, String apeMat, String correo, Date fecha, String tel, String cel) {
        this.nombre = new SimpleStringProperty(nombre);
        this.apePat= new SimpleStringProperty(apePat);
        this.apeMat= new SimpleStringProperty(apeMat);
        this.correo = new SimpleStringProperty(correo);
        this.fecha = fecha;
        this.tel = new SimpleStringProperty(tel);
        this.cel = new SimpleStringProperty(cel);
    }
    
     public Pacientes(String status, Date hora) {
        this.status = new SimpleStringProperty(status);
        this.hora = hora;
    }
    
    public Pacientes(Integer id, String examen){
        this.id= new SimpleIntegerProperty(id);
        this.examen= new SimpleStringProperty(examen);
    }  
    
     public Pacientes(String examen){
        this.examen= new SimpleStringProperty(examen);
    }  
    
    
}
