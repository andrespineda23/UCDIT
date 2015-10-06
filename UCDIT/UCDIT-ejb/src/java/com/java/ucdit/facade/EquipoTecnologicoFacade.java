/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.facade;

import com.java.ucdit.entidades.EquipoTecnologico;
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
public class EquipoTecnologicoFacade extends AbstractFacade<EquipoTecnologico> {

    @PersistenceContext(unitName = "UCDIT-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EquipoTecnologicoFacade() {
        super(EquipoTecnologico.class);
    }

    public EquipoTecnologico buscarEquipoTecnologicoPorCodigo(String codigo) {
        try {
            em.clear();
            Query query = em.createQuery("SELECT p FROM EquipoTecnologico p WHERE p.codigoequipo=:codigo");
            query.setHint("javax.persistence.cache.storeMode", "REFRESH");
            query.setParameter("codigo", codigo);
            EquipoTecnologico registro = (EquipoTecnologico) query.getSingleResult();
            return registro;
        } catch (Exception e) {
            System.out.println("Error EquipoTecnologicoFacade buscarEquipoTecnologicoPorCodigo: " + e.toString());
            return null;
        }
    }
    
    public EquipoTecnologico obtenerUltimoEquipoTecnologicoRegistrado() {
        try {
            em.clear();
            Query query = em.createQuery("SELECT p FROM EquipoTecnologico p ORDER BY p.idequipotecnologico DESC");
            query.setHint("javax.persistence.cache.storeMode", "REFRESH");
            List<EquipoTecnologico> registros = query.getResultList();
            if (registros != null) {
                System.out.println("registros : " + registros.size());
                EquipoTecnologico ultimoRegistro = registros.get(0);
                return ultimoRegistro;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error EquipoTecnologicoFacade obtenerUltimoEquipoTecnologicoRegistrado: " + e.toString());
            return null;
        }
    }
}
