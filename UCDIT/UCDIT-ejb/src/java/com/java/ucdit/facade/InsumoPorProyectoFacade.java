/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.facade;

import com.java.ucdit.entidades.InsumoPorProyecto;
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
public class InsumoPorProyectoFacade extends AbstractFacade<InsumoPorProyecto> {

    @PersistenceContext(unitName = "UCDIT-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InsumoPorProyectoFacade() {
        super(InsumoPorProyecto.class);
    }

    public List<InsumoPorProyecto> obtenerInsumoPorProyectoPorIdProyecto(BigInteger idProyecto) {
        try {
            em.clear();
            Query query = em.createQuery("SELECT p FROM InsumoPorProyecto p WHERE p.proyecto.idproyecto=:idProyecto");
            query.setHint("javax.persistence.cache.storeMode", "REFRESH");
            query.setParameter("idProyecto", idProyecto);
            List<InsumoPorProyecto> lista = query.getResultList();
            return lista;
        } catch (Exception e) {
            System.out.println("Error InsumoPorProyectoFacade obtenerInsumoPorProyectoPorIdProyecto: " + e.toString());
            return null;
        }
    }

}
