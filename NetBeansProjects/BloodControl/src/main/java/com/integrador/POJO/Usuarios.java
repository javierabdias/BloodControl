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
import javax.persistence.CascadeType;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author abdias
 */
@Entity
@Table(name = "USUARIOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuarios.findAll", query = "SELECT u FROM Usuarios u")
    , @NamedQuery(name = "Usuarios.findByUsuId", query = "SELECT u FROM Usuarios u WHERE u.usuId = :usuId")
    , @NamedQuery(name = "Usuarios.findByUsuNombre", query = "SELECT u FROM Usuarios u WHERE u.usuNombre = :usuNombre")
    , @NamedQuery(name = "Usuarios.findByUsuAp", query = "SELECT u FROM Usuarios u WHERE u.usuAp = :usuAp")
    , @NamedQuery(name = "Usuarios.findByUsuAm", query = "SELECT u FROM Usuarios u WHERE u.usuAm = :usuAm")
    , @NamedQuery(name = "Usuarios.findByUsuFn", query = "SELECT u FROM Usuarios u WHERE u.usuFn = :usuFn")
    , @NamedQuery(name = "Usuarios.findByUsuCe", query = "SELECT u FROM Usuarios u WHERE u.usuCe = :usuCe")
    , @NamedQuery(name = "Usuarios.findByUsuContra", query = "SELECT u FROM Usuarios u WHERE u.usuContra = :usuContra")
    , @NamedQuery(name = "Usuarios.findByUsuTel", query = "SELECT u FROM Usuarios u WHERE u.usuTel = :usuTel")
    , @NamedQuery(name = "Usuarios.findByUsuCel", query = "SELECT u FROM Usuarios u WHERE u.usuCel = :usuCel")
    , @NamedQuery(name = "Usuarios.findByUsuTipo", query = "SELECT u FROM Usuarios u WHERE u.usuTipo = :usuTipo")})
public class Usuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "USU_ID")
    private Integer usuId;
    @Column(name = "USU_NOMBRE")
    private String usuNombre;
    @Column(name = "USU_AP")
    private String usuAp;
    @Column(name = "USU_AM")
    private String usuAm;
    @Column(name = "USU_FN")
    @Temporal(TemporalType.DATE)
    private Date usuFn;
    @Column(name = "USU_CE")
    private String usuCe;
    @Column(name = "USU_CONTRA")
    private String usuContra;
    @Column(name = "USU_TEL")
    private String usuTel;
    @Column(name = "USU_CEL")
    private String usuCel;
    @Column(name = "USU_TIPO")
    private String usuTipo;
    @JoinColumn(name = "DIR_ID", referencedColumnName = "DIR_ID")
    @ManyToOne(optional = false)
    private Direccion dirId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuId")
    private Collection<Cita> citaCollection;

    public Usuarios() {
    }

    public Usuarios(Integer usuId) {
        this.usuId = usuId;
    }

    public Integer getUsuId() {
        return usuId;
    }

    public void setUsuId(Integer usuId) {
        this.usuId = usuId;
    }

    public String getUsuNombre() {
        return usuNombre;
    }

    public void setUsuNombre(String usuNombre) {
        this.usuNombre = usuNombre;
    }

    public String getUsuAp() {
        return usuAp;
    }

    public void setUsuAp(String usuAp) {
        this.usuAp = usuAp;
    }

    public String getUsuAm() {
        return usuAm;
    }

    public void setUsuAm(String usuAm) {
        this.usuAm = usuAm;
    }

    public Date getUsuFn() {
        return usuFn;
    }

    public void setUsuFn(Date usuFn) {
        this.usuFn = usuFn;
    }

    public String getUsuCe() {
        return usuCe;
    }

    public void setUsuCe(String usuCe) {
        this.usuCe = usuCe;
    }

    public String getUsuContra() {
        return usuContra;
    }

    public void setUsuContra(String usuContra) {
        this.usuContra = usuContra;
    }

    public String getUsuTel() {
        return usuTel;
    }

    public void setUsuTel(String usuTel) {
        this.usuTel = usuTel;
    }

    public String getUsuCel() {
        return usuCel;
    }

    public void setUsuCel(String usuCel) {
        this.usuCel = usuCel;
    }

    public String getUsuTipo() {
        return usuTipo;
    }

    public void setUsuTipo(String usuTipo) {
        this.usuTipo = usuTipo;
    }

    public Direccion getDirId() {
        return dirId;
    }

    public void setDirId(Direccion dirId) {
        this.dirId = dirId;
    }

    @XmlTransient
    public Collection<Cita> getCitaCollection() {
        return citaCollection;
    }

    public void setCitaCollection(Collection<Cita> citaCollection) {
        this.citaCollection = citaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuId != null ? usuId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuarios)) {
            return false;
        }
        Usuarios other = (Usuarios) object;
        if ((this.usuId == null && other.usuId != null) || (this.usuId != null && !this.usuId.equals(other.usuId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.integrador.POJO.Usuarios[ usuId=" + usuId + " ]";
    }
    
}
