package productview;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class SecondaryController {
    @FXML
    public Button b;
    @FXML
    private void press() throws IOException{
        Stage stage = (Stage) this.b.getScene().getWindow();
        try {
            PrimaryController.current = 2;
            App.setRoot("productview");
            stage.setTitle("Tienda");
            stage.show();
          } catch (IOException e) {
            System.err.println(String.format("Error creando ventana: %s", e.getMessage()));
          }

    }
    
}
