/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.controller.usuario;

import com.java.ucdit.bo.interfaces.usuario.AdministrarSupervisorBOInterface;
import com.java.ucdit.entidades.Supervisor;
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
public class ControllerDetallesSupervisor implements Serializable {

    @EJB
    AdministrarSupervisorBOInterface administrarSupervisorBO;
    //
    private Supervisor supervisorDetalles;
    private BigInteger idSupervisor;
    //
    private String editarNombre, editarIdentificacion, editarApellido, editarValorHora;
    private String editarTelFijo, editarTelCelular, editarCorreo, editarAreaEnfoque;
    private boolean editarEstado;
    //
    private boolean validacionesNombre, validacionesIdentificacion, validacionesApellido, validacionesValorHora;
    private boolean validacionesTelFijo, validacionesTelCelular, validacionesCorreo, validacionesArea;
    private String mensajeFormulario;
    private String colorMensaje;
    private boolean modificacionRegistro;

    public ControllerDetallesSupervisor() {
    }

    @PostConstruct
    public void init() {
        colorMensaje = "black";
        mensajeFormulario = "N/A";
    }

    public void recibirIdSupervisorDetalle(BigInteger idSupervisor) {
        this.idSupervisor = idSupervisor;
        supervisorDetalles = administrarSupervisorBO.obtenerSupervisorPorId(this.idSupervisor);
        modificacionRegistro = false;
        cargarInformacionRegistro();
    }

    private void cargarInformacionRegistro() {
        if (Utilidades.validarNulo(supervisorDetalles)) {
            editarValorHora = String.valueOf(supervisorDetalles.getPersona().getValorhoratrabajo());
            editarIdentificacion = supervisorDetalles.getPersona().getNumerodocumento();
            editarNombre = supervisorDetalles.getPersona().getNombrepersona();
            editarCorreo = supervisorDetalles.getPersona().getCorreoelectronico();
            editarTelCelular = supervisorDetalles.getPersona().getNumerocelular();
            editarAreaEnfoque = supervisorDetalles.getAreaenfoque();
            editarApellido = supervisorDetalles.getPersona().getApellidopersona();
            editarTelFijo = supervisorDetalles.getPersona().getNumerofijo();
            editarEstado = supervisorDetalles.getPersona().getUsuario().getEstado();
            //
            validacionesValorHora = true;
            validacionesIdentificacion = true;
            validacionesNombre = true;
            validacionesCorreo = true;
            validacionesTelCelular = true;
            validacionesApellido = true;
            validacionesArea = true;
            validacionesTelFijo = true;
        }
    }

    private void restaurarRegistro() {
        colorMensaje = "black";
        mensajeFormulario = "N/A";
        modificacionRegistro = false;
        supervisorDetalles = null;
        supervisorDetalles = administrarSupervisorBO.obtenerSupervisorPorId(this.idSupervisor);
        cargarInformacionRegistro();
    }

    public void validarNombreSupervisor() {
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

    public void validarApellidoSupervisor() {
        if (Utilidades.validarNulo(editarApellido) && (!editarApellido.isEmpty()) && (editarApellido.trim().length() > 0)) {
            int tam = editarApellido.length();
            if (tam >= 4) {
                if ((Utilidades.validarCaracterString(editarApellido)) == false) {
                    validacionesApellido = false;
                    FacesContext.getCurrentInstance().addMessage("form:editarApellido", new FacesMessage("El apellido ingresado es incorrecto."));
                } else {
                    validacionesApellido = true;
                }
            } else {
                validacionesApellido = false;
                FacesContext.getCurrentInstance().addMessage("form:editarApellido", new FacesMessage("La tamaño minimo permitido es 4 caracteres."));
            }
        } else {
            validacionesApellido = false;
            FacesContext.getCurrentInstance().addMessage("form:editarApellido", new FacesMessage("El apellido es obligatorio."));
        }
        modificacionRegistro = true;
    }

    public void validarAreaEnfoqueSupervisor() {
        if (Utilidades.validarNulo(editarAreaEnfoque) && (!editarAreaEnfoque.isEmpty()) && (editarAreaEnfoque.trim().length() > 0)) {
            int tam = editarAreaEnfoque.length();
            if (tam >= 4) {
                if ((Utilidades.validarCaracteresAlfaNumericos(editarAreaEnfoque)) == false) {
                    validacionesArea = false;
                    FacesContext.getCurrentInstance().addMessage("form:editarAreaEnfoque", new FacesMessage("El areá enfoque ingresado es incorrecto."));
                } else {
                    validacionesArea = true;
                }
            } else {
                validacionesArea = false;
                FacesContext.getCurrentInstance().addMessage("form:editarAreaEnfoque", new FacesMessage("La tamaño minimo permitido es 4 caracteres."));
            }
        } else {
            validacionesArea = false;
            FacesContext.getCurrentInstance().addMessage("form:editarAreaEnfoque", new FacesMessage("El areá enfoque es obligatorio."));
        }
        modificacionRegistro = true;
    }

    public void validarIdentificacionSupervisor() {
        if (Utilidades.validarNulo(editarIdentificacion) && (!editarIdentificacion.isEmpty()) && (editarIdentificacion.trim().length() > 0)) {
            int tam = editarIdentificacion.length();
            if (tam >= 8) {
                if (Utilidades.validarCaracteresAlfaNumericos(editarIdentificacion)) {
                    Supervisor registro = administrarSupervisorBO.obtenerSupervisorPorDocumento(editarIdentificacion);
                    if (registro == null) {
                        validacionesIdentificacion = true;
                    } else {
                        if (!supervisorDetalles.getIdsupervisor().equals(registro.getIdsupervisor())) {
                            validacionesIdentificacion = false;
                            FacesContext.getCurrentInstance().addMessage("form:editarIdentificacion", new FacesMessage("La identificación ingresada ya esta registrada."));
                        } else {
                            validacionesIdentificacion = true;
                        }
                    }
                } else {
                    validacionesIdentificacion = false;
                    FacesContext.getCurrentInstance().addMessage("form:editarIdentificacion", new FacesMessage("La identificación ingresado es incorrecta."));
                }
            } else {
                validacionesIdentificacion = false;
                FacesContext.getCurrentInstance().addMessage("form:editarIdentificacion", new FacesMessage("La tamaño minimo permitido es 8 caracteres."));
            }
        } else {
            validacionesIdentificacion = false;
            FacesContext.getCurrentInstance().addMessage("form:editarIdentificacion", new FacesMessage("La identificación es obligatoria."));
        }
        modificacionRegistro = true;
    }

    public void validarCorreoSupervisor() {
        if (Utilidades.validarNulo(editarCorreo) && (!editarCorreo.isEmpty()) && (editarCorreo.trim().length() > 0)) {
            int tam = editarCorreo.length();
            if (tam >= 15) {
                if (Utilidades.validarCorreoElectronico(editarCorreo)) {
                    Supervisor registro = administrarSupervisorBO.obtenerSupervisorPorCorreo(editarCorreo);
                    if (registro == null) {
                        validacionesCorreo = true;
                    } else {
                        if (!supervisorDetalles.getIdsupervisor().equals(registro.getIdsupervisor())) {
                            validacionesCorreo = false;
                            FacesContext.getCurrentInstance().addMessage("form:editarCorreo", new FacesMessage("El correo ingresado ya esta registrado."));
                        } else {
                            validacionesCorreo = true;
                        }
                    }
                } else {
                    validacionesCorreo = false;
                    FacesContext.getCurrentInstance().addMessage("form:editarCorreo", new FacesMessage("El correo ingresado es incorrecto."));
                }
            } else {
                validacionesCorreo = false;
                FacesContext.getCurrentInstance().addMessage("form:editarCorreo", new FacesMessage("La tamaño minimo permitido es 15 caracteres."));
            }
        } else {
            validacionesCorreo = false;
            FacesContext.getCurrentInstance().addMessage("form:editarCorreo", new FacesMessage("El correo es obligatorio."));
        }
        modificacionRegistro = true;
    }

    public void validarValorHoraSupervisor() {
        if ((Utilidades.validarNulo(editarValorHora)) && (!editarValorHora.isEmpty()) && (editarValorHora.trim().length() > 0)) {
            if (Utilidades.isNumber(editarValorHora)) {
                validacionesValorHora = true;
            } else {
                FacesContext.getCurrentInstance().addMessage("form:editarValorHora", new FacesMessage("El valor hora es incorrecto."));
                validacionesValorHora = false;
            }
        } else {
            FacesContext.getCurrentInstance().addMessage("form:editarValorHora", new FacesMessage("El valor hora es obligatorio."));
            validacionesValorHora = false;
        }
        modificacionRegistro = true;
    }

    public void validarEstado() {
        modificacionRegistro = true;
    }

    public void validarDatosOpcionales(int tipoReg) {
        if (tipoReg == 1) {
            if (Utilidades.validarNulo(editarTelFijo) && (!editarTelFijo.isEmpty()) && (editarTelFijo.trim().length() > 0)) {
                int tam = editarTelFijo.length();
                if (tam == 7) {
                    if (Utilidades.isNumber(editarTelFijo)) {
                        validacionesTelFijo = true;
                    } else {
                        validacionesTelFijo = false;
                        FacesContext.getCurrentInstance().addMessage("form:editarTelFijo", new FacesMessage("El nombre se encuentra incorrecto."));
                    }
                } else {
                    validacionesTelFijo = false;
                    FacesContext.getCurrentInstance().addMessage("form:editarTelFijo", new FacesMessage("El tamaño permitido es 7 caracteres."));
                }
            }
        } else {
            if (Utilidades.validarNulo(editarTelCelular) && (!editarTelCelular.isEmpty()) && (editarTelCelular.trim().length() > 0)) {
                int tam = editarTelCelular.length();
                if (tam == 10) {
                    if (Utilidades.isNumber(editarTelCelular)) {
                        validacionesTelCelular = true;
                    } else {
                        validacionesTelCelular = false;
                        FacesContext.getCurrentInstance().addMessage("form:editarTelCelular", new FacesMessage("El telefono se encuentra incorrecto."));
                    }
                } else {
                    validacionesTelCelular = false;
                    FacesContext.getCurrentInstance().addMessage("form:editarTelCelular", new FacesMessage("El tamaño permitido es 10 caracteres."));
                }
            }
        }
        modificacionRegistro = true;
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
     * del editar docente
     */
    public void registrarModificacionSupervisor() {
        if (modificacionRegistro == true) {
            if (validarResultadosValidacion() == true) {
                almacenarModificacionSupervisorEnSistema();
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

    public void almacenarModificacionSupervisorEnSistema() {
        try {
            supervisorDetalles.getPersona().getUsuario().setEstado(editarEstado);
            supervisorDetalles.getPersona().setNumerodocumento(editarIdentificacion);
            supervisorDetalles.getPersona().setNombrepersona(editarNombre);
            supervisorDetalles.getPersona().setValorhoratrabajo(Integer.valueOf(editarValorHora));
            supervisorDetalles.getPersona().setApellidopersona(editarApellido);
            supervisorDetalles.getPersona().setCorreoelectronico(editarCorreo);
            if (Utilidades.validarNulo(editarTelFijo)) {
                supervisorDetalles.getPersona().setNumerofijo(editarTelFijo);
            } else {
                supervisorDetalles.getPersona().setNumerofijo("");
            }
            if (Utilidades.validarNulo(editarTelCelular)) {
                supervisorDetalles.getPersona().setNumerocelular(editarTelCelular);
            } else {
                supervisorDetalles.getPersona().setNumerocelular("");
            }
            supervisorDetalles.setAreaenfoque(editarAreaEnfoque);
            administrarSupervisorBO.editarSupervisor(supervisorDetalles);
        } catch (Exception e) {
            System.out.println("Error ControllerRegistrarSupervisor almacenarModificacionSupervisorEnSistema : " + e.toString());
        }
    }

    public String cancelarRegistroSupervisor() {
        editarValorHora = null;
        editarIdentificacion = null;
        editarNombre = null;
        editarTelCelular = null;
        editarApellido = null;
        editarCorreo = null;
        editarAreaEnfoque = null;
        editarTelFijo = null;
        //
        validacionesValorHora = true;
        validacionesIdentificacion = true;
        validacionesNombre = true;
        validacionesTelCelular = true;
        validacionesCorreo = true;
        validacionesApellido = true;
        validacionesArea = true;
        validacionesTelFijo = true;
        mensajeFormulario = "N/A";
        colorMensaje = "black";
        return "administrarsupervisor";
    }

    //GET-SET
    public String getEditarNombre() {
        return editarNombre;
    }

    public void setEditarNombre(String editarNombre) {
        this.editarNombre = editarNombre;
    }

    public String getEditarIdentificacion() {
        return editarIdentificacion;
    }

    public void setEditarIdentificacion(String editarIdentificacion) {
        this.editarIdentificacion = editarIdentificacion;
    }

    public String getEditarApellido() {
        return editarApellido;
    }

    public void setEditarApellido(String editarApellido) {
        this.editarApellido = editarApellido;
    }

    public String getEditarValorHora() {
        return editarValorHora;
    }

    public void setEditarValorHora(String editarValorHora) {
        this.editarValorHora = editarValorHora;
    }

    public String getEditarTelFijo() {
        return editarTelFijo;
    }

    public void setEditarTelFijo(String editarTelFijo) {
        this.editarTelFijo = editarTelFijo;
    }

    public String getEditarTelCelular() {
        return editarTelCelular;
    }

    public void setEditarTelCelular(String editarTelCelular) {
        this.editarTelCelular = editarTelCelular;
    }

    public String getEditarCorreo() {
        return editarCorreo;
    }

    public void setEditarCorreo(String editarCorreo) {
        this.editarCorreo = editarCorreo;
    }

    public String getEditarAreaEnfoque() {
        return editarAreaEnfoque;
    }

    public void setEditarAreaEnfoque(String editarAreaEnfoque) {
        this.editarAreaEnfoque = editarAreaEnfoque;
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

    public boolean isEditarEstado() {
        return editarEstado;
    }

    public void setEditarEstado(boolean editarEstado) {
        this.editarEstado = editarEstado;
    }

}
