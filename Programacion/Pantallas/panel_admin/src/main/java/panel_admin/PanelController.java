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
        PantallaPrincipal.mostrarVentana_altaProductos(actionEvent, "Alta_Producto_Redimensionado_Cesar_Javi_Gerard.fxml");
    }
    
}
