/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.bo.interfaces.variable;

import com.java.ucdit.entidades.TipoEquipo;
import java.math.BigInteger;
import java.util.List;

/**
 *
 * @author ELECTRONICA
 */
public interface AdministrarTipoEquipoBOInterface {

    public List<TipoEquipo> consultarTipoEquipoRegistrados();

    public void crearTipoEquipo(TipoEquipo tipoEquipo);

    public void editarTipoEquipo(TipoEquipo tipoEquipo);

    public TipoEquipo obtenerTipoEquipoPorId(BigInteger idTipoEquipo);
}
