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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ELECTRONICA
 */
@Entity
@Table(name = "componente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Componente.findAll", query = "SELECT c FROM Componente c"),
    @NamedQuery(name = "Componente.findByIdcomponente", query = "SELECT c FROM Componente c WHERE c.idcomponente = :idcomponente"),
    @NamedQuery(name = "Componente.findByNombrecomponente", query = "SELECT c FROM Componente c WHERE c.nombrecomponente = :nombrecomponente"),
    @NamedQuery(name = "Componente.findByCodigocomponente", query = "SELECT c FROM Componente c WHERE c.codigocomponente = :codigocomponente"),
    @NamedQuery(name = "Componente.findByMarcacomponente", query = "SELECT c FROM Componente c WHERE c.marcacomponente = :marcacomponente"),
    @NamedQuery(name = "Componente.findBySeriecomponente", query = "SELECT c FROM Componente c WHERE c.seriecomponente = :seriecomponente"),
    @NamedQuery(name = "Componente.findByModelocomponente", query = "SELECT c FROM Componente c WHERE c.modelocomponente = :modelocomponente"),
    @NamedQuery(name = "Componente.findByEstadocomponente", query = "SELECT c FROM Componente c WHERE c.estadocomponente = :estadocomponente")})
public class Componente implements Serializable {

    private static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcomponente")
    private BigInteger idcomponente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombrecomponente")
    private String nombrecomponente;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "codigocomponente")
    private String codigocomponente;
    @Size(max = 45)
    @Column(name = "marcacomponente")
    private String marcacomponente;
    @Size(max = 45)
    @Column(name = "seriecomponente")
    private String seriecomponente;
    @Size(max = 45)
    @Column(name = "modelocomponente")
    private String modelocomponente;
    @Column(name = "estadocomponente")
    private Boolean estadocomponente;
    @JoinColumn(name = "equipotecnologico", referencedColumnName = "idequipotecnologico")
    @ManyToOne(optional = false)
    private EquipoTecnologico equipotecnologico;

    public Componente() {
    }

    public Componente(BigInteger idcomponente) {
        this.idcomponente = idcomponente;
    }

    public Componente(BigInteger idcomponente, String nombrecomponente, String codigocomponente) {
        this.idcomponente = idcomponente;
        this.nombrecomponente = nombrecomponente;
        this.codigocomponente = codigocomponente;
    }

    public BigInteger getIdcomponente() {
        return idcomponente;
    }

    public void setIdcomponente(BigInteger idcomponente) {
        this.idcomponente = idcomponente;
    }

    public String getNombrecomponente() {
        if (null != nombrecomponente) {
            return nombrecomponente.toUpperCase();
        }
        return nombrecomponente;
    }

    public void setNombrecomponente(String nombrecomponente) {
        this.nombrecomponente = nombrecomponente.toUpperCase();;
    }

    public String getCodigocomponente() {
        if (null != codigocomponente) {
            return codigocomponente.toUpperCase();
        }
        return codigocomponente;
    }

    public void setCodigocomponente(String codigocomponente) {
        this.codigocomponente = codigocomponente.toUpperCase();;
    }

    public String getMarcacomponente() {
        if (null != marcacomponente) {
            return marcacomponente.toUpperCase();
        }
        return marcacomponente;
    }

    public void setMarcacomponente(String marcacomponente) {
        this.marcacomponente = marcacomponente.toUpperCase();;
    }

    public String getSeriecomponente() {
        if (null != seriecomponente) {
            return seriecomponente.toUpperCase();
        }
        return seriecomponente;
    }

    public void setSeriecomponente(String seriecomponente) {
        this.seriecomponente = seriecomponente.toUpperCase();;
    }

    public String getModelocomponente() {
        if (null != modelocomponente) {
            return modelocomponente.toUpperCase();
        }
        return modelocomponente;
    }

    public void setModelocomponente(String modelocomponente) {
        this.modelocomponente = modelocomponente.toUpperCase();;
    }

    public Boolean getEstadocomponente() {
        return estadocomponente;
    }

    public void setEstadocomponente(Boolean estadocomponente) {
        this.estadocomponente = estadocomponente;
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
        hash += (idcomponente != null ? idcomponente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Componente)) {
            return false;
        }
        Componente other = (Componente) object;
        if ((this.idcomponente == null && other.idcomponente != null) || (this.idcomponente != null && !this.idcomponente.equals(other.idcomponente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.java.ucdit.entidades.Componente[ idcomponente=" + idcomponente + " ]";
    }

}
