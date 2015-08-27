/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.bo.interfaces.inventario;

import com.java.ucdit.entidades.Proveedor;
import java.math.BigInteger;
import java.util.List;

/**
 *
 * @author ELECTRONICA
 */
public interface AdministrarProveedoresBOInterface {

    public List<Proveedor> consultarProveedoresRegistrados();

    public void crearProveedor(Proveedor proveedor);

    public void editarProveedor(Proveedor proveedor);

    public Proveedor obtenerProveedorPorId(BigInteger idProveedor);

    public Proveedor obtenerProveedorPorNit(String nitProveedor);
}
