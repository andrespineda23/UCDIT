/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.facade;

import com.java.ucdit.entidades.GastoAdicional;
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
public class GastoAdicionalFacade extends AbstractFacade<GastoAdicional> {

    @PersistenceContext(unitName = "UCDIT-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GastoAdicionalFacade() {
        super(GastoAdicional.class);
    }

    public List<GastoAdicional> consultarGastoAdicionalPorIdProyecto(BigInteger proyecto) {
        try {
            em.clear();
            Query query = em.createQuery("SELECT p FROM GastoAdicional p WHERE p.proyecto.idproyecto=:proyecto");
            query.setHint("javax.persistence.cache.storeMode", "REFRESH");
            query.setParameter("proyecto", proyecto);
            List<GastoAdicional> lista = query.getResultList();
            return lista;
        } catch (Exception e) {
            System.out.println("Error GastoAdicionalFacade consultarGastoAdicionalsPorIdEquipo: " + e.toString());
            return null;
        }
    }
}
