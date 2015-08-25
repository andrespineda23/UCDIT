/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.facade;

import com.java.ucdit.entidades.GastoAdicional;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ELECTRONICA
 */
@Stateless
public class GastoAdicionalFacade extends AbstractFacade<GastoAdicional> {
    @PersistenceContext(unitName = "UCDIT-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GastoAdicionalFacade() {
        super(GastoAdicional.class);
    }
    
}
