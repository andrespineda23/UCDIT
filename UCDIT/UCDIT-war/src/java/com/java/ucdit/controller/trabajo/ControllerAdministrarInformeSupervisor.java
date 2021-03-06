/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.controller.trabajo;

import com.java.ucdit.bo.interfaces.trabajo.AdministrarInformePersonalBOInterface;
import com.java.ucdit.entidades.InformePersonal;
import com.java.ucdit.entidades.PersonalInterno;
import com.java.ucdit.utilidades.Utilidades;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ELECTRONICA
 */
@ManagedBean
@SessionScoped
public class ControllerAdministrarInformeSupervisor implements Serializable {

    @EJB
    AdministrarInformePersonalBOInterface administrarInformePersonalBO;
    //
    private List<InformePersonal> listaInformePersonal;
    private List<InformePersonal> listaInformePersonalTemporal;
    private List<InformePersonal> listaInformePersonalTabla;
    private int posicionInformePersonalTabla;
    private int tamTotalInformePersonal;
    private boolean bloquearPagSigInformePersonal, bloquearPagAntInformePersonal;
    private String cantidadRegistros;
    private List<PersonalInterno> listaPersonalInterno;
    private PersonalInterno personalInterno;

    public ControllerAdministrarInformeSupervisor() {
    }

    @PostConstruct
    public void init() {
    }

    //1 - TODOS 2 - ACTIVOS 3 - TERMINADOS
    public void actualizarPersonalInterno() {
        if (Utilidades.validarNulo(personalInterno)) {
            listaInformePersonal = administrarInformePersonalBO.obtenerInformePersonalPorIdPersonal(personalInterno.getIdpersonalinterno());
            listaInformePersonalTemporal = listaInformePersonal;
            cargarInformacionTabla();
        } else {
            listaInformePersonal = administrarInformePersonalBO.obtenerInformePersonalRegistrado();
            listaInformePersonalTemporal = listaInformePersonal;
            cargarInformacionTabla();
        }
    }

    private void iniciarDatosTabla() {
        personalInterno = null;
        listaPersonalInterno = administrarInformePersonalBO.obtenerPersonalInternoRegistrado();
        cantidadRegistros = "N/A";
        listaInformePersonalTemporal = null;
        listaInformePersonal = null;
        listaInformePersonalTabla = null;
        posicionInformePersonalTabla = 0;
        tamTotalInformePersonal = 0;
        personalInterno = null;
        bloquearPagAntInformePersonal = true;
        bloquearPagSigInformePersonal = true;
    }

    public void buscarInformePersonalRegistrados() {
        iniciarDatosTabla();
        try {
            actualizarPersonalInterno();
            cargarInformacionTabla();
        } catch (Exception e) {
            System.out.println("Error ControllerAdministrarInformePersonal buscarInformePersonalRegistrador : " + e.toString());
        }
    }

    private void cargarInformacionTabla() {
        if (listaInformePersonalTemporal != null) {
            if (listaInformePersonalTemporal.size() > 0) {
                listaInformePersonalTabla = new ArrayList<InformePersonal>();
                tamTotalInformePersonal = listaInformePersonalTemporal.size();
                posicionInformePersonalTabla = 0;
                cantidadRegistros = String.valueOf(tamTotalInformePersonal);
                cargarDatosTablaInformePersonal();
            } else {
                listaInformePersonalTabla = null;
                tamTotalInformePersonal = 0;
                posicionInformePersonalTabla = 0;
                bloquearPagAntInformePersonal = true;
                cantidadRegistros = String.valueOf(tamTotalInformePersonal);
                bloquearPagSigInformePersonal = true;
            }
        } else {
            listaInformePersonalTabla = null;
            tamTotalInformePersonal = 0;
            posicionInformePersonalTabla = 0;
            cantidadRegistros = String.valueOf(tamTotalInformePersonal);
            bloquearPagAntInformePersonal = true;
            bloquearPagSigInformePersonal = true;
        }
    }

    private void cargarDatosTablaInformePersonal() {
        if (tamTotalInformePersonal < 10) {
            for (int i = 0; i < tamTotalInformePersonal; i++) {
                listaInformePersonalTabla.add(listaInformePersonalTemporal.get(i));
            }
            bloquearPagSigInformePersonal = true;
            bloquearPagAntInformePersonal = true;
        } else {
            for (int i = 0; i < 10; i++) {
                listaInformePersonalTabla.add(listaInformePersonalTemporal.get(i));
            }
            bloquearPagSigInformePersonal = false;
            bloquearPagAntInformePersonal = true;
        }
    }

    public void cargarPaginaSiguienteInformePersonal() {
        listaInformePersonalTabla = new ArrayList<InformePersonal>();
        posicionInformePersonalTabla = posicionInformePersonalTabla + 10;
        int diferencia = tamTotalInformePersonal - posicionInformePersonalTabla;
        if (diferencia > 10) {
            for (int i = posicionInformePersonalTabla; i < (posicionInformePersonalTabla + 10); i++) {
                listaInformePersonalTabla.add(listaInformePersonalTemporal.get(i));
            }
            bloquearPagSigInformePersonal = false;
            bloquearPagAntInformePersonal = false;
        } else {
            for (int i = posicionInformePersonalTabla; i < (posicionInformePersonalTabla + diferencia); i++) {
                listaInformePersonalTabla.add(listaInformePersonalTemporal.get(i));
            }
            bloquearPagSigInformePersonal = true;
            bloquearPagAntInformePersonal = false;
        }
    }

    public void cargarPaginaAnteriorInformePersonal() {
        listaInformePersonalTabla = new ArrayList<InformePersonal>();
        posicionInformePersonalTabla = posicionInformePersonalTabla - 10;
        int diferencia = tamTotalInformePersonal - posicionInformePersonalTabla;
        if (diferencia == tamTotalInformePersonal) {
            for (int i = posicionInformePersonalTabla; i < (posicionInformePersonalTabla + 10); i++) {
                listaInformePersonalTabla.add(listaInformePersonalTemporal.get(i));
            }
            bloquearPagSigInformePersonal = false;
            bloquearPagAntInformePersonal = true;
        } else {
            for (int i = posicionInformePersonalTabla; i < (posicionInformePersonalTabla + 10); i++) {
                listaInformePersonalTabla.add(listaInformePersonalTemporal.get(i));
            }
            bloquearPagSigInformePersonal = false;
            bloquearPagAntInformePersonal = false;
        }
    }

    public void limpiarProcesoBusqueda() {
        listaInformePersonalTemporal = null;
        listaInformePersonal = null;
        listaInformePersonalTabla = null;
        posicionInformePersonalTabla = 0;
        tamTotalInformePersonal = 0;
        personalInterno = null;
        bloquearPagAntInformePersonal = true;
        bloquearPagSigInformePersonal = true;
        cantidadRegistros = "N/A";
    }

    private void cancelarFormulario() {
        listaInformePersonalTemporal = null;
        listaInformePersonal = null;
        listaInformePersonalTabla = null;
        posicionInformePersonalTabla = 0;
        tamTotalInformePersonal = 0;
        personalInterno = null;
        bloquearPagAntInformePersonal = true;
        bloquearPagSigInformePersonal = true;
        cantidadRegistros = "N/A";
        listaPersonalInterno = null;
    }

    public void descargarInformePersonal(String ruta) throws FileNotFoundException, IOException {
        File ficheroPDF = new File(ruta);
        FacesContext ctx = FacesContext.getCurrentInstance();
        FileInputStream fis = new FileInputStream(ficheroPDF);
        byte[] bytes = new byte[1000];
        int read = 0;
        if (!ctx.getResponseComplete()) {
            String fileName = ficheroPDF.getName();
            String contentType = "application/pdf";
            HttpServletResponse response = (HttpServletResponse) ctx.getExternalContext().getResponse();
            response.setContentType(contentType);
            response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName + "\"");
            ServletOutputStream out = response.getOutputStream();
            while ((read = fis.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            out.flush();
            out.close();
            ctx.responseComplete();
        }
    }

    public String paginaInicio() {
        cancelarFormulario();
        return "iniciosupervisor";
    }

    public String paginaDetalles() {
        cancelarFormulario();
        return "detallesinformepersonal";
    }

    public String nuevoRegistro() {
        cancelarFormulario();
        return "registrarinformepersonal";
    }

    // GET - SET
    public List<InformePersonal> getListaInformePersonal() {
        return listaInformePersonal;
    }

    public void setListaInformePersonal(List<InformePersonal> listaInformePersonal) {
        this.listaInformePersonal = listaInformePersonal;
    }

    public List<InformePersonal> getListaInformePersonalTemporal() {
        return listaInformePersonalTemporal;
    }

    public void setListaInformePersonalTemporal(List<InformePersonal> listaInformePersonalTemporal) {
        this.listaInformePersonalTemporal = listaInformePersonalTemporal;
    }

    public List<InformePersonal> getListaInformePersonalTabla() {
        return listaInformePersonalTabla;
    }

    public void setListaInformePersonalTabla(List<InformePersonal> listaInformePersonalTabla) {
        this.listaInformePersonalTabla = listaInformePersonalTabla;
    }

    public int getPosicionInformePersonalTabla() {
        return posicionInformePersonalTabla;
    }

    public void setPosicionInformePersonalTabla(int posicionInformePersonalTabla) {
        this.posicionInformePersonalTabla = posicionInformePersonalTabla;
    }

    public int getTamTotalInformePersonal() {
        return tamTotalInformePersonal;
    }

    public void setTamTotalInformePersonal(int tamTotalInformePersonal) {
        this.tamTotalInformePersonal = tamTotalInformePersonal;
    }

    public boolean isBloquearPagSigInformePersonal() {
        return bloquearPagSigInformePersonal;
    }

    public void setBloquearPagSigInformePersonal(boolean bloquearPagSigInformePersonal) {
        this.bloquearPagSigInformePersonal = bloquearPagSigInformePersonal;
    }

    public boolean isBloquearPagAntInformePersonal() {
        return bloquearPagAntInformePersonal;
    }

    public void setBloquearPagAntInformePersonal(boolean bloquearPagAntInformePersonal) {
        this.bloquearPagAntInformePersonal = bloquearPagAntInformePersonal;
    }

    public String getCantidadRegistros() {
        return cantidadRegistros;
    }

    public void setCantidadRegistros(String cantidadRegistros) {
        this.cantidadRegistros = cantidadRegistros;
    }

    public List<PersonalInterno> getListaPersonalInterno() {
        return listaPersonalInterno;
    }

    public void setListaPersonalInterno(List<PersonalInterno> listaPersonalInterno) {
        this.listaPersonalInterno = listaPersonalInterno;
    }

    public PersonalInterno getPersonalInterno() {
        return personalInterno;
    }

    public void setPersonalInterno(PersonalInterno personalInterno) {
        this.personalInterno = personalInterno;
    }

}
