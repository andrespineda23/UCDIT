/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.bo.interfaces.proyecto;

import com.java.ucdit.entidades.EquipoPorProyecto;
import com.java.ucdit.entidades.EquipoTecnologico;
import com.java.ucdit.entidades.Proyecto;
import java.math.BigInteger;
import java.util.List;

/**
 *
 * @author ELECTRONICA
 */
public interface AdministrarEquipoPorProyectoBOInterface {

    public List<EquipoPorProyecto> consultarEquipoPorProyectosRegistrados(BigInteger idProyecto);

    public void crearEquipoPorProyecto(EquipoPorProyecto equipo);

    public void editarEquipoPorProyecto(EquipoPorProyecto insumo);

    public List<EquipoTecnologico> obtenerEquiposTecnologicosRegistrados();

    public List<Proyecto> obtenerProyectosRegistrados();

    public Proyecto obtenerProyectoPorId(BigInteger idProyecto);
}
