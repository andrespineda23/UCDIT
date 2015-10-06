/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.bo.interfaces.proyecto;

import com.java.ucdit.entidades.BitacoraProyecto;
import com.java.ucdit.entidades.Proyecto;
import java.math.BigInteger;
import java.util.List;

/**
 *
 * @author ELECTRONICA
 */
public interface AdministrarBitacoraProyectoBOInterface {

    public List<BitacoraProyecto> consultarBitacoraPorIdProyecto(BigInteger idProyecto);

    public void crearBitacoraProyecto(BitacoraProyecto bitacoraProyecto);

    public void editarBitacoraProyecto(BitacoraProyecto bitacoraProyecto);

    public Proyecto obtenerProyectoPorId(BigInteger idProyecto);

    public BitacoraProyecto obtenerBitacoraProyectoPorId(BigInteger idRegistro);
}
