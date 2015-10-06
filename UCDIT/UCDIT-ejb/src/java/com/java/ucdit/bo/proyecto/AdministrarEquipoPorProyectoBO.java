/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.bo.proyecto;

import com.java.ucdit.bo.interfaces.proyecto.AdministrarEquipoPorProyectoBOInterface;
import com.java.ucdit.entidades.EquipoPorProyecto;
import com.java.ucdit.entidades.EquipoTecnologico;
import com.java.ucdit.entidades.Proyecto;
import com.java.ucdit.facade.EquipoPorProyectoFacade;
import com.java.ucdit.facade.EquipoTecnologicoFacade;
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
public class AdministrarEquipoPorProyectoBO implements AdministrarEquipoPorProyectoBOInterface {

    @EJB
    EquipoTecnologicoFacade equipoTecnologicoFacade;
    @EJB
    EquipoPorProyectoFacade equipoPorProyectoFacade;
    @EJB
    ProyectoFacade proyectoFacade;

    @Override
    public List<EquipoPorProyecto> consultarEquipoPorProyectosRegistrados(BigInteger idProyecto) {
        try {
            List<EquipoPorProyecto> lista = equipoPorProyectoFacade.obtenerEquipoPorProyectoPorIdProyecto(idProyecto);
            return lista;
        } catch (Exception e) {
            System.out.println("Error AdministrarEquipoPorProyectoBO consultarInsumoPorProyectosRegistrados: " + e.toString());
            return null;
        }
    }

    @Override
    public void crearEquipoPorProyecto(EquipoPorProyecto equipo) {
        try {
            equipoPorProyectoFacade.create(equipo);
        } catch (Exception e) {
            System.out.println("Error AdministrarEquipoPorProyectoBO crearEquipoPorProyecto: " + e.toString());
        }
    }

    @Override
    public void editarEquipoPorProyecto(EquipoPorProyecto insumo) {
        try {
            equipoPorProyectoFacade.edit(insumo);
        } catch (Exception e) {
            System.out.println("Error AdministrarEquipoPorProyectoBO editarEquipoPorProyecto: " + e.toString());
        }
    }

    @Override
    public List<EquipoTecnologico> obtenerEquiposTecnologicosRegistrados() {
        try {
            List<EquipoTecnologico> lista = equipoTecnologicoFacade.findAll();
            return lista;
        } catch (Exception e) {
            System.out.println("Error AdministrarEquipoPorProyectoBO obtenerEquiposTecnologicosRegistrados: " + e.toString());
            return null;
        }
    }

    @Override
    public List<Proyecto> obtenerProyectosRegistrados() {
        try {
            List<Proyecto> lista = proyectoFacade.findAll();
            return lista;
        } catch (Exception e) {
            System.out.println("Error AdministrarEquipoPorProyectoBO obtenerProyectosRegistrados: " + e.toString());
            return null;
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
    public EquipoPorProyecto obtenerEquipoPorProyectoPorId(BigInteger idRegistro) {
        try {
            EquipoPorProyecto registro = equipoPorProyectoFacade.find(idRegistro);
            return registro;
        } catch (Exception e) {
            System.out.println("Error AdministrarProyectoPorProyectosBO obtenerEquipoPorProyectoPorId: " + e.toString());
            return null;
        }
    }
}
