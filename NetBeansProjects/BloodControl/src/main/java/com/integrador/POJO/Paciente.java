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
import javax.persistence.JoinColumn;
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
@Table(name = "PACIENTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Paciente.findAll", query = "SELECT p FROM Paciente p")
    , @NamedQuery(name = "Paciente.findByPacId", query = "SELECT p FROM Paciente p WHERE p.pacId = :pacId")
    , @NamedQuery(name = "Paciente.findByPacNombre", query = "SELECT p FROM Paciente p WHERE p.pacNombre = :pacNombre")
    , @NamedQuery(name = "Paciente.findByPacAp", query = "SELECT p FROM Paciente p WHERE p.pacAp = :pacAp")
    , @NamedQuery(name = "Paciente.findByPacAm", query = "SELECT p FROM Paciente p WHERE p.pacAm = :pacAm")
    , @NamedQuery(name = "Paciente.findByPacFn", query = "SELECT p FROM Paciente p WHERE p.pacFn = :pacFn")
    , @NamedQuery(name = "Paciente.findByPacCe", query = "SELECT p FROM Paciente p WHERE p.pacCe = :pacCe")
    , @NamedQuery(name = "Paciente.findByPacContra", query = "SELECT p FROM Paciente p WHERE p.pacContra = :pacContra")
    , @NamedQuery(name = "Paciente.findByPacTel", query = "SELECT p FROM Paciente p WHERE p.pacTel = :pacTel")
    , @NamedQuery(name = "Paciente.findByPacCel", query = "SELECT p FROM Paciente p WHERE p.pacCel = :pacCel")})
public class Paciente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PAC_ID")
    private Integer pacId;
    @Basic(optional = false)
    @Column(name = "PAC_NOMBRE")
    private String pacNombre;
    @Basic(optional = false)
    @Column(name = "PAC_AP")
    private String pacAp;
    @Basic(optional = false)
    @Column(name = "PAC_AM")
    private String pacAm;
    @Column(name = "PAC_FN")
    private String pacFn;
    @Basic(optional = false)
    @Column(name = "PAC_CE")
    private String pacCe;
    @Basic(optional = false)
    @Column(name = "PAC_CONTRA")
    private String pacContra;
    @Column(name = "PAC_TEL")
    private Integer pacTel;
    @Column(name = "PAC_CEL")
    private Integer pacCel;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pacId")
    private Collection<Cita> citaCollection;
    @JoinColumn(name = "DIR_ID", referencedColumnName = "DIR_ID")
    @ManyToOne(optional = false)
    private Direccion dirId;

    public Paciente() {
    }

    public Paciente(Integer pacId) {
        this.pacId = pacId;
    }

    public Paciente(Integer pacId, String pacNombre, String pacAp, String pacAm, String pacCe, String pacContra) {
        this.pacId = pacId;
        this.pacNombre = pacNombre;
        this.pacAp = pacAp;
        this.pacAm = pacAm;
        this.pacCe = pacCe;
        this.pacContra = pacContra;
    }

    public Integer getPacId() {
        return pacId;
    }

    public void setPacId(Integer pacId) {
        this.pacId = pacId;
    }

    public String getPacNombre() {
        return pacNombre;
    }

    public void setPacNombre(String pacNombre) {
        this.pacNombre = pacNombre;
    }

    public String getPacAp() {
        return pacAp;
    }

    public void setPacAp(String pacAp) {
        this.pacAp = pacAp;
    }

    public String getPacAm() {
        return pacAm;
    }

    public void setPacAm(String pacAm) {
        this.pacAm = pacAm;
    }

    public String getPacFn() {
        return pacFn;
    }

    public void setPacFn(String pacFn) {
        this.pacFn = pacFn;
    }

    public String getPacCe() {
        return pacCe;
    }

    public void setPacCe(String pacCe) {
        this.pacCe = pacCe;
    }

    public String getPacContra() {
        return pacContra;
    }

    public void setPacContra(String pacContra) {
        this.pacContra = pacContra;
    }

    public Integer getPacTel() {
        return pacTel;
    }

    public void setPacTel(Integer pacTel) {
        this.pacTel = pacTel;
    }

    public Integer getPacCel() {
        return pacCel;
    }

    public void setPacCel(Integer pacCel) {
        this.pacCel = pacCel;
    }

    @XmlTransient
    public Collection<Cita> getCitaCollection() {
        return citaCollection;
    }

    public void setCitaCollection(Collection<Cita> citaCollection) {
        this.citaCollection = citaCollection;
    }

    public Direccion getDirId() {
        return dirId;
    }

    public void setDirId(Direccion dirId) {
        this.dirId = dirId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pacId != null ? pacId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Paciente)) {
            return false;
        }
        Paciente other = (Paciente) object;
        if ((this.pacId == null && other.pacId != null) || (this.pacId != null && !this.pacId.equals(other.pacId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.integrador.POJO.Paciente[ pacId=" + pacId + " ]";
    }
    
}
