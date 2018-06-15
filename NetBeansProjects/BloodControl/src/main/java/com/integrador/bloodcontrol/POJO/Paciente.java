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
    @Column(name = "PAC_CE")
    private String pacCe;
    @Column(name = "PAC_CONTRA")
    private String pacContra;
    @Column(name = "PAC_TEL")
    private String pacTel;
    @Column(name = "PAC_CEL")
    private String pacCel;
    @OneToMany(mappedBy = "pacId")
    private Collection<Citas> citasCollection;
    @JoinColumn(name = "ER_ID", referencedColumnName = "ER_ID")
    @ManyToOne
    private EstadoRegistro erId;
    @JoinColumn(name = "PER_ID", referencedColumnName = "PER_ID")
    @ManyToOne
    private Persona perId;

    public Paciente() {
    }

    public Paciente(Integer pacId) {
        this.pacId = pacId;
    }

    public Integer getPacId() {
        return pacId;
    }

    public void setPacId(Integer pacId) {
        this.pacId = pacId;
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

    public Persona getPerId() {
        return perId;
    }

    public void setPerId(Persona perId) {
        this.perId = perId;
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
        return "com.integrador.bloodcontrol.POJO.Paciente[ pacId=" + pacId + " ]";
    }
    
}
