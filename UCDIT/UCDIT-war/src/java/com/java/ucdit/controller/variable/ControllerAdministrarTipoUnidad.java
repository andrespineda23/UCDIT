/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.controller.variable;

import com.java.ucdit.bo.interfaces.variable.AdministrarTipoUnidadBOInterface;
import com.java.ucdit.entidades.TipoUnidad;
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
public class ControllerAdministrarTipoUnidad implements Serializable {

    @EJB
    AdministrarTipoUnidadBOInterface administrarTipoUnidadsBO;
    //
    private List<TipoUnidad> listaTipoUnidad;
    private List<TipoUnidad> listaTipoUnidadTabla;
    private int posicionTipoUnidadTabla;
    private int tamTotalTipoUnidad;
    private boolean bloquearPagSigTipoUnidad, bloquearPagAntTipoUnidad;
    private String cantidadRegistros;

    public ControllerAdministrarTipoUnidad() {
    }

    @PostConstruct
    public void init() {
        buscarTipoUnidadsRegistrador();
    }

    private void iniciarDatosTabla() {
        cantidadRegistros = "N/A";
        listaTipoUnidad = null;
        listaTipoUnidadTabla = null;
        posicionTipoUnidadTabla = 0;
        tamTotalTipoUnidad = 0;
        bloquearPagAntTipoUnidad = true;
        bloquearPagSigTipoUnidad = true;
    }

    public void buscarTipoUnidadsRegistrador() {
        iniciarDatosTabla();
        try {
            listaTipoUnidad = null;
            listaTipoUnidad = administrarTipoUnidadsBO.consultarTipoUnidadRegistrados();
            if (listaTipoUnidad != null) {
                if (listaTipoUnidad.size() > 0) {
                    listaTipoUnidadTabla = new ArrayList<TipoUnidad>();
                    tamTotalTipoUnidad = listaTipoUnidad.size();
                    posicionTipoUnidadTabla = 0;
                    cantidadRegistros = String.valueOf(tamTotalTipoUnidad);
                    cargarDatosTablaTipoUnidad();
                } else {
                    listaTipoUnidadTabla = null;
                    tamTotalTipoUnidad = 0;
                    posicionTipoUnidadTabla = 0;
                    bloquearPagAntTipoUnidad = true;
                    cantidadRegistros = String.valueOf(tamTotalTipoUnidad);
                    bloquearPagSigTipoUnidad = true;
                }
            } else {
                listaTipoUnidadTabla = null;
                tamTotalTipoUnidad = 0;
                posicionTipoUnidadTabla = 0;
                cantidadRegistros = String.valueOf(tamTotalTipoUnidad);
                bloquearPagAntTipoUnidad = true;
                bloquearPagSigTipoUnidad = true;
            }
        } catch (Exception e) {
            System.out.println("Error ControllerAdministrarTipoUnidad buscarTipoUnidadsRegistrador : " + e.toString());
        }
    }

    private void cargarDatosTablaTipoUnidad() {
        if (tamTotalTipoUnidad < 5) {
            for (int i = 0; i < tamTotalTipoUnidad; i++) {
                listaTipoUnidadTabla.add(listaTipoUnidad.get(i));
            }
            bloquearPagSigTipoUnidad = true;
            bloquearPagAntTipoUnidad = true;
        } else {
            for (int i = 0; i < 5; i++) {
                listaTipoUnidadTabla.add(listaTipoUnidad.get(i));
            }
            bloquearPagSigTipoUnidad = false;
            bloquearPagAntTipoUnidad = true;
        }
    }

    public void cargarPaginaSiguienteTipoUnidad() {
        listaTipoUnidadTabla = new ArrayList<TipoUnidad>();
        posicionTipoUnidadTabla = posicionTipoUnidadTabla + 5;
        int diferencia = tamTotalTipoUnidad - posicionTipoUnidadTabla;
        if (diferencia > 5) {
            for (int i = posicionTipoUnidadTabla; i < (posicionTipoUnidadTabla + 5); i++) {
                listaTipoUnidadTabla.add(listaTipoUnidad.get(i));
            }
            bloquearPagSigTipoUnidad = false;
            bloquearPagAntTipoUnidad = false;
        } else {
            for (int i = posicionTipoUnidadTabla; i < (posicionTipoUnidadTabla + diferencia); i++) {
                listaTipoUnidadTabla.add(listaTipoUnidad.get(i));
            }
            bloquearPagSigTipoUnidad = true;
            bloquearPagAntTipoUnidad = false;
        }
    }

    public void cargarPaginaAnteriorTipoUnidad() {
        listaTipoUnidadTabla = new ArrayList<TipoUnidad>();
        posicionTipoUnidadTabla = posicionTipoUnidadTabla - 5;
        int diferencia = tamTotalTipoUnidad - posicionTipoUnidadTabla;
        if (diferencia == tamTotalTipoUnidad) {
            for (int i = posicionTipoUnidadTabla; i < (posicionTipoUnidadTabla + 5); i++) {
                listaTipoUnidadTabla.add(listaTipoUnidad.get(i));
            }
            bloquearPagSigTipoUnidad = false;
            bloquearPagAntTipoUnidad = true;
        } else {
            for (int i = posicionTipoUnidadTabla; i < (posicionTipoUnidadTabla + 5); i++) {
                listaTipoUnidadTabla.add(listaTipoUnidad.get(i));
            }
            bloquearPagSigTipoUnidad = false;
            bloquearPagAntTipoUnidad = false;
        }
    }

    public void limpiarProcesoBusqueda() {
        listaTipoUnidad = null;
        listaTipoUnidadTabla = null;
        posicionTipoUnidadTabla = 0;
        tamTotalTipoUnidad = 0;
        bloquearPagAntTipoUnidad = true;
        bloquearPagSigTipoUnidad = true;
        cantidadRegistros = "N/A";
    }

    public String paginaDetalles() {
        limpiarProcesoBusqueda();
        return "detallestipounidad";
    }

    // GET - SET
    public List<TipoUnidad> getListaTipoUnidad() {
        return listaTipoUnidad;
    }

    public void setListaTipoUnidad(List<TipoUnidad> listaTipoUnidad) {
        this.listaTipoUnidad = listaTipoUnidad;
    }

    public List<TipoUnidad> getListaTipoUnidadTabla() {
        return listaTipoUnidadTabla;
    }

    public void setListaTipoUnidadTabla(List<TipoUnidad> listaTipoUnidadTabla) {
        this.listaTipoUnidadTabla = listaTipoUnidadTabla;
    }

    public int getPosicionTipoUnidadTabla() {
        return posicionTipoUnidadTabla;
    }

    public void setPosicionTipoUnidadTabla(int posicionTipoUnidadTabla) {
        this.posicionTipoUnidadTabla = posicionTipoUnidadTabla;
    }

    public int getTamTotalTipoUnidad() {
        return tamTotalTipoUnidad;
    }

    public void setTamTotalTipoUnidad(int tamTotalTipoUnidad) {
        this.tamTotalTipoUnidad = tamTotalTipoUnidad;
    }

    public boolean isBloquearPagSigTipoUnidad() {
        return bloquearPagSigTipoUnidad;
    }

    public void setBloquearPagSigTipoUnidad(boolean bloquearPagSigTipoUnidad) {
        this.bloquearPagSigTipoUnidad = bloquearPagSigTipoUnidad;
    }

    public boolean isBloquearPagAntTipoUnidad() {
        return bloquearPagAntTipoUnidad;
    }

    public void setBloquearPagAntTipoUnidad(boolean bloquearPagAntTipoUnidad) {
        this.bloquearPagAntTipoUnidad = bloquearPagAntTipoUnidad;
    }

    public String getCantidadRegistros() {
        return cantidadRegistros;
    }

    public void setCantidadRegistros(String cantidadRegistros) {
        this.cantidadRegistros = cantidadRegistros;
    }

}
