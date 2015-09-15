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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ELECTRONICA
 */
@Entity
@Table(name = "equipoporproyecto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EquipoPorProyecto.findAll", query = "SELECT e FROM EquipoPorProyecto e"),
    @NamedQuery(name = "EquipoPorProyecto.findByIdequipoporproyecto", query = "SELECT e FROM EquipoPorProyecto e WHERE e.idequipoporproyecto = :idequipoporproyecto"),
    @NamedQuery(name = "EquipoPorProyecto.findByCantidadminutosuso", query = "SELECT e FROM EquipoPorProyecto e WHERE e.cantidadminutosuso = :cantidadminutosuso"),
    @NamedQuery(name = "EquipoPorProyecto.findByCostouso", query = "SELECT e FROM EquipoPorProyecto e WHERE e.costouso = :costouso")})
public class EquipoPorProyecto implements Serializable {
    @Column(name = "usadoparaprototipo")
    private Boolean usadoparaprototipo;
    private static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idequipoporproyecto")
    private BigInteger idequipoporproyecto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidadminutosuso")
    private int cantidadminutosuso;
    @Column(name = "costouso")
    private Integer costouso;
    @JoinColumn(name = "proyecto", referencedColumnName = "idproyecto")
    @ManyToOne(optional = false)
    private Proyecto proyecto;
    @JoinColumn(name = "equipotecnologico", referencedColumnName = "idequipotecnologico")
    @ManyToOne(optional = false)
    private EquipoTecnologico equipotecnologico;

    public EquipoPorProyecto() {
    }

    public EquipoPorProyecto(BigInteger idequipoporproyecto) {
        this.idequipoporproyecto = idequipoporproyecto;
    }

    public EquipoPorProyecto(BigInteger idequipoporproyecto, int cantidadminutosuso) {
        this.idequipoporproyecto = idequipoporproyecto;
        this.cantidadminutosuso = cantidadminutosuso;
    }

    public BigInteger getIdequipoporproyecto() {
        return idequipoporproyecto;
    }

    public void setIdequipoporproyecto(BigInteger idequipoporproyecto) {
        this.idequipoporproyecto = idequipoporproyecto;
    }

    public int getCantidadminutosuso() {
        return cantidadminutosuso;
    }

    public void setCantidadminutosuso(int cantidadminutosuso) {
        this.cantidadminutosuso = cantidadminutosuso;
    }

    public Integer getCostouso() {
        return costouso;
    }

    public void setCostouso(Integer costouso) {
        this.costouso = costouso;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
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
        hash += (idequipoporproyecto != null ? idequipoporproyecto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EquipoPorProyecto)) {
            return false;
        }
        EquipoPorProyecto other = (EquipoPorProyecto) object;
        if ((this.idequipoporproyecto == null && other.idequipoporproyecto != null) || (this.idequipoporproyecto != null && !this.idequipoporproyecto.equals(other.idequipoporproyecto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.java.ucdit.entidades.EquipoPorProyecto[ idequipoporproyecto=" + idequipoporproyecto + " ]";
    }

    public Boolean getUsadoparaprototipo() {
        return usadoparaprototipo;
    }

    public void setUsadoparaprototipo(Boolean usadoparaprototipo) {
        this.usadoparaprototipo = usadoparaprototipo;
    }
    
}
