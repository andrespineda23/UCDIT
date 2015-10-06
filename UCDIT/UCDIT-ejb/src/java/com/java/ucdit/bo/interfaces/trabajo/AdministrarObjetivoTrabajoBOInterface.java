/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.bo.interfaces.trabajo;

import com.java.ucdit.entidades.ObjetivoPorPersonalProyecto;
import com.java.ucdit.entidades.ObjetivoTrabajo;
import com.java.ucdit.entidades.PersonalInterno;
import com.java.ucdit.entidades.PersonalPorProyecto;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ELECTRONICA
 */
public interface AdministrarObjetivoTrabajoBOInterface {

    public List<ObjetivoTrabajo> consultarObjetivoTrabajoRegistrado();

    public void crearObjetivoTrabajo(ObjetivoTrabajo objetivoTrabajo, Date fecha, PersonalPorProyecto personalPorProyecto);

    public void editarObjetivoTrabajo(ObjetivoTrabajo objetivoTrabajo);

    public ObjetivoTrabajo recibirObjetivoTrabajoPorId(BigInteger idBigInteger);

    public List<PersonalPorProyecto> obtenerPersonalPorProyectoPorIdPersonal(BigInteger idPersonal);

    public List<PersonalInterno> obtenerPersonalInternoRegistrado();

    public ObjetivoPorPersonalProyecto obtenerObjetivoPorPersonalProyectoPorId(BigInteger idObjetivoPorPersonalProyecto);

    public void editarObjetivoPorPersonalProyecto(ObjetivoPorPersonalProyecto objetivoTrabajo);

    public List<ObjetivoPorPersonalProyecto> consultarObjetivoPorPersonalProyectoRegistrado();

    public List<ObjetivoPorPersonalProyecto> obtenerObjetivoPorPersonalProyectoPorPersonal(BigInteger personal);
}
