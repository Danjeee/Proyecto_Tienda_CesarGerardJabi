package login;


import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import login.Clases.Mail;
import login.Clases.User;

public class OlivadarController {

    @FXML
    private TextField emailArevisar;

    @FXML
    private App PantallaPrincipal = new App();

    @FXML
    public void cargarVentana_olvidar(ActionEvent actionEvent) throws IOException {
        App.setRoot("Login");
    }

    @FXML
    public void flecha_volver(ActionEvent actionEvent) throws IOException {
        App.setRoot("Login");
    }

    // Verificar email, si es correcto se envia, si no, lo que hacemos es sacar una alerta
    @FXML
    public void enviar(){

        User i = buscarMail(emailArevisar);
        if (i != null) {

            App.user = i.getDNI();
            try {
                App.setRoot("seleccion");
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("INICIO DE SESIÓN INCORRECTO.");
            alert.setTitle("ERROR");
            alert.setContentText("RECUERDE RELLENAR CORRECTAMENTE LOS CAMPOS.");
            alert.showAndWait();

        }
        
        Mail.send(null, "Restablecer la contraseña", "Se ha enviado el mail para poder restablecer la contraseña");
    }

    private User buscarMail(ArrayList<User> emails) {
        User u = null;

        for (User i : emails) {
            if (i.getMail().equals(emailArevisar.getText()) ) {
                u = i;
            }
        }
        return u;

    }
}