/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.facade;

import com.java.ucdit.entidades.BitacoraProyecto;
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
public class BitacoraProyectoFacade extends AbstractFacade<BitacoraProyecto> {
    @PersistenceContext(unitName = "UCDIT-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BitacoraProyectoFacade() {
        super(BitacoraProyecto.class);
    }
    
    public List<BitacoraProyecto> obtenerBitacoraProyectoPorIdProyecto(BigInteger idProyecto) {
        try {
            em.clear();
            Query query = em.createQuery("SELECT p FROM BitacoraProyecto p WHERE p.proyecto.idproyecto=:idProyecto");
            query.setHint("javax.persistence.cache.storeMode", "REFRESH");
            query.setParameter("idProyecto", idProyecto);
            List<BitacoraProyecto> lista = query.getResultList();
            return lista;
        } catch (Exception e) {
            System.out.println("Error BitacoraProyectoFacade obtenerBitacoraProyectoPorIdProyecto: " + e.toString());
            return null;
        }
    }
    
}
