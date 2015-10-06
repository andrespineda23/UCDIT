/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.controller.inventario;

import com.java.ucdit.bo.interfaces.inventario.AdministrarComponenteEquipoBOInterface;
import com.java.ucdit.entidades.Componente;
import com.java.ucdit.entidades.EquipoTecnologico;
import com.java.ucdit.utilidades.Utilidades;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
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
public class ControllerRegistrarComponente implements Serializable {

    @EJB
    AdministrarComponenteEquipoBOInterface administrarComponenteEquipoBO;

    //
    private String nuevoNombre, nuevoCodigo, nuevoMarca;
    private String nuevoModelo, nuevoSerie;
    //
    private boolean validacionesNombre, validacionesCodigo, validacionesMarca;
    private boolean validacionesModelo, validacionesSerie;
    private String mensajeFormulario;
    private boolean activarCasillas;
    private String colorMensaje;
    private boolean activarLimpiar;
    private boolean activarAceptar;
    private boolean fechaDiferida;
    private BigInteger idEquipo;
    private EquipoTecnologico equipoTecnologico;

    public ControllerRegistrarComponente() {
    }

    @PostConstruct
    public void init() {
        nuevoCodigo = null;
        nuevoNombre = null;
        nuevoMarca = null;
        nuevoSerie = null;
        nuevoModelo = null;
        fechaDiferida = true;
        //
        validacionesMarca = true;
        validacionesCodigo = false;
        validacionesNombre = false;
        validacionesSerie = true;
        validacionesModelo = true;
        activarLimpiar = true;
        colorMensaje = "black";
        activarCasillas = false;
        activarAceptar = false;
        mensajeFormulario = "N/A";
    }

    public void recibirIdEquipoTecnologico(BigInteger idEquipo) {
        this.idEquipo = idEquipo;
        equipoTecnologico = administrarComponenteEquipoBO.obtenerEquipoTecnologicoPorId(idEquipo);
        System.out.println("idEquipo: "+idEquipo);
    }

    public void validarNombreComponente() {
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

    public void validarCodigoComponente() {
        if (Utilidades.validarNulo(nuevoCodigo) && (!nuevoCodigo.isEmpty()) && (nuevoCodigo.trim().length() > 0)) {
            int tam = nuevoCodigo.length();
            if (tam >= 4) {
                if (Utilidades.validarCaracteresAlfaNumericos(nuevoCodigo)) {
                    Componente registro = administrarComponenteEquipoBO.validarCodigoRepetidoComponente(nuevoCodigo, idEquipo);
                    if (null == registro) {
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
                FacesContext.getCurrentInstance().addMessage("form:nuevoCodigo", new FacesMessage("El tamaño minimo permitido es 4 caracteres."));
            }
        } else {
            validacionesCodigo = false;
            FacesContext.getCurrentInstance().addMessage("form:nuevoCodigo", new FacesMessage("El codigo es obligatorio."));
        }
    }

    public void validarMarcaComponente() {
        if (Utilidades.validarNulo(nuevoMarca) && (!nuevoMarca.isEmpty()) && (nuevoMarca.trim().length() > 0)) {
            int tam = nuevoMarca.length();
            if (tam >= 2) {
                if (Utilidades.validarCaracteresAlfaNumericos(nuevoMarca)) {
                    validacionesMarca = true;
                } else {
                    validacionesMarca = false;
                    FacesContext.getCurrentInstance().addMessage("form:nuevoMarca", new FacesMessage("La marca ingresada es incorrecta."));
                }
            } else {
                validacionesMarca = false;
                FacesContext.getCurrentInstance().addMessage("form:nuevoMarca", new FacesMessage("El tamaño minimo permitido es 2 caracteres."));
            }
        }
    }

    public void validarModeloComponente() {
        if (Utilidades.validarNulo(nuevoModelo) && (!nuevoModelo.isEmpty()) && (nuevoModelo.trim().length() > 0)) {
            int tam = nuevoModelo.length();
            if (tam >= 2) {
                if (Utilidades.validarCaracteresAlfaNumericos(nuevoModelo)) {
                    validacionesModelo = true;
                } else {
                    validacionesModelo = false;
                    FacesContext.getCurrentInstance().addMessage("form:nuevoModelo", new FacesMessage("El modelo ingresado es incorrecto."));
                }
            } else {
                validacionesModelo = false;
                FacesContext.getCurrentInstance().addMessage("form:nuevoModelo", new FacesMessage("El tamaño minimo permitido es 2 caracteres."));
            }
        }
    }

    public void validarSerieComponente() {
        if (Utilidades.validarNulo(nuevoSerie) && (!nuevoSerie.isEmpty()) && (nuevoSerie.trim().length() > 0)) {
            int tam = nuevoSerie.length();
            if (tam >= 2) {
                if (Utilidades.validarCaracteresAlfaNumericos(nuevoSerie)) {
                    validacionesSerie = true;
                } else {
                    validacionesSerie = false;
                    FacesContext.getCurrentInstance().addMessage("form:nuevoSerie", new FacesMessage("La serie ingresada es incorrecta."));
                }
            } else {
                validacionesSerie = false;
                FacesContext.getCurrentInstance().addMessage("form:nuevoSerie", new FacesMessage("El tamaño minimo permitido es 2 caracteres."));
            }
        }
    }

    private boolean validarResultadosValidacion() {
        boolean retorno = true;
        if (validacionesCodigo == false) {
            retorno = false;
        }
        if (validacionesNombre == false) {
            retorno = false;
        }
        if (validacionesMarca == false) {
            retorno = false;
        }
        if (validacionesModelo == false) {
            retorno = false;
        }
        if (validacionesSerie == false) {
            retorno = false;
        }
        return retorno;
    }

    /**
     * Metodo encargado de realizar el registro y validaciones de la información
     * del nuevo docente
     */
    public void registrarNuevoComponente() {
        if (validarResultadosValidacion() == true) {
            almacenarNuevoComponenteEnSistema();
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

    public void almacenarNuevoComponenteEnSistema() {
        try {
            Componente nuevaComponente = new Componente();
            nuevaComponente.setEstadocomponente(true);
            nuevaComponente.setCodigocomponente(nuevoCodigo);
            nuevaComponente.setNombrecomponente(nuevoNombre);
            nuevaComponente.setSeriecomponente(nuevoSerie);
            nuevaComponente.setModelocomponente(nuevoModelo);
            nuevaComponente.setMarcacomponente(nuevoMarca);
            nuevaComponente.setEquipotecnologico(equipoTecnologico);
            administrarComponenteEquipoBO.crearComponenteEquipo(nuevaComponente);
        } catch (Exception e) {
            System.out.println("Error AdministrarComponenteEquipoBOInterface almacenarNuevoComponenteEnSistema : " + e.toString());
        }
    }

    public void limpiarFormulario() {
        nuevoCodigo = null;
        nuevoNombre = null;
        nuevoSerie = null;
        nuevoMarca = null;
        nuevoModelo = null;
        fechaDiferida = true;
        //
        validacionesMarca = true;
        validacionesCodigo = false;
        validacionesNombre = false;
        validacionesSerie = true;
        validacionesModelo = true;
        mensajeFormulario = "";
    }

    public String cancelarRegistroComponente() {
        nuevoCodigo = null;
        nuevoNombre = null;
        nuevoSerie = null;
        nuevoMarca = null;
        fechaDiferida = true;
        nuevoModelo = null;
        //
        validacionesMarca = true;
        validacionesCodigo = false;
        validacionesNombre = false;
        validacionesSerie = true;
        validacionesModelo = true;
        activarAceptar = false;
        mensajeFormulario = "N/A";
        activarLimpiar = true;
        colorMensaje = "black";
        activarCasillas = false;
        return "administrarcomponente";
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

    public String getNuevoMarca() {
        return nuevoMarca;
    }

    public void setNuevoMarca(String nuevoMarca) {
        this.nuevoMarca = nuevoMarca;
    }

    public String getNuevoModelo() {
        return nuevoModelo;
    }

    public void setNuevoModelo(String nuevoModelo) {
        this.nuevoModelo = nuevoModelo;
    }

    public String getNuevoSerie() {
        return nuevoSerie;
    }

    public void setNuevoSerie(String nuevoSerie) {
        this.nuevoSerie = nuevoSerie;
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

    public BigInteger getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(BigInteger idEquipo) {
        this.idEquipo = idEquipo;
    }

    public EquipoTecnologico getEquipoTecnologico() {
        return equipoTecnologico;
    }

    public void setEquipoTecnologico(EquipoTecnologico equipoTecnologico) {
        this.equipoTecnologico = equipoTecnologico;
    }

}
