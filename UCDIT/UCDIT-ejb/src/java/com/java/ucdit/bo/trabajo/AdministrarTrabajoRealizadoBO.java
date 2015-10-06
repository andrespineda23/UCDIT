/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.bo.trabajo;

import com.java.ucdit.bo.interfaces.trabajo.AdministrarTrabajoRealizadoBOInterface;
import com.java.ucdit.entidades.TrabajoRealizado;
import com.java.ucdit.facade.ObjetivoPorPersonalProyectoFacade;
import com.java.ucdit.facade.PersonalInternoFacade;
import com.java.ucdit.facade.ProyectoFacade;
import com.java.ucdit.facade.TrabajoRealizadoFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.ejb.LocalBean;

/**
 *
 * @author ELECTRONICA
 */
@Stateful
public class AdministrarTrabajoRealizadoBO implements AdministrarTrabajoRealizadoBOInterface {

    @EJB
    TrabajoRealizadoFacade trabajoRealizadoFacade;
    @EJB
    ObjetivoPorPersonalProyectoFacade objetivoPorPersonalProyectoFacade;
    @EJB
    PersonalInternoFacade personalInternoFacade;
    @EJB
    ProyectoFacade proyectoFacade;
}
