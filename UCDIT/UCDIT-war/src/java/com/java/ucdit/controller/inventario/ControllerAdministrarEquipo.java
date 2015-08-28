/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.controller.inventario;

import com.java.ucdit.bo.interfaces.inventario.AdministrarEquipoTecnologicoBOInterface;
import com.java.ucdit.entidades.EquipoTecnologico;
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
public class ControllerAdministrarEquipo implements Serializable {

    @EJB
    AdministrarEquipoTecnologicoBOInterface administrarEquipoTecnologicosBO;
    //
    private List<EquipoTecnologico> listaEquipoTecnologicos;
    private List<EquipoTecnologico> listaEquipoTecnologicosTabla;
    private int posicionEquipoTecnologicoTabla;
    private int tamTotalEquipoTecnologico;
    private boolean bloquearPagSigEquipoTecnologico, bloquearPagAntEquipoTecnologico;
    private String cantidadRegistros;

    public ControllerAdministrarEquipo() {
    }

    @PostConstruct
    public void init() {
        buscarEquipoTecnologicosRegistrador();
    }

    private void iniciarDatosTabla() {
        cantidadRegistros = "N/A";
        listaEquipoTecnologicos = null;
        listaEquipoTecnologicosTabla = null;
        posicionEquipoTecnologicoTabla = 0;
        tamTotalEquipoTecnologico = 0;
        bloquearPagAntEquipoTecnologico = true;
        bloquearPagSigEquipoTecnologico = true;
    }

    public void buscarEquipoTecnologicosRegistrador() {
        iniciarDatosTabla();
        try {
            listaEquipoTecnologicos = null;
            listaEquipoTecnologicos = administrarEquipoTecnologicosBO.consultarEquiposTecnologicosRegistrados();
            if (listaEquipoTecnologicos != null) {
                if (listaEquipoTecnologicos.size() > 0) {
                    listaEquipoTecnologicosTabla = new ArrayList<EquipoTecnologico>();
                    tamTotalEquipoTecnologico = listaEquipoTecnologicos.size();
                    posicionEquipoTecnologicoTabla = 0;
                    cantidadRegistros = String.valueOf(tamTotalEquipoTecnologico);
                    cargarDatosTablaEquipoTecnologico();
                } else {
                    listaEquipoTecnologicosTabla = null;
                    tamTotalEquipoTecnologico = 0;
                    posicionEquipoTecnologicoTabla = 0;
                    bloquearPagAntEquipoTecnologico = true;
                    cantidadRegistros = String.valueOf(tamTotalEquipoTecnologico);
                    bloquearPagSigEquipoTecnologico = true;
                }
            } else {
                listaEquipoTecnologicosTabla = null;
                tamTotalEquipoTecnologico = 0;
                posicionEquipoTecnologicoTabla = 0;
                cantidadRegistros = String.valueOf(tamTotalEquipoTecnologico);
                bloquearPagAntEquipoTecnologico = true;
                bloquearPagSigEquipoTecnologico = true;
            }
        } catch (Exception e) {
            System.out.println("Error ControllerAdministrarEquipoTecnologico buscarEquipoTecnologicosRegistrador : " + e.toString());
        }
    }

    private void cargarDatosTablaEquipoTecnologico() {
        if (tamTotalEquipoTecnologico < 10) {
            for (int i = 0; i < tamTotalEquipoTecnologico; i++) {
                listaEquipoTecnologicosTabla.add(listaEquipoTecnologicos.get(i));
            }
            bloquearPagSigEquipoTecnologico = true;
            bloquearPagAntEquipoTecnologico = true;
        } else {
            for (int i = 0; i < 10; i++) {
                listaEquipoTecnologicosTabla.add(listaEquipoTecnologicos.get(i));
            }
            bloquearPagSigEquipoTecnologico = false;
            bloquearPagAntEquipoTecnologico = true;
        }
    }

    public void cargarPaginaSiguienteEquipoTecnologico() {
        listaEquipoTecnologicosTabla = new ArrayList<EquipoTecnologico>();
        posicionEquipoTecnologicoTabla = posicionEquipoTecnologicoTabla + 10;
        int diferencia = tamTotalEquipoTecnologico - posicionEquipoTecnologicoTabla;
        if (diferencia > 10) {
            for (int i = posicionEquipoTecnologicoTabla; i < (posicionEquipoTecnologicoTabla + 10); i++) {
                listaEquipoTecnologicosTabla.add(listaEquipoTecnologicos.get(i));
            }
            bloquearPagSigEquipoTecnologico = false;
            bloquearPagAntEquipoTecnologico = false;
        } else {
            for (int i = posicionEquipoTecnologicoTabla; i < (posicionEquipoTecnologicoTabla + diferencia); i++) {
                listaEquipoTecnologicosTabla.add(listaEquipoTecnologicos.get(i));
            }
            bloquearPagSigEquipoTecnologico = true;
            bloquearPagAntEquipoTecnologico = false;
        }
    }

    public void cargarPaginaAnteriorEquipoTecnologico() {
        listaEquipoTecnologicosTabla = new ArrayList<EquipoTecnologico>();
        posicionEquipoTecnologicoTabla = posicionEquipoTecnologicoTabla - 10;
        int diferencia = tamTotalEquipoTecnologico - posicionEquipoTecnologicoTabla;
        if (diferencia == tamTotalEquipoTecnologico) {
            for (int i = posicionEquipoTecnologicoTabla; i < (posicionEquipoTecnologicoTabla + 10); i++) {
                listaEquipoTecnologicosTabla.add(listaEquipoTecnologicos.get(i));
            }
            bloquearPagSigEquipoTecnologico = false;
            bloquearPagAntEquipoTecnologico = true;
        } else {
            for (int i = posicionEquipoTecnologicoTabla; i < (posicionEquipoTecnologicoTabla + 10); i++) {
                listaEquipoTecnologicosTabla.add(listaEquipoTecnologicos.get(i));
            }
            bloquearPagSigEquipoTecnologico = false;
            bloquearPagAntEquipoTecnologico = false;
        }
    }

    public void limpiarProcesoBusqueda() {
        listaEquipoTecnologicos = null;
        listaEquipoTecnologicosTabla = null;
        posicionEquipoTecnologicoTabla = 0;
        tamTotalEquipoTecnologico = 0;
        bloquearPagAntEquipoTecnologico = true;
        bloquearPagSigEquipoTecnologico = true;
        cantidadRegistros = "N/A";
    }

    public String paginaDetalles() {
        limpiarProcesoBusqueda();
        return "detallesequipo";
    }

    // GET - SET
    public List<EquipoTecnologico> getListaEquipoTecnologicos() {
        return listaEquipoTecnologicos;
    }

    public void setListaEquipoTecnologicos(List<EquipoTecnologico> listaEquipoTecnologicos) {
        this.listaEquipoTecnologicos = listaEquipoTecnologicos;
    }

    public List<EquipoTecnologico> getListaEquipoTecnologicosTabla() {
        return listaEquipoTecnologicosTabla;
    }

    public void setListaEquipoTecnologicosTabla(List<EquipoTecnologico> listaEquipoTecnologicosTabla) {
        this.listaEquipoTecnologicosTabla = listaEquipoTecnologicosTabla;
    }

    public int getPosicionEquipoTecnologicoTabla() {
        return posicionEquipoTecnologicoTabla;
    }

    public void setPosicionEquipoTecnologicoTabla(int posicionEquipoTecnologicoTabla) {
        this.posicionEquipoTecnologicoTabla = posicionEquipoTecnologicoTabla;
    }

    public int getTamTotalEquipoTecnologico() {
        return tamTotalEquipoTecnologico;
    }

    public void setTamTotalEquipoTecnologico(int tamTotalEquipoTecnologico) {
        this.tamTotalEquipoTecnologico = tamTotalEquipoTecnologico;
    }

    public boolean isBloquearPagSigEquipoTecnologico() {
        return bloquearPagSigEquipoTecnologico;
    }

    public void setBloquearPagSigEquipoTecnologico(boolean bloquearPagSigEquipoTecnologico) {
        this.bloquearPagSigEquipoTecnologico = bloquearPagSigEquipoTecnologico;
    }

    public boolean isBloquearPagAntEquipoTecnologico() {
        return bloquearPagAntEquipoTecnologico;
    }

    public void setBloquearPagAntEquipoTecnologico(boolean bloquearPagAntEquipoTecnologico) {
        this.bloquearPagAntEquipoTecnologico = bloquearPagAntEquipoTecnologico;
    }

    public String getCantidadRegistros() {
        return cantidadRegistros;
    }

    public void setCantidadRegistros(String cantidadRegistros) {
        this.cantidadRegistros = cantidadRegistros;
    }

}
