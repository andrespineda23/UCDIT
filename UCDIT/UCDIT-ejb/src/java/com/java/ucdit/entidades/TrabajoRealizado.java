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
@Table(name = "trabajorealizado")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TrabajoRealizado.findAll", query = "SELECT t FROM TrabajoRealizado t"),
    @NamedQuery(name = "TrabajoRealizado.findByIdtrabajorealizado", query = "SELECT t FROM TrabajoRealizado t WHERE t.idtrabajorealizado = :idtrabajorealizado"),
    @NamedQuery(name = "TrabajoRealizado.findByFechareporte", query = "SELECT t FROM TrabajoRealizado t WHERE t.fechareporte = :fechareporte"),
    @NamedQuery(name = "TrabajoRealizado.findByHorastrabajadas", query = "SELECT t FROM TrabajoRealizado t WHERE t.horastrabajadas = :horastrabajadas"),
    @NamedQuery(name = "TrabajoRealizado.findByDescripcion", query = "SELECT t FROM TrabajoRealizado t WHERE t.descripcion = :descripcion"),
    @NamedQuery(name = "TrabajoRealizado.findByValornumerico", query = "SELECT t FROM TrabajoRealizado t WHERE t.valornumerico = :valornumerico")})
public class TrabajoRealizado implements Serializable {

    private static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtrabajorealizado")
    private BigInteger idtrabajorealizado;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechareporte")
    @Temporal(TemporalType.DATE)
    private Date fechareporte;
    @Basic(optional = false)
    @NotNull
    @Column(name = "horastrabajadas")
    private int horastrabajadas;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "valornumerico")
    private Integer valornumerico;
    @JoinColumn(name = "objetivoporpersonalproyecto", referencedColumnName = "idobjetivoporpersonalproyecto")
    @ManyToOne(optional = false)
    private ObjetivoPorPersonalProyecto objetivoporpersonalproyecto;

    public TrabajoRealizado() {
    }

    public TrabajoRealizado(BigInteger idtrabajorealizado) {
        this.idtrabajorealizado = idtrabajorealizado;
    }

    public TrabajoRealizado(BigInteger idtrabajorealizado, Date fechareporte, int horastrabajadas, String descripcion) {
        this.idtrabajorealizado = idtrabajorealizado;
        this.fechareporte = fechareporte;
        this.horastrabajadas = horastrabajadas;
        this.descripcion = descripcion;
    }

    public BigInteger getIdtrabajorealizado() {
        return idtrabajorealizado;
    }

    public void setIdtrabajorealizado(BigInteger idtrabajorealizado) {
        this.idtrabajorealizado = idtrabajorealizado;
    }

    public Date getFechareporte() {
        return fechareporte;
    }

    public void setFechareporte(Date fechareporte) {
        this.fechareporte = fechareporte;
    }

    public int getHorastrabajadas() {
        return horastrabajadas;
    }

    public void setHorastrabajadas(int horastrabajadas) {
        this.horastrabajadas = horastrabajadas;
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

    public Integer getValornumerico() {
        return valornumerico;
    }

    public void setValornumerico(Integer valornumerico) {
        this.valornumerico = valornumerico;
    }

    public ObjetivoPorPersonalProyecto getObjetivoporpersonalproyecto() {
        return objetivoporpersonalproyecto;
    }

    public void setObjetivoporpersonalproyecto(ObjetivoPorPersonalProyecto objetivoporpersonalproyecto) {
        this.objetivoporpersonalproyecto = objetivoporpersonalproyecto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtrabajorealizado != null ? idtrabajorealizado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TrabajoRealizado)) {
            return false;
        }
        TrabajoRealizado other = (TrabajoRealizado) object;
        if ((this.idtrabajorealizado == null && other.idtrabajorealizado != null) || (this.idtrabajorealizado != null && !this.idtrabajorealizado.equals(other.idtrabajorealizado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.java.ucdit.entidades.TrabajoRealizado[ idtrabajorealizado=" + idtrabajorealizado + " ]";
    }

}
