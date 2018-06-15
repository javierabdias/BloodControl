/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.integrador.bloodcontrol.POJO;

import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.OneToMany;
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
    , @NamedQuery(name = "Estudios.findByEstNombre", query = "SELECT e FROM Estudios e WHERE e.estNombre = :estNombre")
    , @NamedQuery(name = "Estudios.findByEstMin", query = "SELECT e FROM Estudios e WHERE e.estMin = :estMin")
    , @NamedQuery(name = "Estudios.findByEstMax", query = "SELECT e FROM Estudios e WHERE e.estMax = :estMax")})
public class Estudios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "EST_ID")
    private Integer estId;
    @Column(name = "EST_NOMBRE")
    private String estNombre;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "EST_MIN")
    private Double estMin;
    @Column(name = "EST_MAX")
    private Double estMax;
    @JoinColumn(name = "ER_ID", referencedColumnName = "ER_ID")
    @ManyToOne
    private EstadoRegistro erId;
    @JoinColumn(name = "EXA_ID", referencedColumnName = "EXA_ID")
    @ManyToOne
    private Examen exaId;
    
    @OneToMany (mappedBy = "primaryKey.estId")
    private Collection <Resultados> resultados;

    public Collection<Resultados> getResultados() {
        return resultados;
    }

    public void setResultados(Collection<Resultados> resultados) {
        this.resultados = resultados;
    }


    public Estudios() {
    }

    public Estudios(Integer estId) {
        this.estId = estId;
    }

    public Integer getEstId() {
        return estId;
    }

    public void setEstId(Integer estId) {
        this.estId = estId;
    }

    public String getEstNombre() {
        return estNombre;
    }

    public void setEstNombre(String estNombre) {
        this.estNombre = estNombre;
    }

    public Double getEstMin() {
        return estMin;
    }

    public void setEstMin(Double estMin) {
        this.estMin = estMin;
    }

    public Double getEstMax() {
        return estMax;
    }

    public void setEstMax(Double estMax) {
        this.estMax = estMax;
    }

    public EstadoRegistro getErId() {
        return erId;
    }

    public void setErId(EstadoRegistro erId) {
        this.erId = erId;
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
        return "com.integrador.bloodcontrol.POJO.Estudios[ estId=" + estId + " ]";
    }
    
}
