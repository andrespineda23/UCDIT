/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.controller.proyecto;

import com.java.ucdit.bo.interfaces.proyecto.AdministrarGastoAdicionalProyectoBOInterface;
import com.java.ucdit.entidades.GastoAdicional;
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
public class ControllerDetallesGastoAdicional implements Serializable {

    @EJB
    AdministrarGastoAdicionalProyectoBOInterface administrarGastoAdicionalProyectoBO;

    //
    private String editarEvento;
    private Date editarFechaCompra;
    private String editarValorCompra;
    //
    private boolean validacionesEvento, validacionesFechaCompra;
    private boolean validacionesValorCompra;
    private String mensajeFormulario;
    private String colorMensaje;
    private boolean fechaDiferida;
    private BigInteger idGastoAdicional;
    private GastoAdicional gastoAdicionalDetalle;
    private boolean modificacionRegistro;

    public ControllerDetallesGastoAdicional() {
    }

    @PostConstruct
    public void init() {
        colorMensaje = "black";
        mensajeFormulario = "N/A";
    }

    public void recibirIdGastoAdicional(BigInteger idGastoAdicional) {
        this.idGastoAdicional = idGastoAdicional;
        gastoAdicionalDetalle = administrarGastoAdicionalProyectoBO.obtenerGastoAdicionalPorId(idGastoAdicional);
        cargarInformacionRegistro();
    }

    private void cargarInformacionRegistro() {
        if (Utilidades.validarNulo(gastoAdicionalDetalle)) {
            editarEvento = gastoAdicionalDetalle.getDescripcion();
            editarFechaCompra = gastoAdicionalDetalle.getFechagasto();
            editarValorCompra = String.valueOf(gastoAdicionalDetalle.getValorgasto());
            fechaDiferida = true;
            modificacionRegistro = false;
            //
            validacionesFechaCompra = true;
            validacionesEvento = true;
            validacionesValorCompra = true;
        }
    }

    public void validarEventoGastoAdicional() {
        if (Utilidades.validarNulo(editarEvento) && (!editarEvento.isEmpty()) && (editarEvento.trim().length() > 0)) {
            int tam = editarEvento.length();
            if (tam >= 4) {
                if (!Utilidades.validarCaracteresAlfaNumericos(editarEvento)) {
                    validacionesEvento = false;
                    FacesContext.getCurrentInstance().addMessage("form:editarEvento", new FacesMessage("El evento ingresado es incorrecto."));
                } else {
                    validacionesEvento = true;
                }
            } else {
                validacionesEvento = false;
                FacesContext.getCurrentInstance().addMessage("form:editarEvento", new FacesMessage("El tamaño minimo permitido es 4 caracteres."));
            }
        } else {
            validacionesEvento = false;
            FacesContext.getCurrentInstance().addMessage("form:editarEvento", new FacesMessage("El nombre es obligatorio."));
        }
    }

    public void validarValorCompraGastoAdicional() {
        if (Utilidades.validarNulo(editarValorCompra) && (!editarValorCompra.isEmpty()) && (editarValorCompra.trim().length() > 0)) {
            if (Utilidades.isNumber(editarValorCompra)) {
                validacionesValorCompra = true;
            } else {
                validacionesValorCompra = false;
                FacesContext.getCurrentInstance().addMessage("form:editarValorCompra", new FacesMessage("El valor compra es incorrecto."));
            }
        } else {
            validacionesValorCompra = false;
            FacesContext.getCurrentInstance().addMessage("form:editarValorCompra", new FacesMessage("El valor compra es obligatorio."));
        }
    }

    public void validarFechaCompraGastoAdicional() {
        if (Utilidades.validarNulo(editarFechaCompra)) {
            if (fechaDiferida == true) {
                editarFechaCompra = new Date();
                if (Utilidades.fechaIngresadaCorrecta(editarFechaCompra)) {
                    validacionesFechaCompra = true;
                } else {
                    FacesContext.getCurrentInstance().addMessage("form:editarFechaCompra", new FacesMessage("La fecha de compra es incorrecta."));
                    validacionesFechaCompra = false;
                }
            } else {
                if (Utilidades.fechaDiferidaIngresadaCorrecta(editarFechaCompra)) {
                    validacionesFechaCompra = true;
                } else {
                    FacesContext.getCurrentInstance().addMessage("form:editarFechaCompra", new FacesMessage("La fecha de compra es incorrecta."));
                    validacionesFechaCompra = false;
                }
            }
        } else {
            FacesContext.getCurrentInstance().addMessage("form:editarFechaCompra", new FacesMessage("La fecha de compra es obligatoria."));
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
            recibirIdGastoAdicional(idGastoAdicional);
            colorMensaje = "green";
            mensajeFormulario = "El formulario ha sido ingresado con exito.";
        } else {
            colorMensaje = "red";
            mensajeFormulario = "Existen errores en el formulario, por favor corregir para continuar.";
        }
    }

    private void almacenarNuevoGastoAdicionalEnSistema() {
        try {
            gastoAdicionalDetalle.setDescripcion(editarEvento);
            gastoAdicionalDetalle.setFechagasto(editarFechaCompra);
            gastoAdicionalDetalle.setValorgasto(Integer.valueOf(editarValorCompra));
            administrarGastoAdicionalProyectoBO.editarGastoAdicional(gastoAdicionalDetalle);
        } catch (Exception e) {
            System.out.println("Error ControllerRegistrarGastoAdicional almacenarNuevoGastoAdicionalEnSistema : " + e.toString());
        }
    }

    public String cancelarRegistroGastoAdicional() {
        editarFechaCompra = new Date();
        editarEvento = null;
        fechaDiferida = true;
        editarValorCompra = "0";
        //
        validacionesFechaCompra = true;
        validacionesEvento = false;
        validacionesValorCompra = false;
        mensajeFormulario = "N/A";
        idGastoAdicional = null;
        gastoAdicionalDetalle = null;
        colorMensaje = "black";
        return "administrargastoadicional";
    }

    //GET-SET
    public String getEditarEvento() {
        return editarEvento;
    }

    public void setEditarEvento(String editarEvento) {
        this.editarEvento = editarEvento;
    }

    public Date getEditarFechaCompra() {
        return editarFechaCompra;
    }

    public void setEditarFechaCompra(Date editarFechaCompra) {
        this.editarFechaCompra = editarFechaCompra;
    }

    public String getEditarValorCompra() {
        return editarValorCompra;
    }

    public void setEditarValorCompra(String editarValorCompra) {
        this.editarValorCompra = editarValorCompra;
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

    public BigInteger getIdGastoAdicional() {
        return idGastoAdicional;
    }

    public void setIdGastoAdicional(BigInteger idGastoAdicional) {
        this.idGastoAdicional = idGastoAdicional;
    }

    public GastoAdicional getGastoAdicionalDetalle() {
        return gastoAdicionalDetalle;
    }

    public void setGastoAdicionalDetalle(GastoAdicional gastoAdicionalDetalle) {
        this.gastoAdicionalDetalle = gastoAdicionalDetalle;
    }

    public boolean isModificacionRegistro() {
        return modificacionRegistro;
    }

    public void setModificacionRegistro(boolean modificacionRegistro) {
        this.modificacionRegistro = modificacionRegistro;
    }

}
