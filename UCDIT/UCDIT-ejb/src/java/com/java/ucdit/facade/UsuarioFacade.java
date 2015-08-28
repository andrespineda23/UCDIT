/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.facade;

import com.java.ucdit.entidades.Usuario;
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
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "UCDIT-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    public Usuario obtenerUltimoUsuarioRegistrada() {
        try {
            em.clear();
            Query query = em.createQuery("SELECT p FROM Usuario p ORDER BY p.idusuario DESC");
            query.setHint("javax.persistence.cache.storeMode", "REFRESH");
            List<Usuario> registros = query.getResultList();
            if (registros != null) {
                System.out.println("registros : " + registros.size());
                Usuario ultimoRegistro = registros.get(0);
                return ultimoRegistro;
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error UsuarioFacade obtenerUltimoUsuarioRegistrada: " + e.toString());
            return null;
        }
    }

}
