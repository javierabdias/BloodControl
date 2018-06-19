/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.integrador.POJOLista;

import com.integrador.bloodcontrol.POJO.Citas;

/**
 *
 * @author abdias
 */
public class SigleCita {
    
    private static Citas cita;
    private static Double total;
    
    
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

}
