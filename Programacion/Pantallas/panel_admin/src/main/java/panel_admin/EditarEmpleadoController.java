package panel_admin;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import panel_admin.MenuHamburguesa.MenuHamb;

public class EditarEmpleadoController {

    public static Object current;
    @FXML
    private App PantallaPrincipal = new App();

    @FXML
    public void retroceder_ListaEmpleados(ActionEvent actionEvent) throws IOException {
        App.setRoot("AdministrarEmpleados_Cesar_Javi_Gerard");
        initialize();
    }
    
    public void initialize() {
    }

}
