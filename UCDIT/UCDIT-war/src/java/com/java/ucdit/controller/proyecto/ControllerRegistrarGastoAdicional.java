/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.controller.proyecto;

import com.java.ucdit.bo.interfaces.proyecto.AdministrarGastoAdicionalProyectoBOInterface;
import com.java.ucdit.entidades.GastoAdicional;
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
public class ControllerRegistrarGastoAdicional implements Serializable {

    @EJB
    AdministrarGastoAdicionalProyectoBOInterface administrarGastoAdicionalProyectoBO;

    //
    private String nuevoEvento;
    private Date nuevoFechaCompra;
    private String nuevoValorCompra;
    //
    private boolean validacionesEvento, validacionesFechaCompra;
    private boolean validacionesValorCompra;
    private String mensajeFormulario;
    private boolean activarCasillas;
    private String colorMensaje;
    private boolean activarLimpiar;
    private boolean activarAceptar;
    private boolean fechaDiferida;
    private BigInteger idProyecto;
    private Proyecto proyecto;

    public ControllerRegistrarGastoAdicional() {
    }

    public void recibirIdProyecto(BigInteger idProyecto) {
        this.idProyecto = idProyecto;
        proyecto = administrarGastoAdicionalProyectoBO.obtenerProyectoPorId(idProyecto);
    }

    @PostConstruct
    public void init() {
        nuevoEvento = null;
        nuevoFechaCompra = new Date();
        nuevoValorCompra = "0";
        fechaDiferida = true;
        //
        validacionesFechaCompra = true;
        validacionesEvento = false;
        validacionesValorCompra = false;
        activarLimpiar = true;
        colorMensaje = "black";
        activarCasillas = false;
        activarAceptar = false;
        mensajeFormulario = "N/A";
    }

    public void validarEventoGastoAdicional() {
        if (Utilidades.validarNulo(nuevoEvento) && (!nuevoEvento.isEmpty()) && (nuevoEvento.trim().length() > 0)) {
            int tam = nuevoEvento.length();
            if (tam >= 4) {
                if (!Utilidades.validarCaracteresAlfaNumericos(nuevoEvento)) {
                    validacionesEvento = false;
                    FacesContext.getCurrentInstance().addMessage("form:nuevoEvento", new FacesMessage("El evento ingresado es incorrecto."));
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

    public void validarValorCompraGastoAdicional() {
        if (Utilidades.validarNulo(nuevoValorCompra) && (!nuevoValorCompra.isEmpty()) && (nuevoValorCompra.trim().length() > 0)) {
            if (Utilidades.isNumber(nuevoValorCompra)) {
                validacionesValorCompra = true;
            } else {
                validacionesValorCompra = false;
                FacesContext.getCurrentInstance().addMessage("form:nuevoValorCompra", new FacesMessage("El valor compra es incorrecto."));
            }
        } else {
            validacionesValorCompra = false;
            FacesContext.getCurrentInstance().addMessage("form:nuevoValorCompra", new FacesMessage("El valor compra es obligatorio."));
        }
    }

    public void validarFechaCompraGastoAdicional() {
        if (Utilidades.validarNulo(nuevoFechaCompra)) {
            if (fechaDiferida == true) {
                nuevoFechaCompra = new Date();
                if (Utilidades.fechaIngresadaCorrecta(nuevoFechaCompra)) {
                    validacionesFechaCompra = true;
                } else {
                    FacesContext.getCurrentInstance().addMessage("form:nuevoFechaCompra", new FacesMessage("La fecha de compra es incorrecta."));
                    validacionesFechaCompra = false;
                }
            } else {
                if (Utilidades.fechaDiferidaIngresadaCorrecta(nuevoFechaCompra)) {
                    validacionesFechaCompra = true;
                } else {
                    FacesContext.getCurrentInstance().addMessage("form:nuevoFechaCompra", new FacesMessage("La fecha de compra es incorrecta."));
                    validacionesFechaCompra = false;
                }
            }
        } else {
            FacesContext.getCurrentInstance().addMessage("form:nuevoFechaCompra", new FacesMessage("La fecha de compra es obligatoria."));
            validacionesFechaCompra = false;
        }
    }

    private boolean validarResultadosValidacion() {
        boolean retorno = true;
        if (validacionesFechaCompra == false) {
            retorno = false;
        }
        if (validacionesEvento == false) {
            retorno = false;
        }
        if (validacionesValorCompra == false) {
            retorno = false;
        }
        return retorno;
    }

    public void registrarNuevoGastoAdicional() {
        if (validarResultadosValidacion() == true) {
            almacenarNuevoGastoAdicionalEnSistema();
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

    private void almacenarNuevoGastoAdicionalEnSistema() {
        try {
            GastoAdicional nuevaGastoAdicional = new GastoAdicional();
            nuevaGastoAdicional.setDescripcion(nuevoEvento);
            nuevaGastoAdicional.setFechagasto(nuevoFechaCompra);
            nuevaGastoAdicional.setValorgasto(Integer.valueOf(nuevoValorCompra));
            nuevaGastoAdicional.setProyecto(proyecto);
            administrarGastoAdicionalProyectoBO.crearGastoAdicional(nuevaGastoAdicional);
        } catch (Exception e) {
            System.out.println("Error ControllerRegistrarGastoAdicional almacenarNuevoGastoAdicionalEnSistema : " + e.toString());
        }
    }

    public void limpiarFormulario() {
        nuevoFechaCompra = new Date();
        nuevoEvento = null;
        nuevoValorCompra = "0";
        fechaDiferida = true;
        //
        validacionesFechaCompra = true;
        validacionesEvento = false;
        validacionesValorCompra = false;
        mensajeFormulario = "";
    }

    public String cancelarRegistroGastoAdicional() {
        nuevoFechaCompra = new Date();
        nuevoEvento = null;
        fechaDiferida = true;
        nuevoValorCompra = "0";
        //
        validacionesFechaCompra = true;
        validacionesEvento = false;
        validacionesValorCompra = false;
        activarAceptar = false;
        mensajeFormulario = "N/A";
        activarLimpiar = true;
        colorMensaje = "black";
        activarCasillas = false;
        return "administrargastoadicional";
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

    public Date getNuevoFechaCompra() {
        return nuevoFechaCompra;
    }

    public void setNuevoFechaCompra(Date nuevoFechaCompra) {
        this.nuevoFechaCompra = nuevoFechaCompra;
    }

    public String getNuevoValorCompra() {
        return nuevoValorCompra;
    }

    public void setNuevoValorCompra(String nuevoValorCompra) {
        this.nuevoValorCompra = nuevoValorCompra;
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

    public String getColorMensaje() {
        return colorMensaje;
    }

    public void setColorMensaje(String colorMensaje) {
        this.colorMensaje = colorMensaje;
    }

}
