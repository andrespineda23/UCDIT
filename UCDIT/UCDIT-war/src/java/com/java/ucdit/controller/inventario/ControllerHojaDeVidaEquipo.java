/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.controller.inventario;

import com.java.ucdit.bo.interfaces.inventario.AdministrarHojaDeVidaBOInterface;
import com.java.ucdit.entidades.HojaDeVida;
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
public class ControllerHojaDeVidaEquipo implements Serializable {

    @EJB
    AdministrarHojaDeVidaBOInterface administrarHojaDeVidasBO;
    //
    private List<HojaDeVida> listaHojaDeVidas;
    private List<HojaDeVida> listaHojaDeVidasTabla;
    private int posicionHojaDeVidaTabla;
    private int tamTotalHojaDeVida;
    private boolean bloquearPagSigHojaDeVida, bloquearPagAntHojaDeVida;
    private String cantidadRegistros;
    private BigInteger idEquipo;

    public ControllerHojaDeVidaEquipo() {
    }

    @PostConstruct
    public void init() {
    }

    private void iniciarDatosTabla() {
        cantidadRegistros = "N/A";
        listaHojaDeVidas = null;
        listaHojaDeVidasTabla = null;
        posicionHojaDeVidaTabla = 0;
        tamTotalHojaDeVida = 0;
        bloquearPagAntHojaDeVida = true;
        bloquearPagSigHojaDeVida = true;
    }

    public void buscarHojaDeVidaEquipo(BigInteger idEquipo) {
        this.idEquipo = idEquipo;
        iniciarDatosTabla();
        try {
            listaHojaDeVidas = null;
            listaHojaDeVidas = administrarHojaDeVidasBO.consultarHojaDeVidaPorIdEquipo(idEquipo);
            if (listaHojaDeVidas != null) {
                if (listaHojaDeVidas.size() > 0) {
                    listaHojaDeVidasTabla = new ArrayList<HojaDeVida>();
                    tamTotalHojaDeVida = listaHojaDeVidas.size();
                    posicionHojaDeVidaTabla = 0;
                    cantidadRegistros = String.valueOf(tamTotalHojaDeVida);
                    cargarDatosTablaHojaDeVida();
                } else {
                    listaHojaDeVidasTabla = null;
                    tamTotalHojaDeVida = 0;
                    posicionHojaDeVidaTabla = 0;
                    bloquearPagAntHojaDeVida = true;
                    cantidadRegistros = String.valueOf(tamTotalHojaDeVida);
                    bloquearPagSigHojaDeVida = true;
                }
            } else {
                listaHojaDeVidasTabla = null;
                tamTotalHojaDeVida = 0;
                posicionHojaDeVidaTabla = 0;
                cantidadRegistros = String.valueOf(tamTotalHojaDeVida);
                bloquearPagAntHojaDeVida = true;
                bloquearPagSigHojaDeVida = true;
            }
        } catch (Exception e) {
            System.out.println("Error ControllerAdministrarHojaDeVida buscarHojaDeVidasRegistrador : " + e.toString());
        }
    }

    private void cargarDatosTablaHojaDeVida() {
        if (tamTotalHojaDeVida < 10) {
            for (int i = 0; i < tamTotalHojaDeVida; i++) {
                listaHojaDeVidasTabla.add(listaHojaDeVidas.get(i));
            }
            bloquearPagSigHojaDeVida = true;
            bloquearPagAntHojaDeVida = true;
        } else {
            for (int i = 0; i < 10; i++) {
                listaHojaDeVidasTabla.add(listaHojaDeVidas.get(i));
            }
            bloquearPagSigHojaDeVida = false;
            bloquearPagAntHojaDeVida = true;
        }
    }

    public void cargarPaginaSiguienteHojaDeVida() {
        listaHojaDeVidasTabla = new ArrayList<HojaDeVida>();
        posicionHojaDeVidaTabla = posicionHojaDeVidaTabla + 10;
        int diferencia = tamTotalHojaDeVida - posicionHojaDeVidaTabla;
        if (diferencia > 10) {
            for (int i = posicionHojaDeVidaTabla; i < (posicionHojaDeVidaTabla + 10); i++) {
                listaHojaDeVidasTabla.add(listaHojaDeVidas.get(i));
            }
            bloquearPagSigHojaDeVida = false;
            bloquearPagAntHojaDeVida = false;
        } else {
            for (int i = posicionHojaDeVidaTabla; i < (posicionHojaDeVidaTabla + diferencia); i++) {
                listaHojaDeVidasTabla.add(listaHojaDeVidas.get(i));
            }
            bloquearPagSigHojaDeVida = true;
            bloquearPagAntHojaDeVida = false;
        }
    }

    public void cargarPaginaAnteriorHojaDeVida() {
        listaHojaDeVidasTabla = new ArrayList<HojaDeVida>();
        posicionHojaDeVidaTabla = posicionHojaDeVidaTabla - 10;
        int diferencia = tamTotalHojaDeVida - posicionHojaDeVidaTabla;
        if (diferencia == tamTotalHojaDeVida) {
            for (int i = posicionHojaDeVidaTabla; i < (posicionHojaDeVidaTabla + 10); i++) {
                listaHojaDeVidasTabla.add(listaHojaDeVidas.get(i));
            }
            bloquearPagSigHojaDeVida = false;
            bloquearPagAntHojaDeVida = true;
        } else {
            for (int i = posicionHojaDeVidaTabla; i < (posicionHojaDeVidaTabla + 10); i++) {
                listaHojaDeVidasTabla.add(listaHojaDeVidas.get(i));
            }
            bloquearPagSigHojaDeVida = false;
            bloquearPagAntHojaDeVida = false;
        }
    }

    public void limpiarProcesoBusqueda() {
        listaHojaDeVidas = null;
        listaHojaDeVidasTabla = null;
        posicionHojaDeVidaTabla = 0;
        tamTotalHojaDeVida = 0;
        bloquearPagAntHojaDeVida = true;
        bloquearPagSigHojaDeVida = true;
        cantidadRegistros = "N/A";
    }

    public String nuevoRegistro() {
        limpiarProcesoBusqueda();
        return "registrarhojavidaequipo";
    }
    public String detallesHojaDeVidaEquipo() {
        limpiarProcesoBusqueda();
        return "detalleshojadevidaequipo";
    }

    public String paginaDetallesEquipo() {
        limpiarProcesoBusqueda();
        return "detallesequipo";
    }

    // GET - SET
    public List<HojaDeVida> getListaHojaDeVidas() {
        return listaHojaDeVidas;
    }

    public void setListaHojaDeVidas(List<HojaDeVida> listaHojaDeVidas) {
        this.listaHojaDeVidas = listaHojaDeVidas;
    }

    public List<HojaDeVida> getListaHojaDeVidasTabla() {
        return listaHojaDeVidasTabla;
    }

    public void setListaHojaDeVidasTabla(List<HojaDeVida> listaHojaDeVidasTabla) {
        this.listaHojaDeVidasTabla = listaHojaDeVidasTabla;
    }

    public int getPosicionHojaDeVidaTabla() {
        return posicionHojaDeVidaTabla;
    }

    public void setPosicionHojaDeVidaTabla(int posicionHojaDeVidaTabla) {
        this.posicionHojaDeVidaTabla = posicionHojaDeVidaTabla;
    }

    public int getTamTotalHojaDeVida() {
        return tamTotalHojaDeVida;
    }

    public void setTamTotalHojaDeVida(int tamTotalHojaDeVida) {
        this.tamTotalHojaDeVida = tamTotalHojaDeVida;
    }

    public boolean isBloquearPagSigHojaDeVida() {
        return bloquearPagSigHojaDeVida;
    }

    public void setBloquearPagSigHojaDeVida(boolean bloquearPagSigHojaDeVida) {
        this.bloquearPagSigHojaDeVida = bloquearPagSigHojaDeVida;
    }

    public boolean isBloquearPagAntHojaDeVida() {
        return bloquearPagAntHojaDeVida;
    }

    public void setBloquearPagAntHojaDeVida(boolean bloquearPagAntHojaDeVida) {
        this.bloquearPagAntHojaDeVida = bloquearPagAntHojaDeVida;
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
