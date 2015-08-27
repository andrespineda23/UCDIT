/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.bo;

import com.java.ucdit.bo.interfaces.AdministrarIndexBOInterface;
import com.java.ucdit.entidades.Persona;
import com.java.ucdit.entidades.PersonalInterno;
import com.java.ucdit.entidades.Supervisor;
import com.java.ucdit.entidades.Usuario;
import com.java.ucdit.facade.PersonaFacade;
import com.java.ucdit.facade.PersonalInternoFacade;
import com.java.ucdit.facade.SupervisorFacade;
import com.java.ucdit.facade.UsuarioFacade;
import com.java.ucdit.utilidades.Utilidades;
import java.math.BigInteger;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author ELECTRONICA
 */
@Stateful
public class AdministrarIndexBO implements AdministrarIndexBOInterface {

    @EJB
    private SupervisorFacade supervisorFacade;
    @EJB
    private PersonalInternoFacade personalInternoFacade;
    @EJB
    private PersonaFacade personaFacade;
    @EJB
    private UsuarioFacade usuarioFacade;

    @Override
    public Persona obtenerPersonaPorUsuarioContrasenia(String usuario, String contrasenia) {
        try {
            Persona registro = personaFacade.obtenerPersonaPorUsuarioContrasenia(usuario, contrasenia);
            return registro;
        } catch (Exception e) {
            System.out.println("Error AdministrarIndexBO obtenerPersonaPorUsuarioContrasenia: " + e.toString());
            return null;
        }
    }

    @Override
    public PersonalInterno obtenerPersonalInternoPorIdPersona(BigInteger idPersona) {
        try {
            PersonalInterno registro = personalInternoFacade.obtenerPersonalInternoPorIdPersona(idPersona);
            return registro;
        } catch (Exception e) {
            System.out.println("Error AdministrarIndexBO obtenerPersonalInternoPorIdPersona: " + e.toString());
            return null;
        }
    }

    @Override
    public Supervisor obtenerSupervisorPorIdPersona(BigInteger idPersona) {
        try {
            Supervisor registro = supervisorFacade.obtenerSupervisorPorIdPersona(idPersona);
            return registro;
        } catch (Exception e) {
            System.out.println("Error AdministrarIndexBO obtenerSupervisorPorIdPersona: " + e.toString());
            return null;
        }
    }

    @Override
    public Persona obtenerPersonaPorCorreo(String correo) {
        try {
            Persona registro = personaFacade.obtenerPersonaPorCorreo(correo);
            return registro;
        } catch (Exception e) {
            System.out.println("Error AdministrarIndexBO obtenerPersonaPorCorreo: " + e.toString());
            return null;
        }
    }
    
    @Override
    public Persona obtenerPersonaPorDocumento(String documento) {
        try {
            Persona registro = personaFacade.obtenerPersonaPorDocumento(documento);
            return registro;
        } catch (Exception e) {
            System.out.println("Error AdministrarIndexBO obtenerPersonaPorDocumento: " + e.toString());
            return null;
        }
    }

    @Override
    public String recuperarContraseniaUsuario(Usuario usuario) {
        try {
            String nuevaContrasenia = Utilidades.generarNuevaContrasenia();
            usuario.setContrasenia(nuevaContrasenia);
            usuarioFacade.edit(usuario);
            return nuevaContrasenia;
        } catch (Exception e) {
            System.out.println("Error AdministrarIndexBO recuperarContraseniaUsuario: " + e.toString());
            return null;
        }
    }
}
