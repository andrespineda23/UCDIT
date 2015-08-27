/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.controller.seguridad;

import com.java.ucdit.bo.interfaces.AdministrarIndexBOInterface;
import com.java.ucdit.email.EnvioCorreo;
import com.java.ucdit.entidades.Persona;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author ELECTRONICA
 */
@ManagedBean
@SessionScoped
public class ControllerRecuperarContrasenia implements Serializable {

    @EJB
    AdministrarIndexBOInterface administrarIndexBO;

    private String numeroDocumento;
    private boolean validacionesDocumento;
    private String mensajeFormulario;
    private boolean activarBotones;

    public ControllerRecuperarContrasenia() {
    }

    @PostConstruct
    public void init() {
        numeroDocumento = null;
        validacionesDocumento = false;
        mensajeFormulario = "";
        activarBotones = false;
    }

    public void validarNumeroDocumento() {
        if (numeroDocumento != null && (!numeroDocumento.isEmpty()) && (numeroDocumento.trim().length() > 0)) {
            validacionesDocumento = true;
        } else {
            validacionesDocumento = false;
        }
    }

    private boolean validarProcesoRecuperacion() {
        boolean retorno = true;
        if (validacionesDocumento == false) {
            retorno = false;
        }
        return retorno;
    }

    public void recuperarContrasenia() {
        if (validarProcesoRecuperacion() == true) {
            Persona registro = administrarIndexBO.obtenerPersonaPorDocumento(numeroDocumento);
            if (null != registro) {
                String contrasenia = administrarIndexBO.recuperarContraseniaUsuario(registro.getUsuario());
                EnvioCorreo envio = new EnvioCorreo();
                envio.enviarCorreoRecuperacion(registro.getCorreoelectronico(), contrasenia);
                activarBotones = true;
                mensajeFormulario = "La nueva contraseña ha sido enviada al correo del usuario";
            } else {
                mensajeFormulario = "El documento ingresado no esta registrado";
            }
        } else {
            mensajeFormulario = "El documento es obligatorio";
        }
    }

    public void limpiarDatosFormulario() {
        numeroDocumento = null;
        validacionesDocumento = false;
        mensajeFormulario = "";
        activarBotones = false; 
    }

    //GET - SET

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getMensajeFormulario() {
        return mensajeFormulario;
    }

    public void setMensajeFormulario(String mensajeFormulario) {
        this.mensajeFormulario = mensajeFormulario;
    }

    public boolean isActivarBotones() {
        return activarBotones;
    }

    public void setActivarBotones(boolean activarBotones) {
        this.activarBotones = activarBotones;
    }

}
