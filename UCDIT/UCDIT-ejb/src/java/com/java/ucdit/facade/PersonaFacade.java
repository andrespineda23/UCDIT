/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.facade;

import com.java.ucdit.entidades.Persona;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ELECTRONICA
 */
@Stateless
public class PersonaFacade extends AbstractFacade<Persona> {

    @PersistenceContext(unitName = "UCDIT-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonaFacade() {
        super(Persona.class);
    }

    public Persona obtenerPersonaPorUsuarioContrasenia(String usuario, String contrasenia) {
        try {
            em.clear();
            Query query = em.createQuery("SELECT p FROM Persona p WHERE p.usuario.usuario=:usuario AND p.usuario.contrasenia=:contrasenia");
            query.setHint("javax.persistence.cache.storeMode", "REFRESH");
            query.setParameter("usuario", usuario);
            query.setParameter("contrasenia", contrasenia);
            Persona registro = (Persona) query.getSingleResult();
            return registro;
        } catch (Exception e) {
            System.out.println("Error PersonaFacade obtenerPersonaPorUsuarioContrasenia: " + e.toString());
            return null;
        }
    }

    public Persona obtenerPersonaPorCorreo(String correo) {
        try {
            em.clear();
            Query query = em.createQuery("SELECT p FROM Persona p WHERE p.correoelectronico=:correo");
            query.setHint("javax.persistence.cache.storeMode", "REFRESH");
            query.setParameter("correo", correo);
            Persona registro = (Persona) query.getSingleResult();
            return registro;
        } catch (Exception e) {
            System.out.println("Error PersonaFacade obtenerPersonaPorCorreo: " + e.toString());
            return null;
        }
    }
    public Persona obtenerPersonaPorDocumento(String documento) {
        try {
            em.clear();
            Query query = em.createQuery("SELECT p FROM Persona p WHERE p.numerodocumento=:documento");
            query.setHint("javax.persistence.cache.storeMode", "REFRESH");
            query.setParameter("documento", documento);
            Persona registro = (Persona) query.getSingleResult();
            return registro;
        } catch (Exception e) {
            System.out.println("Error PersonaFacade obtenerPersonaPorDocumento: " + e.toString());
            return null;
        }
    }
}
