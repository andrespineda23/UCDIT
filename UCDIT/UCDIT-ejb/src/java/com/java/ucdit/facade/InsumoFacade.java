/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.facade;

import com.java.ucdit.entidades.Insumo;
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
public class InsumoFacade extends AbstractFacade<Insumo> {

    @PersistenceContext(unitName = "UCDIT-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InsumoFacade() {
        super(Insumo.class);
    }

    public Insumo obtenerInsumoPorCodigo(String codigo) {
        try {
            em.clear();
            Query query = em.createQuery("SELECT p FROM Insumo p WHERE p.codigoinsumo=:codigo");
            query.setHint("javax.persistence.cache.storeMode", "REFRESH");
            query.setParameter("codigo", codigo);
            Insumo registro = (Insumo) query.getSingleResult();
            return registro;
        } catch (Exception e) {
            System.out.println("Error InsumoFacade obtenerInsumoPorCodigo: " + e.toString());
            return null;
        }
    }

    public Insumo obtenerUltimoInsumoRegistrado() {
        try {
            em.clear();
            Query query = em.createQuery("SELECT p FROM Insumo p ORDER BY p.idinsumo DESC");
            query.setHint("javax.persistence.cache.storeMode", "REFRESH");
            List<Insumo> registros = query.getResultList();
            if (registros != null) {
                System.out.println("registros : " + registros.size());
                Insumo ultimoRegistro = registros.get(0);
                return ultimoRegistro;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error InsumoFacade obtenerUltimaInsumoRegistrada: " + e.toString());
            return null;
        }
    }

}
