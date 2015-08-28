/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.bo.variable;

import com.java.ucdit.bo.interfaces.variable.AdministrarTipoEquipoBOInterface;
import com.java.ucdit.entidades.TipoEquipo;
import com.java.ucdit.facade.TipoEquipoFacade;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author ELECTRONICA
 */
@Stateful
public class AdministrarTipoEquipoBO implements AdministrarTipoEquipoBOInterface{

    @EJB
    TipoEquipoFacade tipoEquipoFacade;

    @Override
    public List<TipoEquipo> consultarTipoEquipoRegistrados() {
        try {
            List<TipoEquipo> lista = tipoEquipoFacade.findAll();
            return lista;
        } catch (Exception e) {
            System.out.println("Error AdministrarTipoEquipoBO consultarTipoEquipoRegistrados: " + e.toString());
            return null;
        }
    }

    @Override
    public void crearTipoEquipo(TipoEquipo tipoEquipo) {
        try {
            tipoEquipoFacade.create(tipoEquipo);
        } catch (Exception e) {
            System.out.println("Error AdministrarTipoEquipoBO crearTipoEquipo: " + e.toString());
        }
    }

    @Override
    public void editarTipoEquipo(TipoEquipo tipoEquipo) {
        try {
            tipoEquipoFacade.edit(tipoEquipo);
        } catch (Exception e) {
            System.out.println("Error AdministrarTipoEquipoBO editarTipoEquipo: " + e.toString());
        }
    }

    @Override
    public TipoEquipo obtenerTipoEquipoPorId(BigInteger idTipoEquipo) {
        try {
            TipoEquipo registro = tipoEquipoFacade.find(idTipoEquipo);
            return registro;
        } catch (Exception e) {
            System.out.println("Error AdministrarTipoEquipoBO obtenerTipoEquipoPorId: " + e.toString());
            return null;
        }
    }
}
