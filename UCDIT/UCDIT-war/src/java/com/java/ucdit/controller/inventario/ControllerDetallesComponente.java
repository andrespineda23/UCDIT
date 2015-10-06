/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.controller.inventario;

import com.java.ucdit.bo.interfaces.inventario.AdministrarComponenteEquipoBOInterface;
import com.java.ucdit.bo.interfaces.inventario.AdministrarEquipoTecnologicoBOInterface;
import com.java.ucdit.entidades.Componente;
import com.java.ucdit.entidades.EquipoTecnologico;
import com.java.ucdit.entidades.IngresoEquipo;
import com.java.ucdit.entidades.Proveedor;
import com.java.ucdit.entidades.TipoEquipo;
import com.java.ucdit.utilidades.Utilidades;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
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
public class ControllerDetallesComponente implements Serializable {

    @EJB
    AdministrarComponenteEquipoBOInterface administrarComponentesBO;

    //
    private Componente componenteDetalle;
    private BigInteger idComponente;
    //
    private String editarNombre, editarCodigo, editarMarca;
    private String editarModelo;
    private String editarSerie;
    //
    private boolean validacionesNombre, validacionesCodigo, validacionesMarca, validacionesSerie;
    private boolean validacionesModelo;
    private String mensajeFormulario;
    private String colorMensaje;
    private boolean modificacionRegistro;
    private boolean editarEstado;

    public ControllerDetallesComponente() {
    }

    @PostConstruct
    public void init() {
        colorMensaje = "black";
        mensajeFormulario = "N/A";
    }

    public void recibirIdComponenteDetalle(BigInteger idEquipo) {
        this.idComponente = idEquipo;
        componenteDetalle = administrarComponentesBO.obtenerComponentePorId(this.idComponente);
        modificacionRegistro = false;
        cargarInformacionRegistro();
    }

    private void cargarInformacionRegistro() {
        if (Utilidades.validarNulo(componenteDetalle)) {
            editarCodigo = componenteDetalle.getCodigocomponente();
            editarNombre = componenteDetalle.getNombrecomponente();
            editarMarca = componenteDetalle.getMarcacomponente();
            editarModelo = componenteDetalle.getModelocomponente();
            editarEstado = componenteDetalle.getEstadocomponente();
            editarSerie = componenteDetalle.getSeriecomponente();
            //
            validacionesMarca = true;
            validacionesCodigo = true;
            validacionesSerie = true;
            validacionesNombre = true;
            validacionesModelo = true;
        }
    }

    private void restaurarRegistro() {
        colorMensaje = "black";
        mensajeFormulario = "N/A";
        modificacionRegistro = false;
        componenteDetalle = null;
        recibirIdComponenteDetalle(this.idComponente);
    }

    public void validarNombreComponente() {
        if (Utilidades.validarNulo(editarNombre) && (!editarNombre.isEmpty()) && (editarNombre.trim().length() > 0)) {
            int tam = editarNombre.length();
            if (tam >= 4) {
                if (!Utilidades.validarCaracteresAlfaNumericos(editarNombre)) {
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

    public void validarCodigoComponente() {
        if (Utilidades.validarNulo(editarCodigo) && (!editarCodigo.isEmpty()) && (editarCodigo.trim().length() > 0)) {
            int tam = editarCodigo.length();
            if (tam >= 4) {
                if (Utilidades.validarCaracteresAlfaNumericos(editarCodigo)) {
                    Componente registro = administrarComponentesBO.validarCodigoRepetidoComponente(editarCodigo, this.idComponente);
                    if (registro == null) {
                        validacionesCodigo = true;
                    } else {
                        if (!componenteDetalle.getIdcomponente().equals(registro.getIdcomponente())) {
                            validacionesCodigo = false;
                            FacesContext.getCurrentInstance().addMessage("form:editarCodigo", new FacesMessage("El codigo ingresado ya esta registrado."));
                        } else {
                            validacionesCodigo = true;
                        }
                    }
                } else {
                    validacionesCodigo = false;
                    FacesContext.getCurrentInstance().addMessage("form:editarCodigo", new FacesMessage("El codigo ingresado es incorrecto."));
                }
            } else {
                validacionesCodigo = false;
                FacesContext.getCurrentInstance().addMessage("form:editarCodigo", new FacesMessage("La tamaño minimo permitido es 4 caracteres."));
            }
        } else {
            validacionesCodigo = false;
            FacesContext.getCurrentInstance().addMessage("form:editarCodigo", new FacesMessage("El codigo es obligatorio."));
        }
        modificacionRegistro = true;
    }

    public void validarMarcaComponente() {
        if (Utilidades.validarNulo(editarMarca) && (!editarMarca.isEmpty()) && (editarMarca.trim().length() > 0)) {
            int tam = editarMarca.length();
            if (tam >= 2) {
                if (Utilidades.validarCaracteresAlfaNumericos(editarMarca)) {
                    validacionesMarca = true;
                } else {
                    validacionesMarca = false;
                    FacesContext.getCurrentInstance().addMessage("form:editarMarca", new FacesMessage("La marca ingresada es incorrecta."));
                }
            } else {
                validacionesMarca = false;
                FacesContext.getCurrentInstance().addMessage("form:editarMarca", new FacesMessage("La tamaño minimo permitido es 4 caracteres."));
            }
        }
        modificacionRegistro = true;
    }

    public void validarSerieComponente() {
        if (Utilidades.validarNulo(editarSerie) && (!editarSerie.isEmpty()) && (editarSerie.trim().length() > 0)) {
            int tam = editarSerie.length();
            if (tam >= 2) {
                if (Utilidades.validarCaracteresAlfaNumericos(editarSerie)) {
                    validacionesSerie = true;
                } else {
                    validacionesSerie = false;
                    FacesContext.getCurrentInstance().addMessage("form:editarSerie", new FacesMessage("La serie ingresada es incorrecta"));
                }
            } else {
                validacionesSerie = false;
                FacesContext.getCurrentInstance().addMessage("form:editarSerie", new FacesMessage("La tamaño minimo permitido es 2 caracteres."));
            }
        }
        modificacionRegistro = true;
    }

    public void validarModeloComponente() {
        if (Utilidades.validarNulo(editarModelo) && (!editarModelo.isEmpty()) && (editarModelo.trim().length() > 0)) {
            int tam = editarModelo.length();
            if (tam >= 2) {
                if (Utilidades.validarCaracteresAlfaNumericos(editarModelo)) {
                    validacionesModelo = true;
                } else {
                    validacionesModelo = false;
                    FacesContext.getCurrentInstance().addMessage("form:editarModelo", new FacesMessage("El modelo ingresado es incorrecto"));
                }
            } else {
                validacionesModelo = false;
                FacesContext.getCurrentInstance().addMessage("form:editarModelo", new FacesMessage("La tamaño minimo permitido es 2 caracteres."));
            }
        }
        modificacionRegistro = true;
    }

    public void validarEstado() {
        modificacionRegistro = true;
    }

    private boolean validarResultadosValidacion() {
        boolean retorno = true;
        if (validacionesCodigo == false) {
            retorno = false;
        }
        if (validacionesNombre == false) {
            retorno = false;
        }
        if (validacionesMarca == false) {
            retorno = false;
        }
        if (validacionesModelo == false) {
            retorno = false;
        }
        if (validacionesSerie == false) {
            retorno = false;
        }
        return retorno;
    }

    /**
     * Metodo encargado de realizar el registro y validaciones de la información
     * del editar docente
     */
    public void registrarModificacionComponente() {
        if (modificacionRegistro == true) {
            if (validarResultadosValidacion() == true) {
                almacenarModificacionComponenteEnSistema();
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

    private void almacenarModificacionComponenteEnSistema() {
        try {
            componenteDetalle.setEstadocomponente(editarEstado);
            componenteDetalle.setCodigocomponente(editarCodigo);
            componenteDetalle.setNombrecomponente(editarNombre);
            componenteDetalle.setSeriecomponente(editarSerie);
            componenteDetalle.setMarcacomponente(editarMarca);
            componenteDetalle.setModelocomponente(editarModelo);
            administrarComponentesBO.editarComponenteEquipo(componenteDetalle);
        } catch (Exception e) {
            System.out.println("Error ControllerRegistrarEquipoTecnologico almacenarModificacionEquipoTecnologicoEnSistema : " + e.toString());
        }
    }

    public String cancelarRegistroComponente() {
        editarCodigo = null;
        editarNombre = null;
        editarMarca = null;
        editarModelo = "0";
        //
        validacionesMarca = true;
        validacionesCodigo = true;
        validacionesNombre = true;
        validacionesModelo = true;
        mensajeFormulario = "N/A";
        colorMensaje = "black";
        idComponente = null;
        componenteDetalle = null;
        return "administrarcomponente";
    }

    //GET-SET
    public Componente getComponenteDetalle() {
        return componenteDetalle;
    }

    public void setComponenteDetalle(Componente componenteDetalle) {
        this.componenteDetalle = componenteDetalle;
    }

    public BigInteger getIdComponente() {
        return idComponente;
    }

    public void setIdComponente(BigInteger idComponente) {
        this.idComponente = idComponente;
    }

    public String getEditarNombre() {
        return editarNombre;
    }

    public void setEditarNombre(String editarNombre) {
        this.editarNombre = editarNombre;
    }

    public String getEditarCodigo() {
        return editarCodigo;
    }

    public void setEditarCodigo(String editarCodigo) {
        this.editarCodigo = editarCodigo;
    }

    public String getEditarMarca() {
        return editarMarca;
    }

    public void setEditarMarca(String editarMarca) {
        this.editarMarca = editarMarca;
    }

    public String getEditarModelo() {
        return editarModelo;
    }

    public void setEditarModelo(String editarModelo) {
        this.editarModelo = editarModelo;
    }

    public String getEditarSerie() {
        return editarSerie;
    }

    public void setEditarSerie(String editarSerie) {
        this.editarSerie = editarSerie;
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
