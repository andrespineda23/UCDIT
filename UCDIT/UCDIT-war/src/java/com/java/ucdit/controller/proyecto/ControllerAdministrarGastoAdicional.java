/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.controller.proyecto;

import com.java.ucdit.bo.interfaces.proyecto.AdministrarGastoAdicionalProyectoBOInterface;
import com.java.ucdit.entidades.GastoAdicional;
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
public class ControllerAdministrarGastoAdicional implements Serializable {

    @EJB
    AdministrarGastoAdicionalProyectoBOInterface administrarGastoAdicionalProyectoBO;
    //
    private List<GastoAdicional> listaGastoAdicionalProyectos;
    private List<GastoAdicional> listaGastoAdicionalProyectosTabla;
    private int posicionGastoAdicionalProyectoTabla;
    private int tamTotalGastoAdicionalProyecto;
    private boolean bloquearPagSigGastoAdicionalProyecto, bloquearPagAntGastoAdicionalProyecto;
    private String cantidadRegistros;
    private BigInteger idProyecto;

    public ControllerAdministrarGastoAdicional() {
    }

    @PostConstruct
    public void init() {
    }

    private void iniciarDatosTabla() {
        cantidadRegistros = "N/A";
        listaGastoAdicionalProyectos = null;
        listaGastoAdicionalProyectosTabla = null;
        posicionGastoAdicionalProyectoTabla = 0;
        tamTotalGastoAdicionalProyecto = 0;
        bloquearPagAntGastoAdicionalProyecto = true;
        bloquearPagSigGastoAdicionalProyecto = true;
    }

    public void buscarGastoAdicionalProyectoRegistrador(BigInteger idProyecto) {
        this.idProyecto = idProyecto;
        iniciarDatosTabla();
        try {
            listaGastoAdicionalProyectos = null;
            listaGastoAdicionalProyectos = administrarGastoAdicionalProyectoBO.consultarGastoAdicionalPorIdProyecto(this.idProyecto);
            if (listaGastoAdicionalProyectos != null) {
                if (listaGastoAdicionalProyectos.size() > 0) {
                    listaGastoAdicionalProyectosTabla = new ArrayList<GastoAdicional>();
                    tamTotalGastoAdicionalProyecto = listaGastoAdicionalProyectos.size();
                    posicionGastoAdicionalProyectoTabla = 0;
                    cantidadRegistros = String.valueOf(tamTotalGastoAdicionalProyecto);
                    cargarDatosTablaGastoAdicionalProyecto();
                } else {
                    listaGastoAdicionalProyectosTabla = null;
                    tamTotalGastoAdicionalProyecto = 0;
                    posicionGastoAdicionalProyectoTabla = 0;
                    bloquearPagAntGastoAdicionalProyecto = true;
                    cantidadRegistros = String.valueOf(tamTotalGastoAdicionalProyecto);
                    bloquearPagSigGastoAdicionalProyecto = true;
                }
            } else {
                listaGastoAdicionalProyectosTabla = null;
                tamTotalGastoAdicionalProyecto = 0;
                posicionGastoAdicionalProyectoTabla = 0;
                cantidadRegistros = String.valueOf(tamTotalGastoAdicionalProyecto);
                bloquearPagAntGastoAdicionalProyecto = true;
                bloquearPagSigGastoAdicionalProyecto = true;
            }
        } catch (Exception e) {
            System.out.println("Error ControllerAdministrarGastoAdicionalProyecto buscarGastoAdicionalProyectoRegistrador : " + e.toString());
        }
    }

    private void cargarDatosTablaGastoAdicionalProyecto() {
        if (tamTotalGastoAdicionalProyecto < 5) {
            for (int i = 0; i < tamTotalGastoAdicionalProyecto; i++) {
                listaGastoAdicionalProyectosTabla.add(listaGastoAdicionalProyectos.get(i));
            }
            bloquearPagSigGastoAdicionalProyecto = true;
            bloquearPagAntGastoAdicionalProyecto = true;
        } else {
            for (int i = 0; i < 5; i++) {
                listaGastoAdicionalProyectosTabla.add(listaGastoAdicionalProyectos.get(i));
            }
            bloquearPagSigGastoAdicionalProyecto = false;
            bloquearPagAntGastoAdicionalProyecto = true;
        }
    }

    public void cargarPaginaSiguienteGastoAdicionalProyecto() {
        listaGastoAdicionalProyectosTabla = new ArrayList<GastoAdicional>();
        posicionGastoAdicionalProyectoTabla = posicionGastoAdicionalProyectoTabla + 5;
        int diferencia = tamTotalGastoAdicionalProyecto - posicionGastoAdicionalProyectoTabla;
        if (diferencia > 5) {
            for (int i = posicionGastoAdicionalProyectoTabla; i < (posicionGastoAdicionalProyectoTabla + 5); i++) {
                listaGastoAdicionalProyectosTabla.add(listaGastoAdicionalProyectos.get(i));
            }
            bloquearPagSigGastoAdicionalProyecto = false;
            bloquearPagAntGastoAdicionalProyecto = false;
        } else {
            for (int i = posicionGastoAdicionalProyectoTabla; i < (posicionGastoAdicionalProyectoTabla + diferencia); i++) {
                listaGastoAdicionalProyectosTabla.add(listaGastoAdicionalProyectos.get(i));
            }
            bloquearPagSigGastoAdicionalProyecto = true;
            bloquearPagAntGastoAdicionalProyecto = false;
        }
    }

    public void cargarPaginaAnteriorGastoAdicionalProyecto() {
        listaGastoAdicionalProyectosTabla = new ArrayList<GastoAdicional>();
        posicionGastoAdicionalProyectoTabla = posicionGastoAdicionalProyectoTabla - 5;
        int diferencia = tamTotalGastoAdicionalProyecto - posicionGastoAdicionalProyectoTabla;
        if (diferencia == tamTotalGastoAdicionalProyecto) {
            for (int i = posicionGastoAdicionalProyectoTabla; i < (posicionGastoAdicionalProyectoTabla + 5); i++) {
                listaGastoAdicionalProyectosTabla.add(listaGastoAdicionalProyectos.get(i));
            }
            bloquearPagSigGastoAdicionalProyecto = false;
            bloquearPagAntGastoAdicionalProyecto = true;
        } else {
            for (int i = posicionGastoAdicionalProyectoTabla; i < (posicionGastoAdicionalProyectoTabla + 5); i++) {
                listaGastoAdicionalProyectosTabla.add(listaGastoAdicionalProyectos.get(i));
            }
            bloquearPagSigGastoAdicionalProyecto = false;
            bloquearPagAntGastoAdicionalProyecto = false;
        }
    }

    public void limpiarProcesoBusqueda() {
        listaGastoAdicionalProyectos = null;
        listaGastoAdicionalProyectosTabla = null;
        posicionGastoAdicionalProyectoTabla = 0;
        tamTotalGastoAdicionalProyecto = 0;
        bloquearPagAntGastoAdicionalProyecto = true;
        bloquearPagSigGastoAdicionalProyecto = true;
        cantidadRegistros = "N/A";
    }

    public String nuevoRegistro() {
        limpiarProcesoBusqueda();
        return "registrargastoadicional";
    }

    public String paginaDetalles() {
        limpiarProcesoBusqueda();
        return "detallesgastoadicional";
    }

    public String paginaProyecto() {
        limpiarProcesoBusqueda();
        return "detallesproyecto";
    }

    // GET - SET
    public List<GastoAdicional> getListaGastoAdicionalProyectos() {
        return listaGastoAdicionalProyectos;
    }

    public void setListaGastoAdicionalProyectos(List<GastoAdicional> listaGastoAdicionalProyectos) {
        this.listaGastoAdicionalProyectos = listaGastoAdicionalProyectos;
    }

    public List<GastoAdicional> getListaGastoAdicionalProyectosTabla() {
        return listaGastoAdicionalProyectosTabla;
    }

    public void setListaGastoAdicionalProyectosTabla(List<GastoAdicional> listaGastoAdicionalProyectosTabla) {
        this.listaGastoAdicionalProyectosTabla = listaGastoAdicionalProyectosTabla;
    }

    public int getPosicionGastoAdicionalProyectoTabla() {
        return posicionGastoAdicionalProyectoTabla;
    }

    public void setPosicionGastoAdicionalProyectoTabla(int posicionGastoAdicionalProyectoTabla) {
        this.posicionGastoAdicionalProyectoTabla = posicionGastoAdicionalProyectoTabla;
    }

    public int getTamTotalGastoAdicionalProyecto() {
        return tamTotalGastoAdicionalProyecto;
    }

    public void setTamTotalGastoAdicionalProyecto(int tamTotalGastoAdicionalProyecto) {
        this.tamTotalGastoAdicionalProyecto = tamTotalGastoAdicionalProyecto;
    }

    public boolean isBloquearPagSigGastoAdicionalProyecto() {
        return bloquearPagSigGastoAdicionalProyecto;
    }

    public void setBloquearPagSigGastoAdicionalProyecto(boolean bloquearPagSigGastoAdicionalProyecto) {
        this.bloquearPagSigGastoAdicionalProyecto = bloquearPagSigGastoAdicionalProyecto;
    }

    public boolean isBloquearPagAntGastoAdicionalProyecto() {
        return bloquearPagAntGastoAdicionalProyecto;
    }

    public void setBloquearPagAntGastoAdicionalProyecto(boolean bloquearPagAntGastoAdicionalProyecto) {
        this.bloquearPagAntGastoAdicionalProyecto = bloquearPagAntGastoAdicionalProyecto;
    }

    public String getCantidadRegistros() {
        return cantidadRegistros;
    }

    public void setCantidadRegistros(String cantidadRegistros) {
        this.cantidadRegistros = cantidadRegistros;
    }

    public BigInteger getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(BigInteger idProyecto) {
        this.idProyecto = idProyecto;
    }

}
