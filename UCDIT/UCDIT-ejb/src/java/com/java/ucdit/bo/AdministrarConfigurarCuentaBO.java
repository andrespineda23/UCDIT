/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.bo;

import com.java.ucdit.bo.interfaces.AdministrarConfigurarCuentaBOInterface;
import com.java.ucdit.entidades.Persona;
import com.java.ucdit.entidades.PersonalInterno;
import com.java.ucdit.entidades.Supervisor;
import com.java.ucdit.facade.PersonaFacade;
import com.java.ucdit.facade.PersonalInternoFacade;
import com.java.ucdit.facade.SupervisorFacade;
import com.java.ucdit.facade.UsuarioFacade;
import java.math.BigInteger;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author ELECTRONICA
 */
@Stateful
public class AdministrarConfigurarCuentaBO implements AdministrarConfigurarCuentaBOInterface {

    @EJB
    private SupervisorFacade supervisorFacade;
    @EJB
    private PersonalInternoFacade personalInternoFacade;
    @EJB
    private PersonaFacade personaFacade;
    @EJB
    private UsuarioFacade usuarioFacade;

    @Override
    public Supervisor obtenerSupervisorPorId(BigInteger idRegistro) {
        try {
            Supervisor registro = supervisorFacade.find(idRegistro);
            return registro;
        } catch (Exception e) {
            System.out.println("Error AdministrarConfigurarCuentaBO obtenerSupervisorPorId: " + e.toString());
            return null;
        }
    }

    @Override
    public PersonalInterno obtenerPersonalInternoPorId(BigInteger idRegistro) {
        try {
            PersonalInterno registro = personalInternoFacade.find(idRegistro);
            return registro;
        } catch (Exception e) {
            System.out.println("Error AdministrarConfigurarCuentaBO     public PersonalInterno obtenerPersonalInternoPorId(BigInteger idRegistro) {\n" +
": " + e.toString());
            return null;
        }
    }

    @Override
    public void actualizarPersonalInterno(PersonalInterno personalinterno) {
        try {
            usuarioFacade.edit(personalinterno.getPersona().getUsuario());
            personaFacade.edit(personalinterno.getPersona());
            personalInternoFacade.edit(personalinterno);
        } catch (Exception e) {
            System.out.println("Error AdministrarConfigurarCuentaBO actualizarPersonalInterno: " + e.toString());
        }
    }

    @Override
    public void actualizarSupervisor(Supervisor supervisor) {
        try {
            usuarioFacade.edit(supervisor.getPersona().getUsuario());
            personaFacade.edit(supervisor.getPersona());
            supervisorFacade.edit(supervisor);
        } catch (Exception e) {
            System.out.println("Error AdministrarConfigurarCuentaBO actualizarSupervisor: " + e.toString());
        }
    }

    @Override
    public Persona obtenerPersonaPorCorreo(String correo) {
        try {
            Persona registro = personaFacade.obtenerPersonaPorCorreo(correo);
            return registro;
        } catch (Exception e) {
            System.out.println("Error AdministrarConfigurarCuentaBO obtenerPersonaPorCorreo: " + e.toString());
            return null;
        }
    }

    @Override
    public Persona obtenerPersonaPorDocumento(String documento) {
        try {
            Persona registro = personaFacade.obtenerPersonaPorDocumento(documento);
            return registro;
        } catch (Exception e) {
            System.out.println("Error AdministrarConfigurarCuentaBO obtenerPersonaPorDocumento: " + e.toString());
            return null;
        }
    }

}
