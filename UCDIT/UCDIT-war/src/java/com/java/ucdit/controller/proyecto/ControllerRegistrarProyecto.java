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
import com.java.ucdit.entidades.Proyecto;
import com.java.ucdit.entidades.Supervisor;
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
public class ControllerRegistrarProyecto implements Serializable {

    @EJB
    AdministrarProyectoBOInterface administrarProyectoBO;
    //
    private String nuevoNombre, nuevoDescripcion;
    private String nuevoCostoProyecto;
    private Date nuevoFechaInicio;
    private List<Cliente> listaCliente;
    private Cliente nuevoCliente;
    private List<Supervisor> listaSupervisor;
    private Supervisor nuevoSupervisor;
    private List<PersonalInterno> listaPersonalInterno;
    private List<AsociacionPersonaProyecto> listaPersonalAsociado;
    //
    private boolean validacionesNombre, validacionesDescripcion;
    private boolean validacionesCostoProyecto, validacionesFechaInicio;
    private boolean validacionesCliente, validacionesSupervisor;
    private String mensajeFormulario;
    private boolean activarCasillas;
    private String colorMensaje;
    private boolean activarLimpiar;
    private boolean activarAceptar;
    private boolean fechaDiferida;

    public ControllerRegistrarProyecto() {
    }

    @PostConstruct
    public void init() {
        fechaDiferida = true;
        nuevoNombre = null;
        nuevoFechaInicio = new Date();
        nuevoDescripcion = null;
        nuevoCostoProyecto = "0";
        nuevoCliente = null;
        nuevoSupervisor = null;
        //
        validacionesCliente = false;
        validacionesNombre = false;
        validacionesFechaInicio = true;
        validacionesCliente = false;
        validacionesDescripcion = false;
        validacionesCostoProyecto = true;
        activarLimpiar = true;
        colorMensaje = "black";
        activarCasillas = false;
        activarAceptar = false;
        mensajeFormulario = "N/A";
        iniciarAsociacionPersonaProyecto();
    }

    private void iniciarAsociacionPersonaProyecto() {
        listaPersonalAsociado = new ArrayList<AsociacionPersonaProyecto>();
        listaPersonalInterno = administrarProyectoBO.obtenerPersonalInternoRegistrado();
        if (null != listaPersonalInterno) {
            for (int i = 0; i < listaPersonalInterno.size(); i++) {
                AsociacionPersonaProyecto nuevo = new AsociacionPersonaProyecto();
                nuevo.setPersonalInterno(listaPersonalInterno.get(i));
                nuevo.setActivo(false);
                listaPersonalAsociado.add(nuevo);
            }
        }
    }

    public void validarNombreProyecto() {
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

    public void validarFechaInicioProyecto() {
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

    public void validarClienteProyecto() {
        if (Utilidades.validarNulo(nuevoCliente)) {
            validacionesCliente = true;
        } else {
            validacionesCliente = false;
            FacesContext.getCurrentInstance().addMessage("form:nuevoCliente", new FacesMessage("El cliente es obligatorio."));
        }
    }

    public void validarSupervisorProyecto() {
        if (Utilidades.validarNulo(nuevoSupervisor)) {
            validacionesSupervisor = true;
        } else {
            validacionesSupervisor = false;
            FacesContext.getCurrentInstance().addMessage("form:nuevoSupervisor", new FacesMessage("El supervisot es obligatorio."));
        }
    }

    public void validarDescripcionProyecto() {
        if (Utilidades.validarNulo(nuevoDescripcion) && (!nuevoDescripcion.isEmpty()) && (nuevoDescripcion.trim().length() > 0)) {
            int tam = nuevoDescripcion.length();
            if (tam >= 20) {
                if ((Utilidades.validarCaracterString(nuevoDescripcion)) == false) {
                    validacionesDescripcion = false;
                    FacesContext.getCurrentInstance().addMessage("form:nuevoDescripcion", new FacesMessage("La descripción ingresada es incorrecta."));
                } else {
                    validacionesDescripcion = true;
                }
            } else {
                validacionesDescripcion = false;
                FacesContext.getCurrentInstance().addMessage("form:nuevoDescripcion", new FacesMessage("La tamaño minimo permitido es 20 caracteres."));
            }
        } else {
            validacionesDescripcion = false;
            FacesContext.getCurrentInstance().addMessage("form:nuevoDescripcion", new FacesMessage("La descripción es obligatoria."));
        }
    }

    //1 Costo - 2 Fecha Fin - 3 Fecha Fin Deseada
    public void validarDatosOpcionales(int tipoReg) {
        if (tipoReg == 1) {
            if (Utilidades.validarNulo(nuevoCostoProyecto) && (!nuevoCostoProyecto.isEmpty()) && (nuevoCostoProyecto.trim().length() > 0)) {
                int tam = nuevoCostoProyecto.length();
                if (tam > 5) {
                    if (Utilidades.isNumber(nuevoCostoProyecto)) {
                        validacionesCostoProyecto = true;
                    } else {
                        validacionesCostoProyecto = false;
                        FacesContext.getCurrentInstance().addMessage("form:nuevoCostoProyecto", new FacesMessage("El costo se encuentra incorrecto."));
                    }
                } else {
                    validacionesCostoProyecto = false;
                    FacesContext.getCurrentInstance().addMessage("form:nuevoCostoProyecto", new FacesMessage("El tamaño minimo permitido es 6 caracteres."));
                }
            }
        }
    }

    private boolean validarResultadosValidacion() {
        boolean retorno = true;
        if (validacionesCliente == false) {
            retorno = false;
        }
        if (validacionesFechaInicio == false) {
            retorno = false;
        }
        if (validacionesNombre == false) {
            retorno = false;
        }
        if (validacionesDescripcion == false) {
            retorno = false;
        }
        if (validacionesCostoProyecto == false) {
            retorno = false;
        }
        if (validacionesSupervisor == false) {
            retorno = false;
        }
        return retorno;
    }

    private boolean validarPersonalAsociado() {
        boolean retorno = false;
        if (null != listaPersonalAsociado) {
            for (int i = 0; i < listaPersonalAsociado.size(); i++) {
                if (listaPersonalAsociado.get(i).isActivo() == true) {
                    retorno = true;
                }
            }
        }
        return retorno;
    }

    /**
     * Metodo encargado de realizar el registro y validaciones de la información
     * del nuevo docente
     */
    public void registrarNuevoProyecto() {
        if (validarResultadosValidacion() == true) {
            if (validarPersonalAsociado() == true) {
                almacenarNuevoProyectoEnSistema();
                limpiarFormulario();
                activarLimpiar = false;
                activarAceptar = true;
                activarCasillas = true;
                colorMensaje = "green";
                mensajeFormulario = "El formulario ha sido ingresado con exito.";
            } else {
                colorMensaje = "red";
                mensajeFormulario = "Es necesario asociar un personal interno como minimo al proyecto.";
            }
        } else {
            colorMensaje = "red";
            mensajeFormulario = "Existen errores en el formulario, por favor corregir para continuar.";
        }
    }

    public void almacenarNuevoProyectoEnSistema() {
        try {
            Proyecto nuevaProyecto = new Proyecto();
            nuevaProyecto.setNombreproyecto(nuevoNombre);
            nuevaProyecto.setDescripcionproyecto(nuevoDescripcion);
            nuevaProyecto.setFechainicio(nuevoFechaInicio);
            if (Utilidades.validarNulo(nuevoCostoProyecto)) {
                nuevaProyecto.setCostoproyecto(Integer.valueOf(nuevoCostoProyecto));
            } else {
                nuevaProyecto.setCostoproyecto(Integer.valueOf("0"));
            }
            nuevaProyecto.setSupervisor(nuevoSupervisor);
            nuevaProyecto.setCliente(nuevoCliente);
            nuevaProyecto.setEstadoproyecto(true);
            List<PersonalInterno> personal = cargarPersonalAsociadoAProyecto();
            administrarProyectoBO.crearProyecto(nuevaProyecto, personal);
        } catch (Exception e) {
            System.out.println("Error ControllerRegistrarProyecto almacenarNuevoProyectoEnSistema : " + e.toString());
        }
    }

    private List<PersonalInterno> cargarPersonalAsociadoAProyecto() {
        List<PersonalInterno> lista = new ArrayList<PersonalInterno>();
        for (int i = 0; i < listaPersonalAsociado.size(); i++) {
            if (listaPersonalAsociado.get(i).isActivo() == true) {
                lista.add(listaPersonalAsociado.get(i).getPersonalInterno());
            }
        }
        return lista;
    }

    public void limpiarFormulario() {
        nuevoNombre = null;
        nuevoFechaInicio = new Date();
        nuevoDescripcion = null;
        fechaDiferida = true;
        nuevoCostoProyecto = "0";
        nuevoCliente = null;
        nuevoSupervisor = null;
        //
        validacionesCliente = false;
        validacionesNombre = false;
        validacionesFechaInicio = true;
        validacionesCliente = false;
        validacionesDescripcion = false;
        validacionesCostoProyecto = true;
        mensajeFormulario = "";
        iniciarAsociacionPersonaProyecto();
    }

    public String cancelarRegistroProyecto() {
        nuevoNombre = null;
        nuevoFechaInicio = new Date();
        nuevoDescripcion = null;
        fechaDiferida = true;
        nuevoCostoProyecto = "0";
        nuevoCliente = null;
        nuevoSupervisor = null;
        //
        validacionesCliente = false;
        validacionesNombre = false;
        validacionesFechaInicio = true;
        validacionesCliente = false;
        validacionesDescripcion = false;
        validacionesCostoProyecto = true;
        activarAceptar = false;
        listaCliente = null;
        listaSupervisor = null;
        mensajeFormulario = "N/A";
        activarLimpiar = true;
        colorMensaje = "black";
        activarCasillas = false;
        return "administrarproyecto";
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

    public String getNuevoDescripcion() {
        return nuevoDescripcion;
    }

    public void setNuevoDescripcion(String nuevoDescripcion) {
        this.nuevoDescripcion = nuevoDescripcion;
    }

    public String getNuevoCostoProyecto() {
        return nuevoCostoProyecto;
    }

    public void setNuevoCostoProyecto(String nuevoCostoProyecto) {
        this.nuevoCostoProyecto = nuevoCostoProyecto;
    }

    public Date getNuevoFechaInicio() {
        return nuevoFechaInicio;
    }

    public void setNuevoFechaInicio(Date nuevoFechaInicio) {
        this.nuevoFechaInicio = nuevoFechaInicio;
    }

    public List<Cliente> getListaCliente() {
        if (null == listaCliente) {
            listaCliente = administrarProyectoBO.obtenerClientesRegistrados();
        }
        return listaCliente;
    }

    public void setListaCliente(List<Cliente> listaCliente) {
        this.listaCliente = listaCliente;
    }

    public Cliente getNuevoCliente() {
        return nuevoCliente;
    }

    public void setNuevoCliente(Cliente nuevoCliente) {
        this.nuevoCliente = nuevoCliente;
    }

    public List<Supervisor> getListaSupervisor() {
        if (null == listaSupervisor) {
            listaSupervisor = administrarProyectoBO.obtenerSupervisoresRegistrados();
        }
        return listaSupervisor;
    }

    public void setListaSupervisor(List<Supervisor> listaSupervisor) {
        this.listaSupervisor = listaSupervisor;
    }

    public Supervisor getNuevoSupervisor() {
        return nuevoSupervisor;
    }

    public void setNuevoSupervisor(Supervisor nuevoSupervisor) {
        this.nuevoSupervisor = nuevoSupervisor;
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

    public List<AsociacionPersonaProyecto> getListaPersonalAsociado() {
        return listaPersonalAsociado;
    }

    public void setListaPersonalAsociado(List<AsociacionPersonaProyecto> listaPersonalAsociado) {
        this.listaPersonalAsociado = listaPersonalAsociado;
    }

    public boolean isFechaDiferida() {
        return fechaDiferida;
    }

    public void setFechaDiferida(boolean fechaDiferida) {
        this.fechaDiferida = fechaDiferida;
    }

}
