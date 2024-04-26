package panel_admin;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class PanelController {

    @FXML
    private App PantallaPrincipal = new App();

    @FXML
    public void cargarVentana_AltaProducto(ActionEvent actionEvent) throws IOException {
        App.setRoot("alta");
    }

    @FXML
    public void cargarVentana_AdministrarEmpleado(ActionEvent actionEvent) throws IOException {
        App.setRoot("alta");
    }

    @FXML
    public void cargarVentana_AdministrarProducto(ActionEvent actionEvent) throws IOException {
        App.setRoot("alta");
    }

    @FXML
    public void cargarVentana_AdministrarUsuario(ActionEvent actionEvent) throws IOException {
        App.setRoot("alta");
    }
    
}
