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
@Table(name = "DIR_USU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DirUsu.findAll", query = "SELECT d FROM DirUsu d")
    , @NamedQuery(name = "DirUsu.findByDirId", query = "SELECT d FROM DirUsu d WHERE d.dirId = :dirId")
    , @NamedQuery(name = "DirUsu.findByDirCalle", query = "SELECT d FROM DirUsu d WHERE d.dirCalle = :dirCalle")
    , @NamedQuery(name = "DirUsu.findByDirMza", query = "SELECT d FROM DirUsu d WHERE d.dirMza = :dirMza")
    , @NamedQuery(name = "DirUsu.findByDirLte", query = "SELECT d FROM DirUsu d WHERE d.dirLte = :dirLte")
    , @NamedQuery(name = "DirUsu.findByDirColonia", query = "SELECT d FROM DirUsu d WHERE d.dirColonia = :dirColonia")})
public class DirUsu implements Serializable {

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
    @Column(name = "DIR_LTE")
    private String dirLte;
    @Column(name = "DIR_COLONIA")
    private String dirColonia;
    @OneToMany(mappedBy = "dirId")
    private Collection<Administrador> administradorCollection;
    @OneToMany(mappedBy = "dirId")
    private Collection<Recepcionista> recepcionistaCollection;
    @OneToMany(mappedBy = "dirId")
    private Collection<Laboratorista> laboratoristaCollection;

    public DirUsu() {
    }

    public DirUsu(Integer dirId) {
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

    public String getDirLte() {
        return dirLte;
    }

    public void setDirLte(String dirLte) {
        this.dirLte = dirLte;
    }

    public String getDirColonia() {
        return dirColonia;
    }

    public void setDirColonia(String dirColonia) {
        this.dirColonia = dirColonia;
    }

    @XmlTransient
    public Collection<Administrador> getAdministradorCollection() {
        return administradorCollection;
    }

    public void setAdministradorCollection(Collection<Administrador> administradorCollection) {
        this.administradorCollection = administradorCollection;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dirId != null ? dirId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DirUsu)) {
            return false;
        }
        DirUsu other = (DirUsu) object;
        if ((this.dirId == null && other.dirId != null) || (this.dirId != null && !this.dirId.equals(other.dirId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.integrador.POJO.DirUsu[ dirId=" + dirId + " ]";
    }
    
}
