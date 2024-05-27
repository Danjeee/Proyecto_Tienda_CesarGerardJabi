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
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import login.Clases.Alertas;
import login.Clases.Mail;
import login.Clases.User;

public class OlivadarController {

    @FXML
    private TextField emailArevisar;

    @FXML
    private App PantallaPrincipal = new App();

    @FXML
    public void cargarVentana_olvidar(ActionEvent actionEvent) throws IOException {
        App.setRoot("Login");
    }

    @FXML
    public void flecha_volver(ActionEvent actionEvent) throws IOException {
        App.setRoot("Login");
    }

    // Verificar email, si es correcto se envia, si no, lo que hacemos es sacar una
    // alerta
    @FXML
    public void enviar() {

        /*
         * User i = buscarMail(emailArevisar);
         * if (i != null) {
         * 
         * App.user = i.getDNI();
         * try {
         * App.setRoot("seleccion");
         * } catch (IOException e) {
         * e.printStackTrace();
         * }
         * 
         * } else {
         * Alert alert = new Alert(Alert.AlertType.ERROR);
         * alert.setHeaderText("INICIO DE SESIÓN INCORRECTO.");
         * alert.setTitle("ERROR");
         * alert.setContentText("RECUERDE RELLENAR CORRECTAMENTE LOS CAMPOS.");
         * alert.showAndWait();
         * 
         * }
         */
        /* */

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

            User i = buscarMail(usuarios);
            if (i != null) {
                App.user = i.getDNI();
                Alertas.bienMail();
                Mail.send(null, "Restablecer la contraseña",
                        "Se ha enviado el mail para poder restablecer la contraseña");
            } else {
                Alertas.malEmail();
            }
        } catch (SQLException e) {
            System.err.println("Error al conectar la base de datos: " + e.getMessage());
        }
    }

    /* */

    private User buscarMail(ArrayList<User> emails) {
        User u = null;

        for (User i : emails) {
            if (i.getMail().equals(emailArevisar.getText())) {
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