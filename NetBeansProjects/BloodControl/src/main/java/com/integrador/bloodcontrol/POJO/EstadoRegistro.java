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
import javax.persistence.Id;
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
@Table(name = "ESTADO_REGISTRO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadoRegistro.findAll", query = "SELECT e FROM EstadoRegistro e")
    , @NamedQuery(name = "EstadoRegistro.findByErId", query = "SELECT e FROM EstadoRegistro e WHERE e.erId = :erId")
    , @NamedQuery(name = "EstadoRegistro.findByEstado", query = "SELECT e FROM EstadoRegistro e WHERE e.estado = :estado")})
public class EstadoRegistro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ER_ID")
    private String erId;
    @Column(name = "ESTADO")
    private String estado;
    @OneToMany(mappedBy = "erId")
    private Collection<Administrador> administradorCollection;
    @OneToMany(mappedBy = "erId")
    private Collection<Estudios> estudiosCollection;
    @OneToMany(mappedBy = "erId")
    private Collection<Recepcionista> recepcionistaCollection;
    @OneToMany(mappedBy = "erId")
    private Collection<Laboratorista> laboratoristaCollection;
    @OneToMany(mappedBy = "erId")
    private Collection<Citas> citasCollection;
    @OneToMany(mappedBy = "erId")
    private Collection<Paciente> pacienteCollection;
    @OneToMany(mappedBy = "erId")
    private Collection<Examen> examenCollection;
    
    @OneToMany (mappedBy = "primaryKey.erId")
    private Collection <Resultados> resultados;

    public Collection<Resultados> getResultados() {
        return resultados;
    }

    public void setResultados(Collection<Resultados> resultados) {
        this.resultados = resultados;
    }

    public EstadoRegistro() {
    }

    public EstadoRegistro(String erId) {
        this.erId = erId;
    }

    public String getErId() {
        return erId;
    }

    public void setErId(String erId) {
        this.erId = erId;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @XmlTransient
    public Collection<Administrador> getAdministradorCollection() {
        return administradorCollection;
    }

    public void setAdministradorCollection(Collection<Administrador> administradorCollection) {
        this.administradorCollection = administradorCollection;
    }

    @XmlTransient
    public Collection<Estudios> getEstudiosCollection() {
        return estudiosCollection;
    }

    public void setEstudiosCollection(Collection<Estudios> estudiosCollection) {
        this.estudiosCollection = estudiosCollection;
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
    public Collection<Citas> getCitasCollection() {
        return citasCollection;
    }

    public void setCitasCollection(Collection<Citas> citasCollection) {
        this.citasCollection = citasCollection;
    }

    @XmlTransient
    public Collection<Paciente> getPacienteCollection() {
        return pacienteCollection;
    }

    public void setPacienteCollection(Collection<Paciente> pacienteCollection) {
        this.pacienteCollection = pacienteCollection;
    }

    @XmlTransient
    public Collection<Examen> getExamenCollection() {
        return examenCollection;
    }

    public void setExamenCollection(Collection<Examen> examenCollection) {
        this.examenCollection = examenCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (erId != null ? erId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoRegistro)) {
            return false;
        }
        EstadoRegistro other = (EstadoRegistro) object;
        if ((this.erId == null && other.erId != null) || (this.erId != null && !this.erId.equals(other.erId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.integrador.bloodcontrol.POJO.EstadoRegistro[ erId=" + erId + " ]";
    }
    
}
