/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.facade;

import com.java.ucdit.entidades.EquipoPorProyecto;
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
public class EquipoPorProyectoFacade extends AbstractFacade<EquipoPorProyecto> {

    @PersistenceContext(unitName = "UCDIT-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EquipoPorProyectoFacade() {
        super(EquipoPorProyecto.class);
    }

    public List<EquipoPorProyecto> obtenerEquipoPorProyectoPorIdProyecto(BigInteger idProyecto) {
        try {
            em.clear();
            Query query = em.createQuery("SELECT p FROM EquipoPorProyecto p WHERE p.proyecto.idproyecto=:idProyecto");
            query.setHint("javax.persistence.cache.storeMode", "REFRESH");
            query.setParameter("idProyecto", idProyecto);
            List<EquipoPorProyecto> lista = query.getResultList();
            return lista;
        } catch (Exception e) {
            System.out.println("Error EquipoPorProyectoFacade obtenerEquipoPorProyectoPorIdProyecto: " + e.toString());
            return null;
        }
    }

}
