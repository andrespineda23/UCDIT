/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.controller.trabajo;

import com.java.ucdit.bo.interfaces.trabajo.AdministrarObjetivoTrabajoBOInterface;
import com.java.ucdit.entidades.ObjetivoTrabajo;
import com.java.ucdit.entidades.PersonalInterno;
import com.java.ucdit.entidades.PersonalPorProyecto;
import com.java.ucdit.utilidades.Utilidades;
import java.io.Serializable;
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
public class ControllerRegistrarObjetivoTrabajo implements Serializable {

    @EJB
    AdministrarObjetivoTrabajoBOInterface administrarObjetivoTrabajoBO;
    //
    private String nuevoNombre;
    private Date nuevoFechaInicio;
    //
    private boolean validacionesNombre;
    private boolean validacionesFechaInicio;
    private String mensajeFormulario;
    private boolean activarCasillas;
    private String colorMensaje;
    private boolean activarLimpiar;
    private boolean activarAceptar;
    private boolean fechaDiferida;

    private Date nuevoFechaDeseada;
    private boolean validacionesFechaDeseada;
    private List<PersonalInterno> listaPersonalInterno;
    private PersonalInterno personalInterno;
    private List<PersonalPorProyecto> listaPersonalPorProyecto;
    private PersonalPorProyecto personalPorProyecto;
    private boolean activarPersonalPorProyecto;
    private boolean validacionesPersonalPorProyecto;

    public ControllerRegistrarObjetivoTrabajo() {
    }

    @PostConstruct
    public void init() {
        activarPersonalPorProyecto = true;
        personalInterno = null;
        nuevoFechaDeseada = null;
        fechaDiferida = true;
        nuevoNombre = null;
        nuevoFechaInicio = new Date();
        //
        personalPorProyecto = null;
        validacionesFechaDeseada = false;
        validacionesPersonalPorProyecto = false;
        validacionesNombre = false;
        validacionesFechaInicio = true;
        activarLimpiar = true;
        colorMensaje = "black";
        activarCasillas = false;
        activarAceptar = false;
        mensajeFormulario = "N/A";
        listaPersonalPorProyecto = null;
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
    }
    public void actualizarProyecto() {
        if (Utilidades.validarNulo(personalPorProyecto)) {
            validacionesPersonalPorProyecto = true;
        } else {
            validacionesPersonalPorProyecto = false;
        }
    }

    public void validarNombreObjetivoTrabajo() {
        if (Utilidades.validarNulo(nuevoNombre) && (!nuevoNombre.isEmpty()) && (nuevoNombre.trim().length() > 0)) {
            int tam = nuevoNombre.length();
            if (tam >= 4) {
                if (!Utilidades.validarCaracteresAlfaNumericos(nuevoNombre)) {
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

    public void validarFechaInicioObjetivoTrabajo() {
        if (Utilidades.validarNulo(nuevoFechaInicio)) {
            if (fechaDiferida == true) {
                nuevoFechaInicio = new Date();
                if (Utilidades.fechaIngresadaCorrecta(nuevoFechaInicio)) {
                    validacionesFechaInicio = true;
                } else {
                    validacionesFechaInicio = true;
                    FacesContext.getCurrentInstance().addMessage("form:nuevoFechaInicio", new FacesMessage("La fecha de inicio es incorrecta."));
                }
            } else {
                if (Utilidades.fechaDiferidaIngresadaCorrecta(nuevoFechaInicio)) {
                    validacionesFechaInicio = true;
                } else {
                    validacionesFechaInicio = true;
                    FacesContext.getCurrentInstance().addMessage("form:nuevoFechaInicio", new FacesMessage("La fecha de inicio es incorrecta."));
                }
            }
        } else {
            validacionesFechaInicio = true;
            FacesContext.getCurrentInstance().addMessage("form:nuevoFechaInicio", new FacesMessage("La fecha de inicio es obligatoria."));
        }
    }

    public void validarFechaDeseadaObjetivoTrabajo() {
        if (Utilidades.validarNulo(nuevoFechaDeseada)) {
            nuevoFechaDeseada = new Date();
            if (Utilidades.fechaIngresadaCorrecta(nuevoFechaDeseada)) {
                if (nuevoFechaDeseada.before(nuevoFechaInicio)) {
                    validacionesFechaDeseada = true;
                } else {
                    validacionesFechaDeseada = true;
                    FacesContext.getCurrentInstance().addMessage("form:nuevoFechaDeseada", new FacesMessage("La fecha deseada es menor a la fecha de inicio."));
                }
            } else {
                validacionesFechaDeseada = true;
                FacesContext.getCurrentInstance().addMessage("form:nuevoFechaDeseada", new FacesMessage("La fecha deseada es incorrecta."));
            }
        } else {
            validacionesFechaDeseada = true;
            FacesContext.getCurrentInstance().addMessage("form:nuevoFechaDeseada", new FacesMessage("La fecha deseada es obligatoria."));
        }
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
     * del nuevo docente
     */
    public void registrarNuevoObjetivoTrabajo() {
        if (validarResultadosValidacion() == true) {
            almacenarNuevoObjetivoTrabajoEnSistema();
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

    public void almacenarNuevoObjetivoTrabajoEnSistema() {
        try {
            ObjetivoTrabajo nuevaObjetivoTrabajo = new ObjetivoTrabajo();
            nuevaObjetivoTrabajo.setEstadoobjetivo(true);
            nuevaObjetivoTrabajo.setFechacreacion(nuevoFechaInicio);
            nuevaObjetivoTrabajo.setNombreobjetivo(nuevoNombre);

            administrarObjetivoTrabajoBO.crearObjetivoTrabajo(nuevaObjetivoTrabajo, nuevoFechaDeseada, personalPorProyecto);
        } catch (Exception e) {
            System.out.println("Error ControllerRegistrarObjetivoTrabajo almacenarNuevoObjetivoTrabajoEnSistema : " + e.toString());
        }
    }

    public void limpiarFormulario() {
        nuevoNombre = null;
        nuevoFechaInicio = new Date();
        fechaDiferida = true;
        validacionesNombre = false;
        validacionesFechaInicio = true;
        mensajeFormulario = "";
        personalInterno = null;
        nuevoFechaDeseada = null;
        validacionesFechaDeseada = false;
        //
        listaPersonalInterno = null;
        listaPersonalPorProyecto = null;
        personalInterno = null;
        personalPorProyecto = null;
        activarPersonalPorProyecto = true;
        validacionesPersonalPorProyecto = false;
    }

    public String cancelarRegistroObjetivoTrabajo() {
        nuevoNombre = null;
        personalInterno = null;
        nuevoFechaDeseada = null;
        validacionesFechaDeseada = false;
        nuevoFechaInicio = new Date();
        fechaDiferida = true;
        //
        validacionesNombre = false;
        validacionesFechaInicio = true;
        activarAceptar = false;
        mensajeFormulario = "N/A";
        activarLimpiar = true;
        colorMensaje = "black";
        activarCasillas = false;
        //
        listaPersonalInterno = null;
        listaPersonalPorProyecto = null;
        personalInterno = null;
        personalPorProyecto = null;
        activarPersonalPorProyecto = true;
        validacionesPersonalPorProyecto = false;
        return "administrarobjetivotrabajo";
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

    public Date getNuevoFechaInicio() {
        return nuevoFechaInicio;
    }

    public void setNuevoFechaInicio(Date nuevoFechaInicio) {
        this.nuevoFechaInicio = nuevoFechaInicio;
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

    public boolean isFechaDiferida() {
        return fechaDiferida;
    }

    public void setFechaDiferida(boolean fechaDiferida) {
        this.fechaDiferida = fechaDiferida;
    }

    public Date getNuevoFechaDeseada() {
        return nuevoFechaDeseada;
    }

    public void setNuevoFechaDeseada(Date nuevoFechaDeseada) {
        this.nuevoFechaDeseada = nuevoFechaDeseada;
    }

    public List<PersonalInterno> getListaPersonalInterno() {
        if (null == listaPersonalInterno) {
            listaPersonalInterno = administrarObjetivoTrabajoBO.obtenerPersonalInternoRegistrado();
        }
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

}
