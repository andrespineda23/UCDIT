/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.utilidades;

import java.io.Serializable;
import java.math.BigInteger;

/**
 *
 * @author ELECTRONICA
 */
public class UsuarioLogin implements Serializable {

    private String nombreTipoUsuario;
    private BigInteger idUsuarioLogin;
    private String userUsuario;

    public UsuarioLogin() {
    }

    public UsuarioLogin(String nombreTipoUsuario, BigInteger idUsuarioLogin, String userUsuario) {
        this.nombreTipoUsuario = nombreTipoUsuario;
        this.idUsuarioLogin = idUsuarioLogin;
        this.userUsuario = userUsuario;
    }

    public String getNombreTipoUsuario() {
        return nombreTipoUsuario;
    }

    public void setNombreTipoUsuario(String nombreTipoUsuario) {
        this.nombreTipoUsuario = nombreTipoUsuario;
    }

    public BigInteger getIdUsuarioLogin() {
        return idUsuarioLogin;
    }

    public void setIdUsuarioLogin(BigInteger idUsuarioLogin) {
        this.idUsuarioLogin = idUsuarioLogin;
    }

    public String getUserUsuario() {
        return userUsuario;
    }

    public void setUserUsuario(String userUsuario) {
        this.userUsuario = userUsuario;
    }

}
