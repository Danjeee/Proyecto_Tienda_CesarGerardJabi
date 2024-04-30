package tienda_javi_gerard_cesar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import tienda_javi_gerard_cesar.Clases.MenuHamb;

public class Cart {
    @FXML
    private AnchorPane main;
    @FXML
    private AnchorPane cont;
    @FXML
    private TextField codDes;
    @FXML
    private Label subtotal;
    @FXML
    private Label envio;
    @FXML
    private Label total;
    @FXML
    private Label imp;

    private Connection conenct() {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:4000/tienda_ropa", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    /*private VBox createItem(String img, String nombre, String precio, int cant){
        VBox a = new VBox();
        a.setPrefHeight(75);
        a.setPrefWidth(cant);
    }*/

    public void initialize() {
        MenuHamb.popupHambMake();
        cont.getChildren().add(MenuHamb.menuShadow);
        cont.getChildren().add(MenuHamb.popupHamb);
        cont.getChildren().add(MenuHamb.menuHamb());
        main.getChildren().clear();
    }
}
