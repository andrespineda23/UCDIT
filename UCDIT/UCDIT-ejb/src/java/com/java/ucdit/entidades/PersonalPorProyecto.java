/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.entidades;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Collection;
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
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ELECTRONICA
 */
@Entity
@Table(name = "personalporproyecto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PersonalPorProyecto.findAll", query = "SELECT p FROM PersonalPorProyecto p"),
    @NamedQuery(name = "PersonalPorProyecto.findByIdpersonalporproyecto", query = "SELECT p FROM PersonalPorProyecto p WHERE p.idpersonalporproyecto = :idpersonalporproyecto")})
public class PersonalPorProyecto implements Serializable {

    @Column(name = "estado")
    private Boolean estado;
    private static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpersonalporproyecto")
    private BigInteger idpersonalporproyecto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personalporproyecto")
    private Collection<ObjetivoPorPersonalProyecto> objetivoPorPersonalProyectoCollection;
    @JoinColumn(name = "proyecto", referencedColumnName = "idproyecto")
    @ManyToOne(optional = false)
    private Proyecto proyecto;
    @JoinColumn(name = "personalinterno", referencedColumnName = "idpersonalinterno")
    @ManyToOne(optional = false)
    private PersonalInterno personalinterno;
    @Transient
    private String strEstado;

    public PersonalPorProyecto() {
    }

    public PersonalPorProyecto(BigInteger idpersonalporproyecto) {
        this.idpersonalporproyecto = idpersonalporproyecto;
    }

    public BigInteger getIdpersonalporproyecto() {
        return idpersonalporproyecto;
    }

    public void setIdpersonalporproyecto(BigInteger idpersonalporproyecto) {
        this.idpersonalporproyecto = idpersonalporproyecto;
    }

    @XmlTransient
    public Collection<ObjetivoPorPersonalProyecto> getObjetivoPorPersonalProyectoCollection() {
        return objetivoPorPersonalProyectoCollection;
    }

    public void setObjetivoPorPersonalProyectoCollection(Collection<ObjetivoPorPersonalProyecto> objetivoPorPersonalProyectoCollection) {
        this.objetivoPorPersonalProyectoCollection = objetivoPorPersonalProyectoCollection;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
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
        hash += (idpersonalporproyecto != null ? idpersonalporproyecto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonalPorProyecto)) {
            return false;
        }
        PersonalPorProyecto other = (PersonalPorProyecto) object;
        if ((this.idpersonalporproyecto == null && other.idpersonalporproyecto != null) || (this.idpersonalporproyecto != null && !this.idpersonalporproyecto.equals(other.idpersonalporproyecto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.java.ucdit.entidades.PersonalPorProyecto[ idpersonalporproyecto=" + idpersonalporproyecto + " ]";
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public String getStrEstado() {
        getEstado();
        if (null != estado) {
            if (estado == true) {
                strEstado = "ACTIVO";
            } else {
                strEstado = "INACTIVO";
            }
        }
        return strEstado;
    }

    public void setStrEstado(String strEstado) {
        this.strEstado = strEstado;
    }

}
