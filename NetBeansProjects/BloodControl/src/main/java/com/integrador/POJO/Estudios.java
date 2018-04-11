/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.integrador.POJO;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author abdias
 */
@Entity
@Table(name = "ESTUDIOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estudios.findAll", query = "SELECT e FROM Estudios e")
    , @NamedQuery(name = "Estudios.findByEstId", query = "SELECT e FROM Estudios e WHERE e.estId = :estId")
    , @NamedQuery(name = "Estudios.findByEstNom", query = "SELECT e FROM Estudios e WHERE e.estNom = :estNom")
    , @NamedQuery(name = "Estudios.findByValorMin", query = "SELECT e FROM Estudios e WHERE e.valorMin = :valorMin")
    , @NamedQuery(name = "Estudios.findByValorMax", query = "SELECT e FROM Estudios e WHERE e.valorMax = :valorMax")})
public class Estudios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "EST_ID")
    private Integer estId;
    @Basic(optional = false)
    @Column(name = "EST_NOM")
    private String estNom;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "VALOR_MIN")
    private Double valorMin;
    @Column(name = "VALOR_MAX")
    private Double valorMax;
    @JoinColumn(name = "EXA_ID", referencedColumnName = "EXA_ID")
    @ManyToOne(optional = false)
    private Examen exaId;

    public Estudios() {
    }

    public Estudios(Integer estId) {
        this.estId = estId;
    }

    public Estudios(Integer estId, String estNom) {
        this.estId = estId;
        this.estNom = estNom;
    }

    public Integer getEstId() {
        return estId;
    }

    public void setEstId(Integer estId) {
        this.estId = estId;
    }

    public String getEstNom() {
        return estNom;
    }

    public void setEstNom(String estNom) {
        this.estNom = estNom;
    }

    public Double getValorMin() {
        return valorMin;
    }

    public void setValorMin(Double valorMin) {
        this.valorMin = valorMin;
    }

    public Double getValorMax() {
        return valorMax;
    }

    public void setValorMax(Double valorMax) {
        this.valorMax = valorMax;
    }

    public Examen getExaId() {
        return exaId;
    }

    public void setExaId(Examen exaId) {
        this.exaId = exaId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (estId != null ? estId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estudios)) {
            return false;
        }
        Estudios other = (Estudios) object;
        if ((this.estId == null && other.estId != null) || (this.estId != null && !this.estId.equals(other.estId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.integrador.POJO.Estudios[ estId=" + estId + " ]";
    }
    
}
