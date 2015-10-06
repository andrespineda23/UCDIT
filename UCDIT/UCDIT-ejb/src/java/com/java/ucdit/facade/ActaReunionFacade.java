/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.facade;

import com.java.ucdit.entidades.ActaReunion;
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
public class ActaReunionFacade extends AbstractFacade<ActaReunion> {

    @PersistenceContext(unitName = "UCDIT-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ActaReunionFacade() {
        super(ActaReunion.class);
    }

    public List<ActaReunion> obtenerActasReunionPorIdProyecto(BigInteger proyecto) {
        try {
            em.clear();
            Query query = em.createQuery("SELECT p FROM ActaReunion p WHERE p.proyecto.idproyecto=:proyecto");
            query.setHint("javax.persistence.cache.storeMode", "REFRESH");
            query.setParameter("proyecto", proyecto);
             List<ActaReunion> lista = query.getResultList();
            return lista;
        } catch (Exception e) {
            System.out.println("Error ActaReunionFacade obtenerActaReunionPorIdProyecto: " + e.toString());
            return null;
        }
    }
}
