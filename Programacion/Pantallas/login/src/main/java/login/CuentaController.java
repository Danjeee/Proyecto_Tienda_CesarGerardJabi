package login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class CuentaController {
    
    @FXML
    public void cargarVentana_EditarCliente(ActionEvent actionEvent) throws IOException {
        App.setRoot("pantalla3");
    }

    @FXML
    private TextField nombreCliente;

    @FXML
    private TextField apellidosCliente;

    @FXML
    private TextField DNICliente;

    @FXML
    private TextField direccionCliente;

    @FXML
    private DatePicker nacCliente;

    @FXML
    private TextField emailCliente;

    @FXML
    private CheckBox tarjetaFide;

    @FXML
    private TextField saldoCliente;

    @FXML
    private TextField numPedidos;

    @FXML
    private TextField numerotelef;

    public static String current = "23456789A";

    public void initialize() {
        
        nacCliente.setStyle("-fx-font-size: 14");

        DNICliente.setEditable(false);
        nombreCliente.setEditable(false);
        apellidosCliente.setEditable(false);
        emailCliente.setEditable(false);
        nacCliente.setEditable(false);
        direccionCliente.setEditable(false);
        numPedidos.setEditable(false);
        saldoCliente.setEditable(false);
        tarjetaFide.setDisable(true);

        Connection con = conenct();

        try {

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM cliente WHERE DNI = '" + current + "'");
            

            while (rs.next()) {
                nombreCliente.setText(rs.getString("nombre"));
                apellidosCliente.setText(rs.getString("apellidos"));
                DNICliente.setText(rs.getString("DNI"));
                numerotelef.setText(rs.getString("telefono"));
                emailCliente.setText(rs.getString("email"));
                nacCliente.setValue(LocalDate.parse(rs.getString("f_nacimiento")));
                direccionCliente.setText(rs.getString("direccion"));
                tarjetaFide.selectedProperty().set(rs.getBoolean("tarjeta_fidelizacion"));
                saldoCliente.setText(rs.getString("saldo_cuenta"));
                numPedidos.setText(rs.getString("num_pedidos"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
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
