package panel_admin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class PanelController {

    @FXML
    private App PantallaPrincipal = new App();

    @FXML
    public void cargarVentana_AltaProducto(ActionEvent actionEvent) {
        PantallaPrincipal.mostrarVentana_altaProductos(actionEvent, "Alta");
    }

    @FXML
    public void cargarVentana_AdministrarUsuario(ActionEvent actionEvent) {
        PantallaPrincipal.mostrarVentana_altaProductos(actionEvent, "Editar_Empleado.fxml");
    }

    @FXML
    public void cargarVentana_AdministrarProducto(ActionEvent actionEvent) {
        PantallaPrincipal.mostrarVentana_altaProductos(actionEvent, "Administrar_Producto.fxml");
    }
    
    @FXML
    public void cargarVentana_AdministrarEmpleados(ActionEvent actionEvent) {
        PantallaPrincipal.mostrarVentana_altaProductos(actionEvent, "Administrar_Empleados.fxml");
    }
    
}
