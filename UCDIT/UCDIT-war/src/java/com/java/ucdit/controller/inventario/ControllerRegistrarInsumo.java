/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.controller.inventario;

import com.java.ucdit.bo.interfaces.inventario.AdministrarInsumoBOInterface;
import com.java.ucdit.entidades.Insumo;
import com.java.ucdit.entidades.Proveedor;
import com.java.ucdit.entidades.TipoUnidad;
import com.java.ucdit.utilidades.Utilidades;
import java.io.Serializable;
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
public class ControllerRegistrarInsumo implements Serializable {

    @EJB
    AdministrarInsumoBOInterface administrarInsumoBO;
    //
    private String nuevoNombre, nuevoCodigo, nuevoDescripcion, nuevoCostoCompra;
    private String nuevoCantMinima, nuevoCantExistencia;
    private List<Proveedor> listaProveedor;
    private Proveedor nuevoProveedor;
    private List<TipoUnidad> listaTipoUnidad;
    private TipoUnidad nuevoTipoUnidad;
    //
    private boolean validacionesNombre, validacionesCodigo, validacionesDescripcion, validacionesCostoCompra;
    private boolean validacionesCantMinima, validacionesCantExistencia, validacionesTipoUnidad, validacionesProveedor;
    private String mensajeFormulario;
    private boolean activarCasillas;
    private String colorMensaje;
    private boolean activarLimpiar;
    private boolean activarAceptar;

    public ControllerRegistrarInsumo() {
    }

    @PostConstruct
    public void init() {
        nuevoCodigo = null;
        nuevoNombre = null;
        nuevoDescripcion = null;
        nuevoCantExistencia = "0";
        nuevoCostoCompra = "0";
        nuevoCantMinima = "0";
        nuevoTipoUnidad = null;
        nuevoProveedor = null;
        //
        validacionesCostoCompra = true;
        validacionesDescripcion = true;
        validacionesCodigo = false;
        validacionesNombre = false;
        validacionesCantExistencia = true;
        validacionesCantMinima = false;
        validacionesTipoUnidad = false;
        validacionesProveedor = false;
        activarLimpiar = true;
        colorMensaje = "black";
        activarCasillas = false;
        activarAceptar = false;
        mensajeFormulario = "N/A";
    }

    public void validarNombreInsumo() {
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

    public void validarCodigoInsumo() {
        if (Utilidades.validarNulo(nuevoCodigo) && (!nuevoCodigo.isEmpty()) && (nuevoCodigo.trim().length() > 0)) {
            int tam = nuevoCodigo.length();
            if (tam >= 4) {
                if (Utilidades.validarCaracteresAlfaNumericos(nuevoCodigo)) {
                    Insumo registro = administrarInsumoBO.obtenerInsumoPorCodigo(nuevoCodigo);
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

    public void validarDescripcionInsumo() {
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

    public void validarCantMinimaInsumo() {
        if (Utilidades.validarNulo(nuevoCantMinima) && (!nuevoCantMinima.isEmpty()) && (nuevoCantMinima.trim().length() > 0)) {
            if (Utilidades.isNumber(nuevoCantMinima)) {
                validacionesCantMinima = true;
            } else {
                validacionesCantMinima = false;
                FacesContext.getCurrentInstance().addMessage("form:nuevoCantMinima", new FacesMessage("La cantidad minima es incorrecta."));
            }
        } else {
            validacionesCantMinima = false;
            FacesContext.getCurrentInstance().addMessage("form:nuevoCantMinima", new FacesMessage("La cantidad minima es obligatoria."));
        }
    }

    public void validarCantExistenciaInsumo() {
        if (Utilidades.validarNulo(nuevoCantExistencia) && (!nuevoCantExistencia.isEmpty()) && (nuevoCantExistencia.trim().length() > 0)) {
            if (Utilidades.isNumber(nuevoCantExistencia)) {
                validacionesCantExistencia = true;
            } else {
                validacionesCantExistencia = false;
                FacesContext.getCurrentInstance().addMessage("form:nuevoCantExistencia", new FacesMessage("La cantidad existencia es incorrecta."));
            }
        } else {
            validacionesCantExistencia = false;
            FacesContext.getCurrentInstance().addMessage("form:nuevoCantExistencia", new FacesMessage("La cantidad existencia es obligatoria."));
        }
    }

    public void validarCostoCompraInsumo() {
        if ((Utilidades.validarNulo(nuevoCostoCompra)) && (!nuevoCostoCompra.isEmpty()) && (nuevoCostoCompra.trim().length() > 0)) {
            if (Utilidades.isNumber(nuevoCostoCompra)) {
                validacionesCostoCompra = true;
            } else {
                FacesContext.getCurrentInstance().addMessage("form:nuevoCostoCompra", new FacesMessage("El costo compra se encuentra incorrecto."));
                validacionesCostoCompra = false;
            }
        }
    }

    public void validarProveedorInsumo() {
        if (Utilidades.validarNulo(nuevoProveedor)) {
            validacionesProveedor = true;
        } else {
            FacesContext.getCurrentInstance().addMessage("form:nuevoProveedor", new FacesMessage("El proveedor es obligatorio."));
            validacionesCostoCompra = false;
        }
    }

    public void validarTipoUnidadInsumo() {
        if (Utilidades.validarNulo(nuevoTipoUnidad)) {
            validacionesTipoUnidad = true;
        } else {
            FacesContext.getCurrentInstance().addMessage("form:nuevoTipoUnidad", new FacesMessage("El tipo unidad es obligatorio."));
            validacionesCostoCompra = false;
        }
    }

    private boolean validarResultadosValidacion() {
        boolean retorno = true;
        if (validacionesCodigo == false) {
            retorno = false;
        }
        if (validacionesCostoCompra == false) {
            retorno = false;
        }
        if (validacionesNombre == false) {
            retorno = false;
        }
        if (validacionesDescripcion == false) {
            retorno = false;
        }
        if (validacionesCantMinima == false) {
            retorno = false;
        }
        if (validacionesCantExistencia == false) {
            retorno = false;
        }
        if (validacionesTipoUnidad == false) {
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
    public void registrarNuevoInsumo() {
        if (validarResultadosValidacion() == true) {
            almacenarNuevoInsumoEnSistema();
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

    public void almacenarNuevoInsumoEnSistema() {
        try {
            Insumo nuevaInsumo = new Insumo();
            nuevaInsumo.setCodigoinsumo(nuevoCodigo);
            nuevaInsumo.setNombreinsumo(nuevoNombre);
            nuevaInsumo.setCantidadminima(Integer.valueOf(nuevoCantMinima));
            nuevaInsumo.setCantidadexistencia(Integer.valueOf(nuevoCantExistencia));
            if (Utilidades.validarNulo(nuevoCostoCompra)) {
                nuevaInsumo.setCostocompra(Integer.valueOf(nuevoCostoCompra));
            } else {
                nuevaInsumo.setCostocompra(Integer.valueOf("0"));
            }
            if (Utilidades.validarNulo(nuevoDescripcion)) {
                nuevaInsumo.setDescripcion(nuevoDescripcion);
            } else {
                nuevaInsumo.setDescripcion("");
            }
            nuevaInsumo.setProveedor(nuevoProveedor);
            nuevaInsumo.setTipounidad(nuevoTipoUnidad);
            administrarInsumoBO.crearInsumo(nuevaInsumo);
        } catch (Exception e) {
            System.out.println("Error ControllerRegistrarInsumo almacenarNuevoInsumoEnSistema : " + e.toString());
        }
    }

    public void limpiarFormulario() {
        nuevoCostoCompra = "0";
        nuevoCodigo = null;
        nuevoNombre = null;
        nuevoCantExistencia = "0";
        nuevoDescripcion = null;
        nuevoCantMinima = "0";
        nuevoProveedor = null;
        nuevoTipoUnidad = null;
        //
        validacionesCostoCompra = true;
        validacionesDescripcion = true;
        validacionesCodigo = false;
        validacionesNombre = false;
        validacionesCantExistencia = true;
        validacionesCantMinima = false;
        validacionesTipoUnidad = false;
        validacionesProveedor = false;
        mensajeFormulario = "";
    }

    public String cancelarRegistroInsumo() {
        nuevoCostoCompra = "0";
        nuevoCodigo = null;
        nuevoNombre = null;
        nuevoCantExistencia = "0";
        nuevoDescripcion = null;
        nuevoCantMinima = "0";
        nuevoProveedor = null;
        nuevoTipoUnidad = null;
        //
        validacionesCostoCompra = true;
        validacionesDescripcion = true;
        validacionesCodigo = false;
        validacionesNombre = false;
        validacionesCantExistencia = true;
        validacionesCantMinima = false;
        validacionesTipoUnidad = false;
        validacionesProveedor = false;
        activarAceptar = false;
        mensajeFormulario = "N/A";
        activarLimpiar = true;
        colorMensaje = "black";
        activarCasillas = false;
        listaProveedor = null;
        listaTipoUnidad = null;
        return "administrarinsumo";
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

    public String getNuevoCostoCompra() {
        return nuevoCostoCompra;
    }

    public void setNuevoCostoCompra(String nuevoCostoCompra) {
        this.nuevoCostoCompra = nuevoCostoCompra;
    }

    public String getNuevoCantMinima() {
        return nuevoCantMinima;
    }

    public void setNuevoCantMinima(String nuevoCantMinima) {
        this.nuevoCantMinima = nuevoCantMinima;
    }

    public String getNuevoCantExistencia() {
        return nuevoCantExistencia;
    }

    public void setNuevoCantExistencia(String nuevoCantExistencia) {
        this.nuevoCantExistencia = nuevoCantExistencia;
    }

    public List<Proveedor> getListaProveedor() {
        if (null == listaProveedor) {
            listaProveedor = administrarInsumoBO.consultarProveedoresRegistrados();
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

    public List<TipoUnidad> getListaTipoUnidad() {
        if (null == listaTipoUnidad) {
            listaTipoUnidad = administrarInsumoBO.consultarTipoUnidadRegistrado();
        }
        return listaTipoUnidad;
    }

    public void setListaTipoUnidad(List<TipoUnidad> listaTipoUnidad) {
        this.listaTipoUnidad = listaTipoUnidad;
    }

    public TipoUnidad getNuevoTipoUnidad() {
        return nuevoTipoUnidad;
    }

    public void setNuevoTipoUnidad(TipoUnidad nuevoTipoUnidad) {
        this.nuevoTipoUnidad = nuevoTipoUnidad;
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
