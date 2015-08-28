/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.bo.interfaces.variable;

import com.java.ucdit.entidades.TipoPersonal;
import java.math.BigInteger;
import java.util.List;

/**
 *
 * @author ELECTRONICA
 */
public interface AdministrarTipoPersonalBOInterface {

    public List<TipoPersonal> consultarTipoPersonalRegistrados();

    public void crearTipoPersonal(TipoPersonal tipoPersonal);

    public void editarTipoPersonal(TipoPersonal tipoPersonal);

    public TipoPersonal obtenerTipoPersonalPorId(BigInteger idTipoPersonal);
}
