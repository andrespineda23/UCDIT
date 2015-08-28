/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.bo.inventario;

import com.java.ucdit.bo.interfaces.inventario.AdministrarEquipoTecnologicoBOInterface;
import com.java.ucdit.entidades.EquipoTecnologico;
import com.java.ucdit.entidades.Proveedor;
import com.java.ucdit.entidades.TipoEquipo;
import com.java.ucdit.facade.EquipoTecnologicoFacade;
import com.java.ucdit.facade.ProveedorFacade;
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
public class AdministrarEquipoTecnologicoBO implements AdministrarEquipoTecnologicoBOInterface {

    @EJB
    EquipoTecnologicoFacade equipoTecnologicoFacade;
    @EJB
    ProveedorFacade proveedorFacade;
    @EJB
    TipoEquipoFacade tipoEquipoFacade;
    
    //@Override
    public List<EquipoTecnologico> consultarEquiposTecnologicosRegistrados() {
        try {
            List<EquipoTecnologico> lista = equipoTecnologicoFacade.findAll();
            return lista;
        } catch (Exception e) {
            System.out.println("Error AdministrarEquiposTecnologicosBO consultarEquiposTecnologicosRegistrados: " + e.toString());
            return null;
        }
    }

    @Override
    public void crearEquipoTecnologico(EquipoTecnologico equipo) {
        try {
            equipoTecnologicoFacade.create(equipo);
        } catch (Exception e) {
            System.out.println("Error AdministrarEquiposTecnologicosBO crearEquipoTecnologico: " + e.toString());
        }
    }

    @Override
    public void editarEquipoTecnologico(EquipoTecnologico equipo) {
        try {
            equipoTecnologicoFacade.edit(equipo);
        } catch (Exception e) {
            System.out.println("Error AdministrarEquiposTecnologicosBO editarEquipoTecnologico: " + e.toString());
        }
    }

    @Override
    public EquipoTecnologico obtenerEquipoTecnologicoPorId(BigInteger idEquipoTecnologico) {
        try {
            EquipoTecnologico registro = equipoTecnologicoFacade.find(idEquipoTecnologico);
            return registro;
        } catch (Exception e) {
            System.out.println("Error AdministrarEquiposTecnologicosBO obtenerEquipoTecnologicoPorId: " + e.toString());
            return null;
        }
    }

    @Override
    public EquipoTecnologico obtenerEquipoTecnologicoPorCodigo(String codigoEquipoTecnologico) {
        try {
            EquipoTecnologico registro = equipoTecnologicoFacade.buscarEquipoTecnologicoPorCodigo(codigoEquipoTecnologico);
            return registro;
        } catch (Exception e) {
            System.out.println("Error AdministrarEquiposTecnologicosBO obtenerEquipoTecnologicoPorCodigo: " + e.toString());
            return null;
        }
    }

    @Override
    public List<Proveedor> consultarProveedoresRegistrados() {
        try {
            List<Proveedor> lista = proveedorFacade.findAll();
            return lista;
        } catch (Exception e) {
            System.out.println("Error AdministrarEquiposTecnologicosBO consultarProveedoresRegistrados: " + e.toString());
            return null;
        }
    }

    @Override
    public List<TipoEquipo> consultarTipoEquipoRegistrado() {
        try {
            List<TipoEquipo> lista = tipoEquipoFacade.findAll();
            return lista;
        } catch (Exception e) {
            System.out.println("Error AdministrarEquiposTecnologicosBO consultarTipoEquipoRegistrado: " + e.toString());
            return null;
        }
    }
}
