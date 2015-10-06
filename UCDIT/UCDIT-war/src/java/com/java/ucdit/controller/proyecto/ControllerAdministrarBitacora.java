/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.controller.proyecto;

import com.java.ucdit.bo.interfaces.proyecto.AdministrarBitacoraProyectoBOInterface;
import com.java.ucdit.entidades.BitacoraProyecto;
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
public class ControllerAdministrarBitacora implements Serializable {

    @EJB
    AdministrarBitacoraProyectoBOInterface administrarBitacoraProyectoBO;
    //
    private List<BitacoraProyecto> listaBitacoraProyectos;
    private List<BitacoraProyecto> listaBitacoraProyectosTabla;
    private int posicionBitacoraProyectoTabla;
    private int tamTotalBitacoraProyecto;
    private boolean bloquearPagSigBitacoraProyecto, bloquearPagAntBitacoraProyecto;
    private String cantidadRegistros;
    private BigInteger idProyecto;

    public ControllerAdministrarBitacora() {
    }

    @PostConstruct
    public void init() {
    }

    private void iniciarDatosTabla() {
        cantidadRegistros = "N/A";
        listaBitacoraProyectos = null;
        listaBitacoraProyectosTabla = null;
        posicionBitacoraProyectoTabla = 0;
        tamTotalBitacoraProyecto = 0;
        bloquearPagAntBitacoraProyecto = true;
        bloquearPagSigBitacoraProyecto = true;
    }

    public void buscarBitacoraProyectoRegistrador(BigInteger idProyecto) {
        this.idProyecto = idProyecto;
        System.out.println("idProyecto: "+idProyecto);
        iniciarDatosTabla();
        try {
            listaBitacoraProyectos = null;
            listaBitacoraProyectos = administrarBitacoraProyectoBO.consultarBitacoraPorIdProyecto(this.idProyecto);
            if (listaBitacoraProyectos != null) {
                if (listaBitacoraProyectos.size() > 0) {
                    listaBitacoraProyectosTabla = new ArrayList<BitacoraProyecto>();
                    tamTotalBitacoraProyecto = listaBitacoraProyectos.size();
                    posicionBitacoraProyectoTabla = 0;
                    cantidadRegistros = String.valueOf(tamTotalBitacoraProyecto);
                    cargarDatosTablaBitacoraProyecto();
                } else {
                    listaBitacoraProyectosTabla = null;
                    tamTotalBitacoraProyecto = 0;
                    posicionBitacoraProyectoTabla = 0;
                    bloquearPagAntBitacoraProyecto = true;
                    cantidadRegistros = String.valueOf(tamTotalBitacoraProyecto);
                    bloquearPagSigBitacoraProyecto = true;
                }
            } else {
                listaBitacoraProyectosTabla = null;
                tamTotalBitacoraProyecto = 0;
                posicionBitacoraProyectoTabla = 0;
                cantidadRegistros = String.valueOf(tamTotalBitacoraProyecto);
                bloquearPagAntBitacoraProyecto = true;
                bloquearPagSigBitacoraProyecto = true;
            }
        } catch (Exception e) {
            System.out.println("Error ControllerAdministrarBitacoraProyecto buscarBitacoraProyectoRegistrador : " + e.toString());
        }
    }

    private void cargarDatosTablaBitacoraProyecto() {
        if (tamTotalBitacoraProyecto < 5) {
            for (int i = 0; i < tamTotalBitacoraProyecto; i++) {
                listaBitacoraProyectosTabla.add(listaBitacoraProyectos.get(i));
            }
            bloquearPagSigBitacoraProyecto = true;
            bloquearPagAntBitacoraProyecto = true;
        } else {
            for (int i = 0; i < 5; i++) {
                listaBitacoraProyectosTabla.add(listaBitacoraProyectos.get(i));
            }
            bloquearPagSigBitacoraProyecto = false;
            bloquearPagAntBitacoraProyecto = true;
        }
    }

    public void cargarPaginaSiguienteBitacoraProyecto() {
        listaBitacoraProyectosTabla = new ArrayList<BitacoraProyecto>();
        posicionBitacoraProyectoTabla = posicionBitacoraProyectoTabla + 5;
        int diferencia = tamTotalBitacoraProyecto - posicionBitacoraProyectoTabla;
        if (diferencia > 5) {
            for (int i = posicionBitacoraProyectoTabla; i < (posicionBitacoraProyectoTabla + 5); i++) {
                listaBitacoraProyectosTabla.add(listaBitacoraProyectos.get(i));
            }
            bloquearPagSigBitacoraProyecto = false;
            bloquearPagAntBitacoraProyecto = false;
        } else {
            for (int i = posicionBitacoraProyectoTabla; i < (posicionBitacoraProyectoTabla + diferencia); i++) {
                listaBitacoraProyectosTabla.add(listaBitacoraProyectos.get(i));
            }
            bloquearPagSigBitacoraProyecto = true;
            bloquearPagAntBitacoraProyecto = false;
        }
    }

    public void cargarPaginaAnteriorBitacoraProyecto() {
        listaBitacoraProyectosTabla = new ArrayList<BitacoraProyecto>();
        posicionBitacoraProyectoTabla = posicionBitacoraProyectoTabla - 5;
        int diferencia = tamTotalBitacoraProyecto - posicionBitacoraProyectoTabla;
        if (diferencia == tamTotalBitacoraProyecto) {
            for (int i = posicionBitacoraProyectoTabla; i < (posicionBitacoraProyectoTabla + 5); i++) {
                listaBitacoraProyectosTabla.add(listaBitacoraProyectos.get(i));
            }
            bloquearPagSigBitacoraProyecto = false;
            bloquearPagAntBitacoraProyecto = true;
        } else {
            for (int i = posicionBitacoraProyectoTabla; i < (posicionBitacoraProyectoTabla + 5); i++) {
                listaBitacoraProyectosTabla.add(listaBitacoraProyectos.get(i));
            }
            bloquearPagSigBitacoraProyecto = false;
            bloquearPagAntBitacoraProyecto = false;
        }
    }

    public void limpiarProcesoBusqueda() {
        listaBitacoraProyectos = null;
        listaBitacoraProyectosTabla = null;
        posicionBitacoraProyectoTabla = 0;
        tamTotalBitacoraProyecto = 0;
        bloquearPagAntBitacoraProyecto = true;
        bloquearPagSigBitacoraProyecto = true;
        cantidadRegistros = "N/A";
    }

    public String nuevoRegistro() {
        limpiarProcesoBusqueda();
        return "registrarbitacora";
    }

    public String paginaDetalles() {
        limpiarProcesoBusqueda();
        return "detallesbitacora";
    }
    
    public String paginaProyecto() {
        limpiarProcesoBusqueda();
        return "administrarproyecto";
    }

    // GET - SET
    public List<BitacoraProyecto> getListaBitacoraProyectos() {
        return listaBitacoraProyectos;
    }

    public void setListaBitacoraProyectos(List<BitacoraProyecto> listaBitacoraProyectos) {
        this.listaBitacoraProyectos = listaBitacoraProyectos;
    }

    public List<BitacoraProyecto> getListaBitacoraProyectosTabla() {
        return listaBitacoraProyectosTabla;
    }

    public void setListaBitacoraProyectosTabla(List<BitacoraProyecto> listaBitacoraProyectosTabla) {
        this.listaBitacoraProyectosTabla = listaBitacoraProyectosTabla;
    }

    public int getPosicionBitacoraProyectoTabla() {
        return posicionBitacoraProyectoTabla;
    }

    public void setPosicionBitacoraProyectoTabla(int posicionBitacoraProyectoTabla) {
        this.posicionBitacoraProyectoTabla = posicionBitacoraProyectoTabla;
    }

    public int getTamTotalBitacoraProyecto() {
        return tamTotalBitacoraProyecto;
    }

    public void setTamTotalBitacoraProyecto(int tamTotalBitacoraProyecto) {
        this.tamTotalBitacoraProyecto = tamTotalBitacoraProyecto;
    }

    public boolean isBloquearPagSigBitacoraProyecto() {
        return bloquearPagSigBitacoraProyecto;
    }

    public void setBloquearPagSigBitacoraProyecto(boolean bloquearPagSigBitacoraProyecto) {
        this.bloquearPagSigBitacoraProyecto = bloquearPagSigBitacoraProyecto;
    }

    public boolean isBloquearPagAntBitacoraProyecto() {
        return bloquearPagAntBitacoraProyecto;
    }

    public void setBloquearPagAntBitacoraProyecto(boolean bloquearPagAntBitacoraProyecto) {
        this.bloquearPagAntBitacoraProyecto = bloquearPagAntBitacoraProyecto;
    }

    public String getCantidadRegistros() {
        return cantidadRegistros;
    }

    public void setCantidadRegistros(String cantidadRegistros) {
        this.cantidadRegistros = cantidadRegistros;
    }

    public BigInteger getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(BigInteger idProyecto) {
        this.idProyecto = idProyecto;
    }

}
