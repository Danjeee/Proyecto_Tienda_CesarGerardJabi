package login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class RegistrarUsuarioController {


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
    private void registrar(){

        Connection connection = conenct();
        Alert alerta = new Alert(Alert.AlertType.NONE);

        try {
            
            java.sql.Statement pst = connection.createStatement();
            ResultSet rs = pst.executeQuery("INSERT INTO CLIENTE (DNI, nombre, apellidos, telefono, f_nacimiento, direccion, email, pass, tarjeta_fidelizacion, activo) VALUES "
            + "('" +DNI.getText()+ "','" +nombre.getText()+ "','" +apellidos.getText()+ "'," +telefono.getText()+ ",'" +fechanac.getValue()+ "','" +direccion.getText()+ "','" +email.getText()+ "'," +tarjetaFide.selectedProperty().get()+ ","+1+")");
            alerta.setAlertType(AlertType.INFORMATION);
            alerta.setHeaderText(null);
            alerta.setContentText("El usuario se ha registrado correctamente.");
            alerta.show();

            connection.close();
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e.getClass());
            alerta.setAlertType(Alert.AlertType.ERROR);
            alerta.setHeaderText(null);
            alerta.setContentText("Error al registrar el Usuario");
            alerta.show();
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