package login;

import java.sql.Statement;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import login.Clases.Alertas;

public class RegistrarUsuarioController {

    @FXML
    private App PantallaPrincipal = new App();

    @FXML
    public void cargarVentana_registro(ActionEvent actionEvent) throws IOException {
        App.setRoot("Login");
    }

    @FXML
    public void flecha_volver(ActionEvent actionEvent) throws IOException {
        App.setRoot("Login");
    }

    @FXML
    private TextField nombre;

    @FXML
    private TextField apellidos;

    @FXML
    private TextField email;

    @FXML
    private DatePicker fechanac;

    @FXML
    private PasswordField contraseña;

    @FXML
    private TextField DNI;

    @FXML
    private TextField telefono;

    @FXML
    private TextField direccion;

    @FXML
    private CheckBox tarjetaFide;

    @FXML
    private CheckBox activo;

    @FXML
    private void registrar(){

        Connection connection = conenct();
        try {
            Statement st = connection.createStatement();
            st.executeUpdate("INSERT INTO CLIENTE VALUES('"+DNI.getText()+"','"+nombre.getText()+"','"+apellidos.getText()+"','"+telefono.getText()+"','"+fechanac.getValue()
            +"','"+direccion.getText()+"','"+email.getText()+"','"+contraseña.getText()+"','0',' 0', 'Direccion',"+tarjetaFide.isSelected()+","+activo.isSelected()+",1)");
            Alertas.informacion("EL USUARIO SE HA REGISTRADO CORRECTAMENTE");

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
            Alertas.error("ERROR AL REGISTRAR EL USUARIO");
        }
    }

    private Connection conenct() {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/tienda_ropa", "root", "mysql23");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }    
}