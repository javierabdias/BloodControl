/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.integrador.POJOLista;

import com.integrador.bloodcontrol.POJO.Citas;
import java.util.Date;

/**
 *
 * @author abdias
 */
public class SigleCita {
    
    private static Citas cita;
    private static Double total;
    private static String correo;
    private static String nombre;
    private static Date fecha;
    private static Date hora;
    
    
    private SigleCita(){}
    
    public static Citas getCita() {
        return cita;
    }

    public static void setCita(Citas cita) {
        SigleCita.cita = cita;
    }

    public static Double getTotal() {
        return total;
    }

    public static void setTotal(Double total) {
        SigleCita.total = total;
    }

    public static String getCorreo() {
        return correo;
    }

    public static void setCorreo(String correo) {
        SigleCita.correo = correo;
    }

    public static String getNombre() {
        return nombre;
    }

    public static void setNombre(String nombre) {
        SigleCita.nombre = nombre;
    }

    public static Date getFecha() {
        return fecha;
    }

    public static void setFecha(Date fecha) {
        SigleCita.fecha = fecha;
    }

    public static Date getHora() {
        return hora;
    }

    public static void setHora(Date hora) {
        SigleCita.hora = hora;
    }
    
}
