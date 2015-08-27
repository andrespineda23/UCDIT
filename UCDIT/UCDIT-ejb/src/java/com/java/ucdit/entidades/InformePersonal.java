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
@Table(name = "informepersonal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InformePersonal.findAll", query = "SELECT i FROM InformePersonal i"),
    @NamedQuery(name = "InformePersonal.findByIdinformepersonal", query = "SELECT i FROM InformePersonal i WHERE i.idinformepersonal = :idinformepersonal"),
    @NamedQuery(name = "InformePersonal.findByFechainforme", query = "SELECT i FROM InformePersonal i WHERE i.fechainforme = :fechainforme"),
    @NamedQuery(name = "InformePersonal.findByMesinforme", query = "SELECT i FROM InformePersonal i WHERE i.mesinforme = :mesinforme"),
    @NamedQuery(name = "InformePersonal.findByNombrearchivo", query = "SELECT i FROM InformePersonal i WHERE i.nombrearchivo = :nombrearchivo"),
    @NamedQuery(name = "InformePersonal.findByUbicacion", query = "SELECT i FROM InformePersonal i WHERE i.ubicacion = :ubicacion")})
public class InformePersonal implements Serializable {

    private static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idinformepersonal")
    private BigInteger idinformepersonal;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechainforme")
    @Temporal(TemporalType.DATE)
    private Date fechainforme;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "mesinforme")
    private String mesinforme;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombrearchivo")
    private String nombrearchivo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "ubicacion")
    private String ubicacion;
    @JoinColumn(name = "personalinterno", referencedColumnName = "idpersonalinterno")
    @ManyToOne(optional = false)
    private PersonalInterno personalinterno;

    public InformePersonal() {
    }

    public InformePersonal(BigInteger idinformepersonal) {
        this.idinformepersonal = idinformepersonal;
    }

    public InformePersonal(BigInteger idinformepersonal, Date fechainforme, String mesinforme, String nombrearchivo, String ubicacion) {
        this.idinformepersonal = idinformepersonal;
        this.fechainforme = fechainforme;
        this.mesinforme = mesinforme;
        this.nombrearchivo = nombrearchivo;
        this.ubicacion = ubicacion;
    }

    public BigInteger getIdinformepersonal() {
        return idinformepersonal;
    }

    public void setIdinformepersonal(BigInteger idinformepersonal) {
        this.idinformepersonal = idinformepersonal;
    }

    public Date getFechainforme() {
        return fechainforme;
    }

    public void setFechainforme(Date fechainforme) {
        this.fechainforme = fechainforme;
    }

    public String getMesinforme() {
        if (null != mesinforme) {
            return mesinforme.toUpperCase();
        }
        return mesinforme;
    }

    public void setMesinforme(String mesinforme) {
        this.mesinforme = mesinforme.toUpperCase();
    }

    public String getNombrearchivo() {
        if (null != nombrearchivo) {
            return nombrearchivo.toUpperCase();
        }
        return nombrearchivo;
    }

    public void setNombrearchivo(String nombrearchivo) {
        this.nombrearchivo = nombrearchivo.toUpperCase();
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public PersonalInterno getPersonalinterno() {
        return personalinterno;
    }

    public void setPersonalinterno(PersonalInterno personalinterno) {
        this.personalinterno = personalinterno;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idinformepersonal != null ? idinformepersonal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InformePersonal)) {
            return false;
        }
        InformePersonal other = (InformePersonal) object;
        if ((this.idinformepersonal == null && other.idinformepersonal != null) || (this.idinformepersonal != null && !this.idinformepersonal.equals(other.idinformepersonal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.java.ucdit.entidades.InformePersonal[ idinformepersonal=" + idinformepersonal + " ]";
    }

}
