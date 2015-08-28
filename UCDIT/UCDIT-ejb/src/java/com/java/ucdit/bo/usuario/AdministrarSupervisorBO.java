/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.bo.usuario;

import com.java.ucdit.bo.interfaces.usuario.AdministrarSupervisorBOInterface;
import com.java.ucdit.entidades.Persona;
import com.java.ucdit.entidades.Supervisor;
import com.java.ucdit.entidades.TipoUsuario;
import com.java.ucdit.entidades.Usuario;
import com.java.ucdit.facade.PersonaFacade;
import com.java.ucdit.facade.SupervisorFacade;
import com.java.ucdit.facade.TipoUsuarioFacade;
import com.java.ucdit.facade.UsuarioFacade;
import com.java.ucdit.utilidades.Utilidades;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author ELECTRONICA
 */
@Stateful
public class AdministrarSupervisorBO implements AdministrarSupervisorBOInterface {

    @EJB
    SupervisorFacade supervisorFacade;
    @EJB
    PersonaFacade personaFacade;
    @EJB
    UsuarioFacade usuarioFacade;
    @EJB
    TipoUsuarioFacade tipoUsuarioFacade;

    //@Override
    public List<Supervisor> consultarSupervisoresRegistrados() {
        try {
            List<Supervisor> lista = supervisorFacade.findAll();
            return lista;
        } catch (Exception e) {
            System.out.println("Error AdministrarSupervisoresBO consultarSupervisoresRegistrados: " + e.toString());
            return null;
        }
    }

    @Override
    public void crearSupervisor(Persona persona, Supervisor supervisor) {
        try {
            Usuario usuario = new Usuario();
            usuario.setUsuario(persona.getNumerodocumento());
            usuario.setContrasenia(Utilidades.codificarString(persona.getNumerodocumento()));
            usuario.setEstado(true);
            TipoUsuario tipo = tipoUsuarioFacade.find(new BigInteger("1"));
            usuario.setTipousuario(tipo);
            usuarioFacade.create(usuario);
            Usuario nuevoUsuario = usuarioFacade.obtenerUltimoUsuarioRegistrada();
            persona.setUsuario(nuevoUsuario);
            personaFacade.create(persona);
            Persona personaNueva = personaFacade.obtenerUltimaPersonaRegistrada();
            supervisor.setPersona(personaNueva);
            supervisorFacade.create(supervisor);
        } catch (Exception e) {
            System.out.println("Error AdministrarSupervisoresBO crearSupervisor: " + e.toString());
        }
    }

    @Override
    public void editarSupervisor(Supervisor supervisor) {
        try {
            usuarioFacade.edit(supervisor.getPersona().getUsuario());
            personaFacade.edit(supervisor.getPersona());
            supervisorFacade.edit(supervisor);
        } catch (Exception e) {
            System.out.println("Error AdministrarSupervisoresBO editarSupervisor: " + e.toString());
        }
    }

    @Override
    public Supervisor obtenerSupervisorPorId(BigInteger idSupervisor) {
        try {
            Supervisor registro = supervisorFacade.find(idSupervisor);
            return registro;
        } catch (Exception e) {
            System.out.println("Error AdministrarSupervisoresBO obtenerSupervisorPorId: " + e.toString());
            return null;
        }
    }

    @Override
    public Supervisor obtenerSupervisorPorCorreo(String correo) {
        try {
            Supervisor registro = supervisorFacade.obtenerSupervisorPorCorreo(correo);
            return registro;
        } catch (Exception e) {
            System.out.println("Error AdministrarSupervisoresBO obtenerSupervisorPorCorreo: " + e.toString());
            return null;
        }
    }

    @Override
    public Supervisor obtenerSupervisorPorDocumento(String documento) {
        try {
            Supervisor registro = supervisorFacade.obtenerSupervisorPorDocumento(documento);
            return registro;
        } catch (Exception e) {
            System.out.println("Error AdministrarSupervisoresBO obtenerSupervisorPorDocumento: " + e.toString());
            return null;
        }
    }
}
