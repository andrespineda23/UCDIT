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
@Table(name = "bitacoraproyecto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "BitacoraProyecto.findAll", query = "SELECT b FROM BitacoraProyecto b"),
    @NamedQuery(name = "BitacoraProyecto.findByIdbitacoraproyecto", query = "SELECT b FROM BitacoraProyecto b WHERE b.idbitacoraproyecto = :idbitacoraproyecto"),
    @NamedQuery(name = "BitacoraProyecto.findByDetalleevento", query = "SELECT b FROM BitacoraProyecto b WHERE b.detalleevento = :detalleevento"),
    @NamedQuery(name = "BitacoraProyecto.findByFecharegistro", query = "SELECT b FROM BitacoraProyecto b WHERE b.fecharegistro = :fecharegistro")})
public class BitacoraProyecto implements Serializable {

    private static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idbitacoraproyecto")
    private BigInteger idbitacoraproyecto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "detalleevento")
    private String detalleevento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecharegistro")
    @Temporal(TemporalType.DATE)
    private Date fecharegistro;
    @JoinColumn(name = "proyecto", referencedColumnName = "idproyecto")
    @ManyToOne(optional = false)
    private Proyecto proyecto;

    public BitacoraProyecto() {
    }

    public BitacoraProyecto(BigInteger idbitacoraproyecto) {
        this.idbitacoraproyecto = idbitacoraproyecto;
    }

    public BitacoraProyecto(BigInteger idbitacoraproyecto, String detalleevento, Date fecharegistro) {
        this.idbitacoraproyecto = idbitacoraproyecto;
        this.detalleevento = detalleevento;
        this.fecharegistro = fecharegistro;
    }

    public BigInteger getIdbitacoraproyecto() {
        return idbitacoraproyecto;
    }

    public void setIdbitacoraproyecto(BigInteger idbitacoraproyecto) {
        this.idbitacoraproyecto = idbitacoraproyecto;
    }

    public String getDetalleevento() {
        if (null != detalleevento) {
            return detalleevento.toUpperCase();
        }
        return detalleevento;
    }

    public void setDetalleevento(String detalleevento) {
        this.detalleevento = detalleevento.toUpperCase();
    }

    public Date getFecharegistro() {
        return fecharegistro;
    }

    public void setFecharegistro(Date fecharegistro) {
        this.fecharegistro = fecharegistro;
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
        hash += (idbitacoraproyecto != null ? idbitacoraproyecto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof BitacoraProyecto)) {
            return false;
        }
        BitacoraProyecto other = (BitacoraProyecto) object;
        if ((this.idbitacoraproyecto == null && other.idbitacoraproyecto != null) || (this.idbitacoraproyecto != null && !this.idbitacoraproyecto.equals(other.idbitacoraproyecto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.java.ucdit.entidades.BitacoraProyecto[ idbitacoraproyecto=" + idbitacoraproyecto + " ]";
    }

}
