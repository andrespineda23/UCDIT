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
@Table(name = "persona")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Persona.findAll", query = "SELECT p FROM Persona p"),
    @NamedQuery(name = "Persona.findByIdpersona", query = "SELECT p FROM Persona p WHERE p.idpersona = :idpersona"),
    @NamedQuery(name = "Persona.findByNombrepersona", query = "SELECT p FROM Persona p WHERE p.nombrepersona = :nombrepersona"),
    @NamedQuery(name = "Persona.findByApellidopersona", query = "SELECT p FROM Persona p WHERE p.apellidopersona = :apellidopersona"),
    @NamedQuery(name = "Persona.findByNumerodocumento", query = "SELECT p FROM Persona p WHERE p.numerodocumento = :numerodocumento"),
    @NamedQuery(name = "Persona.findByCorreoelectronico", query = "SELECT p FROM Persona p WHERE p.correoelectronico = :correoelectronico"),
    @NamedQuery(name = "Persona.findByNumerocelular", query = "SELECT p FROM Persona p WHERE p.numerocelular = :numerocelular"),
    @NamedQuery(name = "Persona.findByNumerofijo", query = "SELECT p FROM Persona p WHERE p.numerofijo = :numerofijo"),
    @NamedQuery(name = "Persona.findByValorhoratrabajo", query = "SELECT p FROM Persona p WHERE p.valorhoratrabajo = :valorhoratrabajo")})
public class Persona implements Serializable {
    private static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idpersona")
    private BigInteger idpersona;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombrepersona")
    private String nombrepersona;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "apellidopersona")
    private String apellidopersona;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "numerodocumento")
    private String numerodocumento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "correoelectronico")
    private String correoelectronico;
    @Size(max = 10)
    @Column(name = "numerocelular")
    private String numerocelular;
    @Size(max = 7)
    @Column(name = "numerofijo")
    private String numerofijo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "valorhoratrabajo")
    private int valorhoratrabajo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "persona")
    private Collection<PersonalInterno> personalInternoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "persona")
    private Collection<Supervisor> supervisorCollection;
    @JoinColumn(name = "usuario", referencedColumnName = "idusuario")
    @ManyToOne(optional = false)
    private Usuario usuario;

    public Persona() {
    }

    public Persona(BigInteger idpersona) {
        this.idpersona = idpersona;
    }

    public Persona(BigInteger idpersona, String nombrepersona, String apellidopersona, String numerodocumento, String correoelectronico, int valorhoratrabajo) {
        this.idpersona = idpersona;
        this.nombrepersona = nombrepersona;
        this.apellidopersona = apellidopersona;
        this.numerodocumento = numerodocumento;
        this.correoelectronico = correoelectronico;
        this.valorhoratrabajo = valorhoratrabajo;
    }

    public BigInteger getIdpersona() {
        return idpersona;
    }

    public void setIdpersona(BigInteger idpersona) {
        this.idpersona = idpersona;
    }

    public String getNombrepersona() {
        return nombrepersona;
    }

    public void setNombrepersona(String nombrepersona) {
        this.nombrepersona = nombrepersona;
    }

    public String getApellidopersona() {
        return apellidopersona;
    }

    public void setApellidopersona(String apellidopersona) {
        this.apellidopersona = apellidopersona;
    }

    public String getNumerodocumento() {
        return numerodocumento;
    }

    public void setNumerodocumento(String numerodocumento) {
        this.numerodocumento = numerodocumento;
    }

    public String getCorreoelectronico() {
        return correoelectronico;
    }

    public void setCorreoelectronico(String correoelectronico) {
        this.correoelectronico = correoelectronico;
    }

    public String getNumerocelular() {
        return numerocelular;
    }

    public void setNumerocelular(String numerocelular) {
        this.numerocelular = numerocelular;
    }

    public String getNumerofijo() {
        return numerofijo;
    }

    public void setNumerofijo(String numerofijo) {
        this.numerofijo = numerofijo;
    }

    public int getValorhoratrabajo() {
        return valorhoratrabajo;
    }

    public void setValorhoratrabajo(int valorhoratrabajo) {
        this.valorhoratrabajo = valorhoratrabajo;
    }

    @XmlTransient
    public Collection<PersonalInterno> getPersonalInternoCollection() {
        return personalInternoCollection;
    }

    public void setPersonalInternoCollection(Collection<PersonalInterno> personalInternoCollection) {
        this.personalInternoCollection = personalInternoCollection;
    }

    @XmlTransient
    public Collection<Supervisor> getSupervisorCollection() {
        return supervisorCollection;
    }

    public void setSupervisorCollection(Collection<Supervisor> supervisorCollection) {
        this.supervisorCollection = supervisorCollection;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpersona != null ? idpersona.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Persona)) {
            return false;
        }
        Persona other = (Persona) object;
        if ((this.idpersona == null && other.idpersona != null) || (this.idpersona != null && !this.idpersona.equals(other.idpersona))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.java.ucdit.entidades.Persona[ idpersona=" + idpersona + " ]";
    }
    
}
