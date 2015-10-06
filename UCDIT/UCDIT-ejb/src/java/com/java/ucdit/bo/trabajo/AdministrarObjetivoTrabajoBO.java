/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.bo.trabajo;

import com.java.ucdit.bo.interfaces.trabajo.AdministrarObjetivoTrabajoBOInterface;
import com.java.ucdit.entidades.ObjetivoPorPersonalProyecto;
import com.java.ucdit.entidades.ObjetivoTrabajo;
import com.java.ucdit.entidades.PersonalInterno;
import com.java.ucdit.entidades.PersonalPorProyecto;
import com.java.ucdit.facade.ObjetivoPorPersonalProyectoFacade;
import com.java.ucdit.facade.ObjetivoTrabajoFacade;
import com.java.ucdit.facade.PersonalInternoFacade;
import com.java.ucdit.facade.PersonalPorProyectoFacade;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author ELECTRONICA
 */
@Stateful
public class AdministrarObjetivoTrabajoBO implements AdministrarObjetivoTrabajoBOInterface {

    @EJB
    ObjetivoTrabajoFacade objetivoTrabajoFacade;
    @EJB
    PersonalPorProyectoFacade personalPorProyectoFacade;
    @EJB
    ObjetivoPorPersonalProyectoFacade objetivoPorPersonalProyectoFacade;
    @EJB
    PersonalInternoFacade personalInternoFacade;

    @Override
    public List<ObjetivoTrabajo> consultarObjetivoTrabajoRegistrado() {
        try {
            List<ObjetivoTrabajo> lista = objetivoTrabajoFacade.findAll();
            return lista;
        } catch (Exception e) {
            System.out.println("Error AdministrarObjetivoTrabajoBO consultarObjetivoTrabajoRegistrado: " + e.toString());
            return null;
        }
    }

    @Override
    public List<ObjetivoPorPersonalProyecto> consultarObjetivoPorPersonalProyectoRegistrado() {
        try {
            List<ObjetivoPorPersonalProyecto> lista = objetivoPorPersonalProyectoFacade.findAll();
            return lista;
        } catch (Exception e) {
            System.out.println("Error AdministrarObjetivoTrabajoBO consultarObjetivoPorPersonalProyectoRegistrado: " + e.toString());
            return null;
        }
    }

    @Override
    public List<ObjetivoPorPersonalProyecto> obtenerObjetivoPorPersonalProyectoPorPersonal(BigInteger personal) {
        try {
            List<ObjetivoPorPersonalProyecto> lista = objetivoPorPersonalProyectoFacade.obtenerObjetivoPorPersonalProyectoPorIdPersonal(personal);
            return lista;
        } catch (Exception e) {
            System.out.println("Error AdministrarObjetivoTrabajoBO obtenerObjetivoPorPersonalProyectoPorPersonal: " + e.toString());
            return null;
        }
    }

    @Override
    public void crearObjetivoTrabajo(ObjetivoTrabajo objetivoTrabajo, Date fecha, PersonalPorProyecto personalPorProyecto) {
        try {
            objetivoTrabajoFacade.create(objetivoTrabajo);
            ObjetivoTrabajo objetivoTrabajoNuevo = objetivoTrabajoFacade.obtenerUltimoObjetivoTrabajoRegistrado();
            ObjetivoPorPersonalProyecto objetivoPorPersonalProyecto = new ObjetivoPorPersonalProyecto();
            objetivoPorPersonalProyecto.setFechadeseada(fecha);
            objetivoPorPersonalProyecto.setObjetivotrabajo(objetivoTrabajoNuevo);
            objetivoPorPersonalProyecto.setPersonalporproyecto(personalPorProyecto);
            objetivoPorPersonalProyectoFacade.create(objetivoPorPersonalProyecto);
        } catch (Exception e) {
            System.out.println("Error AdministrarObjetivoTrabajoBO crearObjetivoTrabajo: " + e.toString());
        }
    }

    @Override
    public void editarObjetivoTrabajo(ObjetivoTrabajo objetivoTrabajo) {
        try {
            objetivoTrabajoFacade.edit(objetivoTrabajo);
        } catch (Exception e) {
            System.out.println("Error AdministrarObjetivoTrabajoBO crearObjetivoTrabajo: " + e.toString());
        }
    }

    @Override
    public void editarObjetivoPorPersonalProyecto(ObjetivoPorPersonalProyecto objetivoTrabajo) {
        try {
            objetivoTrabajoFacade.edit(objetivoTrabajo.getObjetivotrabajo());
            objetivoPorPersonalProyectoFacade.edit(objetivoTrabajo);
        } catch (Exception e) {
            System.out.println("Error AdministrarObjetivoTrabajoBO crearObjetivoTrabajo: " + e.toString());
        }
    }

    @Override
    public ObjetivoTrabajo recibirObjetivoTrabajoPorId(BigInteger idBigInteger) {
        try {
            ObjetivoTrabajo registro = objetivoTrabajoFacade.find(idBigInteger);
            return registro;
        } catch (Exception e) {
            System.out.println("Error AdministrarObjetivoTrabajoBO recibirObjetivoTrabajoPorId: " + e.toString());
            return null;
        }
    }

    @Override
    public List<PersonalPorProyecto> obtenerPersonalPorProyectoPorIdPersonal(BigInteger idPersonal) {
        try {
            List<PersonalPorProyecto> lista = personalPorProyectoFacade.obtenerPersonalPorProyectoPorIdPersonal(idPersonal);
            return lista;
        } catch (Exception e) {
            System.out.println("Error AdministrarObjetivoTrabajoBO obtenerPersonalPorProyectoPorIdPersonal: " + e.toString());
            return null;
        }
    }

    @Override
    public List<PersonalInterno> obtenerPersonalInternoRegistrado() {
        try {
            List<PersonalInterno> lista = personalInternoFacade.findAll();
            return lista;
        } catch (Exception e) {
            System.out.println("Error AdministrarObjetivoTrabajoBO obtenerPersonalInternoRegistrado: " + e.toString());
            return null;
        }
    }

    @Override
    public ObjetivoPorPersonalProyecto obtenerObjetivoPorPersonalProyectoPorId(BigInteger idObjetivoPorPersonalProyecto) {
        try {
            ObjetivoPorPersonalProyecto registro = objetivoPorPersonalProyectoFacade.find(idObjetivoPorPersonalProyecto);
            return registro;
        } catch (Exception e) {
            System.out.println("Error AdministrarObjetivoTrabajoBO obtenerObjetivoPorPersonalProyectoPorId: " + e.toString());
            return null;
        }
    }
}
