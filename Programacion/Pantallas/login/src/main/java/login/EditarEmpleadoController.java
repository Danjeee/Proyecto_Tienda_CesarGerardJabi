package login;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import login.Clases.Alertas;
import javafx.scene.control.Alert.AlertType;

public class EditarEmpleadoController {

    public static String current;

    @FXML
    private TextField nombreEmpleado;

    @FXML
    private TextField apellidosEmpleado;

    @FXML
    private DatePicker fechaNac;

    @FXML
    private TextField numtelef;

    @FXML
    private void editarEmpleado(){

        Connection connection = conenct();
        Alert alerta = new Alert(Alert.AlertType.NONE);
        
        try {
       
            Statement st = connection.createStatement();
            

            connection.close();
        } catch (SQLException e) {
            
            e.printStackTrace();
            Alertas.errorRegistrar();
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
