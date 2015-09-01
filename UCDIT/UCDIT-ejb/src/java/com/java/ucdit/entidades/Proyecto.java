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
@Table(name = "proyecto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proyecto.findAll", query = "SELECT p FROM Proyecto p"),
    @NamedQuery(name = "Proyecto.findByIdproyecto", query = "SELECT p FROM Proyecto p WHERE p.idproyecto = :idproyecto"),
    @NamedQuery(name = "Proyecto.findByFechainicio", query = "SELECT p FROM Proyecto p WHERE p.fechainicio = :fechainicio"),
    @NamedQuery(name = "Proyecto.findByFechafindeseada", query = "SELECT p FROM Proyecto p WHERE p.fechafindeseada = :fechafindeseada"),
    @NamedQuery(name = "Proyecto.findByFechafinreal", query = "SELECT p FROM Proyecto p WHERE p.fechafinreal = :fechafinreal"),
    @NamedQuery(name = "Proyecto.findByDescripcionproyecto", query = "SELECT p FROM Proyecto p WHERE p.descripcionproyecto = :descripcionproyecto"),
    @NamedQuery(name = "Proyecto.findByCostoproyecto", query = "SELECT p FROM Proyecto p WHERE p.costoproyecto = :costoproyecto"),
    @NamedQuery(name = "Proyecto.findByCostoinversion", query = "SELECT p FROM Proyecto p WHERE p.costoinversion = :costoinversion"),
    @NamedQuery(name = "Proyecto.findByEstadoproyecto", query = "SELECT p FROM Proyecto p WHERE p.estadoproyecto = :estadoproyecto")})
public class Proyecto implements Serializable {

    private static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idproyecto")
    private BigInteger idproyecto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "nombreproyecto")
    private String nombreproyecto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fechainicio")
    @Temporal(TemporalType.DATE)
    private Date fechainicio;
    @Column(name = "fechafindeseada")
    @Temporal(TemporalType.DATE)
    private Date fechafindeseada;
    @Column(name = "fechafinreal")
    @Temporal(TemporalType.DATE)
    private Date fechafinreal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "descripcionproyecto")
    private String descripcionproyecto;
    @Column(name = "costoproyecto")
    private Integer costoproyecto;
    @Column(name = "costoinversion")
    private Integer costoinversion;
    @Column(name = "estadoproyecto")
    private Boolean estadoproyecto;
    @JoinColumn(name = "supervisor", referencedColumnName = "idsupervisor")
    @ManyToOne(optional = false)
    private Supervisor supervisor;
    @JoinColumn(name = "cliente", referencedColumnName = "idcliente")
    @ManyToOne(optional = false)
    private Cliente cliente;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proyecto")
    private Collection<GastoAdicional> gastoAdicionalCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proyecto")
    private Collection<EquipoPorProyecto> equipoPorProyectoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proyecto")
    private Collection<InsumoPorProyecto> insumoPorProyectoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proyecto")
    private Collection<ActaReunion> actaReunionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proyecto")
    private Collection<PersonalPorProyecto> personalPorProyectoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proyecto")
    private Collection<BitacoraProyecto> bitacoraProyectoCollection;
    @Transient
    private String strEstado;

    public Proyecto() {
    }

    public Proyecto(BigInteger idproyecto) {
        this.idproyecto = idproyecto;
    }

    public Proyecto(BigInteger idproyecto, Date fechainicio, String descripcionproyecto) {
        this.idproyecto = idproyecto;
        this.fechainicio = fechainicio;
        this.descripcionproyecto = descripcionproyecto;
    }

    public BigInteger getIdproyecto() {
        return idproyecto;
    }

    public void setIdproyecto(BigInteger idproyecto) {
        this.idproyecto = idproyecto;
    }

    public Date getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(Date fechainicio) {
        this.fechainicio = fechainicio;
    }

    public Date getFechafindeseada() {
        return fechafindeseada;
    }

    public void setFechafindeseada(Date fechafindeseada) {
        this.fechafindeseada = fechafindeseada;
    }

    public Date getFechafinreal() {
        return fechafinreal;
    }

    public void setFechafinreal(Date fechafinreal) {
        this.fechafinreal = fechafinreal;
    }

    public String getDescripcionproyecto() {
        if (null != descripcionproyecto) {
            return descripcionproyecto.toUpperCase();
        }
        return descripcionproyecto;
    }

    public void setDescripcionproyecto(String descripcionproyecto) {
        this.descripcionproyecto = descripcionproyecto.toUpperCase();
    }

    public Integer getCostoproyecto() {
        return costoproyecto;
    }

    public void setCostoproyecto(Integer costoproyecto) {
        this.costoproyecto = costoproyecto;
    }

    public Integer getCostoinversion() {
        return costoinversion;
    }

    public void setCostoinversion(Integer costoinversion) {
        this.costoinversion = costoinversion;
    }

    public Boolean getEstadoproyecto() {
        return estadoproyecto;
    }

    public void setEstadoproyecto(Boolean estadoproyecto) {
        this.estadoproyecto = estadoproyecto;
    }

    public String getStrEstado() {
        getEstadoproyecto();
        if (null != estadoproyecto) {
            if (estadoproyecto == true) {
                strEstado = "ACTIVO";
            } else {
                strEstado = "FINALIZADO";
            }
        }
        return strEstado;
    }

    public void setStrEstado(String strEstado) {
        this.strEstado = strEstado;
    }

    public Supervisor getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Supervisor supervisor) {
        this.supervisor = supervisor;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @XmlTransient
    public Collection<GastoAdicional> getGastoAdicionalCollection() {
        return gastoAdicionalCollection;
    }

    public void setGastoAdicionalCollection(Collection<GastoAdicional> gastoAdicionalCollection) {
        this.gastoAdicionalCollection = gastoAdicionalCollection;
    }

    @XmlTransient
    public Collection<EquipoPorProyecto> getEquipoPorProyectoCollection() {
        return equipoPorProyectoCollection;
    }

    public void setEquipoPorProyectoCollection(Collection<EquipoPorProyecto> equipoPorProyectoCollection) {
        this.equipoPorProyectoCollection = equipoPorProyectoCollection;
    }

    @XmlTransient
    public Collection<InsumoPorProyecto> getInsumoPorProyectoCollection() {
        return insumoPorProyectoCollection;
    }

    public void setInsumoPorProyectoCollection(Collection<InsumoPorProyecto> insumoPorProyectoCollection) {
        this.insumoPorProyectoCollection = insumoPorProyectoCollection;
    }

    @XmlTransient
    public Collection<ActaReunion> getActaReunionCollection() {
        return actaReunionCollection;
    }

    public void setActaReunionCollection(Collection<ActaReunion> actaReunionCollection) {
        this.actaReunionCollection = actaReunionCollection;
    }

    @XmlTransient
    public Collection<PersonalPorProyecto> getPersonalPorProyectoCollection() {
        return personalPorProyectoCollection;
    }

    public void setPersonalPorProyectoCollection(Collection<PersonalPorProyecto> personalPorProyectoCollection) {
        this.personalPorProyectoCollection = personalPorProyectoCollection;
    }

    @XmlTransient
    public Collection<BitacoraProyecto> getBitacoraProyectoCollection() {
        return bitacoraProyectoCollection;
    }

    public void setBitacoraProyectoCollection(Collection<BitacoraProyecto> bitacoraProyectoCollection) {
        this.bitacoraProyectoCollection = bitacoraProyectoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idproyecto != null ? idproyecto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proyecto)) {
            return false;
        }
        Proyecto other = (Proyecto) object;
        if ((this.idproyecto == null && other.idproyecto != null) || (this.idproyecto != null && !this.idproyecto.equals(other.idproyecto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.java.ucdit.entidades.Proyecto[ idproyecto=" + idproyecto + " ]";
    }

    public String getNombreproyecto() {
        if(null != nombreproyecto){
            return nombreproyecto.toUpperCase();
        }
        return nombreproyecto;
    }

    public void setNombreproyecto(String nombreproyecto) {
        this.nombreproyecto = nombreproyecto.toUpperCase();
    }

}
