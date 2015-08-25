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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ELECTRONICA
 */
@Entity
@Table(name = "equipotecnologico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EquipoTecnologico.findAll", query = "SELECT e FROM EquipoTecnologico e"),
    @NamedQuery(name = "EquipoTecnologico.findByIdequipotecnologico", query = "SELECT e FROM EquipoTecnologico e WHERE e.idequipotecnologico = :idequipotecnologico"),
    @NamedQuery(name = "EquipoTecnologico.findByNombreequipo", query = "SELECT e FROM EquipoTecnologico e WHERE e.nombreequipo = :nombreequipo"),
    @NamedQuery(name = "EquipoTecnologico.findByCodigoequipo", query = "SELECT e FROM EquipoTecnologico e WHERE e.codigoequipo = :codigoequipo"),
    @NamedQuery(name = "EquipoTecnologico.findByFechaadquisicion", query = "SELECT e FROM EquipoTecnologico e WHERE e.fechaadquisicion = :fechaadquisicion"),
    @NamedQuery(name = "EquipoTecnologico.findByValorcompra", query = "SELECT e FROM EquipoTecnologico e WHERE e.valorcompra = :valorcompra"),
    @NamedQuery(name = "EquipoTecnologico.findByValorhorauso", query = "SELECT e FROM EquipoTecnologico e WHERE e.valorhorauso = :valorhorauso"),
    @NamedQuery(name = "EquipoTecnologico.findByDescripcion", query = "SELECT e FROM EquipoTecnologico e WHERE e.descripcion = :descripcion"),
    @NamedQuery(name = "EquipoTecnologico.findByEstadoequipo", query = "SELECT e FROM EquipoTecnologico e WHERE e.estadoequipo = :estadoequipo")})
public class EquipoTecnologico implements Serializable {
    private static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idequipotecnologico")
    private BigInteger idequipotecnologico;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "nombreequipo")
    private String nombreequipo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "codigoequipo")
    private String codigoequipo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechaadquisicion")
    @Temporal(TemporalType.DATE)
    private Date fechaadquisicion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valorcompra")
    private int valorcompra;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valorhorauso")
    private int valorhorauso;
    @Size(max = 256)
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "estadoequipo")
    private Boolean estadoequipo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "equipotecnologico")
    private Collection<EquipoPorProyecto> equipoPorProyectoCollection;
    @JoinColumn(name = "proveedor", referencedColumnName = "idproveedor")
    @ManyToOne(optional = false)
    private Proveedor proveedor;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "equipotecnologico")
    private Collection<Componente> componenteCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "equipotecnologico")
    private Collection<ManualEquipo> manualEquipoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "equipotecnologico")
    private Collection<HojaDeVida> hojaDeVidaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "equipotecnologico")
    private Collection<IngresoEquipo> ingresoEquipoCollection;

    public EquipoTecnologico() {
    }

    public EquipoTecnologico(BigInteger idequipotecnologico) {
        this.idequipotecnologico = idequipotecnologico;
    }

    public EquipoTecnologico(BigInteger idequipotecnologico, String nombreequipo, String codigoequipo, Date fechaadquisicion, int valorcompra, int valorhorauso) {
        this.idequipotecnologico = idequipotecnologico;
        this.nombreequipo = nombreequipo;
        this.codigoequipo = codigoequipo;
        this.fechaadquisicion = fechaadquisicion;
        this.valorcompra = valorcompra;
        this.valorhorauso = valorhorauso;
    }

    public BigInteger getIdequipotecnologico() {
        return idequipotecnologico;
    }

    public void setIdequipotecnologico(BigInteger idequipotecnologico) {
        this.idequipotecnologico = idequipotecnologico;
    }

    public String getNombreequipo() {
        return nombreequipo;
    }

    public void setNombreequipo(String nombreequipo) {
        this.nombreequipo = nombreequipo;
    }

    public String getCodigoequipo() {
        return codigoequipo;
    }

    public void setCodigoequipo(String codigoequipo) {
        this.codigoequipo = codigoequipo;
    }

    public Date getFechaadquisicion() {
        return fechaadquisicion;
    }

    public void setFechaadquisicion(Date fechaadquisicion) {
        this.fechaadquisicion = fechaadquisicion;
    }

    public int getValorcompra() {
        return valorcompra;
    }

    public void setValorcompra(int valorcompra) {
        this.valorcompra = valorcompra;
    }

    public int getValorhorauso() {
        return valorhorauso;
    }

    public void setValorhorauso(int valorhorauso) {
        this.valorhorauso = valorhorauso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getEstadoequipo() {
        return estadoequipo;
    }

    public void setEstadoequipo(Boolean estadoequipo) {
        this.estadoequipo = estadoequipo;
    }

    @XmlTransient
    public Collection<EquipoPorProyecto> getEquipoPorProyectoCollection() {
        return equipoPorProyectoCollection;
    }

    public void setEquipoPorProyectoCollection(Collection<EquipoPorProyecto> equipoPorProyectoCollection) {
        this.equipoPorProyectoCollection = equipoPorProyectoCollection;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    @XmlTransient
    public Collection<Componente> getComponenteCollection() {
        return componenteCollection;
    }

    public void setComponenteCollection(Collection<Componente> componenteCollection) {
        this.componenteCollection = componenteCollection;
    }

    @XmlTransient
    public Collection<ManualEquipo> getManualEquipoCollection() {
        return manualEquipoCollection;
    }

    public void setManualEquipoCollection(Collection<ManualEquipo> manualEquipoCollection) {
        this.manualEquipoCollection = manualEquipoCollection;
    }

    @XmlTransient
    public Collection<HojaDeVida> getHojaDeVidaCollection() {
        return hojaDeVidaCollection;
    }

    public void setHojaDeVidaCollection(Collection<HojaDeVida> hojaDeVidaCollection) {
        this.hojaDeVidaCollection = hojaDeVidaCollection;
    }

    @XmlTransient
    public Collection<IngresoEquipo> getIngresoEquipoCollection() {
        return ingresoEquipoCollection;
    }

    public void setIngresoEquipoCollection(Collection<IngresoEquipo> ingresoEquipoCollection) {
        this.ingresoEquipoCollection = ingresoEquipoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idequipotecnologico != null ? idequipotecnologico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EquipoTecnologico)) {
            return false;
        }
        EquipoTecnologico other = (EquipoTecnologico) object;
        if ((this.idequipotecnologico == null && other.idequipotecnologico != null) || (this.idequipotecnologico != null && !this.idequipotecnologico.equals(other.idequipotecnologico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.java.ucdit.entidades.EquipoTecnologico[ idequipotecnologico=" + idequipotecnologico + " ]";
    }
    
}
