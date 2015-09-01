/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.entidades;

import java.io.Serializable;
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
@Table(name = "tipounidad")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoUnidad.findAll", query = "SELECT t FROM TipoUnidad t"),
    @NamedQuery(name = "TipoUnidad.findByIdtipounidad", query = "SELECT t FROM TipoUnidad t WHERE t.idtipounidad = :idtipounidad"),
    @NamedQuery(name = "TipoUnidad.findByNombretipounidad", query = "SELECT t FROM TipoUnidad t WHERE t.nombretipounidad = :nombretipounidad")})
public class TipoUnidad implements Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tipounidad")
    private Collection<Insumo> insumoCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtipounidad")
    private Long idtipounidad;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "nombretipounidad")
    private String nombretipounidad;

    public TipoUnidad() {
    }

    public TipoUnidad(Long idtipounidad) {
        this.idtipounidad = idtipounidad;
    }

    public TipoUnidad(Long idtipounidad, String nombretipounidad) {
        this.idtipounidad = idtipounidad;
        this.nombretipounidad = nombretipounidad;
    }

    public Long getIdtipounidad() {
        return idtipounidad;
    }

    public void setIdtipounidad(Long idtipounidad) {
        this.idtipounidad = idtipounidad;
    }

    public String getNombretipounidad() {
        if (null != nombretipounidad) {
            return nombretipounidad.toUpperCase();
        }
        return nombretipounidad;
    }

    public void setNombretipounidad(String nombretipounidad) {
        this.nombretipounidad = nombretipounidad.toUpperCase();
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtipounidad != null ? idtipounidad.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoUnidad)) {
            return false;
        }
        TipoUnidad other = (TipoUnidad) object;
        if ((this.idtipounidad == null && other.idtipounidad != null) || (this.idtipounidad != null && !this.idtipounidad.equals(other.idtipounidad))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.java.ucdit.entidades.TipoUnidad[ idtipounidad=" + idtipounidad + " ]";
    }

    @XmlTransient
    public Collection<Insumo> getInsumoCollection() {
        return insumoCollection;
    }

    public void setInsumoCollection(Collection<Insumo> insumoCollection) {
        this.insumoCollection = insumoCollection;
    }

}
