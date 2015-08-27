/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.facade;

import com.java.ucdit.entidades.PersonalInterno;
import java.math.BigInteger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ELECTRONICA
 */
@Stateless
public class PersonalInternoFacade extends AbstractFacade<PersonalInterno> {

    @PersistenceContext(unitName = "UCDIT-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonalInternoFacade() {
        super(PersonalInterno.class);
    }

    public PersonalInterno obtenerPersonalInternoPorIdPersona(BigInteger idPersona) {
        try {
            Query query = em.createQuery("SELECT p FROM PersonalInterno p WHERE p.persona.idpersona=:idPersona");
            query.setHint("javax.persistence.cache.storeMode", "REFRESH");
            query.setParameter("idPersona", idPersona);
            PersonalInterno personal = (PersonalInterno) query.getSingleResult();
            return personal;
        } catch (Exception e) {
            System.out.println("Error PersonalInternoFacade obtenerPersonalInternoPorIdPersona: " + e.toString());
            return null;
        }
    }

    public PersonalInterno obtenerPersonalInternoPorCorreo(String correo) {
        try {
            Query query = em.createQuery("SELECT p FROM PersonalInterno p WHERE p.persona.correoelectronico=:correo");
            query.setHint("javax.persistence.cache.storeMode", "REFRESH");
            query.setParameter("correo", correo);
            PersonalInterno personal = (PersonalInterno) query.getSingleResult();
            return personal;
        } catch (Exception e) {
            System.out.println("Error PersonalInternoFacade obtenerPersonalInternoPorCorreo: " + e.toString());
            return null;
        }
    }

    public PersonalInterno obtenerPersonalInternoPorDocumento(String documento) {
        try {
            Query query = em.createQuery("SELECT p FROM PersonalInterno p WHERE p.persona.numerodocumento=:documento");
            query.setHint("javax.persistence.cache.storeMode", "REFRESH");
            query.setParameter("documento", documento);
            PersonalInterno personal = (PersonalInterno) query.getSingleResult();
            return personal;
        } catch (Exception e) {
            System.out.println("Error PersonalInternoFacade obtenerPersonalInternoPorDocumento: " + e.toString());
            return null;
        }
    }
}
