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
@Table(name = "CITAS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Citas.findAll", query = "SELECT c FROM Citas c")
    , @NamedQuery(name = "Citas.findByCitId", query = "SELECT c FROM Citas c WHERE c.citId = :citId")
    , @NamedQuery(name = "Citas.findByCitFecha", query = "SELECT c FROM Citas c WHERE c.citFecha = :citFecha")
    , @NamedQuery(name = "Citas.findByCitHora", query = "SELECT c FROM Citas c WHERE c.citHora = :citHora")
    , @NamedQuery(name = "Citas.findByCitTotal", query = "SELECT c FROM Citas c WHERE c.citTotal = :citTotal")})
public class Citas implements Serializable {

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
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "CIT_TOTAL")
    private Double citTotal;
    @JoinTable(name = "CITA_EXAMEN", joinColumns = {
        @JoinColumn(name = "CIT_ID", referencedColumnName = "CIT_ID")}, inverseJoinColumns = {
        @JoinColumn(name = "EXA_ID", referencedColumnName = "EXA_ID")})
    @ManyToMany
    private Collection<Examen> examenCollection;
    @JoinColumn(name = "ER_ID", referencedColumnName = "ER_ID")
    @ManyToOne
    private EstadoRegistro erId;
    @JoinColumn(name = "STAP_ID", referencedColumnName = "STAP_ID")
    @ManyToOne
    private StatusPag stapId;
    @JoinColumn(name = "STAE_ID", referencedColumnName = "STAE_ID")
    @ManyToOne
    private StatusExa staeId;
    @JoinColumn(name = "PAC_ID", referencedColumnName = "PAC_ID")
    @ManyToOne
    private Paciente pacId;
    @JoinColumn(name = "REC_ID", referencedColumnName = "REC_ID")
    @ManyToOne
    private Recepcionista recId;
    @OneToMany(mappedBy = "citId")
    private Collection<Pagos> pagosCollection;

    public Citas() {
    }

    public Citas(Integer citId) {
        this.citId = citId;
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

    public Double getCitTotal() {
        return citTotal;
    }

    public void setCitTotal(Double citTotal) {
        this.citTotal = citTotal;
    }

    @XmlTransient
    public Collection<Examen> getExamenCollection() {
        return examenCollection;
    }

    public void setExamenCollection(Collection<Examen> examenCollection) {
        this.examenCollection = examenCollection;
    }

    public EstadoRegistro getErId() {
        return erId;
    }

    public void setErId(EstadoRegistro erId) {
        this.erId = erId;
    }

    public StatusPag getStapId() {
        return stapId;
    }

    public void setStapId(StatusPag stapId) {
        this.stapId = stapId;
    }

    public StatusExa getStaeId() {
        return staeId;
    }

    public void setStaeId(StatusExa staeId) {
        this.staeId = staeId;
    }

    public Paciente getPacId() {
        return pacId;
    }

    public void setPacId(Paciente pacId) {
        this.pacId = pacId;
    }

    public Recepcionista getRecId() {
        return recId;
    }

    public void setRecId(Recepcionista recId) {
        this.recId = recId;
    }

    @XmlTransient
    public Collection<Pagos> getPagosCollection() {
        return pagosCollection;
    }

    public void setPagosCollection(Collection<Pagos> pagosCollection) {
        this.pagosCollection = pagosCollection;
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
        if (!(object instanceof Citas)) {
            return false;
        }
        Citas other = (Citas) object;
        if ((this.citId == null && other.citId != null) || (this.citId != null && !this.citId.equals(other.citId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.integrador.POJO.Citas[ citId=" + citId + " ]";
    }
    
}
