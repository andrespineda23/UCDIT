/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.bo.interfaces.cliente;

import com.java.ucdit.entidades.Cliente;
import java.math.BigInteger;
import java.util.List;

/**
 *
 * @author ELECTRONICA
 */
public interface AdministrarClienteBOInterface {

    public List<Cliente> consultarClientesRegistrados();

    public void crearCliente(Cliente cliente);

    public void editarCliente(Cliente cliente);

    public Cliente obtenerClientePorId(BigInteger idCliente);
}
