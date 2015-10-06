/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.bo.proyecto;

import com.java.ucdit.bo.interfaces.proyecto.AdministrarProyectoBOInterface;
import com.java.ucdit.entidades.Cliente;
import com.java.ucdit.entidades.EquipoPorProyecto;
import com.java.ucdit.entidades.GastoAdicional;
import com.java.ucdit.entidades.InsumoPorProyecto;
import com.java.ucdit.entidades.PersonalInterno;
import com.java.ucdit.entidades.PersonalPorProyecto;
import com.java.ucdit.entidades.Proyecto;
import com.java.ucdit.entidades.Supervisor;
import com.java.ucdit.facade.BitacoraProyectoFacade;
import com.java.ucdit.facade.ClienteFacade;
import com.java.ucdit.facade.EquipoPorProyectoFacade;
import com.java.ucdit.facade.GastoAdicionalFacade;
import com.java.ucdit.facade.InsumoPorProyectoFacade;
import com.java.ucdit.facade.PersonalInternoFacade;
import com.java.ucdit.facade.PersonalPorProyectoFacade;
import com.java.ucdit.facade.ProyectoFacade;
import com.java.ucdit.facade.SupervisorFacade;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author ELECTRONICA
 */
@Stateful
public class AdministrarProyectoBO implements AdministrarProyectoBOInterface {

    @EJB
    ProyectoFacade proyectoFacade;
    @EJB
    PersonalPorProyectoFacade personalPorProyectoFacade;
    @EJB
    GastoAdicionalFacade gastoAdicionalFacade;
    @EJB
    BitacoraProyectoFacade bitacoraProyectoFacade;
    @EJB
    EquipoPorProyectoFacade equipoPorProyectoFacade;
    @EJB
    InsumoPorProyectoFacade insumoPorProyectoFacade;
    @EJB
    ClienteFacade clienteFacade;
    @EJB
    SupervisorFacade supervisorFacade;
    @EJB
    PersonalInternoFacade personalInternoFacade;

    //@Override
    public List<Proyecto> consultarProyectosRegistrados() {
        try {
            List<Proyecto> lista = proyectoFacade.findAll();
            return lista;
        } catch (Exception e) {
            System.out.println("Error AdministrarProyectoBO consultarProyectosRegistrados: " + e.toString());
            return null;
        }
    }

    @Override
    public void crearProyecto(Proyecto proyecto, List<PersonalInterno> lista) {
        try {
            proyectoFacade.create(proyecto);
            Proyecto nuevoProyecto = proyectoFacade.obtenerUltimaProyectoRegistrado();
            for (int i = 0; i < lista.size(); i++) {
                PersonalPorProyecto personal = new PersonalPorProyecto();
                personal.setPersonalinterno(lista.get(i));
                personal.setProyecto(nuevoProyecto);
                personal.setEstado(true);
                personalPorProyectoFacade.create(personal);
            }
        } catch (Exception e) {
            System.out.println("Error AdministrarProyectoBO crearProyecto: " + e.toString());
        }
    }

    @Override
    public void editarProyecto(Proyecto proyecto) {
        try {
            proyectoFacade.edit(proyecto);
        } catch (Exception e) {
            System.out.println("Error AdministrarProyectoBO editarProyecto: " + e.toString());
        }
    }

    @Override
    public Proyecto obtenerProyectoPorId(BigInteger idProyecto) {
        try {
            Proyecto registro = proyectoFacade.find(idProyecto);
            return registro;
        } catch (Exception e) {
            System.out.println("Error AdministrarProyectoBO obtenerProyectoPorId: " + e.toString());
            return null;
        }
    }

    @Override
    public List<Cliente> obtenerClientesRegistrados() {
        try {
            List<Cliente> lista = clienteFacade.findAll();
            return lista;
        } catch (Exception e) {
            System.out.println("Error AdministrarProyectoBO obtenerClientesRegistrados: " + e.toString());
            return null;
        }
    }

    @Override
    public List<Supervisor> obtenerSupervisoresRegistrados() {
        try {
            List<Supervisor> lista = supervisorFacade.findAll();
            if (null != lista) {
                for (int i = 0; i < lista.size(); i++) {
                    if ("supervisor".equals(lista.get(i).getPersona().getUsuario().getUsuario())) {
                        lista.remove(i);
                    }
                }
            }
            return lista;
        } catch (Exception e) {
            System.out.println("Error AdministrarProyectoBO obtenerSupervisoresRegistrados: " + e.toString());
            return null;
        }
    }

    @Override
    public List<PersonalPorProyecto> obtenerPersonalPorProyectoPorIdProyecto(BigInteger proyecto) {
        try {
            List<PersonalPorProyecto> lista = personalPorProyectoFacade.obtenerPersonalPorProyectoPorIdProyecto(proyecto);
            return lista;
        } catch (Exception e) {
            System.out.println("Error AdministrarProyectoBO obtenerPersonalPorProyectoPorIdProyecto: " + e.toString());
            return null;
        }
    }

    @Override
    public void crearPersonalPorProyecto(PersonalPorProyecto personalPorProyecto) {
        try {
            personalPorProyectoFacade.create(personalPorProyecto);
        } catch (Exception e) {
            System.out.println("Error AdministrarProyectoBO crearPersonalPorProyecto: " + e.toString());
        }
    }

    @Override
    public List<InsumoPorProyecto> consultarInsumoPorProyectoPorIdProyecto(BigInteger proyecto) {
        try {
            List<InsumoPorProyecto> lista = insumoPorProyectoFacade.obtenerInsumoPorProyectoPorIdProyecto(proyecto);
            return lista;
        } catch (Exception e) {
            System.out.println("Error AdministrarProyectoBO consultarInsumoPorProyectoPorIdProyecto: " + e.toString());
            return null;
        }
    }

    @Override
    public List<EquipoPorProyecto> consultarEquipoProProyectoPorIdProyecto(BigInteger proyecto) {
        try {
            List<EquipoPorProyecto> lista = equipoPorProyectoFacade.obtenerEquipoPorProyectoPorIdProyecto(proyecto);
            return lista;
        } catch (Exception e) {
            System.out.println("Error AdministrarProyectoBO consultarEquipoProProyectoPorIdProyecto: " + e.toString());
            return null;
        }
    }

    @Override
    public void crearInsumoPorProyecto(InsumoPorProyecto insumoPorProyecto) {
        try {
            insumoPorProyectoFacade.create(insumoPorProyecto);
        } catch (Exception e) {
            System.out.println("Error AdministrarProyectoBO crearInsumoPorProyecto: " + e.toString());
        }
    }

    @Override
    public void editarInsumoPorProyecto(InsumoPorProyecto insumoPorProyecto) {
        try {
            insumoPorProyectoFacade.edit(insumoPorProyecto);
        } catch (Exception e) {
            System.out.println("Error AdministrarProyectoBO editarInsumoPorProyecto: " + e.toString());
        }
    }

    @Override
    public void crearEquipoPorProyecto(EquipoPorProyecto equipoPorProyecto) {
        try {
            equipoPorProyectoFacade.create(equipoPorProyecto);
        } catch (Exception e) {
            System.out.println("Error AdministrarProyectoBO crearEquipoPorProyecto: " + e.toString());
        }
    }

    @Override
    public void editarIEquipoPorProyecto(EquipoPorProyecto equipoPorProyecto) {
        try {
            equipoPorProyectoFacade.edit(equipoPorProyecto);
        } catch (Exception e) {
            System.out.println("Error AdministrarProyectoBO editarIEquipoPorProyecto: " + e.toString());
        }
    }

    @Override
    public List<PersonalInterno> obtenerPersonalInternoRegistrado() {
        try {
            List<PersonalInterno> lista = personalInternoFacade.obtenerPersonalInternoActivo();
            return lista;
        } catch (Exception e) {
            System.out.println("Error AdministrarProyectoBO obtenerPersonalInternoRegistrado: " + e.toString());
            return null;
        }
    }

    @Override
    public void asociarPersonalAProyecto(Proyecto proyecto, List<PersonalPorProyecto> personalPorProyecto, List<PersonalInterno> personalInterno) {
        try {
            for (int i = 0; i < personalPorProyecto.size(); i++) {
                personalPorProyectoFacade.edit(personalPorProyecto.get(i));
            }
            if (null != personalInterno) {
                for (int i = 0; i < personalInterno.size(); i++) {
                    PersonalPorProyecto registro = new PersonalPorProyecto();
                    registro.setEstado(true);
                    registro.setProyecto(proyecto);
                    registro.setPersonalinterno(personalInterno.get(i));
                    personalPorProyectoFacade.create(registro);
                }
            }
        } catch (Exception e) {
            System.out.println("Error AdministrarProyectoBO asociarPersonalAProyecto: " + e.toString());
        }
    }

    @Override
    public List<GastoAdicional> consultarGastoAdicionalProyecto(BigInteger idProyecto) {
        try {
            List<GastoAdicional> lista = gastoAdicionalFacade.consultarGastoAdicionalPorIdProyecto(idProyecto);
            return lista;
        } catch (Exception e) {
            System.out.println("Error AdministrarProyectoBO consultarGastoAdicionalProyecto: " + e.toString());
            return null;
        }
    }

}
