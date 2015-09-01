/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.controller.inventario;

import com.java.ucdit.bo.interfaces.inventario.AdministrarEquipoTecnologicoBOInterface;
import com.java.ucdit.entidades.EquipoTecnologico;
import com.java.ucdit.entidades.Proveedor;
import com.java.ucdit.entidades.TipoEquipo;
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
public class ControllerRegistrarEquipo implements Serializable {

    @EJB
    AdministrarEquipoTecnologicoBOInterface administrarEquipoTecnologicoBO;

    //
    private String nuevoNombre, nuevoCodigo, nuevoDescripcion;
    private Date nuevoFechaCompra;
    private String nuevoValorCompra, nuevoValorUso;
    private List<Proveedor> listaProveedor;
    private Proveedor nuevoProveedor;
    private List<TipoEquipo> listaTipoEquipo;
    private TipoEquipo nuevoTipoEquipo;
    //
    private boolean validacionesNombre, validacionesCodigo, validacionesDescripcion, validacionesFechaCompra;
    private boolean validacionesValorCompra, validacionesValorUso, validacionesTipoEquipo, validacionesProveedor;
    private String mensajeFormulario;
    private boolean activarCasillas;
    private String colorMensaje;
    private boolean activarLimpiar;
    private boolean activarAceptar;

    public ControllerRegistrarEquipo() {
    }

    @PostConstruct
    public void init() {
        nuevoCodigo = null;
        nuevoNombre = null;
        nuevoDescripcion = null;
        nuevoValorUso = "0";
        nuevoFechaCompra = new Date();
        nuevoValorCompra = "0";
        nuevoTipoEquipo = null;
        nuevoProveedor = null;
        //
        validacionesFechaCompra = true;
        validacionesDescripcion = true;
        validacionesCodigo = false;
        validacionesNombre = false;
        validacionesValorUso = false;
        validacionesValorCompra = false;
        validacionesTipoEquipo = false;
        validacionesProveedor = false;
        activarLimpiar = true;
        colorMensaje = "black";
        activarCasillas = false;
        activarAceptar = false;
        mensajeFormulario = "N/A";
    }

    public void validarNombreEquipoTecnologico() {
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

    public void validarCodigoEquipoTecnologico() {
        if (Utilidades.validarNulo(nuevoCodigo) && (!nuevoCodigo.isEmpty()) && (nuevoCodigo.trim().length() > 0)) {
            int tam = nuevoCodigo.length();
            if (tam >= 4) {
                if (Utilidades.validarCaracteresAlfaNumericos(nuevoCodigo)) {
                    EquipoTecnologico registro = administrarEquipoTecnologicoBO.obtenerEquipoTecnologicoPorCodigo(nuevoCodigo);
                    if (registro == null) {
                        validacionesCodigo = true;
                    } else {
                        validacionesCodigo = false;
                        FacesContext.getCurrentInstance().addMessage("form:nuevoCodigo", new FacesMessage("El codigo ingresado ya esta registrado."));
                    }
                } else {
                    validacionesCodigo = false;
                    FacesContext.getCurrentInstance().addMessage("form:nuevoCodigo", new FacesMessage("El codigo ingresado es incorrecto."));
                }
            } else {
                validacionesCodigo = false;
                FacesContext.getCurrentInstance().addMessage("form:nuevoCodigo", new FacesMessage("La tamaño minimo permitido es 4 caracteres."));
            }
        } else {
            validacionesCodigo = false;
            FacesContext.getCurrentInstance().addMessage("form:nuevoCodigo", new FacesMessage("El codigo es obligatorio."));
        }
    }

    public void validarDescripcionEquipoTecnologico() {
        if (Utilidades.validarNulo(nuevoDescripcion) && (!nuevoDescripcion.isEmpty()) && (nuevoDescripcion.trim().length() > 0)) {
            int tam = nuevoDescripcion.length();
            if (tam >= 10) {
                if (Utilidades.validarCaracteresAlfaNumericos(nuevoDescripcion)) {
                    validacionesDescripcion = true;
                } else {
                    validacionesDescripcion = false;
                    FacesContext.getCurrentInstance().addMessage("form:nuevoDescripcion", new FacesMessage("La descripcion ingresada es incorrecta."));
                }
            } else {
                validacionesDescripcion = false;
                FacesContext.getCurrentInstance().addMessage("form:nuevoDescripcion", new FacesMessage("La tamaño minimo permitido es 4 caracteres."));
            }
        }
    }

    public void validarValorCompraEquipoTecnologico() {
        if (Utilidades.validarNulo(nuevoValorCompra) && (!nuevoValorCompra.isEmpty()) && (nuevoValorCompra.trim().length() > 0)) {
            if (Utilidades.isNumber(nuevoValorCompra)) {
                validacionesValorCompra = true;
            } else {
                validacionesValorCompra = false;
                FacesContext.getCurrentInstance().addMessage("form:nuevoValorCompra", new FacesMessage("El valor compra es incorrecto."));
            }
        } else {
            validacionesValorCompra = false;
            FacesContext.getCurrentInstance().addMessage("form:nuevoValorCompra", new FacesMessage("El valor compra es obligatorio."));
        }
    }

    public void validarValorUsoEquipoTecnologico() {
        if (Utilidades.validarNulo(nuevoValorUso) && (!nuevoValorUso.isEmpty()) && (nuevoValorUso.trim().length() > 0)) {
            if (Utilidades.isNumber(nuevoValorUso)) {
                validacionesValorUso = true;
            } else {
                validacionesValorUso = false;
                FacesContext.getCurrentInstance().addMessage("form:nuevoValorUso", new FacesMessage("El valor uso es incorrecto."));
            }
        } else {
            validacionesValorUso = false;
            FacesContext.getCurrentInstance().addMessage("form:nuevoValorUso", new FacesMessage("El valor uso es obligatorio."));
        }
    }

    public void validarFechaCompraEquipoTecnologico() {
        if (Utilidades.validarNulo(nuevoFechaCompra)) {
            if (Utilidades.fechaIngresadaCorrecta(nuevoFechaCompra)) {
                validacionesFechaCompra = true;
            } else {
                FacesContext.getCurrentInstance().addMessage("form:nuevoFechaCompra", new FacesMessage("La fecha de compra es incorrecta."));
                validacionesFechaCompra = false;
            }
        } else {
            FacesContext.getCurrentInstance().addMessage("form:nuevoFechaCompra", new FacesMessage("La fecha de compra es obligatoria."));
            validacionesFechaCompra = false;
        }
    }

    public void validarProveedorEquipoTecnologico() {
        if (Utilidades.validarNulo(nuevoProveedor)) {
            validacionesProveedor = true;
        } else {
            FacesContext.getCurrentInstance().addMessage("form:nuevoProveedor", new FacesMessage("El proveedor es obligatorio."));
            validacionesFechaCompra = false;
        }
    }

    public void validarTipoEquipoEquipoTecnologico() {
        if (Utilidades.validarNulo(nuevoTipoEquipo)) {
            validacionesTipoEquipo = true;
        } else {
            FacesContext.getCurrentInstance().addMessage("form:nuevoTipoEquipo", new FacesMessage("El tipo equipo es obligatorio."));
            validacionesFechaCompra = false;
        }
    }

    private boolean validarResultadosValidacion() {
        boolean retorno = true;
        if (validacionesCodigo == false) {
            retorno = false;
        }
        if (validacionesFechaCompra == false) {
            retorno = false;
        }
        if (validacionesNombre == false) {
            retorno = false;
        }
        if (validacionesDescripcion == false) {
            retorno = false;
        }
        if (validacionesValorCompra == false) {
            retorno = false;
        }
        if (validacionesValorUso == false) {
            retorno = false;
        }
        if (validacionesTipoEquipo == false) {
            retorno = false;
        }
        if (validacionesProveedor == false) {
            retorno = false;
        }
        return retorno;
    }

    /**
     * Metodo encargado de realizar el registro y validaciones de la información
     * del nuevo docente
     */
    public void registrarNuevoEquipoTecnologico() {
        if (validarResultadosValidacion() == true) {
            almacenarNuevoEquipoTecnologicoEnSistema();
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

    public void almacenarNuevoEquipoTecnologicoEnSistema() {
        try {
            EquipoTecnologico nuevaEquipoTecnologico = new EquipoTecnologico();
            nuevaEquipoTecnologico.setEstadoequipo(true);
            nuevaEquipoTecnologico.setCodigoequipo(nuevoCodigo);
            nuevaEquipoTecnologico.setNombreequipo(nuevoNombre);
            nuevaEquipoTecnologico.setFechaadquisicion(nuevoFechaCompra);
            nuevaEquipoTecnologico.setValorhorauso(Integer.valueOf(nuevoValorUso));
            nuevaEquipoTecnologico.setValorcompra(Integer.valueOf(nuevoValorCompra));
            if (Utilidades.validarNulo(nuevoDescripcion)) {
                nuevaEquipoTecnologico.setDescripcion(nuevoValorUso);
            } else {
                nuevaEquipoTecnologico.setDescripcion("");
            }
            nuevaEquipoTecnologico.setProveedor(nuevoProveedor);
            nuevaEquipoTecnologico.setTipoequipo(nuevoTipoEquipo);
            administrarEquipoTecnologicoBO.crearEquipoTecnologico(nuevaEquipoTecnologico);
        } catch (Exception e) {
            System.out.println("Error ControllerRegistrarEquipoTecnologico almacenarNuevoEquipoTecnologicoEnSistema : " + e.toString());
        }
    }

    public void limpiarFormulario() {
        nuevoFechaCompra = new Date();
        nuevoCodigo = null;
        nuevoNombre = null;
        nuevoValorUso = "0";
        nuevoDescripcion = null;
        nuevoValorCompra = "0";
        nuevoProveedor = null;
        nuevoTipoEquipo = null;
        //
        validacionesFechaCompra = true;
        validacionesDescripcion = true;
        validacionesCodigo = false;
        validacionesNombre = false;
        validacionesValorUso = false;
        validacionesValorCompra = false;
        validacionesTipoEquipo = false;
        validacionesProveedor = false;
        mensajeFormulario = "";
    }

    public String cancelarRegistroEquipoTecnologico() {
        nuevoFechaCompra = new Date();
        nuevoCodigo = null;
        nuevoNombre = null;
        nuevoValorUso = "0";
        nuevoDescripcion = null;
        nuevoValorCompra = "0";
        nuevoProveedor = null;
        nuevoTipoEquipo = null;
        //
        validacionesFechaCompra = true;
        validacionesDescripcion = true;
        validacionesCodigo = false;
        validacionesNombre = false;
        validacionesValorUso = false;
        validacionesValorCompra = false;
        validacionesTipoEquipo = false;
        validacionesProveedor = false;
        activarAceptar = false;
        mensajeFormulario = "N/A";
        activarLimpiar = true;
        colorMensaje = "black";
        activarCasillas = false;
        listaProveedor = null;
        listaTipoEquipo = null;
        return "administrarequipo";
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

    public String getNuevoCodigo() {
        return nuevoCodigo;
    }

    public void setNuevoCodigo(String nuevoCodigo) {
        this.nuevoCodigo = nuevoCodigo;
    }

    public String getNuevoDescripcion() {
        return nuevoDescripcion;
    }

    public void setNuevoDescripcion(String nuevoDescripcion) {
        this.nuevoDescripcion = nuevoDescripcion;
    }

    public Date getNuevoFechaCompra() {
        return nuevoFechaCompra;
    }

    public void setNuevoFechaCompra(Date nuevoFechaCompra) {
        this.nuevoFechaCompra = nuevoFechaCompra;
    }

    public String getNuevoValorCompra() {
        return nuevoValorCompra;
    }

    public void setNuevoValorCompra(String nuevoValorCompra) {
        this.nuevoValorCompra = nuevoValorCompra;
    }

    public String getNuevoValorUso() {
        return nuevoValorUso;
    }

    public void setNuevoValorUso(String nuevoValorUso) {
        this.nuevoValorUso = nuevoValorUso;
    }

    public List<Proveedor> getListaProveedor() {
        if (null == listaProveedor) {
            listaProveedor = administrarEquipoTecnologicoBO.consultarProveedoresRegistrados();
        }
        return listaProveedor;
    }

    public void setListaProveedor(List<Proveedor> listaProveedor) {
        this.listaProveedor = listaProveedor;
    }

    public Proveedor getNuevoProveedor() {
        return nuevoProveedor;
    }

    public void setNuevoProveedor(Proveedor nuevoProveedor) {
        this.nuevoProveedor = nuevoProveedor;
    }

    public List<TipoEquipo> getListaTipoEquipo() {
        if (null == listaTipoEquipo) {
            listaTipoEquipo = administrarEquipoTecnologicoBO.consultarTipoEquipoRegistrado();
        }
        return listaTipoEquipo;
    }

    public void setListaTipoEquipo(List<TipoEquipo> listaTipoEquipo) {
        this.listaTipoEquipo = listaTipoEquipo;
    }

    public TipoEquipo getNuevoTipoEquipo() {
        return nuevoTipoEquipo;
    }

    public void setNuevoTipoEquipo(TipoEquipo nuevoTipoEquipo) {
        this.nuevoTipoEquipo = nuevoTipoEquipo;
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
