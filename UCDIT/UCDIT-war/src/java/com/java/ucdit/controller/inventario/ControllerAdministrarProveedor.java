/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.controller.inventario;

import com.java.ucdit.bo.interfaces.inventario.AdministrarProveedoresBOInterface;
import com.java.ucdit.entidades.Proveedor;
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
public class ControllerAdministrarProveedor implements Serializable {

    @EJB
    AdministrarProveedoresBOInterface administrarProveedoresBO;
    //
    private List<Proveedor> listaProveedores;
    private List<Proveedor> listaProveedoresTabla;
    private int posicionProveedorTabla;
    private int tamTotalProveedor;
    private boolean bloquearPagSigProveedor, bloquearPagAntProveedor;
    private String cantidadRegistros;
    private String paginaAnterior;

    public ControllerAdministrarProveedor() {
    }

    @PostConstruct
    public void init() {
    }

    private void iniciarDatosTabla() {
        cantidadRegistros = "N/A";
        listaProveedores = null;
        listaProveedoresTabla = null;
        posicionProveedorTabla = 0;
        tamTotalProveedor = 0;
        bloquearPagAntProveedor = true;
        bloquearPagSigProveedor = true;
    }

    public void buscarProveedoresRegistrador(String page) {
        this.paginaAnterior = page;
        iniciarDatosTabla();
        try {
            listaProveedores = null;
            listaProveedores = administrarProveedoresBO.consultarProveedoresRegistrados();
            if (listaProveedores != null) {
                if (listaProveedores.size() > 0) {
                    listaProveedoresTabla = new ArrayList<Proveedor>();
                    tamTotalProveedor = listaProveedores.size();
                    posicionProveedorTabla = 0;
                    cantidadRegistros = String.valueOf(tamTotalProveedor);
                    cargarDatosTablaProveedor();
                } else {
                    listaProveedoresTabla = null;
                    tamTotalProveedor = 0;
                    posicionProveedorTabla = 0;
                    bloquearPagAntProveedor = true;
                    cantidadRegistros = String.valueOf(tamTotalProveedor);
                    bloquearPagSigProveedor = true;
                }
            } else {
                listaProveedoresTabla = null;
                tamTotalProveedor = 0;
                posicionProveedorTabla = 0;
                cantidadRegistros = String.valueOf(tamTotalProveedor);
                bloquearPagAntProveedor = true;
                bloquearPagSigProveedor = true;
            }
        } catch (Exception e) {
            System.out.println("Error ControllerAdministrarProveedor buscarProveedoresRegistrador : " + e.toString());
        }
    }

    private void cargarDatosTablaProveedor() {
        if (tamTotalProveedor < 5) {
            for (int i = 0; i < tamTotalProveedor; i++) {
                listaProveedoresTabla.add(listaProveedores.get(i));
            }
            bloquearPagSigProveedor = true;
            bloquearPagAntProveedor = true;
        } else {
            for (int i = 0; i < 5; i++) {
                listaProveedoresTabla.add(listaProveedores.get(i));
            }
            bloquearPagSigProveedor = false;
            bloquearPagAntProveedor = true;
        }
    }

    public void cargarPaginaSiguienteProveedor() {
        listaProveedoresTabla = new ArrayList<Proveedor>();
        posicionProveedorTabla = posicionProveedorTabla + 5;
        int diferencia = tamTotalProveedor - posicionProveedorTabla;
        if (diferencia > 5) {
            for (int i = posicionProveedorTabla; i < (posicionProveedorTabla + 5); i++) {
                listaProveedoresTabla.add(listaProveedores.get(i));
            }
            bloquearPagSigProveedor = false;
            bloquearPagAntProveedor = false;
        } else {
            for (int i = posicionProveedorTabla; i < (posicionProveedorTabla + diferencia); i++) {
                listaProveedoresTabla.add(listaProveedores.get(i));
            }
            bloquearPagSigProveedor = true;
            bloquearPagAntProveedor = false;
        }
    }

    public void cargarPaginaAnteriorProveedor() {
        listaProveedoresTabla = new ArrayList<Proveedor>();
        posicionProveedorTabla = posicionProveedorTabla - 5;
        int diferencia = tamTotalProveedor - posicionProveedorTabla;
        if (diferencia == tamTotalProveedor) {
            for (int i = posicionProveedorTabla; i < (posicionProveedorTabla + 5); i++) {
                listaProveedoresTabla.add(listaProveedores.get(i));
            }
            bloquearPagSigProveedor = false;
            bloquearPagAntProveedor = true;
        } else {
            for (int i = posicionProveedorTabla; i < (posicionProveedorTabla + 5); i++) {
                listaProveedoresTabla.add(listaProveedores.get(i));
            }
            bloquearPagSigProveedor = false;
            bloquearPagAntProveedor = false;
        }
    }

    public String limpiarProcesoBusqueda() {
        listaProveedores = null;
        listaProveedoresTabla = null;
        posicionProveedorTabla = 0;
        tamTotalProveedor = 0;
        bloquearPagAntProveedor = true;
        bloquearPagSigProveedor = true;
        cantidadRegistros = "N/A";
        return paginaAnterior;
    }

    public String paginaDetalles() {
        limpiarProcesoBusqueda();
        return "detallesproveedor";
    }

    // GET - SET
    public List<Proveedor> getListaProveedores() {
        return listaProveedores;
    }

    public void setListaProveedores(List<Proveedor> listaProveedores) {
        this.listaProveedores = listaProveedores;
    }

    public List<Proveedor> getListaProveedoresTabla() {
        return listaProveedoresTabla;
    }

    public void setListaProveedoresTabla(List<Proveedor> listaProveedoresTabla) {
        this.listaProveedoresTabla = listaProveedoresTabla;
    }

    public int getPosicionProveedorTabla() {
        return posicionProveedorTabla;
    }

    public void setPosicionProveedorTabla(int posicionProveedorTabla) {
        this.posicionProveedorTabla = posicionProveedorTabla;
    }

    public int getTamTotalProveedor() {
        return tamTotalProveedor;
    }

    public void setTamTotalProveedor(int tamTotalProveedor) {
        this.tamTotalProveedor = tamTotalProveedor;
    }

    public boolean isBloquearPagSigProveedor() {
        return bloquearPagSigProveedor;
    }

    public void setBloquearPagSigProveedor(boolean bloquearPagSigProveedor) {
        this.bloquearPagSigProveedor = bloquearPagSigProveedor;
    }

    public boolean isBloquearPagAntProveedor() {
        return bloquearPagAntProveedor;
    }

    public void setBloquearPagAntProveedor(boolean bloquearPagAntProveedor) {
        this.bloquearPagAntProveedor = bloquearPagAntProveedor;
    }

    public String getCantidadRegistros() {
        return cantidadRegistros;
    }

    public void setCantidadRegistros(String cantidadRegistros) {
        this.cantidadRegistros = cantidadRegistros;
    }

}
