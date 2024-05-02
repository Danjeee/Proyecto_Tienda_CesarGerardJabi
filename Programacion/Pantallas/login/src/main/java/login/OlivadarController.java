package login;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class OlivadarController {

    @FXML
    private App PantallaPrincipal = new App();

    @FXML
    public void cargarVentana_olvidar(ActionEvent actionEvent) throws Exception{
        App.setRoot("Login");
    }
}
