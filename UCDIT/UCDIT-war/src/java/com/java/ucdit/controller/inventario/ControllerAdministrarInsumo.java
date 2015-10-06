/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.controller.inventario;

import com.java.ucdit.bo.interfaces.inventario.AdministrarInsumoBOInterface;
import com.java.ucdit.entidades.Insumo;
import java.io.Serializable;
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
public class ControllerAdministrarInsumo implements Serializable {

    @EJB
    AdministrarInsumoBOInterface administrarInsumosBO;
    //
    private List<Insumo> listaInsumos;
    private List<Insumo> listaInsumosTabla;
    private int posicionInsumoTabla;
    private int tamTotalInsumo;
    private boolean bloquearPagSigInsumo, bloquearPagAntInsumo;
    private String cantidadRegistros;
    private String paginaAnterior;

    public ControllerAdministrarInsumo() {
    }

    @PostConstruct
    public void init() {

    }

    private void iniciarDatosTabla() {
        cantidadRegistros = "N/A";
        listaInsumos = null;
        listaInsumosTabla = null;
        posicionInsumoTabla = 0;
        tamTotalInsumo = 0;
        bloquearPagAntInsumo = true;
        bloquearPagSigInsumo = true;
    }

    public void buscarInsumosRegistrador(String page) {
        this.paginaAnterior = page;
        iniciarDatosTabla();
        try {
            listaInsumos = null;
            listaInsumos = administrarInsumosBO.consultarInsumosRegistrados();
            if (listaInsumos != null) {
                if (listaInsumos.size() > 0) {
                    listaInsumosTabla = new ArrayList<Insumo>();
                    tamTotalInsumo = listaInsumos.size();
                    posicionInsumoTabla = 0;
                    cantidadRegistros = String.valueOf(tamTotalInsumo);
                    cargarDatosTablaInsumo();
                } else {
                    listaInsumosTabla = null;
                    tamTotalInsumo = 0;
                    posicionInsumoTabla = 0;
                    bloquearPagAntInsumo = true;
                    cantidadRegistros = String.valueOf(tamTotalInsumo);
                    bloquearPagSigInsumo = true;
                }
            } else {
                listaInsumosTabla = null;
                tamTotalInsumo = 0;
                posicionInsumoTabla = 0;
                cantidadRegistros = String.valueOf(tamTotalInsumo);
                bloquearPagAntInsumo = true;
                bloquearPagSigInsumo = true;
            }
        } catch (Exception e) {
            System.out.println("Error ControllerAdministrarInsumo buscarInsumosRegistrador : " + e.toString());
        }
    }

    private void cargarDatosTablaInsumo() {
        if (tamTotalInsumo < 10) {
            for (int i = 0; i < tamTotalInsumo; i++) {
                listaInsumosTabla.add(listaInsumos.get(i));
            }
            bloquearPagSigInsumo = true;
            bloquearPagAntInsumo = true;
        } else {
            for (int i = 0; i < 10; i++) {
                listaInsumosTabla.add(listaInsumos.get(i));
            }
            bloquearPagSigInsumo = false;
            bloquearPagAntInsumo = true;
        }
    }

    public void cargarPaginaSiguienteInsumo() {
        listaInsumosTabla = new ArrayList<Insumo>();
        posicionInsumoTabla = posicionInsumoTabla + 10;
        int diferencia = tamTotalInsumo - posicionInsumoTabla;
        if (diferencia > 10) {
            for (int i = posicionInsumoTabla; i < (posicionInsumoTabla + 10); i++) {
                listaInsumosTabla.add(listaInsumos.get(i));
            }
            bloquearPagSigInsumo = false;
            bloquearPagAntInsumo = false;
        } else {
            for (int i = posicionInsumoTabla; i < (posicionInsumoTabla + diferencia); i++) {
                listaInsumosTabla.add(listaInsumos.get(i));
            }
            bloquearPagSigInsumo = true;
            bloquearPagAntInsumo = false;
        }
    }

    public void cargarPaginaAnteriorInsumo() {
        listaInsumosTabla = new ArrayList<Insumo>();
        posicionInsumoTabla = posicionInsumoTabla - 10;
        int diferencia = tamTotalInsumo - posicionInsumoTabla;
        if (diferencia == tamTotalInsumo) {
            for (int i = posicionInsumoTabla; i < (posicionInsumoTabla + 10); i++) {
                listaInsumosTabla.add(listaInsumos.get(i));
            }
            bloquearPagSigInsumo = false;
            bloquearPagAntInsumo = true;
        } else {
            for (int i = posicionInsumoTabla; i < (posicionInsumoTabla + 10); i++) {
                listaInsumosTabla.add(listaInsumos.get(i));
            }
            bloquearPagSigInsumo = false;
            bloquearPagAntInsumo = false;
        }
    }

    public String limpiarProcesoBusqueda() {
        listaInsumos = null;
        listaInsumosTabla = null;
        posicionInsumoTabla = 0;
        tamTotalInsumo = 0;
        bloquearPagAntInsumo = true;
        bloquearPagSigInsumo = true;
        cantidadRegistros = "N/A";
        return paginaAnterior;
    }

    public String paginaDetalles() {
        limpiarProcesoBusqueda();
        return "detallesinsumo";
    }

    // GET - SET
    public int getTamTotalInsumo() {
        return tamTotalInsumo;
    }

    public void setTamTotalInsumo(int tamTotalInsumo) {
        this.tamTotalInsumo = tamTotalInsumo;
    }

    public List<Insumo> getListaInsumos() {
        return listaInsumos;
    }

    public void setListaInsumos(List<Insumo> listaInsumos) {
        this.listaInsumos = listaInsumos;
    }

    public List<Insumo> getListaInsumosTabla() {
        return listaInsumosTabla;
    }

    public void setListaInsumosTabla(List<Insumo> listaInsumosTabla) {
        this.listaInsumosTabla = listaInsumosTabla;
    }

    public int getPosicionInsumoTabla() {
        return posicionInsumoTabla;
    }

    public void setPosicionInsumoTabla(int posicionInsumoTabla) {
        this.posicionInsumoTabla = posicionInsumoTabla;
    }

    public boolean isBloquearPagSigInsumo() {
        return bloquearPagSigInsumo;
    }

    public void setBloquearPagSigInsumo(boolean bloquearPagSigInsumo) {
        this.bloquearPagSigInsumo = bloquearPagSigInsumo;
    }

    public boolean isBloquearPagAntInsumo() {
        return bloquearPagAntInsumo;
    }

    public void setBloquearPagAntInsumo(boolean bloquearPagAntInsumo) {
        this.bloquearPagAntInsumo = bloquearPagAntInsumo;
    }

    public String getCantidadRegistros() {
        return cantidadRegistros;
    }

    public void setCantidadRegistros(String cantidadRegistros) {
        this.cantidadRegistros = cantidadRegistros;
    }

}
