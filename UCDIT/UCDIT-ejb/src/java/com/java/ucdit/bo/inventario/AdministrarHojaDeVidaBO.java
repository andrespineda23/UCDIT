/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.bo.inventario;

import com.java.ucdit.bo.interfaces.inventario.AdministrarHojaDeVidaBOInterface;
import com.java.ucdit.entidades.EquipoTecnologico;
import com.java.ucdit.entidades.HojaDeVida;
import com.java.ucdit.facade.EquipoTecnologicoFacade;
import com.java.ucdit.facade.HojaDeVidaFacade;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author ELECTRONICA
 */
@Stateful
public class AdministrarHojaDeVidaBO implements AdministrarHojaDeVidaBOInterface {
    
    @EJB
    HojaDeVidaFacade hojaDeVidaFacade;
    @EJB
    EquipoTecnologicoFacade equipoTecnologicoFacade;

    //@Override
    public List<HojaDeVida> consultarHojaDeVidaPorIdEquipo(BigInteger equipo) {
        try {
            List<HojaDeVida> lista = hojaDeVidaFacade.consultarHojaDeVidaPorIdEquipo(equipo);
            return lista;
        } catch (Exception e) {
            System.out.println("Error AdministrarHojaDeVidaBO consultarHojaDeVidaPorIdEquipo: " + e.toString());
            return null;
        }
    }
    
    @Override
    public void crearHojaDeVida(HojaDeVida hoja, BigInteger idEquipo) {
        try {
            EquipoTecnologico equipo = equipoTecnologicoFacade.find(idEquipo);
            hoja.setEquipotecnologico(equipo);
            hojaDeVidaFacade.create(hoja);
        } catch (Exception e) {
            System.out.println("Error AdministrarHojaDeVidaBO crearHojaDeVida: " + e.toString());
        }
    }
    
    @Override
    public void editarHojaDeVida(HojaDeVida hoja) {
        try {
            hojaDeVidaFacade.edit(hoja);
        } catch (Exception e) {
            System.out.println("Error AdministrarHojaDeVidaBO editarHojaDeVida: " + e.toString());
        }
    }
    
    @Override
    public HojaDeVida obtenerHojaDeVidaPorId(BigInteger idHojaDeVida) {
        try {
            HojaDeVida registro = hojaDeVidaFacade.find(idHojaDeVida);
            return registro;
        } catch (Exception e) {
            System.out.println("Error AdministrarHojaDeVidaBO obtenerHojaDeVidaPorId: " + e.toString());
            return null;
        }
    }
    
}
