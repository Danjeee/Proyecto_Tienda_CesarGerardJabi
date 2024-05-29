package tienda_javi_gerard_cesar;
import java.io.IOError;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import tienda_javi_gerard_cesar.Clases.Logs;

public class Olivadar {

    @FXML
    private App PantallaPrincipal = new App();

    @FXML
    public void cargarVentana_olvidar(ActionEvent actionEvent){
        try {
        App.setRoot("Login");
    } catch (Exception e){
        Logs.createIOLog(e);
    }
}

    @FXML
    public void flecha_volver(ActionEvent actionEvent){
        try {
        App.setRoot("Login");
    } catch (Exception e){
        Logs.createIOLog(e);
    }
}
}