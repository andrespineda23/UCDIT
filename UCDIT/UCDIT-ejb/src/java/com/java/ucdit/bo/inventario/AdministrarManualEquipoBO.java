/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.bo.inventario;

import com.java.ucdit.bo.interfaces.inventario.AdministrarManualEquipoBOInterface;
import com.java.ucdit.entidades.ManualEquipo;
import com.java.ucdit.entidades.EquipoTecnologico;
import com.java.ucdit.facade.ManualEquipoFacade;
import com.java.ucdit.facade.EquipoTecnologicoFacade;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author ELECTRONICA
 */
@Stateful
public class AdministrarManualEquipoBO implements AdministrarManualEquipoBOInterface{

    @EJB
    ManualEquipoFacade manualEquipoFacade;
    @EJB
    EquipoTecnologicoFacade equipoTecnologicoFacade;

    @Override
    public EquipoTecnologico obtenerEquipoTecnologicoPorId(BigInteger idEquipo) {
        try {
            EquipoTecnologico registro = equipoTecnologicoFacade.find(idEquipo);
            return registro;
        } catch (Exception e) {
            System.out.println("Error AdministrarManualEquipoEquipoBO obtenerEquipoTecnologicoPorId: " + e.toString());
            return null;
        }
    }

    @Override
    public List<ManualEquipo> obtenerManualEquiposPorIdEquipo(BigInteger idEquipo) {
        try {
            List<ManualEquipo> lista = manualEquipoFacade.consultarManualEquiposPorIdEquipo(idEquipo);
            return lista;
        } catch (Exception e) {
            System.out.println("Error AdministrarManualEquipoEquipoBO obtenerManualEquiposPorIdEquipo: " + e.toString());
            return null;
        }
    }

    @Override
    public void crearManualEquipo(ManualEquipo manualEquipo) {
        try {
            manualEquipoFacade.create(manualEquipo);
        } catch (Exception e) {
            System.out.println("Error AdministrarManualEquipoEquipoBO crearManualEquipoEquipo: " + e.toString());
        }
    }

    @Override
    public void editarManualEquipo(ManualEquipo manualEquipo) {
        try {
            manualEquipoFacade.edit(manualEquipo);
        } catch (Exception e) {
            System.out.println("Error AdministrarManualEquipoEquipoBO editarManualEquipoEquipo: " + e.toString());
        }
    }

    @Override
    public ManualEquipo obtenerManualEquipoPorId(BigInteger integer) {
        try {
            ManualEquipo registro = manualEquipoFacade.find(integer);
            return registro;
        } catch (Exception e) {
            System.out.println("Error AdministrarManualEquipoEquipoBO obtenerManualEquipoPorId: " + e.toString());
            return null;
        }
    }
}
