/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.controller.seguridad;

import com.java.ucdit.bo.interfaces.AdministrarIndexBOInterface;
import com.java.ucdit.entidades.Persona;
import com.java.ucdit.entidades.PersonalInterno;
import com.java.ucdit.entidades.Supervisor;
import com.java.ucdit.utilidades.UsuarioLogin;
import com.java.ucdit.utilidades.Utilidades;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigInteger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.NoResultException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ELECTRONICA
 */
@ManagedBean(name = "controllerIndex")
@SessionScoped
public class ControllerIndex implements Serializable {

    @EJB
    AdministrarIndexBOInterface administrarIndexBO;

    private String usuario, contrasenia;
    private String paginaSiguiente;
    private UsuarioLogin usuarioLoginSistema;

    private boolean validacionesUsuario, validacionesContrasenia;
    private String mensajeFormulario;

    public ControllerIndex() {
    }

    @PostConstruct
    public void init() {
        validacionesUsuario = false;
        validacionesContrasenia = false;
        mensajeFormulario = "";
    }

    private boolean validarProcesoLogin() {
        boolean retorno = true;
        if (validacionesContrasenia == false) {
            retorno = false;
        }
        if (validacionesUsuario == false) {
            retorno = false;
        }
        return retorno;
    }

    private void ingresarAlSistema() {
        if (validarProcesoLogin() == true) {
            loginUsuario();
        } else {
            paginaSiguiente = "";
        }
    }

    public void validarUsuario() {
        if (usuario != null && (!usuario.isEmpty()) && (usuario.trim().length() > 0)) {
            validacionesUsuario = true;
        } else {
            validacionesUsuario = false;
        }
    }

    public void validarContrasenia() {
        if (contrasenia != null && (!contrasenia.isEmpty()) && (contrasenia.trim().length() > 0)) {
            validacionesContrasenia = true;
        } else {
            validacionesContrasenia = false;
        }
    }

    //Secuencia 1 Supervisor / Secuencia 2 Personal Interno
    private void loginUsuario() {
        paginaSiguiente = null;
        try {
            Persona personaLogin = null;
            personaLogin = administrarIndexBO.obtenerPersonaPorUsuarioContrasenia(usuario, Utilidades.codificarString(contrasenia));
            usuario = null;
            contrasenia = null;
            if (null != personaLogin) {
                if (personaLogin.getUsuario().getEstado() == true) {
                    BigInteger idTipoUsuario = personaLogin.getUsuario().getTipousuario().getIdtipousuario();
                    usuarioLoginSistema = new UsuarioLogin();
                    BigInteger secuenciaLogin = new BigInteger("1");
                    if (secuenciaLogin.equals(idTipoUsuario)) {
                        Supervisor registro = administrarIndexBO.obtenerSupervisorPorIdPersona(personaLogin.getIdpersona());
                        usuarioLoginSistema.setNombreTipoUsuario("SUPERVISOR");
                        usuarioLoginSistema.setIdUsuarioLogin(registro.getIdsupervisor());
                        usuarioLoginSistema.setUserUsuario(personaLogin.getUsuario().getUsuario());
                        paginaSiguiente = "iniciosupervisor";
                    } else {
                        PersonalInterno registro = administrarIndexBO.obtenerPersonalInternoPorIdPersona(personaLogin.getIdpersona());
                        usuarioLoginSistema.setNombreTipoUsuario("PERSONALINTERNO");
                        usuarioLoginSistema.setIdUsuarioLogin(registro.getIdpersonalinterno());
                        usuarioLoginSistema.setUserUsuario(personaLogin.getUsuario().getUsuario());
                        paginaSiguiente = "iniciopersonal";
                    }
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("sessionUsuario", usuarioLoginSistema);
                } else {
                    mensajeFormulario = "El usuario se encuentra inactivo.";
                }
            } else {
                mensajeFormulario = "El usuario ingresado no existe.";
            }
        } catch (NoResultException nre) {
            System.out.println("NoResultException loginUsuario ControllerIndex : " + nre.toString());
        } catch (Exception e) {
            System.out.println("Error ControllerIndex loginUsuario: " + e.toString());
        }
    }

    public void limpiarDatosFormulario() {
        validacionesUsuario = false;
        validacionesContrasenia = false;
        mensajeFormulario = "";
        usuarioLoginSistema = null;
        usuario = null;
        contrasenia = null;
    }

    /**
     * Metodo encargado de direccionar a la pagina respectiva del usuario que
     * realiza el login dentro del sistema
     *
     * @return
     */
    public String retornarPaginaSiguiente() {
        ingresarAlSistema();
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        if (null != session) {
            session.setAttribute("sessionUsuario", usuarioLoginSistema);
        }
        if (null == usuarioLoginSistema) {
            paginaSiguiente = "";
            return paginaSiguiente;
        }
        return paginaSiguiente;
    }

    public String cerrarSession() throws IOException, ServletException {
        FacesContext faceContext = FacesContext.getCurrentInstance();
        HttpServletRequest httpServletRequest = (HttpServletRequest) faceContext.getExternalContext().getRequest();
        httpServletRequest.logout();
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        try {
            if (null != session) {
                //session.invalidate();
            }
        } catch (Exception e) {
            System.out.println("Error al cerrar la sesion del usuario : " + e.toString());
        }
        return "index";
    }

    //GET-SET
    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getPaginaSiguiente() {
        return paginaSiguiente;
    }

    public void setPaginaSiguiente(String paginaSiguiente) {
        this.paginaSiguiente = paginaSiguiente;
    }

    public String getMensajeFormulario() {
        return mensajeFormulario;
    }

    public void setMensajeFormulario(String mensajeFormulario) {
        this.mensajeFormulario = mensajeFormulario;
    }

}
