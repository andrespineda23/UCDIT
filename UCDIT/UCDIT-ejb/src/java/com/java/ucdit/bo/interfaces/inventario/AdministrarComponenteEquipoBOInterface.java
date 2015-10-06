/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.bo.interfaces.inventario;

import com.java.ucdit.entidades.Componente;
import com.java.ucdit.entidades.EquipoTecnologico;
import java.math.BigInteger;
import java.util.List;

/**
 *
 * @author ELECTRONICA
 */
public interface AdministrarComponenteEquipoBOInterface {

    public EquipoTecnologico obtenerEquipoTecnologicoPorId(BigInteger idEquipo);

    public List<Componente> obtenerComponentesPorIdEquipo(BigInteger idEquipo);

    public void crearComponenteEquipo(Componente componente);

    public void editarComponenteEquipo(Componente componente);

    public Componente validarCodigoRepetidoComponente(String codigo, BigInteger equipo);

    public Componente obtenerComponentePorId(BigInteger idComponete);
}
