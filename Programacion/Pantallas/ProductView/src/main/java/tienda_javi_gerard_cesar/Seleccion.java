package tienda_javi_gerard_cesar;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import tienda_javi_gerard_cesar.Clases.MenuHamb;

public class Seleccion {

    @FXML
    private void ropaButton(){
        String[] multifiltro = { "Camisa", "Pantalon", "Chaqueta" };
        try {
            MenuHamb.multiFiltrar(multifiltro);
            App.setRoot("main");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void accButton(){
        String[] multifiltro = { "Zapatos", "Bolso" };
        try {
            MenuHamb.multiFiltrar(multifiltro);
            App.setRoot("main");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void cargarVentana_seleccion(ActionEvent actionEvent) throws IOException {
        App.setRoot("Login");
    }
}