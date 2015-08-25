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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ELECTRONICA
 */
@Entity
@Table(name = "personalinterno")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PersonalInterno.findAll", query = "SELECT p FROM PersonalInterno p"),
    @NamedQuery(name = "PersonalInterno.findByIdpersonalinterno", query = "SELECT p FROM PersonalInterno p WHERE p.idpersonalinterno = :idpersonalinterno")})
public class PersonalInterno implements Serializable {
    private static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpersonalinterno")
    private BigInteger idpersonalinterno;
    @JoinColumn(name = "tipopersonal", referencedColumnName = "idtipopersonal")
    @ManyToOne(optional = false)
    private TipoPersonal tipopersonal;
    @JoinColumn(name = "persona", referencedColumnName = "idpersona")
    @ManyToOne(optional = false)
    private Persona persona;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personalinterno")
    private Collection<PersonalPorProyecto> personalPorProyectoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personalinterno")
    private Collection<InformePersonal> informePersonalCollection;

    public PersonalInterno() {
    }

    public PersonalInterno(BigInteger idpersonalinterno) {
        this.idpersonalinterno = idpersonalinterno;
    }

    public BigInteger getIdpersonalinterno() {
        return idpersonalinterno;
    }

    public void setIdpersonalinterno(BigInteger idpersonalinterno) {
        this.idpersonalinterno = idpersonalinterno;
    }

    public TipoPersonal getTipopersonal() {
        return tipopersonal;
    }

    public void setTipopersonal(TipoPersonal tipopersonal) {
        this.tipopersonal = tipopersonal;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    @XmlTransient
    public Collection<PersonalPorProyecto> getPersonalPorProyectoCollection() {
        return personalPorProyectoCollection;
    }

    public void setPersonalPorProyectoCollection(Collection<PersonalPorProyecto> personalPorProyectoCollection) {
        this.personalPorProyectoCollection = personalPorProyectoCollection;
    }

    @XmlTransient
    public Collection<InformePersonal> getInformePersonalCollection() {
        return informePersonalCollection;
    }

    public void setInformePersonalCollection(Collection<InformePersonal> informePersonalCollection) {
        this.informePersonalCollection = informePersonalCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpersonalinterno != null ? idpersonalinterno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonalInterno)) {
            return false;
        }
        PersonalInterno other = (PersonalInterno) object;
        if ((this.idpersonalinterno == null && other.idpersonalinterno != null) || (this.idpersonalinterno != null && !this.idpersonalinterno.equals(other.idpersonalinterno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.java.ucdit.entidades.PersonalInterno[ idpersonalinterno=" + idpersonalinterno + " ]";
    }
    
}
