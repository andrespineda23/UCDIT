/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.bo.interfaces.inventario;

import com.java.ucdit.entidades.IngresoInsumo;
import com.java.ucdit.entidades.Insumo;
import com.java.ucdit.entidades.Proveedor;
import java.math.BigInteger;
import java.util.List;

/**
 *
 * @author ELECTRONICA
 */
public interface AdministrarIngresoInsumoBOInterface {

    public List<IngresoInsumo> consultarIngresosInsumoRegistrados(BigInteger idInsumo);

    public void crearIngresoInsumo(IngresoInsumo ingreso);

    public void editarIngresoInsumo(IngresoInsumo ingreso);

    public IngresoInsumo obtenerIngresoInsumoPorId(BigInteger idIngresoInsumo);

    public List<Insumo> obtenerInsumosRegistrados();

    public Insumo obtenerInsumosPorId(BigInteger insumo);

    public List<Proveedor> obtenerProveedoresRegistrados();
}
