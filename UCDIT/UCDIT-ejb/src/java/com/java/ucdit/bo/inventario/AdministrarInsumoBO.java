/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.bo.inventario;

import com.java.ucdit.bo.interfaces.inventario.AdministrarInsumoBOInterface;
import com.java.ucdit.entidades.Insumo;
import com.java.ucdit.entidades.Proveedor;
import com.java.ucdit.entidades.TipoUnidad;
import com.java.ucdit.facade.InsumoFacade;
import com.java.ucdit.facade.ProveedorFacade;
import com.java.ucdit.facade.TipoUnidadFacade;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author ELECTRONICA
 */
@Stateful
public class AdministrarInsumoBO implements AdministrarInsumoBOInterface {

    @EJB
    InsumoFacade insumoFacade;
    @EJB
    ProveedorFacade proveedorFacade;
    @EJB
    TipoUnidadFacade tipoUnidadFacade;

    //@Override
    public List<Insumo> consultarInsumosRegistrados() {
        try {
            List<Insumo> lista = insumoFacade.findAll();
            return lista;
        } catch (Exception e) {
            System.out.println("Error AdministrarInsumosBO consultarInsumosRegistrados: " + e.toString());
            return null;
        }
    }

    @Override
    public void crearInsumo(Insumo insumo) {
        try {
            insumoFacade.create(insumo);
        } catch (Exception e) {
            System.out.println("Error AdministrarInsumosBO crearInsumo: " + e.toString());
        }
    }

    @Override
    public void editarInsumo(Insumo insumo) {
        try {
            insumoFacade.edit(insumo);
        } catch (Exception e) {
            System.out.println("Error AdministrarInsumosBO editarInsumo: " + e.toString());
        }
    }

    @Override
    public Insumo obtenerInsumoPorId(BigInteger idInsumo) {
        try {
            Insumo registro = insumoFacade.find(idInsumo);
            return registro;
        } catch (Exception e) {
            System.out.println("Error AdministrarInsumosBO obtenerInsumoPorId: " + e.toString());
            return null;
        }
    }

    @Override
    public Insumo obtenerInsumoPorCodigo(String codigoInsumo) {
        try {
            Insumo registro = insumoFacade.obtenerInsumoPorCodigo(codigoInsumo);
            return registro;
        } catch (Exception e) {
            System.out.println("Error AdministrarInsumosBO obtenerInsumoPorCodigo: " + e.toString());
            return null;
        }
    }

    @Override
    public List<Proveedor> consultarProveedoresRegistrados() {
        try {
            List<Proveedor> lista = proveedorFacade.findAll();
            return lista;
        } catch (Exception e) {
            System.out.println("Error AdministrarInsumosBO consultarProveedoresRegistrados: " + e.toString());
            return null;
        }
    }

    @Override
    public List<TipoUnidad> consultarTipoUnidadRegistrado() {
        try {
            List<TipoUnidad> lista = tipoUnidadFacade.findAll();
            return lista;
        } catch (Exception e) {
            System.out.println("Error AdministrarInsumosBO consultarInsumosRegistrados: " + e.toString());
            return null;
        }
    }

}
