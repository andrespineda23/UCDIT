/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.controller.proyecto;

import com.java.ucdit.bo.interfaces.proyecto.AdministrarInsumoPorProyectoBOInterface;
import com.java.ucdit.entidades.Proyecto;
import com.java.ucdit.entidades.Insumo;
import com.java.ucdit.entidades.InsumoPorProyecto;
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
public class ControllerRegistrarInsumoPorProyecto implements Serializable {

    @EJB
    AdministrarInsumoPorProyectoBOInterface administrarInsumoPorProyectoBO;
    //
    private BigInteger idProyecto;
    //
    private String nuevoCantidad;
    private Proyecto nuevoProyecto;
    private List<Insumo> listaInsumo;
    private Insumo nuevoInsumo;
    //
    private boolean validacionesCantidad;
    private boolean validacionesInsumo;
    private String mensajeFormulario;
    private boolean activarCasillas;
    private String colorMensaje;
    private boolean activarLimpiar;
    private boolean activarAceptar;
    private boolean prototipo;

    public ControllerRegistrarInsumoPorProyecto() {
    }

    @PostConstruct
    public void init() {
        prototipo = false;
        nuevoCantidad = "1";
        nuevoInsumo = null;
        //
        validacionesCantidad = false;
        validacionesInsumo = false;
        activarLimpiar = true;
        colorMensaje = "black";
        activarCasillas = false;
        activarAceptar = false;
        mensajeFormulario = "N/A";
    }

    public void recibirIdProyecto(BigInteger idProyecto) {
        this.idProyecto = idProyecto;
        nuevoProyecto = administrarInsumoPorProyectoBO.obtenerProyectoPorId(idProyecto);
    }

    public void validarCantidadProyecto() {
        if (Utilidades.validarNulo(nuevoCantidad) && (!nuevoCantidad.isEmpty()) && (nuevoCantidad.trim().length() > 0)) {
            if (!Utilidades.isNumberGreaterThanZero(nuevoCantidad)) {
                validacionesCantidad = false;
                FacesContext.getCurrentInstance().addMessage("form:nuevoCantidad", new FacesMessage("La cantidad ingresada es incorrecta."));
            } else {
                validacionesCantidad = true;
            }
        } else {
            validacionesCantidad = false;
            FacesContext.getCurrentInstance().addMessage("form:nuevoCantidad", new FacesMessage("La cantidad es obligatoria."));
        }
    }

    public void validarInsumoProyecto() {
        if (Utilidades.validarNulo(nuevoInsumo)) {
            validacionesInsumo = true;
        } else {
            validacionesInsumo = false;
            FacesContext.getCurrentInstance().addMessage("form:nuevoInsumo", new FacesMessage("El insumo es obligatorio."));
        }
    }

    private boolean validarResultadosValidacion() {
        boolean retorno = true;
        if (validacionesCantidad == false) {
            retorno = false;
        }
        if (validacionesInsumo == false) {
            retorno = false;
        }
        return retorno;
    }

    private boolean validarCantidadExistencia() {
        boolean retorno = true;
        Integer cantidad = Integer.valueOf(nuevoCantidad);
        int cantidadAQuedar = nuevoInsumo.getCantidadexistencia() - cantidad;
        if (cantidadAQuedar < nuevoInsumo.getCantidadminima()) {
            retorno = false;
        }
        return retorno;
    }

    /**
     * Metodo encargado de realizar el registro y validaciones de la información
     * del nuevo docente
     */
    public void registrarNuevoInsumoPorProyecto() {
        if (validarResultadosValidacion() == true) {
            if (validarCantidadExistencia() == true) {
                almacenarNuevoInsumoPorProyectoEnSistema();
                limpiarFormulario();
                activarLimpiar = false;
                activarAceptar = true;
                activarCasillas = true;
                colorMensaje = "green";
                mensajeFormulario = "El formulario ha sido ingresado con exito.";
            } else {
                colorMensaje = "red";
                mensajeFormulario = "La cantidad seleccionada supera la cantidad de existencia del insumo.";
            }
        } else {
            colorMensaje = "red";
            mensajeFormulario = "Existen errores en el formulario, por favor corregir para continuar.";
        }
    }

    public void almacenarNuevoInsumoPorProyectoEnSistema() {
        try {
            InsumoPorProyecto nuevaProyecto = new InsumoPorProyecto();
            int cantidad = Integer.valueOf(nuevoCantidad);
            nuevaProyecto.setCantidadusada(cantidad);
            nuevaProyecto.setUsadoparaprototipo(prototipo);
            Integer costo = nuevoInsumo.getCostocompra() * cantidad;
            nuevaProyecto.setCostouso(costo);
            nuevaProyecto.setInsumo(nuevoInsumo);
            nuevaProyecto.setProyecto(nuevoProyecto);
            administrarInsumoPorProyectoBO.crearInsumoPorProyecto(nuevaProyecto);
        } catch (Exception e) {
            System.out.println("Error ControllerRegistrarInsumoPorProyecto almacenarNuevoInsumoPorProyectoEnSistema : " + e.toString());
        }
    }

    public void limpiarFormulario() {
        nuevoCantidad = "1";
        nuevoInsumo = null;
        prototipo = false;
        //
        validacionesCantidad = false;
        validacionesInsumo = false;
        mensajeFormulario = "";
    }

    public String cancelarRegistroProyecto() {
        nuevoCantidad = "1";
        nuevoProyecto = null;
        prototipo = false;
        nuevoInsumo = null;
        //
        validacionesCantidad = false;
        validacionesInsumo = false;
        activarAceptar = false;
        listaInsumo = null;
        mensajeFormulario = "N/A";
        activarLimpiar = true;
        colorMensaje = "black";
        activarCasillas = false;
        return "administrarinsumoporproyecto";
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
    public String getNuevoCantidad() {
        return nuevoCantidad;
    }

    public void setNuevoCantidad(String nuevoCantidad) {
        this.nuevoCantidad = nuevoCantidad;
    }

    public Proyecto getNuevoProyecto() {
        return nuevoProyecto;
    }

    public void setNuevoProyecto(Proyecto nuevoProyecto) {
        this.nuevoProyecto = nuevoProyecto;
    }

    public List<Insumo> getListaInsumo() {
        if (null == listaInsumo) {
            listaInsumo = administrarInsumoPorProyectoBO.obtenerInsumosRegistrados();
        }
        return listaInsumo;
    }

    public void setListaInsumo(List<Insumo> listaInsumo) {
        this.listaInsumo = listaInsumo;
    }

    public Insumo getNuevoInsumo() {
        return nuevoInsumo;
    }

    public void setNuevoInsumo(Insumo nuevoInsumo) {
        this.nuevoInsumo = nuevoInsumo;
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

    public BigInteger getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(BigInteger idProyecto) {
        this.idProyecto = idProyecto;
    }

    public boolean isPrototipo() {
        return prototipo;
    }

    public void setPrototipo(boolean prototipo) {
        this.prototipo = prototipo;
    }

}
