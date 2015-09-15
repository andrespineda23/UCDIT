/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.ayuda;

import com.java.ucdit.entidades.Insumo;
import java.io.Serializable;

/**
 *
 * @author ELECTRONICA
 */
public class RegistroFactura implements Serializable {

    private Insumo insumo;
    private Integer cantidad;
    private Integer costo;

    public RegistroFactura() {
    }

    public RegistroFactura(Insumo insumo, Integer cantidad, Integer costo) {
        this.insumo = insumo;
        this.cantidad = cantidad;
        this.costo = costo;
    }

    public Insumo getInsumo() {
        return insumo;
    }

    public void setInsumo(Insumo insumo) {
        this.insumo = insumo;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getCosto() {
        return costo;
    }

    public void setCosto(Integer costo) {
        this.costo = costo;
    }

}
