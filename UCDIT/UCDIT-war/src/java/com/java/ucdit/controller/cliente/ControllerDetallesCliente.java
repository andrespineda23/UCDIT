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
public class ControllerDetallesCliente implements Serializable {

    @EJB
    AdministrarClienteBOInterface administrarClienteBO;
    //
    private Cliente clienteDetalle;
    private BigInteger idCliente;
    //
    private String editarNombre, editarCiudad, editarDireccion;
    private String editarTelFijo, editarTelCelular, editarCorreo;
    //
    private boolean validacionesNombre, validacionesCiudad, validacionesDireccion;
    private boolean validacionesTelFijo, validacionesTelCelular, validacionesCorreo;
    private String mensajeFormulario;
    private String colorMensaje;
    private boolean modificacionRegistro;

    public ControllerDetallesCliente() {
    }

    @PostConstruct
    public void init() {
        colorMensaje = "black";
        mensajeFormulario = "N/A";
    }

    public void recibirIdClienteDetalle(BigInteger idCliente) {
        this.idCliente = idCliente;
        clienteDetalle = administrarClienteBO.obtenerClientePorId(this.idCliente);
        modificacionRegistro = false;
        cargarInformacionRegistro();
    }

    private void cargarInformacionRegistro() {
        if (Utilidades.validarNulo(clienteDetalle)) {
            editarDireccion = clienteDetalle.getDireccion();
            editarNombre = clienteDetalle.getNombrecliente();
            editarCorreo = clienteDetalle.getCorreoelectronico();
            editarTelCelular = clienteDetalle.getTelefonomovil();
            editarCiudad = clienteDetalle.getCiudad();
            editarTelFijo = clienteDetalle.getTelefonofijo();
            //
            validacionesDireccion = true;
            validacionesNombre = true;
            validacionesCorreo = true;
            validacionesTelCelular = true;
            validacionesCiudad = true;
            validacionesTelFijo = true;
        }
    }

    private void restaurarRegistro() {
        colorMensaje = "black";
        mensajeFormulario = "N/A";
        modificacionRegistro = false;
        clienteDetalle = null;
        clienteDetalle = administrarClienteBO.obtenerClientePorId(this.idCliente);
        cargarInformacionRegistro();
    }

    public void validarNombreCliente() {
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

    public void validarCorreoCliente() {
        if (Utilidades.validarNulo(editarCorreo) && (!editarCorreo.isEmpty()) && (editarCorreo.trim().length() > 0)) {
            int tam = editarCorreo.length();
            if (tam >= 15) {
                if (Utilidades.validarCorreoElectronico(editarCorreo)) {
                    validacionesCorreo = true;
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

    public void validarCiudadCliente() {
        if (Utilidades.validarNulo(editarCiudad) && (!editarCiudad.isEmpty()) && (editarCiudad.trim().length() > 0)) {
            int tam = editarCiudad.length();
            if (tam >= 4) {
                if ((Utilidades.validarCaracterString(editarCiudad)) == false) {
                    validacionesCiudad = false;
                    FacesContext.getCurrentInstance().addMessage("form:editarCiudad", new FacesMessage("La ciudad ingresada es incorrecta."));
                } else {
                    validacionesCiudad = true;
                }
            } else {
                validacionesCiudad = false;
                FacesContext.getCurrentInstance().addMessage("form:editarCiudad", new FacesMessage("La tamaño minimo permitido es 4 caracteres."));
            }
        } else {
            validacionesCiudad = false;
            FacesContext.getCurrentInstance().addMessage("form:editarCiudad", new FacesMessage("La ciudad es obligatoria."));
        }
        modificacionRegistro = true;
    }

    public void validarDireccionCliente() {
        if ((Utilidades.validarNulo(editarDireccion)) && (!editarDireccion.isEmpty()) && (editarDireccion.trim().length() > 0)) {
            int tam = editarDireccion.length();
            if (tam >= 8) {
                if (Utilidades.validarDirecciones(editarDireccion)) {
                    validacionesDireccion = true;
                } else {
                    FacesContext.getCurrentInstance().addMessage("form:editarDireccion", new FacesMessage("La dirección se encuentra incorrecta."));
                    validacionesDireccion = false;
                }
            } else {
                FacesContext.getCurrentInstance().addMessage("form:editarDireccion", new FacesMessage("El tamaño minimo permitido es 8 caracteres."));
                validacionesDireccion = false;
            }
        } else {
            FacesContext.getCurrentInstance().addMessage("form:editarDireccion", new FacesMessage("La dirección es obligatoria."));
            validacionesDireccion = false;
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
     * del editar docente
     */
    public void registrarModificacionCliente() {
        if (modificacionRegistro == true) {
            if (validarResultadosValidacion() == true) {
                almacenarModificacionClienteEnSistema();
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

    public void almacenarModificacionClienteEnSistema() {
        try {
            clienteDetalle.setNombrecliente(editarNombre);
            clienteDetalle.setDireccion(editarDireccion);
            clienteDetalle.setCiudad(editarCiudad);
            clienteDetalle.setCorreoelectronico(editarCorreo);
            if (Utilidades.validarNulo(editarTelFijo)) {
                clienteDetalle.setTelefonofijo(editarTelFijo);
            } else {
                clienteDetalle.setTelefonofijo("");
            }
            if (Utilidades.validarNulo(editarTelCelular)) {
                clienteDetalle.setTelefonomovil(editarTelCelular);
            } else {
                clienteDetalle.setTelefonomovil("");
            }
            administrarClienteBO.editarCliente(clienteDetalle);
        } catch (Exception e) {
            System.out.println("Error ControllerDetallesCliente almacenarModificacionClienteEnSistema : " + e.toString());
        }
    }

    public String cancelarRegistroCliente() {
        editarDireccion = null;
        editarNombre = null;
        editarTelCelular = null;
        editarCiudad = null;
        editarCorreo = null;
        editarTelFijo = null;
        //
        validacionesDireccion = false;
        validacionesNombre = false;
        validacionesTelCelular = true;
        validacionesCorreo = false;
        validacionesCiudad = false;
        validacionesTelFijo = true;
        mensajeFormulario = "N/A";
        colorMensaje = "black";

        clienteDetalle = null;
        idCliente = null;
        modificacionRegistro = false;
        return "administrarcliente";
    }

    //GET-SET
    public String getEditarNombre() {
        return editarNombre;
    }

    public void setEditarNombre(String editarNombre) {
        this.editarNombre = editarNombre;
    }

    public String getEditarCiudad() {
        return editarCiudad;
    }

    public void setEditarCiudad(String editarCiudad) {
        this.editarCiudad = editarCiudad;
    }

    public String getEditarDireccion() {
        return editarDireccion;
    }

    public void setEditarDireccion(String editarDireccion) {
        this.editarDireccion = editarDireccion;
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

}
