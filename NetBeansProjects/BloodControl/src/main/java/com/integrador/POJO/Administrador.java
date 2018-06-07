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
@Table(name = "ADMINISTRADOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Administrador.findAll", query = "SELECT a FROM Administrador a")
    , @NamedQuery(name = "Administrador.findByAdmId", query = "SELECT a FROM Administrador a WHERE a.admId = :admId")
    , @NamedQuery(name = "Administrador.findByAdmCe", query = "SELECT a FROM Administrador a WHERE a.admCe = :admCe")
    , @NamedQuery(name = "Administrador.findByAdmContra", query = "SELECT a FROM Administrador a WHERE a.admContra = :admContra")
    , @NamedQuery(name = "Administrador.findByAdmTel", query = "SELECT a FROM Administrador a WHERE a.admTel = :admTel")
    , @NamedQuery(name = "Administrador.findByAdmCel", query = "SELECT a FROM Administrador a WHERE a.admCel = :admCel")})
public class Administrador implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ADM_ID")
    private Integer admId;
    @Column(name = "ADM_CE")
    private String admCe;
    @Column(name = "ADM_CONTRA")
    private String admContra;
    @Column(name = "ADM_TEL")
    private String admTel;
    @Column(name = "ADM_CEL")
    private String admCel;
    @JoinColumn(name = "ER_ID", referencedColumnName = "ER_ID")
    @ManyToOne
    private EstadoRegistro erId;
    @JoinColumn(name = "DIR_ID", referencedColumnName = "DIR_ID")
    @ManyToOne
    private DirUsu dirId;
    @JoinColumn(name = "PER_ID", referencedColumnName = "PER_ID")
    @ManyToOne
    private Persona perId;

    public Administrador() {
    }

    public Administrador(Integer admId) {
        this.admId = admId;
    }

    public Integer getAdmId() {
        return admId;
    }

    public void setAdmId(Integer admId) {
        this.admId = admId;
    }

    public String getAdmCe() {
        return admCe;
    }

    public void setAdmCe(String admCe) {
        this.admCe = admCe;
    }

    public String getAdmContra() {
        return admContra;
    }

    public void setAdmContra(String admContra) {
        this.admContra = admContra;
    }

    public String getAdmTel() {
        return admTel;
    }

    public void setAdmTel(String admTel) {
        this.admTel = admTel;
    }

    public String getAdmCel() {
        return admCel;
    }

    public void setAdmCel(String admCel) {
        this.admCel = admCel;
    }

    public EstadoRegistro getErId() {
        return erId;
    }

    public void setErId(EstadoRegistro erId) {
        this.erId = erId;
    }

    public DirUsu getDirId() {
        return dirId;
    }

    public void setDirId(DirUsu dirId) {
        this.dirId = dirId;
    }

    public Persona getPerId() {
        return perId;
    }

    public void setPerId(Persona perId) {
        this.perId = perId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (admId != null ? admId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Administrador)) {
            return false;
        }
        Administrador other = (Administrador) object;
        if ((this.admId == null && other.admId != null) || (this.admId != null && !this.admId.equals(other.admId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.integrador.POJO.Administrador[ admId=" + admId + " ]";
    }
    
}
