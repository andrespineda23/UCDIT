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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "insumo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Insumo.findAll", query = "SELECT i FROM Insumo i"),
    @NamedQuery(name = "Insumo.findByIdinsumo", query = "SELECT i FROM Insumo i WHERE i.idinsumo = :idinsumo"),
    @NamedQuery(name = "Insumo.findByNombreinsumo", query = "SELECT i FROM Insumo i WHERE i.nombreinsumo = :nombreinsumo"),
    @NamedQuery(name = "Insumo.findByCodigoinsumo", query = "SELECT i FROM Insumo i WHERE i.codigoinsumo = :codigoinsumo"),
    @NamedQuery(name = "Insumo.findByCantidadminima", query = "SELECT i FROM Insumo i WHERE i.cantidadminima = :cantidadminima"),
    @NamedQuery(name = "Insumo.findByCantidadexistencia", query = "SELECT i FROM Insumo i WHERE i.cantidadexistencia = :cantidadexistencia"),
    @NamedQuery(name = "Insumo.findByCostocompra", query = "SELECT i FROM Insumo i WHERE i.costocompra = :costocompra"),
    @NamedQuery(name = "Insumo.findByDescripcion", query = "SELECT i FROM Insumo i WHERE i.descripcion = :descripcion")})
public class Insumo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idinsumo")
    private BigInteger idinsumo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "nombreinsumo")
    private String nombreinsumo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "codigoinsumo")
    private String codigoinsumo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidadminima")
    private int cantidadminima;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidadexistencia")
    private int cantidadexistencia;
    @Column(name = "costocompra")
    private Integer costocompra;
    @Size(max = 256)
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "insumo")
    private Collection<IngresoInsumo> ingresoInsumoCollection;
    @JoinColumn(name = "tipounidad", referencedColumnName = "idtipounidad")
    @ManyToOne(optional = false)
    private TipoUnidad tipounidad;

    public Insumo() {
    }

    public Insumo(BigInteger idinsumo) {
        this.idinsumo = idinsumo;
    }

    public Insumo(BigInteger idinsumo, String nombreinsumo, String codigoinsumo, int cantidadminima, int cantidadexistencia) {
        this.idinsumo = idinsumo;
        this.nombreinsumo = nombreinsumo;
        this.codigoinsumo = codigoinsumo;
        this.cantidadminima = cantidadminima;
        this.cantidadexistencia = cantidadexistencia;
    }

    public BigInteger getIdinsumo() {
        return idinsumo;
    }

    public void setIdinsumo(BigInteger idinsumo) {
        this.idinsumo = idinsumo;
    }

    public String getNombreinsumo() {
        if (null != nombreinsumo) {
            return nombreinsumo.toUpperCase();
        }
        return nombreinsumo;
    }

    public void setNombreinsumo(String nombreinsumo) {
        this.nombreinsumo = nombreinsumo.toUpperCase();
    }

    public String getCodigoinsumo() {
        if (null != codigoinsumo) {
            return codigoinsumo.toUpperCase();
        }
        return codigoinsumo;
    }

    public void setCodigoinsumo(String codigoinsumo) {
        this.codigoinsumo = codigoinsumo.toUpperCase();
    }

    public int getCantidadminima() {
        return cantidadminima;
    }

    public void setCantidadminima(int cantidadminima) {
        this.cantidadminima = cantidadminima;
    }

    public int getCantidadexistencia() {
        return cantidadexistencia;
    }

    public void setCantidadexistencia(int cantidadexistencia) {
        this.cantidadexistencia = cantidadexistencia;
    }

    public Integer getCostocompra() {
        return costocompra;
    }

    public void setCostocompra(Integer costocompra) {
        this.costocompra = costocompra;
    }

    public String getDescripcion() {
        if (null != descripcion) {
            return descripcion.toUpperCase();
        }
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion.toUpperCase();
    }

    @XmlTransient
    public Collection<IngresoInsumo> getIngresoInsumoCollection() {
        return ingresoInsumoCollection;
    }

    public void setIngresoInsumoCollection(Collection<IngresoInsumo> ingresoInsumoCollection) {
        this.ingresoInsumoCollection = ingresoInsumoCollection;
    }

    public TipoUnidad getTipounidad() {
        return tipounidad;
    }

    public void setTipounidad(TipoUnidad tipounidad) {
        this.tipounidad = tipounidad;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idinsumo != null ? idinsumo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Insumo)) {
            return false;
        }
        Insumo other = (Insumo) object;
        if ((this.idinsumo == null && other.idinsumo != null) || (this.idinsumo != null && !this.idinsumo.equals(other.idinsumo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.java.ucdit.entidades.Insumo[ idinsumo=" + idinsumo + " ]";
    }
    
}
