package tienda_javi_gerard_cesar;

import java.sql.Statement;
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
import tienda_javi_gerard_cesar.Clases.Alertas;
import tienda_javi_gerard_cesar.Clases.Cifrado;
import tienda_javi_gerard_cesar.Clases.Logs;
import tienda_javi_gerard_cesar.Clases.Mail;

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
    private CheckBox activo;

    @FXML
    private App PantallaPrincipal = new App();

    @FXML
    public void cargarVentana_registro(ActionEvent actionEvent) {
        try {
            App.setRoot("Login");
        } catch (Exception e) {
            Logs.createIOLog(e);
        }
    }

    @FXML
    public void flecha_volver(ActionEvent actionEvent) {
        try {
            App.setRoot("Login");
        } catch (Exception e) {
            Logs.createIOLog(e);
        }
    }

    @FXML
    private void registrar() {

        Connection connection = conenct();
        Alert alerta = new Alert(Alert.AlertType.NONE);

        try {

            Statement st = connection.createStatement();
            st.executeUpdate("INSERT INTO CLIENTE VALUES('" + DNI.getText() + "','" + nombre.getText() + "','"
                    + apellidos.getText() + "','" + telefono.getText() + "','" + fechanac.getValue()
                    + "','" + direccion.getText() + "','" + email.getText() + "','" + Cifrado.shiftCifrado(contraseña.getText())
                    + "','0',' 0','" + direccion.getText() + "'," + tarjetaFide.isSelected() + "," + activo.isSelected()
                    + ",1)");
            alerta.setAlertType(AlertType.INFORMATION);
            alerta.setHeaderText(null);
            alerta.setContentText("El usuario se ha registrado correctamente.");
            Mail.send(email.getText(), "Te has registrado correctamente en secondHand, te damos la bienvenida y esperemos que lo pases bien", "¡Bienvenidx!");
            alerta.show();
            App.setUser(DNI.getText());
            String[] datos = {DNI.getText(), nombre.getText(), apellidos.getText(), telefono.getText(), fechanac.getValue().toString(), direccion.getText(), email.getText(), contraseña.getText(), "0", "0", String.valueOf(tarjetaFide.isSelected()), String.valueOf(activo.isSelected())};
            Logs.createAdminLog('a', 'c', null, Logs.userToLogs(datos));
            try {
                App.setRoot("seleccion");
            } catch (Exception e) {
                Logs.createIOLog(e);
            }

            connection.close();
        } catch (SQLException e) {

            e.printStackTrace();
            Logs.createSQLLog(e);
            Alertas.errorRegistrar();
        }

    }

    private Connection conenct() {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:4000/tienda_ropa", "root", "");
        } catch (SQLException e) {
            Logs.createSQLLog(e);
        }
        return con;
    }
}