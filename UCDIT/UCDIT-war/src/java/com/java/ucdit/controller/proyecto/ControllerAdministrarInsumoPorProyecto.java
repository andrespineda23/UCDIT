/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.controller.proyecto;

import com.java.ucdit.bo.interfaces.proyecto.AdministrarInsumoPorProyectoBOInterface;
import com.java.ucdit.entidades.InsumoPorProyecto;
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
public class ControllerAdministrarInsumoPorProyecto implements Serializable {

    @EJB
    AdministrarInsumoPorProyectoBOInterface administrarInsumoPorProyectoBO;
    //
    private BigInteger idProyecto;
    //aiuaw
    private List<InsumoPorProyecto> listaInsumoPorProyectos;
    private List<InsumoPorProyecto> listaInsumoPorProyectosTabla;
    private int posicionInsumoPorProyectoTabla;
    private int tamTotalInsumoPorProyecto;
    private boolean bloquearPagSigInsumoPorProyecto, bloquearPagAntInsumoPorProyecto;
    private String cantidadRegistros;

    public ControllerAdministrarInsumoPorProyecto() {
    }

    @PostConstruct
    public void init() {
    }

    public void recibirIdProyecto(BigInteger idProyecto) {
        this.idProyecto = idProyecto;
        buscarInsumoPorProyectoRegistrador();
    }

    private void iniciarDatosTabla() {
        cantidadRegistros = "N/A";
        listaInsumoPorProyectos = null;
        listaInsumoPorProyectos = null;
        listaInsumoPorProyectosTabla = null;
        posicionInsumoPorProyectoTabla = 0;
        tamTotalInsumoPorProyecto = 0;
        bloquearPagAntInsumoPorProyecto = true;
        bloquearPagSigInsumoPorProyecto = true;
    }

    public void buscarInsumoPorProyectoRegistrador() {
        iniciarDatosTabla();
        try {
            listaInsumoPorProyectos = null;
            listaInsumoPorProyectos = administrarInsumoPorProyectoBO.consultarInsumoPorProyectosRegistrados(idProyecto);
            if (listaInsumoPorProyectos != null) {
                if (listaInsumoPorProyectos.size() > 0) {
                    listaInsumoPorProyectosTabla = new ArrayList<InsumoPorProyecto>();
                    tamTotalInsumoPorProyecto = listaInsumoPorProyectos.size();
                    posicionInsumoPorProyectoTabla = 0;
                    cantidadRegistros = String.valueOf(tamTotalInsumoPorProyecto);
                    cargarDatosTablaInsumoPorProyecto();
                } else {
                    listaInsumoPorProyectosTabla = null;
                    tamTotalInsumoPorProyecto = 0;
                    posicionInsumoPorProyectoTabla = 0;
                    bloquearPagAntInsumoPorProyecto = true;
                    cantidadRegistros = String.valueOf(tamTotalInsumoPorProyecto);
                    bloquearPagSigInsumoPorProyecto = true;
                }
            } else {
                listaInsumoPorProyectosTabla = null;
                tamTotalInsumoPorProyecto = 0;
                posicionInsumoPorProyectoTabla = 0;
                cantidadRegistros = String.valueOf(tamTotalInsumoPorProyecto);
                bloquearPagAntInsumoPorProyecto = true;
                bloquearPagSigInsumoPorProyecto = true;
            }
        } catch (Exception e) {
            System.out.println("Error ControllerAdministrarInsumoPorProyecto buscarInsumoPorProyectoRegistrador : " + e.toString());
        }
    }

    private void cargarDatosTablaInsumoPorProyecto() {
        if (tamTotalInsumoPorProyecto < 10) {
            for (int i = 0; i < tamTotalInsumoPorProyecto; i++) {
                listaInsumoPorProyectosTabla.add(listaInsumoPorProyectos.get(i));
            }
            bloquearPagSigInsumoPorProyecto = true;
            bloquearPagAntInsumoPorProyecto = true;
        } else {
            for (int i = 0; i < 10; i++) {
                listaInsumoPorProyectosTabla.add(listaInsumoPorProyectos.get(i));
            }
            bloquearPagSigInsumoPorProyecto = false;
            bloquearPagAntInsumoPorProyecto = true;
        }
    }

    public void cargarPaginaSiguienteInsumoPorProyecto() {
        listaInsumoPorProyectosTabla = new ArrayList<InsumoPorProyecto>();
        posicionInsumoPorProyectoTabla = posicionInsumoPorProyectoTabla + 10;
        int diferencia = tamTotalInsumoPorProyecto - posicionInsumoPorProyectoTabla;
        if (diferencia > 10) {
            for (int i = posicionInsumoPorProyectoTabla; i < (posicionInsumoPorProyectoTabla + 10); i++) {
                listaInsumoPorProyectosTabla.add(listaInsumoPorProyectos.get(i));
            }
            bloquearPagSigInsumoPorProyecto = false;
            bloquearPagAntInsumoPorProyecto = false;
        } else {
            for (int i = posicionInsumoPorProyectoTabla; i < (posicionInsumoPorProyectoTabla + diferencia); i++) {
                listaInsumoPorProyectosTabla.add(listaInsumoPorProyectos.get(i));
            }
            bloquearPagSigInsumoPorProyecto = true;
            bloquearPagAntInsumoPorProyecto = false;
        }
    }

    public void cargarPaginaAnteriorInsumoPorProyecto() {
        listaInsumoPorProyectosTabla = new ArrayList<InsumoPorProyecto>();
        posicionInsumoPorProyectoTabla = posicionInsumoPorProyectoTabla - 10;
        int diferencia = tamTotalInsumoPorProyecto - posicionInsumoPorProyectoTabla;
        if (diferencia == tamTotalInsumoPorProyecto) {
            for (int i = posicionInsumoPorProyectoTabla; i < (posicionInsumoPorProyectoTabla + 10); i++) {
                listaInsumoPorProyectosTabla.add(listaInsumoPorProyectos.get(i));
            }
            bloquearPagSigInsumoPorProyecto = false;
            bloquearPagAntInsumoPorProyecto = true;
        } else {
            for (int i = posicionInsumoPorProyectoTabla; i < (posicionInsumoPorProyectoTabla + 10); i++) {
                listaInsumoPorProyectosTabla.add(listaInsumoPorProyectos.get(i));
            }
            bloquearPagSigInsumoPorProyecto = false;
            bloquearPagAntInsumoPorProyecto = false;
        }
    }

    public void limpiarProcesoBusqueda() {
        listaInsumoPorProyectos = null;
        listaInsumoPorProyectos = null;
        listaInsumoPorProyectosTabla = null;
        posicionInsumoPorProyectoTabla = 0;
        tamTotalInsumoPorProyecto = 0;
        bloquearPagAntInsumoPorProyecto = true;
        bloquearPagSigInsumoPorProyecto = true;
        cantidadRegistros = "N/A";
    }

    public String regresarProyectoDetalles() {
        limpiarProcesoBusqueda();
        return "detallesproyecto";
    }

    public String paginaDetalles() {
        limpiarProcesoBusqueda();
        return "detallesinsumoporproyecto";
    }

    public String paginaNuevoRegistro() {
        limpiarProcesoBusqueda();
        return "registrarinsumoporproyecto";
    }

    // GET - SET
    public List<InsumoPorProyecto> getListaInsumoPorProyectos() {
        return listaInsumoPorProyectos;
    }

    public void setListaInsumoPorProyectos(List<InsumoPorProyecto> listaInsumoPorProyectos) {
        this.listaInsumoPorProyectos = listaInsumoPorProyectos;
    }

    public List<InsumoPorProyecto> getListaInsumoPorProyectosTabla() {
        return listaInsumoPorProyectosTabla;
    }

    public void setListaInsumoPorProyectosTabla(List<InsumoPorProyecto> listaInsumoPorProyectosTabla) {
        this.listaInsumoPorProyectosTabla = listaInsumoPorProyectosTabla;
    }

    public int getPosicionInsumoPorProyectoTabla() {
        return posicionInsumoPorProyectoTabla;
    }

    public void setPosicionInsumoPorProyectoTabla(int posicionInsumoPorProyectoTabla) {
        this.posicionInsumoPorProyectoTabla = posicionInsumoPorProyectoTabla;
    }

    public int getTamTotalInsumoPorProyecto() {
        return tamTotalInsumoPorProyecto;
    }

    public void setTamTotalInsumoPorProyecto(int tamTotalInsumoPorProyecto) {
        this.tamTotalInsumoPorProyecto = tamTotalInsumoPorProyecto;
    }

    public boolean isBloquearPagSigInsumoPorProyecto() {
        return bloquearPagSigInsumoPorProyecto;
    }

    public void setBloquearPagSigInsumoPorProyecto(boolean bloquearPagSigInsumoPorProyecto) {
        this.bloquearPagSigInsumoPorProyecto = bloquearPagSigInsumoPorProyecto;
    }

    public boolean isBloquearPagAntInsumoPorProyecto() {
        return bloquearPagAntInsumoPorProyecto;
    }

    public void setBloquearPagAntInsumoPorProyecto(boolean bloquearPagAntInsumoPorProyecto) {
        this.bloquearPagAntInsumoPorProyecto = bloquearPagAntInsumoPorProyecto;
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
