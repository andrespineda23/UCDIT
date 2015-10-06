/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.controller.proyecto;

import com.java.ucdit.bo.interfaces.proyecto.AdministrarActaReunionBOInterface;
import com.java.ucdit.entidades.ActaReunion;
import com.java.ucdit.entidades.Proyecto;
import com.java.ucdit.utilidades.Utilidades;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.mail.MessagingException;
import javax.servlet.http.Part;

/**
 *
 * @author ELECTRONICA
 */
@ManagedBean
@SessionScoped
public class ControllerRegistrarActaReunion implements Serializable {

    @EJB
    AdministrarActaReunionBOInterface administrarActaReunionBO;
    //
    private String nuevoObjetivo, nuevoResultado, nuevoPersonal;
    private Date nuevoFechaRegistro;
    //
    private boolean validacionesObjetivo, validacionesFechaRegistro;
    private boolean validacionesResultado, validacionesPersonal, validacionesArchivo;
    private String mensajeFormulario;
    private boolean activarCasillas;
    private String colorMensaje;
    private boolean activarLimpiar;
    private boolean activarAceptar;
    private boolean fechaDiferida;
    private BigInteger idProyecto;
    private Proyecto proyecto;
    private final String pathArchivo = "C:\\UCDIT\\Actas de Reunion\\";
    private String rutaArchivo;
    private Part archivo;

    public ControllerRegistrarActaReunion() {
    }

    @PostConstruct
    public void init() {
        rutaArchivo = "";
        archivo = null;
        fechaDiferida = true;
        nuevoObjetivo = null;
        nuevoResultado = null;
        nuevoPersonal = null;
        nuevoFechaRegistro = new Date();
        //
        validacionesObjetivo = false;
        validacionesFechaRegistro = true;
        validacionesResultado = false;
        validacionesPersonal = false;
        activarLimpiar = true;
        colorMensaje = "black";
        validacionesArchivo = false;
        activarCasillas = false;
        activarAceptar = false;
        mensajeFormulario = "N/A";
    }

    public void recibirIdProyecto(BigInteger idProyecto) {
        this.idProyecto = idProyecto;
        proyecto = administrarActaReunionBO.obtenerProyectoPorId(idProyecto);
    }

    public void validarObjetivoActaReunion() {
        if (Utilidades.validarNulo(nuevoObjetivo) && (!nuevoObjetivo.isEmpty()) && (nuevoObjetivo.trim().length() > 0)) {
            int tam = nuevoObjetivo.length();
            if (tam >= 4) {
                if (!Utilidades.validarCaracteresAlfaNumericos(nuevoObjetivo)) {
                    validacionesObjetivo = false;
                    FacesContext.getCurrentInstance().addMessage("form:nuevoObjetivo", new FacesMessage("El objetivo ingresado es incorrecto."));
                } else {
                    validacionesObjetivo = true;
                }
            } else {
                validacionesObjetivo = false;
                FacesContext.getCurrentInstance().addMessage("form:nuevoObjetivo", new FacesMessage("El tamaño minimo permitido es 4 caracteres."));
            }
        } else {
            validacionesObjetivo = false;
            FacesContext.getCurrentInstance().addMessage("form:nuevoObjetivo", new FacesMessage("El objetivo es obligatorio."));
        }
    }

    public void validarPersonalActaReunion() {
        if (Utilidades.validarNulo(nuevoPersonal) && (!nuevoPersonal.isEmpty()) && (nuevoPersonal.trim().length() > 0)) {
            int tam = nuevoPersonal.length();
            if (tam >= 4) {
                if (!Utilidades.validarCaracteresAlfaNumericos(nuevoPersonal)) {
                    validacionesPersonal = false;
                    FacesContext.getCurrentInstance().addMessage("form:nuevoPersonal", new FacesMessage("El personal ingresado es incorrecto."));
                } else {
                    validacionesPersonal = true;
                }
            } else {
                validacionesPersonal = false;
                FacesContext.getCurrentInstance().addMessage("form:nuevoPersonal", new FacesMessage("El tamaño minimo permitido es 4 caracteres."));
            }
        } else {
            validacionesPersonal = false;
            FacesContext.getCurrentInstance().addMessage("form:nuevoPersonal", new FacesMessage("El personal es obligatorio."));
        }
    }

    public void validarResultadoActaReunion() {
        if (Utilidades.validarNulo(nuevoResultado) && (!nuevoResultado.isEmpty()) && (nuevoResultado.trim().length() > 0)) {
            int tam = nuevoResultado.length();
            if (tam >= 4) {
                if (!Utilidades.validarCaracteresAlfaNumericos(nuevoResultado)) {
                    validacionesResultado = false;
                    FacesContext.getCurrentInstance().addMessage("form:nuevoResultado", new FacesMessage("El resultado ingresado es incorrecto."));
                } else {
                    validacionesResultado = true;
                }
            } else {
                validacionesResultado = false;
                FacesContext.getCurrentInstance().addMessage("form:nuevoResultado", new FacesMessage("El tamaño minimo permitido es 4 caracteres."));
            }
        } else {
            validacionesResultado = false;
            FacesContext.getCurrentInstance().addMessage("form:nuevoResultado", new FacesMessage("El resultado es obligatorio."));
        }
    }

    public void validarFechaRegistroActaReunion() {
        if (Utilidades.validarNulo(nuevoFechaRegistro)) {
            if (fechaDiferida == true) {
                nuevoFechaRegistro = new Date();
                if (Utilidades.fechaIngresadaCorrecta(nuevoFechaRegistro)) {
                    validacionesFechaRegistro = true;
                } else {
                    validacionesFechaRegistro = true;
                    FacesContext.getCurrentInstance().addMessage("form:nuevoFechaRegistro", new FacesMessage("La fecha de inicio es incorrecta."));
                }
            } else {
                if (Utilidades.fechaDiferidaIngresadaCorrecta(nuevoFechaRegistro)) {
                    validacionesFechaRegistro = true;
                } else {
                    validacionesFechaRegistro = true;
                    FacesContext.getCurrentInstance().addMessage("form:nuevoFechaRegistro", new FacesMessage("La fecha de inicio es incorrecta."));
                }
            }
        } else {
            validacionesFechaRegistro = true;
            FacesContext.getCurrentInstance().addMessage("form:nuevoFechaRegistro", new FacesMessage("La fecha de inicio es obligatoria."));
        }
    }

    private boolean validarResultadosValidacion() {
        boolean retorno = true;
        if (validacionesFechaRegistro == false) {
            retorno = false;
        }
        if (validacionesPersonal == false) {
            retorno = false;
        }
        if (validacionesResultado == false) {
            retorno = false;
        }
        if (validacionesObjetivo == false) {
            retorno = false;
        }
        if (validacionesArchivo == false) {
            retorno = false;
        }
        return retorno;
    }

    public void actualizarArchivoSeleccionado() throws MessagingException {
        if (Utilidades.validarNulo(archivo)) {
            String filename = getFilename(archivo);
            String rutaArchivoInicial = pathArchivo + filename;
            String extension = "";
            int i = rutaArchivoInicial.lastIndexOf('.');
            if (i > 0) {
                extension = rutaArchivoInicial.substring(i + 1);
            }
            if ("pdf".equals(extension)) {
                validacionesArchivo = true;
            } else {
                FacesContext.getCurrentInstance().addMessage("form:archivo", new FacesMessage("Formato incorrecto. Solo se permite archivos PDF."));
                validacionesArchivo = false;
            }
        } else {
            FacesContext.getCurrentInstance().addMessage("form:archivo", new FacesMessage("El archivo es obligatorio."));
            validacionesArchivo = false;
        }
    }

    public void registrarNuevoActaReunion() {
        if (validarResultadosValidacion() == true) {
            almacenarNuevoActaReunionEnSistema();
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

    private void almacenarNuevoActaReunionEnSistema() {
        try {
            cargarActaAServidor();
            ActaReunion nuevaActaReunion = new ActaReunion();
            nuevaActaReunion.setObjetivosacumplir(nuevoObjetivo);
            nuevaActaReunion.setResultadoreunion(nuevoResultado);
            nuevaActaReunion.setPersonalinvolucrado(nuevoPersonal);
            nuevaActaReunion.setFechareunion(nuevoFechaRegistro);
            nuevaActaReunion.setProyecto(proyecto);
            String filename = getFilename(archivo);
            rutaArchivo = pathArchivo + filename;
            nuevaActaReunion.setUbicacionarchivo(rutaArchivo);
            administrarActaReunionBO.crearActaReunion(nuevaActaReunion);
        } catch (Exception e) {
            System.out.println("Error ControllerRegistrarActaReunion almacenarNuevoActaReunionEnSistema : " + e.toString());
        }
    }

    private void cargarActaAServidor() throws FileNotFoundException, IOException, MessagingException {
        if (Utilidades.validarNulo(archivo)) {
            String filename = getFilename(archivo);
            rutaArchivo = pathArchivo + filename;
            String extension = "";
            int i = rutaArchivo.lastIndexOf('.');
            if (i > 0) {
                extension = rutaArchivo.substring(i + 1);
            }
            if ("pdf".equals(extension)) {
                InputStream is = archivo.getInputStream();
                FileOutputStream os = new FileOutputStream(rutaArchivo);
                int ch = is.read();
                while (ch != -1) {
                    os.write(ch);
                    ch = is.read();
                }
                os.close();
            }
        }
    }

    public void limpiarFormulario() {
        nuevoObjetivo = null;
        nuevoPersonal = null;
        validacionesArchivo = false;
        rutaArchivo = "";
        archivo = null;
        nuevoResultado = null;
        nuevoFechaRegistro = new Date();
        fechaDiferida = true;
        //
        validacionesObjetivo = false;
        validacionesPersonal = false;
        validacionesResultado = false;
        validacionesFechaRegistro = true;
        mensajeFormulario = "";
    }

    public String cancelarRegistroActaReunion() {
        nuevoObjetivo = null;
        nuevoPersonal = null;
        nuevoResultado = null;
        validacionesArchivo = false;
        nuevoFechaRegistro = new Date();
        fechaDiferida = true;
        //
        rutaArchivo = "";
        archivo = null;
        validacionesObjetivo = false;
        validacionesPersonal = false;
        validacionesResultado = false;
        validacionesFechaRegistro = true;
        activarAceptar = false;
        mensajeFormulario = "N/A";
        activarLimpiar = true;
        colorMensaje = "black";
        idProyecto = null;
        proyecto = null;
        activarCasillas = false;
        return "administraractareunion";
    }

    private static String getFilename(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
            }
        }
        return null;
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
    public String getNuevoObjetivo() {
        return nuevoObjetivo;
    }

    public void setNuevoObjetivo(String nuevoObjetivo) {
        this.nuevoObjetivo = nuevoObjetivo;
    }

    public String getNuevoResultado() {
        return nuevoResultado;
    }

    public void setNuevoResultado(String nuevoResultado) {
        this.nuevoResultado = nuevoResultado;
    }

    public String getNuevoPersonal() {
        return nuevoPersonal;
    }

    public void setNuevoPersonal(String nuevoPersonal) {
        this.nuevoPersonal = nuevoPersonal;
    }

    public Date getNuevoFechaRegistro() {
        return nuevoFechaRegistro;
    }

    public void setNuevoFechaRegistro(Date nuevoFechaRegistro) {
        this.nuevoFechaRegistro = nuevoFechaRegistro;
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

    public BigInteger getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(BigInteger idProyecto) {
        this.idProyecto = idProyecto;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public Part getArchivo() {
        return archivo;
    }

    public void setArchivo(Part archivo) {
        this.archivo = archivo;
    }

}
