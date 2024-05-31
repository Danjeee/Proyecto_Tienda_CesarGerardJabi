package tienda_javi_gerard_cesar;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import tienda_javi_gerard_cesar.Clases.Cifrado;
import tienda_javi_gerard_cesar.Clases.ImportantGUI;
import tienda_javi_gerard_cesar.Clases.Logs;
import tienda_javi_gerard_cesar.Clases.Mail;
import tienda_javi_gerard_cesar.Clases.MenuHamb;
import tienda_javi_gerard_cesar.Clases.User;

public class Login {

    @FXML
    private AnchorPane cont;

    @FXML
    private VBox all;

    @FXML
    private TextField usuario;

    @FXML
    private PasswordField contra;

    @FXML
    public void initialize() {
        all.getChildren().add(ImportantGUI.generateFooter());
    }

    @FXML
    public void limpiarBotonUser(ActionEvent Event) {
        usuario.setText(null);
    }

    @FXML
    private void limpiarBotonPwd(ActionEvent event) {
        contra.setText(null);
    }

    @FXML
    public void cargarVentana_olvidar(ActionEvent actionEvent) {
        try {
            App.setRoot("OlvidarContraseñaa");
        } catch (Exception e) {
            Logs.createIOLog(e);
        }
    }

    @FXML
    public void cargarVentana_seleccion(ActionEvent actionEvent) {
        try {
            App.setRoot("seleccion");
        } catch (Exception e) {
            Logs.createIOLog(e);
        }
    }

    @FXML
    public void cargarVentana_registro(ActionEvent actionEvent) {
        try {
            App.setRoot("pantalla2");
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
    private PasswordField pwdFieldContra;

    @FXML
    private TextField textFieldCorreo;

    @FXML
    private Button btnLogin;

    @FXML
    private void comprobarLog() {
        Connection connection = conenct();

        try {

            Statement pst = connection.createStatement();
            ResultSet rs = pst.executeQuery("Select email, pass, DNI, activo from cliente");
            Statement st2 = connection.createStatement();
            ResultSet rs2 = st2.executeQuery("Select email, pass, DNI, activo from empleado");
            ArrayList<User> usuarios = new ArrayList<>();
            while (rs.next()) {
                String mail = rs.getString("email");
                String pass = rs.getString("pass");
                String DNI = rs.getString("DNI");
                boolean act = rs.getBoolean("activo");
                usuarios.add(new User(mail, pass, DNI, act));
            }
            ;

            while (rs2.next()) {
                String mail = rs2.getString("email");
                String pass = rs2.getString("pass");
                String DNI = rs2.getString("DNI");
                boolean act = rs2.getBoolean("activo");
                usuarios.add(new User(mail, pass, DNI, act));
            }

            for (User i : usuarios) {
               
                if (i.getMail().equals(usuario.getText())) {
                    if (i.getPasw().equals(Cifrado.shiftCifrado(contra.getText())) && i.isAct()) {
                        App.setUser(i.getDNI());
                        try {
                            App.setRoot("seleccion");
                        } catch (Exception e) {
                            Logs.createIOLog(e);
                        }
                        break;
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setHeaderText("INICIO DE SESIÓN INCORRECTO.");
                        alert.setTitle("ERROR");
                        if (i.isAct()) {
                            alert.setContentText("RECUERDE RELLENAR CORRECTAMENTE LOS CAMPOS.");
                        } else {
                            alert.setContentText("Usuario inactivo");
                        }
                        
                        alert.showAndWait();
                        break;
                    }
                }
            }
        } catch (SQLException e) {
            Logs.createSQLLog(e);
        }
    }

    @FXML
    private void loginAsGuest() {
        try {
            App.setRoot("seleccion");
        } catch (Exception e) {
            Logs.createIOLog(e);
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