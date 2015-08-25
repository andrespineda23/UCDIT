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
@Table(name = "tipounidad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipouUnidad.findAll", query = "SELECT t FROM TipouUnidad t"),
    @NamedQuery(name = "TipouUnidad.findByIdtipounidad", query = "SELECT t FROM TipouUnidad t WHERE t.idtipounidad = :idtipounidad"),
    @NamedQuery(name = "TipouUnidad.findByNombretipounidad", query = "SELECT t FROM TipouUnidad t WHERE t.nombretipounidad = :nombretipounidad")})
public class TipouUnidad implements Serializable {
    private static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtipounidad")
    private BigInteger idtipounidad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombretipounidad")
    private String nombretipounidad;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipounidad")
    private Collection<Insumo> insumoCollection;

    public TipouUnidad() {
    }

    public TipouUnidad(BigInteger idtipounidad) {
        this.idtipounidad = idtipounidad;
    }

    public TipouUnidad(BigInteger idtipounidad, String nombretipounidad) {
        this.idtipounidad = idtipounidad;
        this.nombretipounidad = nombretipounidad;
    }

    public BigInteger getIdtipounidad() {
        return idtipounidad;
    }

    public void setIdtipounidad(BigInteger idtipounidad) {
        this.idtipounidad = idtipounidad;
    }

    public String getNombretipounidad() {
        return nombretipounidad;
    }

    public void setNombretipounidad(String nombretipounidad) {
        this.nombretipounidad = nombretipounidad;
    }

    @XmlTransient
    public Collection<Insumo> getInsumoCollection() {
        return insumoCollection;
    }

    public void setInsumoCollection(Collection<Insumo> insumoCollection) {
        this.insumoCollection = insumoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtipounidad != null ? idtipounidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipouUnidad)) {
            return false;
        }
        TipouUnidad other = (TipouUnidad) object;
        if ((this.idtipounidad == null && other.idtipounidad != null) || (this.idtipounidad != null && !this.idtipounidad.equals(other.idtipounidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.java.ucdit.entidades.TipouUnidad[ idtipounidad=" + idtipounidad + " ]";
    }
    
}
