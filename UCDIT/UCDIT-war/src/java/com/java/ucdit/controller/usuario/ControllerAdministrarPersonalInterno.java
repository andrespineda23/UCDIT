/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.controller.usuario;

import com.java.ucdit.bo.interfaces.usuario.AdministrarPersonalInternoBOInterface;
import com.java.ucdit.entidades.PersonalInterno;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author ELECTRONICA
 */
@ManagedBean
@SessionScoped
public class ControllerAdministrarPersonalInterno implements Serializable {

    @EJB
    AdministrarPersonalInternoBOInterface administrarPersonalInternoBO;
    //
    private List<PersonalInterno> listaPersonalInterno;
    private List<PersonalInterno> listaPersonalInternoTabla;
    private int posicionPersonalInternoTabla;
    private int tamTotalPersonalInterno;
    private boolean bloquearPagSigPersonalInterno, bloquearPagAntPersonalInterno;
    private String cantidadRegistros;

    public ControllerAdministrarPersonalInterno() {
    }

    @PostConstruct
    public void init() {
        buscarPersonalInternoRegistrador();
    }

    private void iniciarDatosTabla() {
        cantidadRegistros = "N/A";
        listaPersonalInterno = null;
        listaPersonalInternoTabla = null;
        posicionPersonalInternoTabla = 0;
        tamTotalPersonalInterno = 0;
        bloquearPagAntPersonalInterno = true;
        bloquearPagSigPersonalInterno = true;
    }

    public void buscarPersonalInternoRegistrador() {
        iniciarDatosTabla();
        try {
            listaPersonalInterno = null;
            listaPersonalInterno = administrarPersonalInternoBO.consultarPersonalInternoesRegistrados();
            if (listaPersonalInterno != null) {
                if (listaPersonalInterno.size() > 0) {
                    listaPersonalInternoTabla = new ArrayList<PersonalInterno>();
                    tamTotalPersonalInterno = listaPersonalInterno.size();
                    posicionPersonalInternoTabla = 0;
                    cantidadRegistros = String.valueOf(tamTotalPersonalInterno);
                    cargarDatosTablaPersonalInterno();
                } else {
                    listaPersonalInternoTabla = null;
                    tamTotalPersonalInterno = 0;
                    posicionPersonalInternoTabla = 0;
                    bloquearPagAntPersonalInterno = true;
                    cantidadRegistros = String.valueOf(tamTotalPersonalInterno);
                    bloquearPagSigPersonalInterno = true;
                }
            } else {
                listaPersonalInternoTabla = null;
                tamTotalPersonalInterno = 0;
                posicionPersonalInternoTabla = 0;
                cantidadRegistros = String.valueOf(tamTotalPersonalInterno);
                bloquearPagAntPersonalInterno = true;
                bloquearPagSigPersonalInterno = true;
            }
        } catch (Exception e) {
            System.out.println("Error ControllerAdministrarPersonalInterno buscarPersonalInternoRegistrador : " + e.toString());
        }
    }

    private void cargarDatosTablaPersonalInterno() {
        if (tamTotalPersonalInterno < 5) {
            for (int i = 0; i < tamTotalPersonalInterno; i++) {
                listaPersonalInternoTabla.add(listaPersonalInterno.get(i));
            }
            bloquearPagSigPersonalInterno = true;
            bloquearPagAntPersonalInterno = true;
        } else {
            for (int i = 0; i < 5; i++) {
                listaPersonalInternoTabla.add(listaPersonalInterno.get(i));
            }
            bloquearPagSigPersonalInterno = false;
            bloquearPagAntPersonalInterno = true;
        }
    }

    public void cargarPaginaSiguientePersonalInterno() {
        listaPersonalInternoTabla = new ArrayList<PersonalInterno>();
        posicionPersonalInternoTabla = posicionPersonalInternoTabla + 5;
        int diferencia = tamTotalPersonalInterno - posicionPersonalInternoTabla;
        if (diferencia > 5) {
            for (int i = posicionPersonalInternoTabla; i < (posicionPersonalInternoTabla + 5); i++) {
                listaPersonalInternoTabla.add(listaPersonalInterno.get(i));
            }
            bloquearPagSigPersonalInterno = false;
            bloquearPagAntPersonalInterno = false;
        } else {
            for (int i = posicionPersonalInternoTabla; i < (posicionPersonalInternoTabla + diferencia); i++) {
                listaPersonalInternoTabla.add(listaPersonalInterno.get(i));
            }
            bloquearPagSigPersonalInterno = true;
            bloquearPagAntPersonalInterno = false;
        }
    }

    public void cargarPaginaAnteriorPersonalInterno() {
        listaPersonalInternoTabla = new ArrayList<PersonalInterno>();
        posicionPersonalInternoTabla = posicionPersonalInternoTabla - 5;
        int diferencia = tamTotalPersonalInterno - posicionPersonalInternoTabla;
        if (diferencia == tamTotalPersonalInterno) {
            for (int i = posicionPersonalInternoTabla; i < (posicionPersonalInternoTabla + 5); i++) {
                listaPersonalInternoTabla.add(listaPersonalInterno.get(i));
            }
            bloquearPagSigPersonalInterno = false;
            bloquearPagAntPersonalInterno = true;
        } else {
            for (int i = posicionPersonalInternoTabla; i < (posicionPersonalInternoTabla + 5); i++) {
                listaPersonalInternoTabla.add(listaPersonalInterno.get(i));
            }
            bloquearPagSigPersonalInterno = false;
            bloquearPagAntPersonalInterno = false;
        }
    }

    public void limpiarProcesoBusqueda() {
        listaPersonalInterno = null;
        listaPersonalInternoTabla = null;
        posicionPersonalInternoTabla = 0;
        tamTotalPersonalInterno = 0;
        bloquearPagAntPersonalInterno = true;
        bloquearPagSigPersonalInterno = true;
        cantidadRegistros = "N/A";
    }

    public String paginaDetalles() {
        limpiarProcesoBusqueda();
        return "detallespersonalinterno";
    }

    // GET - SET
    public List<PersonalInterno> getListaPersonalInterno() {
        return listaPersonalInterno;
    }

    public void setListaPersonalInterno(List<PersonalInterno> listaPersonalInterno) {
        this.listaPersonalInterno = listaPersonalInterno;
    }

    public List<PersonalInterno> getListaPersonalInternoTabla() {
        return listaPersonalInternoTabla;
    }

    public void setListaPersonalInternoTabla(List<PersonalInterno> listaPersonalInternoTabla) {
        this.listaPersonalInternoTabla = listaPersonalInternoTabla;
    }

    public int getPosicionPersonalInternoTabla() {
        return posicionPersonalInternoTabla;
    }

    public void setPosicionPersonalInternoTabla(int posicionPersonalInternoTabla) {
        this.posicionPersonalInternoTabla = posicionPersonalInternoTabla;
    }

    public int getTamTotalPersonalInterno() {
        return tamTotalPersonalInterno;
    }

    public void setTamTotalPersonalInterno(int tamTotalPersonalInterno) {
        this.tamTotalPersonalInterno = tamTotalPersonalInterno;
    }

    public boolean isBloquearPagSigPersonalInterno() {
        return bloquearPagSigPersonalInterno;
    }

    public void setBloquearPagSigPersonalInterno(boolean bloquearPagSigPersonalInterno) {
        this.bloquearPagSigPersonalInterno = bloquearPagSigPersonalInterno;
    }

    public boolean isBloquearPagAntPersonalInterno() {
        return bloquearPagAntPersonalInterno;
    }

    public void setBloquearPagAntPersonalInterno(boolean bloquearPagAntPersonalInterno) {
        this.bloquearPagAntPersonalInterno = bloquearPagAntPersonalInterno;
    }

    public String getCantidadRegistros() {
        return cantidadRegistros;
    }

    public void setCantidadRegistros(String cantidadRegistros) {
        this.cantidadRegistros = cantidadRegistros;
    }

}
