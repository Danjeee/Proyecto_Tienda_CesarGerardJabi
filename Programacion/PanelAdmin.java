
import javafx.event.ActionEvent;
import javafx.fxml.FXML;


public class PanelAdmin {
    @FXML
    private Ventanas ProgramaPrincipal = new Ventanas();

    @FXML
    public void cargarVentana_AltaProducto(ActionEvent actionEvent) {
        ProgramaPrincipal.mostrarVentanaSecundaria(actionEvent, "AltaProductos.fxml");
    }
    
}
