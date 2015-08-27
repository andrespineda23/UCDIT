/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.bo;

import com.java.ucdit.bo.interfaces.PruebaBOInterface;
import com.java.ucdit.facade.GastoAdicionalFacade;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author ELECTRONICA
 */
@Stateful
public class PruebaBO implements PruebaBOInterface {

    @EJB
    private GastoAdicionalFacade gastoAdicionalFacade;

}
