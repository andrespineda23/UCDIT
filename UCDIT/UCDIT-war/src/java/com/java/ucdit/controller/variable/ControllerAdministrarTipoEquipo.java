/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.controller.variable;

import com.java.ucdit.bo.interfaces.variable.AdministrarTipoEquipoBOInterface;
import com.java.ucdit.entidades.TipoEquipo;
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
public class ControllerAdministrarTipoEquipo implements Serializable {

    @EJB
    AdministrarTipoEquipoBOInterface administrarTipoEquiposBO;
    //
    private List<TipoEquipo> listaTipoEquipo;
    private List<TipoEquipo> listaTipoEquipoTabla;
    private int posicionTipoEquipoTabla;
    private int tamTotalTipoEquipo;
    private boolean bloquearPagSigTipoEquipo, bloquearPagAntTipoEquipo;
    private String cantidadRegistros;

    public ControllerAdministrarTipoEquipo() {
    }

    @PostConstruct
    public void init() {
        buscarTipoEquiposRegistrador();
    }

    private void iniciarDatosTabla() {
        cantidadRegistros = "N/A";
        listaTipoEquipo = null;
        listaTipoEquipoTabla = null;
        posicionTipoEquipoTabla = 0;
        tamTotalTipoEquipo = 0;
        bloquearPagAntTipoEquipo = true;
        bloquearPagSigTipoEquipo = true;
    }

    public void buscarTipoEquiposRegistrador() {
        iniciarDatosTabla();
        try {
            listaTipoEquipo = null;
            listaTipoEquipo = administrarTipoEquiposBO.consultarTipoEquipoRegistrados();
            if (listaTipoEquipo != null) {
                if (listaTipoEquipo.size() > 0) {
                    listaTipoEquipoTabla = new ArrayList<TipoEquipo>();
                    tamTotalTipoEquipo = listaTipoEquipo.size();
                    posicionTipoEquipoTabla = 0;
                    cantidadRegistros = String.valueOf(tamTotalTipoEquipo);
                    cargarDatosTablaTipoEquipo();
                } else {
                    listaTipoEquipoTabla = null;
                    tamTotalTipoEquipo = 0;
                    posicionTipoEquipoTabla = 0;
                    bloquearPagAntTipoEquipo = true;
                    cantidadRegistros = String.valueOf(tamTotalTipoEquipo);
                    bloquearPagSigTipoEquipo = true;
                }
            } else {
                listaTipoEquipoTabla = null;
                tamTotalTipoEquipo = 0;
                posicionTipoEquipoTabla = 0;
                cantidadRegistros = String.valueOf(tamTotalTipoEquipo);
                bloquearPagAntTipoEquipo = true;
                bloquearPagSigTipoEquipo = true;
            }
        } catch (Exception e) {
            System.out.println("Error ControllerAdministrarTipoEquipo buscarTipoEquiposRegistrador : " + e.toString());
        }
    }

    private void cargarDatosTablaTipoEquipo() {
        if (tamTotalTipoEquipo < 5) {
            for (int i = 0; i < tamTotalTipoEquipo; i++) {
                listaTipoEquipoTabla.add(listaTipoEquipo.get(i));
            }
            bloquearPagSigTipoEquipo = true;
            bloquearPagAntTipoEquipo = true;
        } else {
            for (int i = 0; i < 5; i++) {
                listaTipoEquipoTabla.add(listaTipoEquipo.get(i));
            }
            bloquearPagSigTipoEquipo = false;
            bloquearPagAntTipoEquipo = true;
        }
    }

    public void cargarPaginaSiguienteTipoEquipo() {
        listaTipoEquipoTabla = new ArrayList<TipoEquipo>();
        posicionTipoEquipoTabla = posicionTipoEquipoTabla + 5;
        int diferencia = tamTotalTipoEquipo - posicionTipoEquipoTabla;
        if (diferencia > 5) {
            for (int i = posicionTipoEquipoTabla; i < (posicionTipoEquipoTabla + 5); i++) {
                listaTipoEquipoTabla.add(listaTipoEquipo.get(i));
            }
            bloquearPagSigTipoEquipo = false;
            bloquearPagAntTipoEquipo = false;
        } else {
            for (int i = posicionTipoEquipoTabla; i < (posicionTipoEquipoTabla + diferencia); i++) {
                listaTipoEquipoTabla.add(listaTipoEquipo.get(i));
            }
            bloquearPagSigTipoEquipo = true;
            bloquearPagAntTipoEquipo = false;
        }
    }

    public void cargarPaginaAnteriorTipoEquipo() {
        listaTipoEquipoTabla = new ArrayList<TipoEquipo>();
        posicionTipoEquipoTabla = posicionTipoEquipoTabla - 5;
        int diferencia = tamTotalTipoEquipo - posicionTipoEquipoTabla;
        if (diferencia == tamTotalTipoEquipo) {
            for (int i = posicionTipoEquipoTabla; i < (posicionTipoEquipoTabla + 5); i++) {
                listaTipoEquipoTabla.add(listaTipoEquipo.get(i));
            }
            bloquearPagSigTipoEquipo = false;
            bloquearPagAntTipoEquipo = true;
        } else {
            for (int i = posicionTipoEquipoTabla; i < (posicionTipoEquipoTabla + 5); i++) {
                listaTipoEquipoTabla.add(listaTipoEquipo.get(i));
            }
            bloquearPagSigTipoEquipo = false;
            bloquearPagAntTipoEquipo = false;
        }
    }

    public void limpiarProcesoBusqueda() {
        listaTipoEquipo = null;
        listaTipoEquipoTabla = null;
        posicionTipoEquipoTabla = 0;
        tamTotalTipoEquipo = 0;
        bloquearPagAntTipoEquipo = true;
        bloquearPagSigTipoEquipo = true;
        cantidadRegistros = "N/A";
    }

    public String paginaDetalles() {
        limpiarProcesoBusqueda();
        return "detallestipoequipo";
    }

    // GET - SET
    public List<TipoEquipo> getListaTipoEquipo() {
        return listaTipoEquipo;
    }

    public void setListaTipoEquipo(List<TipoEquipo> listaTipoEquipo) {
        this.listaTipoEquipo = listaTipoEquipo;
    }

    public List<TipoEquipo> getListaTipoEquipoTabla() {
        return listaTipoEquipoTabla;
    }

    public void setListaTipoEquipoTabla(List<TipoEquipo> listaTipoEquipoTabla) {
        this.listaTipoEquipoTabla = listaTipoEquipoTabla;
    }

    public int getPosicionTipoEquipoTabla() {
        return posicionTipoEquipoTabla;
    }

    public void setPosicionTipoEquipoTabla(int posicionTipoEquipoTabla) {
        this.posicionTipoEquipoTabla = posicionTipoEquipoTabla;
    }

    public int getTamTotalTipoEquipo() {
        return tamTotalTipoEquipo;
    }

    public void setTamTotalTipoEquipo(int tamTotalTipoEquipo) {
        this.tamTotalTipoEquipo = tamTotalTipoEquipo;
    }

    public boolean isBloquearPagSigTipoEquipo() {
        return bloquearPagSigTipoEquipo;
    }

    public void setBloquearPagSigTipoEquipo(boolean bloquearPagSigTipoEquipo) {
        this.bloquearPagSigTipoEquipo = bloquearPagSigTipoEquipo;
    }

    public boolean isBloquearPagAntTipoEquipo() {
        return bloquearPagAntTipoEquipo;
    }

    public void setBloquearPagAntTipoEquipo(boolean bloquearPagAntTipoEquipo) {
        this.bloquearPagAntTipoEquipo = bloquearPagAntTipoEquipo;
    }

    public String getCantidadRegistros() {
        return cantidadRegistros;
    }

    public void setCantidadRegistros(String cantidadRegistros) {
        this.cantidadRegistros = cantidadRegistros;
    }

}
