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

/**
 *
 * @author abdias
 */
@Entity
@Table(name = "LABORATORISTA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Laboratorista.findAll", query = "SELECT l FROM Laboratorista l")
    , @NamedQuery(name = "Laboratorista.findByLabId", query = "SELECT l FROM Laboratorista l WHERE l.labId = :labId")
    , @NamedQuery(name = "Laboratorista.findByLabCe", query = "SELECT l FROM Laboratorista l WHERE l.labCe = :labCe")
    , @NamedQuery(name = "Laboratorista.findByLabContra", query = "SELECT l FROM Laboratorista l WHERE l.labContra = :labContra")
    , @NamedQuery(name = "Laboratorista.findByLabCed", query = "SELECT l FROM Laboratorista l WHERE l.labCed = :labCed")
    , @NamedQuery(name = "Laboratorista.findByLabTel", query = "SELECT l FROM Laboratorista l WHERE l.labTel = :labTel")
    , @NamedQuery(name = "Laboratorista.findByLabCel", query = "SELECT l FROM Laboratorista l WHERE l.labCel = :labCel")})
public class Laboratorista implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "LAB_ID")
    private Integer labId;
    @Column(name = "LAB_CE")
    private String labCe;
    @Column(name = "LAB_CONTRA")
    private String labContra;
    @Column(name = "LAB_CED")
    private String labCed;
    @Column(name = "LAB_TEL")
    private String labTel;
    @Column(name = "LAB_CEL")
    private String labCel;
    @JoinColumn(name = "ER_ID", referencedColumnName = "ER_ID")
    @ManyToOne
    private EstadoRegistro erId;
    @JoinColumn(name = "DIR_ID", referencedColumnName = "DIR_ID")
    @ManyToOne
    private DirUsu dirId;
    @JoinColumn(name = "PER_ID", referencedColumnName = "PER_ID")
    @ManyToOne
    private Persona perId;
    @OneToMany (mappedBy = "primaryKey.labId")
    private Collection <Resultados> resultados;

    public Collection<Resultados> getResultados() {
        return resultados;
    }
    
    

    public void setResultados(Collection<Resultados> resultados) {
        this.resultados = resultados;
    }


    public Laboratorista() {
    }

    public Laboratorista(Integer labId) {
        this.labId = labId;
    }

    public Integer getLabId() {
        return labId;
    }

    public void setLabId(Integer labId) {
        this.labId = labId;
    }

    public String getLabCe() {
        return labCe;
    }

    public void setLabCe(String labCe) {
        this.labCe = labCe;
    }

    public String getLabContra() {
        return labContra;
    }

    public void setLabContra(String labContra) {
        this.labContra = labContra;
    }

    public String getLabCed() {
        return labCed;
    }

    public void setLabCed(String labCed) {
        this.labCed = labCed;
    }

    public String getLabTel() {
        return labTel;
    }

    public void setLabTel(String labTel) {
        this.labTel = labTel;
    }

    public String getLabCel() {
        return labCel;
    }

    public void setLabCel(String labCel) {
        this.labCel = labCel;
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
        hash += (labId != null ? labId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Laboratorista)) {
            return false;
        }
        Laboratorista other = (Laboratorista) object;
        if ((this.labId == null && other.labId != null) || (this.labId != null && !this.labId.equals(other.labId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.integrador.bloodcontrol.POJO.Laboratorista[ labId=" + labId + " ]";
    }
    
}
