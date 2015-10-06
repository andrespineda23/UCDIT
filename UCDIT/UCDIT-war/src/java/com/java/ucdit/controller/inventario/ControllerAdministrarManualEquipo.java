/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.controller.inventario;

import com.java.ucdit.bo.interfaces.inventario.AdministrarManualEquipoBOInterface;
import com.java.ucdit.entidades.ManualEquipo;
import java.io.Serializable;
import java.math.BigInteger;
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
public class ControllerAdministrarManualEquipo implements Serializable {

    @EJB
    AdministrarManualEquipoBOInterface administrarManualBO;
    //
    private List<ManualEquipo> listaManual;
    private List<ManualEquipo> listaManualTabla;
    private int posicionManualTabla;
    private int tamTotalManual;
    private boolean bloquearPagSigManual, bloquearPagAntManual;
    private String cantidadRegistros;
    private BigInteger idEquipo;

    public ControllerAdministrarManualEquipo() {
    }

    @PostConstruct
    public void init() {

    }

    private void iniciarDatosTabla() {
        cantidadRegistros = "N/A";
        listaManual = null;
        listaManualTabla = null;
        posicionManualTabla = 0;
        tamTotalManual = 0;
        bloquearPagAntManual = true;
        bloquearPagSigManual = true;
    }

    public void buscarManualPorIdEquipo(BigInteger idEquipo) {
        this.idEquipo = idEquipo;
        iniciarDatosTabla();
        try {
            listaManual = null;
            listaManual = administrarManualBO.obtenerManualEquiposPorIdEquipo(this.idEquipo);
            if (listaManual != null) {
                if (listaManual.size() > 0) {
                    listaManualTabla = new ArrayList<ManualEquipo>();
                    tamTotalManual = listaManual.size();
                    posicionManualTabla = 0;
                    cantidadRegistros = String.valueOf(tamTotalManual);
                    cargarDatosTablaManual();
                } else {
                    listaManualTabla = null;
                    tamTotalManual = 0;
                    posicionManualTabla = 0;
                    bloquearPagAntManual = true;
                    cantidadRegistros = String.valueOf(tamTotalManual);
                    bloquearPagSigManual = true;
                }
            } else {
                listaManualTabla = null;
                tamTotalManual = 0;
                posicionManualTabla = 0;
                cantidadRegistros = String.valueOf(tamTotalManual);
                bloquearPagAntManual = true;
                bloquearPagSigManual = true;
            }
        } catch (Exception e) {
            System.out.println("Error ControllerAdministrarManual buscarManualRegistrador : " + e.toString());
        }
    }

    private void cargarDatosTablaManual() {
        if (tamTotalManual < 10) {
            for (int i = 0; i < tamTotalManual; i++) {
                listaManualTabla.add(listaManual.get(i));
            }
            bloquearPagSigManual = true;
            bloquearPagAntManual = true;
        } else {
            for (int i = 0; i < 10; i++) {
                listaManualTabla.add(listaManual.get(i));
            }
            bloquearPagSigManual = false;
            bloquearPagAntManual = true;
        }
    }

    public void cargarPaginaSiguienteManual() {
        listaManualTabla = new ArrayList<ManualEquipo>();
        posicionManualTabla = posicionManualTabla + 10;
        int diferencia = tamTotalManual - posicionManualTabla;
        if (diferencia > 10) {
            for (int i = posicionManualTabla; i < (posicionManualTabla + 10); i++) {
                listaManualTabla.add(listaManual.get(i));
            }
            bloquearPagSigManual = false;
            bloquearPagAntManual = false;
        } else {
            for (int i = posicionManualTabla; i < (posicionManualTabla + diferencia); i++) {
                listaManualTabla.add(listaManual.get(i));
            }
            bloquearPagSigManual = true;
            bloquearPagAntManual = false;
        }
    }

    public void cargarPaginaAnteriorManual() {
        listaManualTabla = new ArrayList<ManualEquipo>();
        posicionManualTabla = posicionManualTabla - 10;
        int diferencia = tamTotalManual - posicionManualTabla;
        if (diferencia == tamTotalManual) {
            for (int i = posicionManualTabla; i < (posicionManualTabla + 10); i++) {
                listaManualTabla.add(listaManual.get(i));
            }
            bloquearPagSigManual = false;
            bloquearPagAntManual = true;
        } else {
            for (int i = posicionManualTabla; i < (posicionManualTabla + 10); i++) {
                listaManualTabla.add(listaManual.get(i));
            }
            bloquearPagSigManual = false;
            bloquearPagAntManual = false;
        }
    }

    public void limpiarProcesoBusqueda() {
        listaManual = null;
        listaManualTabla = null;
        posicionManualTabla = 0;
        tamTotalManual = 0;
        bloquearPagAntManual = true;
        bloquearPagSigManual = true;
        cantidadRegistros = "N/A";
    }

    public String paginaDetalles() {
        limpiarProcesoBusqueda();
        return "detallesmanualequipo";
    }

    public String nuevoRegistro() {
        limpiarProcesoBusqueda();
        return "registrarmanualequipo";
    }

    public String detallesEquipo() {
        limpiarProcesoBusqueda();
        return "detallesequipo";
    }

    // GET - SET
    public List<ManualEquipo> getListaManual() {
        return listaManual;
    }

    public void setListaManual(List<ManualEquipo> listaManual) {
        this.listaManual = listaManual;
    }

    public List<ManualEquipo> getListaManualTabla() {
        return listaManualTabla;
    }

    public void setListaManualTabla(List<ManualEquipo> listaManualTabla) {
        this.listaManualTabla = listaManualTabla;
    }

    public int getPosicionManualTabla() {
        return posicionManualTabla;
    }

    public void setPosicionManualTabla(int posicionManualTabla) {
        this.posicionManualTabla = posicionManualTabla;
    }

    public int getTamTotalManual() {
        return tamTotalManual;
    }

    public void setTamTotalManual(int tamTotalManual) {
        this.tamTotalManual = tamTotalManual;
    }

    public boolean isBloquearPagSigManual() {
        return bloquearPagSigManual;
    }

    public void setBloquearPagSigManual(boolean bloquearPagSigManual) {
        this.bloquearPagSigManual = bloquearPagSigManual;
    }

    public boolean isBloquearPagAntManual() {
        return bloquearPagAntManual;
    }

    public void setBloquearPagAntManual(boolean bloquearPagAntManual) {
        this.bloquearPagAntManual = bloquearPagAntManual;
    }

    public String getCantidadRegistros() {
        return cantidadRegistros;
    }

    public void setCantidadRegistros(String cantidadRegistros) {
        this.cantidadRegistros = cantidadRegistros;
    }

    public BigInteger getIdEquipo() {
        return idEquipo;
    }

    public void setIdEquipo(BigInteger idEquipo) {
        this.idEquipo = idEquipo;
    }

}
