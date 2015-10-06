/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.facade;

import com.java.ucdit.entidades.IngresoEquipo;
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
public class IngresoEquipoFacade extends AbstractFacade<IngresoEquipo> {

    @PersistenceContext(unitName = "UCDIT-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IngresoEquipoFacade() {
        super(IngresoEquipo.class);
    }

    public IngresoEquipo obtenerIngresoEquipoPorIdEquipo(BigInteger equipo) {
        try {
            em.clear();
            Query query = em.createQuery("SELECT p FROM IngresoEquipo p WHERE p.equipotecnologico.idequipotecnologico=:equipo");
            query.setHint("javax.persistence.cache.storeMode", "REFRESH");
            query.setParameter("equipo", equipo);
            IngresoEquipo registro = (IngresoEquipo) query.getSingleResult();
            return registro;
        } catch (Exception e) {
            System.out.println("Error IngresoEquipoFacade obtenerIngresoEquipoPorIdEquipo: " + e.toString());
            return null;
        }
    }

}
