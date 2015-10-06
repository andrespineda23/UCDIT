/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.bo.interfaces.proyecto;

import com.java.ucdit.entidades.GastoAdicional;
import com.java.ucdit.entidades.Proyecto;
import java.math.BigInteger;
import java.util.List;

/**
 *
 * @author ELECTRONICA
 */
public interface AdministrarGastoAdicionalProyectoBOInterface {

    public List<GastoAdicional> consultarGastoAdicionalPorIdProyecto(BigInteger idProyecto);

    public void crearGastoAdicional(GastoAdicional gastoAdicional);

    public void editarGastoAdicional(GastoAdicional gastoAdicional);

    public Proyecto obtenerProyectoPorId(BigInteger idProyecto);

    public GastoAdicional obtenerGastoAdicionalPorId(BigInteger idRegistro);
}
