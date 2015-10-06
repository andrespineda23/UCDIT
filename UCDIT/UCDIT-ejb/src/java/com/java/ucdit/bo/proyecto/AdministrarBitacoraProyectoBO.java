/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.bo.proyecto;

import com.java.ucdit.bo.interfaces.proyecto.AdministrarBitacoraProyectoBOInterface;
import com.java.ucdit.entidades.BitacoraProyecto;
import com.java.ucdit.entidades.EquipoPorProyecto;
import com.java.ucdit.entidades.EquipoTecnologico;
import com.java.ucdit.entidades.Proyecto;
import com.java.ucdit.facade.BitacoraProyectoFacade;
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
public class AdministrarBitacoraProyectoBO implements AdministrarBitacoraProyectoBOInterface {

    @EJB
    ProyectoFacade proyectoFacade;
    @EJB
    BitacoraProyectoFacade bitacoraProyectoFacade;

    //@Override
    public List<BitacoraProyecto> consultarBitacoraPorIdProyecto(BigInteger idProyecto) {
        try {
            List<BitacoraProyecto> lista = bitacoraProyectoFacade.obtenerBitacoraProyectoPorIdProyecto(idProyecto);
            return lista;
        } catch (Exception e) {
            System.out.println("Error AdministrarEquipoPorProyectoBO consultarBitacoraPorIdProyecto: " + e.toString());
            return null;
        }
    }

    @Override
    public void crearBitacoraProyecto(BitacoraProyecto bitacoraProyecto) {
        try {
            bitacoraProyectoFacade.create(bitacoraProyecto);
        } catch (Exception e) {
            System.out.println("Error AdministrarEquipoPorProyectoBO crearBitacoraProyecto: " + e.toString());
        }
    }

    @Override
    public void editarBitacoraProyecto(BitacoraProyecto bitacoraProyecto) {
        try {
            bitacoraProyectoFacade.edit(bitacoraProyecto);
        } catch (Exception e) {
            System.out.println("Error AdministrarEquipoPorProyectoBO editarBitacoraProyecto: " + e.toString());
        }
    }

    @Override
    public Proyecto obtenerProyectoPorId(BigInteger idProyecto) {
        try {
            Proyecto registro = proyectoFacade.find(idProyecto);
            return registro;
        } catch (Exception e) {
            System.out.println("Error AdministrarProyectoPorProyectosBO obtenerProyectoPorId: " + e.toString());
            return null;
        }
    }

    @Override
    public BitacoraProyecto obtenerBitacoraProyectoPorId(BigInteger idRegistro) {
        try {
            BitacoraProyecto registro = bitacoraProyectoFacade.find(idRegistro);
            return registro;
        } catch (Exception e) {
            System.out.println("Error AdministrarProyectoPorProyectosBO obtenerBitacoraProyectoPorId: " + e.toString());
            return null;
        }
    }
}
