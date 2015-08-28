/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.controller.cliente;

import com.java.ucdit.bo.interfaces.cliente.AdministrarClienteBOInterface;
import com.java.ucdit.entidades.Cliente;
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
public class ControllerAdministrarCliente implements Serializable {

    @EJB
    AdministrarClienteBOInterface administrarClienteBO;
    //
    private List<Cliente> listaClientes;
    private List<Cliente> listaClientesTabla;
    private int posicionClienteTabla;
    private int tamTotalCliente;
    private boolean bloquearPagSigCliente, bloquearPagAntCliente;
    private String cantidadRegistros;

    public ControllerAdministrarCliente() {
    }

    @PostConstruct
    public void init() {
        buscarClienteRegistrador();
    }

    private void iniciarDatosTabla() {
        cantidadRegistros = "N/A";
        listaClientes = null;
        listaClientesTabla = null;
        posicionClienteTabla = 0;
        tamTotalCliente = 0;
        bloquearPagAntCliente = true;
        bloquearPagSigCliente = true;
    }

    public void buscarClienteRegistrador() {
        iniciarDatosTabla();
        try {
            listaClientes = null;
            listaClientes = administrarClienteBO.consultarClientesRegistrados();
            if (listaClientes != null) {
                if (listaClientes.size() > 0) {
                    listaClientesTabla = new ArrayList<Cliente>();
                    tamTotalCliente = listaClientes.size();
                    posicionClienteTabla = 0;
                    cantidadRegistros = String.valueOf(tamTotalCliente);
                    cargarDatosTablaCliente();
                } else {
                    listaClientesTabla = null;
                    tamTotalCliente = 0;
                    posicionClienteTabla = 0;
                    bloquearPagAntCliente = true;
                    cantidadRegistros = String.valueOf(tamTotalCliente);
                    bloquearPagSigCliente = true;
                }
            } else {
                listaClientesTabla = null;
                tamTotalCliente = 0;
                posicionClienteTabla = 0;
                cantidadRegistros = String.valueOf(tamTotalCliente);
                bloquearPagAntCliente = true;
                bloquearPagSigCliente = true;
            }
        } catch (Exception e) {
            System.out.println("Error ControllerAdministrarCliente buscarClienteRegistrador : " + e.toString());
        }
    }

    private void cargarDatosTablaCliente() {
        if (tamTotalCliente < 5) {
            for (int i = 0; i < tamTotalCliente; i++) {
                listaClientesTabla.add(listaClientes.get(i));
            }
            bloquearPagSigCliente = true;
            bloquearPagAntCliente = true;
        } else {
            for (int i = 0; i < 5; i++) {
                listaClientesTabla.add(listaClientes.get(i));
            }
            bloquearPagSigCliente = false;
            bloquearPagAntCliente = true;
        }
    }

    public void cargarPaginaSiguienteCliente() {
        listaClientesTabla = new ArrayList<Cliente>();
        posicionClienteTabla = posicionClienteTabla + 5;
        int diferencia = tamTotalCliente - posicionClienteTabla;
        if (diferencia > 5) {
            for (int i = posicionClienteTabla; i < (posicionClienteTabla + 5); i++) {
                listaClientesTabla.add(listaClientes.get(i));
            }
            bloquearPagSigCliente = false;
            bloquearPagAntCliente = false;
        } else {
            for (int i = posicionClienteTabla; i < (posicionClienteTabla + diferencia); i++) {
                listaClientesTabla.add(listaClientes.get(i));
            }
            bloquearPagSigCliente = true;
            bloquearPagAntCliente = false;
        }
    }

    public void cargarPaginaAnteriorCliente() {
        listaClientesTabla = new ArrayList<Cliente>();
        posicionClienteTabla = posicionClienteTabla - 5;
        int diferencia = tamTotalCliente - posicionClienteTabla;
        if (diferencia == tamTotalCliente) {
            for (int i = posicionClienteTabla; i < (posicionClienteTabla + 5); i++) {
                listaClientesTabla.add(listaClientes.get(i));
            }
            bloquearPagSigCliente = false;
            bloquearPagAntCliente = true;
        } else {
            for (int i = posicionClienteTabla; i < (posicionClienteTabla + 5); i++) {
                listaClientesTabla.add(listaClientes.get(i));
            }
            bloquearPagSigCliente = false;
            bloquearPagAntCliente = false;
        }
    }

    public void limpiarProcesoBusqueda() {
        listaClientes = null;
        listaClientesTabla = null;
        posicionClienteTabla = 0;
        tamTotalCliente = 0;
        bloquearPagAntCliente = true;
        bloquearPagSigCliente = true;
        cantidadRegistros = "N/A";
    }

    public String paginaDetalles() {
        limpiarProcesoBusqueda();
        return "detallescliente";
    }

    // GET - SET
    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }

    public List<Cliente> getListaClientesTabla() {
        return listaClientesTabla;
    }

    public void setListaClientesTabla(List<Cliente> listaClientesTabla) {
        this.listaClientesTabla = listaClientesTabla;
    }

    public int getPosicionClienteTabla() {
        return posicionClienteTabla;
    }

    public void setPosicionClienteTabla(int posicionClienteTabla) {
        this.posicionClienteTabla = posicionClienteTabla;
    }

    public int getTamTotalCliente() {
        return tamTotalCliente;
    }

    public void setTamTotalCliente(int tamTotalCliente) {
        this.tamTotalCliente = tamTotalCliente;
    }

    public boolean isBloquearPagSigCliente() {
        return bloquearPagSigCliente;
    }

    public void setBloquearPagSigCliente(boolean bloquearPagSigCliente) {
        this.bloquearPagSigCliente = bloquearPagSigCliente;
    }

    public boolean isBloquearPagAntCliente() {
        return bloquearPagAntCliente;
    }

    public void setBloquearPagAntCliente(boolean bloquearPagAntCliente) {
        this.bloquearPagAntCliente = bloquearPagAntCliente;
    }

    public String getCantidadRegistros() {
        return cantidadRegistros;
    }

    public void setCantidadRegistros(String cantidadRegistros) {
        this.cantidadRegistros = cantidadRegistros;
    }

}
