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
import java.math.BigInteger;
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
public class ControllerDetallesInsumo implements Serializable {

    @EJB
    AdministrarInsumoBOInterface administrarInsumoBO;
    //
    private Insumo insumoDetalle;
    private BigInteger idInsumo;
    //
    private String editarNombre, editarCodigo, editarDescripcion, editarCostoCompra;
    private String editarCantMinima, editarCantExistencia;
    private List<Proveedor> listaProveedor;
    private Proveedor editarProveedor;
    private List<TipoUnidad> listaTipoUnidad;
    private TipoUnidad editarTipoUnidad;
    //
    private boolean validacionesNombre, validacionesCodigo, validacionesDescripcion, validacionesCostoCompra;
    private boolean validacionesCantMinima, validacionesCantExistencia, validacionesTipoUnidad, validacionesProveedor;
    private String mensajeFormulario;
    private String colorMensaje;
    private boolean modificacionRegistro;

    public ControllerDetallesInsumo() {
    }

    @PostConstruct
    public void init() {
        colorMensaje = "black";
        mensajeFormulario = "N/A";
    }

    public void recibirIdInsumoDetalle(BigInteger idInsumo) {
        this.idInsumo = idInsumo;
        insumoDetalle = administrarInsumoBO.obtenerInsumoPorId(this.idInsumo);
        modificacionRegistro = false;
        cargarInformacionRegistro();
    }

    private void cargarInformacionRegistro() {
        if (Utilidades.validarNulo(insumoDetalle)) {
            editarCodigo = insumoDetalle.getCodigoinsumo();
            editarNombre = insumoDetalle.getNombreinsumo();
            editarDescripcion = insumoDetalle.getDescripcion();
            editarCantExistencia = String.valueOf(insumoDetalle.getCantidadexistencia());
            editarCostoCompra = String.valueOf(insumoDetalle.getCostocompra());
            editarCantMinima = String.valueOf(insumoDetalle.getCantidadminima());
            editarTipoUnidad = insumoDetalle.getTipounidad();
            editarProveedor = insumoDetalle.getProveedor();
            //
            validacionesCostoCompra = true;
            validacionesDescripcion = true;
            validacionesCodigo = true;
            validacionesNombre = true;
            validacionesCantExistencia = true;
            validacionesCantMinima = true;
            validacionesTipoUnidad = true;
            validacionesProveedor = true;
            listaProveedor = administrarInsumoBO.consultarProveedoresRegistrados();
            listaTipoUnidad = administrarInsumoBO.consultarTipoUnidadRegistrado();
        }
    }

    private void restaurarRegistro() {
        colorMensaje = "black";
        mensajeFormulario = "N/A";
        modificacionRegistro = false;
        insumoDetalle = null;
        insumoDetalle = administrarInsumoBO.obtenerInsumoPorId(this.idInsumo);
        cargarInformacionRegistro();
    }

    public void validarNombreInsumo() {
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

    public void validarCodigoInsumo() {
        if (Utilidades.validarNulo(editarCodigo) && (!editarCodigo.isEmpty()) && (editarCodigo.trim().length() > 0)) {
            int tam = editarCodigo.length();
            if (tam >= 4) {
                if (Utilidades.validarCaracteresAlfaNumericos(editarCodigo)) {
                    Insumo registro = administrarInsumoBO.obtenerInsumoPorCodigo(editarCodigo);
                    if (registro == null) {
                        validacionesCodigo = true;
                    } else {
                        if (!insumoDetalle.getIdinsumo().equals(registro.getIdinsumo())) {
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

    public void validarDescripcionInsumo() {
        if (Utilidades.validarNulo(editarDescripcion) && (!editarDescripcion.isEmpty()) && (editarDescripcion.trim().length() > 0)) {
            int tam = editarDescripcion.length();
            if (tam >= 10) {
                if (Utilidades.validarCaracteresAlfaNumericos(editarDescripcion)) {
                    validacionesDescripcion = true;
                } else {
                    validacionesDescripcion = false;
                    FacesContext.getCurrentInstance().addMessage("form:editarDescripcion", new FacesMessage("La descripcion ingresada es incorrecta."));
                }
            } else {
                validacionesDescripcion = false;
                FacesContext.getCurrentInstance().addMessage("form:editarDescripcion", new FacesMessage("La tamaño minimo permitido es 4 caracteres."));
            }
        }
        modificacionRegistro = true;
    }

    public void validarCantMinimaInsumo() {
        if (Utilidades.validarNulo(editarCantMinima) && (!editarCantMinima.isEmpty()) && (editarCantMinima.trim().length() > 0)) {
            if (Utilidades.isNumber(editarCantMinima)) {
                validacionesCantMinima = true;
            } else {
                validacionesCantMinima = false;
                FacesContext.getCurrentInstance().addMessage("form:editarCantMinima", new FacesMessage("La cantidad minima es incorrecta."));
            }
        } else {
            validacionesCantMinima = false;
            FacesContext.getCurrentInstance().addMessage("form:editarCantMinima", new FacesMessage("La cantidad minima es obligatoria."));
        }
        modificacionRegistro = true;
    }

    public void validarCantExistenciaInsumo() {
        if (Utilidades.validarNulo(editarCantExistencia) && (!editarCantExistencia.isEmpty()) && (editarCantExistencia.trim().length() > 0)) {
            if (Utilidades.isNumber(editarCantExistencia)) {
                validacionesCantExistencia = true;
            } else {
                validacionesCantExistencia = false;
                FacesContext.getCurrentInstance().addMessage("form:editarCantExistencia", new FacesMessage("La cantidad existencia es incorrecta."));
            }
        } else {
            validacionesCantExistencia = false;
            FacesContext.getCurrentInstance().addMessage("form:editarCantExistencia", new FacesMessage("La cantidad existencia es obligatoria."));
        }
        modificacionRegistro = true;
    }

    public void validarCostoCompraInsumo() {
        if ((Utilidades.validarNulo(editarCostoCompra)) && (!editarCostoCompra.isEmpty()) && (editarCostoCompra.trim().length() > 0)) {
            if (Utilidades.isNumber(editarCostoCompra)) {
                validacionesCostoCompra = true;
            } else {
                FacesContext.getCurrentInstance().addMessage("form:editarCostoCompra", new FacesMessage("El costo compra se encuentra incorrecto."));
                validacionesCostoCompra = false;
            }
        }
        modificacionRegistro = true;
    }

    public void validarProveedorInsumo() {
        if (Utilidades.validarNulo(editarProveedor)) {
            validacionesProveedor = true;
        } else {
            FacesContext.getCurrentInstance().addMessage("form:editarProveedor", new FacesMessage("El proveedor es obligatorio."));
            validacionesCostoCompra = false;
        }
        modificacionRegistro = true;
    }

    public void validarTipoUnidadInsumo() {
        if (Utilidades.validarNulo(editarTipoUnidad)) {
            validacionesTipoUnidad = true;
        } else {
            FacesContext.getCurrentInstance().addMessage("form:editarTipoUnidad", new FacesMessage("El tipo unidad es obligatorio."));
            validacionesCostoCompra = false;
        }
        modificacionRegistro = true;
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
     * del editar docente
     */
    public void registrarModificacionInsumo() {
        if (modificacionRegistro == true) {
            if (validarResultadosValidacion() == true) {
                almacenarModificacionInsumoEnSistema();
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

    private void almacenarModificacionInsumoEnSistema() {
        try {
            insumoDetalle.setCodigoinsumo(editarCodigo);
            insumoDetalle.setNombreinsumo(editarNombre);
            insumoDetalle.setCantidadminima(Integer.valueOf(editarCantMinima));
            insumoDetalle.setCantidadexistencia(Integer.valueOf(editarCantExistencia));
            if (Utilidades.validarNulo(editarCostoCompra)) {
                insumoDetalle.setCostocompra(Integer.valueOf(editarCostoCompra));
            } else {
                insumoDetalle.setCostocompra(Integer.valueOf("0"));
            }
            if (Utilidades.validarNulo(editarDescripcion)) {
                insumoDetalle.setDescripcion(editarDescripcion);
            } else {
                insumoDetalle.setDescripcion("");
            }
            insumoDetalle.setProveedor(editarProveedor);
            insumoDetalle.setTipounidad(editarTipoUnidad);
            administrarInsumoBO.editarInsumo(insumoDetalle);
        } catch (Exception e) {
            System.out.println("Error ControllerRegistrarInsumo almacenarModificacionInsumoEnSistema : " + e.toString());
        }
    }

    public String cancelarRegistroInsumo() {
        editarCostoCompra = null;
        editarCodigo = null;
        editarNombre = null;
        editarCantExistencia = null;
        editarDescripcion = null;
        editarCantMinima = null;
        editarProveedor = null;
        editarTipoUnidad = null;
        //
        validacionesCostoCompra = true;
        validacionesDescripcion = true;
        validacionesCodigo = true;
        validacionesNombre = true;
        validacionesCantExistencia = true;
        validacionesCantMinima = true;
        validacionesTipoUnidad = true;
        validacionesProveedor = true;
        mensajeFormulario = "N/A";
        colorMensaje = "black";
        listaProveedor = null;
        listaTipoUnidad = null;
        return "administrarinsumo";
    }

    //GET-SET
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

    public String getEditarDescripcion() {
        return editarDescripcion;
    }

    public void setEditarDescripcion(String editarDescripcion) {
        this.editarDescripcion = editarDescripcion;
    }

    public String getEditarCostoCompra() {
        return editarCostoCompra;
    }

    public void setEditarCostoCompra(String editarCostoCompra) {
        this.editarCostoCompra = editarCostoCompra;
    }

    public String getEditarCantMinima() {
        return editarCantMinima;
    }

    public void setEditarCantMinima(String editarCantMinima) {
        this.editarCantMinima = editarCantMinima;
    }

    public String getEditarCantExistencia() {
        return editarCantExistencia;
    }

    public void setEditarCantExistencia(String editarCantExistencia) {
        this.editarCantExistencia = editarCantExistencia;
    }

    public List<Proveedor> getListaProveedor() {
        return listaProveedor;
    }

    public void setListaProveedor(List<Proveedor> listaProveedor) {
        this.listaProveedor = listaProveedor;
    }

    public Proveedor getEditarProveedor() {
        return editarProveedor;
    }

    public void setEditarProveedor(Proveedor editarProveedor) {
        this.editarProveedor = editarProveedor;
    }

    public List<TipoUnidad> getListaTipoUnidad() {
        return listaTipoUnidad;
    }

    public void setListaTipoUnidad(List<TipoUnidad> listaTipoUnidad) {
        this.listaTipoUnidad = listaTipoUnidad;
    }

    public TipoUnidad getEditarTipoUnidad() {
        return editarTipoUnidad;
    }

    public void setEditarTipoUnidad(TipoUnidad editarTipoUnidad) {
        this.editarTipoUnidad = editarTipoUnidad;
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
