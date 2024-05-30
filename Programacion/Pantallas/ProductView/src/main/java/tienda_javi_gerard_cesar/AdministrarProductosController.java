package tienda_javi_gerard_cesar;


import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import tienda_javi_gerard_cesar.Clases.*;

public class AdministrarProductosController {

    @FXML
    private AnchorPane cont;

    @FXML
    private App PantallaPrincipal = new App();

    @FXML
    public void retroceder_PanelAdmin(ActionEvent actionEvent){
        try {
            App.setRoot("PanelAdministracion_Cesar_Javi_Gerard");
        } catch (Exception e) {
            Logs.createIOLog(e);
        }
    }

    public void initialize() {
        MenuHamb.init(cont);
    }
}
