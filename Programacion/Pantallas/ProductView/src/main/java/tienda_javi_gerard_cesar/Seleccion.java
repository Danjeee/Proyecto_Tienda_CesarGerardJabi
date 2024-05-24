package tienda_javi_gerard_cesar;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import tienda_javi_gerard_cesar.Clases.ImportantGUI;
import tienda_javi_gerard_cesar.Clases.MenuHamb;

public class Seleccion {

    @FXML
    private VBox all;
    @FXML
    private AnchorPane cont;

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
    public void initialize(){
        MenuHamb.init(cont);
        all.getChildren().add(0, ImportantGUI.generateHeader());
        all.getChildren().add(ImportantGUI.generateFooter());
    }

    @FXML
    public void cargarVentana_seleccion(ActionEvent actionEvent) throws IOException {
        App.setRoot("Login");
    }
}