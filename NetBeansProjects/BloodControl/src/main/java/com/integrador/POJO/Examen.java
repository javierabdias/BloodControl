/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.integrador.POJO;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
    , @NamedQuery(name = "Examen.findByPrecio", query = "SELECT e FROM Examen e WHERE e.precio = :precio")})
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
    @Column(name = "PRECIO")
    private Double precio;
    @ManyToMany(mappedBy = "examenCollection")
    private Collection<Cita> citaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "exaId")
    private Collection<Estudios> estudiosCollection;

    public Examen() {
    }

    public Examen(String exaNom) {
        this.exaNom = exaNom;
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

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    @XmlTransient
    public Collection<Cita> getCitaCollection() {
        return citaCollection;
    }

    public void setCitaCollection(Collection<Cita> citaCollection) {
        this.citaCollection = citaCollection;
    }

    @XmlTransient
    public Collection<Estudios> getEstudiosCollection() {
        return estudiosCollection;
    }

    public void setEstudiosCollection(Collection<Estudios> estudiosCollection) {
        this.estudiosCollection = estudiosCollection;
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
