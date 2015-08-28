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
@Table(name = "tipoequipo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoEquipo.findAll", query = "SELECT t FROM TipoEquipo t"),
    @NamedQuery(name = "TipoEquipo.findByIdtipoequipo", query = "SELECT t FROM TipoEquipo t WHERE t.idtipoequipo = :idtipoequipo"),
    @NamedQuery(name = "TipoEquipo.findByNombretipoequipo", query = "SELECT t FROM TipoEquipo t WHERE t.nombretipoequipo = :nombretipoequipo")})
public class TipoEquipo implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipoequipo")
    private Collection<EquipoTecnologico> equipoTecnologicoCollection;

    private static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtipoequipo")
    private BigInteger idtipoequipo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombretipoequipo")
    private String nombretipoequipo;

    public TipoEquipo() {
    }

    public TipoEquipo(BigInteger idtipoequipo) {
        this.idtipoequipo = idtipoequipo;
    }

    public TipoEquipo(BigInteger idtipoequipo, String nombretipoequipo) {
        this.idtipoequipo = idtipoequipo;
        this.nombretipoequipo = nombretipoequipo;
    }

    public BigInteger getIdtipoequipo() {
        return idtipoequipo;
    }

    public void setIdtipoequipo(BigInteger idtipoequipo) {
        this.idtipoequipo = idtipoequipo;
    }

    public String getNombretipoequipo() {
        if (null != nombretipoequipo) {
            return nombretipoequipo.toUpperCase();
        }
        return nombretipoequipo;
    }

    public void setNombretipoequipo(String nombretipoequipo) {
        this.nombretipoequipo = nombretipoequipo.toUpperCase();
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtipoequipo != null ? idtipoequipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoEquipo)) {
            return false;
        }
        TipoEquipo other = (TipoEquipo) object;
        if ((this.idtipoequipo == null && other.idtipoequipo != null) || (this.idtipoequipo != null && !this.idtipoequipo.equals(other.idtipoequipo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.java.ucdit.entidades.TipoEquipo[ idtipoequipo=" + idtipoequipo + " ]";
    }

    @XmlTransient
    public Collection<EquipoTecnologico> getEquipoTecnologicoCollection() {
        return equipoTecnologicoCollection;
    }

    public void setEquipoTecnologicoCollection(Collection<EquipoTecnologico> equipoTecnologicoCollection) {
        this.equipoTecnologicoCollection = equipoTecnologicoCollection;
    }

}
