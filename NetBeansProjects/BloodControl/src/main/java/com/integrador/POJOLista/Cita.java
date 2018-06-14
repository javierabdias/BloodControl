/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.integrador.POJOLista;

import java.util.Date;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author abdias
 */
public class Cita {
    private StringProperty nombre;
    private StringProperty apePat;
    private StringProperty apeMat;
    private Date fecha;
    private Date hora;
    private StringProperty pago;
    private StringProperty extraccion;

   public String getNombre() {
        return nombre.get();
    }
    
   public void setNombre(String nombre) {
        this.nombre= new SimpleStringProperty(nombre);
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
    
    public Date getFecha(){
        return fecha;
    }

    public Date getHora() {
        return hora;
    }
    
    public void setHora(Date hora) {
        this.hora = hora;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    public String getPago() {
        return pago.get();
    }

    public void setPago(String pago) {
        this.pago= new SimpleStringProperty(pago);
    }
    
    public String getExtraccion() {
        return extraccion.get();
    }

    public void setExtraccion(String extraccion) {
        this.extraccion= new SimpleStringProperty(extraccion);
    }
    
     public Cita (String nombre, String apePat, String apeMat ,Date fecha, Date hora, String pago, String extraccion) {
        this.nombre = new SimpleStringProperty(nombre);
        this.apePat= new SimpleStringProperty(apePat);
        this.apeMat= new SimpleStringProperty(apeMat);
        this.fecha = fecha;
        this.hora= hora;
        this.pago= new SimpleStringProperty(pago);
        this.extraccion= new SimpleStringProperty(extraccion);
    }
}
