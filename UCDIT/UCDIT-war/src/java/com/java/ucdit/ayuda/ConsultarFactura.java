/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.ayuda;

import com.java.ucdit.entidades.Insumo;
import com.java.ucdit.entidades.Proveedor;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author ELECTRONICA
 */
public class ConsultarFactura implements Serializable {

    private Date fechacompra;
    private String numerofactura;
    private Proveedor proveedor;
    private String valortotal;

    public ConsultarFactura() {
    }

    public Date getFechacompra() {
        return fechacompra;
    }

    public void setFechacompra(Date fechacompra) {
        this.fechacompra = fechacompra;
    }

    public String getNumerofactura() {
        return numerofactura;
    }

    public void setNumerofactura(String numerofactura) {
        this.numerofactura = numerofactura;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    public String getValortotal() {
        return valortotal;
    }

    public void setValortotal(String valortotal) {
        this.valortotal = valortotal;
    }

    public ConsultarFactura(Date fechacompra, String numerofactura, Proveedor proveedor, String valortotal) {
        this.fechacompra = fechacompra;
        this.numerofactura = numerofactura;
        this.proveedor = proveedor;
        this.valortotal = valortotal;
    }

}
