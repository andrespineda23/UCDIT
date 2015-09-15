/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.controller.inventario;

import com.java.ucdit.ayuda.RegistroFactura;
import com.java.ucdit.bo.interfaces.inventario.AdministrarIngresoInsumoBOInterface;
import com.java.ucdit.entidades.IngresoInsumo;
import com.java.ucdit.entidades.Insumo;
import com.java.ucdit.entidades.Proveedor;
import com.java.ucdit.utilidades.Utilidades;
import java.io.Serializable;
import java.util.ArrayList;
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
public class ControllerRegistrarFactura implements Serializable {

    @EJB
    AdministrarIngresoInsumoBOInterface administrarIngresosInsumoBO;

    private List<RegistroFactura> listaRegistroFactura;
    private List<Insumo> listaInsumos;
    private Insumo insumoIngreso;
    //
    private String nuevoValor, nuevoCantidad;
    private String nuevoFactura, nuevoDescripcion;
    private Date nuevoFecha;
    private List<Proveedor> listaProveedor;
    private Proveedor nuevoProveedor;
    //
    private boolean validacionesFactura, validacionesValor, validacionesDescripcion, validacionesFecha, validacionesProveedor, validacionesCantidad, validacionesInsumo;
    private String mensajeFormulario;
    private boolean activarCasillas;
    private String colorMensaje;
    private boolean activarLimpiar;
    private boolean activarAceptar;
    private boolean fechaDiferida;

    public ControllerRegistrarFactura() {
    }

    @PostConstruct
    public void init() {
        listaRegistroFactura = null;
        insumoIngreso = null;
        fechaDiferida = true;
        nuevoFecha = new Date();
        nuevoValor = "0";
        nuevoFactura = null;
        nuevoCantidad = "1";
        nuevoDescripcion = null;
        nuevoProveedor = null;
        //
        validacionesFecha = true;
        validacionesInsumo = false;
        validacionesValor = false;
        validacionesCantidad = true;
        validacionesProveedor = false;
        validacionesFactura = false;
        validacionesDescripcion = false;
        activarLimpiar = true;
        activarCasillas = false;
        activarAceptar = false;
        colorMensaje = "black";
        mensajeFormulario = "N/A";
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

    public void validarCantidadIngresoInsumo() {
        if (Utilidades.validarNulo(nuevoCantidad) && (!nuevoCantidad.isEmpty()) && (nuevoCantidad.trim().length() > 0)) {
            if (!Utilidades.isNumberGreaterThanZero(nuevoCantidad)) {
                validacionesCantidad = false;
                FacesContext.getCurrentInstance().addMessage("form:nuevoCantidad", new FacesMessage("La cantidad ingresada es incorrecta."));
            } else {
                validacionesCantidad = true;
            }
        } else {
            validacionesCantidad = false;
            FacesContext.getCurrentInstance().addMessage("form:nuevoCantidad", new FacesMessage("La cantidad es obligatoria."));
        }
    }

    public void validarValorIngresoInsumo() {
        if (Utilidades.validarNulo(nuevoValor) && (!nuevoValor.isEmpty()) && (nuevoValor.trim().length() > 0)) {
            if (Utilidades.isNumber(nuevoValor)) {
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
                if ((Utilidades.validarCaracteresAlfaNumericos(nuevoDescripcion)) == false) {
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
            if (fechaDiferida == true) {
                nuevoFecha = new Date();
                if (Utilidades.fechaIngresadaCorrecta(nuevoFecha)) {
                    validacionesFecha = true;
                } else {
                    FacesContext.getCurrentInstance().addMessage("form:nuevoFecha", new FacesMessage("La fecha se encuentra incorrecta."));
                    validacionesFecha = false;
                }
            } else {
                if (Utilidades.fechaDiferidaIngresadaCorrecta(nuevoFecha)) {
                    validacionesFecha = true;
                } else {
                    FacesContext.getCurrentInstance().addMessage("form:nuevoFecha", new FacesMessage("La fecha se encuentra incorrecta."));
                    validacionesFecha = false;
                }
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

    public void validarInsumoIngresoInsumo() {
        if (Utilidades.validarNulo(insumoIngreso)) {
            validacionesInsumo = true;
        } else {
            FacesContext.getCurrentInstance().addMessage("form:insumoIngreso", new FacesMessage("El insumo es obligatorio."));
            validacionesProveedor = false;
        }
    }

    private boolean validarRegistroFactura() {
        boolean retorno = true;
        if (validacionesValor == false) {
            retorno = false;
        }
        if (validacionesCantidad == false) {
            retorno = false;
        }
        if (validacionesInsumo == false) {
            retorno = false;
        }
        return retorno;
    }

    public void adicionarRegistroFactura() {
        if (validarRegistroFactura() == true) {
            cargarRegistroAFactura();
        } else {
            colorMensaje = "red";
            mensajeFormulario = "Existen errores en el formulario, por favor corregir para continuar.";
        }
    }

    private void cargarRegistroAFactura() {
        RegistroFactura registroFactura = new RegistroFactura();
        registroFactura.setCosto(Integer.valueOf(nuevoValor));
        registroFactura.setCantidad(Integer.valueOf(nuevoCantidad));
        registroFactura.setInsumo(insumoIngreso);
        if (!Utilidades.validarNulo(listaRegistroFactura)) {
            listaRegistroFactura = new ArrayList<RegistroFactura>();
        }
        listaRegistroFactura.add(registroFactura);
        nuevoValor = "0";
        nuevoCantidad = "1";
        if (Utilidades.validarNulo(listaInsumos)) {
            for (int i = 0; i < listaInsumos.size(); i++) {
                if (insumoIngreso.getIdinsumo().equals(listaInsumos.get(i).getIdinsumo())) {
                    listaInsumos.remove(i);
                }
            }
        }
        insumoIngreso = null;
        validacionesCantidad = true;
        validacionesValor = true;
        validacionesInsumo = false;
    }

    private boolean validarResultadosValidacion() {
        boolean retorno = true;
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
            if (Utilidades.validarNulo(listaRegistroFactura) == false) {
                almacenarNuevoIngresoInsumoEnSistema();
                limpiarFormulario();
                activarLimpiar = false;
                activarAceptar = true;
                activarCasillas = true;
                colorMensaje = "green";
                mensajeFormulario = "El formulario ha sido ingresado con exito.";
            } else {
                colorMensaje = "red";
                mensajeFormulario = "Es necesario asociar un insumo a la factura ingresada.";
            }
        } else {
            colorMensaje = "red";
            mensajeFormulario = "Existen errores en el formulario, por favor corregir para continuar.";
        }
    }

    public void almacenarNuevoIngresoInsumoEnSistema() {
        try {
            for (int i = 0; i < listaRegistroFactura.size(); i++) {
                IngresoInsumo nuevaIngresoInsumo = new IngresoInsumo();
                nuevaIngresoInsumo.setValorcompra(listaRegistroFactura.get(i).getCosto());
                nuevaIngresoInsumo.setNumerofactura(nuevoFactura);
                nuevaIngresoInsumo.setCantidad(listaRegistroFactura.get(i).getCantidad());
                nuevaIngresoInsumo.setFechacompra(nuevoFecha);
                nuevaIngresoInsumo.setDescripcion(nuevoDescripcion);
                nuevaIngresoInsumo.setProveedor(nuevoProveedor);
                nuevaIngresoInsumo.setInsumo(listaRegistroFactura.get(i).getInsumo());
                administrarIngresosInsumoBO.crearIngresoInsumo(nuevaIngresoInsumo);
            }
        } catch (Exception e) {
            System.out.println("Error ControllerRegistrarIngresoInsumo almacenarNuevoIngresoInsumoEnSistema : " + e.toString());
        }
    }

    public void limpiarFormulario() {
        nuevoFecha = new Date();
        nuevoValor = "0";
        nuevoFactura = null;
        fechaDiferida = true;
        nuevoCantidad = "1";
        nuevoDescripcion = null;
        nuevoProveedor = null;
        //
        validacionesFecha = true;
        validacionesValor = false;
        validacionesInsumo = false;
        validacionesCantidad = true;
        validacionesFactura = false;
        validacionesProveedor = false;
        validacionesDescripcion = false;
        //
        mensajeFormulario = "";
        listaInsumos = administrarIngresosInsumoBO.obtenerInsumosRegistrados();
        insumoIngreso = null;
        listaRegistroFactura = null;
    }

    public String cancelarRegistroIngresoInsumo() {
        nuevoFecha = new Date();
        nuevoValor = "0";
        nuevoCantidad = "1";
        nuevoFactura = null;
        nuevoProveedor = null;
        fechaDiferida = true;
        nuevoDescripcion = null;
        //
        validacionesFecha = true;
        validacionesValor = false;
        validacionesProveedor = false;
        validacionesFactura = false;
        validacionesInsumo = false;
        validacionesDescripcion = false;
        validacionesCantidad = true;
        validacionesInsumo = false;
        //
        listaInsumos = administrarIngresosInsumoBO.obtenerInsumosRegistrados();
        insumoIngreso = null;
        listaRegistroFactura = null;
        //
        activarAceptar = false;
        mensajeFormulario = "N/A";
        activarLimpiar = true;
        listaProveedor = null;
        colorMensaje = "black";
        activarCasillas = false;
        return "iniciosupervisor";
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
    public List<RegistroFactura> getListaRegistroFactura() {
        return listaRegistroFactura;
    }

    public void setListaRegistroFactura(List<RegistroFactura> listaRegistroFactura) {
        this.listaRegistroFactura = listaRegistroFactura;
    }

    public List<Insumo> getListaInsumos() {
        if (null == listaInsumos) {
            listaInsumos = administrarIngresosInsumoBO.obtenerInsumosRegistrados();
        }
        return listaInsumos;
    }

    public void setListaInsumos(List<Insumo> listaInsumos) {
        this.listaInsumos = listaInsumos;
    }

    public Insumo getInsumoIngreso() {
        return insumoIngreso;
    }

    public void setInsumoIngreso(Insumo insumoIngreso) {
        this.insumoIngreso = insumoIngreso;
    }

    public String getNuevoValor() {
        return nuevoValor;
    }

    public void setNuevoValor(String nuevoValor) {
        this.nuevoValor = nuevoValor;
    }

    public String getNuevoCantidad() {
        return nuevoCantidad;
    }

    public void setNuevoCantidad(String nuevoCantidad) {
        this.nuevoCantidad = nuevoCantidad;
    }

    public String getNuevoFactura() {
        return nuevoFactura;
    }

    public void setNuevoFactura(String nuevoFactura) {
        this.nuevoFactura = nuevoFactura;
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

}
