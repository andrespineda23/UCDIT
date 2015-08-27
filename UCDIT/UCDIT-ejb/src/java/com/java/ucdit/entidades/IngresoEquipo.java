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
@Table(name = "ingresoequipo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IngresoEquipo.findAll", query = "SELECT i FROM IngresoEquipo i"),
    @NamedQuery(name = "IngresoEquipo.findByIdingresoequipo", query = "SELECT i FROM IngresoEquipo i WHERE i.idingresoequipo = :idingresoequipo"),
    @NamedQuery(name = "IngresoEquipo.findByFechacompra", query = "SELECT i FROM IngresoEquipo i WHERE i.fechacompra = :fechacompra"),
    @NamedQuery(name = "IngresoEquipo.findByNumerofactura", query = "SELECT i FROM IngresoEquipo i WHERE i.numerofactura = :numerofactura"),
    @NamedQuery(name = "IngresoEquipo.findByDescripcion", query = "SELECT i FROM IngresoEquipo i WHERE i.descripcion = :descripcion")})
public class IngresoEquipo implements Serializable {
    private static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idingresoequipo")
    private BigInteger idingresoequipo;
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
    @JoinColumn(name = "equipotecnologico", referencedColumnName = "idequipotecnologico")
    @ManyToOne(optional = false)
    private EquipoTecnologico equipotecnologico;

    public IngresoEquipo() {
    }

    public IngresoEquipo(BigInteger idingresoequipo) {
        this.idingresoequipo = idingresoequipo;
    }

    public IngresoEquipo(BigInteger idingresoequipo, Date fechacompra, String numerofactura) {
        this.idingresoequipo = idingresoequipo;
        this.fechacompra = fechacompra;
        this.numerofactura = numerofactura;
    }

    public BigInteger getIdingresoequipo() {
        return idingresoequipo;
    }

    public void setIdingresoequipo(BigInteger idingresoequipo) {
        this.idingresoequipo = idingresoequipo;
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
        if(null != descripcion){
            return descripcion.toUpperCase();
        }
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion.toUpperCase();
    }

    public EquipoTecnologico getEquipotecnologico() {
        return equipotecnologico;
    }

    public void setEquipotecnologico(EquipoTecnologico equipotecnologico) {
        this.equipotecnologico = equipotecnologico;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idingresoequipo != null ? idingresoequipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IngresoEquipo)) {
            return false;
        }
        IngresoEquipo other = (IngresoEquipo) object;
        if ((this.idingresoequipo == null && other.idingresoequipo != null) || (this.idingresoequipo != null && !this.idingresoequipo.equals(other.idingresoequipo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.java.ucdit.entidades.IngresoEquipo[ idingresoequipo=" + idingresoequipo + " ]";
    }
    
}
