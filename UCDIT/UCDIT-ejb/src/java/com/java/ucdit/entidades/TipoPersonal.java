/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.entidades;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ELECTRONICA
 */
@Entity
@Table(name = "tipopersonal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoPersonal.findAll", query = "SELECT t FROM TipoPersonal t"),
    @NamedQuery(name = "TipoPersonal.findByIdtipopersonal", query = "SELECT t FROM TipoPersonal t WHERE t.idtipopersonal = :idtipopersonal"),
    @NamedQuery(name = "TipoPersonal.findByNombretipopersonal", query = "SELECT t FROM TipoPersonal t WHERE t.nombretipopersonal = :nombretipopersonal")})
public class TipoPersonal implements Serializable {

    private static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtipopersonal")
    private BigInteger idtipopersonal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombretipopersonal")
    private String nombretipopersonal;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipopersonal")
    private Collection<PersonalInterno> personalInternoCollection;

    public TipoPersonal() {
    }

    public TipoPersonal(BigInteger idtipopersonal) {
        this.idtipopersonal = idtipopersonal;
    }

    public TipoPersonal(BigInteger idtipopersonal, String nombretipopersonal) {
        this.idtipopersonal = idtipopersonal;
        this.nombretipopersonal = nombretipopersonal;
    }

    public BigInteger getIdtipopersonal() {
        return idtipopersonal;
    }

    public void setIdtipopersonal(BigInteger idtipopersonal) {
        this.idtipopersonal = idtipopersonal;
    }

    public String getNombretipopersonal() {
        if (null != nombretipopersonal) {
            return nombretipopersonal.toUpperCase();
        }
        return nombretipopersonal;
    }

    public void setNombretipopersonal(String nombretipopersonal) {
        this.nombretipopersonal = nombretipopersonal.toUpperCase();
    }

    @XmlTransient
    public Collection<PersonalInterno> getPersonalInternoCollection() {
        return personalInternoCollection;
    }

    public void setPersonalInternoCollection(Collection<PersonalInterno> personalInternoCollection) {
        this.personalInternoCollection = personalInternoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtipopersonal != null ? idtipopersonal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoPersonal)) {
            return false;
        }
        TipoPersonal other = (TipoPersonal) object;
        if ((this.idtipopersonal == null && other.idtipopersonal != null) || (this.idtipopersonal != null && !this.idtipopersonal.equals(other.idtipopersonal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.java.ucdit.entidades.TipoPersonal[ idtipopersonal=" + idtipopersonal + " ]";
    }

}
