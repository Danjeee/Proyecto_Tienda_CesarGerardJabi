package tienda_javi_gerard_cesar;


import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import tienda_javi_gerard_cesar.Clases.MenuHamb;

public class AdministrarEmpleadosController {

    @FXML
    private AnchorPane cont;

    @FXML
    private App PantallaPrincipal = new App();

    @FXML
    public void retroceder_PanelAdmin(ActionEvent actionEvent) throws IOException {
        App.setRoot("PanelAdministracion_Cesar_Javi_Gerard");
    }

    public void initialize() {
        MenuHamb.init(cont);
    }
}
