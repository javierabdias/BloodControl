/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.integrador.POJO;

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
@Table(name = "STATUS_PAG")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StatusPag.findAll", query = "SELECT s FROM StatusPag s")
    , @NamedQuery(name = "StatusPag.findByStapId", query = "SELECT s FROM StatusPag s WHERE s.stapId = :stapId")
    , @NamedQuery(name = "StatusPag.findByStatus", query = "SELECT s FROM StatusPag s WHERE s.status = :status")})
public class StatusPag implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "STAP_ID")
    private Integer stapId;
    @Column(name = "STATUS")
    private String status;
    @OneToMany(mappedBy = "stapId")
    private Collection<Citas> citasCollection;

    public StatusPag() {
    }

    public StatusPag(Integer stapId) {
        this.stapId = stapId;
    }

    public Integer getStapId() {
        return stapId;
    }

    public void setStapId(Integer stapId) {
        this.stapId = stapId;
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
        hash += (stapId != null ? stapId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StatusPag)) {
            return false;
        }
        StatusPag other = (StatusPag) object;
        if ((this.stapId == null && other.stapId != null) || (this.stapId != null && !this.stapId.equals(other.stapId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.integrador.POJO.StatusPag[ stapId=" + stapId + " ]";
    }
    
}
