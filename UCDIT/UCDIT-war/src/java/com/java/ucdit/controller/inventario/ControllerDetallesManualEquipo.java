/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.controller.inventario;

import com.java.ucdit.bo.interfaces.inventario.AdministrarManualEquipoBOInterface;
import com.java.ucdit.entidades.ManualEquipo;
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
public class ControllerDetallesManualEquipo implements Serializable {

    @EJB
    AdministrarManualEquipoBOInterface administrarManualEquipoBO;
    //
    private String editarTipo, editarNombre, editarCodigo, editarUbicacion;
    //
    private boolean validacionesTipo, validacionesUbicacion;
    private boolean validacionesNombre, validacionesCodigo, validacionesArchivo;
    private String mensajeFormulario;
    private String colorMensaje;
    private BigInteger idManualEquipo;
    private ManualEquipo manualEquipoDetalle;
    private final String pathArchivo = "C:\\UCDIT\\Manuales de Equipo\\";
    private String rutaArchivo;
    private Part archivo;
    private boolean activarArchivo;
    private boolean activarUbicacion;
    private boolean modificacionRegistro;
    private boolean modificacionArchivo;

    public ControllerDetallesManualEquipo() {
    }

    @PostConstruct
    public void init() {
        colorMensaje = "black";
        mensajeFormulario = "N/A";
    }

    public void recibirIdManualEquipo(BigInteger idManualEquipo) {
        this.idManualEquipo = idManualEquipo;
        manualEquipoDetalle = administrarManualEquipoBO.obtenerManualEquipoPorId(idManualEquipo);
        cargarDatosRegistro();
    }

    private void cargarDatosRegistro() {
        if (Utilidades.validarNulo(manualEquipoDetalle)) {
            modificacionRegistro = false;
            rutaArchivo = "";
            archivo = null;
            editarTipo = manualEquipoDetalle.getTipomanual();
            editarNombre = manualEquipoDetalle.getNombremanual();
            editarCodigo = manualEquipoDetalle.getCodigomanual();
            editarUbicacion = manualEquipoDetalle.getUbicacionmanual();
            //
            validacionesTipo = true;
            validacionesNombre = true;
            validacionesUbicacion = true;
            validacionesCodigo = true;
            validacionesArchivo = true;
            if ("DIGITAL".equalsIgnoreCase(editarTipo)) {
                activarArchivo = false;
                activarUbicacion = true;
            } else {
                activarUbicacion = false;
                activarArchivo = true;
            }
            modificacionArchivo = false;
        }
    }

    public void validarTipoManualEquipo() {
        if ("DIGITAL".equalsIgnoreCase(editarTipo)) {
            activarArchivo = false;
            activarUbicacion = true;
            validacionesUbicacion = true;
        } else {
            activarUbicacion = false;
            activarArchivo = true;
            validacionesUbicacion = false;
        }
        editarUbicacion = null;
        modificacionRegistro = true;
    }

    public void validarCodigoManualEquipo() {
        if (Utilidades.validarNulo(editarCodigo) && (!editarCodigo.isEmpty()) && (editarCodigo.trim().length() > 0)) {
            int tam = editarCodigo.length();
            if (tam >= 4) {
                if (!Utilidades.validarCaracteresAlfaNumericos(editarCodigo)) {
                    validacionesCodigo = false;
                    FacesContext.getCurrentInstance().addMessage("form:editarCodigo", new FacesMessage("El codigo ingresado es incorrecto."));
                } else {
                    validacionesCodigo = true;
                }
            } else {
                validacionesCodigo = false;
                FacesContext.getCurrentInstance().addMessage("form:editarCodigo", new FacesMessage("El tamaño minimo permitido es 4 caracteres."));
            }
        } else {
            validacionesCodigo = false;
            FacesContext.getCurrentInstance().addMessage("form:editarCodigo", new FacesMessage("El codigo es obligatorio."));
        }
        modificacionRegistro = true;
    }

    public void validarUbicacionManualEquipo() {
        if (Utilidades.validarNulo(editarUbicacion) && (!editarUbicacion.isEmpty()) && (editarUbicacion.trim().length() > 0)) {
            int tam = editarUbicacion.length();
            if (tam >= 4) {
                if (!Utilidades.validarCaracteresAlfaNumericos(editarUbicacion)) {
                    validacionesUbicacion = false;
                    FacesContext.getCurrentInstance().addMessage("form:editarUbicacion", new FacesMessage("La ubicación ingresada es incorrecta."));
                } else {
                    validacionesUbicacion = true;
                }
            } else {
                validacionesUbicacion = false;
                FacesContext.getCurrentInstance().addMessage("form:editarUbicacion", new FacesMessage("El tamaño minimo permitido es 4 caracteres."));
            }
        } else {
            validacionesUbicacion = false;
            FacesContext.getCurrentInstance().addMessage("form:editarUbicacion", new FacesMessage("La ubicación es obligatoria."));
        }
        modificacionRegistro = true;
    }

    public void validarNombreManualEquipo() {
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
        modificacionArchivo = true;
    }

    public void registrarNuevoManualEquipo() {
        if (modificacionRegistro == true) {
            if (validarNombresValidacion() == true) {
                almacenarNuevoManualEquipoEnSistema();
                recibirIdManualEquipo(idManualEquipo);
                colorMensaje = "green";
                mensajeFormulario = "El formulario ha sido ingresado con exito.";
            } else {
                colorMensaje = "red";
                mensajeFormulario = "Existen errores en el formulario, por favor corregir para continuar.";
            }
        } else {
            colorMensaje = "red";
            mensajeFormulario = "No existen modificaciones para ser almacenadas.";
        }
    }

    private void almacenarNuevoManualEquipoEnSistema() {
        try {
            ManualEquipo nuevaManualEquipo = new ManualEquipo();
            nuevaManualEquipo.setTipomanual(editarTipo);
            nuevaManualEquipo.setNombremanual(editarNombre);
            nuevaManualEquipo.setCodigomanual(editarCodigo);
            if ("DIGITAL".equalsIgnoreCase(editarTipo)) {
                cargarManualAServidor();
                String filename = getFilename(archivo);
                rutaArchivo = pathArchivo + filename;
                nuevaManualEquipo.setUbicacionmanual(rutaArchivo);
            } else {
                nuevaManualEquipo.setUbicacionmanual(editarUbicacion);
            }
            administrarManualEquipoBO.crearManualEquipo(nuevaManualEquipo);
        } catch (Exception e) {
            System.out.println("Error ControllerRegistrarManualEquipo almacenarNuevoManualEquipoEnSistema : " + e.toString());
        }
    }

    private void cargarManualAServidor() throws FileNotFoundException, IOException, MessagingException {
        if (modificacionArchivo == true) {
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
    }

    public String cancelarRegistroManualEquipo() {
        editarTipo = "FISICO";;
        editarCodigo = null;
        modificacionArchivo = false;
        editarNombre = null;
        validacionesArchivo = false;
        editarUbicacion = null;
        //
        activarUbicacion = false;
        activarArchivo = true;
        rutaArchivo = "";
        archivo = null;
        validacionesTipo = false;
        validacionesCodigo = false;
        validacionesNombre = false;
        validacionesUbicacion = false;
        mensajeFormulario = "N/A";
        colorMensaje = "black";
        idManualEquipo = null;
        manualEquipoDetalle = null;
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

    //GET-SET
    public String getEditarTipo() {
        return editarTipo;
    }

    public void setEditarTipo(String editarTipo) {
        this.editarTipo = editarTipo;
    }

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

    public String getEditarUbicacion() {
        return editarUbicacion;
    }

    public void setEditarUbicacion(String editarUbicacion) {
        this.editarUbicacion = editarUbicacion;
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

    public BigInteger getIdManualEquipo() {
        return idManualEquipo;
    }

    public void setIdManualEquipo(BigInteger idManualEquipo) {
        this.idManualEquipo = idManualEquipo;
    }

    public ManualEquipo getManualEquipoDetalle() {
        return manualEquipoDetalle;
    }

    public void setManualEquipoDetalle(ManualEquipo manualEquipoDetalle) {
        this.manualEquipoDetalle = manualEquipoDetalle;
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

    public boolean isModificacionRegistro() {
        return modificacionRegistro;
    }

    public void setModificacionRegistro(boolean modificacionRegistro) {
        this.modificacionRegistro = modificacionRegistro;
    }

    public boolean isModificacionArchivo() {
        return modificacionArchivo;
    }

    public void setModificacionArchivo(boolean modificacionArchivo) {
        this.modificacionArchivo = modificacionArchivo;
    }

}
