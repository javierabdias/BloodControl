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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author abdias
 */
@Entity
@Table(name = "EXAMEN")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Examen.findAll", query = "SELECT e FROM Examen e")
    , @NamedQuery(name = "Examen.findByExaId", query = "SELECT e FROM Examen e WHERE e.exaId = :exaId")
    , @NamedQuery(name = "Examen.findByExaNom", query = "SELECT e FROM Examen e WHERE e.exaNom = :exaNom")
    , @NamedQuery(name = "Examen.findByExaPrecio", query = "SELECT e FROM Examen e WHERE e.exaPrecio = :exaPrecio")})
public class Examen implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "EXA_ID")
    private Integer exaId;
    @Column(name = "EXA_NOM")
    private String exaNom;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "EXA_PRECIO")
    private Double exaPrecio;
    @ManyToMany(mappedBy = "examenCollection")
    private Collection<Citas> citasCollection;
    @OneToMany(mappedBy = "exaId")
    private Collection<Estudios> estudiosCollection;
    @JoinColumn(name = "ER_ID", referencedColumnName = "ER_ID")
    @ManyToOne
    private EstadoRegistro erId;

    public Examen() {
    }

    public Examen(Integer exaId) {
        this.exaId = exaId;
    }

    public Integer getExaId() {
        return exaId;
    }

    public void setExaId(Integer exaId) {
        this.exaId = exaId;
    }

    public String getExaNom() {
        return exaNom;
    }

    public void setExaNom(String exaNom) {
        this.exaNom = exaNom;
    }

    public Double getExaPrecio() {
        return exaPrecio;
    }

    public void setExaPrecio(Double exaPrecio) {
        this.exaPrecio = exaPrecio;
    }

    @XmlTransient
    public Collection<Citas> getCitasCollection() {
        return citasCollection;
    }

    public void setCitasCollection(Collection<Citas> citasCollection) {
        this.citasCollection = citasCollection;
    }

    @XmlTransient
    public Collection<Estudios> getEstudiosCollection() {
        return estudiosCollection;
    }

    public void setEstudiosCollection(Collection<Estudios> estudiosCollection) {
        this.estudiosCollection = estudiosCollection;
    }

    public EstadoRegistro getErId() {
        return erId;
    }

    public void setErId(EstadoRegistro erId) {
        this.erId = erId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (exaId != null ? exaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Examen)) {
            return false;
        }
        Examen other = (Examen) object;
        if ((this.exaId == null && other.exaId != null) || (this.exaId != null && !this.exaId.equals(other.exaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.integrador.POJO.Examen[ exaId=" + exaId + " ]";
    }
    
}
