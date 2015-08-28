/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.controller.variable;

import com.java.ucdit.bo.interfaces.variable.AdministrarTipoPersonalBOInterface;
import com.java.ucdit.entidades.TipoPersonal;
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
public class ControllerDetallesTipoPersonal implements Serializable {

    @EJB
    AdministrarTipoPersonalBOInterface administrarTipoPersonalBO;
    //
    private TipoPersonal tipoPersonalDetalle;
    private BigInteger idTipoPersonal;
    //
    private String editarNombre;
    //
    private boolean validacionesNombre;
    private String mensajeFormulario;
    private String colorMensaje;
    private boolean modificacionRegistro;

    public ControllerDetallesTipoPersonal() {
    }

    @PostConstruct
    public void init() {
        colorMensaje = "black";
        mensajeFormulario = "N/A";
    }

    public void recibirIdTipoPersonalDetalle(BigInteger idTipoPersonal) {
        this.idTipoPersonal = idTipoPersonal;
        tipoPersonalDetalle = administrarTipoPersonalBO.obtenerTipoPersonalPorId(this.idTipoPersonal);
        modificacionRegistro = false;
        cargarInformacionRegistro();
    }

    private void cargarInformacionRegistro() {
        if (Utilidades.validarNulo(tipoPersonalDetalle)) {
            editarNombre = tipoPersonalDetalle.getNombretipopersonal();
            validacionesNombre = true;
        }
    }

    private void restaurarRegistro() {
        colorMensaje = "black";
        mensajeFormulario = "N/A";
        modificacionRegistro = false;
        tipoPersonalDetalle = null;
        tipoPersonalDetalle = administrarTipoPersonalBO.obtenerTipoPersonalPorId(this.idTipoPersonal);
        cargarInformacionRegistro();
    }

    public void validarNombreTipoPersonal() {
        if (Utilidades.validarNulo(editarNombre) && (!editarNombre.isEmpty()) && (editarNombre.trim().length() > 0)) {
            int tam = editarNombre.length();
            if (tam >= 4) {
                if (!Utilidades.validarCaracterString(editarNombre)) {
                    validacionesNombre = false;
                    FacesContext.getCurrentInstance().addMessage("form:editarNombre", new FacesMessage("El nombre ingresado es incorrecto."));
                } else {
                    validacionesNombre = true;
                }
            } else {
                validacionesNombre = false;
                FacesContext.getCurrentInstance().addMessage("form:editarNombre", new FacesMessage("El tamaño minimo permitido es 4 caracteres."));
            }
        } else {
            validacionesNombre = false;
            FacesContext.getCurrentInstance().addMessage("form:editarNombre", new FacesMessage("El nombre es obligatorio."));
        }
        modificacionRegistro = true;
    }

    private boolean validarResultadosValidacion() {
        boolean retorno = true;
        if (validacionesNombre == false) {
            retorno = false;
        }
        return retorno;
    }

    /**
     * Metodo encargado de realizar el registro y validaciones de la información
     * del editar docente
     */
    public void registrarModificacionTipoPersonal() {
        if (modificacionRegistro == true) {
            if (validarResultadosValidacion() == true) {
                almacenarModificacionTipoPersonalEnSistema();
                restaurarRegistro();
                colorMensaje = "green";
                mensajeFormulario = "El formulario ha sido ingresado con exito.";
            } else {
                colorMensaje = "red";
                mensajeFormulario = "Existen errores en el formulario, por favor corregir para continuar.";
            }
        } else {
            colorMensaje = "black";
            mensajeFormulario = "No existen modificaciones para ser almacenadas.";
        }
    }

    public void almacenarModificacionTipoPersonalEnSistema() {
        try {
            tipoPersonalDetalle.setNombretipopersonal(editarNombre);
            administrarTipoPersonalBO.editarTipoPersonal(tipoPersonalDetalle);
        } catch (Exception e) {
            System.out.println("Error ControllerDetallesTipoPersonal almacenarModificacionTipoPersonalEnSistema : " + e.toString());
        }
    }

    public String cancelarRegistroTipoPersonal() {
        editarNombre = null;
        validacionesNombre = false;
        mensajeFormulario = "N/A";
        colorMensaje = "black";

        tipoPersonalDetalle = null;
        idTipoPersonal = null;
        modificacionRegistro = false;
        return "administrartipopersonal";
    }

    //GET-SET
    public TipoPersonal getTipoPersonalDetalle() {
        return tipoPersonalDetalle;
    }

    public void setTipoPersonalDetalle(TipoPersonal tipoPersonalDetalle) {
        this.tipoPersonalDetalle = tipoPersonalDetalle;
    }

    public BigInteger getIdTipoPersonal() {
        return idTipoPersonal;
    }

    public void setIdTipoPersonal(BigInteger idTipoPersonal) {
        this.idTipoPersonal = idTipoPersonal;
    }

    public String getEditarNombre() {
        return editarNombre;
    }

    public void setEditarNombre(String editarNombre) {
        this.editarNombre = editarNombre;
    }

    public boolean isValidacionesNombre() {
        return validacionesNombre;
    }

    public void setValidacionesNombre(boolean validacionesNombre) {
        this.validacionesNombre = validacionesNombre;
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

    public boolean isModificacionRegistro() {
        return modificacionRegistro;
    }

    public void setModificacionRegistro(boolean modificacionRegistro) {
        this.modificacionRegistro = modificacionRegistro;
    }

}
