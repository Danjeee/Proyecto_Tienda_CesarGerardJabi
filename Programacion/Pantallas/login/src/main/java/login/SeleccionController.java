package login;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class SeleccionController {

    @FXML
    private App PantallaPrincipal = new App();

    @FXML
    public void cargarVentana_seleccion(ActionEvent actionEvent) throws IOException {
        App.setRoot("Login");
    }
}