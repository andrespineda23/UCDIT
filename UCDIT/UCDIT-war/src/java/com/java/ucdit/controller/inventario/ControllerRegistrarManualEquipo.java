/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.controller.inventario;

import com.java.ucdit.bo.interfaces.inventario.AdministrarManualEquipoBOInterface;
import com.java.ucdit.entidades.ManualEquipo;
import com.java.ucdit.entidades.EquipoTecnologico;
import com.java.ucdit.utilidades.Utilidades;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.math.BigInteger;
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
public class ControllerRegistrarManualEquipo implements Serializable {

    @EJB
    AdministrarManualEquipoBOInterface administrarManualEquipoBO;
    //
    private String nuevoTipo, nuevoNombre, nuevoCodigo, nuevoUbicacion;
    //
    private boolean validacionesTipo, validacionesUbicacion;
    private boolean validacionesNombre, validacionesCodigo, validacionesArchivo;
    private String mensajeFormulario;
    private boolean activarCasillas;
    private String colorMensaje;
    private boolean activarLimpiar;
    private boolean activarAceptar;

    private BigInteger idEquipoTecnologico;
    private EquipoTecnologico equipoTecnologico;
    private final String pathArchivo = "C:\\UCDIT\\Manuales de Equipo\\";
    private String rutaArchivo;
    private Part archivo;
    private boolean activarArchivo;
    private boolean activarUbicacion;

    public ControllerRegistrarManualEquipo() {
    }

    @PostConstruct
    public void init() {
        rutaArchivo = "";
        archivo = null;
        nuevoTipo = "FISICO";
        nuevoNombre = null;
        nuevoCodigo = null;
        activarUbicacion = false;
        activarArchivo = true;
        //
        validacionesTipo = false;
        validacionesNombre = false;
        validacionesUbicacion = false;
        validacionesCodigo = false;
        activarLimpiar = true;
        colorMensaje = "black";
        validacionesArchivo = false;
        activarCasillas = false;
        activarAceptar = false;
        mensajeFormulario = "N/A";
    }

    public void recibirIdEquipoTecnologico(BigInteger idEquipoTecnologico) {
        this.idEquipoTecnologico = idEquipoTecnologico;
        equipoTecnologico = administrarManualEquipoBO.obtenerEquipoTecnologicoPorId(idEquipoTecnologico);
    }

    public void validarTipoManualEquipo() {
        if ("DIGITAL".equalsIgnoreCase(nuevoTipo)) {
            activarArchivo = false;
            activarUbicacion = true;
            validacionesUbicacion = true;
        } else {
            activarUbicacion = false;
            activarArchivo = true;
            validacionesUbicacion = false;
        }
        nuevoUbicacion = null;
    }

    public void validarCodigoManualEquipo() {
        if (Utilidades.validarNulo(nuevoCodigo) && (!nuevoCodigo.isEmpty()) && (nuevoCodigo.trim().length() > 0)) {
            int tam = nuevoCodigo.length();
            if (tam >= 4) {
                if (!Utilidades.validarCaracteresAlfaNumericos(nuevoCodigo)) {
                    validacionesCodigo = false;
                    FacesContext.getCurrentInstance().addMessage("form:nuevoCodigo", new FacesMessage("El codigo ingresado es incorrecto."));
                } else {
                    validacionesCodigo = true;
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

    public void validarUbicacionManualEquipo() {
        if (Utilidades.validarNulo(nuevoUbicacion) && (!nuevoUbicacion.isEmpty()) && (nuevoUbicacion.trim().length() > 0)) {
            int tam = nuevoUbicacion.length();
            if (tam >= 4) {
                if (!Utilidades.validarCaracteresAlfaNumericos(nuevoUbicacion)) {
                    validacionesUbicacion = false;
                    FacesContext.getCurrentInstance().addMessage("form:nuevoUbicacion", new FacesMessage("La ubicación ingresada es incorrecta."));
                } else {
                    validacionesUbicacion = true;
                }
            } else {
                validacionesUbicacion = false;
                FacesContext.getCurrentInstance().addMessage("form:nuevoUbicacion", new FacesMessage("El tamaño minimo permitido es 4 caracteres."));
            }
        } else {
            validacionesUbicacion = false;
            FacesContext.getCurrentInstance().addMessage("form:nuevoUbicacion", new FacesMessage("La ubicación es obligatoria."));
        }
    }

    public void validarNombreManualEquipo() {
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

    private boolean validarNombresValidacion() {
        boolean retorno = true;
        if (validacionesArchivo == false) {
            retorno = false;
        }
        if (validacionesCodigo == false) {
            retorno = false;
        }
        if (validacionesNombre == false) {
            retorno = false;
        }
        if (validacionesUbicacion == false) {
            retorno = false;
        }
        if (validacionesTipo == false) {
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

    public void registrarNuevoManualEquipo() {
        if (validarNombresValidacion() == true) {
            almacenarNuevoManualEquipoEnSistema();
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

    private void almacenarNuevoManualEquipoEnSistema() {
        try {
            ManualEquipo nuevaManualEquipo = new ManualEquipo();
            nuevaManualEquipo.setTipomanual(nuevoTipo);
            nuevaManualEquipo.setNombremanual(nuevoNombre);
            nuevaManualEquipo.setCodigomanual(nuevoCodigo);
            nuevaManualEquipo.setEquipotecnologico(equipoTecnologico);
            if ("DIGITAL".equalsIgnoreCase(nuevoTipo)) {
                cargarManualAServidor();
                String filename = getFilename(archivo);
                rutaArchivo = pathArchivo + filename;
                nuevaManualEquipo.setUbicacionmanual(rutaArchivo);
            } else {
                nuevaManualEquipo.setUbicacionmanual(nuevoUbicacion);
            }
            administrarManualEquipoBO.crearManualEquipo(nuevaManualEquipo);
        } catch (Exception e) {
            System.out.println("Error ControllerRegistrarManualEquipo almacenarNuevoManualEquipoEnSistema : " + e.toString());
        }
    }

    private void cargarManualAServidor() throws FileNotFoundException, IOException, MessagingException {
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
        nuevoTipo = "FISICO";;
        nuevoCodigo = null;
        validacionesArchivo = false;
        rutaArchivo = "";
        archivo = null;
        nuevoNombre = null;
        nuevoUbicacion = null;
        activarUbicacion = false;
        activarArchivo = true;
        //
        validacionesTipo = false;
        validacionesCodigo = false;
        validacionesNombre = false;
        validacionesUbicacion = false;
        mensajeFormulario = "";
    }

    public String cancelarRegistroManualEquipo() {
        nuevoTipo = "FISICO";;
        nuevoCodigo = null;
        nuevoNombre = null;
        validacionesArchivo = false;
        nuevoUbicacion = null;
        //
        activarUbicacion = false;
        activarArchivo = true;
        rutaArchivo = "";
        archivo = null;
        validacionesTipo = false;
        validacionesCodigo = false;
        validacionesNombre = false;
        validacionesUbicacion = false;
        activarAceptar = false;
        mensajeFormulario = "N/A";
        activarLimpiar = true;
        colorMensaje = "black";
        idEquipoTecnologico = null;
        equipoTecnologico = null;
        activarCasillas = false;
        return "administrarmanualequipo";
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
    public String getNuevoTipo() {
        return nuevoTipo;
    }

    public void setNuevoTipo(String nuevoTipo) {
        this.nuevoTipo = nuevoTipo;
    }

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

    public String getNuevoUbicacion() {
        return nuevoUbicacion;
    }

    public void setNuevoUbicacion(String nuevoUbicacion) {
        this.nuevoUbicacion = nuevoUbicacion;
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

    public BigInteger getIdEquipoTecnologico() {
        return idEquipoTecnologico;
    }

    public void setIdEquipoTecnologico(BigInteger idEquipoTecnologico) {
        this.idEquipoTecnologico = idEquipoTecnologico;
    }

    public EquipoTecnologico getEquipoTecnologico() {
        return equipoTecnologico;
    }

    public void setEquipoTecnologico(EquipoTecnologico equipoTecnologico) {
        this.equipoTecnologico = equipoTecnologico;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public boolean isActivarArchivo() {
        return activarArchivo;
    }

    public void setActivarArchivo(boolean activarArchivo) {
        this.activarArchivo = activarArchivo;
    }

    public boolean isActivarUbicacion() {
        return activarUbicacion;
    }

    public void setActivarUbicacion(boolean activarUbicacion) {
        this.activarUbicacion = activarUbicacion;
    }

    public Part getArchivo() {
        return archivo;
    }

    public void setArchivo(Part archivo) {
        this.archivo = archivo;
    }

}
