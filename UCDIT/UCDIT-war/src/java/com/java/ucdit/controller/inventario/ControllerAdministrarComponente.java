/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.controller.inventario;

import com.java.ucdit.bo.interfaces.inventario.AdministrarComponenteEquipoBOInterface;
import com.java.ucdit.entidades.Componente;
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
public class ControllerAdministrarComponente implements Serializable {

    @EJB
    AdministrarComponenteEquipoBOInterface administrarComponentesBO;
    //
    private List<Componente> listaComponentes;
    private List<Componente> listaComponentesTabla;
    private int posicionComponenteTabla;
    private int tamTotalComponente;
    private boolean bloquearPagSigComponente, bloquearPagAntComponente;
    private String cantidadRegistros;
    private BigInteger idEquipo;

    public ControllerAdministrarComponente() {
    }

    @PostConstruct
    public void init() {

    }

    private void iniciarDatosTabla() {
        cantidadRegistros = "N/A";
        listaComponentes = null;
        listaComponentesTabla = null;
        posicionComponenteTabla = 0;
        tamTotalComponente = 0;
        bloquearPagAntComponente = true;
        bloquearPagSigComponente = true;
    }

    public void buscarComponentesPorIdEquipo(BigInteger idEquipo) {
        this.idEquipo = idEquipo;
        iniciarDatosTabla();
        try {
            listaComponentes = null;
            listaComponentes = administrarComponentesBO.obtenerComponentesPorIdEquipo(this.idEquipo);
            if (listaComponentes != null) {
                if (listaComponentes.size() > 0) {
                    listaComponentesTabla = new ArrayList<Componente>();
                    tamTotalComponente = listaComponentes.size();
                    posicionComponenteTabla = 0;
                    cantidadRegistros = String.valueOf(tamTotalComponente);
                    cargarDatosTablaComponente();
                } else {
                    listaComponentesTabla = null;
                    tamTotalComponente = 0;
                    posicionComponenteTabla = 0;
                    bloquearPagAntComponente = true;
                    cantidadRegistros = String.valueOf(tamTotalComponente);
                    bloquearPagSigComponente = true;
                }
            } else {
                listaComponentesTabla = null;
                tamTotalComponente = 0;
                posicionComponenteTabla = 0;
                cantidadRegistros = String.valueOf(tamTotalComponente);
                bloquearPagAntComponente = true;
                bloquearPagSigComponente = true;
            }
        } catch (Exception e) {
            System.out.println("Error ControllerAdministrarComponente buscarComponentesRegistrador : " + e.toString());
        }
    }

    private void cargarDatosTablaComponente() {
        if (tamTotalComponente < 10) {
            for (int i = 0; i < tamTotalComponente; i++) {
                listaComponentesTabla.add(listaComponentes.get(i));
            }
            bloquearPagSigComponente = true;
            bloquearPagAntComponente = true;
        } else {
            for (int i = 0; i < 10; i++) {
                listaComponentesTabla.add(listaComponentes.get(i));
            }
            bloquearPagSigComponente = false;
            bloquearPagAntComponente = true;
        }
    }

    public void cargarPaginaSiguienteComponente() {
        listaComponentesTabla = new ArrayList<Componente>();
        posicionComponenteTabla = posicionComponenteTabla + 10;
        int diferencia = tamTotalComponente - posicionComponenteTabla;
        if (diferencia > 10) {
            for (int i = posicionComponenteTabla; i < (posicionComponenteTabla + 10); i++) {
                listaComponentesTabla.add(listaComponentes.get(i));
            }
            bloquearPagSigComponente = false;
            bloquearPagAntComponente = false;
        } else {
            for (int i = posicionComponenteTabla; i < (posicionComponenteTabla + diferencia); i++) {
                listaComponentesTabla.add(listaComponentes.get(i));
            }
            bloquearPagSigComponente = true;
            bloquearPagAntComponente = false;
        }
    }

    public void cargarPaginaAnteriorComponente() {
        listaComponentesTabla = new ArrayList<Componente>();
        posicionComponenteTabla = posicionComponenteTabla - 10;
        int diferencia = tamTotalComponente - posicionComponenteTabla;
        if (diferencia == tamTotalComponente) {
            for (int i = posicionComponenteTabla; i < (posicionComponenteTabla + 10); i++) {
                listaComponentesTabla.add(listaComponentes.get(i));
            }
            bloquearPagSigComponente = false;
            bloquearPagAntComponente = true;
        } else {
            for (int i = posicionComponenteTabla; i < (posicionComponenteTabla + 10); i++) {
                listaComponentesTabla.add(listaComponentes.get(i));
            }
            bloquearPagSigComponente = false;
            bloquearPagAntComponente = false;
        }
    }

    public void limpiarProcesoBusqueda() {
        listaComponentes = null;
        listaComponentesTabla = null;
        posicionComponenteTabla = 0;
        tamTotalComponente = 0;
        bloquearPagAntComponente = true;
        bloquearPagSigComponente = true;
        cantidadRegistros = "N/A";
    }

    public String paginaDetalles() {
        limpiarProcesoBusqueda();
        return "detallescomponente";
    }

    public String nuevoRegistro() {
        limpiarProcesoBusqueda();
        return "registrarcomponente";
    }

    public String detallesEquipo() {
        limpiarProcesoBusqueda();
        return "detallesequipo";
    }

    // GET - SET
    public List<Componente> getListaComponentes() {
        return listaComponentes;
    }

    public void setListaComponentes(List<Componente> listaComponentes) {
        this.listaComponentes = listaComponentes;
    }

    public List<Componente> getListaComponentesTabla() {
        return listaComponentesTabla;
    }

    public void setListaComponentesTabla(List<Componente> listaComponentesTabla) {
        this.listaComponentesTabla = listaComponentesTabla;
    }

    public int getPosicionComponenteTabla() {
        return posicionComponenteTabla;
    }

    public void setPosicionComponenteTabla(int posicionComponenteTabla) {
        this.posicionComponenteTabla = posicionComponenteTabla;
    }

    public int getTamTotalComponente() {
        return tamTotalComponente;
    }

    public void setTamTotalComponente(int tamTotalComponente) {
        this.tamTotalComponente = tamTotalComponente;
    }

    public boolean isBloquearPagSigComponente() {
        return bloquearPagSigComponente;
    }

    public void setBloquearPagSigComponente(boolean bloquearPagSigComponente) {
        this.bloquearPagSigComponente = bloquearPagSigComponente;
    }

    public boolean isBloquearPagAntComponente() {
        return bloquearPagAntComponente;
    }

    public void setBloquearPagAntComponente(boolean bloquearPagAntComponente) {
        this.bloquearPagAntComponente = bloquearPagAntComponente;
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
