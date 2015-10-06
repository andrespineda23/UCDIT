/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.bo.proyecto;

import com.java.ucdit.bo.interfaces.proyecto.AdministrarGastoAdicionalProyectoBOInterface;
import com.java.ucdit.entidades.GastoAdicional;
import com.java.ucdit.entidades.Proyecto;
import com.java.ucdit.facade.GastoAdicionalFacade;
import com.java.ucdit.facade.ProyectoFacade;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author ELECTRONICA
 */
@Stateful
public class AdministrarGastoAdicionalProyectoBO implements AdministrarGastoAdicionalProyectoBOInterface {

    @EJB
    ProyectoFacade proyectoFacade;
    @EJB
    GastoAdicionalFacade gastoAdicionalFacade;
    
    @Override
    public List<GastoAdicional> consultarGastoAdicionalPorIdProyecto(BigInteger idProyecto) {
        try {
            List<GastoAdicional> lista = gastoAdicionalFacade.consultarGastoAdicionalPorIdProyecto(idProyecto);
            return lista;
        } catch (Exception e) {
            System.out.println("Error AdministrarGastoAdicionalProyectoBO consultarGastoAdicionalPorIdProyecto: " + e.toString());
            return null;
        }
    }

    @Override
    public void crearGastoAdicional(GastoAdicional gastoAdicional) {
        try {
            gastoAdicionalFacade.create(gastoAdicional);
        } catch (Exception e) {
            System.out.println("Error AdministrarGastoAdicionalProyectoBO crearGastoAdicional: " + e.toString());
        }
    }

    @Override
    public void editarGastoAdicional(GastoAdicional gastoAdicional) {
        try {
            gastoAdicionalFacade.edit(gastoAdicional);
        } catch (Exception e) {
            System.out.println("Error AdministrarGastoAdicionalProyectoBO editarGastoAdicional: " + e.toString());
        }
    }

    @Override
    public Proyecto obtenerProyectoPorId(BigInteger idProyecto) {
        try {
            Proyecto registro = proyectoFacade.find(idProyecto);
            return registro;
        } catch (Exception e) {
            System.out.println("Error AdministrarGastoAdicionalProyectoBO obtenerProyectoPorId: " + e.toString());
            return null;
        }
    }

    @Override
    public GastoAdicional obtenerGastoAdicionalPorId(BigInteger idRegistro) {
        try {
            GastoAdicional registro = gastoAdicionalFacade.find(idRegistro);
            return registro;
        } catch (Exception e) {
            System.out.println("Error AdministrarGastoAdicionalProyectoBO obtenerGastoAdicionalPorId: " + e.toString());
            return null;
        }
    }
}
