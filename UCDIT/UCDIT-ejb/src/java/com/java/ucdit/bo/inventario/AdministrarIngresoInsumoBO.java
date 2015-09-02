/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.bo.inventario;

import com.java.ucdit.bo.interfaces.inventario.AdministrarIngresoInsumoBOInterface;
import com.java.ucdit.entidades.IngresoInsumo;
import com.java.ucdit.entidades.Insumo;
import com.java.ucdit.entidades.Proveedor;
import com.java.ucdit.facade.IngresoInsumoFacade;
import com.java.ucdit.facade.InsumoFacade;
import com.java.ucdit.facade.ProveedorFacade;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author ELECTRONICA
 */
@Stateful
public class AdministrarIngresoInsumoBO implements AdministrarIngresoInsumoBOInterface {

    @EJB
    IngresoInsumoFacade ingresoInsumoFacade;
    @EJB
    InsumoFacade insumoFacade;
    @EJB
    ProveedorFacade proveedorFacade;

    //@Override
    public List<IngresoInsumo> consultarIngresosInsumoRegistrados(BigInteger idInsumo) {
        try {
            List<IngresoInsumo> lista = ingresoInsumoFacade.obtenerIngresoInsumoPorIdInsumo(idInsumo);
            return lista;
        } catch (Exception e) {
            System.out.println("Error AdministrarIngresosInsumoBO consultarIngresosInsumoRegistrados: " + e.toString());
            return null;
        }
    }

    @Override
    public void crearIngresoInsumo(IngresoInsumo ingreso) {
        try {
            Insumo insumo = ingreso.getInsumo();
            int cantidad = insumo.getCantidadexistencia() + ingreso.getCantidad();
            insumo.setCantidadexistencia(cantidad);
            insumoFacade.edit(insumo);
            ingresoInsumoFacade.create(ingreso);
        } catch (Exception e) {
            System.out.println("Error AdministrarIngresosInsumoBO crearIngresoInsumo: " + e.toString());
        }
    }

    @Override
    public void editarIngresoInsumo(IngresoInsumo ingreso) {
        try {
            ingresoInsumoFacade.edit(ingreso);
        } catch (Exception e) {
            System.out.println("Error AdministrarIngresosInsumoBO editarIngresoInsumo: " + e.toString());
        }
    }

    @Override
    public IngresoInsumo obtenerIngresoInsumoPorId(BigInteger idIngresoInsumo) {
        try {
            IngresoInsumo registro = ingresoInsumoFacade.find(idIngresoInsumo);
            return registro;
        } catch (Exception e) {
            System.out.println("Error AdministrarIngresosInsumoBO obtenerIngresoInsumoPorId: " + e.toString());
            return null;
        }
    }

    @Override
    public List<Insumo> obtenerInsumosRegistrados() {
        try {
            List<Insumo> lista = insumoFacade.findAll();
            return lista;
        } catch (Exception e) {
            System.out.println("Error AdministrarIngresosInsumoBO obtenerInsumosRegistrados: " + e.toString());
            return null;
        }
    }

    @Override
    public List<Proveedor> obtenerProveedoresRegistrados() {
        try {
            List<Proveedor> lista = proveedorFacade.findAll();
            return lista;
        } catch (Exception e) {
            System.out.println("Error AdministrarIngresosInsumoBO obtenerProveedoresRegistrados: " + e.toString());
            return null;
        }
    }

    @Override
    public Insumo obtenerInsumosPorId(BigInteger insumo) {
        try {
            Insumo registro = insumoFacade.find(insumo);
            return registro;
        } catch (Exception e) {
            System.out.println("Error AdministrarIngresosInsumoBO obtenerInsumosPorId: " + e.toString());
            return null;
        }
    }

}
