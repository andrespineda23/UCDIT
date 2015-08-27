/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.bo.interfaces;

import com.java.ucdit.entidades.Persona;
import com.java.ucdit.entidades.PersonalInterno;
import com.java.ucdit.entidades.Supervisor;
import com.java.ucdit.entidades.Usuario;
import java.math.BigInteger;

/**
 *
 * @author ELECTRONICA
 */
public interface AdministrarIndexBOInterface {

    public Persona obtenerPersonaPorUsuarioContrasenia(String usuario, String contrasenia);

    public PersonalInterno obtenerPersonalInternoPorIdPersona(BigInteger idPersona);

    public Supervisor obtenerSupervisorPorIdPersona(BigInteger idPersona);

    public Persona obtenerPersonaPorCorreo(String correo);

    public String recuperarContraseniaUsuario(Usuario usuario);

    public Persona obtenerPersonaPorDocumento(String documento);
}
