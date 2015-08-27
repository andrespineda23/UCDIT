/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.bo.inventario;

import com.java.ucdit.bo.interfaces.inventario.AdministrarProveedoresBOInterface;
import com.java.ucdit.entidades.Proveedor;
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
public class AdministrarProveedoresBO implements AdministrarProveedoresBOInterface {

    @EJB
    private ProveedorFacade proveedorFacade;

    @Override
    public List<Proveedor> consultarProveedoresRegistrados() {
        try {
            List<Proveedor> lista = proveedorFacade.findAll();
            return lista;
        } catch (Exception e) {
            System.out.println("Error AdministrarProveedoresBO consultarProveedoresRegistrados: " + e.toString());
            return null;
        }
    }

    @Override
    public void crearProveedor(Proveedor proveedor) {
        try {
            proveedorFacade.create(proveedor);
        } catch (Exception e) {
            System.out.println("Error AdministrarProveedoresBO crearProveedor: " + e.toString());
        }
    }

    @Override
    public void editarProveedor(Proveedor proveedor) {
        try {
            proveedorFacade.edit(proveedor);
        } catch (Exception e) {
            System.out.println("Error AdministrarProveedoresBO editarProveedor: " + e.toString());
        }
    }

    @Override
    public Proveedor obtenerProveedorPorId(BigInteger idProveedor) {
        try {
            Proveedor registro = proveedorFacade.find(idProveedor);
            return registro;
        } catch (Exception e) {
            System.out.println("Error AdministrarProveedoresBO obtenerProveedorPorId: " + e.toString());
            return null;
        }
    }

    @Override
    public Proveedor obtenerProveedorPorNit(String nitProveedor) {
        try {
            Proveedor registro = proveedorFacade.obtenerProveedorPorNit(nitProveedor);
            return registro;
        } catch (Exception e) {
            System.out.println("Error AdministrarProveedoresBO obtenerProveedorPorNit: " + e.toString());
            return null;
        }
    }

}
