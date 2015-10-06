/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.bo.trabajo;

import com.java.ucdit.bo.interfaces.trabajo.AdministrarInformePersonalBOInterface;
import com.java.ucdit.entidades.InformePersonal;
import com.java.ucdit.entidades.PersonalInterno;
import com.java.ucdit.facade.InformePersonalFacade;
import com.java.ucdit.facade.PersonalInternoFacade;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author ELECTRONICA
 */
@Stateful
public class AdministrarInformePersonalBO implements AdministrarInformePersonalBOInterface {

    @EJB
    InformePersonalFacade informePersonalFacade;
    @EJB
    PersonalInternoFacade personalInternoFacade;

    @Override
    public List<PersonalInterno> obtenerPersonalInternoRegistrado() {
        try {
            List<PersonalInterno> lista = personalInternoFacade.findAll();
            return lista;
        } catch (Exception e) {
            System.out.println("Error AdministrarInformePersonalBO obtenerPersonalInternoRegistrado: " + e.toString());
            return null;
        }
    }

    @Override
    public List<InformePersonal> obtenerInformePersonalRegistrado() {
        try {
            List<InformePersonal> lista = informePersonalFacade.findAll();
            return lista;
        } catch (Exception e) {
            System.out.println("Error AdministrarInformePersonalBO obtenerPersonalInternoRegistrado: " + e.toString());
            return null;
        }
    }

    @Override
    public List<InformePersonal> obtenerInformePersonalPorIdPersonal(BigInteger idPersonal) {
        try {
            List<InformePersonal> lista = informePersonalFacade.obtenerActasReunionPorIdPersonal(idPersonal);
            return lista;
        } catch (Exception e) {
            System.out.println("Error AdministrarInformePersonalBO obtenerInformePersonalPorIdPersonal: " + e.toString());
            return null;
        }
    }

    @Override
    public void crearInformePersonal(InformePersonal informePersonal) {
        try {
            informePersonalFacade.create(informePersonal);
        } catch (Exception e) {
            System.out.println("Error AdministrarInformePersonalBO crearInformePersonal: " + e.toString());
        }
    }

    @Override
    public void editarInformePersonal(InformePersonal informePersonal) {
        try {
            informePersonalFacade.edit(informePersonal);
        } catch (Exception e) {
            System.out.println("Error AdministrarInformePersonalBO editarInformePersonal: " + e.toString());
        }
    }

    @Override
    public PersonalInterno obtenerPersonalInternoPorId(BigInteger idPersonalInterno) {
        try {
            PersonalInterno registro = personalInternoFacade.find(idPersonalInterno);
            return registro;
        } catch (Exception e) {
            System.out.println("Error AdministrarInformePersonalBO obtenerPersonalInternoPorId: " + e.toString());
            return null;
        }
    }

    @Override
    public InformePersonal obtenerInformePersonalPorId(BigInteger idInformePersonal) {
        try {
            InformePersonal registro = informePersonalFacade.find(idInformePersonal);
            return registro;
        } catch (Exception e) {
            System.out.println("Error AdministrarInformePersonalBO obtenerInformePersonalPorId: " + e.toString());
            return null;
        }
    }

}
