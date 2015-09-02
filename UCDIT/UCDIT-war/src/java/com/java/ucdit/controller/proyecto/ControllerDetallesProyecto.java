/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.controller.proyecto;

import com.java.ucdit.bo.interfaces.proyecto.AdministrarProyectoBOInterface;
import com.java.ucdit.entidades.Cliente;
import com.java.ucdit.entidades.PersonalInterno;
import com.java.ucdit.entidades.PersonalPorProyecto;
import com.java.ucdit.entidades.Proyecto;
import com.java.ucdit.entidades.Supervisor;
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
public class ControllerDetallesProyecto implements Serializable {

    @EJB
    AdministrarProyectoBOInterface administrarProyectoBO;
    //
    private Proyecto proyectoDetalle;
    private BigInteger idProyecto;
    //
    private String editarNombre, editarDescripcion;
    private String editarCostoProyecto;
    private Date editarFechaInicio;
    private List<Cliente> listaCliente;
    private Cliente editarCliente;
    private List<Supervisor> listaSupervisor;
    private Supervisor editarSupervisor;
    private List<PersonalInterno> listaPersonalInterno;
    private List<PersonalPorProyecto> listaPersonalAsociado;
    //
    private boolean validacionesNombre, validacionesDescripcion;
    private boolean validacionesCostoProyecto, validacionesFechaInicio;
    private boolean validacionesCliente, validacionesSupervisor;
    private String mensajeFormulario;
    private String colorMensaje;
    private boolean modificacionRegistro;
    private boolean fechaDiferida;

    public ControllerDetallesProyecto() {
    }

    @PostConstruct
    public void init() {
        colorMensaje = "black";
        mensajeFormulario = "N/A";
    }

    public void recibirIdProyectoDetalle(BigInteger idProyecto) {
        this.idProyecto = idProyecto;
        proyectoDetalle = administrarProyectoBO.obtenerProyectoPorId(this.idProyecto);
        modificacionRegistro = false;
        cargarInformacionRegistro();
    }

    private void cargarInformacionRegistro() {
        if (Utilidades.validarNulo(proyectoDetalle)) {
            fechaDiferida = true;
            editarNombre = proyectoDetalle.getNombreproyecto();
            editarFechaInicio = proyectoDetalle.getFechainicio();
            editarDescripcion = proyectoDetalle.getDescripcionproyecto();
            editarCostoProyecto = String.valueOf(proyectoDetalle.getCostoproyecto());
            editarCliente = proyectoDetalle.getCliente();
            editarSupervisor = proyectoDetalle.getSupervisor();
            listaPersonalAsociado = administrarProyectoBO.obtenerPersonalPorProyectoPorIdProyecto(proyectoDetalle.getIdproyecto());
            //
            validacionesCliente = true;
            validacionesNombre = true;
            validacionesFechaInicio = true;
            validacionesCliente = true;
            validacionesDescripcion = true;
            validacionesCostoProyecto = true;
        }
    }

    private void restaurarRegistro() {
        colorMensaje = "black";
        mensajeFormulario = "N/A";
        modificacionRegistro = false;
        proyectoDetalle = null;
        proyectoDetalle = administrarProyectoBO.obtenerProyectoPorId(this.idProyecto);
        cargarInformacionRegistro();
    }

    public void validarNombreProyecto() {
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

    public void validarFechaInicioProyecto() {
        if (Utilidades.validarNulo(editarFechaInicio)) {
            if (fechaDiferida == true) {
                editarFechaInicio = new Date();
                if (Utilidades.fechaIngresadaCorrecta(editarFechaInicio)) {
                    validacionesFechaInicio = true;
                } else {
                    validacionesFechaInicio = true;
                    FacesContext.getCurrentInstance().addMessage("form:editarFechaInicio", new FacesMessage("La fecha de inicio es incorrecta."));
                }
            } else {
                if (Utilidades.fechaDiferidaIngresadaCorrecta(editarFechaInicio)) {
                    validacionesFechaInicio = true;
                } else {
                    validacionesFechaInicio = true;
                    FacesContext.getCurrentInstance().addMessage("form:editarFechaInicio", new FacesMessage("La fecha de inicio es incorrecta."));
                }
            }
        } else {
            validacionesFechaInicio = true;
            FacesContext.getCurrentInstance().addMessage("form:editarFechaInicio", new FacesMessage("La fecha de inicio es obligatoria."));
        }
        modificacionRegistro = true;
    }

    public void validarClienteProyecto() {
        if (Utilidades.validarNulo(editarCliente)) {
            validacionesCliente = true;
        } else {
            validacionesCliente = false;
            FacesContext.getCurrentInstance().addMessage("form:editarCliente", new FacesMessage("El cliente es obligatorio."));
        }
        modificacionRegistro = true;
    }

    public void validarSupervisorProyecto() {
        if (Utilidades.validarNulo(editarSupervisor)) {
            validacionesSupervisor = true;
        } else {
            validacionesSupervisor = false;
            FacesContext.getCurrentInstance().addMessage("form:editarSupervisor", new FacesMessage("El supervisot es obligatorio."));
        }
        modificacionRegistro = true;
    }

    public void validarDescripcionProyecto() {
        if (Utilidades.validarNulo(editarDescripcion) && (!editarDescripcion.isEmpty()) && (editarDescripcion.trim().length() > 0)) {
            int tam = editarDescripcion.length();
            if (tam >= 20) {
                if ((Utilidades.validarCaracterString(editarDescripcion)) == false) {
                    validacionesDescripcion = false;
                    FacesContext.getCurrentInstance().addMessage("form:editarDescripcion", new FacesMessage("La descripción ingresada es incorrecta."));
                } else {
                    validacionesDescripcion = true;
                }
            } else {
                validacionesDescripcion = false;
                FacesContext.getCurrentInstance().addMessage("form:editarDescripcion", new FacesMessage("La tamaño minimo permitido es 20 caracteres."));
            }
        } else {
            validacionesDescripcion = false;
            FacesContext.getCurrentInstance().addMessage("form:editarDescripcion", new FacesMessage("La descripción es obligatoria."));
        }
        modificacionRegistro = true;
    }

    //1 Costo - 2 Fecha Fin - 3 Fecha Fin Deseada
    public void validarDatosOpcionales(int tipoReg) {
        if (tipoReg == 1) {
            if (Utilidades.validarNulo(editarCostoProyecto) && (!editarCostoProyecto.isEmpty()) && (editarCostoProyecto.trim().length() > 0)) {
                int tam = editarCostoProyecto.length();
                if (tam >= 6) {
                    if (Utilidades.isNumber(editarCostoProyecto)) {
                        validacionesCostoProyecto = true;
                    } else {
                        validacionesCostoProyecto = false;
                        FacesContext.getCurrentInstance().addMessage("form:editarCostoProyecto", new FacesMessage("El costo se encuentra incorrecto."));
                    }
                } else {
                    validacionesCostoProyecto = false;
                    FacesContext.getCurrentInstance().addMessage("form:editarCostoProyecto", new FacesMessage("El tamaño minimo permitido es 5 caracteres."));
                }
            }
        }
        modificacionRegistro = true;
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

    /**
     * Metodo encargado de realizar el registro y validaciones de la información
     * del editar docente
     */
    public void registrarModificacionProyecto() {
        if (modificacionRegistro == true) {
            if (validarResultadosValidacion() == true) {
                almacenarModificacionProyectoEnSistema();
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

    public void almacenarModificacionProyectoEnSistema() {
        try {
            proyectoDetalle.setNombreproyecto(editarNombre);
            proyectoDetalle.setDescripcionproyecto(editarDescripcion);
            proyectoDetalle.setFechainicio(editarFechaInicio);
            if (Utilidades.validarNulo(editarCostoProyecto)) {
                proyectoDetalle.setCostoproyecto(Integer.valueOf(editarCostoProyecto));
            } else {
                proyectoDetalle.setCostoproyecto(Integer.valueOf("0"));
            }
            proyectoDetalle.setSupervisor(editarSupervisor);
            proyectoDetalle.setCliente(editarCliente);
            administrarProyectoBO.editarProyecto(proyectoDetalle);
        } catch (Exception e) {
            System.out.println("Error ControllerRegistrarProyecto almacenarModificacionProyectoEnSistema : " + e.toString());
        }
    }

    public String cancelarRegistroProyecto() {
        limpiarFormularioDirigirPaginas();
        return "administrarproyecto";
    }

    public String dirigiarPaginaInsumoProyecto() {
        limpiarFormularioDirigirPaginas();
        return "administrarinsumoporproyecto";
    }

    public String dirigiarPaginaEquipoProyecto() {
        limpiarFormularioDirigirPaginas();
        return "administrarequipoporproyecto";
    }

    public String dirigiarPaginaAsociarPersonal() {
        limpiarFormularioDirigirPaginas();
        return "asociarpersonalaproyecto";
    }

    private void limpiarFormularioDirigirPaginas() {
        editarNombre = null;
        editarFechaInicio = new Date();
        editarDescripcion = null;
        editarCostoProyecto = "0";
        editarCliente = null;
        editarSupervisor = null;
        //
        fechaDiferida = true;
        validacionesCliente = false;
        validacionesNombre = false;
        validacionesFechaInicio = true;
        validacionesCliente = false;
        validacionesDescripcion = false;
        validacionesCostoProyecto = true;
        listaCliente = null;
        listaPersonalAsociado = null;
        listaSupervisor = null;
        mensajeFormulario = "N/A";
        colorMensaje = "black";
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

    public String getEditarNombre() {
        return editarNombre;
    }

    public void setEditarNombre(String editarNombre) {
        this.editarNombre = editarNombre;
    }

    public String getEditarDescripcion() {
        return editarDescripcion;
    }

    public void setEditarDescripcion(String editarDescripcion) {
        this.editarDescripcion = editarDescripcion;
    }

    public String getEditarCostoProyecto() {
        return editarCostoProyecto;
    }

    public void setEditarCostoProyecto(String editarCostoProyecto) {
        this.editarCostoProyecto = editarCostoProyecto;
    }

    public Date getEditarFechaInicio() {
        return editarFechaInicio;
    }

    public void setEditarFechaInicio(Date editarFechaInicio) {
        this.editarFechaInicio = editarFechaInicio;
    }

    public List<Cliente> getListaCliente() {
        return listaCliente;
    }

    public void setListaCliente(List<Cliente> listaCliente) {
        this.listaCliente = listaCliente;
    }

    public Cliente getEditarCliente() {
        return editarCliente;
    }

    public void setEditarCliente(Cliente editarCliente) {
        this.editarCliente = editarCliente;
    }

    public List<Supervisor> getListaSupervisor() {
        return listaSupervisor;
    }

    public void setListaSupervisor(List<Supervisor> listaSupervisor) {
        this.listaSupervisor = listaSupervisor;
    }

    public Supervisor getEditarSupervisor() {
        return editarSupervisor;
    }

    public void setEditarSupervisor(Supervisor editarSupervisor) {
        this.editarSupervisor = editarSupervisor;
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

    public boolean isModificacionRegistro() {
        return modificacionRegistro;
    }

    public void setModificacionRegistro(boolean modificacionRegistro) {
        this.modificacionRegistro = modificacionRegistro;
    }

    public boolean isFechaDiferida() {
        return fechaDiferida;
    }

    public void setFechaDiferida(boolean fechaDiferida) {
        this.fechaDiferida = fechaDiferida;
    }

}
