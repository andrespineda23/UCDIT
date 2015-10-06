/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.facade;

import com.java.ucdit.entidades.ObjetivoTrabajo;
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
public class ObjetivoTrabajoFacade extends AbstractFacade<ObjetivoTrabajo> {
    @PersistenceContext(unitName = "UCDIT-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ObjetivoTrabajoFacade() {
        super(ObjetivoTrabajo.class);
    }
 
     public ObjetivoTrabajo obtenerUltimoObjetivoTrabajoRegistrado() {
        try {
            em.clear();
            Query query = em.createQuery("SELECT p FROM ObjetivoTrabajo p ORDER BY p.idobjetivotrabajo DESC");
            query.setHint("javax.persistence.cache.storeMode", "REFRESH");
            List<ObjetivoTrabajo> registros = query.getResultList();
            if (registros != null) {
                System.out.println("registros : " + registros.size());
                ObjetivoTrabajo ultimoRegistro = registros.get(0);
                return ultimoRegistro;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error ObjetivoTrabajoFacade obtenerUltimoObjetivoTrabajoRegistrado: " + e.toString());
            return null;
        }
    }
}
