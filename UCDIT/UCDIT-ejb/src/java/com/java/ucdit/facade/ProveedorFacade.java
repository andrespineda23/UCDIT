/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.facade;

import com.java.ucdit.entidades.Proveedor;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author ELECTRONICA
 */
@Stateless
public class ProveedorFacade extends AbstractFacade<Proveedor> {

    @PersistenceContext(unitName = "UCDIT-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProveedorFacade() {
        super(Proveedor.class);
    }

    public Proveedor obtenerProveedorPorNit(String nit) {
        try {
            em.clear();
            Query query = em.createQuery("SELECT p FROM Proveedor p WHERE p.identificacionproveedor=:nit");
            query.setHint("javax.persistence.cache.storeMode", "REFRESH");
            query.setParameter("nit", nit);
            Proveedor registro = (Proveedor) query.getSingleResult();
            return registro;
        } catch (Exception e) {
            System.out.println("Error ProveedorFacade obtenerProveedorPorNit: " + e.toString());
            return null;
        }
    }
}
