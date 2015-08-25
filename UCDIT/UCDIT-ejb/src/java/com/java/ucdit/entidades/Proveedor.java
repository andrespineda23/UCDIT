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
@Table(name = "proveedor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proveedor.findAll", query = "SELECT p FROM Proveedor p"),
    @NamedQuery(name = "Proveedor.findByIdproveedor", query = "SELECT p FROM Proveedor p WHERE p.idproveedor = :idproveedor"),
    @NamedQuery(name = "Proveedor.findByNombreproveedor", query = "SELECT p FROM Proveedor p WHERE p.nombreproveedor = :nombreproveedor"),
    @NamedQuery(name = "Proveedor.findByIdentificacionproveedor", query = "SELECT p FROM Proveedor p WHERE p.identificacionproveedor = :identificacionproveedor"),
    @NamedQuery(name = "Proveedor.findByCiudad", query = "SELECT p FROM Proveedor p WHERE p.ciudad = :ciudad"),
    @NamedQuery(name = "Proveedor.findByDireccion", query = "SELECT p FROM Proveedor p WHERE p.direccion = :direccion"),
    @NamedQuery(name = "Proveedor.findByCorreoelectronico", query = "SELECT p FROM Proveedor p WHERE p.correoelectronico = :correoelectronico"),
    @NamedQuery(name = "Proveedor.findByTelefonomovil", query = "SELECT p FROM Proveedor p WHERE p.telefonomovil = :telefonomovil"),
    @NamedQuery(name = "Proveedor.findByTelefonofijo", query = "SELECT p FROM Proveedor p WHERE p.telefonofijo = :telefonofijo")})
public class Proveedor implements Serializable {
    private static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idproveedor")
    private BigInteger idproveedor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "nombreproveedor")
    private String nombreproveedor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "identificacionproveedor")
    private String identificacionproveedor;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "ciudad")
    private String ciudad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "direccion")
    private String direccion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "correoelectronico")
    private String correoelectronico;
    @Size(max = 10)
    @Column(name = "telefonomovil")
    private String telefonomovil;
    @Size(max = 7)
    @Column(name = "telefonofijo")
    private String telefonofijo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proveedor")
    private Collection<EquipoTecnologico> equipoTecnologicoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "proveedor")
    private Collection<Insumo> insumoCollection;

    public Proveedor() {
    }

    public Proveedor(BigInteger idproveedor) {
        this.idproveedor = idproveedor;
    }

    public Proveedor(BigInteger idproveedor, String nombreproveedor, String identificacionproveedor, String ciudad, String direccion, String correoelectronico) {
        this.idproveedor = idproveedor;
        this.nombreproveedor = nombreproveedor;
        this.identificacionproveedor = identificacionproveedor;
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.correoelectronico = correoelectronico;
    }

    public BigInteger getIdproveedor() {
        return idproveedor;
    }

    public void setIdproveedor(BigInteger idproveedor) {
        this.idproveedor = idproveedor;
    }

    public String getNombreproveedor() {
        return nombreproveedor;
    }

    public void setNombreproveedor(String nombreproveedor) {
        this.nombreproveedor = nombreproveedor;
    }

    public String getIdentificacionproveedor() {
        return identificacionproveedor;
    }

    public void setIdentificacionproveedor(String identificacionproveedor) {
        this.identificacionproveedor = identificacionproveedor;
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

    public String getCorreoelectronico() {
        return correoelectronico;
    }

    public void setCorreoelectronico(String correoelectronico) {
        this.correoelectronico = correoelectronico;
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

    @XmlTransient
    public Collection<EquipoTecnologico> getEquipoTecnologicoCollection() {
        return equipoTecnologicoCollection;
    }

    public void setEquipoTecnologicoCollection(Collection<EquipoTecnologico> equipoTecnologicoCollection) {
        this.equipoTecnologicoCollection = equipoTecnologicoCollection;
    }

    @XmlTransient
    public Collection<Insumo> getInsumoCollection() {
        return insumoCollection;
    }

    public void setInsumoCollection(Collection<Insumo> insumoCollection) {
        this.insumoCollection = insumoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idproveedor != null ? idproveedor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proveedor)) {
            return false;
        }
        Proveedor other = (Proveedor) object;
        if ((this.idproveedor == null && other.idproveedor != null) || (this.idproveedor != null && !this.idproveedor.equals(other.idproveedor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.java.ucdit.entidades.Proveedor[ idproveedor=" + idproveedor + " ]";
    }
    
}
