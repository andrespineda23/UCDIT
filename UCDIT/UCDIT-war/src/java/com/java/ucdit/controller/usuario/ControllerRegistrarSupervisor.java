/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.controller.usuario;

import com.java.ucdit.bo.interfaces.usuario.AdministrarSupervisorBOInterface;
import com.java.ucdit.entidades.Persona;
import com.java.ucdit.entidades.Supervisor;
import com.java.ucdit.utilidades.Utilidades;
import java.io.Serializable;
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
public class ControllerRegistrarSupervisor implements Serializable {

    @EJB
    AdministrarSupervisorBOInterface administrarSupervisorBO;
    //
    private String nuevoNombre, nuevoIdentificacion, nuevoApellido, nuevoValorHora;
    private String nuevoTelFijo, nuevoTelCelular, nuevoCorreo, nuevoAreaEnfoque;
    //
    private boolean validacionesNombre, validacionesIdentificacion, validacionesApellido, validacionesValorHora;
    private boolean validacionesTelFijo, validacionesTelCelular, validacionesCorreo, validacionesArea;
    private String mensajeFormulario;
    private boolean activarCasillas;
    private String colorMensaje;
    private boolean activarLimpiar;
    private boolean activarAceptar;

    public ControllerRegistrarSupervisor() {
    }

    @PostConstruct
    public void init() {
        nuevoValorHora = null;
        nuevoIdentificacion = null;
        nuevoNombre = null;
        nuevoCorreo = null;
        nuevoTelCelular = null;
        nuevoAreaEnfoque = null;
        nuevoApellido = null;
        nuevoTelFijo = null;
        //
        validacionesValorHora = false;
        validacionesIdentificacion = false;
        validacionesNombre = false;
        validacionesCorreo = false;
        validacionesTelCelular = true;
        validacionesApellido = false;
        validacionesArea = false;
        validacionesTelFijo = true;
        activarLimpiar = true;
        colorMensaje = "black";
        activarCasillas = false;
        activarAceptar = false;
        mensajeFormulario = "N/A";
    }

    public void validarNombreSupervisor() {
        if (Utilidades.validarNulo(nuevoNombre) && (!nuevoNombre.isEmpty()) && (nuevoNombre.trim().length() > 0)) {
            int tam = nuevoNombre.length();
            if (tam >= 4) {
                if (!Utilidades.validarCaracterString(nuevoNombre)) {
                    validacionesNombre = false;
                    FacesContext.getCurrentInstance().addMessage("form:nuevoNombre", new FacesMessage("El nombre ingresado es incorrecto."));
                } else {
                    validacionesNombre = true;
                }
            } else {
                validacionesNombre = false;
                FacesContext.getCurrentInstance().addMessage("form:nuevoNombre", new FacesMessage("El tamaño minimo permitido es 4 caracteres."));
            }
        } else {
            validacionesNombre = false;
            FacesContext.getCurrentInstance().addMessage("form:nuevoNombre", new FacesMessage("El nombre es obligatorio."));
        }
    }

    public void validarApellidoSupervisor() {
        if (Utilidades.validarNulo(nuevoApellido) && (!nuevoApellido.isEmpty()) && (nuevoApellido.trim().length() > 0)) {
            int tam = nuevoApellido.length();
            if (tam >= 4) {
                if ((Utilidades.validarCaracterString(nuevoApellido)) == false) {
                    validacionesApellido = false;
                    FacesContext.getCurrentInstance().addMessage("form:nuevoApellido", new FacesMessage("El apellido ingresado es incorrecto."));
                } else {
                    validacionesApellido = true;
                }
            } else {
                validacionesApellido = false;
                FacesContext.getCurrentInstance().addMessage("form:nuevoApellido", new FacesMessage("La tamaño minimo permitido es 4 caracteres."));
            }
        } else {
            validacionesApellido = false;
            FacesContext.getCurrentInstance().addMessage("form:nuevoApellido", new FacesMessage("El apellido es obligatorio."));
        }
    }

    public void validarAreaEnfoqueSupervisor() {
        if (Utilidades.validarNulo(nuevoAreaEnfoque) && (!nuevoAreaEnfoque.isEmpty()) && (nuevoAreaEnfoque.trim().length() > 0)) {
            int tam = nuevoAreaEnfoque.length();
            if (tam >= 4) {
                if ((Utilidades.validarCaracteresAlfaNumericos(nuevoAreaEnfoque)) == false) {
                    validacionesArea = false;
                    FacesContext.getCurrentInstance().addMessage("form:nuevoAreaEnfoque", new FacesMessage("El areá enfoque ingresado es incorrecto."));
                } else {
                    validacionesArea = true;
                }
            } else {
                validacionesArea = false;
                FacesContext.getCurrentInstance().addMessage("form:nuevoAreaEnfoque", new FacesMessage("La tamaño minimo permitido es 4 caracteres."));
            }
        } else {
            validacionesArea = false;
            FacesContext.getCurrentInstance().addMessage("form:nuevoAreaEnfoque", new FacesMessage("El areá enfoque es obligatorio."));
        }
    }

    public void validarIdentificacionSupervisor() {
        if (Utilidades.validarNulo(nuevoIdentificacion) && (!nuevoIdentificacion.isEmpty()) && (nuevoIdentificacion.trim().length() > 0)) {
            int tam = nuevoIdentificacion.length();
            if (tam >= 8) {
                if (Utilidades.validarCaracteresAlfaNumericos(nuevoIdentificacion)) {
                    Supervisor registro = administrarSupervisorBO.obtenerSupervisorPorDocumento(nuevoIdentificacion);
                    if (registro == null) {
                        validacionesIdentificacion = true;
                    } else {
                        validacionesIdentificacion = false;
                        FacesContext.getCurrentInstance().addMessage("form:nuevoIdentificacion", new FacesMessage("La identificación ingresada ya esta registrada."));
                    }
                } else {
                    validacionesIdentificacion = false;
                    FacesContext.getCurrentInstance().addMessage("form:nuevoIdentificacion", new FacesMessage("La identificación ingresado es incorrecta."));
                }
            } else {
                validacionesIdentificacion = false;
                FacesContext.getCurrentInstance().addMessage("form:nuevoIdentificacion", new FacesMessage("La tamaño minimo permitido es 8 caracteres."));
            }
        } else {
            validacionesIdentificacion = false;
            FacesContext.getCurrentInstance().addMessage("form:nuevoIdentificacion", new FacesMessage("La identificación es obligatoria."));
        }
    }

    public void validarCorreoSupervisor() {
        if (Utilidades.validarNulo(nuevoCorreo) && (!nuevoCorreo.isEmpty()) && (nuevoCorreo.trim().length() > 0)) {
            int tam = nuevoCorreo.length();
            if (tam >= 15) {
                if (Utilidades.validarCorreoElectronico(nuevoCorreo)) {
                    Supervisor registro = administrarSupervisorBO.obtenerSupervisorPorCorreo(nuevoCorreo);
                    if (registro == null) {
                        validacionesCorreo = true;
                    } else {
                        validacionesCorreo = false;
                        FacesContext.getCurrentInstance().addMessage("form:nuevoCorreo", new FacesMessage("El correo ingresado ya esta registrado."));
                    }
                } else {
                    validacionesCorreo = false;
                    FacesContext.getCurrentInstance().addMessage("form:nuevoCorreo", new FacesMessage("El correo ingresado es incorrecto."));
                }
            } else {
                validacionesCorreo = false;
                FacesContext.getCurrentInstance().addMessage("form:nuevoCorreo", new FacesMessage("La tamaño minimo permitido es 15 caracteres."));
            }
        } else {
            validacionesCorreo = false;
            FacesContext.getCurrentInstance().addMessage("form:nuevoCorreo", new FacesMessage("El correo es obligatorio."));
        }
    }

    public void validarValorHoraSupervisor() {
        if ((Utilidades.validarNulo(nuevoValorHora)) && (!nuevoValorHora.isEmpty()) && (nuevoValorHora.trim().length() > 0)) {
            if (Utilidades.isNumber(nuevoValorHora)) {
                validacionesValorHora = true;
            } else {
                FacesContext.getCurrentInstance().addMessage("form:nuevoValorHora", new FacesMessage("El valor hora es incorrecto."));
                validacionesValorHora = false;
            }
        } else {
            FacesContext.getCurrentInstance().addMessage("form:nuevoValorHora", new FacesMessage("El valor hora es obligatorio."));
            validacionesValorHora = false;
        }
    }

    public void validarDatosOpcionales(int tipoReg) {
        if (tipoReg == 1) {
            if (Utilidades.validarNulo(nuevoTelFijo) && (!nuevoTelFijo.isEmpty()) && (nuevoTelFijo.trim().length() > 0)) {
                int tam = nuevoTelFijo.length();
                if (tam == 7) {
                    if (Utilidades.isNumber(nuevoTelFijo)) {
                        validacionesTelFijo = true;
                    } else {
                        validacionesTelFijo = false;
                        FacesContext.getCurrentInstance().addMessage("form:nuevoTelFijo", new FacesMessage("El nombre se encuentra incorrecto."));
                    }
                } else {
                    validacionesTelFijo = false;
                    FacesContext.getCurrentInstance().addMessage("form:nuevoTelFijo", new FacesMessage("El tamaño permitido es 7 caracteres."));
                }
            }
        } else {
            if (Utilidades.validarNulo(nuevoTelCelular) && (!nuevoTelCelular.isEmpty()) && (nuevoTelCelular.trim().length() > 0)) {
                int tam = nuevoTelCelular.length();
                if (tam == 10) {
                    if (Utilidades.isNumber(nuevoTelCelular)) {
                        validacionesTelCelular = true;
                    } else {
                        validacionesTelCelular = false;
                        FacesContext.getCurrentInstance().addMessage("form:nuevoTelCelular", new FacesMessage("El telefono se encuentra incorrecto."));
                    }
                } else {
                    validacionesTelCelular = false;
                    FacesContext.getCurrentInstance().addMessage("form:nuevoTelCelular", new FacesMessage("El tamaño permitido es 10 caracteres."));
                }
            }
        }
    }

    private boolean validarResultadosValidacion() {
        boolean retorno = true;
        if (validacionesArea == false) {
            retorno = false;
        }
        if (validacionesIdentificacion == false) {
            retorno = false;
        }
        if (validacionesValorHora == false) {
            retorno = false;
        }
        if (validacionesCorreo == false) {
            retorno = false;
        }
        if (validacionesNombre == false) {
            retorno = false;
        }
        if (validacionesApellido == false) {
            retorno = false;
        }
        if (validacionesTelFijo == false) {
            retorno = false;
        }
        if (validacionesTelCelular == false) {
            retorno = false;
        }
        return retorno;
    }

    /**
     * Metodo encargado de realizar el registro y validaciones de la información
     * del nuevo docente
     */
    public void registrarNuevoSupervisor() {
        if (validarResultadosValidacion() == true) {
            almacenarNuevoSupervisorEnSistema();
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

    public void almacenarNuevoSupervisorEnSistema() {
        try {
            Persona nuevaPersona = new Persona();
            Supervisor nuevaSupervisor = new Supervisor();
            nuevaPersona.setNumerodocumento(nuevoIdentificacion);
            nuevaPersona.setNombrepersona(nuevoNombre);
            nuevaPersona.setValorhoratrabajo(Integer.valueOf(nuevoValorHora));
            nuevaPersona.setApellidopersona(nuevoApellido);
            nuevaPersona.setCorreoelectronico(nuevoCorreo);
            if (Utilidades.validarNulo(nuevoTelFijo)) {
                nuevaPersona.setNumerofijo(nuevoTelFijo);
            } else {
                nuevaPersona.setNumerofijo("");
            }
            if (Utilidades.validarNulo(nuevoTelCelular)) {
                nuevaPersona.setNumerocelular(nuevoTelCelular);
            } else {
                nuevaPersona.setNumerocelular("");
            }
            nuevaSupervisor.setAreaenfoque(nuevoAreaEnfoque);
            administrarSupervisorBO.crearSupervisor(nuevaPersona, nuevaSupervisor);
        } catch (Exception e) {
            System.out.println("Error ControllerRegistrarSupervisor almacenarNuevoSupervisorEnSistema : " + e.toString());
        }
    }

    public void limpiarFormulario() {
        nuevoValorHora = null;
        nuevoIdentificacion = null;
        nuevoNombre = null;
        nuevoTelCelular = null;
        nuevoApellido = null;
        nuevoAreaEnfoque = null;
        nuevoCorreo = null;
        nuevoTelFijo = null;
        //
        validacionesValorHora = false;
        validacionesIdentificacion = false;
        validacionesNombre = false;
        validacionesTelCelular = true;
        validacionesArea = false;
        validacionesApellido = false;
        validacionesCorreo = false;
        validacionesTelFijo = true;
        mensajeFormulario = "";
    }

    public String cancelarRegistroSupervisor() {
        nuevoValorHora = null;
        nuevoIdentificacion = null;
        nuevoNombre = null;
        nuevoTelCelular = null;
        nuevoApellido = null;
        nuevoCorreo = null;
        nuevoAreaEnfoque = null;
        nuevoTelFijo = null;
        //
        validacionesValorHora = false;
        validacionesIdentificacion = false;
        validacionesNombre = false;
        validacionesTelCelular = true;
        validacionesCorreo = false;
        validacionesApellido = false;
        validacionesArea = false;
        validacionesTelFijo = true;
        activarAceptar = false;
        mensajeFormulario = "N/A";
        activarLimpiar = true;
        colorMensaje = "black";
        activarCasillas = false;
        return "administrarsupervisor";
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
    public String getNuevoNombre() {
        return nuevoNombre;
    }

    public void setNuevoNombre(String nuevoNombre) {
        this.nuevoNombre = nuevoNombre;
    }

    public String getNuevoIdentificacion() {
        return nuevoIdentificacion;
    }

    public void setNuevoIdentificacion(String nuevoIdentificacion) {
        this.nuevoIdentificacion = nuevoIdentificacion;
    }

    public String getNuevoApellido() {
        return nuevoApellido;
    }

    public void setNuevoApellido(String nuevoApellido) {
        this.nuevoApellido = nuevoApellido;
    }

    public String getNuevoValorHora() {
        return nuevoValorHora;
    }

    public void setNuevoValorHora(String nuevoValorHora) {
        this.nuevoValorHora = nuevoValorHora;
    }

    public String getNuevoTelFijo() {
        return nuevoTelFijo;
    }

    public void setNuevoTelFijo(String nuevoTelFijo) {
        this.nuevoTelFijo = nuevoTelFijo;
    }

    public String getNuevoTelCelular() {
        return nuevoTelCelular;
    }

    public void setNuevoTelCelular(String nuevoTelCelular) {
        this.nuevoTelCelular = nuevoTelCelular;
    }

    public String getNuevoCorreo() {
        return nuevoCorreo;
    }

    public void setNuevoCorreo(String nuevoCorreo) {
        this.nuevoCorreo = nuevoCorreo;
    }

    public String getNuevoAreaEnfoque() {
        return nuevoAreaEnfoque;
    }

    public void setNuevoAreaEnfoque(String nuevoAreaEnfoque) {
        this.nuevoAreaEnfoque = nuevoAreaEnfoque;
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

}
