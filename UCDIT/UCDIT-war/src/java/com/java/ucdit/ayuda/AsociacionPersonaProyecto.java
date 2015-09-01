/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.ayuda;

import com.java.ucdit.entidades.PersonalInterno;
import java.io.Serializable;

/**
 *
 * @author ELECTRONICA
 */
public class AsociacionPersonaProyecto implements Serializable {

    private PersonalInterno personalInterno;
    private boolean activo;

    public AsociacionPersonaProyecto() {
    }

    public AsociacionPersonaProyecto(PersonalInterno personalInterno, boolean activo) {
        this.personalInterno = personalInterno;
        this.activo = activo;
    }

    public PersonalInterno getPersonalInterno() {
        return personalInterno;
    }

    public void setPersonalInterno(PersonalInterno personalInterno) {
        this.personalInterno = personalInterno;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

}
