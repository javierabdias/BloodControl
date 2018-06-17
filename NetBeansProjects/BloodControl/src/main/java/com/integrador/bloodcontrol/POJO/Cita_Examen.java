/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.integrador.bloodcontrol.POJO;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * @author abdias
 */

@Entity
@Table (name="CITA_EXAMEN")
@AssociationOverrides({
    @AssociationOverride (name = "primaryKey.cita", joinColumns = @JoinColumn(name="CIT_ID")),
    @AssociationOverride (name = "primaryKey.examen", joinColumns = @JoinColumn(name="EXA_ID")) })

public class Cita_Examen {
    
    private CitaExamenId primaryKey= new CitaExamenId ();

    public Cita_Examen() {
    }
    
    @EmbeddedId
    public CitaExamenId getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(CitaExamenId primaryKey) {
        this.primaryKey = primaryKey;
    }
    
    @Transient
    public Citas getCitas(){
        return getPrimaryKey().getCita();
    }
    
    public void setCitas(Citas cita){
        getPrimaryKey().setCita(cita);
    }
    
    @Transient
    public Examen getExamen(){
        return getPrimaryKey().getExamen();
    }
    
    public void setExamen(Examen examen){
        getPrimaryKey().setExamen(examen);    
    }
    
}
