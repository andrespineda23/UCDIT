/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.controller.inventario;

import com.java.ucdit.bo.interfaces.inventario.AdministrarHojaDeVidaBOInterface;
import com.java.ucdit.entidades.HojaDeVida;
import com.java.ucdit.utilidades.Utilidades;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
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
public class ControllerDetallesHojaDeVidaEquipo implements Serializable {

    @EJB
    AdministrarHojaDeVidaBOInterface administrarHojaDeVidaBO;

    private HojaDeVida hojaDeVidaEditar;
    private BigInteger idHojaDeVida;
    //
    private String editarDescripcion;
    private Date editarFecha;
    //
    private boolean validacionesDescripcion, validacionesFecha;
    private String mensajeFormulario;
    private String colorMensaje;
    private boolean fechaDiferida;

    public ControllerDetallesHojaDeVidaEquipo() {
    }

    @PostConstruct
    public void init() {
        colorMensaje = "black";
        mensajeFormulario = "N/A";
    }

    public void recibirIdDetalleHojaDeVida(BigInteger idHV) {
        this.idHojaDeVida = idHV;
        hojaDeVidaEditar = administrarHojaDeVidaBO.obtenerHojaDeVidaPorId(idHojaDeVida);
        cargarDatosHV();
    }

    private void cargarDatosHV() {
        if (Utilidades.validarNulo(hojaDeVidaEditar)) {
            editarFecha = hojaDeVidaEditar.getFecharegistro();
            editarDescripcion = hojaDeVidaEditar.getDescripcion();
            fechaDiferida = true;
            validacionesDescripcion = true;
            validacionesFecha = true;
        }
    }

    public void validarFechaHojaVida() {
        if (Utilidades.validarNulo(editarFecha)) {
            if (fechaDiferida == true) {
                editarFecha = new Date();
                if (Utilidades.fechaIngresadaCorrecta(editarFecha)) {
                    validacionesFecha = true;
                } else {
                    FacesContext.getCurrentInstance().addMessage("form:editarFecha", new FacesMessage("La fecha se encuentra incorrecta."));
                    validacionesFecha = false;
                }
            } else {
                if (Utilidades.fechaDiferidaIngresadaCorrecta(editarFecha)) {
                    validacionesFecha = true;
                } else {
                    FacesContext.getCurrentInstance().addMessage("form:editarFecha", new FacesMessage("La fecha se encuentra incorrecta."));
                    validacionesFecha = false;
                }
            }
        } else {
            FacesContext.getCurrentInstance().addMessage("form:editarFecha", new FacesMessage("La fecha es obligatoria."));
            validacionesFecha = false;
        }
    }

    public void validarDescripcionHojaVida() {
        if (Utilidades.validarNulo(editarDescripcion) && (!editarDescripcion.isEmpty()) && (editarDescripcion.trim().length() > 0)) {
            int tam = editarDescripcion.length();
            if (tam >= 4) {
                if (!Utilidades.validarCaracteresAlfaNumericos(editarDescripcion)) {
                    validacionesDescripcion = false;
                    FacesContext.getCurrentInstance().addMessage("form:editarDescripcion", new FacesMessage("La descripción ingresada es incorrecta."));
                } else {
                    validacionesDescripcion = true;
                }
            } else {
                validacionesDescripcion = false;
                FacesContext.getCurrentInstance().addMessage("form:editarDescripcion", new FacesMessage("El tamaño minimo permitido es 4 caracteres."));
            }
        } else {
            validacionesDescripcion = false;
            FacesContext.getCurrentInstance().addMessage("form:editarDescripcion", new FacesMessage("La descripción ingresada es obligatoria."));
        }
    }

    private boolean validarResultadosValidacion() {
        boolean retorno = true;
        if (validacionesDescripcion == false) {
            retorno = false;
        }
        if (validacionesFecha == false) {
            retorno = false;
        }
        return retorno;
    }

    /**
     * Metodo encargado de realizar el registro y validaciones de la información
     * del editar docente
     */
    public void registrarModificacionHojaDeVida() {
        if (validarResultadosValidacion() == true) {
            almacenarModificacionHojaDeVidaEnSistema();
            recibirIdDetalleHojaDeVida(idHojaDeVida);
            colorMensaje = "green";
            mensajeFormulario = "El formulario ha sido ingresado con exito.";
        } else {
            colorMensaje = "red";
            mensajeFormulario = "Existen errores en el formulario, por favor corregir para continuar.";
        }
    }

    public void almacenarModificacionHojaDeVidaEnSistema() {
        try {
            hojaDeVidaEditar.setDescripcion(editarDescripcion);
            hojaDeVidaEditar.setFecharegistro(editarFecha);
            administrarHojaDeVidaBO.editarHojaDeVida(hojaDeVidaEditar);
        } catch (Exception e) {
            System.out.println("Error ControllerDetallesHojaDeVidaEquipo almacenarModificacionHojaDeVidaEnSistema : " + e.toString());
        }
    }

    public String cancelarRegistroHojaDeVida() {
        editarDescripcion = null;
        validacionesDescripcion = false;
        mensajeFormulario = "N/A";
        validacionesFecha = true;
        colorMensaje = "black";
        fechaDiferida = true;
        hojaDeVidaEditar = null;
        idHojaDeVida = null;
        return "hojadevidaequipo";
    }

    //GET-SET
    public HojaDeVida getHojaDeVidaEditar() {
        return hojaDeVidaEditar;
    }

    public void setHojaDeVidaEditar(HojaDeVida hojaDeVidaEditar) {
        this.hojaDeVidaEditar = hojaDeVidaEditar;
    }

    public BigInteger getIdHojaDeVida() {
        return idHojaDeVida;
    }

    public void setIdHojaDeVida(BigInteger idHojaDeVida) {
        this.idHojaDeVida = idHojaDeVida;
    }

    public String getEditarDescripcion() {
        return editarDescripcion;
    }

    public void setEditarDescripcion(String editarDescripcion) {
        this.editarDescripcion = editarDescripcion;
    }

    public Date getEditarFecha() {
        return editarFecha;
    }

    public void setEditarFecha(Date editarFecha) {
        this.editarFecha = editarFecha;
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

    public boolean isFechaDiferida() {
        return fechaDiferida;
    }

    public void setFechaDiferida(boolean fechaDiferida) {
        this.fechaDiferida = fechaDiferida;
    }

}
