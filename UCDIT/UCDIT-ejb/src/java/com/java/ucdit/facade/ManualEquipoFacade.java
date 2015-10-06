/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.facade;

import com.java.ucdit.entidades.ManualEquipo;
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
public class ManualEquipoFacade extends AbstractFacade<ManualEquipo> {
    @PersistenceContext(unitName = "UCDIT-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ManualEquipoFacade() {
        super(ManualEquipo.class);
    }
    
    public List<ManualEquipo> consultarManualEquiposPorIdEquipo(BigInteger equipo) {
        try {
            em.clear();
            Query query = em.createQuery("SELECT p FROM ManualEquipo p WHERE p.equipotecnologico.idequipotecnologico=:equipo");
            query.setHint("javax.persistence.cache.storeMode", "REFRESH");
            query.setParameter("equipo", equipo);
            List<ManualEquipo> lista = query.getResultList();
            return lista;
        } catch (Exception e) {
            System.out.println("Error ManualEquipoFacade consultarManualEquiposPorIdEquipo: " + e.toString());
            return null;
        }
    }
}
