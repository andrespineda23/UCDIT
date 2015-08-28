/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.bo.variable;

import com.java.ucdit.bo.interfaces.variable.AdministrarTipoPersonalBOInterface;
import com.java.ucdit.entidades.TipoPersonal;
import com.java.ucdit.facade.TipoPersonalFacade;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author ELECTRONICA
 */
@Stateful
public class AdministrarTipoPersonalBO implements AdministrarTipoPersonalBOInterface {

    @EJB
    TipoPersonalFacade tipoPersonalFacade;

    @Override
    public List<TipoPersonal> consultarTipoPersonalRegistrados() {
        try {
            List<TipoPersonal> lista = tipoPersonalFacade.findAll();
            return lista;
        } catch (Exception e) {
            System.out.println("Error AdministrarTipoPersonalBO consultarTipoPersonalRegistrados: " + e.toString());
            return null;
        }
    }

    @Override
    public void crearTipoPersonal(TipoPersonal tipoPersonal) {
        try {
            tipoPersonalFacade.create(tipoPersonal);
        } catch (Exception e) {
            System.out.println("Error AdministrarTipoPersonalBO crearTipoPersonal: " + e.toString());
        }
    }

    @Override
    public void editarTipoPersonal(TipoPersonal tipoPersonal) {
        try {
            tipoPersonalFacade.edit(tipoPersonal);
        } catch (Exception e) {
            System.out.println("Error AdministrarTipoPersonalBO editarTipoPersonal: " + e.toString());
        }
    }

    @Override
    public TipoPersonal obtenerTipoPersonalPorId(BigInteger idTipoPersonal) {
        try {
            TipoPersonal registro = tipoPersonalFacade.find(idTipoPersonal);
            return registro;
        } catch (Exception e) {
            System.out.println("Error AdministrarTipoPersonalBO obtenerTipoPersonalPorId: " + e.toString());
            return null;
        }
    }
}
