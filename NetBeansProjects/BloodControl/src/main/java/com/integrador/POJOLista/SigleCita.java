/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.integrador.POJOLista;

import java.util.Date;
import javafx.collections.ObservableList;

/**
 *
 * @author abdias
 */
public class SigleCita {
    
    private static String correo;
    private static Date fecha;
    private static Date hora;
    
    private static ObservableList<CitaExamen> examenes;
    
    private SigleCita(){}

    public static String getCorreo() {
        return correo;
    }

    public static Date getFecha() {
        return fecha;
    }

    public static Date getHora() {
        return hora;
    }

    public static ObservableList<CitaExamen> getExamenes() {
        return examenes;
    }

    public static void setCorreo(String correo) {
        SigleCita.correo = correo;
    }

    public static void setFecha(Date fecha) {
        SigleCita.fecha = fecha;
    }

    public static void setHora(Date hora) {
        SigleCita.hora = hora;
    }

    public static void setExamenes(ObservableList<CitaExamen> examenes) {
        SigleCita.examenes = examenes;
    }
    
    
    
    
}
