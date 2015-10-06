/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.controller.proyecto;

import com.java.ucdit.bo.interfaces.proyecto.AdministrarInsumoPorProyectoBOInterface;
import com.java.ucdit.entidades.InsumoPorProyecto;
import com.java.ucdit.utilidades.Utilidades;
import java.io.Serializable;
import java.math.BigInteger;
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
public class ControllerDetallesInsumoPorProyecto implements Serializable {

    @EJB
    AdministrarInsumoPorProyectoBOInterface administrarInsumoPorProyectoBO;
    //
    private InsumoPorProyecto insumoPorProyectoDetalle;
    private BigInteger idInsumoPorProyecto;
    //
    private String editarCantidad;
    //
    private boolean validacionesCantidad;
    private String mensajeFormulario;
    private String colorMensaje;
    private boolean prototipo;
    private boolean modificacionRegistro;

    public ControllerDetallesInsumoPorProyecto() {
    }

    @PostConstruct
    public void init() {
        colorMensaje = "black";
        mensajeFormulario = "N/A";
    }

    public void recibirIdInsumoPorProyecto(BigInteger idInsumoPorProyecto) {
        this.idInsumoPorProyecto = idInsumoPorProyecto;
        modificacionRegistro = false;
        insumoPorProyectoDetalle = administrarInsumoPorProyectoBO.obtenerInsumoPorProyectoPorId(idInsumoPorProyecto);
        cargarInformacionRegistro();
    }

    private void cargarInformacionRegistro() {
        if (Utilidades.validarNulo(insumoPorProyectoDetalle)) {
            prototipo = insumoPorProyectoDetalle.getUsadoparaprototipo();
            editarCantidad = String.valueOf(insumoPorProyectoDetalle.getCantidadusada());
            validacionesCantidad = true;
        }
    }

    public void validarCantidadProyecto() {
        if (Utilidades.validarNulo(editarCantidad) && (!editarCantidad.isEmpty()) && (editarCantidad.trim().length() > 0)) {
            if (!Utilidades.isNumberGreaterThanZero(editarCantidad)) {
                validacionesCantidad = false;
                FacesContext.getCurrentInstance().addMessage("form:editarCantidad", new FacesMessage("La cantidad ingresada es incorrecta."));
            } else {
                validacionesCantidad = true;
            }
        } else {
            validacionesCantidad = false;
            FacesContext.getCurrentInstance().addMessage("form:editarCantidad", new FacesMessage("La cantidad es obligatoria."));
        }
        modificacionRegistro = true;
    }

    private boolean validarResultadosValidacion() {
        boolean retorno = true;
        if (validacionesCantidad == false) {
            retorno = false;
        }
        return retorno;
    }

    private boolean validarCantidadExistencia() {
        boolean retorno = true;
        Integer cantidad = Integer.valueOf(editarCantidad);
        int cantidadAQuedar = insumoPorProyectoDetalle.getInsumo().getCantidadexistencia() - cantidad;
        if (cantidadAQuedar < insumoPorProyectoDetalle.getInsumo().getCantidadminima()) {
            retorno = false;
        }
        return retorno;
    }

    /**
     * Metodo encargado de realizar el registro y validaciones de la información
     * del editar docente
     */
    public void registrarModificacionInsumoPorProyecto() {
        if (modificacionRegistro == true) {
            if (validarResultadosValidacion() == true) {
                if (validarCantidadExistencia() == true) {
                    almacenarModificacionInsumoPorProyectoEnSistema();
                    recibirIdInsumoPorProyecto(this.idInsumoPorProyecto);
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
        } else {
            colorMensaje = "black";
            mensajeFormulario = "No existen modificaciones para ser almacenadas.";
        }
    }

    private void almacenarModificacionInsumoPorProyectoEnSistema() {
        try {
            int cantidad = Integer.valueOf(editarCantidad);
            insumoPorProyectoDetalle.setCantidadusada(cantidad);
            insumoPorProyectoDetalle.setUsadoparaprototipo(prototipo);
            Integer costo = insumoPorProyectoDetalle.getInsumo().getCostocompra() * cantidad;
            insumoPorProyectoDetalle.setCostouso(costo);
            administrarInsumoPorProyectoBO.editarInsumoPorProyecto(insumoPorProyectoDetalle);
        } catch (Exception e) {
            System.out.println("Error ControllerRegistrarInsumoPorProyecto almacenarModificacionInsumoPorProyectoEnSistema : " + e.toString());
        }
    }

    public String cancelarRegistroProyecto() {
        editarCantidad = null;
        modificacionRegistro = false;
        prototipo = false;
        //
        validacionesCantidad = true;
        mensajeFormulario = "N/A";
        colorMensaje = "black";
        return "administrarinsumoporproyecto";
    }

    //GET-SET
    public InsumoPorProyecto getInsumoPorProyectoDetalle() {
        return insumoPorProyectoDetalle;
    }

    public void setInsumoPorProyectoDetalle(InsumoPorProyecto insumoPorProyectoDetalle) {
        this.insumoPorProyectoDetalle = insumoPorProyectoDetalle;
    }

    public BigInteger getIdInsumoPorProyecto() {
        return idInsumoPorProyecto;
    }

    public void setIdInsumoPorProyecto(BigInteger idInsumoPorProyecto) {
        this.idInsumoPorProyecto = idInsumoPorProyecto;
    }

    public String getEditarCantidad() {
        return editarCantidad;
    }

    public void setEditarCantidad(String editarCantidad) {
        this.editarCantidad = editarCantidad;
    }

    public String getMensajeFormulario() {
        return mensajeFormulario;
    }

    public void setMensajeFormulario(String mensajeFormulario) {
        this.mensajeFormulario = mensajeFormulario;
    }

    public String getColorMensaje() {
        return colorMensaje;
    }

    public void setColorMensaje(String colorMensaje) {
        this.colorMensaje = colorMensaje;
    }

    public boolean isPrototipo() {
        return prototipo;
    }

    public void setPrototipo(boolean prototipo) {
        this.prototipo = prototipo;
    }

}
