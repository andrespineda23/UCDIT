/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.controller.usuario;

import com.java.ucdit.bo.interfaces.usuario.AdministrarPersonalInternoBOInterface;
import com.java.ucdit.entidades.PersonalInterno;
import com.java.ucdit.entidades.TipoPersonal;
import com.java.ucdit.utilidades.Utilidades;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
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
public class ControllerDetallesPersonalInterno implements Serializable {

    @EJB
    AdministrarPersonalInternoBOInterface administrarPersonalInternoBO;
    //
    private PersonalInterno personalInternoDetalles;
    private BigInteger idPersonalInterno;
    //
    private String editarNombre, editarIdentificacion, editarApellido, editarValorHora;
    private String editarTelFijo, editarTelCelular, editarCorreo;
    private List<TipoPersonal> listaTipoPersonal;
    private TipoPersonal editarTipoPersonal;
    //
    private boolean validacionesNombre, validacionesIdentificacion, validacionesApellido, validacionesValorHora;
    private boolean validacionesTelFijo, validacionesTelCelular, validacionesCorreo, validacionesTipoPersonal;
    private String mensajeFormulario;
    private String colorMensaje;
    private boolean modificacionRegistro;

    public ControllerDetallesPersonalInterno() {
    }

    @PostConstruct
    public void init() {
        colorMensaje = "black";
        mensajeFormulario = "N/A";
    }

    public void recibirIdPersonalInternoDetalle(BigInteger idPersonalInterno) {
        this.idPersonalInterno = idPersonalInterno;
        personalInternoDetalles = administrarPersonalInternoBO.obtenerPersonalInternoPorId(this.idPersonalInterno);
        modificacionRegistro = false;
        listaTipoPersonal = administrarPersonalInternoBO.obtenerTipoPersonalRegistrado();
        cargarInformacionRegistro();
    }

    private void cargarInformacionRegistro() {
        if (Utilidades.validarNulo(personalInternoDetalles)) {
            editarValorHora = String.valueOf(personalInternoDetalles.getPersona().getValorhoratrabajo());
            editarIdentificacion = personalInternoDetalles.getPersona().getNumerodocumento();
            editarNombre = personalInternoDetalles.getPersona().getNombrepersona();
            editarCorreo = personalInternoDetalles.getPersona().getCorreoelectronico();
            editarTelCelular = personalInternoDetalles.getPersona().getNumerocelular();
            editarTipoPersonal = personalInternoDetalles.getTipopersonal();
            editarApellido = personalInternoDetalles.getPersona().getApellidopersona();
            editarTelFijo = personalInternoDetalles.getPersona().getNumerofijo();
            //
            validacionesValorHora = true;
            validacionesIdentificacion = true;
            validacionesNombre = true;
            validacionesCorreo = true;
            validacionesTelCelular = true;
            validacionesApellido = true;
            validacionesTipoPersonal = true;
            validacionesTelFijo = true;
        }
    }

    private void restaurarRegistro() {
        colorMensaje = "black";
        mensajeFormulario = "N/A";
        modificacionRegistro = false;
        personalInternoDetalles = null;
        personalInternoDetalles = administrarPersonalInternoBO.obtenerPersonalInternoPorId(this.idPersonalInterno);
        cargarInformacionRegistro();
    }

    public void validarNombrePersonalInterno() {
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

    public void validarApellidoPersonalInterno() {
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

    public void validarIdentificacionPersonalInterno() {
        if (Utilidades.validarNulo(editarIdentificacion) && (!editarIdentificacion.isEmpty()) && (editarIdentificacion.trim().length() > 0)) {
            int tam = editarIdentificacion.length();
            if (tam >= 8) {
                if (Utilidades.validarCaracteresAlfaNumericos(editarIdentificacion)) {
                    PersonalInterno registro = administrarPersonalInternoBO.obtenerPersonalInternoPorDocumento(editarIdentificacion);
                    if (registro == null) {
                        validacionesIdentificacion = true;
                    } else {
                        if (!personalInternoDetalles.getIdpersonalinterno().equals(registro.getIdpersonalinterno())) {
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

    public void validarCorreoPersonalInterno() {
        if (Utilidades.validarNulo(editarCorreo) && (!editarCorreo.isEmpty()) && (editarCorreo.trim().length() > 0)) {
            int tam = editarCorreo.length();
            if (tam >= 15) {
                if (Utilidades.validarCorreoElectronico(editarCorreo)) {
                    PersonalInterno registro = administrarPersonalInternoBO.obtenerPersonalInternoPorCorreo(editarCorreo);
                    if (registro == null) {
                        validacionesCorreo = true;
                    } else {
                        if (!personalInternoDetalles.getIdpersonalinterno().equals(registro.getIdpersonalinterno())) {
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

    public void validarValorHoraPersonalInterno() {
        if ((Utilidades.validarNulo(editarValorHora)) && (!editarValorHora.isEmpty()) && (editarValorHora.trim().length() > 0)) {
            int tam = editarValorHora.length();
            if (tam >= 8) {
                if (Utilidades.isNumber(editarValorHora)) {
                    validacionesValorHora = true;
                } else {
                    FacesContext.getCurrentInstance().addMessage("form:editarValorHora", new FacesMessage("La dirección se encuentra incorrecta."));
                    validacionesValorHora = false;
                }
            } else {
                FacesContext.getCurrentInstance().addMessage("form:editarValorHora", new FacesMessage("El tamaño minimo permitido es 8 caracteres."));
                validacionesValorHora = false;
            }
        } else {
            FacesContext.getCurrentInstance().addMessage("form:editarValorHora", new FacesMessage("El valor hora es obligatorio."));
            validacionesValorHora = false;
        }
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

    public void validarTipoPersonal() {
        if (Utilidades.validarNulo(editarTipoPersonal)) {
            validacionesTipoPersonal = true;
        } else {
            validacionesTipoPersonal = false;
            FacesContext.getCurrentInstance().addMessage("form:editarTipoPersonal", new FacesMessage("El tipo personal es obligatorio."));
        }
        modificacionRegistro = true;
    }

    private boolean validarResultadosValidacion() {
        boolean retorno = true;
        if (validacionesTipoPersonal == false) {
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
    public void registrarModificacionPersonalInterno() {
        if (modificacionRegistro == true) {
            if (validarResultadosValidacion() == true) {
                almacenarModificacionPersonalInternoEnSistema();
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

    public void almacenarModificacionPersonalInternoEnSistema() {
        try {
            personalInternoDetalles.getPersona().setNumerodocumento(editarIdentificacion);
            personalInternoDetalles.getPersona().setNombrepersona(editarNombre);
            personalInternoDetalles.getPersona().setValorhoratrabajo(Integer.valueOf(editarValorHora));
            personalInternoDetalles.getPersona().setApellidopersona(editarApellido);
            personalInternoDetalles.getPersona().setCorreoelectronico(editarCorreo);
            if (Utilidades.validarNulo(editarTelFijo)) {
                personalInternoDetalles.getPersona().setNumerofijo(editarTelFijo);
            } else {
                personalInternoDetalles.getPersona().setNumerofijo("");
            }
            if (Utilidades.validarNulo(editarTelCelular)) {
                personalInternoDetalles.getPersona().setNumerocelular(editarTelCelular);
            } else {
                personalInternoDetalles.getPersona().setNumerocelular("");
            }
            administrarPersonalInternoBO.editarPersonalInterno(personalInternoDetalles);
        } catch (Exception e) {
            System.out.println("Error ControllerRegistrarPersonalInterno almacenarModificacionPersonalInternoEnSistema : " + e.toString());
        }
    }

    public String cancelarRegistroPersonalInterno() {
        editarValorHora = null;
        editarIdentificacion = null;
        editarNombre = null;
        editarTelCelular = null;
        editarApellido = null;
        editarCorreo = null;
        editarTipoPersonal = null;
        editarTelFijo = null;
        //
        validacionesValorHora = false;
        validacionesIdentificacion = false;
        validacionesNombre = false;
        validacionesTelCelular = true;
        validacionesCorreo = false;
        validacionesApellido = false;
        validacionesTipoPersonal = false;
        validacionesTelFijo = true;
        listaTipoPersonal = null;
        personalInternoDetalles = null;
        idPersonalInterno = null;
        modificacionRegistro = false;
        mensajeFormulario = "N/A";
        colorMensaje = "black";
        return "administrarpersonalinterno";
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

    public List<TipoPersonal> getListaTipoPersonal() {
        return listaTipoPersonal;
    }

    public void setListaTipoPersonal(List<TipoPersonal> listaTipoPersonal) {
        this.listaTipoPersonal = listaTipoPersonal;
    }

    public TipoPersonal getEditarTipoPersonal() {
        return editarTipoPersonal;
    }

    public void setEditarTipoPersonal(TipoPersonal editarTipoPersonal) {
        this.editarTipoPersonal = editarTipoPersonal;
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

    public boolean isModificacionRegistro() {
        return modificacionRegistro;
    }

    public void setModificacionRegistro(boolean modificacionRegistro) {
        this.modificacionRegistro = modificacionRegistro;
    }

}
