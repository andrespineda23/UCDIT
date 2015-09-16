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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ELECTRONICA
 */
@Entity
@Table(name = "insumoporproyecto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InsumoPorProyecto.findAll", query = "SELECT i FROM InsumoPorProyecto i"),
    @NamedQuery(name = "InsumoPorProyecto.findByIdinsumoporproyecto", query = "SELECT i FROM InsumoPorProyecto i WHERE i.idinsumoporproyecto = :idinsumoporproyecto"),
    @NamedQuery(name = "InsumoPorProyecto.findByCantidadusada", query = "SELECT i FROM InsumoPorProyecto i WHERE i.cantidadusada = :cantidadusada"),
    @NamedQuery(name = "InsumoPorProyecto.findByCostouso", query = "SELECT i FROM InsumoPorProyecto i WHERE i.costouso = :costouso")})
public class InsumoPorProyecto implements Serializable {

    @Column(name = "usadoparaprototipo")
    private Boolean usadoparaprototipo;
    private static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idinsumoporproyecto")
    private BigInteger idinsumoporproyecto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "cantidadusada")
    private int cantidadusada;
    @Column(name = "costouso")
    private Integer costouso;
    @JoinColumn(name = "proyecto", referencedColumnName = "idproyecto")
    @ManyToOne(optional = false)
    private Proyecto proyecto;
    @JoinColumn(name = "insumo", referencedColumnName = "idinsumo")
    @ManyToOne(optional = false)
    private Insumo insumo;
    @Transient
    private String strPrototipo;

    public InsumoPorProyecto() {
    }

    public InsumoPorProyecto(BigInteger idinsumoporproyecto) {
        this.idinsumoporproyecto = idinsumoporproyecto;
    }

    public InsumoPorProyecto(BigInteger idinsumoporproyecto, int cantidadusada) {
        this.idinsumoporproyecto = idinsumoporproyecto;
        this.cantidadusada = cantidadusada;
    }

    public BigInteger getIdinsumoporproyecto() {
        return idinsumoporproyecto;
    }

    public void setIdinsumoporproyecto(BigInteger idinsumoporproyecto) {
        this.idinsumoporproyecto = idinsumoporproyecto;
    }

    public int getCantidadusada() {
        return cantidadusada;
    }

    public void setCantidadusada(int cantidadusada) {
        this.cantidadusada = cantidadusada;
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

    public Insumo getInsumo() {
        return insumo;
    }

    public void setInsumo(Insumo insumo) {
        this.insumo = insumo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idinsumoporproyecto != null ? idinsumoporproyecto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InsumoPorProyecto)) {
            return false;
        }
        InsumoPorProyecto other = (InsumoPorProyecto) object;
        if ((this.idinsumoporproyecto == null && other.idinsumoporproyecto != null) || (this.idinsumoporproyecto != null && !this.idinsumoporproyecto.equals(other.idinsumoporproyecto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.java.ucdit.entidades.InsumoPorProyecto[ idinsumoporproyecto=" + idinsumoporproyecto + " ]";
    }

    public Boolean getUsadoparaprototipo() {
        return usadoparaprototipo;
    }

    public void setUsadoparaprototipo(Boolean usadoparaprototipo) {
        this.usadoparaprototipo = usadoparaprototipo;
    }

    public String getStrPrototipo() {
        getUsadoparaprototipo();
        if (null != usadoparaprototipo) {
            if (usadoparaprototipo == true) {
                strPrototipo = "PROTOTIPO";
            } else {
                strPrototipo = "PROYECTO";
            }
        }
        return strPrototipo;
    }

    public void setStrPrototipo(String strPrototipo) {
        this.strPrototipo = strPrototipo;
    }

}
