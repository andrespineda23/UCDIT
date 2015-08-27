/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.facade;

import com.java.ucdit.entidades.Supervisor;
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
public class SupervisorFacade extends AbstractFacade<Supervisor> {

    @PersistenceContext(unitName = "UCDIT-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SupervisorFacade() {
        super(Supervisor.class);
    }

    public Supervisor obtenerSupervisorPorIdPersona(BigInteger idPersona) {
        try {
            Query query = em.createQuery("SELECT p FROM Supervisor p WHERE p.persona.idpersona=:idPersona");
            query.setHint("javax.persistence.cache.storeMode", "REFRESH");
            query.setParameter("idPersona", idPersona);
            Supervisor personal = (Supervisor) query.getSingleResult();
            return personal;
        } catch (Exception e) {
            System.out.println("Error SupervisorFacade obtenerSupervisorPorIdPersona: " + e.toString());
            return null;
        }
    }
}
