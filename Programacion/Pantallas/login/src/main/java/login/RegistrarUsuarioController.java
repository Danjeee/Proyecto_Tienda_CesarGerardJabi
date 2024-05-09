/*
package login;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.spi.DirStateFactory.Result;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
    private App PantallaPrincipal = new App();

    private void registrar(){

        Connection connection = conenct();
        Alert alerta = new Alert(Alert.AlertType.NONE);

        try {
            st.executeUpdate("INSERT INTO articulo(nombre, precio, marca, descripcion, activo, imagen, material) VALUES "
            + "('" + nombre.getText() + "'," + precio.getText() + ",'" + marca.getText() + "','" + descripcion.getText() + "'," + activo.isSelected() + ",'" + nombre_imagen.getText() + "'," + material.getValue() + ")");
            
            java.sql.Statement pst = connection.createStatement();
            ResultSet rs = pst.executeQuery("INSERT INTO CLIENTE (DNI, nombre, apellidos, telefono, f_nacimiento, direccion, email, pass, saldo_cuenta, num_pedidos, dir_envio, tarjeta_fidelizacion, activo, m_pago) VALUES "
            + "('" +DNI.getText()+ "','" +nombre.getText()+ "','" +apellidos.getText()+ "', '" +telefono.getText()+ "','" +fechanac.getdate+ "', '" Calle Mayor, 123', 'maria.gonzalez@example.com', '123456', 1000.00, 2, 'Calle Sol, 10', true, true,1),
            ")
            java.sql.Statement st = connection1.createStatement();

            st.executeQuery("null")
        } catch (Exception e) {
            // TODO: handle exception
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
*/