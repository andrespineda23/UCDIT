/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.controller.proyecto;

import com.java.ucdit.bo.interfaces.proyecto.AdministrarBitacoraProyectoBOInterface;
import com.java.ucdit.entidades.BitacoraProyecto;
import com.java.ucdit.entidades.Proyecto;
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
public class ControllerRegistrarBitacora implements Serializable {

    @EJB
    AdministrarBitacoraProyectoBOInterface administrarBitacoraProyectoBO;
    //
    private String nuevoEvento;
    private Date nuevoFechaEvento;
    //
    private boolean validacionesEvento, validacionesFechaEvento;
    private String mensajeFormulario;
    private boolean activarCasillas;
    private String colorMensaje;
    private boolean activarLimpiar;
    private boolean activarAceptar;
    private boolean fechaDiferida;
    private BigInteger idProyecto;
    private Proyecto proyecto;

    public ControllerRegistrarBitacora() {
    }

    @PostConstruct
    public void init() {
        fechaDiferida = true;
        nuevoEvento = null;
        nuevoFechaEvento = new Date();
        //
        validacionesEvento = false;
        validacionesFechaEvento = true;
        activarLimpiar = true;
        colorMensaje = "black";
        activarCasillas = false;
        activarAceptar = false;
        mensajeFormulario = "N/A";
    }

    public void recibirIdProyecto(BigInteger idProyecto) {
        this.idProyecto = idProyecto;
        proyecto = administrarBitacoraProyectoBO.obtenerProyectoPorId(idProyecto);
    }

    public void validarEventoBitacoraProyecto() {
        if (Utilidades.validarNulo(nuevoEvento) && (!nuevoEvento.isEmpty()) && (nuevoEvento.trim().length() > 0)) {
            int tam = nuevoEvento.length();
            if (tam >= 4) {
                if (!Utilidades.validarCaracteresAlfaNumericos(nuevoEvento)) {
                    validacionesEvento = false;
                    FacesContext.getCurrentInstance().addMessage("form:nuevoEvento", new FacesMessage("El nombre ingresado es incorrecto."));
                } else {
                    validacionesEvento = true;
                }
            } else {
                validacionesEvento = false;
                FacesContext.getCurrentInstance().addMessage("form:nuevoEvento", new FacesMessage("El tamaño minimo permitido es 4 caracteres."));
            }
        } else {
            validacionesEvento = false;
            FacesContext.getCurrentInstance().addMessage("form:nuevoEvento", new FacesMessage("El nombre es obligatorio."));
        }
    }

    public void validarFechaEventoBitacoraProyecto() {
        if (Utilidades.validarNulo(nuevoFechaEvento)) {
            if (fechaDiferida == true) {
                nuevoFechaEvento = new Date();
                if (Utilidades.fechaIngresadaCorrecta(nuevoFechaEvento)) {
                    validacionesFechaEvento = true;
                } else {
                    validacionesFechaEvento = true;
                    FacesContext.getCurrentInstance().addMessage("form:nuevoFechaEvento", new FacesMessage("La fecha de inicio es incorrecta."));
                }
            } else {
                if (Utilidades.fechaDiferidaIngresadaCorrecta(nuevoFechaEvento)) {
                    validacionesFechaEvento = true;
                } else {
                    validacionesFechaEvento = true;
                    FacesContext.getCurrentInstance().addMessage("form:nuevoFechaEvento", new FacesMessage("La fecha de inicio es incorrecta."));
                }
            }
        } else {
            validacionesFechaEvento = true;
            FacesContext.getCurrentInstance().addMessage("form:nuevoFechaEvento", new FacesMessage("La fecha de inicio es obligatoria."));
        }
    }

    private boolean validarResultadosValidacion() {
        boolean retorno = true;
        if (validacionesFechaEvento == false) {
            retorno = false;
        }
        if (validacionesEvento == false) {
            retorno = false;
        }
        return retorno;
    }

   
    public void registrarNuevoBitacoraProyecto() {
        if (validarResultadosValidacion() == true) {
            almacenarNuevoBitacoraProyectoEnSistema();
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

    public void almacenarNuevoBitacoraProyectoEnSistema() {
        try {
            BitacoraProyecto nuevaBitacoraProyecto = new BitacoraProyecto();
            nuevaBitacoraProyecto.setDetalleevento(nuevoEvento);
            nuevaBitacoraProyecto.setFecharegistro(nuevoFechaEvento);
            nuevaBitacoraProyecto.setProyecto(proyecto);
            administrarBitacoraProyectoBO.crearBitacoraProyecto(nuevaBitacoraProyecto);
        } catch (Exception e) {
            System.out.println("Error ControllerRegistrarBitacoraProyecto almacenarNuevoBitacoraProyectoEnSistema : " + e.toString());
        }
    }

    public void limpiarFormulario() {
        nuevoEvento = null;
        nuevoFechaEvento = new Date();
        fechaDiferida = true;
        //
        validacionesEvento = false;
        validacionesFechaEvento = true;
        mensajeFormulario = "";
    }

    public String cancelarRegistroBitacoraProyecto() {
        nuevoEvento = null;
        nuevoFechaEvento = new Date();
        fechaDiferida = true;
        //
        validacionesEvento = false;
        validacionesFechaEvento = true;
        activarAceptar = false;
        mensajeFormulario = "N/A";
        activarLimpiar = true;
        colorMensaje = "black";
        idProyecto = null;
        proyecto = null;
        activarCasillas = false;
        return "administrarbitacora";
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
    public String getNuevoEvento() {
        return nuevoEvento;
    }

    public void setNuevoEvento(String nuevoEvento) {
        this.nuevoEvento = nuevoEvento;
    }

    public Date getNuevoFechaEvento() {
        return nuevoFechaEvento;
    }

    public void setNuevoFechaEvento(Date nuevoFechaEvento) {
        this.nuevoFechaEvento = nuevoFechaEvento;
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

    public BigInteger getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(BigInteger idProyecto) {
        this.idProyecto = idProyecto;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

}
