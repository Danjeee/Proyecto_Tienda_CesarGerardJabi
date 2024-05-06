package panel_admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import panel_admin.ConectaBBDD;
import panel_admin.Clases.*;
import panel_admin.Utilidades;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import panel_admin.Clases.Articulo;
import panel_admin.MenuHamburguesa.MenuHamb;

public class AltaProductosController {

    @FXML
    private AnchorPane cont;

    @FXML
    private TextField nombre;
    @FXML
    private TextField precio;
    @FXML
    private TextField marca;
    @FXML
    private TextArea descripcion;
    @FXML
    private CheckBox activo;
    @FXML
    private TextField nombre_imagen;
    @FXML
    private ChoiceBox<Integer> material;
    private Integer[] opciones = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};



    @FXML
    private App PantallaPrincipal = new App();

    @FXML
    public void retroceder_PanelAdmin(ActionEvent actionEvent) throws IOException {
        App.setRoot("PanelAdministracion_Cesar_Javi_Gerard");
    }

    private Connection connect() {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:4000/tienda_ropa", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    @FXML
    private void guardarCambios() {

        Articulo art1;
        int graba;
        ConectaBBDD con2 = new ConectaBBDD();
        // creamos la alerta
        Alert a = new Alert(Alert.AlertType.NONE);
            try {
                Connection conection1 = con2.conecta();
                Statement st = conection1.createStatement();
                st.executeUpdate("INSERT INTO articulo(nombre, precio, marca, descripcion, activo, imagen, material) VALUES "
                + "('" + nombre.getText() + "'," + precio.getText() + ",'" + marca.getText() + "','" + descripcion.getText() + "'," + activo.isSelected() + ",'" + nombre_imagen.getText() + "'," + material.getValue() + ")");
                
            
                con2.cerrarConexion();

            } catch (Exception ex) {
                System.out.println(ex.getClass());
                a.setAlertType(Alert.AlertType.ERROR);
                a.setHeaderText(null);
                a.setContentText("ERROR: con la BBDD.");
                a.show();
            }
    }

    public void initialize() {
        MenuHamb.popupHambMake();
        cont.getChildren().add(MenuHamb.menuShadow);
        cont.getChildren().add(MenuHamb.popupHamb);
        cont.getChildren().add(MenuHamb.menuHamb());

        material.getItems().addAll(opciones);
    }
}