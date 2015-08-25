/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.entidades;

import java.io.Serializable;
import java.math.BigInteger;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ELECTRONICA
 */
@Entity
@Table(name = "manualequipo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ManualEquipo.findAll", query = "SELECT m FROM ManualEquipo m"),
    @NamedQuery(name = "ManualEquipo.findByIdmanualequipo", query = "SELECT m FROM ManualEquipo m WHERE m.idmanualequipo = :idmanualequipo"),
    @NamedQuery(name = "ManualEquipo.findByTipomanual", query = "SELECT m FROM ManualEquipo m WHERE m.tipomanual = :tipomanual"),
    @NamedQuery(name = "ManualEquipo.findByNombremanual", query = "SELECT m FROM ManualEquipo m WHERE m.nombremanual = :nombremanual"),
    @NamedQuery(name = "ManualEquipo.findByCodigomanual", query = "SELECT m FROM ManualEquipo m WHERE m.codigomanual = :codigomanual"),
    @NamedQuery(name = "ManualEquipo.findByUbicacionmanual", query = "SELECT m FROM ManualEquipo m WHERE m.ubicacionmanual = :ubicacionmanual")})
public class ManualEquipo implements Serializable {
    private static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idmanualequipo")
    private BigInteger idmanualequipo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "tipomanual")
    private String tipomanual;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombremanual")
    private String nombremanual;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "codigomanual")
    private String codigomanual;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "ubicacionmanual")
    private String ubicacionmanual;
    @JoinColumn(name = "equipotecnologico", referencedColumnName = "idequipotecnologico")
    @ManyToOne(optional = false)
    private EquipoTecnologico equipotecnologico;

    public ManualEquipo() {
    }

    public ManualEquipo(BigInteger idmanualequipo) {
        this.idmanualequipo = idmanualequipo;
    }

    public ManualEquipo(BigInteger idmanualequipo, String tipomanual, String nombremanual, String codigomanual, String ubicacionmanual) {
        this.idmanualequipo = idmanualequipo;
        this.tipomanual = tipomanual;
        this.nombremanual = nombremanual;
        this.codigomanual = codigomanual;
        this.ubicacionmanual = ubicacionmanual;
    }

    public BigInteger getIdmanualequipo() {
        return idmanualequipo;
    }

    public void setIdmanualequipo(BigInteger idmanualequipo) {
        this.idmanualequipo = idmanualequipo;
    }

    public String getTipomanual() {
        return tipomanual;
    }

    public void setTipomanual(String tipomanual) {
        this.tipomanual = tipomanual;
    }

    public String getNombremanual() {
        return nombremanual;
    }

    public void setNombremanual(String nombremanual) {
        this.nombremanual = nombremanual;
    }

    public String getCodigomanual() {
        return codigomanual;
    }

    public void setCodigomanual(String codigomanual) {
        this.codigomanual = codigomanual;
    }

    public String getUbicacionmanual() {
        return ubicacionmanual;
    }

    public void setUbicacionmanual(String ubicacionmanual) {
        this.ubicacionmanual = ubicacionmanual;
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
        hash += (idmanualequipo != null ? idmanualequipo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ManualEquipo)) {
            return false;
        }
        ManualEquipo other = (ManualEquipo) object;
        if ((this.idmanualequipo == null && other.idmanualequipo != null) || (this.idmanualequipo != null && !this.idmanualequipo.equals(other.idmanualequipo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.java.ucdit.entidades.ManualEquipo[ idmanualequipo=" + idmanualequipo + " ]";
    }
    
}
