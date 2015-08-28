/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.controller.variable;

import com.java.ucdit.bo.interfaces.variable.AdministrarTipoEquipoBOInterface;
import com.java.ucdit.entidades.TipoEquipo;
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
public class ControllerDetallesTipoEquipo implements Serializable {

    @EJB
    AdministrarTipoEquipoBOInterface administrarTipoEquipoBO;
    //
    private TipoEquipo tipoEquipoDetalle;
    private BigInteger idTipoEquipo;
    //
    private String editarNombre;
    //
    private boolean validacionesNombre;
    private String mensajeFormulario;
    private String colorMensaje;
    private boolean modificacionRegistro;

    public ControllerDetallesTipoEquipo() {
    }

    @PostConstruct
    public void init() {
        colorMensaje = "black";
        mensajeFormulario = "N/A";
    }

    public void recibirIdTipoEquipoDetalle(BigInteger idTipoEquipo) {
        this.idTipoEquipo = idTipoEquipo;
        tipoEquipoDetalle = administrarTipoEquipoBO.obtenerTipoEquipoPorId(this.idTipoEquipo);
        modificacionRegistro = false;
        cargarInformacionRegistro();
    }

    private void cargarInformacionRegistro() {
        if (Utilidades.validarNulo(tipoEquipoDetalle)) {
            editarNombre = tipoEquipoDetalle.getNombretipoequipo();
            validacionesNombre = true;
        }
    }

    private void restaurarRegistro() {
        colorMensaje = "black";
        mensajeFormulario = "N/A";
        modificacionRegistro = false;
        tipoEquipoDetalle = null;
        tipoEquipoDetalle = administrarTipoEquipoBO.obtenerTipoEquipoPorId(this.idTipoEquipo);
        cargarInformacionRegistro();
    }

    public void validarNombreTipoEquipo() {
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
    public void registrarModificacionTipoEquipo() {
        if (modificacionRegistro == true) {
            if (validarResultadosValidacion() == true) {
                almacenarModificacionTipoEquipoEnSistema();
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

    public void almacenarModificacionTipoEquipoEnSistema() {
        try {
            tipoEquipoDetalle.setNombretipoequipo(editarNombre);
            administrarTipoEquipoBO.editarTipoEquipo(tipoEquipoDetalle);
        } catch (Exception e) {
            System.out.println("Error ControllerDetallesTipoEquipo almacenarModificacionTipoEquipoEnSistema : " + e.toString());
        }
    }

    public String cancelarRegistroTipoEquipo() {
        editarNombre = null;
        validacionesNombre = false;
        mensajeFormulario = "N/A";
        colorMensaje = "black";

        tipoEquipoDetalle = null;
        idTipoEquipo = null;
        modificacionRegistro = false;
        return "administrartipoequipo";
    }

    //GET-SET
    public TipoEquipo getTipoEquipoDetalle() {
        return tipoEquipoDetalle;
    }

    public void setTipoEquipoDetalle(TipoEquipo tipoEquipoDetalle) {
        this.tipoEquipoDetalle = tipoEquipoDetalle;
    }

    public BigInteger getIdTipoEquipo() {
        return idTipoEquipo;
    }

    public void setIdTipoEquipo(BigInteger idTipoEquipo) {
        this.idTipoEquipo = idTipoEquipo;
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
