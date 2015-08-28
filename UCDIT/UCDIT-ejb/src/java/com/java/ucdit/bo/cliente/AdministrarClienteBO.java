/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.bo.cliente;

import com.java.ucdit.bo.interfaces.cliente.AdministrarClienteBOInterface;
import com.java.ucdit.entidades.Cliente;
import com.java.ucdit.facade.ClienteFacade;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author ELECTRONICA
 */
@Stateful
public class AdministrarClienteBO implements AdministrarClienteBOInterface {

    @EJB
    ClienteFacade clienteFacade;

    //@Override
    public List<Cliente> consultarClientesRegistrados() {
        try {
            List<Cliente> lista = clienteFacade.findAll();
            return lista;
        } catch (Exception e) {
            System.out.println("Error AdministrarClientesBO consultarClientesRegistrados: " + e.toString());
            return null;
        }
    }

    @Override
    public void crearCliente(Cliente cliente) {
        try {
            clienteFacade.create(cliente);
        } catch (Exception e) {
            System.out.println("Error AdministrarClientesBO crearCliente: " + e.toString());
        }
    }

    @Override
    public void editarCliente(Cliente cliente) {
        try {
            clienteFacade.edit(cliente);
        } catch (Exception e) {
            System.out.println("Error AdministrarClientesBO editarCliente: " + e.toString());
        }
    }

    @Override
    public Cliente obtenerClientePorId(BigInteger idCliente) {
        try {
            Cliente registro = clienteFacade.find(idCliente);
            return registro;
        } catch (Exception e) {
            System.out.println("Error AdministrarClientesBO obtenerClientePorId: " + e.toString());
            return null;
        }
    }
}
