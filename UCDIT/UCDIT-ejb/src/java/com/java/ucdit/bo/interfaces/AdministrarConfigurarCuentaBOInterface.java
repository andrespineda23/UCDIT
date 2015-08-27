/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.bo.interfaces;

import com.java.ucdit.entidades.Persona;
import com.java.ucdit.entidades.PersonalInterno;
import com.java.ucdit.entidades.Supervisor;
import java.math.BigInteger;

/**
 *
 * @author ELECTRONICA
 */
public interface AdministrarConfigurarCuentaBOInterface {

    public Supervisor obtenerSupervisorPorId(BigInteger idRegistro);

    public PersonalInterno obtenerPersonalInternoPorId(BigInteger idRegistro);

    public void actualizarPersonalInterno(PersonalInterno personalinterno);

    public void actualizarSupervisor(Supervisor supervisor);

    public Persona obtenerPersonaPorCorreo(String correo);

    public Persona obtenerPersonaPorDocumento(String documento);
}
