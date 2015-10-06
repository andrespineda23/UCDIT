/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.bo.interfaces.trabajo;

import com.java.ucdit.entidades.InformePersonal;
import com.java.ucdit.entidades.PersonalInterno;
import java.math.BigInteger;
import java.util.List;

/**
 *
 * @author ELECTRONICA
 */
public interface AdministrarInformePersonalBOInterface {

    public List<PersonalInterno> obtenerPersonalInternoRegistrado();

    public List<InformePersonal> obtenerInformePersonalRegistrado();

    public void crearInformePersonal(InformePersonal informePersonal);

    public List<InformePersonal> obtenerInformePersonalPorIdPersonal(BigInteger idPersonal);

    public void editarInformePersonal(InformePersonal informePersonal);

    public PersonalInterno obtenerPersonalInternoPorId(BigInteger idPersonalInterno);

    public InformePersonal obtenerInformePersonalPorId(BigInteger idInformePersonal);

}
