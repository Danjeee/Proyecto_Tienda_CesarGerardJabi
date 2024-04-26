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
        PantallaPrincipal.mostrarVentana_altaProductos(actionEvent, "C:\\Users\\CicloM\\Documents\\GitHub\\Proyecto_Tienda_CesarGerardJabi\\LM\\Diseño\\PanelAdministracion\\panel_administración_Redimensionada_Cesar_Javi_Gerard.fxml");
    }

    @FXML
    public void cargarVentana_AdministrarUsuario(ActionEvent actionEvent) {
        PantallaPrincipal.mostrarVentana_altaProductos(actionEvent, "Proyecto_Tienda_CesarGerardJabi.LM.Diseño.EditarEmpleado.pantalla3.fxml");
    }

    @FXML
    public void cargarVentana_AdministrarProducto(ActionEvent actionEvent) {
        PantallaPrincipal.mostrarVentana_altaProductos(actionEvent, "Administrar_Producto_Redimensionado_Cesar_Javi_Gerard.fxml");
    }
    
    @FXML
    public void cargarVentana_AdministrarEmpleados(ActionEvent actionEvent) {
        PantallaPrincipal.mostrarVentana_altaProductos(actionEvent, "Administrar_Empleados_Redimensionado_Cesar_Javi_Gerard.fxml");
    }
    
}
