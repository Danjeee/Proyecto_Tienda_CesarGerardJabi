package login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import login.Clases.Alertas;
import login.Clases.MenuHamb;
import login.Clases.User;

public class LoginController {

    @FXML
    private AnchorPane cont;

    @FXML
    private TextField usuario;

    @FXML
    private PasswordField contra;

    @FXML
    private App PantallaPrincipal = new App();

    @FXML
    public void initialize() {
        MenuHamb.popupHambMake();
        cont.getChildren().add(MenuHamb.menuShadow);
        cont.getChildren().add(MenuHamb.popupHamb);
        cont.getChildren().add(MenuHamb.menuHamb());
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
            App.setRoot("OlvidarContraseña");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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
    public void flecha_volver(ActionEvent actionEvent) throws IOException {
        App.setRoot("Login");
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
            ResultSet rs = pst.executeQuery("Select email, pass, DNI from cliente");
            Statement st2 = connection.createStatement();
            ResultSet rs2 = st2.executeQuery("Select email, pass, DNI from empleado");
            ArrayList<User> usuarios = new ArrayList<>();
            while (rs.next()) {
                String mail = rs.getString("email");
                String pass = rs.getString("pass");
                String DNI = rs.getString("DNI");
                usuarios.add(new User(mail, pass, DNI));
            }
            ;

            while (rs2.next()) {
                String mail = rs2.getString("email");
                String pass = rs2.getString("pass");
                String DNI = rs2.getString("DNI");
                usuarios.add(new User(mail, pass, DNI));
            }
            ;

            User i = buscarUser(usuarios);
            if (i != null) {

                App.user = i.getDNI();
                try {
                    App.setRoot("seleccion");
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else {
                Alertas.rellenarCampos();
            }

        } catch (SQLException e) {
            System.err.println("Error al conectar la base de datos: " + e.getMessage());
        }
    }

    private User buscarUser(ArrayList<User> usuarios) {
        User u = null;

        for (User i : usuarios) {
            if (i.getMail().equals(usuario.getText()) && i.getPasw().equals(contra.getText())) {
                u = i;
            }
        }
        return u;

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