/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.controller.proyecto;

import com.java.ucdit.bo.interfaces.proyecto.AdministrarEquipoPorProyectoBOInterface;
import com.java.ucdit.entidades.EquipoTecnologico;
import com.java.ucdit.entidades.EquipoPorProyecto;
import com.java.ucdit.entidades.Proyecto;
import com.java.ucdit.utilidades.Utilidades;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author ELECTRONICA
 */
@ManagedBean
@SessionScoped
public class ControllerRegistrarEquipoPorProyecto implements Serializable {

    @EJB
    AdministrarEquipoPorProyectoBOInterface administrarEquipoPorProyectoBO;
    //
    private BigInteger idProyecto;
    //
    private String nuevoMinutos;
    private Proyecto nuevoProyecto;
    private List<EquipoTecnologico> listaEquipoTecnologico;
    private EquipoTecnologico nuevoEquipoTecnologico;
    //
    private boolean validacionesMinutos;
    private boolean validacionesEquipo;
    private String mensajeFormulario;
    private boolean activarCasillas;
    private String colorMensaje;
    private boolean activarLimpiar;
    private boolean activarAceptar;
    private boolean prototipo;

    public ControllerRegistrarEquipoPorProyecto() {
    }

    @PostConstruct
    public void init() {
        prototipo = false;
        nuevoMinutos = "1";
        nuevoEquipoTecnologico = null;
        //
        validacionesMinutos = false;
        validacionesEquipo = false;
        activarLimpiar = true;
        colorMensaje = "black";
        activarCasillas = false;
        activarAceptar = false;
        mensajeFormulario = "N/A";
    }

    public void recibirIdProyecto(BigInteger idProyecto) {
        this.idProyecto = idProyecto;
        nuevoProyecto = administrarEquipoPorProyectoBO.obtenerProyectoPorId(idProyecto);
    }

    public void validarMinutosProyecto() {
        if (Utilidades.validarNulo(nuevoMinutos) && (!nuevoMinutos.isEmpty()) && (nuevoMinutos.trim().length() > 0)) {
            if (!Utilidades.isNumberGreaterThanZero(nuevoMinutos)) {
                validacionesMinutos = false;
                FacesContext.getCurrentInstance().addMessage("form:nuevoMinutos", new FacesMessage("La cantidad ingresada es incorrecta."));
            } else {
                validacionesMinutos = true;
            }
        } else {
            validacionesMinutos = false;
            FacesContext.getCurrentInstance().addMessage("form:nuevoMinutos", new FacesMessage("La cantidad es obligatoria."));
        }
    }

    public void validarEquipoProyecto() {
        if (Utilidades.validarNulo(nuevoEquipoTecnologico)) {
            validacionesEquipo = true;
        } else {
            validacionesEquipo = false;
            FacesContext.getCurrentInstance().addMessage("form:nuevoEquipoTecnologico", new FacesMessage("El insumo es obligatorio."));
        }
    }

    private boolean validarResultadosValidacion() {
        boolean retorno = true;
        if (validacionesMinutos == false) {
            retorno = false;
        }
        if (validacionesEquipo == false) {
            retorno = false;
        }
        return retorno;
    }

    /**
     * Metodo encargado de realizar el registro y validaciones de la información
     * del nuevo docente
     */
    public void registrarNuevoEquipoPorProyecto() {
        if (validarResultadosValidacion() == true) {
            almacenarNuevoEquipoPorProyectoEnSistema();
            limpiarFormulario();
            activarLimpiar = false;
            activarAceptar = true;
            activarCasillas = true;
            colorMensaje = "green";
            mensajeFormulario = "El formulario ha sido ingresado con exito.";
        } else {
            colorMensaje = "red";
            mensajeFormulario = "Existen errores en el formulario, por favor corregir para continuar.";
        }
    }

    public void almacenarNuevoEquipoPorProyectoEnSistema() {
        try {
            EquipoPorProyecto nuevaProyecto = new EquipoPorProyecto();
            nuevaProyecto.setUsadoparaprototipo(prototipo);
            int cantidad = Integer.valueOf(nuevoMinutos);
            nuevaProyecto.setCantidadminutosuso(cantidad);
            Integer costo = (nuevoEquipoTecnologico.getValorhorauso() / 60) * cantidad;
            nuevaProyecto.setCostouso(costo);
            nuevaProyecto.setEquipotecnologico(nuevoEquipoTecnologico);
            nuevaProyecto.setProyecto(nuevoProyecto);
            administrarEquipoPorProyectoBO.crearEquipoPorProyecto(nuevaProyecto);
        } catch (Exception e) {
            System.out.println("Error ControllerRegistrarEquipoPorProyecto almacenarNuevoEquipoPorProyectoEnSistema : " + e.toString());
        }
    }

    public void limpiarFormulario() {
        prototipo = false;
        nuevoMinutos = "1";
        nuevoEquipoTecnologico = null;
        //
        validacionesMinutos = false;
        validacionesEquipo = false;
        mensajeFormulario = "";
    }

    public String cancelarRegistroProyecto() {
        nuevoMinutos = "1";
        nuevoProyecto = null;
        nuevoEquipoTecnologico = null;
        //
        validacionesMinutos = false;
        prototipo = false;
        validacionesEquipo = false;
        activarAceptar = false;
        listaEquipoTecnologico = null;
        mensajeFormulario = "N/A";
        activarLimpiar = true;
        colorMensaje = "black";
        activarCasillas = false;
        return "administrarequipoporproyecto";
    }

    public void cambiarActivarCasillas() {
        mensajeFormulario = "N/A";
        colorMensaje = "black";
        activarAceptar = false;
        activarLimpiar = true;
        if (activarCasillas == true) {
            activarCasillas = false;
        }
    }

    //GET-SET
    public BigInteger getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(BigInteger idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getNuevoMinutos() {
        return nuevoMinutos;
    }

    public void setNuevoMinutos(String nuevoMinutos) {
        this.nuevoMinutos = nuevoMinutos;
    }

    public Proyecto getNuevoProyecto() {
        return nuevoProyecto;
    }

    public void setNuevoProyecto(Proyecto nuevoProyecto) {
        this.nuevoProyecto = nuevoProyecto;
    }

    public List<EquipoTecnologico> getListaEquipoTecnologico() {
        return listaEquipoTecnologico;
    }

    public void setListaEquipoTecnologico(List<EquipoTecnologico> listaEquipoTecnologico) {
        this.listaEquipoTecnologico = listaEquipoTecnologico;
    }

    public EquipoTecnologico getNuevoEquipoTecnologico() {
        return nuevoEquipoTecnologico;
    }

    public void setNuevoEquipoTecnologico(EquipoTecnologico nuevoEquipoTecnologico) {
        this.nuevoEquipoTecnologico = nuevoEquipoTecnologico;
    }

    public String getMensajeFormulario() {
        return mensajeFormulario;
    }

    public void setMensajeFormulario(String mensajeFormulario) {
        this.mensajeFormulario = mensajeFormulario;
    }

    public boolean isActivarCasillas() {
        return activarCasillas;
    }

    public void setActivarCasillas(boolean activarCasillas) {
        this.activarCasillas = activarCasillas;
    }

    public String getColorMensaje() {
        return colorMensaje;
    }

    public void setColorMensaje(String colorMensaje) {
        this.colorMensaje = colorMensaje;
    }

    public boolean isActivarLimpiar() {
        return activarLimpiar;
    }

    public void setActivarLimpiar(boolean activarLimpiar) {
        this.activarLimpiar = activarLimpiar;
    }

    public boolean isActivarAceptar() {
        return activarAceptar;
    }

    public void setActivarAceptar(boolean activarAceptar) {
        this.activarAceptar = activarAceptar;
    }

    public boolean isPrototipo() {
        return prototipo;
    }

    public void setPrototipo(boolean prototipo) {
        this.prototipo = prototipo;
    }

}
