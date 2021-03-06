/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.utilidades;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author ELECTRONICA
 */
public class Utilidades {

    //algoritmos
    private static String SHA1 = "SHA-1";

    private final static String NUMEROS = "0123456789";

    private final static String MAYUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private final static String MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";

    private static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    /**
     * Metodo que valida si un objeto se encuentra vacio
     *
     * @param obj Objeto a validar
     * @return true-Diferente de nulo / false-Es nulo
     */
    public static Boolean validarNulo(Object obj) {
        if (null != obj) {
            return true;
        }
        return false;
    }

    /**
     * Metodo que valida un correo electronico ingresado
     *
     * @param correo Correo electronico
     * @return true-Correo correcto / false-Correo incorrecto
     */
    public static boolean validarCorreoElectronico(String correo) {
        Pattern pattern = Pattern.compile(PATTERN_EMAIL);
        Matcher matcher = pattern.matcher(correo);
        return matcher.matches();
    }

    /**
     * Metodo que valida que un caracter string este conformado unicamente por
     * caracteres
     *
     * @param str String a validar
     * @return true-Palabra correcta / false-Palabra incorrecta
     */
    public static boolean validarCaracterString(String str) {
        System.out.println("validarCaracterString data : " + str);
        boolean respuesta = false;
        Pattern pattern = Pattern.compile("([a-z]|[A-Z]|[ÁÉÍÓÚ]|[áéíóú]|[ñÑ]|\\s)+");
        Matcher matcher = pattern.matcher(str);
        respuesta = matcher.matches();
        return respuesta;
    }

    /**
     * Metodo que valida que un caracter string este conformado unicamente por
     * caracteres
     *
     * @param str String a validar
     * @return true-Palabra correcta / false-Palabra incorrecta
     */
    public static boolean validarCaracteresAlfaNumericos(String str) {
        boolean respuesta = false;
        Pattern pattern = Pattern.compile("([a-z]|[A-Z]|[ÁÉÍÓÚ]|[áéíóú]|[0-9]|[-]|[/]|[.]|[#]|\\s)+");
        Matcher matcher = pattern.matcher(str);
        respuesta = matcher.matches();
        return respuesta;
    }

    public static boolean validarDirecciones(String str) {
        boolean respuesta = false;
        Pattern pattern = Pattern.compile("([a-z]|[A-Z]|[0-9]|[-]|[.]|[#]|[o]|\\s)+");
        Matcher matcher = pattern.matcher(str);
        respuesta = matcher.matches();
        return respuesta;
    }

    /**
     * Metodo que valida si un numero es numero y no posee algun caracter
     * diferente
     *
     * @param numero Numero a validar
     * @return true-Es numero / false-No es numero
     */
    public static boolean isNumber(String numero) {
        try {
            boolean respuesta = false;
            Pattern pattern = Pattern.compile("([0-9])+");
            Matcher matcher = pattern.matcher(numero);
            respuesta = matcher.matches();
            return respuesta;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Metodo que valida si un numero es numero y no posee algun caracter
     * diferente. Adicionalmente si ese numero es mayor a cero
     *
     * @param numero Numero a validar
     * @return true-Es numero / false-No es numero
     */
    public static boolean isNumberGreaterThanZero(String numero) {
        try {
            boolean respuesta = false;
            Pattern pattern = Pattern.compile("^[1-9][0-9]?$|^100$");
            Matcher matcher = pattern.matcher(numero);
            respuesta = matcher.matches();
            return respuesta;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * *
     * Convierte un arreglo de bytes a String usando valores hexadecimales
     *
     * @param digest arreglo de bytes a convertir
     * @return String creado a partir de <code>digest</code>
     */
    private static String toHexadecimal(byte[] digest) {
        String hash = "";
        for (byte aux : digest) {
            int b = aux & 0xff;
            if (Integer.toHexString(b).length() == 1) {
                hash += "0";
            }
            hash += Integer.toHexString(b);
        }
        return hash;
    }

    /**
     * *
     * Encripta un mensaje de texto mediante algoritmo de resumen de mensaje.
     *
     * @param message texto a encriptar
     * @return mensaje encriptado
     */
    public static String codificarString(String message) {
        byte[] digest = null;
        byte[] buffer = message.getBytes();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(SHA1);
            messageDigest.reset();
            messageDigest.update(buffer);
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("Error creando Digest");
        }
        return toHexadecimal(digest);
    }

    public static String generarNuevaContrasenia() {
        String key = MAYUSCULAS + MINUSCULAS + NUMEROS;
        String pswd = "";
        for (int i = 0; i < 12; i++) {
            pswd += (key.charAt((int) (Math.random() * key.length())));
        }
        return pswd;
    }

    /**
     * Metodo encargado de validar si una fecha ingresada es menor o igual a la
     * fecha actual
     *
     * @param fechaValidar Fecha a validar
     * @return true - fecha correcta / false - fecha incorrecta (fecha mayor)
     */
    public static boolean fechaIngresadaCorrecta(Date fechaValidar) {
        try {
            boolean retorno = true;
            Date fechaDia = new Date();
            if (fechaValidar.getDay() < fechaDia.getDay()) {
                retorno = false;
            }
            if (fechaValidar.getMonth() < fechaDia.getMonth()) {
                retorno = false;
            }
            if (fechaValidar.getYear() < fechaDia.getYear()) {
                retorno = false;
            }
            return retorno;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Metodo encargado de validar si una fecha ingresada es mayor a la fecha
     * actual
     *
     * @param fechaValidar Fecha a validar
     * @return true - fecha correcta / false - fecha incorrecta (fecha mayor)
     */
    public static boolean fechaDiferidaIngresadaCorrecta(Date fechaValidar) {
        try {
            boolean retorno = false;
            Date fechaDia = new Date();
            if (fechaDia.after(fechaValidar)) {
                retorno = true;
            }
            return retorno;
        } catch (Exception e) {
            return false;
        }
    }
}
