/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.integrador.bloodcontrol.Funciones;

import java.util.Properties;
import javafx.concurrent.Task;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author abdias
 */
public class CorreoAdjunto extends Task <Void> {
    
    String Username = "bloodcontrol.no.reply@gmail.com";
    String PassWord = "Bloodcontrol.abdias";
    String To = "";
    String Subject = "NOTIFICACION BLOODCONTROL";
    String correo, nombre, Mensaje, Bienvenida, archivo;

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

    public void setArchivo(String archivo) {
        this.archivo = archivo;
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
                new javax.mail.Authenticator()
                {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(Username, PassWord);
                    }
                });
    try
                {
                    BodyPart mime = new MimeBodyPart();
                    mime.setText(Mensaje);
                    BodyPart adjunto = new MimeBodyPart();
                    adjunto.setDataHandler(new DataHandler(new FileDataSource(Path.getPath()+"/"+archivo)));
                    adjunto.setFileName(archivo);
                    
                    MimeMultipart message = new MimeMultipart();
                    message.addBodyPart(mime);
                    message.addBodyPart(adjunto);
                    
                    Message struct = new MimeMessage(session);
                    struct.setFrom(new InternetAddress(Username));
                    struct.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(correo));
                    struct.setSubject(Subject);
                    struct.setContent(message);
                    Transport.send(struct);
                    
                }
                catch (MessagingException e)
                {
                    System.out.println(e);
                }    
        
        System.gc();
    
        
        
        
        return null;
    }
    
}
