package panel_admin;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import panel_admin.MenuHamburguesa.MenuHamb;

public class PanelController {

    @FXML
    private AnchorPane cont;

    @FXML
    private App PantallaPrincipal = new App();

    @FXML
    public void cargarVentana_AltaProducto(ActionEvent actionEvent) throws IOException {
        App.setRoot("AltaProductos_Cesar_Javi_Gerard");
    }

    @FXML
    public void cargarVentana_AdministrarEmpleados(ActionEvent actionEvent) throws IOException {
        App.setRoot("AdministrarEmpleados_Cesar_Javi_Gerard");
    }

    @FXML
    public void cargarVentana_AdministrarUsuarios(ActionEvent actionEvent) throws IOException {
        App.setRoot("AdministrarUsuarios_Cesar_Javi_Gerard");
    }

    @FXML
    public void cargarVentana_AdministrarProductos(ActionEvent actionEvent) throws IOException {
        App.setRoot("AdministrarProductos_Cesar_Javi_Gerard");
    }

    public void initialize() {
        MenuHamb.popupHambMake();
        cont.getChildren().add(MenuHamb.menuShadow);
        cont.getChildren().add(MenuHamb.popupHamb);
        cont.getChildren().add(MenuHamb.menuHamb());
    }
}
