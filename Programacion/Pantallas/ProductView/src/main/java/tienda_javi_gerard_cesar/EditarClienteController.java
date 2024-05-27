package tienda_javi_gerard_cesar;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import tienda_javi_gerard_cesar.Clases.*;

public class EditarClienteController {

    @FXML
    private App PantallaPrincipal = new App();

    @FXML
    public void retroceder_ListaClientes(ActionEvent actionEvent) throws IOException {
        App.setRoot("AdministrarClientes_Cesar_Javi_Gerard");
        initialize();
    }
    
    public void initialize() {
    }

}
