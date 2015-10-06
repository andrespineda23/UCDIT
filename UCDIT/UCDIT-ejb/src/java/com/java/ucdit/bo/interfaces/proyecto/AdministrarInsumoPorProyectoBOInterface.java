/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.bo.interfaces.proyecto;

import com.java.ucdit.entidades.Insumo;
import com.java.ucdit.entidades.InsumoPorProyecto;
import com.java.ucdit.entidades.Proyecto;
import java.math.BigInteger;
import java.util.List;

/**
 *
 * @author ELECTRONICA
 */
public interface AdministrarInsumoPorProyectoBOInterface {

    public List<InsumoPorProyecto> consultarInsumoPorProyectosRegistrados(BigInteger idProyecto);

    public void crearInsumoPorProyecto(InsumoPorProyecto insumo);

    public void editarInsumoPorProyecto(InsumoPorProyecto insumo);

    public List<Insumo> obtenerInsumosRegistrados();

    public List<Proyecto> obtenerProyectosRegistrados();

    public Proyecto obtenerProyectoPorId(BigInteger idProyecto);

    public InsumoPorProyecto obtenerInsumoPorProyectoPorId(BigInteger idRegistro);
}
