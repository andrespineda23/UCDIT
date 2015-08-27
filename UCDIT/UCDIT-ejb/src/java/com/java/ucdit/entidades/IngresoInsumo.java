/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.entidades;

import java.io.Serializable;
import java.math.BigInteger;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ELECTRONICA
 */
@Entity
@Table(name = "ingresoinsumo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IngresoInsumo.findAll", query = "SELECT i FROM IngresoInsumo i"),
    @NamedQuery(name = "IngresoInsumo.findByIdingresoinsumo", query = "SELECT i FROM IngresoInsumo i WHERE i.idingresoinsumo = :idingresoinsumo"),
    @NamedQuery(name = "IngresoInsumo.findByFechacompra", query = "SELECT i FROM IngresoInsumo i WHERE i.fechacompra = :fechacompra"),
    @NamedQuery(name = "IngresoInsumo.findByNumerofactura", query = "SELECT i FROM IngresoInsumo i WHERE i.numerofactura = :numerofactura"),
    @NamedQuery(name = "IngresoInsumo.findByDescripcion", query = "SELECT i FROM IngresoInsumo i WHERE i.descripcion = :descripcion"),
    @NamedQuery(name = "IngresoInsumo.findByValorcompra", query = "SELECT i FROM IngresoInsumo i WHERE i.valorcompra = :valorcompra")})
public class IngresoInsumo implements Serializable {

    private static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idingresoinsumo")
    private BigInteger idingresoinsumo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechacompra")
    @Temporal(TemporalType.DATE)
    private Date fechacompra;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "numerofactura")
    private String numerofactura;
    @Size(max = 256)
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "valorcompra")
    private Integer valorcompra;
    @JoinColumn(name = "insumo", referencedColumnName = "idinsumo")
    @ManyToOne(optional = false)
    private Insumo insumo;

    public IngresoInsumo() {
    }

    public IngresoInsumo(BigInteger idingresoinsumo) {
        this.idingresoinsumo = idingresoinsumo;
    }

    public IngresoInsumo(BigInteger idingresoinsumo, Date fechacompra, String numerofactura) {
        this.idingresoinsumo = idingresoinsumo;
        this.fechacompra = fechacompra;
        this.numerofactura = numerofactura;
    }

    public BigInteger getIdingresoinsumo() {
        return idingresoinsumo;
    }

    public void setIdingresoinsumo(BigInteger idingresoinsumo) {
        this.idingresoinsumo = idingresoinsumo;
    }

    public Date getFechacompra() {
        return fechacompra;
    }

    public void setFechacompra(Date fechacompra) {
        this.fechacompra = fechacompra;
    }

    public String getNumerofactura() {
        return numerofactura;
    }

    public void setNumerofactura(String numerofactura) {
        this.numerofactura = numerofactura;
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

    public Integer getValorcompra() {
        return valorcompra;
    }

    public void setValorcompra(Integer valorcompra) {
        this.valorcompra = valorcompra;
    }

    public Insumo getInsumo() {
        return insumo;
    }

    public void setInsumo(Insumo insumo) {
        this.insumo = insumo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idingresoinsumo != null ? idingresoinsumo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IngresoInsumo)) {
            return false;
        }
        IngresoInsumo other = (IngresoInsumo) object;
        if ((this.idingresoinsumo == null && other.idingresoinsumo != null) || (this.idingresoinsumo != null && !this.idingresoinsumo.equals(other.idingresoinsumo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.java.ucdit.entidades.IngresoInsumo[ idingresoinsumo=" + idingresoinsumo + " ]";
    }

}
