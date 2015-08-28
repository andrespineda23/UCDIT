/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.bo.interfaces.inventario;

import com.java.ucdit.entidades.Insumo;
import com.java.ucdit.entidades.Proveedor;
import com.java.ucdit.entidades.TipoUnidad;
import java.math.BigInteger;
import java.util.List;

/**
 *
 * @author ELECTRONICA
 */
public interface AdministrarInsumoBOInterface {

    public List<Insumo> consultarInsumosRegistrados();

    public void crearInsumo(Insumo insumo);

    public void editarInsumo(Insumo insumo);

    public Insumo obtenerInsumoPorId(BigInteger idInsumo);

    public Insumo obtenerInsumoPorCodigo(String codigoInsumo);

    public List<Proveedor> consultarProveedoresRegistrados();

    public List<TipoUnidad> consultarTipoUnidadRegistrado();
}
