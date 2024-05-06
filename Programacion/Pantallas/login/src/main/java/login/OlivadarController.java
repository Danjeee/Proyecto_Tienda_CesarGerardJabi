package login;
import java.io.IOError;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class OlivadarController {

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
}