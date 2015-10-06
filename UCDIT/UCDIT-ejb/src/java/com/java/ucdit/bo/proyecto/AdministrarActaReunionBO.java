/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.bo.proyecto;

import com.java.ucdit.bo.interfaces.proyecto.AdministrarActaReunionBOInterface;
import com.java.ucdit.entidades.ActaReunion;
import com.java.ucdit.entidades.Proyecto;
import com.java.ucdit.facade.ActaReunionFacade;
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
public class AdministrarActaReunionBO implements AdministrarActaReunionBOInterface {

    @EJB
    ProyectoFacade proyectoFacade;
    @EJB
    ActaReunionFacade actaReunionFacade;

    @Override
    public List<Proyecto> obtenerProyectosRegistrados() {
        try {
            List<Proyecto> lista = proyectoFacade.findAll();
            return lista;
        } catch (Exception e) {
            System.out.println("Error AdministrarActaReunionBO obtenerProyectosRegistradosActivos: " + e.toString());
            return null;
        }
    }

    @Override
    public List<ActaReunion> consultarActaReunionPorIdProyecto(BigInteger idProyecto) {
        try {
            List<ActaReunion> lista = actaReunionFacade.obtenerActasReunionPorIdProyecto(idProyecto);
            return lista;
        } catch (Exception e) {
            System.out.println("Error AdministrarActaReunionBO consultarActaReunionPorIdProyecto: " + e.toString());
            return null;
        }
    }

    @Override
    public void crearActaReunion(ActaReunion actaReunion) {
        try {
            actaReunionFacade.create(actaReunion);
        } catch (Exception e) {
            System.out.println("Error AdministrarActaReunionBO obtenerProyectosRegistradosActivos: " + e.toString());
        }
    }

    @Override
    public void editarActaReunion(ActaReunion actaReunion) {
        try {
            actaReunionFacade.edit(actaReunion);
        } catch (Exception e) {
            System.out.println("Error AdministrarActaReunionBO obtenerProyectosRegistradosActivos: " + e.toString());
        }
    }

    @Override
    public Proyecto obtenerProyectoPorId(BigInteger idProyecto) {
        try {
            Proyecto registro = proyectoFacade.find(idProyecto);
            return registro;
        } catch (Exception e) {
            System.out.println("Error AdministrarActaReunionBO obtenerProyectoPorId: " + e.toString());
            return null;
        }
    }
}
