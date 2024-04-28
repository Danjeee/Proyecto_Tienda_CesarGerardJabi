package login;

import java.io.IOException;

import javax.swing.Action;

import javafx.fxml.FXML;

public class RegistroController {

    @FXML
    private App PantallaPrincipal = new App();

    @FXML
    public void cargarventana_Registro(ActionEvent actionEvent) throws Exception{
        App.setRoot("Pantalla2.fxml");
    }
}
