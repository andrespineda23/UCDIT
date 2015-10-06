/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.bo.interfaces.inventario;

import com.java.ucdit.entidades.EquipoTecnologico;
import com.java.ucdit.entidades.IngresoEquipo;
import com.java.ucdit.entidades.Proveedor;
import com.java.ucdit.entidades.TipoEquipo;
import java.math.BigInteger;
import java.util.List;

/**
 *
 * @author ELECTRONICA
 */
public interface AdministrarEquipoTecnologicoBOInterface {

    public List<EquipoTecnologico> consultarEquiposTecnologicosRegistrados();

    public void crearEquipoTecnologico(EquipoTecnologico equipo, String factura);

    public void editarEquipoTecnologico(IngresoEquipo equipo);

    public IngresoEquipo obtenerIngresoEquipoPorIdEquipo(BigInteger idEquipoTecnologico);

    public EquipoTecnologico obtenerEquipoTecnologicoPorId(BigInteger idEquipoTecnologico);

    public EquipoTecnologico obtenerEquipoTecnologicoPorCodigo(String codigoEquipoTecnologico);

    public List<Proveedor> consultarProveedoresRegistrados();

    public List<TipoEquipo> consultarTipoEquipoRegistrado();
}
