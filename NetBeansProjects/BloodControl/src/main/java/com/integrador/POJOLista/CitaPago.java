/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.integrador.POJOLista;

import java.util.Date;

/**
 *
 * @author abdias
 */
public class CitaPago {

    String correo;
    Date fecha;
    Date hora;

    public CitaPago(String correo, Date fecha, Date hora) {
        this.correo = correo;
        this.fecha = fecha;
        this.hora = hora;
    }
    
    public CitaPago() {
    }

    public String getCorreo() {
        return correo;
    }

    public Date getFecha() {
        return fecha;
    }

    public Date getHora() {
        return hora;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }
    
    
}
