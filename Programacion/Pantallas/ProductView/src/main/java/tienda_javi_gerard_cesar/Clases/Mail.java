package tienda_javi_gerard_cesar.Clases;

import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class Mail {
    public static void send(String recipiente, String mensaje, String subj){
        Properties prop = new Properties(); 
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "465");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.socketFactory.port", "465");
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        String cuentaMail = "secondhand1daw@gmail.com";
        String pass = "yxmo pked wpjo kpih";

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(cuentaMail, pass);
            }
        });
        Message message = prepararMensaje(session, cuentaMail, recipiente, subj, mensaje);
        try {
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    private static Message prepararMensaje(Session session, String cuentaMail, String rec, String subj, String mensaje){
        Message m = new MimeMessage(session);
        try {
            m.setFrom(new InternetAddress(cuentaMail));
            m.setRecipient(Message.RecipientType.TO, new InternetAddress(rec));
            m.setSubject(subj);
            m.setText(mensaje);
            return m;
        } catch (AddressException e){
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
