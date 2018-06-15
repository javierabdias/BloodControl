/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.integrador.bloodcontrol.POJO;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
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
@Table(name="RESULTADOS")
@AssociationOverrides({
    @AssociationOverride( name = "primaryKey.citId", joinColumns = @JoinColumn (name = "CIT_ID")),
    @AssociationOverride( name = "primaryKey.estId", joinColumns = @JoinColumn (name = "EST_ID")),
    @AssociationOverride( name = "primaryKey.labId", joinColumns = @JoinColumn (name = "LAB_ID")),
    @AssociationOverride( name = "primaryKey.erId", joinColumns = @JoinColumn (name = "ER_ID"))
})

public class Resultados implements Serializable {
    
    private ResultadosId primaryKey = new ResultadosId();
    private Double resultado;
    private Date timestamp;
    
    @EmbeddedId
    public ResultadosId getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(ResultadosId primaryKey) {
        this.primaryKey = primaryKey;
    }
    
    @Column (name = "RESULTADO")
    public Double getResultado() {
        return resultado;
    }

    public void setResultado(Double resultado) {
        this.resultado = resultado;
    }
        
    @Column (name = "RES_TIEMPO")
    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
    
    public void setCitas(Citas cita){
        getPrimaryKey().setCitId(cita);
    }
    
    @Transient
    public Citas getCitas(){
        return getPrimaryKey().getCitId();
    }
    
    public void setEstudios(Estudios est){
        getPrimaryKey().setEstId(est);
    }
    
    @Transient
    public Estudios getEstudios(){
        return getPrimaryKey().getEstId();
    }
    
    public void setLaboratorista(Laboratorista lab){
        getPrimaryKey().setLabId(lab);
    }
    
    @Transient
    public Laboratorista getLaboratorista(){
        return getPrimaryKey().getLabId();
    }
    
     public void setER(EstadoRegistro er){
        getPrimaryKey().setErId(er);
    }
    
    @Transient
    public EstadoRegistro getER(){
        return getPrimaryKey().getErId();
    }

}
