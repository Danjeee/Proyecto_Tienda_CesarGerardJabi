package tienda_javi_gerard_cesar.Clases;

import java.util.Properties;

import javax.mail.*;

public class Mail {
    public static void send(){
        Properties prop = new Properties(); 
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");

        String cuentaMail = "secondhand1daw@gmail.com";
        String pass = "SecondHand45_";

        Session session = Session.getInstance(prop, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(cuentaMail, pass);
            }
        });
        Message message = prepararMensaje();
    }
}
