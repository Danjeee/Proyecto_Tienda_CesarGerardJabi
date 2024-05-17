package login;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class EditarEmpleadoController {

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

            st.execute
        } catch (Exception e) {
            // TODO: handle exception
        }

    }
}
