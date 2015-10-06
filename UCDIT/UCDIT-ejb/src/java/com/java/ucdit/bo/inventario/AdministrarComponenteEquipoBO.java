/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.bo.inventario;

import com.java.ucdit.bo.interfaces.inventario.AdministrarComponenteEquipoBOInterface;
import com.java.ucdit.entidades.Componente;
import com.java.ucdit.entidades.EquipoTecnologico;
import com.java.ucdit.facade.ComponenteFacade;
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
public class AdministrarComponenteEquipoBO implements AdministrarComponenteEquipoBOInterface {

    @EJB
    ComponenteFacade componenteFacade;
    @EJB
    EquipoTecnologicoFacade equipoTecnologicoFacade;

    @Override
    public EquipoTecnologico obtenerEquipoTecnologicoPorId(BigInteger idEquipo) {
        try {
            EquipoTecnologico registro = equipoTecnologicoFacade.find(idEquipo);
            return registro;
        } catch (Exception e) {
            System.out.println("Error AdministrarComponenteEquipoBO obtenerEquipoTecnologicoPorId: " + e.toString());
            return null;
        }
    }

    @Override
    public List<Componente> obtenerComponentesPorIdEquipo(BigInteger idEquipo) {
        try {
            List<Componente> lista = componenteFacade.consultarComponentesPorIdEquipo(idEquipo);
            return lista;
        } catch (Exception e) {
            System.out.println("Error AdministrarComponenteEquipoBO obtenerComponentesPorIdEquipo: " + e.toString());
            return null;
        }
    }

    @Override
    public void crearComponenteEquipo(Componente componente) {
        try {
            componenteFacade.create(componente);
        } catch (Exception e) {
            System.out.println("Error AdministrarComponenteEquipoBO crearComponenteEquipo: " + e.toString());
        }
    }

    @Override
    public void editarComponenteEquipo(Componente componente) {
        try {
            componenteFacade.edit(componente);
        } catch (Exception e) {
            System.out.println("Error AdministrarComponenteEquipoBO editarComponenteEquipo: " + e.toString());
        }
    }

    @Override
    public Componente validarCodigoRepetidoComponente(String codigo, BigInteger equipo) {
        try {
            Componente registro = componenteFacade.consultarComponentePorCodigoIdComponente(codigo, equipo);
            return registro;
        } catch (Exception e) {
            System.out.println("Error AdministrarComponenteEquipoBO validarCodigoRepetidoComponente: " + e.toString());
            return null;
        }
    }

    @Override
    public Componente obtenerComponentePorId(BigInteger idComponete) {
        try {
            Componente registro = componenteFacade.find(idComponete);
            return registro;
        } catch (Exception e) {
            System.out.println("Error AdministrarComponenteEquipoBO obtenerComponentePorId: " + e.toString());
            return null;
        }
    }
}
