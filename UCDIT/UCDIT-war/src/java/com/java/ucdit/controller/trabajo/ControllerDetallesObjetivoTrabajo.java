/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.controller.trabajo;

import com.java.ucdit.bo.interfaces.trabajo.AdministrarObjetivoTrabajoBOInterface;
import com.java.ucdit.entidades.ObjetivoPorPersonalProyecto;
import com.java.ucdit.entidades.PersonalInterno;
import com.java.ucdit.entidades.PersonalPorProyecto;
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
public class ControllerDetallesObjetivoTrabajo implements Serializable {

    @EJB
    AdministrarObjetivoTrabajoBOInterface administrarObjetivoTrabajoBO;
    //
    private String editarNombre;
    private Date editarFechaInicio;
    //
    private boolean validacionesNombre;
    private boolean validacionesFechaInicio;
    private String mensajeFormulario;
    private String colorMensaje;

    private Date editarFechaDeseada;
    private boolean validacionesFechaDeseada;
    private List<PersonalInterno> listaPersonalInterno;
    private PersonalInterno personalInterno;
    private List<PersonalPorProyecto> listaPersonalPorProyecto;
    private PersonalPorProyecto personalPorProyecto;
    private boolean activarPersonalPorProyecto;
    private boolean validacionesPersonalPorProyecto;
    private BigInteger idObjetivoPorPersonalProyecto;
    private ObjetivoPorPersonalProyecto objetivoPorPersonalProyectoDetalle;
    private boolean estadoObjetivo;
    private boolean modificacionesRegistro;

    public ControllerDetallesObjetivoTrabajo() {
    }

    @PostConstruct
    public void init() {
        colorMensaje = "black";
        mensajeFormulario = "N/A";
    }

    public void recibirIdObjetivoPorPersonalProyecto(BigInteger idRegistro) {
        this.idObjetivoPorPersonalProyecto = idRegistro;
        objetivoPorPersonalProyectoDetalle = administrarObjetivoTrabajoBO.obtenerObjetivoPorPersonalProyectoPorId(idObjetivoPorPersonalProyecto);
        cargarInformacionRegistro();
    }

    private void cargarInformacionRegistro() {
        if (Utilidades.validarNulo(objetivoPorPersonalProyectoDetalle)) {
            activarPersonalPorProyecto = false;
            personalInterno = objetivoPorPersonalProyectoDetalle.getPersonalporproyecto().getPersonalinterno();
            editarFechaDeseada = objetivoPorPersonalProyectoDetalle.getFechadeseada();
            editarNombre = objetivoPorPersonalProyectoDetalle.getObjetivotrabajo().getNombreobjetivo();
            editarFechaInicio = objetivoPorPersonalProyectoDetalle.getObjetivotrabajo().getFechacreacion();
            personalPorProyecto = objetivoPorPersonalProyectoDetalle.getPersonalporproyecto();
            estadoObjetivo = objetivoPorPersonalProyectoDetalle.getObjetivotrabajo().getEstadoobjetivo();
            //
            modificacionesRegistro = false;
            validacionesFechaDeseada = true;
            validacionesPersonalPorProyecto = true;
            validacionesNombre = true;
            validacionesFechaInicio = true;
            listaPersonalInterno = administrarObjetivoTrabajoBO.obtenerPersonalInternoRegistrado();
            listaPersonalPorProyecto = administrarObjetivoTrabajoBO.obtenerPersonalPorProyectoPorIdPersonal(personalInterno.getIdpersonalinterno());
        }
    }

    public void actualizarPersonalInterno() {
        if (Utilidades.validarNulo(personalInterno)) {
            personalPorProyecto = null;
            activarPersonalPorProyecto = false;
            listaPersonalPorProyecto = administrarObjetivoTrabajoBO.obtenerPersonalPorProyectoPorIdPersonal(personalInterno.getIdpersonalinterno());
            validacionesPersonalPorProyecto = false;
        } else {
            personalPorProyecto = null;
            activarPersonalPorProyecto = false;
            listaPersonalPorProyecto = null;
            validacionesPersonalPorProyecto = false;
        }
        modificacionesRegistro = true;
    }

    public void actualizarProyecto() {
        if (Utilidades.validarNulo(personalPorProyecto)) {
            validacionesPersonalPorProyecto = true;
        } else {
            validacionesPersonalPorProyecto = false;
        }
        modificacionesRegistro = true;
    }
    
    public void actualizarEstado(){
        modificacionesRegistro = true;
    }

    public void validarNombreObjetivoTrabajo() {
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
        modificacionesRegistro = true;
    }

    public void validarFechaDeseadaObjetivoTrabajo() {
        if (Utilidades.validarNulo(editarFechaDeseada)) {
            editarFechaDeseada = new Date();
            if (Utilidades.fechaIngresadaCorrecta(editarFechaDeseada)) {
                if (editarFechaDeseada.before(editarFechaInicio)) {
                    validacionesFechaDeseada = true;
                } else {
                    validacionesFechaDeseada = true;
                    FacesContext.getCurrentInstance().addMessage("form:editarFechaDeseada", new FacesMessage("La fecha deseada es menor a la fecha de inicio."));
                }
            } else {
                validacionesFechaDeseada = true;
                FacesContext.getCurrentInstance().addMessage("form:editarFechaDeseada", new FacesMessage("La fecha deseada es incorrecta."));
            }
        } else {
            validacionesFechaDeseada = true;
            FacesContext.getCurrentInstance().addMessage("form:editarFechaDeseada", new FacesMessage("La fecha deseada es obligatoria."));
        }
        modificacionesRegistro = true;
    }

    private boolean validarResultadosValidacion() {
        boolean retorno = true;
        if (validacionesFechaInicio == false) {
            retorno = false;
        }
        if (validacionesNombre == false) {
            retorno = false;
        }
        if (validacionesFechaDeseada == false) {
            retorno = false;
        }
        if (validacionesPersonalPorProyecto == false) {
            retorno = false;
        }
        return retorno;
    }

    /**
     * Metodo encargado de realizar el registro y validaciones de la información
     * del editar docente
     */
    public void registrarModificacionObjetivoTrabajo() {
        if (modificacionesRegistro == true) {
            if (validarResultadosValidacion() == true) {
                almacenarModificacionObjetivoTrabajoEnSistema();
                recibirIdObjetivoPorPersonalProyecto(idObjetivoPorPersonalProyecto);
                colorMensaje = "green";
                mensajeFormulario = "El formulario ha sido ingresado con exito.";
            } else {
                colorMensaje = "red";
                mensajeFormulario = "Existen errores en el formulario, por favor corregir para continuar.";
            }
        } else {
            colorMensaje = "black";
            mensajeFormulario = "No existen modificaciones para ser almacenadas en el sistema.";
        }
    }

    public void almacenarModificacionObjetivoTrabajoEnSistema() {
        try {
            objetivoPorPersonalProyectoDetalle.getObjetivotrabajo().setNombreobjetivo(editarNombre);
            objetivoPorPersonalProyectoDetalle.getObjetivotrabajo().setEstadoobjetivo(estadoObjetivo);
            objetivoPorPersonalProyectoDetalle.setFechadeseada(editarFechaDeseada);
            objetivoPorPersonalProyectoDetalle.setPersonalporproyecto(personalPorProyecto);
            administrarObjetivoTrabajoBO.editarObjetivoPorPersonalProyecto(objetivoPorPersonalProyectoDetalle);
        } catch (Exception e) {
            System.out.println("Error ControllerRegistrarObjetivoTrabajo almacenarModificacionObjetivoTrabajoEnSistema : " + e.toString());
        }
    }

    public String cancelarRegistroObjetivoTrabajo() {
        editarNombre = null;
        personalInterno = null;
        editarFechaDeseada = null;
        validacionesFechaDeseada = false;
        editarFechaInicio = new Date();
        //
        validacionesNombre = false;
        validacionesFechaInicio = true;
        mensajeFormulario = "N/A";
        colorMensaje = "black";
        //
        listaPersonalInterno = null;
        listaPersonalPorProyecto = null;
        personalInterno = null;
        personalPorProyecto = null;
        activarPersonalPorProyecto = true;
        validacionesPersonalPorProyecto = false;
        idObjetivoPorPersonalProyecto = null;
        objetivoPorPersonalProyectoDetalle = null;
        return "administrarobjetivotrabajo";
    }

    //GET-SET
    public String getEditarNombre() {
        return editarNombre;
    }

    public void setEditarNombre(String editarNombre) {
        this.editarNombre = editarNombre;
    }

    public Date getEditarFechaInicio() {
        return editarFechaInicio;
    }

    public void setEditarFechaInicio(Date editarFechaInicio) {
        this.editarFechaInicio = editarFechaInicio;
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

    public Date getEditarFechaDeseada() {
        return editarFechaDeseada;
    }

    public void setEditarFechaDeseada(Date editarFechaDeseada) {
        this.editarFechaDeseada = editarFechaDeseada;
    }

    public List<PersonalInterno> getListaPersonalInterno() {
        return listaPersonalInterno;
    }

    public void setListaPersonalInterno(List<PersonalInterno> listaPersonalInterno) {
        this.listaPersonalInterno = listaPersonalInterno;
    }

    public PersonalInterno getPersonalInterno() {
        return personalInterno;
    }

    public void setPersonalInterno(PersonalInterno personalInterno) {
        this.personalInterno = personalInterno;
    }

    public List<PersonalPorProyecto> getListaPersonalPorProyecto() {
        return listaPersonalPorProyecto;
    }

    public void setListaPersonalPorProyecto(List<PersonalPorProyecto> listaPersonalPorProyecto) {
        this.listaPersonalPorProyecto = listaPersonalPorProyecto;
    }

    public PersonalPorProyecto getPersonalPorProyecto() {
        return personalPorProyecto;
    }

    public void setPersonalPorProyecto(PersonalPorProyecto personalPorProyecto) {
        this.personalPorProyecto = personalPorProyecto;
    }

    public boolean isActivarPersonalPorProyecto() {
        return activarPersonalPorProyecto;
    }

    public void setActivarPersonalPorProyecto(boolean activarPersonalPorProyecto) {
        this.activarPersonalPorProyecto = activarPersonalPorProyecto;
    }

    public BigInteger getIdObjetivoPorPersonalProyecto() {
        return idObjetivoPorPersonalProyecto;
    }

    public void setIdObjetivoPorPersonalProyecto(BigInteger idObjetivoPorPersonalProyecto) {
        this.idObjetivoPorPersonalProyecto = idObjetivoPorPersonalProyecto;
    }

    public ObjetivoPorPersonalProyecto getObjetivoPorPersonalProyectoDetalle() {
        return objetivoPorPersonalProyectoDetalle;
    }

    public void setObjetivoPorPersonalProyectoDetalle(ObjetivoPorPersonalProyecto objetivoPorPersonalProyectoDetalle) {
        this.objetivoPorPersonalProyectoDetalle = objetivoPorPersonalProyectoDetalle;
    }

    public boolean isModificacionesRegistro() {
        return modificacionesRegistro;
    }

    public void setModificacionesRegistro(boolean modificacionesRegistro) {
        this.modificacionesRegistro = modificacionesRegistro;
    }

    public boolean isEstadoObjetivo() {
        return estadoObjetivo;
    }

    public void setEstadoObjetivo(boolean estadoObjetivo) {
        this.estadoObjetivo = estadoObjetivo;
    }

}
