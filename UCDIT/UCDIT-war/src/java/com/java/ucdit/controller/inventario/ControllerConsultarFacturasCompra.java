/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.controller.inventario;

import com.java.ucdit.ayuda.ConsultarFactura;
import com.java.ucdit.bo.interfaces.inventario.AdministrarIngresoInsumoBOInterface;
import com.java.ucdit.entidades.IngresoInsumo;
import com.java.ucdit.entidades.Proveedor;
import com.java.ucdit.utilidades.Utilidades;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
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
public class ControllerConsultarFacturasCompra implements Serializable {

    @EJB
    AdministrarIngresoInsumoBOInterface administrarIngresoInsumosBO;
    //
    private List<ConsultarFactura> listaIngresoInsumos;
    private List<ConsultarFactura> listaIngresoInsumosTabla;
    private int posicionIngresoInsumoTabla;
    private int tamTotalIngresoInsumo;
    private boolean bloquearPagSigIngresoInsumo, bloquearPagAntIngresoInsumo;
    private String cantidadRegistros;

    public ControllerConsultarFacturasCompra() {
    }

    @PostConstruct
    public void init() {
        buscarIngresoInsumosRegistrador();
    }

    private void iniciarDatosTabla() {
        cantidadRegistros = "N/A";
        listaIngresoInsumos = null;
        listaIngresoInsumosTabla = null;
        posicionIngresoInsumoTabla = 0;
        tamTotalIngresoInsumo = 0;
        bloquearPagAntIngresoInsumo = true;
        bloquearPagSigIngresoInsumo = true;
    }

    private void organizarDatosIngresoInsumo(List<IngresoInsumo> lista) {
        if (Utilidades.validarNulo(lista)) {
            int tam = lista.size();
            if (0 != tam) {
                List<String> listaNumeroFactura = new ArrayList<String>();
                List<Proveedor> listaProveedor = new ArrayList<Proveedor>();
                List<Date> listaFecha = new ArrayList<Date>();
                List<Integer> listaValor = new ArrayList<Integer>();
                listaNumeroFactura.add(lista.get(0).getNumerofactura());
                listaProveedor.add(lista.get(0).getProveedor());
                listaFecha.add(lista.get(0).getFechacompra());
                for (int i = 1; i < lista.size(); i++) {
                    if (!listaNumeroFactura.contains(lista.get(i).getNumerofactura())) {
                        listaNumeroFactura.add(lista.get(i).getNumerofactura());
                        listaProveedor.add(lista.get(i).getProveedor());
                        listaFecha.add(lista.get(i).getFechacompra());
                    }
                }
                for (int i = 0; i < listaNumeroFactura.size(); i++) {
                    Integer valor = 0;
                    for (int j = 0; j < lista.size(); j++) {
                        if (listaNumeroFactura.get(i).equalsIgnoreCase(lista.get(j).getNumerofactura())) {
                            valor = valor + lista.get(j).getValorcompra();
                        }
                    }
                    listaValor.add(valor);
                }
                List<ConsultarFactura> auxiliar = new ArrayList<ConsultarFactura>();
                for (int i = 0; i < listaNumeroFactura.size(); i++) {
                    ConsultarFactura registro = new ConsultarFactura();
                    registro.setFechacompra(listaFecha.get(i));
                    registro.setNumerofactura(listaNumeroFactura.get(i));
                    registro.setProveedor(listaProveedor.get(i));
                    registro.setValortotal(String.valueOf(listaValor.get(i)));
                    auxiliar.add(registro);
                }
                listaIngresoInsumos = auxiliar;
            }
        }
    }

    public void buscarIngresoInsumosRegistrador() {
        iniciarDatosTabla();
        try {
            listaIngresoInsumos = null;
            List<IngresoInsumo> lista = administrarIngresoInsumosBO.consultarIngresoInsumoRegistrado();
            organizarDatosIngresoInsumo(lista);
            if (listaIngresoInsumos != null) {
                if (listaIngresoInsumos.size() > 0) {
                    listaIngresoInsumosTabla = new ArrayList<ConsultarFactura>();
                    tamTotalIngresoInsumo = listaIngresoInsumos.size();
                    posicionIngresoInsumoTabla = 0;
                    cantidadRegistros = String.valueOf(tamTotalIngresoInsumo);
                    cargarDatosTablaIngresoInsumo();
                } else {
                    listaIngresoInsumosTabla = null;
                    tamTotalIngresoInsumo = 0;
                    posicionIngresoInsumoTabla = 0;
                    bloquearPagAntIngresoInsumo = true;
                    cantidadRegistros = String.valueOf(tamTotalIngresoInsumo);
                    bloquearPagSigIngresoInsumo = true;
                }
            } else {
                listaIngresoInsumosTabla = null;
                tamTotalIngresoInsumo = 0;
                posicionIngresoInsumoTabla = 0;
                cantidadRegistros = String.valueOf(tamTotalIngresoInsumo);
                bloquearPagAntIngresoInsumo = true;
                bloquearPagSigIngresoInsumo = true;
            }
        } catch (Exception e) {
            System.out.println("Error ControllerAdministrarIngresoInsumo buscarIngresoInsumosRegistrador : " + e.toString());
        }
    }

    private void cargarDatosTablaIngresoInsumo() {
        if (tamTotalIngresoInsumo < 10) {
            for (int i = 0; i < tamTotalIngresoInsumo; i++) {
                listaIngresoInsumosTabla.add(listaIngresoInsumos.get(i));
            }
            bloquearPagSigIngresoInsumo = true;
            bloquearPagAntIngresoInsumo = true;
        } else {
            for (int i = 0; i < 10; i++) {
                listaIngresoInsumosTabla.add(listaIngresoInsumos.get(i));
            }
            bloquearPagSigIngresoInsumo = false;
            bloquearPagAntIngresoInsumo = true;
        }
    }

    public void cargarPaginaSiguienteIngresoInsumo() {
        listaIngresoInsumosTabla = new ArrayList<ConsultarFactura>();
        posicionIngresoInsumoTabla = posicionIngresoInsumoTabla + 10;
        int diferencia = tamTotalIngresoInsumo - posicionIngresoInsumoTabla;
        if (diferencia > 10) {
            for (int i = posicionIngresoInsumoTabla; i < (posicionIngresoInsumoTabla + 10); i++) {
                listaIngresoInsumosTabla.add(listaIngresoInsumos.get(i));
            }
            bloquearPagSigIngresoInsumo = false;
            bloquearPagAntIngresoInsumo = false;
        } else {
            for (int i = posicionIngresoInsumoTabla; i < (posicionIngresoInsumoTabla + diferencia); i++) {
                listaIngresoInsumosTabla.add(listaIngresoInsumos.get(i));
            }
            bloquearPagSigIngresoInsumo = true;
            bloquearPagAntIngresoInsumo = false;
        }
    }

    public void cargarPaginaAnteriorIngresoInsumo() {
        listaIngresoInsumosTabla = new ArrayList<ConsultarFactura>();
        posicionIngresoInsumoTabla = posicionIngresoInsumoTabla - 10;
        int diferencia = tamTotalIngresoInsumo - posicionIngresoInsumoTabla;
        if (diferencia == tamTotalIngresoInsumo) {
            for (int i = posicionIngresoInsumoTabla; i < (posicionIngresoInsumoTabla + 10); i++) {
                listaIngresoInsumosTabla.add(listaIngresoInsumos.get(i));
            }
            bloquearPagSigIngresoInsumo = false;
            bloquearPagAntIngresoInsumo = true;
        } else {
            for (int i = posicionIngresoInsumoTabla; i < (posicionIngresoInsumoTabla + 10); i++) {
                listaIngresoInsumosTabla.add(listaIngresoInsumos.get(i));
            }
            bloquearPagSigIngresoInsumo = false;
            bloquearPagAntIngresoInsumo = false;
        }
    }

    public void limpiarProcesoBusqueda() {
        listaIngresoInsumos = null;
        listaIngresoInsumosTabla = null;
        posicionIngresoInsumoTabla = 0;
        tamTotalIngresoInsumo = 0;
        bloquearPagAntIngresoInsumo = true;
        bloquearPagSigIngresoInsumo = true;
        cantidadRegistros = "N/A";
    }

    // GET - SET
    public List<ConsultarFactura> getListaIngresoInsumos() {
        return listaIngresoInsumos;
    }

    public void setListaIngresoInsumos(List<ConsultarFactura> listaIngresoInsumos) {
        this.listaIngresoInsumos = listaIngresoInsumos;
    }

    public List<ConsultarFactura> getListaIngresoInsumosTabla() {
        return listaIngresoInsumosTabla;
    }

    public void setListaIngresoInsumosTabla(List<ConsultarFactura> listaIngresoInsumosTabla) {
        this.listaIngresoInsumosTabla = listaIngresoInsumosTabla;
    }

    public int getPosicionIngresoInsumoTabla() {
        return posicionIngresoInsumoTabla;
    }

    public void setPosicionIngresoInsumoTabla(int posicionIngresoInsumoTabla) {
        this.posicionIngresoInsumoTabla = posicionIngresoInsumoTabla;
    }

    public int getTamTotalIngresoInsumo() {
        return tamTotalIngresoInsumo;
    }

    public void setTamTotalIngresoInsumo(int tamTotalIngresoInsumo) {
        this.tamTotalIngresoInsumo = tamTotalIngresoInsumo;
    }

    public boolean isBloquearPagSigIngresoInsumo() {
        return bloquearPagSigIngresoInsumo;
    }

    public void setBloquearPagSigIngresoInsumo(boolean bloquearPagSigIngresoInsumo) {
        this.bloquearPagSigIngresoInsumo = bloquearPagSigIngresoInsumo;
    }

    public boolean isBloquearPagAntIngresoInsumo() {
        return bloquearPagAntIngresoInsumo;
    }

    public void setBloquearPagAntIngresoInsumo(boolean bloquearPagAntIngresoInsumo) {
        this.bloquearPagAntIngresoInsumo = bloquearPagAntIngresoInsumo;
    }

    public String getCantidadRegistros() {
        return cantidadRegistros;
    }

    public void setCantidadRegistros(String cantidadRegistros) {
        this.cantidadRegistros = cantidadRegistros;
    }

}
