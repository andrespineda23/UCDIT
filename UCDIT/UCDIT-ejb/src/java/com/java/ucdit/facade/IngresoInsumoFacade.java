/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.facade;

import com.java.ucdit.entidades.IngresoInsumo;
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
public class IngresoInsumoFacade extends AbstractFacade<IngresoInsumo> {

    @PersistenceContext(unitName = "UCDIT-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public IngresoInsumoFacade() {
        super(IngresoInsumo.class);
    }

    public List<IngresoInsumo> obtenerIngresoInsumoPorIdInsumo(BigInteger idInsumo) {
        try {
            em.clear();
            Query query = em.createQuery("SELECT p FROM IngresoInsumo p WHERE p.insumo.idinsumo=:idInsumo");
            query.setHint("javax.persistence.cache.storeMode", "REFRESH");
            query.setParameter("idInsumo", idInsumo);
            List<IngresoInsumo> lista = query.getResultList();
            return lista;
        } catch (Exception e) {
            System.out.println("Error IngresoInsumoFacade obtenerIngresoInsumoPorIdInsumo: " + e.toString());
            return null;
        }
    }

}
