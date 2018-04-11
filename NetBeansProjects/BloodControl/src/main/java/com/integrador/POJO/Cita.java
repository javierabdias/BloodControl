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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "CITA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cita.findAll", query = "SELECT c FROM Cita c")
    , @NamedQuery(name = "Cita.findByCitId", query = "SELECT c FROM Cita c WHERE c.citId = :citId")
    , @NamedQuery(name = "Cita.findByCitFecha", query = "SELECT c FROM Cita c WHERE c.citFecha = :citFecha")
    , @NamedQuery(name = "Cita.findByCitHora", query = "SELECT c FROM Cita c WHERE c.citHora = :citHora")
    , @NamedQuery(name = "Cita.findByTotal", query = "SELECT c FROM Cita c WHERE c.total = :total")
    , @NamedQuery(name = "Cita.findByStatus", query = "SELECT c FROM Cita c WHERE c.status = :status")})
public class Cita implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CIT_ID")
    private Integer citId;
    @Column(name = "CIT_FECHA")
    @Temporal(TemporalType.DATE)
    private Date citFecha;
    @Column(name = "CIT_HORA")
    @Temporal(TemporalType.TIME)
    private Date citHora;
    @Basic(optional = false)
    @Column(name = "TOTAL")
    private double total;
    @Column(name = "STATUS")
    private String status;
    @JoinTable(name = "CITA_EXAMEN", joinColumns = {
        @JoinColumn(name = "CIT_ID", referencedColumnName = "CIT_ID")}, inverseJoinColumns = {
        @JoinColumn(name = "EXA_ID", referencedColumnName = "EXA_ID")})
    @ManyToMany
    private Collection<Examen> examenCollection;
    @JoinColumn(name = "PAC_ID", referencedColumnName = "PAC_ID")
    @ManyToOne(optional = false)
    private Paciente pacId;
    @JoinColumn(name = "USU_ID", referencedColumnName = "USU_ID")
    @ManyToOne(optional = false)
    private Usuarios usuId;

    public Cita() {
    }

    public Cita(Integer citId) {
        this.citId = citId;
    }

    public Cita(Integer citId, double total) {
        this.citId = citId;
        this.total = total;
    }

    public Integer getCitId() {
        return citId;
    }

    public void setCitId(Integer citId) {
        this.citId = citId;
    }

    public Date getCitFecha() {
        return citFecha;
    }

    public void setCitFecha(Date citFecha) {
        this.citFecha = citFecha;
    }

    public Date getCitHora() {
        return citHora;
    }

    public void setCitHora(Date citHora) {
        this.citHora = citHora;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @XmlTransient
    public Collection<Examen> getExamenCollection() {
        return examenCollection;
    }

    public void setExamenCollection(Collection<Examen> examenCollection) {
        this.examenCollection = examenCollection;
    }

    public Paciente getPacId() {
        return pacId;
    }

    public void setPacId(Paciente pacId) {
        this.pacId = pacId;
    }

    public Usuarios getUsuId() {
        return usuId;
    }

    public void setUsuId(Usuarios usuId) {
        this.usuId = usuId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (citId != null ? citId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cita)) {
            return false;
        }
        Cita other = (Cita) object;
        if ((this.citId == null && other.citId != null) || (this.citId != null && !this.citId.equals(other.citId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.integrador.POJO.Cita[ citId=" + citId + " ]";
    }
    
}
