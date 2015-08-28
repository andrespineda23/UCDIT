/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.controller.cliente;

import com.java.ucdit.bo.interfaces.cliente.AdministrarClienteBOInterface;
import com.java.ucdit.entidades.Cliente;
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
public class ControllerRegistrarCliente implements Serializable {

    @EJB
    AdministrarClienteBOInterface administrarClienteBO;
    //
    private String nuevoNombre, nuevoCiudad, nuevoDireccion;
    private String nuevoTelFijo, nuevoTelCelular, nuevoCorreo;
    //
    private boolean validacionesNombre, validacionesCiudad, validacionesDireccion;
    private boolean validacionesTelFijo, validacionesTelCelular, validacionesCorreo;
    private String mensajeFormulario;
    private boolean activarCasillas;
    private String colorMensaje;
    private boolean activarLimpiar;
    private boolean activarAceptar;

    public ControllerRegistrarCliente() {
    }

    @PostConstruct
    public void init() {
        nuevoDireccion = null;
        nuevoNombre = null;
        nuevoCorreo = null;
        nuevoTelCelular = null;
        nuevoCiudad = null;
        nuevoTelFijo = null;
        //
        validacionesDireccion = false;
        validacionesNombre = false;
        validacionesCorreo = false;
        validacionesTelCelular = true;
        validacionesCiudad = false;
        validacionesTelFijo = true;
        activarLimpiar = true;
        colorMensaje = "black";
        activarCasillas = false;
        activarAceptar = false;
        mensajeFormulario = "N/A";
    }

    public void validarNombreCliente() {
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

    public void validarCorreoCliente() {
        if (Utilidades.validarNulo(nuevoCorreo) && (!nuevoCorreo.isEmpty()) && (nuevoCorreo.trim().length() > 0)) {
            int tam = nuevoCorreo.length();
            if (tam >= 15) {
                if (Utilidades.validarCorreoElectronico(nuevoCorreo)) {
                    validacionesCorreo = true;
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

    public void validarCiudadCliente() {
        if (Utilidades.validarNulo(nuevoCiudad) && (!nuevoCiudad.isEmpty()) && (nuevoCiudad.trim().length() > 0)) {
            int tam = nuevoCiudad.length();
            if (tam >= 4) {
                if ((Utilidades.validarCaracterString(nuevoCiudad)) == false) {
                    validacionesCiudad = false;
                    FacesContext.getCurrentInstance().addMessage("form:nuevoCiudad", new FacesMessage("La ciudad ingresada es incorrecta."));
                } else {
                    validacionesCiudad = true;
                }
            } else {
                validacionesCiudad = false;
                FacesContext.getCurrentInstance().addMessage("form:nuevoCiudad", new FacesMessage("La tamaño minimo permitido es 4 caracteres."));
            }
        } else {
            validacionesCiudad = false;
            FacesContext.getCurrentInstance().addMessage("form:nuevoCiudad", new FacesMessage("La ciudad es obligatoria."));
        }
    }

    public void validarDireccionCliente() {
        if ((Utilidades.validarNulo(nuevoDireccion)) && (!nuevoDireccion.isEmpty()) && (nuevoDireccion.trim().length() > 0)) {
            int tam = nuevoDireccion.length();
            if (tam >= 8) {
                if (Utilidades.validarDirecciones(nuevoDireccion)) {
                    validacionesDireccion = true;
                } else {
                    FacesContext.getCurrentInstance().addMessage("form:nuevoDireccion", new FacesMessage("La dirección se encuentra incorrecta."));
                    validacionesDireccion = false;
                }
            } else {
                FacesContext.getCurrentInstance().addMessage("form:nuevoDireccion", new FacesMessage("El tamaño minimo permitido es 8 caracteres."));
                validacionesDireccion = false;
            }
        } else {
            FacesContext.getCurrentInstance().addMessage("form:nuevoDireccion", new FacesMessage("La dirección es obligatoria."));
            validacionesDireccion = false;
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

        if (validacionesDireccion == false) {
            retorno = false;
        }
        if (validacionesCorreo == false) {
            retorno = false;
        }
        if (validacionesNombre == false) {
            retorno = false;
        }
        if (validacionesCiudad == false) {
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
    public void registrarNuevoCliente() {
        if (validarResultadosValidacion() == true) {
            almacenarNuevoClienteEnSistema();
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

    public void almacenarNuevoClienteEnSistema() {
        try {
            Cliente nuevaCliente = new Cliente();
            nuevaCliente.setNombrecliente(nuevoNombre);
            nuevaCliente.setDireccion(nuevoDireccion);
            nuevaCliente.setCiudad(nuevoCiudad);
            nuevaCliente.setCorreoelectronico(nuevoCorreo);
            if (Utilidades.validarNulo(nuevoTelFijo)) {
                nuevaCliente.setTelefonofijo(nuevoTelFijo);
            } else {
                nuevaCliente.setTelefonofijo("");
            }
            if (Utilidades.validarNulo(nuevoTelCelular)) {
                nuevaCliente.setTelefonomovil(nuevoTelCelular);
            } else {
                nuevaCliente.setTelefonomovil("");
            }
            administrarClienteBO.crearCliente(nuevaCliente);
        } catch (Exception e) {
            System.out.println("Error ControllerRegistrarCliente almacenarNuevoClienteEnSistema : " + e.toString());
        }
    }

    public void limpiarFormulario() {
        nuevoDireccion = null;
        nuevoNombre = null;
        nuevoTelCelular = null;
        nuevoCiudad = null;
        nuevoCorreo = null;
        nuevoTelFijo = null;
        //
        validacionesDireccion = false;
        validacionesNombre = false;
        validacionesTelCelular = true;
        validacionesCiudad = false;
        validacionesCorreo = false;
        validacionesTelFijo = true;
        mensajeFormulario = "";
    }

    public String cancelarRegistroCliente() {
        nuevoDireccion = null;
        nuevoNombre = null;
        nuevoTelCelular = null;
        nuevoCiudad = null;
        nuevoCorreo = null;
        nuevoTelFijo = null;
        //
        validacionesDireccion = false;
        validacionesNombre = false;
        validacionesTelCelular = true;
        validacionesCorreo = false;
        validacionesCiudad = false;
        validacionesTelFijo = true;
        activarAceptar = false;
        mensajeFormulario = "N/A";
        activarLimpiar = true;
        colorMensaje = "black";
        activarCasillas = false;
        return "administrarcliente";
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

    public String getNuevoCiudad() {
        return nuevoCiudad;
    }

    public void setNuevoCiudad(String nuevoCiudad) {
        this.nuevoCiudad = nuevoCiudad;
    }

    public String getNuevoDireccion() {
        return nuevoDireccion;
    }

    public void setNuevoDireccion(String nuevoDireccion) {
        this.nuevoDireccion = nuevoDireccion;
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
