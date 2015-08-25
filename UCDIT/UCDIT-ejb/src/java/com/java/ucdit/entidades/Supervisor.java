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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ELECTRONICA
 */
@Entity
@Table(name = "supervisor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Supervisor.findAll", query = "SELECT s FROM Supervisor s"),
    @NamedQuery(name = "Supervisor.findByIdsupervisor", query = "SELECT s FROM Supervisor s WHERE s.idsupervisor = :idsupervisor"),
    @NamedQuery(name = "Supervisor.findByAreaenfoque", query = "SELECT s FROM Supervisor s WHERE s.areaenfoque = :areaenfoque")})
public class Supervisor implements Serializable {
    private static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idsupervisor")
    private BigInteger idsupervisor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "areaenfoque")
    private String areaenfoque;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "supervisor")
    private Collection<Proyecto> proyectoCollection;
    @JoinColumn(name = "persona", referencedColumnName = "idpersona")
    @ManyToOne(optional = false)
    private Persona persona;

    public Supervisor() {
    }

    public Supervisor(BigInteger idsupervisor) {
        this.idsupervisor = idsupervisor;
    }

    public Supervisor(BigInteger idsupervisor, String areaenfoque) {
        this.idsupervisor = idsupervisor;
        this.areaenfoque = areaenfoque;
    }

    public BigInteger getIdsupervisor() {
        return idsupervisor;
    }

    public void setIdsupervisor(BigInteger idsupervisor) {
        this.idsupervisor = idsupervisor;
    }

    public String getAreaenfoque() {
        return areaenfoque;
    }

    public void setAreaenfoque(String areaenfoque) {
        this.areaenfoque = areaenfoque;
    }

    @XmlTransient
    public Collection<Proyecto> getProyectoCollection() {
        return proyectoCollection;
    }

    public void setProyectoCollection(Collection<Proyecto> proyectoCollection) {
        this.proyectoCollection = proyectoCollection;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsupervisor != null ? idsupervisor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Supervisor)) {
            return false;
        }
        Supervisor other = (Supervisor) object;
        if ((this.idsupervisor == null && other.idsupervisor != null) || (this.idsupervisor != null && !this.idsupervisor.equals(other.idsupervisor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.java.ucdit.entidades.Supervisor[ idsupervisor=" + idsupervisor + " ]";
    }
    
}
