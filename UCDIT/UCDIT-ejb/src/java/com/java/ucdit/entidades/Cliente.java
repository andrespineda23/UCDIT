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
@Table(name = "cliente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c"),
    @NamedQuery(name = "Cliente.findByIdcliente", query = "SELECT c FROM Cliente c WHERE c.idcliente = :idcliente"),
    @NamedQuery(name = "Cliente.findByNombrecliente", query = "SELECT c FROM Cliente c WHERE c.nombrecliente = :nombrecliente"),
    @NamedQuery(name = "Cliente.findByCiudad", query = "SELECT c FROM Cliente c WHERE c.ciudad = :ciudad"),
    @NamedQuery(name = "Cliente.findByDireccion", query = "SELECT c FROM Cliente c WHERE c.direccion = :direccion"),
    @NamedQuery(name = "Cliente.findByTelefonomovil", query = "SELECT c FROM Cliente c WHERE c.telefonomovil = :telefonomovil"),
    @NamedQuery(name = "Cliente.findByTelefonofijo", query = "SELECT c FROM Cliente c WHERE c.telefonofijo = :telefonofijo"),
    @NamedQuery(name = "Cliente.findByCorreoelectronico", query = "SELECT c FROM Cliente c WHERE c.correoelectronico = :correoelectronico")})
public class Cliente implements Serializable {
    private static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcliente")
    private BigInteger idcliente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 90)
    @Column(name = "nombrecliente")
    private String nombrecliente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "ciudad")
    private String ciudad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "direccion")
    private String direccion;
    @Size(max = 10)
    @Column(name = "telefonomovil")
    private String telefonomovil;
    @Size(max = 7)
    @Column(name = "telefonofijo")
    private String telefonofijo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "correoelectronico")
    private String correoelectronico;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
    private Collection<Proyecto> proyectoCollection;

    public Cliente() {
    }

    public Cliente(BigInteger idcliente) {
        this.idcliente = idcliente;
    }

    public Cliente(BigInteger idcliente, String nombrecliente, String ciudad, String direccion, String correoelectronico) {
        this.idcliente = idcliente;
        this.nombrecliente = nombrecliente;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.correoelectronico = correoelectronico;
    }

    public BigInteger getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(BigInteger idcliente) {
        this.idcliente = idcliente;
    }

    public String getNombrecliente() {
        return nombrecliente;
    }

    public void setNombrecliente(String nombrecliente) {
        this.nombrecliente = nombrecliente;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefonomovil() {
        return telefonomovil;
    }

    public void setTelefonomovil(String telefonomovil) {
        this.telefonomovil = telefonomovil;
    }

    public String getTelefonofijo() {
        return telefonofijo;
    }

    public void setTelefonofijo(String telefonofijo) {
        this.telefonofijo = telefonofijo;
    }

    public String getCorreoelectronico() {
        return correoelectronico;
    }

    public void setCorreoelectronico(String correoelectronico) {
        this.correoelectronico = correoelectronico;
    }

    @XmlTransient
    public Collection<Proyecto> getProyectoCollection() {
        return proyectoCollection;
    }

    public void setProyectoCollection(Collection<Proyecto> proyectoCollection) {
        this.proyectoCollection = proyectoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcliente != null ? idcliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.idcliente == null && other.idcliente != null) || (this.idcliente != null && !this.idcliente.equals(other.idcliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.java.ucdit.entidades.Cliente[ idcliente=" + idcliente + " ]";
    }
    
}
