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
@Table(name = "STATUS_EXA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StatusExa.findAll", query = "SELECT s FROM StatusExa s")
    , @NamedQuery(name = "StatusExa.findByStaeId", query = "SELECT s FROM StatusExa s WHERE s.staeId = :staeId")
    , @NamedQuery(name = "StatusExa.findByStatus", query = "SELECT s FROM StatusExa s WHERE s.status = :status")})
public class StatusExa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "STAE_ID")
    private String staeId;
    @Column(name = "STATUS")
    private String status;
    @OneToMany(mappedBy = "staeId")
    private Collection<Citas> citasCollection;

    public StatusExa() {
    }

    public StatusExa(String staeId) {
        this.staeId = staeId;
    }

    public String getStaeId() {
        return staeId;
    }

    public void setStaeId(String staeId) {
        this.staeId = staeId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @XmlTransient
    public Collection<Citas> getCitasCollection() {
        return citasCollection;
    }

    public void setCitasCollection(Collection<Citas> citasCollection) {
        this.citasCollection = citasCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (staeId != null ? staeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StatusExa)) {
            return false;
        }
        StatusExa other = (StatusExa) object;
        if ((this.staeId == null && other.staeId != null) || (this.staeId != null && !this.staeId.equals(other.staeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.integrador.POJO.StatusExa[ staeId=" + staeId + " ]";
    }
    
}
