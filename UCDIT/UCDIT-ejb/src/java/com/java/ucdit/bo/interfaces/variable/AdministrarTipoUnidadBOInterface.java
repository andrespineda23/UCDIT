/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.bo.interfaces.variable;

import com.java.ucdit.entidades.TipoUnidad;
import java.math.BigInteger;
import java.util.List;

/**
 *
 * @author ELECTRONICA
 */
public interface AdministrarTipoUnidadBOInterface {

    public List<TipoUnidad> consultarTipoUnidadRegistrados();

    public void crearTipoUnidad(TipoUnidad tipoUnidad);

    public void editarTipoUnidad(TipoUnidad tipoUnidad);

    public TipoUnidad obtenerTipoUnidadPorId(BigInteger idTipoUnidad);
}
