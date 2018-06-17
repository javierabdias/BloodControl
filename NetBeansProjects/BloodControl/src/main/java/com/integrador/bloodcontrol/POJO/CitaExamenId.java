/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.integrador.bloodcontrol.POJO;

import java.io.Serializable;
import javax.persistence.ManyToOne;

/**
 *
 * @author abdias
 */
public class CitaExamenId implements Serializable{

    private Examen examen;
    private Citas cita;
    
    @ManyToOne
    public Citas getCita() {
        return cita;
    }

    public void setCita(Citas cita) {
        this.cita = cita;
    }
    
    @ManyToOne
    public Examen getExamen() {
        return examen;
    }

    public void setExamen(Examen examen) {
        this.examen = examen;
    }
    
    
}
