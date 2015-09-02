/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.bo.proyecto;

import com.java.ucdit.bo.interfaces.proyecto.AdministrarInsumoPorProyectoBOInterface;
import com.java.ucdit.entidades.Insumo;
import com.java.ucdit.entidades.InsumoPorProyecto;
import com.java.ucdit.entidades.Proyecto;
import com.java.ucdit.facade.InsumoFacade;
import com.java.ucdit.facade.InsumoPorProyectoFacade;
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
public class AdministrarInsumoPorProyectoBO implements AdministrarInsumoPorProyectoBOInterface {

    @EJB
    InsumoFacade insumoFacade;
    @EJB
    InsumoPorProyectoFacade insumoPorProyectoFacade;
    @EJB
    ProyectoFacade proyectoFacade;

    @Override
    public List<InsumoPorProyecto> consultarInsumoPorProyectosRegistrados(BigInteger idProyecto) {
        try {
            List<InsumoPorProyecto> lista = insumoPorProyectoFacade.obtenerInsumoPorProyectoPorIdProyecto(idProyecto);
            return lista;
        } catch (Exception e) {
            System.out.println("Error AdministrarInsumoPorProyectoBO consultarInsumoPorProyectosRegistrados: " + e.toString());
            return null;
        }
    }

    @Override
    public void crearInsumoPorProyecto(InsumoPorProyecto insumo) {
        try {
            Insumo insumoEditar = insumo.getInsumo();
            int cantidad = insumoEditar.getCantidadexistencia() - insumo.getCantidadusada();
            insumoEditar.setCantidadexistencia(cantidad);
            insumoFacade.edit(insumoEditar);
            insumoPorProyectoFacade.create(insumo);
        } catch (Exception e) {
            System.out.println("Error AdministrarInsumoPorProyectoBO crearInsumoPorProyecto: " + e.toString());
        }
    }

    @Override
    public void editarInsumoPorProyecto(InsumoPorProyecto insumo) {
        try {
            insumoPorProyectoFacade.edit(insumo);
        } catch (Exception e) {
            System.out.println("Error AdministrarInsumoPorProyectoBO editarInsumoPorProyecto: " + e.toString());
        }
    }

    @Override
    public List<Insumo> obtenerInsumosRegistrados() {
        try {
            List<Insumo> lista = insumoFacade.findAll();
            return lista;
        } catch (Exception e) {
            System.out.println("Error AdministrarInsumoPorProyectoBO obtenerInsumosRegistrados: " + e.toString());
            return null;
        }
    }

    @Override
    public List<Proyecto> obtenerProyectosRegistrados() {
        try {
            List<Proyecto> lista = proyectoFacade.findAll();
            return lista;
        } catch (Exception e) {
            System.out.println("Error AdministrarInsumoPorProyectoBO obtenerProyectosRegistrados: " + e.toString());
            return null;
        }
    }

    @Override
    public Proyecto obtenerProyectoPorId(BigInteger idProyecto) {
        try {
            Proyecto registro = proyectoFacade.find(idProyecto);
            return registro;
        } catch (Exception e) {
            System.out.println("Error AdministrarInsumoPorProyectoBO obtenerProyectoPorId: " + e.toString());
            return null;
        }
    }
}
