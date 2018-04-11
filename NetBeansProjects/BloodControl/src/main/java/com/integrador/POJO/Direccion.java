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
@Table(name = "DIRECCION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Direccion.findAll", query = "SELECT d FROM Direccion d")
    , @NamedQuery(name = "Direccion.findByDirId", query = "SELECT d FROM Direccion d WHERE d.dirId = :dirId")
    , @NamedQuery(name = "Direccion.findByDirCalle", query = "SELECT d FROM Direccion d WHERE d.dirCalle = :dirCalle")
    , @NamedQuery(name = "Direccion.findByDirMza", query = "SELECT d FROM Direccion d WHERE d.dirMza = :dirMza")
    , @NamedQuery(name = "Direccion.findByDirLote", query = "SELECT d FROM Direccion d WHERE d.dirLote = :dirLote")
    , @NamedQuery(name = "Direccion.findByDirColonia", query = "SELECT d FROM Direccion d WHERE d.dirColonia = :dirColonia")})
public class Direccion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "DIR_ID")
    private Integer dirId;
    @Column(name = "DIR_CALLE")
    private String dirCalle;
    @Column(name = "DIR_MZA")
    private String dirMza;
    @Column(name = "DIR_LOTE")
    private String dirLote;
    @Column(name = "DIR_COLONIA")
    private String dirColonia;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dirId")
    private Collection<Usuarios> usuariosCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dirId")
    private Collection<Paciente> pacienteCollection;

    public Direccion() {
    }

    public Direccion(Integer dirId) {
        this.dirId = dirId;
    }

    public Integer getDirId() {
        return dirId;
    }

    public void setDirId(Integer dirId) {
        this.dirId = dirId;
    }

    public String getDirCalle() {
        return dirCalle;
    }

    public void setDirCalle(String dirCalle) {
        this.dirCalle = dirCalle;
    }

    public String getDirMza() {
        return dirMza;
    }

    public void setDirMza(String dirMza) {
        this.dirMza = dirMza;
    }

    public String getDirLote() {
        return dirLote;
    }

    public void setDirLote(String dirLote) {
        this.dirLote = dirLote;
    }

    public String getDirColonia() {
        return dirColonia;
    }

    public void setDirColonia(String dirColonia) {
        this.dirColonia = dirColonia;
    }

    @XmlTransient
    public Collection<Usuarios> getUsuariosCollection() {
        return usuariosCollection;
    }

    public void setUsuariosCollection(Collection<Usuarios> usuariosCollection) {
        this.usuariosCollection = usuariosCollection;
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
        hash += (dirId != null ? dirId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Direccion)) {
            return false;
        }
        Direccion other = (Direccion) object;
        if ((this.dirId == null && other.dirId != null) || (this.dirId != null && !this.dirId.equals(other.dirId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.integrador.POJO.Direccion[ dirId=" + dirId + " ]";
    }
    
}
