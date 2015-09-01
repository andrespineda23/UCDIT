/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.controller.inventario;

import com.java.ucdit.bo.interfaces.inventario.AdministrarIngresoInsumoBOInterface;
import com.java.ucdit.entidades.IngresoInsumo;
import com.java.ucdit.entidades.Insumo;
import com.java.ucdit.entidades.Proveedor;
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
public class ControllerRegistrarIngresoInsumo implements Serializable {

    @EJB
    AdministrarIngresoInsumoBOInterface administrarIngresosInsumoBO;
    //
    private BigInteger idInsumo;
    private Insumo nuevoInsumo;
    //
    private String nuevoFactura, nuevoValor, nuevoDescripcion;
    private Date nuevoFecha;
    private List<Proveedor> listaProveedor;
    private Proveedor nuevoProveedor;
    //
    private boolean validacionesFactura, validacionesValor, validacionesDescripcion, validacionesFecha, validacionesProveedor;
    private String mensajeFormulario;
    private boolean activarCasillas;
    private String colorMensaje;
    private boolean activarLimpiar;
    private boolean activarAceptar;

    public ControllerRegistrarIngresoInsumo() {
    }

    @PostConstruct
    public void init() {
        nuevoFecha = new Date();
        nuevoValor = "0";
        nuevoFactura = null;
        nuevoDescripcion = null;
        nuevoProveedor = null;
        //
        validacionesFecha = true;
        validacionesValor = false;
        validacionesProveedor = false;
        validacionesFactura = false;
        validacionesDescripcion = false;
        activarLimpiar = true;
        colorMensaje = "black";
        activarCasillas = false;
        activarAceptar = false;
        mensajeFormulario = "N/A";
    }

    public void recibirIdInsumo(BigInteger insumo) {
        this.idInsumo = insumo;
        nuevoInsumo = administrarIngresosInsumoBO.obtenerInsumosPorId(insumo);
    }

    public void validarFacturaIngresoInsumo() {
        if (Utilidades.validarNulo(nuevoFactura) && (!nuevoFactura.isEmpty()) && (nuevoFactura.trim().length() > 0)) {
            int tam = nuevoFactura.length();
            if (tam >= 2) {
                if (!Utilidades.validarCaracteresAlfaNumericos(nuevoFactura)) {
                    validacionesFactura = false;
                    FacesContext.getCurrentInstance().addMessage("form:nuevoFactura", new FacesMessage("La factura ingresada es incorrecta."));
                } else {
                    validacionesFactura = true;
                }
            } else {
                validacionesFactura = false;
                FacesContext.getCurrentInstance().addMessage("form:nuevoFactura", new FacesMessage("El tamaño minimo permitido es 2 caracteres."));
            }
        } else {
            validacionesFactura = false;
            FacesContext.getCurrentInstance().addMessage("form:nuevoFactura", new FacesMessage("La factura es obligatoria."));
        }
    }

    public void validarValorIngresoInsumo() {
        if (Utilidades.validarNulo(nuevoValor) && (!nuevoValor.isEmpty()) && (nuevoValor.trim().length() > 0)) {
            if (Utilidades.isNumberGreaterThanZero(nuevoValor)) {
                validacionesValor = true;
            } else {
                validacionesValor = false;
                FacesContext.getCurrentInstance().addMessage("form:nuevoValor", new FacesMessage("El valor ingresado es incorrecto."));
            }
        } else {
            validacionesValor = false;
            FacesContext.getCurrentInstance().addMessage("form:nuevoValor", new FacesMessage("El valor es obligatorio."));
        }
    }

    public void validarDescripcionIngresoInsumo() {
        if (Utilidades.validarNulo(nuevoDescripcion) && (!nuevoDescripcion.isEmpty()) && (nuevoDescripcion.trim().length() > 0)) {
            int tam = nuevoDescripcion.length();
            if (tam >= 4) {
                if ((Utilidades.validarCaracterString(nuevoDescripcion)) == false) {
                    validacionesDescripcion = false;
                    FacesContext.getCurrentInstance().addMessage("form:nuevoDescripcion", new FacesMessage("La descripción ingresada es incorrecta."));
                } else {
                    validacionesDescripcion = true;
                }
            } else {
                validacionesDescripcion = false;
                FacesContext.getCurrentInstance().addMessage("form:nuevoDescripcion", new FacesMessage("La tamaño minimo permitido es 4 caracteres."));
            }
        } else {
            validacionesDescripcion = false;
            FacesContext.getCurrentInstance().addMessage("form:nuevoDescripcion", new FacesMessage("La descripción es obligatoria."));
        }
    }

    public void validarFechaIngresoInsumo() {
        if (Utilidades.validarNulo(nuevoFecha)) {
            if (Utilidades.fechaIngresadaCorrecta(nuevoFecha)) {
                validacionesFecha = true;
            } else {
                FacesContext.getCurrentInstance().addMessage("form:nuevoFecha", new FacesMessage("La fecha se encuentra incorrecta."));
                validacionesFecha = false;
            }
        } else {
            FacesContext.getCurrentInstance().addMessage("form:nuevoFecha", new FacesMessage("La fecha es obligatoria."));
            validacionesFecha = false;
        }
    }

    public void validarProveedorIngresoInsumo() {
        if (Utilidades.validarNulo(nuevoProveedor)) {
            validacionesProveedor = true;
        } else {
            FacesContext.getCurrentInstance().addMessage("form:nuevoProveedor", new FacesMessage("El proveedor es obligatorio."));
            validacionesProveedor = false;
        }
    }

    private boolean validarResultadosValidacion() {
        boolean retorno = true;
        if (validacionesValor == false) {
            retorno = false;
        }
        if (validacionesFecha == false) {
            retorno = false;
        }
        if (validacionesFactura == false) {
            retorno = false;
        }
        if (validacionesDescripcion == false) {
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
    public void registrarNuevoIngresoInsumo() {
        if (validarResultadosValidacion() == true) {
            almacenarNuevoIngresoInsumoEnSistema();
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

    public void almacenarNuevoIngresoInsumoEnSistema() {
        try {
            IngresoInsumo nuevaIngresoInsumo = new IngresoInsumo();
            nuevaIngresoInsumo.setValorcompra(Integer.valueOf(nuevoValor));
            nuevaIngresoInsumo.setNumerofactura(nuevoFactura);
            nuevaIngresoInsumo.setFechacompra(nuevoFecha);
            nuevaIngresoInsumo.setDescripcion(nuevoDescripcion);
            nuevaIngresoInsumo.setProveedor(nuevoProveedor);
            nuevaIngresoInsumo.setInsumo(nuevoInsumo);
            administrarIngresosInsumoBO.crearIngresoInsumo(nuevaIngresoInsumo);
        } catch (Exception e) {
            System.out.println("Error ControllerRegistrarIngresoInsumo almacenarNuevoIngresoInsumoEnSistema : " + e.toString());
        }
    }

    public void limpiarFormulario() {
        nuevoFecha = new Date();
        nuevoValor = "0";
        nuevoFactura = null;
        nuevoDescripcion = null;
        nuevoProveedor = null;
        //
        validacionesFecha = true;
        validacionesValor = false;
        validacionesFactura = false;
        validacionesProveedor = false;
        validacionesDescripcion = false;
        mensajeFormulario = "";
    }

    public String cancelarRegistroIngresoInsumo() {
        nuevoFecha = new Date();
        nuevoValor = "0";
        nuevoFactura = null;
        nuevoProveedor = null;
        nuevoDescripcion = null;
        //
        validacionesFecha = true;
        validacionesValor = false;
        validacionesProveedor = false;
        validacionesFactura = false;
        validacionesDescripcion = false;
        activarAceptar = false;
        mensajeFormulario = "N/A";
        activarLimpiar = true;
        listaProveedor = null;
        colorMensaje = "black";
        activarCasillas = false;
        return "administraringresoinsumo";
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
    public BigInteger getIdInsumo() {
        return idInsumo;
    }

    public void setIdInsumo(BigInteger idInsumo) {
        this.idInsumo = idInsumo;
    }

    public Insumo getNuevoInsumo() {
        return nuevoInsumo;
    }

    public void setNuevoInsumo(Insumo nuevoInsumo) {
        this.nuevoInsumo = nuevoInsumo;
    }

    public String getNuevoFactura() {
        return nuevoFactura;
    }

    public void setNuevoFactura(String nuevoFactura) {
        this.nuevoFactura = nuevoFactura;
    }

    public String getNuevoValor() {
        return nuevoValor;
    }

    public void setNuevoValor(String nuevoValor) {
        this.nuevoValor = nuevoValor;
    }

    public String getNuevoDescripcion() {
        return nuevoDescripcion;
    }

    public void setNuevoDescripcion(String nuevoDescripcion) {
        this.nuevoDescripcion = nuevoDescripcion;
    }

    public Date getNuevoFecha() {
        return nuevoFecha;
    }

    public void setNuevoFecha(Date nuevoFecha) {
        this.nuevoFecha = nuevoFecha;
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

    public List<Proveedor> getListaProveedor() {
        if (null == listaProveedor) {
            listaProveedor = administrarIngresosInsumoBO.obtenerProveedoresRegistrados();
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

}
