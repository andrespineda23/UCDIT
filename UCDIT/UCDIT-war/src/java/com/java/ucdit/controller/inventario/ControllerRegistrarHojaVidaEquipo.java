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
public class ControllerRegistrarHojaVidaEquipo implements Serializable {

    @EJB
    AdministrarHojaDeVidaBOInterface administrarHojaDeVidaBO;
    //
    private String nuevoDescripcion;
    private Date nuevoFecha;
    //
    private boolean validacionesDescripcion, validacionesFecha;
    private String mensajeFormulario;
    private boolean activarCasillas;
    private String colorMensaje;
    private boolean activarLimpiar;
    private boolean activarAceptar;
    private boolean fechaDiferida;
    private BigInteger idEquipo;

    public ControllerRegistrarHojaVidaEquipo() {
    }

    public void recibirIDEquipo(BigInteger idEquipo) {
        this.idEquipo = idEquipo;
    }

    @PostConstruct
    public void init() {
        nuevoFecha = new Date();
        nuevoDescripcion = null;
        fechaDiferida = true;
        validacionesDescripcion = false;
        validacionesFecha = true;
        activarLimpiar = true;
        colorMensaje = "black";
        activarCasillas = false;
        activarAceptar = false;
        mensajeFormulario = "N/A";
    }

    public void validarFechaHojaVida() {
        if (Utilidades.validarNulo(nuevoFecha)) {
            if (fechaDiferida == true) {
                nuevoFecha = new Date();
                if (Utilidades.fechaIngresadaCorrecta(nuevoFecha)) {
                    validacionesFecha = true;
                } else {
                    FacesContext.getCurrentInstance().addMessage("form:nuevoFecha", new FacesMessage("La fecha se encuentra incorrecta."));
                    validacionesFecha = false;
                }
            } else {
                if (Utilidades.fechaDiferidaIngresadaCorrecta(nuevoFecha)) {
                    validacionesFecha = true;
                } else {
                    FacesContext.getCurrentInstance().addMessage("form:nuevoFecha", new FacesMessage("La fecha se encuentra incorrecta."));
                    validacionesFecha = false;
                }
            }
        } else {
            FacesContext.getCurrentInstance().addMessage("form:nuevoFecha", new FacesMessage("La fecha es obligatoria."));
            validacionesFecha = false;
        }
    }

    public void validarDescripcionHojaVida() {
        if (Utilidades.validarNulo(nuevoDescripcion) && (!nuevoDescripcion.isEmpty()) && (nuevoDescripcion.trim().length() > 0)) {
            int tam = nuevoDescripcion.length();
            if (tam >= 4) {
                if (!Utilidades.validarCaracteresAlfaNumericos(nuevoDescripcion)) {
                    validacionesDescripcion = false;
                    FacesContext.getCurrentInstance().addMessage("form:nuevoDescripcion", new FacesMessage("La descripción ingresada es incorrecta."));
                } else {
                    validacionesDescripcion = true;
                }
            } else {
                validacionesDescripcion = false;
                FacesContext.getCurrentInstance().addMessage("form:nuevoDescripcion", new FacesMessage("El tamaño minimo permitido es 4 caracteres."));
            }
        } else {
            validacionesDescripcion = false;
            FacesContext.getCurrentInstance().addMessage("form:nuevoDescripcion", new FacesMessage("La descripción ingresada es obligatoria."));
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
     * del nuevo docente
     */
    public void registrarNuevoHojaDeVida() {
        if (validarResultadosValidacion() == true) {
            almacenarNuevoHojaDeVidaEnSistema();
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

    public void almacenarNuevoHojaDeVidaEnSistema() {
        try {
            HojaDeVida nuevaHojaDeVida = new HojaDeVida();
            nuevaHojaDeVida.setDescripcion(nuevoDescripcion);
            nuevaHojaDeVida.setFecharegistro(nuevoFecha);
            administrarHojaDeVidaBO.crearHojaDeVida(nuevaHojaDeVida, idEquipo);
        } catch (Exception e) {
            System.out.println("Error ControllerRegistrarHojaDeVida almacenarNuevoHojaDeVidaEnSistema : " + e.toString());
        }
    }

    public void limpiarFormulario() {
        nuevoDescripcion = null;
        nuevoFecha = new Date();
        validacionesDescripcion = false;
        mensajeFormulario = "";
        validacionesFecha = true;
        fechaDiferida = true;
    }

    public String cancelarRegistroHojaDeVida() {
        nuevoDescripcion = null;
        validacionesDescripcion = false;
        activarAceptar = false;
        mensajeFormulario = "N/A";
        activarLimpiar = true;
        validacionesFecha = true;
        colorMensaje = "black";
        activarCasillas = false;
        fechaDiferida = true;
        return "hojadevidaequipo";
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
    public String getNuevoDescripcion() {
        return nuevoDescripcion;
    }

    public void setNuevoDescripcion(String nuevoDescripcion) {
        this.nuevoDescripcion = nuevoDescripcion;
    }

    public Date getNuevoFecha() {
        return nuevoFecha;
    }

    public void setNuevoFecha(Date nuevoFecha) {
        this.nuevoFecha = nuevoFecha;
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

    public boolean isFechaDiferida() {
        return fechaDiferida;
    }

    public void setFechaDiferida(boolean fechaDiferida) {
        this.fechaDiferida = fechaDiferida;
    }

    public BigInteger getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(BigInteger idEquipo) {
        this.idEquipo = idEquipo;
    }

}
