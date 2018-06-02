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
@Table(name = "RECEPCIONISTA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Recepcionista.findAll", query = "SELECT r FROM Recepcionista r")
    , @NamedQuery(name = "Recepcionista.findByRecId", query = "SELECT r FROM Recepcionista r WHERE r.recId = :recId")
    , @NamedQuery(name = "Recepcionista.findByRecCe", query = "SELECT r FROM Recepcionista r WHERE r.recCe = :recCe")
    , @NamedQuery(name = "Recepcionista.findByRecContra", query = "SELECT r FROM Recepcionista r WHERE r.recContra = :recContra")
    , @NamedQuery(name = "Recepcionista.findByPacTel", query = "SELECT r FROM Recepcionista r WHERE r.pacTel = :pacTel")
    , @NamedQuery(name = "Recepcionista.findByPacCel", query = "SELECT r FROM Recepcionista r WHERE r.pacCel = :pacCel")})
public class Recepcionista implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "REC_ID")
    private Integer recId;
    @Column(name = "REC_CE")
    private String recCe;
    @Column(name = "REC_CONTRA")
    private String recContra;
    @Column(name = "PAC_TEL")
    private String pacTel;
    @Column(name = "PAC_CEL")
    private String pacCel;
    @OneToMany(mappedBy = "recId")
    private Collection<Citas> citasCollection;
    @JoinColumn(name = "ER_ID", referencedColumnName = "ER_ID")
    @ManyToOne
    private EstadoRegistro erId;
    @JoinColumn(name = "DIR_ID", referencedColumnName = "DIR_ID")
    @ManyToOne
    private DirUsu dirId;
    @JoinColumn(name = "PER_ID", referencedColumnName = "PER_ID")
    @ManyToOne
    private Persona perId;
    @OneToMany(mappedBy = "recId")
    private Collection<Pagos> pagosCollection;

    public Recepcionista() {
    }

    public Recepcionista(Integer recId) {
        this.recId = recId;
    }

    public Integer getRecId() {
        return recId;
    }

    public void setRecId(Integer recId) {
        this.recId = recId;
    }

    public String getRecCe() {
        return recCe;
    }

    public void setRecCe(String recCe) {
        this.recCe = recCe;
    }

    public String getRecContra() {
        return recContra;
    }

    public void setRecContra(String recContra) {
        this.recContra = recContra;
    }

    public String getPacTel() {
        return pacTel;
    }

    public void setPacTel(String pacTel) {
        this.pacTel = pacTel;
    }

    public String getPacCel() {
        return pacCel;
    }

    public void setPacCel(String pacCel) {
        this.pacCel = pacCel;
    }

    @XmlTransient
    public Collection<Citas> getCitasCollection() {
        return citasCollection;
    }

    public void setCitasCollection(Collection<Citas> citasCollection) {
        this.citasCollection = citasCollection;
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
        hash += (recId != null ? recId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Recepcionista)) {
            return false;
        }
        Recepcionista other = (Recepcionista) object;
        if ((this.recId == null && other.recId != null) || (this.recId != null && !this.recId.equals(other.recId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.integrador.POJO.Recepcionista[ recId=" + recId + " ]";
    }
    
}
