/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.java.ucdit.email;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author ELECTRONICA
 */
public class EnvioCorreo {

    public EnvioCorreo() {
    }

    /**
     * Metodo encargado de enviar via e-mail al usuario que solicito la
     * recuperación de la contraseña un correo con la información de su nueva
     * contraseña
     *
     * @param nuevaContrasenia
     * @param correoElectronico
     */
    public void enviarCorreoRecuperacion(String  correoElectronico, String nuevaContrasenia) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("proyecto.ucdit@gmail.com", "electronica123");
                    }
                });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("proyecto.ucdit@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(correoElectronico));
            message.setSubject("Recuperación de Contraseña - UCDIT");
            message.setText("Se solicito el cambio de contraseña de usuario de la herramienta UCDIT, a continuación se muestra la nueva contraseña: " + nuevaContrasenia + " . Se solicita al usuario ingresar al sistema y realizar el cambio de la misma para evitar inconvenientes futuros.");

            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
