/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.entidades;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ELECTRONICA
 */
@Entity
@Table(name = "objetivotrabajo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ObjetivoTrabajo.findAll", query = "SELECT o FROM ObjetivoTrabajo o"),
    @NamedQuery(name = "ObjetivoTrabajo.findByIdobjetivotrabajo", query = "SELECT o FROM ObjetivoTrabajo o WHERE o.idobjetivotrabajo = :idobjetivotrabajo"),
    @NamedQuery(name = "ObjetivoTrabajo.findByNombreobjetivo", query = "SELECT o FROM ObjetivoTrabajo o WHERE o.nombreobjetivo = :nombreobjetivo"),
    @NamedQuery(name = "ObjetivoTrabajo.findByFechacreacion", query = "SELECT o FROM ObjetivoTrabajo o WHERE o.fechacreacion = :fechacreacion"),
    @NamedQuery(name = "ObjetivoTrabajo.findByEstadoobjetivo", query = "SELECT o FROM ObjetivoTrabajo o WHERE o.estadoobjetivo = :estadoobjetivo")})
public class ObjetivoTrabajo implements Serializable {

    private static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idobjetivotrabajo")
    private BigInteger idobjetivotrabajo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "nombreobjetivo")
    private String nombreobjetivo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechacreacion")
    @Temporal(TemporalType.DATE)
    private Date fechacreacion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "estadoobjetivo")
    private boolean estadoobjetivo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "objetivotrabajo")
    private Collection<ObjetivoPorPersonalProyecto> objetivoPorPersonalProyectoCollection;
    @Transient
    private String strEstado;

    public ObjetivoTrabajo() {
    }

    public ObjetivoTrabajo(BigInteger idobjetivotrabajo) {
        this.idobjetivotrabajo = idobjetivotrabajo;
    }

    public ObjetivoTrabajo(BigInteger idobjetivotrabajo, String nombreobjetivo, Date fechacreacion, boolean estadoobjetivo) {
        this.idobjetivotrabajo = idobjetivotrabajo;
        this.nombreobjetivo = nombreobjetivo;
        this.fechacreacion = fechacreacion;
        this.estadoobjetivo = estadoobjetivo;
    }

    public BigInteger getIdobjetivotrabajo() {
        return idobjetivotrabajo;
    }

    public void setIdobjetivotrabajo(BigInteger idobjetivotrabajo) {
        this.idobjetivotrabajo = idobjetivotrabajo;
    }

    public String getNombreobjetivo() {
        if (null != nombreobjetivo) {
            return nombreobjetivo.toUpperCase();
        }
        return nombreobjetivo;
    }

    public void setNombreobjetivo(String nombreobjetivo) {
        this.nombreobjetivo = nombreobjetivo.toUpperCase();
    }

    public Date getFechacreacion() {
        return fechacreacion;
    }

    public void setFechacreacion(Date fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    public boolean getEstadoobjetivo() {
        return estadoobjetivo;
    }

    public void setEstadoobjetivo(boolean estadoobjetivo) {
        this.estadoobjetivo = estadoobjetivo;
    }

    @XmlTransient
    public Collection<ObjetivoPorPersonalProyecto> getObjetivoPorPersonalProyectoCollection() {
        return objetivoPorPersonalProyectoCollection;
    }

    public void setObjetivoPorPersonalProyectoCollection(Collection<ObjetivoPorPersonalProyecto> objetivoPorPersonalProyectoCollection) {
        this.objetivoPorPersonalProyectoCollection = objetivoPorPersonalProyectoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idobjetivotrabajo != null ? idobjetivotrabajo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ObjetivoTrabajo)) {
            return false;
        }
        ObjetivoTrabajo other = (ObjetivoTrabajo) object;
        if ((this.idobjetivotrabajo == null && other.idobjetivotrabajo != null) || (this.idobjetivotrabajo != null && !this.idobjetivotrabajo.equals(other.idobjetivotrabajo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.java.ucdit.entidades.ObjetivoTrabajo[ idobjetivotrabajo=" + idobjetivotrabajo + " ]";
    }

    public String getStrEstado() {
        getEstadoobjetivo();
        if (estadoobjetivo == true) {
            strEstado = "ACTIVO";
        } else {
            strEstado = "CERRADO";
        }
        return strEstado;
    }

    public void setStrEstado(String strEstado) {
        this.strEstado = strEstado;
    }

}
