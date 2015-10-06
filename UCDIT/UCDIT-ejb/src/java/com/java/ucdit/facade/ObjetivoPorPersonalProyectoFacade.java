/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.facade;

import com.java.ucdit.entidades.ObjetivoPorPersonalProyecto;
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
public class ObjetivoPorPersonalProyectoFacade extends AbstractFacade<ObjetivoPorPersonalProyecto> {
    @PersistenceContext(unitName = "UCDIT-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ObjetivoPorPersonalProyectoFacade() {
        super(ObjetivoPorPersonalProyecto.class);
    }
    
     public List<ObjetivoPorPersonalProyecto> obtenerObjetivoPorPersonalProyectoPorIdPersonal(BigInteger idPersonal) {
        try {
            em.clear();
            Query query = em.createQuery("SELECT p FROM ObjetivoPorPersonalProyecto p WHERE p.personalporproyecto.personalinterno.idpersonalinterno=:idPersonal");
            query.setHint("javax.persistence.cache.storeMode", "REFRESH");
            query.setParameter("idPersonal", idPersonal);
            List<ObjetivoPorPersonalProyecto> lista = query.getResultList();
            return lista;
        } catch (Exception e) {
            System.out.println("Error ObjetivoPorPersonalProyectoFacade obtenerObjetivoPorPersonalProyectoPorIdPersonal: " + e.toString());
            return null;
        }
    }
    
}
