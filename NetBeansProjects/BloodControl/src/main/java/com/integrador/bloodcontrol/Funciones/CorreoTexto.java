/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.integrador.bloodcontrol.Funciones;

import java.util.Properties;
import javafx.concurrent.Task;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author abdias
 */
public class CorreoTexto extends Task <Void> {

    String Username = "bloodcontrol.no.reply@gmail.com";
    String PassWord = "Bloodcontrol.abdias";
    String To = "";
    String Subject = "NOTIFICACION BLOODCONTROL";
    String correo, nombre, Mensaje, Bienvenida;

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setMensaje(String Mensaje) {
        this.Mensaje = Mensaje;
    }

    public void setBienvenida(String Bienvenida) {
        this.Bienvenida = Bienvenida;
    }
    
    @Override
    protected Void call() throws Exception {

        To = correo;
        Mensaje = Bienvenida + "\n\n" + nombre + "\n\n" + Mensaje;
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(Username, PassWord);
            }
        });
        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(Username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(To));
            message.setSubject(Subject);
            message.setText(Mensaje);
            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

}
