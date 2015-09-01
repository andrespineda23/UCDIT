/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.facade;

import com.java.ucdit.entidades.Proyecto;
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
public class ProyectoFacade extends AbstractFacade<Proyecto> {
    @PersistenceContext(unitName = "UCDIT-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProyectoFacade() {
        super(Proyecto.class);
    }
 
    public Proyecto obtenerUltimaProyectoRegistrado() {
        try {
            em.clear();
            Query query = em.createQuery("SELECT p FROM Proyecto p ORDER BY p.idproyecto DESC");
            query.setHint("javax.persistence.cache.storeMode", "REFRESH");
            List<Proyecto> registros = query.getResultList();
            if (registros != null) {
                System.out.println("registros : " + registros.size());
                Proyecto ultimoRegistro = registros.get(0);
                return ultimoRegistro;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error ProyectoFacade obtenerUltimaProyectoRegistrado: " + e.toString());
            return null;
        }
    }
}
