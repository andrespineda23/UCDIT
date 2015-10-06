/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.controller.inventario;

import com.java.ucdit.bo.interfaces.inventario.AdministrarEquipoTecnologicoBOInterface;
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
public class ControllerDetallesEquipo implements Serializable {

    @EJB
    AdministrarEquipoTecnologicoBOInterface administrarEquipoTecnologicoBO;

    //
    private IngresoEquipo equipoTecnologicoDetalles;
    private BigInteger idEquipoTecnologico;
    //
    private String editarNombre, editarCodigo, editarDescripcion;
    private Date editarFechaCompra;
    private String editarValorCompra, editarValorUso;
    private List<Proveedor> listaProveedor;
    private Proveedor editarProveedor;
    private List<TipoEquipo> listaTipoEquipo;
    private TipoEquipo editarTipoEquipo;
    private String editarNumeroFactura;
    //
    private boolean validacionesNombre, validacionesCodigo, validacionesDescripcion, validacionesFechaCompra, validacionesNumeroFactura;
    private boolean validacionesValorCompra, validacionesValorUso, validacionesTipoEquipo, validacionesProveedor;
    private String mensajeFormulario;
    private String colorMensaje;
    private boolean modificacionRegistro;
    private boolean editarEstado;
    private boolean fechaDiferida;

    public ControllerDetallesEquipo() {
    }

    @PostConstruct
    public void init() {
        fechaDiferida = true;
        colorMensaje = "black";
        mensajeFormulario = "N/A";
    }

    public void recibirIdEquipoDetalle(BigInteger idEquipo) {
        this.idEquipoTecnologico = idEquipo;
        equipoTecnologicoDetalles = administrarEquipoTecnologicoBO.obtenerIngresoEquipoPorIdEquipo(this.idEquipoTecnologico);
        modificacionRegistro = false;
        cargarInformacionRegistro();
    }

    private void cargarInformacionRegistro() {
        if (Utilidades.validarNulo(equipoTecnologicoDetalles)) {
            editarCodigo = equipoTecnologicoDetalles.getEquipotecnologico().getCodigoequipo();
            editarNombre = equipoTecnologicoDetalles.getEquipotecnologico().getNombreequipo();
            editarDescripcion = equipoTecnologicoDetalles.getEquipotecnologico().getDescripcion();
            editarValorUso = String.valueOf(equipoTecnologicoDetalles.getEquipotecnologico().getValorhorauso());
            editarFechaCompra = equipoTecnologicoDetalles.getEquipotecnologico().getFechaadquisicion();
            editarValorCompra = String.valueOf(equipoTecnologicoDetalles.getEquipotecnologico().getValorcompra());
            editarTipoEquipo = equipoTecnologicoDetalles.getEquipotecnologico().getTipoequipo();
            editarProveedor = equipoTecnologicoDetalles.getEquipotecnologico().getProveedor();
            editarEstado = equipoTecnologicoDetalles.getEquipotecnologico().getEstadoequipo();
            editarNumeroFactura = equipoTecnologicoDetalles.getNumerofactura();
            //
            validacionesFechaCompra = true;
            validacionesDescripcion = true;
            validacionesCodigo = true;
            validacionesNumeroFactura = true;
            validacionesNombre = true;
            validacionesValorUso = true;
            validacionesValorCompra = true;
            validacionesTipoEquipo = true;
            validacionesProveedor = true;
            listaProveedor = administrarEquipoTecnologicoBO.consultarProveedoresRegistrados();
            listaTipoEquipo = administrarEquipoTecnologicoBO.consultarTipoEquipoRegistrado();
        }
    }

    private void restaurarRegistro() {
        colorMensaje = "black";
        mensajeFormulario = "N/A";
        modificacionRegistro = false;
        equipoTecnologicoDetalles = null;
        recibirIdEquipoDetalle(this.idEquipoTecnologico);
    }

    public void validarNombreEquipoTecnologico() {
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

    public void validarCodigoEquipoTecnologico() {
        if (Utilidades.validarNulo(editarCodigo) && (!editarCodigo.isEmpty()) && (editarCodigo.trim().length() > 0)) {
            int tam = editarCodigo.length();
            if (tam >= 4) {
                if (Utilidades.validarCaracteresAlfaNumericos(editarCodigo)) {
                    EquipoTecnologico registro = administrarEquipoTecnologicoBO.obtenerEquipoTecnologicoPorCodigo(editarCodigo);
                    if (registro == null) {
                        validacionesCodigo = true;
                    } else {
                        if (!equipoTecnologicoDetalles.getEquipotecnologico().getIdequipotecnologico().equals(registro.getIdequipotecnologico())) {
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

    public void validarDescripcionEquipoTecnologico() {
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

    public void validarNumeroFacturaEquipoTecnologico() {
        if (Utilidades.validarNulo(editarNumeroFactura) && (!editarNumeroFactura.isEmpty()) && (editarNumeroFactura.trim().length() > 0)) {
            int tam = editarNumeroFactura.length();
            if (tam >= 2) {
                if (Utilidades.validarCaracteresAlfaNumericos(editarNumeroFactura)) {
                    validacionesNumeroFactura = true;
                } else {
                    validacionesNumeroFactura = false;
                    FacesContext.getCurrentInstance().addMessage("form:editarNumeroFactura", new FacesMessage("El número factura es incorrecto."));
                }
            } else {
                validacionesNumeroFactura = false;
                FacesContext.getCurrentInstance().addMessage("form:editarNumeroFactura", new FacesMessage("La tamaño minimo permitido es 2 caracteres."));
            }
        } else {
            validacionesNumeroFactura = false;
            FacesContext.getCurrentInstance().addMessage("form:editarNumeroFactura", new FacesMessage("El número factura es obligatorio."));
        }
        modificacionRegistro = true;
    }

    public void validarValorCompraEquipoTecnologico() {
        if (Utilidades.validarNulo(editarValorCompra) && (!editarValorCompra.isEmpty()) && (editarValorCompra.trim().length() > 0)) {
            if (Utilidades.isNumber(editarValorCompra)) {
                validacionesValorCompra = true;
            } else {
                validacionesValorCompra = false;
                FacesContext.getCurrentInstance().addMessage("form:editarValorCompra", new FacesMessage("El valor compra es incorrecto."));
            }
        } else {
            validacionesValorCompra = false;
            FacesContext.getCurrentInstance().addMessage("form:editarValorCompra", new FacesMessage("El valor compra es obligatorio."));
        }
        modificacionRegistro = true;
    }

    public void validarValorUsoEquipoTecnologico() {
        if (Utilidades.validarNulo(editarValorUso) && (!editarValorUso.isEmpty()) && (editarValorUso.trim().length() > 0)) {
            if (Utilidades.isNumber(editarValorUso)) {
                validacionesValorUso = true;
            } else {
                validacionesValorUso = false;
                FacesContext.getCurrentInstance().addMessage("form:editarValorUso", new FacesMessage("El valor uso es incorrecto."));
            }
        } else {
            validacionesValorUso = false;
            FacesContext.getCurrentInstance().addMessage("form:editarValorUso", new FacesMessage("El valor uso es obligatorio."));
        }
        modificacionRegistro = true;
    }

    public void validarFechaCompraEquipoTecnologico() {
        if (Utilidades.validarNulo(editarFechaCompra)) {
            if (fechaDiferida == true) {
                editarFechaCompra = new Date();
                if (Utilidades.fechaIngresadaCorrecta(editarFechaCompra)) {
                    validacionesFechaCompra = true;
                } else {
                    FacesContext.getCurrentInstance().addMessage("form:editarFechaCompra", new FacesMessage("La fecha de compra es incorrecta."));
                    validacionesFechaCompra = false;
                }
            } else {
                if (Utilidades.fechaDiferidaIngresadaCorrecta(editarFechaCompra)) {
                    validacionesFechaCompra = true;
                } else {
                    FacesContext.getCurrentInstance().addMessage("form:editarFechaCompra", new FacesMessage("La fecha de compra es incorrecta."));
                    validacionesFechaCompra = false;
                }
            }
        } else {
            FacesContext.getCurrentInstance().addMessage("form:editarFechaCompra", new FacesMessage("La fecha de compra es obligatoria."));
            validacionesFechaCompra = false;
        }
        modificacionRegistro = true;
    }

    public void validarProveedorEquipoTecnologico() {
        if (Utilidades.validarNulo(editarProveedor)) {
            validacionesProveedor = true;
        } else {
            FacesContext.getCurrentInstance().addMessage("form:editarProveedor", new FacesMessage("El proveedor es obligatorio."));
            validacionesFechaCompra = false;
        }
        modificacionRegistro = true;
    }

    public void validarTipoEquipoEquipoTecnologico() {
        if (Utilidades.validarNulo(editarTipoEquipo)) {
            validacionesTipoEquipo = true;
        } else {
            FacesContext.getCurrentInstance().addMessage("form:editarTipoEquipo", new FacesMessage("El tipo equipo es obligatorio."));
            validacionesFechaCompra = false;
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
        if (validacionesNumeroFactura == false) {
            retorno = false;
        }
        return retorno;
    }

    /**
     * Metodo encargado de realizar el registro y validaciones de la información
     * del editar docente
     */
    public void registrarModificacionEquipoTecnologico() {
        if (modificacionRegistro == true) {
            if (validarResultadosValidacion() == true) {
                almacenarModificacionEquipoTecnologicoEnSistema();
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

    private void almacenarModificacionEquipoTecnologicoEnSistema() {
        try {
            equipoTecnologicoDetalles.getEquipotecnologico().setEstadoequipo(editarEstado);
            equipoTecnologicoDetalles.getEquipotecnologico().setCodigoequipo(editarCodigo);
            equipoTecnologicoDetalles.getEquipotecnologico().setNombreequipo(editarNombre);
            equipoTecnologicoDetalles.getEquipotecnologico().setFechaadquisicion(editarFechaCompra);
            equipoTecnologicoDetalles.setFechacompra(editarFechaCompra);
            equipoTecnologicoDetalles.getEquipotecnologico().setValorhorauso(Integer.valueOf(editarValorUso));
            equipoTecnologicoDetalles.getEquipotecnologico().setValorcompra(Integer.valueOf(editarValorCompra));
            if (Utilidades.validarNulo(editarDescripcion)) {
                equipoTecnologicoDetalles.getEquipotecnologico().setDescripcion(editarDescripcion);
                equipoTecnologicoDetalles.setDescripcion(editarDescripcion);
            } else {
                equipoTecnologicoDetalles.getEquipotecnologico().setDescripcion("");
                equipoTecnologicoDetalles.setDescripcion("");
            }
            equipoTecnologicoDetalles.getEquipotecnologico().setProveedor(editarProveedor);
            equipoTecnologicoDetalles.getEquipotecnologico().setTipoequipo(editarTipoEquipo);
            equipoTecnologicoDetalles.setNumerofactura(editarNumeroFactura);
            administrarEquipoTecnologicoBO.editarEquipoTecnologico(equipoTecnologicoDetalles);
        } catch (Exception e) {
            System.out.println("Error ControllerRegistrarEquipoTecnologico almacenarModificacionEquipoTecnologicoEnSistema : " + e.toString());
        }
    }

    public String cancelarRegistroEquipoTecnologico() {
        editarFechaCompra = new Date();
        editarCodigo = null;
        editarNombre = null;
        editarValorUso = "0";
        fechaDiferida = true;
        editarDescripcion = null;
        editarValorCompra = "0";
        editarProveedor = null;
        editarTipoEquipo = null;
        //
        validacionesFechaCompra = false;
        validacionesDescripcion = true;
        validacionesCodigo = false;
        validacionesNombre = false;
        validacionesValorUso = false;
        validacionesValorCompra = false;
        validacionesTipoEquipo = false;
        validacionesProveedor = false;
        mensajeFormulario = "N/A";
        colorMensaje = "black";
        listaProveedor = null;
        listaTipoEquipo = null;
        idEquipoTecnologico = null;
        equipoTecnologicoDetalles = null;
        return "administrarequipo";
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

    public Date getEditarFechaCompra() {
        return editarFechaCompra;
    }

    public void setEditarFechaCompra(Date editarFechaCompra) {
        this.editarFechaCompra = editarFechaCompra;
    }

    public String getEditarValorCompra() {
        return editarValorCompra;
    }

    public void setEditarValorCompra(String editarValorCompra) {
        this.editarValorCompra = editarValorCompra;
    }

    public String getEditarValorUso() {
        return editarValorUso;
    }

    public void setEditarValorUso(String editarValorUso) {
        this.editarValorUso = editarValorUso;
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

    public List<TipoEquipo> getListaTipoEquipo() {
        return listaTipoEquipo;
    }

    public void setListaTipoEquipo(List<TipoEquipo> listaTipoEquipo) {
        this.listaTipoEquipo = listaTipoEquipo;
    }

    public TipoEquipo getEditarTipoEquipo() {
        return editarTipoEquipo;
    }

    public void setEditarTipoEquipo(TipoEquipo editarTipoEquipo) {
        this.editarTipoEquipo = editarTipoEquipo;
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

    public boolean isFechaDiferida() {
        return fechaDiferida;
    }

    public void setFechaDiferida(boolean fechaDiferida) {
        this.fechaDiferida = fechaDiferida;
    }

    public BigInteger getIdEquipoTecnologico() {
        return idEquipoTecnologico;
    }

    public void setIdEquipoTecnologico(BigInteger idEquipoTecnologico) {
        this.idEquipoTecnologico = idEquipoTecnologico;
    }

    public String getEditarNumeroFactura() {
        return editarNumeroFactura;
    }

    public void setEditarNumeroFactura(String editarNumeroFactura) {
        this.editarNumeroFactura = editarNumeroFactura;
    }

}
