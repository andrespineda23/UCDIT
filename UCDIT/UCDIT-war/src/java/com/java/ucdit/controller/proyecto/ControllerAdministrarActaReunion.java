/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.controller.proyecto;

import com.java.ucdit.bo.interfaces.proyecto.AdministrarActaReunionBOInterface;
import com.java.ucdit.entidades.ActaReunion;
import com.java.ucdit.entidades.Proyecto;
import com.java.ucdit.utilidades.Utilidades;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ELECTRONICA
 */
@ManagedBean
@SessionScoped
public class ControllerAdministrarActaReunion implements Serializable {

    @EJB
    AdministrarActaReunionBOInterface administrarActaReunionBO;
    //
    private List<ActaReunion> listaActaReunion;
    private List<ActaReunion> listaActaReunionTemporal;
    private List<ActaReunion> listaActaReunionTabla;
    private int posicionActaReunionTabla;
    private int tamTotalActaReunion;
    private boolean bloquearPagSigActaReunion, bloquearPagAntActaReunion;
    private String cantidadRegistros;
    private List<Proyecto> listaProyecto;
    private Proyecto proyecto;

    public ControllerAdministrarActaReunion() {
    }

    @PostConstruct
    public void init() {
    }

    //1 - TODOS 2 - ACTIVOS 3 - TERMINADOS
    public void actualizarProyecto() {
        if (Utilidades.validarNulo(proyecto)) {
            listaActaReunion = administrarActaReunionBO.consultarActaReunionPorIdProyecto(proyecto.getIdproyecto());
            listaActaReunionTemporal = listaActaReunion;
            cargarInformacionTabla();
        } else {
            iniciarDatosTabla();
        }
    }

    private void iniciarDatosTabla() {
        proyecto = null;
        listaProyecto = administrarActaReunionBO.obtenerProyectosRegistrados();
        cantidadRegistros = "N/A";
        listaActaReunionTemporal = null;
        listaActaReunion = null;
        listaActaReunionTabla = null;
        posicionActaReunionTabla = 0;
        tamTotalActaReunion = 0;
        proyecto = null;
        bloquearPagAntActaReunion = true;
        bloquearPagSigActaReunion = true;
    }

    public void buscarActaReunionRegistrados() {
        iniciarDatosTabla();
        try {
            actualizarProyecto();
            cargarInformacionTabla();
        } catch (Exception e) {
            System.out.println("Error ControllerAdministrarActaReunion buscarActaReunionRegistrador : " + e.toString());
        }
    }

    private void cargarInformacionTabla() {
        if (listaActaReunionTemporal != null) {
            if (listaActaReunionTemporal.size() > 0) {
                listaActaReunionTabla = new ArrayList<ActaReunion>();
                tamTotalActaReunion = listaActaReunionTemporal.size();
                posicionActaReunionTabla = 0;
                cantidadRegistros = String.valueOf(tamTotalActaReunion);
                cargarDatosTablaActaReunion();
            } else {
                listaActaReunionTabla = null;
                tamTotalActaReunion = 0;
                posicionActaReunionTabla = 0;
                bloquearPagAntActaReunion = true;
                cantidadRegistros = String.valueOf(tamTotalActaReunion);
                bloquearPagSigActaReunion = true;
            }
        } else {
            listaActaReunionTabla = null;
            tamTotalActaReunion = 0;
            posicionActaReunionTabla = 0;
            cantidadRegistros = String.valueOf(tamTotalActaReunion);
            bloquearPagAntActaReunion = true;
            bloquearPagSigActaReunion = true;
        }
    }

    private void cargarDatosTablaActaReunion() {
        if (tamTotalActaReunion < 5) {
            for (int i = 0; i < tamTotalActaReunion; i++) {
                listaActaReunionTabla.add(listaActaReunionTemporal.get(i));
            }
            bloquearPagSigActaReunion = true;
            bloquearPagAntActaReunion = true;
        } else {
            for (int i = 0; i < 5; i++) {
                listaActaReunionTabla.add(listaActaReunionTemporal.get(i));
            }
            bloquearPagSigActaReunion = false;
            bloquearPagAntActaReunion = true;
        }
    }

    public void cargarPaginaSiguienteActaReunion() {
        listaActaReunionTabla = new ArrayList<ActaReunion>();
        posicionActaReunionTabla = posicionActaReunionTabla + 5;
        int diferencia = tamTotalActaReunion - posicionActaReunionTabla;
        if (diferencia > 5) {
            for (int i = posicionActaReunionTabla; i < (posicionActaReunionTabla + 5); i++) {
                listaActaReunionTabla.add(listaActaReunionTemporal.get(i));
            }
            bloquearPagSigActaReunion = false;
            bloquearPagAntActaReunion = false;
        } else {
            for (int i = posicionActaReunionTabla; i < (posicionActaReunionTabla + diferencia); i++) {
                listaActaReunionTabla.add(listaActaReunionTemporal.get(i));
            }
            bloquearPagSigActaReunion = true;
            bloquearPagAntActaReunion = false;
        }
    }

    public void cargarPaginaAnteriorActaReunion() {
        listaActaReunionTabla = new ArrayList<ActaReunion>();
        posicionActaReunionTabla = posicionActaReunionTabla - 5;
        int diferencia = tamTotalActaReunion - posicionActaReunionTabla;
        if (diferencia == tamTotalActaReunion) {
            for (int i = posicionActaReunionTabla; i < (posicionActaReunionTabla + 5); i++) {
                listaActaReunionTabla.add(listaActaReunionTemporal.get(i));
            }
            bloquearPagSigActaReunion = false;
            bloquearPagAntActaReunion = true;
        } else {
            for (int i = posicionActaReunionTabla; i < (posicionActaReunionTabla + 5); i++) {
                listaActaReunionTabla.add(listaActaReunionTemporal.get(i));
            }
            bloquearPagSigActaReunion = false;
            bloquearPagAntActaReunion = false;
        }
    }

    public void limpiarProcesoBusqueda() {
        listaActaReunionTemporal = null;
        listaActaReunion = null;
        listaActaReunionTabla = null;
        posicionActaReunionTabla = 0;
        tamTotalActaReunion = 0;
        proyecto = null;
        bloquearPagAntActaReunion = true;
        bloquearPagSigActaReunion = true;
        cantidadRegistros = "N/A";
    }

    private void cancelarFormulario() {
        listaActaReunionTemporal = null;
        listaActaReunion = null;
        listaActaReunionTabla = null;
        posicionActaReunionTabla = 0;
        tamTotalActaReunion = 0;
        proyecto = null;
        bloquearPagAntActaReunion = true;
        bloquearPagSigActaReunion = true;
        cantidadRegistros = "N/A";
        listaProyecto = null;
    }

    public void descargarActaReunion(String ruta) throws FileNotFoundException, IOException {
        File ficheroPDF = new File(ruta);
        FacesContext ctx = FacesContext.getCurrentInstance();
        FileInputStream fis = new FileInputStream(ficheroPDF);
        byte[] bytes = new byte[1000];
        int read = 0;
        if (!ctx.getResponseComplete()) {
            String fileName = ficheroPDF.getName();
            String contentType = "application/pdf";
            HttpServletResponse response = (HttpServletResponse) ctx.getExternalContext().getResponse();
            response.setContentType(contentType);
            response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
            ServletOutputStream out = response.getOutputStream();
            while ((read = fis.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
            ctx.responseComplete();
        }
    }

    public String paginaInicio() {
        cancelarFormulario();
        return "iniciosupervisor";
    }

    public String paginaDetalles() {
        cancelarFormulario();
        return "detallesactareunion";
    }

    public String nuevoRegistro() {
        cancelarFormulario();
        return "registraractareunion";
    }

    // GET - SET
    public List<ActaReunion> getListaActaReunion() {
        return listaActaReunion;
    }

    public void setListaActaReunion(List<ActaReunion> listaActaReunion) {
        this.listaActaReunion = listaActaReunion;
    }

    public List<ActaReunion> getListaActaReunionTemporal() {
        return listaActaReunionTemporal;
    }

    public void setListaActaReunionTemporal(List<ActaReunion> listaActaReunionTemporal) {
        this.listaActaReunionTemporal = listaActaReunionTemporal;
    }

    public List<ActaReunion> getListaActaReunionTabla() {
        return listaActaReunionTabla;
    }

    public void setListaActaReunionTabla(List<ActaReunion> listaActaReunionTabla) {
        this.listaActaReunionTabla = listaActaReunionTabla;
    }

    public int getPosicionActaReunionTabla() {
        return posicionActaReunionTabla;
    }

    public void setPosicionActaReunionTabla(int posicionActaReunionTabla) {
        this.posicionActaReunionTabla = posicionActaReunionTabla;
    }

    public int getTamTotalActaReunion() {
        return tamTotalActaReunion;
    }

    public void setTamTotalActaReunion(int tamTotalActaReunion) {
        this.tamTotalActaReunion = tamTotalActaReunion;
    }

    public boolean isBloquearPagSigActaReunion() {
        return bloquearPagSigActaReunion;
    }

    public void setBloquearPagSigActaReunion(boolean bloquearPagSigActaReunion) {
        this.bloquearPagSigActaReunion = bloquearPagSigActaReunion;
    }

    public boolean isBloquearPagAntActaReunion() {
        return bloquearPagAntActaReunion;
    }

    public void setBloquearPagAntActaReunion(boolean bloquearPagAntActaReunion) {
        this.bloquearPagAntActaReunion = bloquearPagAntActaReunion;
    }

    public String getCantidadRegistros() {
        return cantidadRegistros;
    }

    public void setCantidadRegistros(String cantidadRegistros) {
        this.cantidadRegistros = cantidadRegistros;
    }

    public List<Proyecto> getListaProyecto() {
        return listaProyecto;
    }

    public void setListaProyecto(List<Proyecto> listaProyecto) {
        this.listaProyecto = listaProyecto;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

}
