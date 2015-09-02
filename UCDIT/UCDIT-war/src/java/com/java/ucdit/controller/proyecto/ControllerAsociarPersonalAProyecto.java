/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.controller.proyecto;

import com.java.ucdit.ayuda.AsociacionPersonaProyecto;
import com.java.ucdit.bo.interfaces.proyecto.AdministrarProyectoBOInterface;
import com.java.ucdit.entidades.Cliente;
import com.java.ucdit.entidades.PersonalInterno;
import com.java.ucdit.entidades.PersonalPorProyecto;
import com.java.ucdit.entidades.Proyecto;
import com.java.ucdit.entidades.Supervisor;
import com.java.ucdit.utilidades.Utilidades;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author ELECTRONICA
 */
@ManagedBean
@SessionScoped
public class ControllerAsociarPersonalAProyecto implements Serializable {

    @EJB
    AdministrarProyectoBOInterface administrarProyectoBO;
    //
    private Proyecto proyectoDetalle;
    private BigInteger idProyecto;
    //
    private String proyectoNombre, proyectoDescripcion;
    private String proyectoCostoProyecto;
    private Date proyectoFechaInicio;
    private Cliente proyectoCliente;
    private Supervisor proyectoSupervisor;
    private List<PersonalInterno> listaPersonalInterno;
    private List<PersonalPorProyecto> listaPersonalAsociado;
    private List<AsociacionPersonaProyecto> listaAsociacionPersonaProyecto;
    private String colorMensaje, mensajeFormulario;
    //

    public ControllerAsociarPersonalAProyecto() {
    }

    @PostConstruct
    public void init() {
    }

    private void eliminarPersonalAsociado() {
        if (null != listaPersonalInterno) {
            for (int i = 0; i < listaPersonalInterno.size(); i++) {
                if (listaPersonalInterno.get(i).getIdpersonalinterno().equals(listaPersonalAsociado.get(i).getPersonalinterno().getIdpersonalinterno())) {
                    listaPersonalInterno.remove(i);
                }
            }
        }
    }

    private void iniciarAsociacionPersonaProyecto() {
        if (null != listaPersonalInterno) {
            for (int i = 0; i < listaPersonalInterno.size(); i++) {
                AsociacionPersonaProyecto nuevo = new AsociacionPersonaProyecto();
                nuevo.setPersonalInterno(listaPersonalInterno.get(i));
                nuevo.setActivo(false);
                listaAsociacionPersonaProyecto.add(nuevo);
            }
        }
    }

    public void recibirIdProyectoDetalle(BigInteger idProyecto) {
        this.idProyecto = idProyecto;
        proyectoDetalle = administrarProyectoBO.obtenerProyectoPorId(this.idProyecto);
        cargarInformacionRegistro();
    }

    private void cargarInformacionRegistro() {
        if (Utilidades.validarNulo(proyectoDetalle)) {
            proyectoNombre = proyectoDetalle.getNombreproyecto();
            proyectoFechaInicio = proyectoDetalle.getFechainicio();
            proyectoDescripcion = proyectoDetalle.getDescripcionproyecto();
            proyectoCostoProyecto = String.valueOf(proyectoDetalle.getCostoproyecto());
            proyectoCliente = proyectoDetalle.getCliente();
            proyectoSupervisor = proyectoDetalle.getSupervisor();
            listaPersonalInterno = administrarProyectoBO.obtenerPersonalInternoRegistrado();
            listaPersonalAsociado = administrarProyectoBO.obtenerPersonalPorProyectoPorIdProyecto(proyectoDetalle.getIdproyecto());
            eliminarPersonalAsociado();
            iniciarAsociacionPersonaProyecto();
        }
    }

    private void restaurarRegistro() {
        proyectoDetalle = null;
        proyectoDetalle = administrarProyectoBO.obtenerProyectoPorId(this.idProyecto);
        cargarInformacionRegistro();
    }

    private boolean validarResultadosValidacion() {
        boolean retorno = true;
        int cantidadAntigua = cantidadPersonalAsociadoAntiguo();
        int cantidadNueva = cantidadPersonalAsociadoNuevo();
        if (cantidadAntigua == 0 && cantidadNueva == 0) {
            retorno = false;
        }
        return retorno;
    }

    public void registrarModificacionProyecto() {
        if (validarResultadosValidacion() == true) {
            almacenarModificacionProyectoEnSistema();
            restaurarRegistro();
            colorMensaje = "green";
            mensajeFormulario = "El formulario ha sido ingresado con exito.";
        } else {
            colorMensaje = "red";
            mensajeFormulario = "Es necesario que exista al menos una persona asociada al proyecto.";
        }
    }

    public void almacenarModificacionProyectoEnSistema() {
        try {
            List<PersonalInterno> lista = cargarNuevoPersonalAsociadoAProyecto();
            administrarProyectoBO.asociarPersonalAProyecto(proyectoDetalle, listaPersonalAsociado, lista);
        } catch (Exception e) {
            System.out.println("Error ControllerRegistrarProyecto almacenarModificacionProyectoEnSistema : " + e.toString());
        }
    }

    private int cantidadPersonalAsociadoAntiguo() {
        int cantidad = 0;
        for (int i = 0; i < listaPersonalAsociado.size(); i++) {
            if (listaPersonalAsociado.get(i).getEstado() == true) {
                cantidad++;
            }
        }
        return cantidad;
    }

    private int cantidadPersonalAsociadoNuevo() {
        int cantidad = 0;
        if (null != listaAsociacionPersonaProyecto) {
            for (int i = 0; i < listaAsociacionPersonaProyecto.size(); i++) {
                if (listaAsociacionPersonaProyecto.get(i).isActivo() == true) {
                    cantidad++;
                }
            }
        }
        return cantidad;
    }

    private List<PersonalInterno> cargarNuevoPersonalAsociadoAProyecto() {
        List<PersonalInterno> lista = new ArrayList<PersonalInterno>();
        for (int i = 0; i < listaAsociacionPersonaProyecto.size(); i++) {
            if (listaAsociacionPersonaProyecto.get(i).isActivo() == true) {
                lista.add(listaAsociacionPersonaProyecto.get(i).getPersonalInterno());
            }
        }
        return lista;
    }

    public String cancelarRegistroProyecto() {
        proyectoNombre = null;
        proyectoFechaInicio = new Date();
        proyectoDescripcion = null;
        proyectoCostoProyecto = "0";
        proyectoCliente = null;
        proyectoSupervisor = null;
        listaPersonalAsociado = null;
        listaAsociacionPersonaProyecto = null;
        listaPersonalAsociado = null;
        listaPersonalInterno = null;
        proyectoDetalle = null;
        idProyecto = null;
        colorMensaje = "black";
        mensajeFormulario = "";
        return "detallesproyecto";
    }

    //GET-SET
    public Proyecto getProyectoDetalle() {
        return proyectoDetalle;
    }

    public void setProyectoDetalle(Proyecto proyectoDetalle) {
        this.proyectoDetalle = proyectoDetalle;
    }

    public BigInteger getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(BigInteger idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getProyectoNombre() {
        return proyectoNombre;
    }

    public void setProyectoNombre(String proyectoNombre) {
        this.proyectoNombre = proyectoNombre;
    }

    public String getProyectoDescripcion() {
        return proyectoDescripcion;
    }

    public void setProyectoDescripcion(String proyectoDescripcion) {
        this.proyectoDescripcion = proyectoDescripcion;
    }

    public String getProyectoCostoProyecto() {
        return proyectoCostoProyecto;
    }

    public void setProyectoCostoProyecto(String proyectoCostoProyecto) {
        this.proyectoCostoProyecto = proyectoCostoProyecto;
    }

    public Date getProyectoFechaInicio() {
        return proyectoFechaInicio;
    }

    public void setProyectoFechaInicio(Date proyectoFechaInicio) {
        this.proyectoFechaInicio = proyectoFechaInicio;
    }

    public Cliente getProyectoCliente() {
        return proyectoCliente;
    }

    public void setProyectoCliente(Cliente proyectoCliente) {
        this.proyectoCliente = proyectoCliente;
    }

    public Supervisor getProyectoSupervisor() {
        return proyectoSupervisor;
    }

    public void setProyectoSupervisor(Supervisor proyectoSupervisor) {
        this.proyectoSupervisor = proyectoSupervisor;
    }

    public List<PersonalInterno> getListaPersonalInterno() {
        return listaPersonalInterno;
    }

    public void setListaPersonalInterno(List<PersonalInterno> listaPersonalInterno) {
        this.listaPersonalInterno = listaPersonalInterno;
    }

    public List<PersonalPorProyecto> getListaPersonalAsociado() {
        return listaPersonalAsociado;
    }

    public void setListaPersonalAsociado(List<PersonalPorProyecto> listaPersonalAsociado) {
        this.listaPersonalAsociado = listaPersonalAsociado;
    }

    public List<AsociacionPersonaProyecto> getListaAsociacionPersonaProyecto() {
        return listaAsociacionPersonaProyecto;
    }

    public void setListaAsociacionPersonaProyecto(List<AsociacionPersonaProyecto> listaAsociacionPersonaProyecto) {
        this.listaAsociacionPersonaProyecto = listaAsociacionPersonaProyecto;
    }

    public String getColorMensaje() {
        return colorMensaje;
    }

    public void setColorMensaje(String colorMensaje) {
        this.colorMensaje = colorMensaje;
    }

    public String getMensajeFormulario() {
        return mensajeFormulario;
    }

    public void setMensajeFormulario(String mensajeFormulario) {
        this.mensajeFormulario = mensajeFormulario;
    }

}
