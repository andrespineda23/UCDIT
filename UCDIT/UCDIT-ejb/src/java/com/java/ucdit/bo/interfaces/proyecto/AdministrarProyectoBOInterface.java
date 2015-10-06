/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.bo.interfaces.proyecto;

import com.java.ucdit.entidades.Cliente;
import com.java.ucdit.entidades.EquipoPorProyecto;
import com.java.ucdit.entidades.GastoAdicional;
import com.java.ucdit.entidades.InsumoPorProyecto;
import com.java.ucdit.entidades.PersonalInterno;
import com.java.ucdit.entidades.PersonalPorProyecto;
import com.java.ucdit.entidades.Proyecto;
import com.java.ucdit.entidades.Supervisor;
import java.math.BigInteger;
import java.util.List;

/**
 *
 * @author ELECTRONICA
 */
public interface AdministrarProyectoBOInterface {

    public List<Proyecto> consultarProyectosRegistrados();

    public void crearProyecto(Proyecto proyecto, List<PersonalInterno> lista);

    public void editarProyecto(Proyecto proyecto);

    public Proyecto obtenerProyectoPorId(BigInteger idProyecto);

    public List<Cliente> obtenerClientesRegistrados();

    public List<Supervisor> obtenerSupervisoresRegistrados();

    public List<PersonalPorProyecto> obtenerPersonalPorProyectoPorIdProyecto(BigInteger proyecto);

    public void crearPersonalPorProyecto(PersonalPorProyecto personalPorProyecto);

    public List<InsumoPorProyecto> consultarInsumoPorProyectoPorIdProyecto(BigInteger proyecto);

    public List<EquipoPorProyecto> consultarEquipoProProyectoPorIdProyecto(BigInteger proyecto);

    public void crearInsumoPorProyecto(InsumoPorProyecto insumoPorProyecto);

    public void editarInsumoPorProyecto(InsumoPorProyecto insumoPorProyecto);

    public void crearEquipoPorProyecto(EquipoPorProyecto equipoPorProyecto);

    public void editarIEquipoPorProyecto(EquipoPorProyecto equipoPorProyecto);

    public List<PersonalInterno> obtenerPersonalInternoRegistrado();

    public void asociarPersonalAProyecto(Proyecto proyecto, List<PersonalPorProyecto> personalPorProyecto, List<PersonalInterno> personalInterno);

    public List<GastoAdicional> consultarGastoAdicionalProyecto(BigInteger idProyecto);
}
