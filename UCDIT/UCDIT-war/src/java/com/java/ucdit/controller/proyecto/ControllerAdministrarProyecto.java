/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.controller.proyecto;

import com.java.ucdit.bo.interfaces.proyecto.AdministrarProyectoBOInterface;
import com.java.ucdit.entidades.Proyecto;
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
public class ControllerAdministrarProyecto implements Serializable {

    @EJB
    AdministrarProyectoBOInterface administrarProyectoBO;
    //
    private List<Proyecto> listaProyectos;
    private List<Proyecto> listaProyectosTemporal;
    private List<Proyecto> listaProyectosTabla;
    private int posicionProyectoTabla;
    private int tamTotalProyecto;
    private boolean bloquearPagSigProyecto, bloquearPagAntProyecto;
    private String cantidadRegistros;
    private int tipoConsulta;
    private String paginaAnterior;

    public ControllerAdministrarProyecto() {
    }

    @PostConstruct
    public void init() {
        tipoConsulta = 1;
    }

    //1 - TODOS 2 - ACTIVOS 3 - TERMINADOS
    public void actualizarTipoConsulta() {
        if (tipoConsulta == 1) {
            listaProyectos = administrarProyectoBO.consultarProyectosRegistrados();
            listaProyectosTemporal = listaProyectos;
        } else {
            listaProyectos = administrarProyectoBO.consultarProyectosRegistrados();
            listaProyectosTemporal = new ArrayList<Proyecto>();
            if (tipoConsulta == 2) {
                if (null != listaProyectos) {
                    for (int i = 0; i < listaProyectos.size(); i++) {
                        if (listaProyectos.get(i).getEstadoproyecto() == true) {
                            listaProyectosTemporal.add(listaProyectos.get(i));
                        }
                    }
                }
            } else {
                if (null != listaProyectos) {
                    for (int i = 0; i < listaProyectos.size(); i++) {
                        if (listaProyectos.get(i).getEstadoproyecto() == false) {
                            listaProyectosTemporal.add(listaProyectos.get(i));
                        }
                    }
                }
            }
        }
        cargarInformacionTabla();
    }

    private void iniciarDatosTabla() {
        cantidadRegistros = "N/A";
        listaProyectosTemporal = null;
        listaProyectos = null;
        listaProyectosTabla = null;
        posicionProyectoTabla = 0;
        tamTotalProyecto = 0;
        tipoConsulta = 1;
        bloquearPagAntProyecto = true;
        bloquearPagSigProyecto = true;
    }

    public void buscarProyectoRegistrador(String page) {
        this.paginaAnterior = page;
        iniciarDatosTabla();
        try {
            actualizarTipoConsulta();
            cargarInformacionTabla();
        } catch (Exception e) {
            System.out.println("Error ControllerAdministrarProyecto buscarProyectoRegistrador : " + e.toString());
        }
    }

    private void cargarInformacionTabla() {
        if (listaProyectosTemporal != null) {
            if (listaProyectosTemporal.size() > 0) {
                listaProyectosTabla = new ArrayList<Proyecto>();
                tamTotalProyecto = listaProyectosTemporal.size();
                posicionProyectoTabla = 0;
                cantidadRegistros = String.valueOf(tamTotalProyecto);
                cargarDatosTablaProyecto();
            } else {
                listaProyectosTabla = null;
                tamTotalProyecto = 0;
                posicionProyectoTabla = 0;
                bloquearPagAntProyecto = true;
                cantidadRegistros = String.valueOf(tamTotalProyecto);
                bloquearPagSigProyecto = true;
            }
        } else {
            listaProyectosTabla = null;
            tamTotalProyecto = 0;
            posicionProyectoTabla = 0;
            cantidadRegistros = String.valueOf(tamTotalProyecto);
            bloquearPagAntProyecto = true;
            bloquearPagSigProyecto = true;
        }
    }

    private void cargarDatosTablaProyecto() {
        if (tamTotalProyecto < 5) {
            for (int i = 0; i < tamTotalProyecto; i++) {
                listaProyectosTabla.add(listaProyectosTemporal.get(i));
            }
            bloquearPagSigProyecto = true;
            bloquearPagAntProyecto = true;
        } else {
            for (int i = 0; i < 5; i++) {
                listaProyectosTabla.add(listaProyectosTemporal.get(i));
            }
            bloquearPagSigProyecto = false;
            bloquearPagAntProyecto = true;
        }
    }

    public void cargarPaginaSiguienteProyecto() {
        listaProyectosTabla = new ArrayList<Proyecto>();
        posicionProyectoTabla = posicionProyectoTabla + 5;
        int diferencia = tamTotalProyecto - posicionProyectoTabla;
        if (diferencia > 5) {
            for (int i = posicionProyectoTabla; i < (posicionProyectoTabla + 5); i++) {
                listaProyectosTabla.add(listaProyectosTemporal.get(i));
            }
            bloquearPagSigProyecto = false;
            bloquearPagAntProyecto = false;
        } else {
            for (int i = posicionProyectoTabla; i < (posicionProyectoTabla + diferencia); i++) {
                listaProyectosTabla.add(listaProyectosTemporal.get(i));
            }
            bloquearPagSigProyecto = true;
            bloquearPagAntProyecto = false;
        }
    }

    public void cargarPaginaAnteriorProyecto() {
        listaProyectosTabla = new ArrayList<Proyecto>();
        posicionProyectoTabla = posicionProyectoTabla - 5;
        int diferencia = tamTotalProyecto - posicionProyectoTabla;
        if (diferencia == tamTotalProyecto) {
            for (int i = posicionProyectoTabla; i < (posicionProyectoTabla + 5); i++) {
                listaProyectosTabla.add(listaProyectosTemporal.get(i));
            }
            bloquearPagSigProyecto = false;
            bloquearPagAntProyecto = true;
        } else {
            for (int i = posicionProyectoTabla; i < (posicionProyectoTabla + 5); i++) {
                listaProyectosTabla.add(listaProyectosTemporal.get(i));
            }
            bloquearPagSigProyecto = false;
            bloquearPagAntProyecto = false;
        }
    }

    public String limpiarProcesoBusqueda() {
        listaProyectosTemporal = null;
        listaProyectos = null;
        listaProyectosTabla = null;
        posicionProyectoTabla = 0;
        tamTotalProyecto = 0;
        tipoConsulta = 1;
        bloquearPagAntProyecto = true;
        bloquearPagSigProyecto = true;
        cantidadRegistros = "N/A";
        return paginaAnterior;
    }

    public String paginaDetalles() {
        limpiarProcesoBusqueda();
        return "detallesproyecto";
    }

    public String paginaBitacora() {
        limpiarProcesoBusqueda();
        return "administrarbitacora";
    }

    // GET - SET
    public List<Proyecto> getListaProyectos() {
        return listaProyectos;
    }

    public void setListaProyectos(List<Proyecto> listaProyectos) {
        this.listaProyectos = listaProyectos;
    }

    public List<Proyecto> getListaProyectosTemporal() {
        return listaProyectosTemporal;
    }

    public void setListaProyectosTemporal(List<Proyecto> listaProyectosTemporal) {
        this.listaProyectosTemporal = listaProyectosTemporal;
    }

    public List<Proyecto> getListaProyectosTabla() {
        return listaProyectosTabla;
    }

    public void setListaProyectosTabla(List<Proyecto> listaProyectosTabla) {
        this.listaProyectosTabla = listaProyectosTabla;
    }

    public int getPosicionProyectoTabla() {
        return posicionProyectoTabla;
    }

    public void setPosicionProyectoTabla(int posicionProyectoTabla) {
        this.posicionProyectoTabla = posicionProyectoTabla;
    }

    public int getTamTotalProyecto() {
        return tamTotalProyecto;
    }

    public void setTamTotalProyecto(int tamTotalProyecto) {
        this.tamTotalProyecto = tamTotalProyecto;
    }

    public boolean isBloquearPagSigProyecto() {
        return bloquearPagSigProyecto;
    }

    public void setBloquearPagSigProyecto(boolean bloquearPagSigProyecto) {
        this.bloquearPagSigProyecto = bloquearPagSigProyecto;
    }

    public boolean isBloquearPagAntProyecto() {
        return bloquearPagAntProyecto;
    }

    public void setBloquearPagAntProyecto(boolean bloquearPagAntProyecto) {
        this.bloquearPagAntProyecto = bloquearPagAntProyecto;
    }

    public String getCantidadRegistros() {
        return cantidadRegistros;
    }

    public void setCantidadRegistros(String cantidadRegistros) {
        this.cantidadRegistros = cantidadRegistros;
    }

    public int getTipoConsulta() {
        return tipoConsulta;
    }

    public void setTipoConsulta(int tipoConsulta) {
        this.tipoConsulta = tipoConsulta;
    }

}
