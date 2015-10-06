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
@Table(name = "actareunion")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ActaReunion.findAll", query = "SELECT a FROM ActaReunion a"),
    @NamedQuery(name = "ActaReunion.findByIdactareunion", query = "SELECT a FROM ActaReunion a WHERE a.idactareunion = :idactareunion"),
    @NamedQuery(name = "ActaReunion.findByFechareunion", query = "SELECT a FROM ActaReunion a WHERE a.fechareunion = :fechareunion"),
    @NamedQuery(name = "ActaReunion.findByObjetivosacumplir", query = "SELECT a FROM ActaReunion a WHERE a.objetivosacumplir = :objetivosacumplir"),
    @NamedQuery(name = "ActaReunion.findByResultadoreunion", query = "SELECT a FROM ActaReunion a WHERE a.resultadoreunion = :resultadoreunion"),
    @NamedQuery(name = "ActaReunion.findByPersonalinvolucrado", query = "SELECT a FROM ActaReunion a WHERE a.personalinvolucrado = :personalinvolucrado")})
public class ActaReunion implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "ubicacionarchivo")
    private String ubicacionarchivo;

    private static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idactareunion")
    private BigInteger idactareunion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechareunion")
    @Temporal(TemporalType.DATE)
    private Date fechareunion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "objetivosacumplir")
    private String objetivosacumplir;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "resultadoreunion")
    private String resultadoreunion;
    @Size(max = 200)
    @Column(name = "personalinvolucrado")
    private String personalinvolucrado;
    @JoinColumn(name = "proyecto", referencedColumnName = "idproyecto")
    @ManyToOne(optional = false)
    private Proyecto proyecto;

    public ActaReunion() {
    }

    public ActaReunion(BigInteger idactareunion) {
        this.idactareunion = idactareunion;
    }

    public ActaReunion(BigInteger idactareunion, Date fechareunion, String objetivosacumplir, String resultadoreunion) {
        this.idactareunion = idactareunion;
        this.fechareunion = fechareunion;
        this.objetivosacumplir = objetivosacumplir;
        this.resultadoreunion = resultadoreunion;
    }

    public BigInteger getIdactareunion() {
        return idactareunion;
    }

    public void setIdactareunion(BigInteger idactareunion) {
        this.idactareunion = idactareunion;
    }

    public Date getFechareunion() {
        return fechareunion;
    }

    public void setFechareunion(Date fechareunion) {
        this.fechareunion = fechareunion;
    }

    public String getObjetivosacumplir() {
        if (null != objetivosacumplir) {
            return objetivosacumplir.toUpperCase();
        }
        return objetivosacumplir;
    }

    public void setObjetivosacumplir(String objetivosacumplir) {
        this.objetivosacumplir = objetivosacumplir.toUpperCase();
    }

    public String getResultadoreunion() {
        if (null != resultadoreunion) {
            return resultadoreunion.toUpperCase();
        }
        return resultadoreunion;
    }

    public void setResultadoreunion(String resultadoreunion) {
        this.resultadoreunion = resultadoreunion.toUpperCase();
    }

    public String getPersonalinvolucrado() {
        if (null != personalinvolucrado) {
            return personalinvolucrado.toUpperCase();
        }
        return personalinvolucrado;
    }

    public void setPersonalinvolucrado(String personalinvolucrado) {
        this.personalinvolucrado = personalinvolucrado.toUpperCase();
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
        hash += (idactareunion != null ? idactareunion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ActaReunion)) {
            return false;
        }
        ActaReunion other = (ActaReunion) object;
        if ((this.idactareunion == null && other.idactareunion != null) || (this.idactareunion != null && !this.idactareunion.equals(other.idactareunion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.java.ucdit.entidades.ActaReunion[ idactareunion=" + idactareunion + " ]";
    }

    public String getUbicacionarchivo() {
        return ubicacionarchivo;
    }

    public void setUbicacionarchivo(String ubicacionarchivo) {
        this.ubicacionarchivo = ubicacionarchivo;
    }

}
