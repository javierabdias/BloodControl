/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.integrador.bloodcontrol.POJO;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author abdias
 */
@Entity
@Table(name = "PAGOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pagos.findAll", query = "SELECT p FROM Pagos p")
    , @NamedQuery(name = "Pagos.findByPagId", query = "SELECT p FROM Pagos p WHERE p.pagId = :pagId")
    , @NamedQuery(name = "Pagos.findByPagTiempo", query = "SELECT p FROM Pagos p WHERE p.pagTiempo = :pagTiempo")
    , @NamedQuery(name = "Pagos.findByPagMonto", query = "SELECT p FROM Pagos p WHERE p.pagMonto = :pagMonto")})
public class Pagos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PAG_ID")
    private Integer pagId;
    @Basic(optional = false)
    @Column(name = "PAG_TIEMPO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pagTiempo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PAG_MONTO")
    private Double pagMonto;
    @JoinColumn(name = "CIT_ID", referencedColumnName = "CIT_ID")
    @ManyToOne
    private Citas citId;
    @JoinColumn(name = "REC_ID", referencedColumnName = "REC_ID")
    @ManyToOne
    private Recepcionista recId;

    public Pagos() {
    }

    public Pagos(Integer pagId) {
        this.pagId = pagId;
    }

    public Pagos(Integer pagId, Date pagTiempo) {
        this.pagId = pagId;
        this.pagTiempo = pagTiempo;
    }

    public Integer getPagId() {
        return pagId;
    }

    public void setPagId(Integer pagId) {
        this.pagId = pagId;
    }

    public Date getPagTiempo() {
        return pagTiempo;
    }

    public void setPagTiempo(Date pagTiempo) {
        this.pagTiempo = pagTiempo;
    }

    public Double getPagMonto() {
        return pagMonto;
    }

    public void setPagMonto(Double pagMonto) {
        this.pagMonto = pagMonto;
    }

    public Citas getCitId() {
        return citId;
    }

    public void setCitId(Citas citId) {
        this.citId = citId;
    }

    public Recepcionista getRecId() {
        return recId;
    }

    public void setRecId(Recepcionista recId) {
        this.recId = recId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pagId != null ? pagId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pagos)) {
            return false;
        }
        Pagos other = (Pagos) object;
        if ((this.pagId == null && other.pagId != null) || (this.pagId != null && !this.pagId.equals(other.pagId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.integrador.POJO.Pagos[ pagId=" + pagId + " ]";
    }
    
}
