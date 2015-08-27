/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.controller.usuario;

import com.java.ucdit.bo.interfaces.usuario.AdministrarSupervisorBOInterface;
import com.java.ucdit.entidades.Supervisor;
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
public class ControllerAdministrarSupervisor implements Serializable {

    @EJB
    AdministrarSupervisorBOInterface administrarSupervisorBO;
    //
    private List<Supervisor> listaSupervisor;
    private List<Supervisor> listaSupervisorTabla;
    private int posicionSupervisorTabla;
    private int tamTotalSupervisor;
    private boolean bloquearPagSigSupervisor, bloquearPagAntSupervisor;
    private String cantidadRegistros;

    public ControllerAdministrarSupervisor() {
    }

    @PostConstruct
    public void init() {
        buscarSupervisorRegistrador();
    }

    private void iniciarDatosTabla() {
        cantidadRegistros = "N/A";
        listaSupervisor = null;
        listaSupervisorTabla = null;
        posicionSupervisorTabla = 0;
        tamTotalSupervisor = 0;
        bloquearPagAntSupervisor = true;
        bloquearPagSigSupervisor = true;
    }

    public void buscarSupervisorRegistrador() {
        iniciarDatosTabla();
        try {
            listaSupervisor = null;
            listaSupervisor = administrarSupervisorBO.consultarSupervisoresRegistrados();
            if (listaSupervisor != null) {
                if (listaSupervisor.size() > 0) {
                    listaSupervisorTabla = new ArrayList<Supervisor>();
                    tamTotalSupervisor = listaSupervisor.size();
                    posicionSupervisorTabla = 0;
                    cantidadRegistros = String.valueOf(tamTotalSupervisor);
                    cargarDatosTablaSupervisor();
                } else {
                    listaSupervisorTabla = null;
                    tamTotalSupervisor = 0;
                    posicionSupervisorTabla = 0;
                    bloquearPagAntSupervisor = true;
                    cantidadRegistros = String.valueOf(tamTotalSupervisor);
                    bloquearPagSigSupervisor = true;
                }
            } else {
                listaSupervisorTabla = null;
                tamTotalSupervisor = 0;
                posicionSupervisorTabla = 0;
                cantidadRegistros = String.valueOf(tamTotalSupervisor);
                bloquearPagAntSupervisor = true;
                bloquearPagSigSupervisor = true;
            }
        } catch (Exception e) {
            System.out.println("Error ControllerAdministrarSupervisor buscarSupervisorRegistrador : " + e.toString());
        }
    }

    private void cargarDatosTablaSupervisor() {
        if (tamTotalSupervisor < 5) {
            for (int i = 0; i < tamTotalSupervisor; i++) {
                listaSupervisorTabla.add(listaSupervisor.get(i));
            }
            bloquearPagSigSupervisor = true;
            bloquearPagAntSupervisor = true;
        } else {
            for (int i = 0; i < 5; i++) {
                listaSupervisorTabla.add(listaSupervisor.get(i));
            }
            bloquearPagSigSupervisor = false;
            bloquearPagAntSupervisor = true;
        }
    }

    public void cargarPaginaSiguienteSupervisor() {
        listaSupervisorTabla = new ArrayList<Supervisor>();
        posicionSupervisorTabla = posicionSupervisorTabla + 5;
        int diferencia = tamTotalSupervisor - posicionSupervisorTabla;
        if (diferencia > 5) {
            for (int i = posicionSupervisorTabla; i < (posicionSupervisorTabla + 5); i++) {
                listaSupervisorTabla.add(listaSupervisor.get(i));
            }
            bloquearPagSigSupervisor = false;
            bloquearPagAntSupervisor = false;
        } else {
            for (int i = posicionSupervisorTabla; i < (posicionSupervisorTabla + diferencia); i++) {
                listaSupervisorTabla.add(listaSupervisor.get(i));
            }
            bloquearPagSigSupervisor = true;
            bloquearPagAntSupervisor = false;
        }
    }

    public void cargarPaginaAnteriorSupervisor() {
        listaSupervisorTabla = new ArrayList<Supervisor>();
        posicionSupervisorTabla = posicionSupervisorTabla - 5;
        int diferencia = tamTotalSupervisor - posicionSupervisorTabla;
        if (diferencia == tamTotalSupervisor) {
            for (int i = posicionSupervisorTabla; i < (posicionSupervisorTabla + 5); i++) {
                listaSupervisorTabla.add(listaSupervisor.get(i));
            }
            bloquearPagSigSupervisor = false;
            bloquearPagAntSupervisor = true;
        } else {
            for (int i = posicionSupervisorTabla; i < (posicionSupervisorTabla + 5); i++) {
                listaSupervisorTabla.add(listaSupervisor.get(i));
            }
            bloquearPagSigSupervisor = false;
            bloquearPagAntSupervisor = false;
        }
    }

    public void limpiarProcesoBusqueda() {
        listaSupervisor = null;
        listaSupervisorTabla = null;
        posicionSupervisorTabla = 0;
        tamTotalSupervisor = 0;
        bloquearPagAntSupervisor = true;
        bloquearPagSigSupervisor = true;
        cantidadRegistros = "N/A";
    }

    public String paginaDetalles() {
        limpiarProcesoBusqueda();
        return "detallessupervisor";
    }

    // GET - SET
    public List<Supervisor> getListaSupervisor() {
        return listaSupervisor;
    }

    public void setListaSupervisor(List<Supervisor> listaSupervisor) {
        this.listaSupervisor = listaSupervisor;
    }

    public List<Supervisor> getListaSupervisorTabla() {
        return listaSupervisorTabla;
    }

    public void setListaSupervisorTabla(List<Supervisor> listaSupervisorTabla) {
        this.listaSupervisorTabla = listaSupervisorTabla;
    }

    public int getPosicionSupervisorTabla() {
        return posicionSupervisorTabla;
    }

    public void setPosicionSupervisorTabla(int posicionSupervisorTabla) {
        this.posicionSupervisorTabla = posicionSupervisorTabla;
    }

    public int getTamTotalSupervisor() {
        return tamTotalSupervisor;
    }

    public void setTamTotalSupervisor(int tamTotalSupervisor) {
        this.tamTotalSupervisor = tamTotalSupervisor;
    }

    public boolean isBloquearPagSigSupervisor() {
        return bloquearPagSigSupervisor;
    }

    public void setBloquearPagSigSupervisor(boolean bloquearPagSigSupervisor) {
        this.bloquearPagSigSupervisor = bloquearPagSigSupervisor;
    }

    public boolean isBloquearPagAntSupervisor() {
        return bloquearPagAntSupervisor;
    }

    public void setBloquearPagAntSupervisor(boolean bloquearPagAntSupervisor) {
        this.bloquearPagAntSupervisor = bloquearPagAntSupervisor;
    }

    public String getCantidadRegistros() {
        return cantidadRegistros;
    }

    public void setCantidadRegistros(String cantidadRegistros) {
        this.cantidadRegistros = cantidadRegistros;
    }

}
