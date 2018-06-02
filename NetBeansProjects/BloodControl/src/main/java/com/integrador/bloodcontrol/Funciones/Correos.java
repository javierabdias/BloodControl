package com.integrador.bloodcontrol.Funciones;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;




public class Correos extends Thread {
   
    public static String Username = "lunchpack.no.replay@gmail.com";
    public static String PassWord = "St.anger1";
    String To = "";
    String Subject = "Comprobacion de registro";
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
    
    
    public void run()
    {
        To = correo;
        Mensaje = Bienvenida+"\n\n"+nombre+"\n\n"+ Mensaje;
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
     try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(Username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(To));
            message.setSubject(Subject);
            message.setText(Mensaje);
            Transport.send(message);
         
            } catch (MessagingException e)
            {
                throw new RuntimeException(e);
            }
    
}

}
