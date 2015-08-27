/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.bo.interfaces.usuario;

import com.java.ucdit.entidades.Persona;
import com.java.ucdit.entidades.PersonalInterno;
import com.java.ucdit.entidades.TipoPersonal;
import java.math.BigInteger;
import java.util.List;

/**
 *
 * @author ELECTRONICA
 */
public interface AdministrarPersonalInternoBOInterface {

    public List<PersonalInterno> consultarPersonalInternoesRegistrados();

    public void crearPersonalInterno(Persona persona, PersonalInterno personalInterno);

    public void editarPersonalInterno(PersonalInterno personalInterno);

    public PersonalInterno obtenerPersonalInternoPorId(BigInteger idPersonalInterno);

    public PersonalInterno obtenerPersonalInternoPorCorreo(String correo);

    public PersonalInterno obtenerPersonalInternoPorDocumento(String documento);

    public List<TipoPersonal> obtenerTipoPersonalRegistrado();

}
