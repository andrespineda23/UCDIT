/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.bo.interfaces.proyecto;

import com.java.ucdit.entidades.ActaReunion;
import com.java.ucdit.entidades.Proyecto;
import java.math.BigInteger;
import java.util.List;

/**
 *
 * @author ELECTRONICA
 */
public interface AdministrarActaReunionBOInterface {

    public List<Proyecto> obtenerProyectosRegistrados();

    public List<ActaReunion> consultarActaReunionPorIdProyecto(BigInteger idProyecto);

    public void crearActaReunion(ActaReunion actaReunion);

    public void editarActaReunion(ActaReunion actaReunion);

    public Proyecto obtenerProyectoPorId(BigInteger idProyecto);
}
