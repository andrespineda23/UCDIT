/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.controller.proyecto;

import com.java.ucdit.bo.interfaces.proyecto.AdministrarEquipoPorProyectoBOInterface;
import com.java.ucdit.entidades.EquipoPorProyecto;
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
public class ControllerDetallesEquipoPorProyecto implements Serializable {

    @EJB
    AdministrarEquipoPorProyectoBOInterface administrarEquipoPorProyectoBO;
    //
    private BigInteger idEquipoPorProyecto;
    private EquipoPorProyecto equipoPorProyectoDetalle;
    //
    private String editarMinutos;
    //
    private boolean validacionesMinutos;
    private String mensajeFormulario;
    private String colorMensaje;
    private boolean prototipo;
    private boolean modificacionRegistro;

    public ControllerDetallesEquipoPorProyecto() {
    }

    @PostConstruct
    public void init() {
        colorMensaje = "black";
        mensajeFormulario = "N/A";
    }

    public void recibirIdEquipoPorProyecto(BigInteger idEquipoPorProyecto) {
        this.idEquipoPorProyecto = idEquipoPorProyecto;
        equipoPorProyectoDetalle = administrarEquipoPorProyectoBO.obtenerEquipoPorProyectoPorId(idEquipoPorProyecto);
        cargarInformacionRegistro();
        modificacionRegistro = false;
    }

    private void cargarInformacionRegistro() {
        if (Utilidades.validarNulo(equipoPorProyectoDetalle)) {
            prototipo = equipoPorProyectoDetalle.getUsadoparaprototipo();
            editarMinutos = String.valueOf(equipoPorProyectoDetalle.getCantidadminutosuso());
            validacionesMinutos = true;
        }
    }

    public void validarMinutosProyecto() {
        if (Utilidades.validarNulo(editarMinutos) && (!editarMinutos.isEmpty()) && (editarMinutos.trim().length() > 0)) {
            if (!Utilidades.isNumberGreaterThanZero(editarMinutos)) {
                validacionesMinutos = false;
                FacesContext.getCurrentInstance().addMessage("form:editarMinutos", new FacesMessage("La cantidad ingresada es incorrecta."));
            } else {
                validacionesMinutos = true;
            }
        } else {
            validacionesMinutos = false;
            FacesContext.getCurrentInstance().addMessage("form:editarMinutos", new FacesMessage("La cantidad es obligatoria."));
        }
        modificacionRegistro = true;
    }

    private boolean validarResultadosValidacion() {
        boolean retorno = true;
        if (validacionesMinutos == false) {
            retorno = false;
        }
        return retorno;
    }

    /**
     * Metodo encargado de realizar el registro y validaciones de la información
     * del editar docente
     */
    public void registrarModificacionEquipoPorProyecto() {
        if (modificacionRegistro == true) {
            if (validarResultadosValidacion() == true) {
                almacenarModificacionEquipoPorProyectoEnSistema();
                recibirIdEquipoPorProyecto(this.idEquipoPorProyecto);
                colorMensaje = "green";
                mensajeFormulario = "El formulario ha sido ingresado con exito.";
            } else {
                colorMensaje = "red";
                mensajeFormulario = "Existen errores en el formulario, por favor corregir para continuar.";
            }
        } else {
            colorMensaje = "black";
            mensajeFormulario = "No existen cambios para ser almacenados en el sistema.";
        }
    }

    private void almacenarModificacionEquipoPorProyectoEnSistema() {
        try {
            equipoPorProyectoDetalle.setUsadoparaprototipo(prototipo);
            int cantidad = Integer.valueOf(editarMinutos);
            equipoPorProyectoDetalle.setCantidadminutosuso(cantidad);
            Integer costo = equipoPorProyectoDetalle.getEquipotecnologico().getValorhorauso() * cantidad;
            equipoPorProyectoDetalle.setCostouso(costo);
            administrarEquipoPorProyectoBO.editarEquipoPorProyecto(equipoPorProyectoDetalle);
        } catch (Exception e) {
            System.out.println("Error ControllerDetallesEquipoPorProyecto almacenarModificacionEquipoPorProyectoEnSistema : " + e.toString());
        }
    }

    public String cancelarRegistroProyecto() {
        editarMinutos = null;
        //
        modificacionRegistro = false;
        validacionesMinutos = true;
        prototipo = false;
        mensajeFormulario = "N/A";
        colorMensaje = "black";
        return "administrarequipoporproyecto";
    }

    //GET-SET
    public BigInteger getIdEquipoPorProyecto() {
        return idEquipoPorProyecto;
    }

    public void setIdEquipoPorProyecto(BigInteger idEquipoPorProyecto) {
        this.idEquipoPorProyecto = idEquipoPorProyecto;
    }

    public EquipoPorProyecto getEquipoPorProyectoDetalle() {
        return equipoPorProyectoDetalle;
    }

    public void setEquipoPorProyectoDetalle(EquipoPorProyecto equipoPorProyectoDetalle) {
        this.equipoPorProyectoDetalle = equipoPorProyectoDetalle;
    }

    public String getEditarMinutos() {
        return editarMinutos;
    }

    public void setEditarMinutos(String editarMinutos) {
        this.editarMinutos = editarMinutos;
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

    public boolean isPrototipo() {
        return prototipo;
    }

    public void setPrototipo(boolean prototipo) {
        this.prototipo = prototipo;
    }

    public boolean isModificacionRegistro() {
        return modificacionRegistro;
    }

    public void setModificacionRegistro(boolean modificacionRegistro) {
        this.modificacionRegistro = modificacionRegistro;
    }

}
