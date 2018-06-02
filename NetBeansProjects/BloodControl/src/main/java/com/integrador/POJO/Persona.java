/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.integrador.POJO;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author abdias
 */
@Entity
@Table(name = "PERSONA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Persona.findAll", query = "SELECT p FROM Persona p")
    , @NamedQuery(name = "Persona.findByPerId", query = "SELECT p FROM Persona p WHERE p.perId = :perId")
    , @NamedQuery(name = "Persona.findByPerNombre", query = "SELECT p FROM Persona p WHERE p.perNombre = :perNombre")
    , @NamedQuery(name = "Persona.findByPerAp", query = "SELECT p FROM Persona p WHERE p.perAp = :perAp")
    , @NamedQuery(name = "Persona.findByPerAm", query = "SELECT p FROM Persona p WHERE p.perAm = :perAm")
    , @NamedQuery(name = "Persona.findByPerFn", query = "SELECT p FROM Persona p WHERE p.perFn = :perFn")})
public class Persona implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PER_ID")
    private Integer perId;
    @Column(name = "PER_NOMBRE")
    private String perNombre;
    @Column(name = "PER_AP")
    private String perAp;
    @Column(name = "PER_AM")
    private String perAm;
    @Column(name = "PER_FN")
    @Temporal(TemporalType.DATE)
    private Date perFn;
    @OneToMany(mappedBy = "perId")
    private Collection<Recepcionista> recepcionistaCollection;
    @OneToMany(mappedBy = "perId")
    private Collection<Laboratorista> laboratoristaCollection;
    @OneToMany(mappedBy = "perId")
    private Collection<Paciente> pacienteCollection;

    public Persona() {
    }

    public Persona(Integer perId) {
        this.perId = perId;
    }

    public Integer getPerId() {
        return perId;
    }

    public void setPerId(Integer perId) {
        this.perId = perId;
    }

    public String getPerNombre() {
        return perNombre;
    }

    public void setPerNombre(String perNombre) {
        this.perNombre = perNombre;
    }

    public String getPerAp() {
        return perAp;
    }

    public void setPerAp(String perAp) {
        this.perAp = perAp;
    }

    public String getPerAm() {
        return perAm;
    }

    public void setPerAm(String perAm) {
        this.perAm = perAm;
    }

    public Date getPerFn() {
        return perFn;
    }

    public void setPerFn(Date perFn) {
        this.perFn = perFn;
    }

    @XmlTransient
    public Collection<Recepcionista> getRecepcionistaCollection() {
        return recepcionistaCollection;
    }

    public void setRecepcionistaCollection(Collection<Recepcionista> recepcionistaCollection) {
        this.recepcionistaCollection = recepcionistaCollection;
    }

    @XmlTransient
    public Collection<Laboratorista> getLaboratoristaCollection() {
        return laboratoristaCollection;
    }

    public void setLaboratoristaCollection(Collection<Laboratorista> laboratoristaCollection) {
        this.laboratoristaCollection = laboratoristaCollection;
    }

    @XmlTransient
    public Collection<Paciente> getPacienteCollection() {
        return pacienteCollection;
    }

    public void setPacienteCollection(Collection<Paciente> pacienteCollection) {
        this.pacienteCollection = pacienteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (perId != null ? perId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Persona)) {
            return false;
        }
        Persona other = (Persona) object;
        if ((this.perId == null && other.perId != null) || (this.perId != null && !this.perId.equals(other.perId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.integrador.POJO.Persona[ perId=" + perId + " ]";
    }
    
}
