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
@Table(name = "gastoadicional")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "GastoAdicional.findAll", query = "SELECT g FROM GastoAdicional g"),
    @NamedQuery(name = "GastoAdicional.findByIdgastoadicional", query = "SELECT g FROM GastoAdicional g WHERE g.idgastoadicional = :idgastoadicional"),
    @NamedQuery(name = "GastoAdicional.findByFechagasto", query = "SELECT g FROM GastoAdicional g WHERE g.fechagasto = :fechagasto"),
    @NamedQuery(name = "GastoAdicional.findByDescripcion", query = "SELECT g FROM GastoAdicional g WHERE g.descripcion = :descripcion"),
    @NamedQuery(name = "GastoAdicional.findByValorgasto", query = "SELECT g FROM GastoAdicional g WHERE g.valorgasto = :valorgasto")})
public class GastoAdicional implements Serializable {

    private static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idgastoadicional")
    private BigInteger idgastoadicional;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechagasto")
    @Temporal(TemporalType.DATE)
    private Date fechagasto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valorgasto")
    private int valorgasto;
    @JoinColumn(name = "proyecto", referencedColumnName = "idproyecto")
    @ManyToOne(optional = false)
    private Proyecto proyecto;

    public GastoAdicional() {
    }

    public GastoAdicional(BigInteger idgastoadicional) {
        this.idgastoadicional = idgastoadicional;
    }

    public GastoAdicional(BigInteger idgastoadicional, Date fechagasto, String descripcion, int valorgasto) {
        this.idgastoadicional = idgastoadicional;
        this.fechagasto = fechagasto;
        this.descripcion = descripcion;
        this.valorgasto = valorgasto;
    }

    public BigInteger getIdgastoadicional() {
        return idgastoadicional;
    }

    public void setIdgastoadicional(BigInteger idgastoadicional) {
        this.idgastoadicional = idgastoadicional;
    }

    public Date getFechagasto() {
        return fechagasto;
    }

    public void setFechagasto(Date fechagasto) {
        this.fechagasto = fechagasto;
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

    public int getValorgasto() {
        return valorgasto;
    }

    public void setValorgasto(int valorgasto) {
        this.valorgasto = valorgasto;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idgastoadicional != null ? idgastoadicional.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GastoAdicional)) {
            return false;
        }
        GastoAdicional other = (GastoAdicional) object;
        if ((this.idgastoadicional == null && other.idgastoadicional != null) || (this.idgastoadicional != null && !this.idgastoadicional.equals(other.idgastoadicional))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.java.ucdit.entidades.GastoAdicional[ idgastoadicional=" + idgastoadicional + " ]";
    }

}
