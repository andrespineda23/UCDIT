/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.facade;

import com.java.ucdit.entidades.Componente;
import com.java.ucdit.entidades.Componente;
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
public class ComponenteFacade extends AbstractFacade<Componente> {

    @PersistenceContext(unitName = "UCDIT-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ComponenteFacade() {
        super(Componente.class);
    }

    public List<Componente> consultarComponentesPorIdEquipo(BigInteger equipo) {
        try {
            em.clear();
            Query query = em.createQuery("SELECT p FROM Componente p WHERE p.equipotecnologico.idequipotecnologico=:equipo");
            query.setHint("javax.persistence.cache.storeMode", "REFRESH");
            query.setParameter("equipo", equipo);
            List<Componente> lista = query.getResultList();
            return lista;
        } catch (Exception e) {
            System.out.println("Error ComponenteFacade consultarComponentesPorIdEquipo: " + e.toString());
            return null;
        }
    }

    public Componente consultarComponentePorCodigoIdComponente(String codigo, BigInteger equipo) {
        try {
            em.clear();
            Query query = em.createQuery("SELECT p FROM Componente p WHERE p.equipotecnologico.idequipotecnologico=:equipo AND p.codigocomponente=:codigo");
            query.setHint("javax.persistence.cache.storeMode", "REFRESH");
            query.setParameter("codigo", codigo);
            query.setParameter("equipo", equipo);
            Componente registro = (Componente) query.getSingleResult();
            return registro;
        } catch (Exception e) {
            System.out.println("Error ComponenteFacade consultarCodigoRepetidoComponente: " + e.toString());
            return null;
        }
    }
}
