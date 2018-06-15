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
public class ResultadosId implements Serializable{
    
   private Citas citId;
    private Estudios estId;
    private Laboratorista labId;
    private EstadoRegistro erId;
    
    @ManyToOne
    public Citas getCitId() {
        return citId;
    }

    public void setCitId(Citas citId) {
        this.citId = citId;
    }
    
    @ManyToOne
    public Estudios getEstId() {
        return estId;
    }

    public void setEstId(Estudios estId) {
        this.estId = estId;
    }
    
    @ManyToOne
    public EstadoRegistro getErId() {
        return erId;
    }

    public void setErId(EstadoRegistro erId) {
        this.erId = erId;
    }
    
    @ManyToOne
    public Laboratorista getLabId() {
        return labId;
    }

    public void setLabId(Laboratorista labId) {
        this.labId = labId;
    }
    
}
