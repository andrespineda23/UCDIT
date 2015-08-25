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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ELECTRONICA
 */
@Entity
@Table(name = "objetivoporpersonalproyecto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ObjetivoPorPersonalProyecto.findAll", query = "SELECT o FROM ObjetivoPorPersonalProyecto o"),
    @NamedQuery(name = "ObjetivoPorPersonalProyecto.findByIdobjetivoporpersonalproyecto", query = "SELECT o FROM ObjetivoPorPersonalProyecto o WHERE o.idobjetivoporpersonalproyecto = :idobjetivoporpersonalproyecto"),
    @NamedQuery(name = "ObjetivoPorPersonalProyecto.findByFechadeseada", query = "SELECT o FROM ObjetivoPorPersonalProyecto o WHERE o.fechadeseada = :fechadeseada"),
    @NamedQuery(name = "ObjetivoPorPersonalProyecto.findByFechafinalizacion", query = "SELECT o FROM ObjetivoPorPersonalProyecto o WHERE o.fechafinalizacion = :fechafinalizacion")})
public class ObjetivoPorPersonalProyecto implements Serializable {
    private static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idobjetivoporpersonalproyecto")
    private BigInteger idobjetivoporpersonalproyecto;
    @Column(name = "fechadeseada")
    @Temporal(TemporalType.DATE)
    private Date fechadeseada;
    @Column(name = "fechafinalizacion")
    @Temporal(TemporalType.DATE)
    private Date fechafinalizacion;
    @JoinColumn(name = "personalporproyecto", referencedColumnName = "idpersonalporproyecto")
    @ManyToOne(optional = false)
    private PersonalPorProyecto personalporproyecto;
    @JoinColumn(name = "objetivotrabajo", referencedColumnName = "idobjetivotrabajo")
    @ManyToOne(optional = false)
    private ObjetivoTrabajo objetivotrabajo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "objetivoporpersonalproyecto")
    private Collection<TrabajoRealizado> trabajoRealizadoCollection;

    public ObjetivoPorPersonalProyecto() {
    }

    public ObjetivoPorPersonalProyecto(BigInteger idobjetivoporpersonalproyecto) {
        this.idobjetivoporpersonalproyecto = idobjetivoporpersonalproyecto;
    }

    public BigInteger getIdobjetivoporpersonalproyecto() {
        return idobjetivoporpersonalproyecto;
    }

    public void setIdobjetivoporpersonalproyecto(BigInteger idobjetivoporpersonalproyecto) {
        this.idobjetivoporpersonalproyecto = idobjetivoporpersonalproyecto;
    }

    public Date getFechadeseada() {
        return fechadeseada;
    }

    public void setFechadeseada(Date fechadeseada) {
        this.fechadeseada = fechadeseada;
    }

    public Date getFechafinalizacion() {
        return fechafinalizacion;
    }

    public void setFechafinalizacion(Date fechafinalizacion) {
        this.fechafinalizacion = fechafinalizacion;
    }

    public PersonalPorProyecto getPersonalporproyecto() {
        return personalporproyecto;
    }

    public void setPersonalporproyecto(PersonalPorProyecto personalporproyecto) {
        this.personalporproyecto = personalporproyecto;
    }

    public ObjetivoTrabajo getObjetivotrabajo() {
        return objetivotrabajo;
    }

    public void setObjetivotrabajo(ObjetivoTrabajo objetivotrabajo) {
        this.objetivotrabajo = objetivotrabajo;
    }

    @XmlTransient
    public Collection<TrabajoRealizado> getTrabajoRealizadoCollection() {
        return trabajoRealizadoCollection;
    }

    public void setTrabajoRealizadoCollection(Collection<TrabajoRealizado> trabajoRealizadoCollection) {
        this.trabajoRealizadoCollection = trabajoRealizadoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idobjetivoporpersonalproyecto != null ? idobjetivoporpersonalproyecto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ObjetivoPorPersonalProyecto)) {
            return false;
        }
        ObjetivoPorPersonalProyecto other = (ObjetivoPorPersonalProyecto) object;
        if ((this.idobjetivoporpersonalproyecto == null && other.idobjetivoporpersonalproyecto != null) || (this.idobjetivoporpersonalproyecto != null && !this.idobjetivoporpersonalproyecto.equals(other.idobjetivoporpersonalproyecto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.java.ucdit.entidades.ObjetivoPorPersonalProyecto[ idobjetivoporpersonalproyecto=" + idobjetivoporpersonalproyecto + " ]";
    }
    
}
