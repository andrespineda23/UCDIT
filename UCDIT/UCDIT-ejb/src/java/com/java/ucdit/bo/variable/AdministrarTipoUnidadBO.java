/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.bo.variable;

import com.java.ucdit.bo.interfaces.variable.AdministrarTipoUnidadBOInterface;
import com.java.ucdit.entidades.TipoUnidad;
import com.java.ucdit.facade.TipoUnidadFacade;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author ELECTRONICA
 */
@Stateful
public class AdministrarTipoUnidadBO implements AdministrarTipoUnidadBOInterface{

    @EJB
    TipoUnidadFacade tipoUnidadFacade;

    @Override
    public List<TipoUnidad> consultarTipoUnidadRegistrados() {
        try {
            List<TipoUnidad> lista = tipoUnidadFacade.findAll();
            return lista;
        } catch (Exception e) {
            System.out.println("Error AdministrarTipoUnidadBO consultarTipoUnidadRegistrados: " + e.toString());
            return null;
        }
    }

    @Override
    public void crearTipoUnidad(TipoUnidad tipoUnidad) {
        try {
            tipoUnidadFacade.create(tipoUnidad);
        } catch (Exception e) {
            System.out.println("Error AdministrarTipoUnidadBO crearTipoUnidad: " + e.toString());
        }
    }

    @Override
    public void editarTipoUnidad(TipoUnidad tipoUnidad) {
        try {
            tipoUnidadFacade.edit(tipoUnidad);
        } catch (Exception e) {
            System.out.println("Error AdministrarTipoUnidadBO editarTipoUnidad: " + e.toString());
        }
    }

    @Override
    public TipoUnidad obtenerTipoUnidadPorId(BigInteger idTipoUnidad) {
        try {
            TipoUnidad registro = tipoUnidadFacade.find(idTipoUnidad);
            return registro;
        } catch (Exception e) {
            System.out.println("Error AdministrarTipoUnidadBO obtenerTipoUnidadPorId: " + e.toString());
            return null;
        }
    }
}
