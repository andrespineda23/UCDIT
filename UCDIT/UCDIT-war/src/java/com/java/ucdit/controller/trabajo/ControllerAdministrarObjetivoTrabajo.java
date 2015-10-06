/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.controller.trabajo;

import com.java.ucdit.bo.interfaces.trabajo.AdministrarObjetivoTrabajoBOInterface;
import com.java.ucdit.entidades.ObjetivoPorPersonalProyecto;
import com.java.ucdit.entidades.PersonalInterno;
import com.java.ucdit.utilidades.Utilidades;
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
public class ControllerAdministrarObjetivoTrabajo implements Serializable {

    @EJB
    AdministrarObjetivoTrabajoBOInterface administrarObjetivoTrabajoBO;
    //
    private List<ObjetivoPorPersonalProyecto> listaObjetivoTrabajo;
    private List<ObjetivoPorPersonalProyecto> listaObjetivoTrabajoTabla;
    private int posicionObjetivoTrabajoTabla;
    private int tamTotalObjetivoTrabajo;
    private boolean bloquearPagSigObjetivoTrabajo, bloquearPagAntObjetivoTrabajo;
    private String cantidadRegistros;
    private List<PersonalInterno> listaPeresonalInterno;
    private PersonalInterno personalInterno;

    public ControllerAdministrarObjetivoTrabajo() {
    }

    @PostConstruct
    public void init() {
        buscarObjetivoTrabajoRegistrador();
    }

    private void iniciarDatosTabla() {
        cantidadRegistros = "N/A";
        listaObjetivoTrabajo = null;
        listaObjetivoTrabajoTabla = null;
        posicionObjetivoTrabajoTabla = 0;
        tamTotalObjetivoTrabajo = 0;
        bloquearPagAntObjetivoTrabajo = true;
        bloquearPagSigObjetivoTrabajo = true;
        personalInterno = null;
        listaPeresonalInterno = administrarObjetivoTrabajoBO.obtenerPersonalInternoRegistrado();
    }

    public void actualizarPersonalInterno() {
        listaObjetivoTrabajo = null;
        listaObjetivoTrabajo = new ArrayList<ObjetivoPorPersonalProyecto>();
        if (Utilidades.validarNulo(personalInterno)) {
            listaObjetivoTrabajo = administrarObjetivoTrabajoBO.obtenerObjetivoPorPersonalProyectoPorPersonal(personalInterno.getIdpersonalinterno());
        } else {
            listaObjetivoTrabajo = administrarObjetivoTrabajoBO.consultarObjetivoPorPersonalProyectoRegistrado();
        }
    }

    public void buscarObjetivoTrabajoRegistrador() {
        iniciarDatosTabla();
        try {
            actualizarPersonalInterno();
            cargarAListasValores();
        } catch (Exception e) {
            System.out.println("Error ControllerAdministrarObjetivoTrabajo buscarObjetivoTrabajoRegistrador : " + e.toString());
        }
    }

    private void cargarAListasValores() {
        if (listaObjetivoTrabajo != null) {
            if (listaObjetivoTrabajo.size() > 0) {
                listaObjetivoTrabajoTabla = new ArrayList<ObjetivoPorPersonalProyecto>();
                tamTotalObjetivoTrabajo = listaObjetivoTrabajo.size();
                posicionObjetivoTrabajoTabla = 0;
                cantidadRegistros = String.valueOf(tamTotalObjetivoTrabajo);
                cargarDatosTablaObjetivoTrabajo();
            } else {
                listaObjetivoTrabajoTabla = null;
                tamTotalObjetivoTrabajo = 0;
                posicionObjetivoTrabajoTabla = 0;
                bloquearPagAntObjetivoTrabajo = true;
                cantidadRegistros = String.valueOf(tamTotalObjetivoTrabajo);
                bloquearPagSigObjetivoTrabajo = true;
            }
        } else {
            listaObjetivoTrabajoTabla = null;
            tamTotalObjetivoTrabajo = 0;
            posicionObjetivoTrabajoTabla = 0;
            cantidadRegistros = String.valueOf(tamTotalObjetivoTrabajo);
            bloquearPagAntObjetivoTrabajo = true;
            bloquearPagSigObjetivoTrabajo = true;
        }
    }

    private void cargarDatosTablaObjetivoTrabajo() {
        if (tamTotalObjetivoTrabajo < 10) {
            for (int i = 0; i < tamTotalObjetivoTrabajo; i++) {
                listaObjetivoTrabajoTabla.add(listaObjetivoTrabajo.get(i));
            }
            bloquearPagSigObjetivoTrabajo = true;
            bloquearPagAntObjetivoTrabajo = true;
        } else {
            for (int i = 0; i < 10; i++) {
                listaObjetivoTrabajoTabla.add(listaObjetivoTrabajo.get(i));
            }
            bloquearPagSigObjetivoTrabajo = false;
            bloquearPagAntObjetivoTrabajo = true;
        }
    }

    public void cargarPaginaSiguienteObjetivoTrabajo() {
        listaObjetivoTrabajoTabla = new ArrayList<ObjetivoPorPersonalProyecto>();
        posicionObjetivoTrabajoTabla = posicionObjetivoTrabajoTabla + 10;
        int diferencia = tamTotalObjetivoTrabajo - posicionObjetivoTrabajoTabla;
        if (diferencia > 10) {
            for (int i = posicionObjetivoTrabajoTabla; i < (posicionObjetivoTrabajoTabla + 10); i++) {
                listaObjetivoTrabajoTabla.add(listaObjetivoTrabajo.get(i));
            }
            bloquearPagSigObjetivoTrabajo = false;
            bloquearPagAntObjetivoTrabajo = false;
        } else {
            for (int i = posicionObjetivoTrabajoTabla; i < (posicionObjetivoTrabajoTabla + diferencia); i++) {
                listaObjetivoTrabajoTabla.add(listaObjetivoTrabajo.get(i));
            }
            bloquearPagSigObjetivoTrabajo = true;
            bloquearPagAntObjetivoTrabajo = false;
        }
    }

    public void cargarPaginaAnteriorObjetivoTrabajo() {
        listaObjetivoTrabajoTabla = new ArrayList<ObjetivoPorPersonalProyecto>();
        posicionObjetivoTrabajoTabla = posicionObjetivoTrabajoTabla - 10;
        int diferencia = tamTotalObjetivoTrabajo - posicionObjetivoTrabajoTabla;
        if (diferencia == tamTotalObjetivoTrabajo) {
            for (int i = posicionObjetivoTrabajoTabla; i < (posicionObjetivoTrabajoTabla + 10); i++) {
                listaObjetivoTrabajoTabla.add(listaObjetivoTrabajo.get(i));
            }
            bloquearPagSigObjetivoTrabajo = false;
            bloquearPagAntObjetivoTrabajo = true;
        } else {
            for (int i = posicionObjetivoTrabajoTabla; i < (posicionObjetivoTrabajoTabla + 10); i++) {
                listaObjetivoTrabajoTabla.add(listaObjetivoTrabajo.get(i));
            }
            bloquearPagSigObjetivoTrabajo = false;
            bloquearPagAntObjetivoTrabajo = false;
        }
    }

    public void limpiarProcesoBusqueda() {
        listaObjetivoTrabajo = null;
        listaObjetivoTrabajoTabla = null;
        posicionObjetivoTrabajoTabla = 0;
        tamTotalObjetivoTrabajo = 0;
        bloquearPagAntObjetivoTrabajo = true;
        bloquearPagSigObjetivoTrabajo = true;
        cantidadRegistros = "N/A";
        personalInterno = null;
    }

    public String paginaDetalles() {
        limpiarProcesoBusqueda();
        return "detallesobjetivoporpersonalproyecto";
    }

    // GET - SET
    public List<ObjetivoPorPersonalProyecto> getListaObjetivoTrabajo() {
        return listaObjetivoTrabajo;
    }

    public void setListaObjetivoTrabajo(List<ObjetivoPorPersonalProyecto> listaObjetivoTrabajo) {
        this.listaObjetivoTrabajo = listaObjetivoTrabajo;
    }

    public List<ObjetivoPorPersonalProyecto> getListaObjetivoTrabajoTabla() {
        return listaObjetivoTrabajoTabla;
    }

    public void setListaObjetivoTrabajoTabla(List<ObjetivoPorPersonalProyecto> listaObjetivoTrabajoTabla) {
        this.listaObjetivoTrabajoTabla = listaObjetivoTrabajoTabla;
    }

    public int getPosicionObjetivoTrabajoTabla() {
        return posicionObjetivoTrabajoTabla;
    }

    public void setPosicionObjetivoTrabajoTabla(int posicionObjetivoTrabajoTabla) {
        this.posicionObjetivoTrabajoTabla = posicionObjetivoTrabajoTabla;
    }

    public int getTamTotalObjetivoTrabajo() {
        return tamTotalObjetivoTrabajo;
    }

    public void setTamTotalObjetivoTrabajo(int tamTotalObjetivoTrabajo) {
        this.tamTotalObjetivoTrabajo = tamTotalObjetivoTrabajo;
    }

    public boolean isBloquearPagSigObjetivoTrabajo() {
        return bloquearPagSigObjetivoTrabajo;
    }

    public void setBloquearPagSigObjetivoTrabajo(boolean bloquearPagSigObjetivoTrabajo) {
        this.bloquearPagSigObjetivoTrabajo = bloquearPagSigObjetivoTrabajo;
    }

    public boolean isBloquearPagAntObjetivoTrabajo() {
        return bloquearPagAntObjetivoTrabajo;
    }

    public void setBloquearPagAntObjetivoTrabajo(boolean bloquearPagAntObjetivoTrabajo) {
        this.bloquearPagAntObjetivoTrabajo = bloquearPagAntObjetivoTrabajo;
    }

    public String getCantidadRegistros() {
        return cantidadRegistros;
    }

    public void setCantidadRegistros(String cantidadRegistros) {
        this.cantidadRegistros = cantidadRegistros;
    }

    public List<PersonalInterno> getListaPeresonalInterno() {
        return listaPeresonalInterno;
    }

    public void setListaPeresonalInterno(List<PersonalInterno> listaPeresonalInterno) {
        this.listaPeresonalInterno = listaPeresonalInterno;
    }

    public PersonalInterno getPersonalInterno() {
        return personalInterno;
    }

    public void setPersonalInterno(PersonalInterno personalInterno) {
        this.personalInterno = personalInterno;
    }

}
