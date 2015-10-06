/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.bo.interfaces.inventario;

import com.java.ucdit.entidades.HojaDeVida;
import java.math.BigInteger;
import java.util.List;

/**
 *
 * @author ELECTRONICA
 */
public interface AdministrarHojaDeVidaBOInterface {

    public List<HojaDeVida> consultarHojaDeVidaPorIdEquipo(BigInteger equipo);

    public void crearHojaDeVida(HojaDeVida hoja, BigInteger idEquipo);

    public void editarHojaDeVida(HojaDeVida equipo);

    public HojaDeVida obtenerHojaDeVidaPorId(BigInteger idHojaDeVida);
}
