/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.controller.proyecto;

import com.java.ucdit.bo.interfaces.proyecto.AdministrarEquipoPorProyectoBOInterface;
import com.java.ucdit.entidades.EquipoPorProyecto;
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
public class ControllerAdministrarEquipoPorProyecto implements Serializable {

    @EJB
    AdministrarEquipoPorProyectoBOInterface administrarEquipoPorProyectoBO;
    //
    private BigInteger idProyecto;
    //aiuaw
    private List<EquipoPorProyecto> listaEquipoPorProyectos;
    private List<EquipoPorProyecto> listaEquipoPorProyectosTabla;
    private int posicionEquipoPorProyectoTabla;
    private int tamTotalEquipoPorProyecto;
    private boolean bloquearPagSigEquipoPorProyecto, bloquearPagAntEquipoPorProyecto;
    private String cantidadRegistros;

    public ControllerAdministrarEquipoPorProyecto() {
    }

    @PostConstruct
    public void init() {
    }

    public void recibirIdProyecto(BigInteger idProyecto) {
        this.idProyecto = idProyecto;
        buscarEquipoPorProyectoRegistrador();
    }

    private void iniciarDatosTabla() {
        cantidadRegistros = "N/A";
        listaEquipoPorProyectos = null;
        listaEquipoPorProyectos = null;
        listaEquipoPorProyectosTabla = null;
        posicionEquipoPorProyectoTabla = 0;
        tamTotalEquipoPorProyecto = 0;
        bloquearPagAntEquipoPorProyecto = true;
        bloquearPagSigEquipoPorProyecto = true;
    }

    public void buscarEquipoPorProyectoRegistrador() {
        iniciarDatosTabla();
        try {
            listaEquipoPorProyectos = null;
            listaEquipoPorProyectos = administrarEquipoPorProyectoBO.consultarEquipoPorProyectosRegistrados(idProyecto);
            if (listaEquipoPorProyectos != null) {
                if (listaEquipoPorProyectos.size() > 0) {
                    listaEquipoPorProyectosTabla = new ArrayList<EquipoPorProyecto>();
                    tamTotalEquipoPorProyecto = listaEquipoPorProyectos.size();
                    posicionEquipoPorProyectoTabla = 0;
                    cantidadRegistros = String.valueOf(tamTotalEquipoPorProyecto);
                    cargarDatosTablaEquipoPorProyecto();
                } else {
                    listaEquipoPorProyectosTabla = null;
                    tamTotalEquipoPorProyecto = 0;
                    posicionEquipoPorProyectoTabla = 0;
                    bloquearPagAntEquipoPorProyecto = true;
                    cantidadRegistros = String.valueOf(tamTotalEquipoPorProyecto);
                    bloquearPagSigEquipoPorProyecto = true;
                }
            } else {
                listaEquipoPorProyectosTabla = null;
                tamTotalEquipoPorProyecto = 0;
                posicionEquipoPorProyectoTabla = 0;
                cantidadRegistros = String.valueOf(tamTotalEquipoPorProyecto);
                bloquearPagAntEquipoPorProyecto = true;
                bloquearPagSigEquipoPorProyecto = true;
            }
        } catch (Exception e) {
            System.out.println("Error ControllerAdministrarEquipoPorProyecto buscarEquipoPorProyectoRegistrador : " + e.toString());
        }
    }

    private void cargarDatosTablaEquipoPorProyecto() {
        if (tamTotalEquipoPorProyecto < 10) {
            for (int i = 0; i < tamTotalEquipoPorProyecto; i++) {
                listaEquipoPorProyectosTabla.add(listaEquipoPorProyectos.get(i));
            }
            bloquearPagSigEquipoPorProyecto = true;
            bloquearPagAntEquipoPorProyecto = true;
        } else {
            for (int i = 0; i < 10; i++) {
                listaEquipoPorProyectosTabla.add(listaEquipoPorProyectos.get(i));
            }
            bloquearPagSigEquipoPorProyecto = false;
            bloquearPagAntEquipoPorProyecto = true;
        }
    }

    public void cargarPaginaSiguienteEquipoPorProyecto() {
        listaEquipoPorProyectosTabla = new ArrayList<EquipoPorProyecto>();
        posicionEquipoPorProyectoTabla = posicionEquipoPorProyectoTabla + 10;
        int diferencia = tamTotalEquipoPorProyecto - posicionEquipoPorProyectoTabla;
        if (diferencia > 10) {
            for (int i = posicionEquipoPorProyectoTabla; i < (posicionEquipoPorProyectoTabla + 10); i++) {
                listaEquipoPorProyectosTabla.add(listaEquipoPorProyectos.get(i));
            }
            bloquearPagSigEquipoPorProyecto = false;
            bloquearPagAntEquipoPorProyecto = false;
        } else {
            for (int i = posicionEquipoPorProyectoTabla; i < (posicionEquipoPorProyectoTabla + diferencia); i++) {
                listaEquipoPorProyectosTabla.add(listaEquipoPorProyectos.get(i));
            }
            bloquearPagSigEquipoPorProyecto = true;
            bloquearPagAntEquipoPorProyecto = false;
        }
    }

    public void cargarPaginaAnteriorEquipoPorProyecto() {
        listaEquipoPorProyectosTabla = new ArrayList<EquipoPorProyecto>();
        posicionEquipoPorProyectoTabla = posicionEquipoPorProyectoTabla - 10;
        int diferencia = tamTotalEquipoPorProyecto - posicionEquipoPorProyectoTabla;
        if (diferencia == tamTotalEquipoPorProyecto) {
            for (int i = posicionEquipoPorProyectoTabla; i < (posicionEquipoPorProyectoTabla + 10); i++) {
                listaEquipoPorProyectosTabla.add(listaEquipoPorProyectos.get(i));
            }
            bloquearPagSigEquipoPorProyecto = false;
            bloquearPagAntEquipoPorProyecto = true;
        } else {
            for (int i = posicionEquipoPorProyectoTabla; i < (posicionEquipoPorProyectoTabla + 10); i++) {
                listaEquipoPorProyectosTabla.add(listaEquipoPorProyectos.get(i));
            }
            bloquearPagSigEquipoPorProyecto = false;
            bloquearPagAntEquipoPorProyecto = false;
        }
    }

    public void limpiarProcesoBusqueda() {
        listaEquipoPorProyectos = null;
        listaEquipoPorProyectos = null;
        listaEquipoPorProyectosTabla = null;
        posicionEquipoPorProyectoTabla = 0;
        tamTotalEquipoPorProyecto = 0;
        bloquearPagAntEquipoPorProyecto = true;
        bloquearPagSigEquipoPorProyecto = true;
        cantidadRegistros = "N/A";
    }

    public String regresarProyectoDetalles() {
        limpiarProcesoBusqueda();
        return "detallesproyecto";
    }

    public String paginaDetalles() {
        limpiarProcesoBusqueda();
        return "detallesequipoporproyecto";
    }

    public String paginaNuevoRegistro() {
        limpiarProcesoBusqueda();
        return "registrarequipoporproyecto";
    }

    // GET - SET
    public BigInteger getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(BigInteger idProyecto) {
        this.idProyecto = idProyecto;
    }

    public List<EquipoPorProyecto> getListaEquipoPorProyectos() {
        return listaEquipoPorProyectos;
    }

    public void setListaEquipoPorProyectos(List<EquipoPorProyecto> listaEquipoPorProyectos) {
        this.listaEquipoPorProyectos = listaEquipoPorProyectos;
    }

    public List<EquipoPorProyecto> getListaEquipoPorProyectosTabla() {
        return listaEquipoPorProyectosTabla;
    }

    public void setListaEquipoPorProyectosTabla(List<EquipoPorProyecto> listaEquipoPorProyectosTabla) {
        this.listaEquipoPorProyectosTabla = listaEquipoPorProyectosTabla;
    }

    public int getPosicionEquipoPorProyectoTabla() {
        return posicionEquipoPorProyectoTabla;
    }

    public void setPosicionEquipoPorProyectoTabla(int posicionEquipoPorProyectoTabla) {
        this.posicionEquipoPorProyectoTabla = posicionEquipoPorProyectoTabla;
    }

    public int getTamTotalEquipoPorProyecto() {
        return tamTotalEquipoPorProyecto;
    }

    public void setTamTotalEquipoPorProyecto(int tamTotalEquipoPorProyecto) {
        this.tamTotalEquipoPorProyecto = tamTotalEquipoPorProyecto;
    }

    public boolean isBloquearPagSigEquipoPorProyecto() {
        return bloquearPagSigEquipoPorProyecto;
    }

    public void setBloquearPagSigEquipoPorProyecto(boolean bloquearPagSigEquipoPorProyecto) {
        this.bloquearPagSigEquipoPorProyecto = bloquearPagSigEquipoPorProyecto;
    }

    public boolean isBloquearPagAntEquipoPorProyecto() {
        return bloquearPagAntEquipoPorProyecto;
    }

    public void setBloquearPagAntEquipoPorProyecto(boolean bloquearPagAntEquipoPorProyecto) {
        this.bloquearPagAntEquipoPorProyecto = bloquearPagAntEquipoPorProyecto;
    }

    public String getCantidadRegistros() {
        return cantidadRegistros;
    }

    public void setCantidadRegistros(String cantidadRegistros) {
        this.cantidadRegistros = cantidadRegistros;
    }

}
