package login;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import login.Clases.MenuHamb;

public class LoginController {
    @FXML
    private AnchorPane cont;

    @FXML
    private App PantallaPrincipal = new App();
    @FXML
    public void initialize(){
        MenuHamb.popupHambMake();
        cont.getChildren().add(MenuHamb.menuShadow);
        cont.getChildren().add(MenuHamb.popupHamb);
        cont.getChildren().add(MenuHamb.menuHamb());
    }

    @FXML
    public void cargarVentana_olvidar(ActionEvent actionEvent) throws IOException {
        App.setRoot("OlvidarContraseña");
    }


    @FXML
    public void cargarVentana_seleccion(ActionEvent actionEvent) throws IOException {
        App.setRoot("seleccion");
    }

    @FXML
    public void cargarVentana_registro(ActionEvent actionEvent) throws IOException {
        App.setRoot("pantalla2");
    }

    @FXML
    public void flecha_volver(ActionEvent actionEvent) throws IOException{
        App.setRoot("Login");
    }
}