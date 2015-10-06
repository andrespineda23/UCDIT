/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.controller.trabajo;

import com.java.ucdit.bo.interfaces.trabajo.AdministrarInformePersonalBOInterface;
import com.java.ucdit.entidades.InformePersonal;
import com.java.ucdit.entidades.PersonalInterno;
import com.java.ucdit.utilidades.UsuarioLogin;
import com.java.ucdit.utilidades.Utilidades;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
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
public class ControllerRegistrarInformePersonal implements Serializable {

    @EJB
    AdministrarInformePersonalBOInterface administrarInformePersonalBO;
    //
    private String nuevoNombre;
    private Date nuevoFechaRegistro;
    //
    private boolean validacionesNombre;
    private boolean validacionesArchivo;
    private String mensajeFormulario;
    private boolean activarCasillas;
    private String colorMensaje;
    private boolean activarAceptar;
    private final String pathArchivo = "C:\\UCDIT\\Informe de Trabajo\\";
    private String rutaArchivo;
    private Part archivo;

    public ControllerRegistrarInformePersonal() {
    }

    @PostConstruct
    public void init() {
        rutaArchivo = "";
        archivo = null;
        nuevoNombre = null;
        nuevoFechaRegistro = new Date();
        //
        validacionesNombre = false;
        colorMensaje = "black";
        validacionesArchivo = false;
        activarCasillas = false;
        activarAceptar = false;
        mensajeFormulario = "N/A";
    }

    public void validarNombreInformePersonal() {
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

    private boolean validarMessValidacion() {
        boolean retorno = true;
        if (validacionesNombre == false) {
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

    public void registrarNuevoInformePersonal() {
        if (validarMessValidacion() == true) {
            almacenarNuevoInformePersonalEnSistema();
            limpiarFormulario();
            activarAceptar = true;
            activarCasillas = true;
            colorMensaje = "green";
            mensajeFormulario = "El formulario ha sido ingresado con exito.";
        } else {
            colorMensaje = "red";
            mensajeFormulario = "Existen errores en el formulario, por favor corregir para continuar.";
        }
    }

    private String nombreMes() {
        String nombre = "";
        switch (nuevoFechaRegistro.getMonth()) {
            case 1: {
                nombre = "ENERO";
                break;
            }
            case 2: {
                nombre = "FEBRERO";
                break;
            }
            case 3: {
                nombre = "MARZO";
                break;
            }
            case 4: {
                nombre = "ABRIL";
                break;
            }
            case 5: {
                nombre = "MAYO";
                break;
            }
            case 6: {
                nombre = "JUNIO";
                break;
            }
            case 7: {
                nombre = "JULIO";
                break;
            }
            case 8: {
                nombre = "AGOSTO";
                break;
            }
            case 9: {
                nombre = "SEPTIEMBRE";
                break;
            }
            case 10: {
                nombre = "OCTUBRE";
                break;
            }
            case 11: {
                nombre = "NOVIEMBRE";
                break;
            }
            case 12: {
                nombre = "DICIEMBRE";
                break;
            }

        }
        return nombre;

    }

    private void almacenarNuevoInformePersonalEnSistema() {
        try {
            cargarInformeAServidor();
            InformePersonal nuevaInformePersonal = new InformePersonal();
            nuevaInformePersonal.setFechainforme(nuevoFechaRegistro);
            nuevaInformePersonal.setMesinforme(nombreMes());
            String filename = getFilename(archivo);
            rutaArchivo = pathArchivo + filename;
            nuevaInformePersonal.setUbicacion(rutaArchivo);
            nuevaInformePersonal.setNombrearchivo(nuevoNombre);
             UsuarioLogin usuarioLoginSistema = (UsuarioLogin) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("sessionUsuario");
             PersonalInterno personalInterno = administrarInformePersonalBO.obtenerPersonalInternoPorId(usuarioLoginSistema.getIdUsuarioLogin());
             nuevaInformePersonal.setPersonalinterno(personalInterno);
            administrarInformePersonalBO.crearInformePersonal(nuevaInformePersonal);
        } catch (Exception e) {
            System.out.println("Error ControllerRegistrarInformePersonal almacenarNuevoInformePersonalEnSistema : " + e.toString());
        }
    }

    private void cargarInformeAServidor() throws FileNotFoundException, IOException, MessagingException {
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
        nuevoNombre = null;
        validacionesArchivo = false;
        rutaArchivo = "";
        archivo = null;
        nuevoFechaRegistro = new Date();
        //
        validacionesNombre = false;

        mensajeFormulario = "";
    }

    public String cancelarRegistroInformePersonal() {
        nuevoNombre = null;
        validacionesArchivo = false;
        nuevoFechaRegistro = new Date();
        //
        rutaArchivo = "";
        archivo = null;
        validacionesNombre = false;
        activarAceptar = false;
        mensajeFormulario = "N/A";
        colorMensaje = "black";
        activarCasillas = false;
        return "administrarinformepersonal";
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

    public boolean isActivarAceptar() {
        return activarAceptar;
    }

    public void setActivarAceptar(boolean activarAceptar) {
        this.activarAceptar = activarAceptar;
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
