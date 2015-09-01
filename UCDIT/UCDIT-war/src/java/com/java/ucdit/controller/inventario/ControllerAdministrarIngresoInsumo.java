/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.controller.inventario;

import com.java.ucdit.bo.interfaces.inventario.AdministrarIngresoInsumoBOInterface;
import com.java.ucdit.entidades.IngresoInsumo;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author ELECTRONICA
 */
@ManagedBean
@SessionScoped
public class ControllerAdministrarIngresoInsumo implements Serializable {

    @EJB
    AdministrarIngresoInsumoBOInterface administrarIngresosInsumoBO;
    //
    private BigInteger idInsumo;
    //
    private List<IngresoInsumo> listaIngresosInsumo;
    private List<IngresoInsumo> listaIngresosInsumoTabla;
    private int posicionIngresoInsumoTabla;
    private int tamTotalIngresoInsumo;
    private boolean bloquearPagSigIngresoInsumo, bloquearPagAntIngresoInsumo;
    private String cantidadRegistros;

    public ControllerAdministrarIngresoInsumo() {
    }

    @PostConstruct
    public void init() {
    }

    private void iniciarDatosTabla() {
        cantidadRegistros = "N/A";
        listaIngresosInsumo = null;
        listaIngresosInsumoTabla = null;
        posicionIngresoInsumoTabla = 0;
        tamTotalIngresoInsumo = 0;
        bloquearPagAntIngresoInsumo = true;
        bloquearPagSigIngresoInsumo = true;
    }

    public void recibirIdInsumo(BigInteger idInsumo) {
        this.idInsumo = idInsumo;
        buscarIngresosInsumoRegistrador();
    }

    public void buscarIngresosInsumoRegistrador() {
        iniciarDatosTabla();
        try {
            listaIngresosInsumo = null;
            listaIngresosInsumo = administrarIngresosInsumoBO.consultarIngresosInsumoRegistrados(idInsumo);
            if (listaIngresosInsumo != null) {
                if (listaIngresosInsumo.size() > 0) {
                    listaIngresosInsumoTabla = new ArrayList<IngresoInsumo>();
                    tamTotalIngresoInsumo = listaIngresosInsumo.size();
                    posicionIngresoInsumoTabla = 0;
                    cantidadRegistros = String.valueOf(tamTotalIngresoInsumo);
                    cargarDatosTablaIngresoInsumo();
                } else {
                    listaIngresosInsumoTabla = null;
                    tamTotalIngresoInsumo = 0;
                    posicionIngresoInsumoTabla = 0;
                    bloquearPagAntIngresoInsumo = true;
                    cantidadRegistros = String.valueOf(tamTotalIngresoInsumo);
                    bloquearPagSigIngresoInsumo = true;
                }
            } else {
                listaIngresosInsumoTabla = null;
                tamTotalIngresoInsumo = 0;
                posicionIngresoInsumoTabla = 0;
                cantidadRegistros = String.valueOf(tamTotalIngresoInsumo);
                bloquearPagAntIngresoInsumo = true;
                bloquearPagSigIngresoInsumo = true;
            }
        } catch (Exception e) {
            System.out.println("Error ControllerAdministrarIngresoInsumo buscarIngresosInsumoRegistrador : " + e.toString());
        }
    }

    private void cargarDatosTablaIngresoInsumo() {
        if (tamTotalIngresoInsumo < 5) {
            for (int i = 0; i < tamTotalIngresoInsumo; i++) {
                listaIngresosInsumoTabla.add(listaIngresosInsumo.get(i));
            }
            bloquearPagSigIngresoInsumo = true;
            bloquearPagAntIngresoInsumo = true;
        } else {
            for (int i = 0; i < 5; i++) {
                listaIngresosInsumoTabla.add(listaIngresosInsumo.get(i));
            }
            bloquearPagSigIngresoInsumo = false;
            bloquearPagAntIngresoInsumo = true;
        }
    }

    public void cargarPaginaSiguienteIngresoInsumo() {
        listaIngresosInsumoTabla = new ArrayList<IngresoInsumo>();
        posicionIngresoInsumoTabla = posicionIngresoInsumoTabla + 5;
        int diferencia = tamTotalIngresoInsumo - posicionIngresoInsumoTabla;
        if (diferencia > 5) {
            for (int i = posicionIngresoInsumoTabla; i < (posicionIngresoInsumoTabla + 5); i++) {
                listaIngresosInsumoTabla.add(listaIngresosInsumo.get(i));
            }
            bloquearPagSigIngresoInsumo = false;
            bloquearPagAntIngresoInsumo = false;
        } else {
            for (int i = posicionIngresoInsumoTabla; i < (posicionIngresoInsumoTabla + diferencia); i++) {
                listaIngresosInsumoTabla.add(listaIngresosInsumo.get(i));
            }
            bloquearPagSigIngresoInsumo = true;
            bloquearPagAntIngresoInsumo = false;
        }
    }

    public void cargarPaginaAnteriorIngresoInsumo() {
        listaIngresosInsumoTabla = new ArrayList<IngresoInsumo>();
        posicionIngresoInsumoTabla = posicionIngresoInsumoTabla - 5;
        int diferencia = tamTotalIngresoInsumo - posicionIngresoInsumoTabla;
        if (diferencia == tamTotalIngresoInsumo) {
            for (int i = posicionIngresoInsumoTabla; i < (posicionIngresoInsumoTabla + 5); i++) {
                listaIngresosInsumoTabla.add(listaIngresosInsumo.get(i));
            }
            bloquearPagSigIngresoInsumo = false;
            bloquearPagAntIngresoInsumo = true;
        } else {
            for (int i = posicionIngresoInsumoTabla; i < (posicionIngresoInsumoTabla + 5); i++) {
                listaIngresosInsumoTabla.add(listaIngresosInsumo.get(i));
            }
            bloquearPagSigIngresoInsumo = false;
            bloquearPagAntIngresoInsumo = false;
        }
    }

    public void limpiarProcesoBusqueda() {
        listaIngresosInsumo = null;
        listaIngresosInsumoTabla = null;
        posicionIngresoInsumoTabla = 0;
        tamTotalIngresoInsumo = 0;
        bloquearPagAntIngresoInsumo = true;
        bloquearPagSigIngresoInsumo = true;
        cantidadRegistros = "N/A";
    }

    public String paginaDetalles() {
        limpiarProcesoBusqueda();
        return "detallesingresoinsumo";
    }
    public String paginaNuevoRegistro() {
        limpiarProcesoBusqueda();
        return "registraringresoinsumo";
    }
    public String regresarPaginaDetalles() {
        limpiarProcesoBusqueda();
        return "detallesinsumo";
    }

    // GET - SET
    public BigInteger getIdInsumo() {
        return idInsumo;
    }

    public void setIdInsumo(BigInteger idInsumo) {
        this.idInsumo = idInsumo;
    }

    public List<IngresoInsumo> getListaIngresosInsumo() {
        return listaIngresosInsumo;
    }

    public void setListaIngresosInsumo(List<IngresoInsumo> listaIngresosInsumo) {
        this.listaIngresosInsumo = listaIngresosInsumo;
    }

    public List<IngresoInsumo> getListaIngresosInsumoTabla() {
        return listaIngresosInsumoTabla;
    }

    public void setListaIngresosInsumoTabla(List<IngresoInsumo> listaIngresosInsumoTabla) {
        this.listaIngresosInsumoTabla = listaIngresosInsumoTabla;
    }

    public int getPosicionIngresoInsumoTabla() {
        return posicionIngresoInsumoTabla;
    }

    public void setPosicionIngresoInsumoTabla(int posicionIngresoInsumoTabla) {
        this.posicionIngresoInsumoTabla = posicionIngresoInsumoTabla;
    }

    public int getTamTotalIngresoInsumo() {
        return tamTotalIngresoInsumo;
    }

    public void setTamTotalIngresoInsumo(int tamTotalIngresoInsumo) {
        this.tamTotalIngresoInsumo = tamTotalIngresoInsumo;
    }

    public boolean isBloquearPagSigIngresoInsumo() {
        return bloquearPagSigIngresoInsumo;
    }

    public void setBloquearPagSigIngresoInsumo(boolean bloquearPagSigIngresoInsumo) {
        this.bloquearPagSigIngresoInsumo = bloquearPagSigIngresoInsumo;
    }

    public boolean isBloquearPagAntIngresoInsumo() {
        return bloquearPagAntIngresoInsumo;
    }

    public void setBloquearPagAntIngresoInsumo(boolean bloquearPagAntIngresoInsumo) {
        this.bloquearPagAntIngresoInsumo = bloquearPagAntIngresoInsumo;
    }

    public String getCantidadRegistros() {
        return cantidadRegistros;
    }

    public void setCantidadRegistros(String cantidadRegistros) {
        this.cantidadRegistros = cantidadRegistros;
    }

}
