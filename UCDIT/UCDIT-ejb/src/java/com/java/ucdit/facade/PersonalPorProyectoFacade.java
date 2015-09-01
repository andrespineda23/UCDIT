/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.facade;

import com.java.ucdit.entidades.PersonalPorProyecto;
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
public class PersonalPorProyectoFacade extends AbstractFacade<PersonalPorProyecto> {

    @PersistenceContext(unitName = "UCDIT-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PersonalPorProyectoFacade() {
        super(PersonalPorProyecto.class);
    }

    public List<PersonalPorProyecto> obtenerPersonalPorProyectoPorIdProyecto(BigInteger idProyecto) {
        try {
            em.clear();
            Query query = em.createQuery("SELECT p FROM PersonalPorProyecto p WHERE p.proyecto.idproyecto=:idProyecto");
            query.setHint("javax.persistence.cache.storeMode", "REFRESH");
            query.setParameter("idProyecto", idProyecto);
            List<PersonalPorProyecto> lista = query.getResultList();
            return lista;
        } catch (Exception e) {
            System.out.println("Error PersonalPorProyectoFacade obtenerPersonalPorProyectoPorIdProyecto: " + e.toString());
            return null;
        }
    }

}
