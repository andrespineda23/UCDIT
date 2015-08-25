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
@Table(name = "hojadevida")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HojaDeVida.findAll", query = "SELECT h FROM HojaDeVida h"),
    @NamedQuery(name = "HojaDeVida.findByIdhojadevida", query = "SELECT h FROM HojaDeVida h WHERE h.idhojadevida = :idhojadevida"),
    @NamedQuery(name = "HojaDeVida.findByFecharegistro", query = "SELECT h FROM HojaDeVida h WHERE h.fecharegistro = :fecharegistro"),
    @NamedQuery(name = "HojaDeVida.findByDescripcion", query = "SELECT h FROM HojaDeVida h WHERE h.descripcion = :descripcion")})
public class HojaDeVida implements Serializable {
    private static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idhojadevida")
    private BigInteger idhojadevida;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fecharegistro")
    @Temporal(TemporalType.DATE)
    private Date fecharegistro;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "descripcion")
    private String descripcion;
    @JoinColumn(name = "equipotecnologico", referencedColumnName = "idequipotecnologico")
    @ManyToOne(optional = false)
    private EquipoTecnologico equipotecnologico;

    public HojaDeVida() {
    }

    public HojaDeVida(BigInteger idhojadevida) {
        this.idhojadevida = idhojadevida;
    }

    public HojaDeVida(BigInteger idhojadevida, Date fecharegistro, String descripcion) {
        this.idhojadevida = idhojadevida;
        this.fecharegistro = fecharegistro;
        this.descripcion = descripcion;
    }

    public BigInteger getIdhojadevida() {
        return idhojadevida;
    }

    public void setIdhojadevida(BigInteger idhojadevida) {
        this.idhojadevida = idhojadevida;
    }

    public Date getFecharegistro() {
        return fecharegistro;
    }

    public void setFecharegistro(Date fecharegistro) {
        this.fecharegistro = fecharegistro;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
        hash += (idhojadevida != null ? idhojadevida.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HojaDeVida)) {
            return false;
        }
        HojaDeVida other = (HojaDeVida) object;
        if ((this.idhojadevida == null && other.idhojadevida != null) || (this.idhojadevida != null && !this.idhojadevida.equals(other.idhojadevida))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.java.ucdit.entidades.HojaDeVida[ idhojadevida=" + idhojadevida + " ]";
    }
    
}
