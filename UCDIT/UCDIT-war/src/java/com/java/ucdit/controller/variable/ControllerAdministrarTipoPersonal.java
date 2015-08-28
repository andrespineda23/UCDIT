/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.controller.variable;

import com.java.ucdit.bo.interfaces.variable.AdministrarTipoPersonalBOInterface;
import com.java.ucdit.entidades.TipoPersonal;
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
public class ControllerAdministrarTipoPersonal implements Serializable {

    @EJB
    AdministrarTipoPersonalBOInterface administrarTipoPersonalsBO;
    //
    private List<TipoPersonal> listaTipoPersonal;
    private List<TipoPersonal> listaTipoPersonalTabla;
    private int posicionTipoPersonalTabla;
    private int tamTotalTipoPersonal;
    private boolean bloquearPagSigTipoPersonal, bloquearPagAntTipoPersonal;
    private String cantidadRegistros;

    public ControllerAdministrarTipoPersonal() {
    }

    @PostConstruct
    public void init() {
        buscarTipoPersonalsRegistrador();
    }

    private void iniciarDatosTabla() {
        cantidadRegistros = "N/A";
        listaTipoPersonal = null;
        listaTipoPersonalTabla = null;
        posicionTipoPersonalTabla = 0;
        tamTotalTipoPersonal = 0;
        bloquearPagAntTipoPersonal = true;
        bloquearPagSigTipoPersonal = true;
    }

    public void buscarTipoPersonalsRegistrador() {
        iniciarDatosTabla();
        try {
            listaTipoPersonal = null;
            listaTipoPersonal = administrarTipoPersonalsBO.consultarTipoPersonalRegistrados();
            if (listaTipoPersonal != null) {
                if (listaTipoPersonal.size() > 0) {
                    listaTipoPersonalTabla = new ArrayList<TipoPersonal>();
                    tamTotalTipoPersonal = listaTipoPersonal.size();
                    posicionTipoPersonalTabla = 0;
                    cantidadRegistros = String.valueOf(tamTotalTipoPersonal);
                    cargarDatosTablaTipoPersonal();
                } else {
                    listaTipoPersonalTabla = null;
                    tamTotalTipoPersonal = 0;
                    posicionTipoPersonalTabla = 0;
                    bloquearPagAntTipoPersonal = true;
                    cantidadRegistros = String.valueOf(tamTotalTipoPersonal);
                    bloquearPagSigTipoPersonal = true;
                }
            } else {
                listaTipoPersonalTabla = null;
                tamTotalTipoPersonal = 0;
                posicionTipoPersonalTabla = 0;
                cantidadRegistros = String.valueOf(tamTotalTipoPersonal);
                bloquearPagAntTipoPersonal = true;
                bloquearPagSigTipoPersonal = true;
            }
        } catch (Exception e) {
            System.out.println("Error ControllerAdministrarTipoPersonal buscarTipoPersonalsRegistrador : " + e.toString());
        }
    }

    private void cargarDatosTablaTipoPersonal() {
        if (tamTotalTipoPersonal < 5) {
            for (int i = 0; i < tamTotalTipoPersonal; i++) {
                listaTipoPersonalTabla.add(listaTipoPersonal.get(i));
            }
            bloquearPagSigTipoPersonal = true;
            bloquearPagAntTipoPersonal = true;
        } else {
            for (int i = 0; i < 5; i++) {
                listaTipoPersonalTabla.add(listaTipoPersonal.get(i));
            }
            bloquearPagSigTipoPersonal = false;
            bloquearPagAntTipoPersonal = true;
        }
    }

    public void cargarPaginaSiguienteTipoPersonal() {
        listaTipoPersonalTabla = new ArrayList<TipoPersonal>();
        posicionTipoPersonalTabla = posicionTipoPersonalTabla + 5;
        int diferencia = tamTotalTipoPersonal - posicionTipoPersonalTabla;
        if (diferencia > 5) {
            for (int i = posicionTipoPersonalTabla; i < (posicionTipoPersonalTabla + 5); i++) {
                listaTipoPersonalTabla.add(listaTipoPersonal.get(i));
            }
            bloquearPagSigTipoPersonal = false;
            bloquearPagAntTipoPersonal = false;
        } else {
            for (int i = posicionTipoPersonalTabla; i < (posicionTipoPersonalTabla + diferencia); i++) {
                listaTipoPersonalTabla.add(listaTipoPersonal.get(i));
            }
            bloquearPagSigTipoPersonal = true;
            bloquearPagAntTipoPersonal = false;
        }
    }

    public void cargarPaginaAnteriorTipoPersonal() {
        listaTipoPersonalTabla = new ArrayList<TipoPersonal>();
        posicionTipoPersonalTabla = posicionTipoPersonalTabla - 5;
        int diferencia = tamTotalTipoPersonal - posicionTipoPersonalTabla;
        if (diferencia == tamTotalTipoPersonal) {
            for (int i = posicionTipoPersonalTabla; i < (posicionTipoPersonalTabla + 5); i++) {
                listaTipoPersonalTabla.add(listaTipoPersonal.get(i));
            }
            bloquearPagSigTipoPersonal = false;
            bloquearPagAntTipoPersonal = true;
        } else {
            for (int i = posicionTipoPersonalTabla; i < (posicionTipoPersonalTabla + 5); i++) {
                listaTipoPersonalTabla.add(listaTipoPersonal.get(i));
            }
            bloquearPagSigTipoPersonal = false;
            bloquearPagAntTipoPersonal = false;
        }
    }

    public void limpiarProcesoBusqueda() {
        listaTipoPersonal = null;
        listaTipoPersonalTabla = null;
        posicionTipoPersonalTabla = 0;
        tamTotalTipoPersonal = 0;
        bloquearPagAntTipoPersonal = true;
        bloquearPagSigTipoPersonal = true;
        cantidadRegistros = "N/A";
    }

    public String paginaDetalles() {
        limpiarProcesoBusqueda();
        return "detallestipopersonal";
    }

    // GET - SET
    public List<TipoPersonal> getListaTipoPersonal() {
        return listaTipoPersonal;
    }

    public void setListaTipoPersonal(List<TipoPersonal> listaTipoPersonal) {
        this.listaTipoPersonal = listaTipoPersonal;
    }

    public List<TipoPersonal> getListaTipoPersonalTabla() {
        return listaTipoPersonalTabla;
    }

    public void setListaTipoPersonalTabla(List<TipoPersonal> listaTipoPersonalTabla) {
        this.listaTipoPersonalTabla = listaTipoPersonalTabla;
    }

    public int getPosicionTipoPersonalTabla() {
        return posicionTipoPersonalTabla;
    }

    public void setPosicionTipoPersonalTabla(int posicionTipoPersonalTabla) {
        this.posicionTipoPersonalTabla = posicionTipoPersonalTabla;
    }

    public int getTamTotalTipoPersonal() {
        return tamTotalTipoPersonal;
    }

    public void setTamTotalTipoPersonal(int tamTotalTipoPersonal) {
        this.tamTotalTipoPersonal = tamTotalTipoPersonal;
    }

    public boolean isBloquearPagSigTipoPersonal() {
        return bloquearPagSigTipoPersonal;
    }

    public void setBloquearPagSigTipoPersonal(boolean bloquearPagSigTipoPersonal) {
        this.bloquearPagSigTipoPersonal = bloquearPagSigTipoPersonal;
    }

    public boolean isBloquearPagAntTipoPersonal() {
        return bloquearPagAntTipoPersonal;
    }

    public void setBloquearPagAntTipoPersonal(boolean bloquearPagAntTipoPersonal) {
        this.bloquearPagAntTipoPersonal = bloquearPagAntTipoPersonal;
    }

    public String getCantidadRegistros() {
        return cantidadRegistros;
    }

    public void setCantidadRegistros(String cantidadRegistros) {
        this.cantidadRegistros = cantidadRegistros;
    }

}
