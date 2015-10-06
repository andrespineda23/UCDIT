/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.controller.proyecto;

import com.java.ucdit.bo.interfaces.proyecto.AdministrarBitacoraProyectoBOInterface;
import com.java.ucdit.entidades.BitacoraProyecto;
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
public class ControllerDetallesBitacora implements Serializable {

    @EJB
    AdministrarBitacoraProyectoBOInterface administrarBitacoraProyectoBO;
    //
    private String editarEvento;
    private Date editarFechaEvento;
    //
    private boolean validacionesEvento, validacionesFechaEvento;
    private String mensajeFormulario;
    private String colorMensaje;
    private boolean fechaDiferida;
    private BigInteger idBitacora;
    private BitacoraProyecto bitacoraProyectoEditar;
    private boolean modificacionesRegistro;

    public ControllerDetallesBitacora() {
    }

    @PostConstruct
    public void init() {
        colorMensaje = "black";
        mensajeFormulario = "N/A";
    }

    public void recibirIdBitacoraProyecto(BigInteger idBitacora) {
        this.idBitacora = idBitacora;
        bitacoraProyectoEditar = administrarBitacoraProyectoBO.obtenerBitacoraProyectoPorId(idBitacora);
        cargarInformacionRegistro();
    }

    private void cargarInformacionRegistro() {
        if (Utilidades.validarNulo(bitacoraProyectoEditar)) {
            fechaDiferida = true;
            modificacionesRegistro = false;
            editarEvento = bitacoraProyectoEditar.getDetalleevento();
            editarFechaEvento = bitacoraProyectoEditar.getFecharegistro();
            //
            validacionesEvento = true;
            validacionesFechaEvento = true;
        }
    }

    public void validarEventoBitacoraProyecto() {
        if (Utilidades.validarNulo(editarEvento) && (!editarEvento.isEmpty()) && (editarEvento.trim().length() > 0)) {
            int tam = editarEvento.length();
            if (tam >= 4) {
                if (!Utilidades.validarCaracteresAlfaNumericos(editarEvento)) {
                    validacionesEvento = false;
                    FacesContext.getCurrentInstance().addMessage("form:editarEvento", new FacesMessage("El nombre ingresado es incorrecto."));
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
        modificacionesRegistro = true;
    }

    public void validarFechaEventoBitacoraProyecto() {
        if (Utilidades.validarNulo(editarFechaEvento)) {
            if (fechaDiferida == true) {
                editarFechaEvento = new Date();
                if (Utilidades.fechaIngresadaCorrecta(editarFechaEvento)) {
                    validacionesFechaEvento = true;
                } else {
                    validacionesFechaEvento = true;
                    FacesContext.getCurrentInstance().addMessage("form:editarFechaEvento", new FacesMessage("La fecha de inicio es incorrecta."));
                }
            } else {
                if (Utilidades.fechaDiferidaIngresadaCorrecta(editarFechaEvento)) {
                    validacionesFechaEvento = true;
                } else {
                    validacionesFechaEvento = true;
                    FacesContext.getCurrentInstance().addMessage("form:editarFechaEvento", new FacesMessage("La fecha de inicio es incorrecta."));
                }
            }
        } else {
            validacionesFechaEvento = true;
            FacesContext.getCurrentInstance().addMessage("form:editarFechaEvento", new FacesMessage("La fecha de inicio es obligatoria."));
        }
        modificacionesRegistro = true;
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

    public void registrarModificacionBitacoraProyecto() {
        if (modificacionesRegistro == true) {
            if (validarResultadosValidacion() == true) {
                almacenarModificacionBitacoraProyectoEnSistema();
                recibirIdBitacoraProyecto(this.idBitacora);
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

    private void almacenarModificacionBitacoraProyectoEnSistema() {
        try {
            bitacoraProyectoEditar.setDetalleevento(editarEvento);
            bitacoraProyectoEditar.setFecharegistro(editarFechaEvento);
            administrarBitacoraProyectoBO.editarBitacoraProyecto(bitacoraProyectoEditar);
        } catch (Exception e) {
            System.out.println("Error ControllerDetallesBitacora almacenarModificacionBitacoraProyectoEnSistema : " + e.toString());
        }
    }

    public String cancelarRegistroBitacoraProyecto() {
        editarEvento = null;
        editarFechaEvento = new Date();
        fechaDiferida = true;
        //
        validacionesEvento = false;
        validacionesFechaEvento = true;
        mensajeFormulario = "N/A";
        colorMensaje = "black";
        idBitacora = null;
        bitacoraProyectoEditar = null;
        modificacionesRegistro = false;
        return "administrarbitacora";
    }

    //GET-SET
    public String getEditarEvento() {
        return editarEvento;
    }

    public void setEditarEvento(String editarEvento) {
        this.editarEvento = editarEvento;
    }

    public Date getEditarFechaEvento() {
        return editarFechaEvento;
    }

    public void setEditarFechaEvento(Date editarFechaEvento) {
        this.editarFechaEvento = editarFechaEvento;
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

    public BigInteger getIdBitacora() {
        return idBitacora;
    }

    public void setIdBitacora(BigInteger idBitacora) {
        this.idBitacora = idBitacora;
    }

    public BitacoraProyecto getBitacoraProyectoEditar() {
        return bitacoraProyectoEditar;
    }

    public void setBitacoraProyectoEditar(BitacoraProyecto bitacoraProyectoEditar) {
        this.bitacoraProyectoEditar = bitacoraProyectoEditar;
    }

    public boolean isModificacionesRegistro() {
        return modificacionesRegistro;
    }

    public void setModificacionesRegistro(boolean modificacionesRegistro) {
        this.modificacionesRegistro = modificacionesRegistro;
    }

}
