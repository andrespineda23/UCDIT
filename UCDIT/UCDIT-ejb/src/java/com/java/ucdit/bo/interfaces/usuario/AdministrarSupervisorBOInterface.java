/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.bo.interfaces.usuario;

import com.java.ucdit.entidades.Persona;
import com.java.ucdit.entidades.Supervisor;
import java.math.BigInteger;
import java.util.List;

/**
 *
 * @author ELECTRONICA
 */
public interface AdministrarSupervisorBOInterface {

    public List<Supervisor> consultarSupervisoresRegistrados();

    public void crearSupervisor(Persona persona, Supervisor supervisor);

    public void editarSupervisor(Supervisor supervisor);

    public Supervisor obtenerSupervisorPorId(BigInteger idSupervisor);

    public Supervisor obtenerSupervisorPorCorreo(String correo);

    public Supervisor obtenerSupervisorPorDocumento(String documento);
}
