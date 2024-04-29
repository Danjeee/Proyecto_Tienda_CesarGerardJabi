package login;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private App PantallaPrincipal = new App();

    @FXML
    public void cargarVentana_seleccion(ActionEvent actionEvent) throws IOException {
        App.setRoot("seleccion");
    }

    @FXML
    public void cargarVentana_registro(ActionEvent actionEvent) throws IOException {
        App.setRoot("pantalla2");
    }

    @FXML
    public void flecha_volver(ActionEvent actionEvent) throws IOException{
        App.setRoot("Login");
    }
}