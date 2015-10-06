/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.facade;

import com.java.ucdit.entidades.InformePersonal;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ELECTRONICA
 */
@Stateless
public class InformePersonalFacade extends AbstractFacade<InformePersonal> {

    @PersistenceContext(unitName = "UCDIT-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InformePersonalFacade() {
        super(InformePersonal.class);
    }

    public List<InformePersonal> obtenerActasReunionPorIdPersonal(BigInteger personal) {
        try {
            em.clear();
            Query query = em.createQuery("SELECT p FROM InformePersonal p WHERE p.personalinterno.idpersonalinterno=:personal");
            query.setHint("javax.persistence.cache.storeMode", "REFRESH");
            query.setParameter("personal", personal);
            List<InformePersonal> lista = query.getResultList();
            return lista;
        } catch (Exception e) {
            System.out.println("Error InformePersonalFacade obtenerInformePersonalPorIdPersonal: " + e.toString());
            return null;
        }
    }
}
