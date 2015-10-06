/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.bo.interfaces.inventario;

import com.java.ucdit.entidades.EquipoTecnologico;
import com.java.ucdit.entidades.ManualEquipo;
import java.math.BigInteger;
import java.util.List;

/**
 *
 * @author ELECTRONICA
 */
public interface AdministrarManualEquipoBOInterface {

    public EquipoTecnologico obtenerEquipoTecnologicoPorId(BigInteger idEquipo);

    public List<ManualEquipo> obtenerManualEquiposPorIdEquipo(BigInteger idEquipo);

    public void crearManualEquipo(ManualEquipo manualEquipo);

    public void editarManualEquipo(ManualEquipo manualEquipo);

    public ManualEquipo obtenerManualEquipoPorId(BigInteger integer);
}
