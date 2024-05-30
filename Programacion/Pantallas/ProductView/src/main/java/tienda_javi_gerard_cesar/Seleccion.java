package tienda_javi_gerard_cesar;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import tienda_javi_gerard_cesar.Clases.ImportantGUI;
import tienda_javi_gerard_cesar.Clases.Logs;
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
        } catch (Exception e) {
            Logs.createIOLog(e);
        }
    }
    @FXML
    private void accButton(){
        String[] multifiltro = { "Zapatos", "Bolso" };
        try {
            MenuHamb.multiFiltrar(multifiltro);
            App.setRoot("main");
        } catch (Exception e) {
            Logs.createIOLog(e);
        }
    }
    @FXML
    public void initialize(){
        MenuHamb.init(cont);
        all.getChildren().add(0, ImportantGUI.generateHeader());
        all.getChildren().add(ImportantGUI.generateFooter());
    }

    @FXML
    public void cargarVentana_seleccion(ActionEvent actionEvent){
        try {
            App.setRoot("Login");
        } catch (IOException e) {
            Logs.createIOLog(e);
        }
    }
}