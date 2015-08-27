/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.bo.usuario;

import com.java.ucdit.bo.interfaces.usuario.AdministrarPersonalInternoBOInterface;
import com.java.ucdit.entidades.Persona;
import com.java.ucdit.entidades.PersonalInterno;
import com.java.ucdit.entidades.TipoPersonal;
import com.java.ucdit.facade.PersonaFacade;
import com.java.ucdit.facade.PersonalInternoFacade;
import com.java.ucdit.facade.TipoPersonalFacade;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author ELECTRONICA
 */
@Stateful
public class AdministrarPersonalInternoBO implements AdministrarPersonalInternoBOInterface {

    @EJB
    private PersonalInternoFacade personalInternoFacade;
    @EJB
    private TipoPersonalFacade tipoPersonalFacade;
    @EJB
    private PersonaFacade personaFacade;

    //@Override
    public List<PersonalInterno> consultarPersonalInternoesRegistrados() {
        try {
            List<PersonalInterno> lista = personalInternoFacade.findAll();
            return lista;
        } catch (Exception e) {
            System.out.println("Error AdministrarPersonalInternoesBO consultarPersonalInternoesRegistrados: " + e.toString());
            return null;
        }
    }

    @Override
    public void crearPersonalInterno(Persona persona, PersonalInterno personalInterno) {
        try {
            personaFacade.create(persona);
            Persona personaNueva = personaFacade.obtenerUltimaPersonaRegistrada();
            personalInterno.setPersona(personaNueva);
            personalInternoFacade.create(personalInterno);
        } catch (Exception e) {
            System.out.println("Error AdministrarPersonalInternoesBO crearPersonalInterno: " + e.toString());
        }
    }

    @Override
    public void editarPersonalInterno(PersonalInterno personalInterno) {
        try {
            personaFacade.edit(personalInterno.getPersona());
            personalInternoFacade.edit(personalInterno);
        } catch (Exception e) {
            System.out.println("Error AdministrarPersonalInternoesBO editarPersonalInterno: " + e.toString());
        }
    }

    @Override
    public PersonalInterno obtenerPersonalInternoPorId(BigInteger idPersonalInterno) {
        try {
            PersonalInterno registro = personalInternoFacade.find(idPersonalInterno);
            return registro;
        } catch (Exception e) {
            System.out.println("Error AdministrarPersonalInternoesBO obtenerPersonalInternoPorId: " + e.toString());
            return null;
        }
    }

    @Override
    public PersonalInterno obtenerPersonalInternoPorCorreo(String correo) {
        try {
            PersonalInterno registro = personalInternoFacade.obtenerPersonalInternoPorCorreo(correo);
            return registro;
        } catch (Exception e) {
            System.out.println("Error AdministrarPersonalInternoesBO obtenerPersonalInternoPorCorreo: " + e.toString());
            return null;
        }
    }

    @Override
    public PersonalInterno obtenerPersonalInternoPorDocumento(String documento) {
        try {
            PersonalInterno registro = personalInternoFacade.obtenerPersonalInternoPorDocumento(documento);
            return registro;
        } catch (Exception e) {
            System.out.println("Error AdministrarPersonalInternoesBO obtenerPersonalInternoPorDocumento: " + e.toString());
            return null;
        }
    }

    @Override
    public List<TipoPersonal> obtenerTipoPersonalRegistrado() {
        try {
            List<TipoPersonal> lista = tipoPersonalFacade.findAll();
            return lista;
        } catch (Exception e) {
            System.out.println("Error AdministrarPersonalInternoesBO obtenerTipoPersonalRegistrado: " + e.toString());
            return null;
        }
    }
}
