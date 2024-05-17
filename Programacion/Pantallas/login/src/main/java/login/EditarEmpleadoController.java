package login;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
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

        Alert alerta = new Alert(Alert.AlertType.NONE);

        Connection con = conectar();
        
        try {
            java.sql.Statement st = .createStatement();

            st.executeQuery(null)
        } catch (Exception e) {
            // TODO: handle exception
        }

    }

     private ArrayList<Empleado> cargarEmpleados() {
        ArrayList<Empleado> a = new ArrayList<>();
        Connection con = conectar();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(
                    "select * from empleado");
            while (rs.next()) {
                String dni = rs.getString("dni");
                String nom = rs.getString("nombre");
                String apell = rs.getString("apellidos");
                String telefono = rs.getString("telefono");
                String f_nacim = rs.getString("f_nacimiento");
                String direcc = rs.getString("direccion");
                String email = rs.getString("email");
                boolean act = rs.getBoolean("activo");
                boolean privilegios = rs.getBoolean("tiene_privilegios");
                String pass = rs.getString("pass");

                Departamento departamento = obtenerDepartamentoPorCodigo(rs.getInt("dpto"));

                Empleado empleado = new Empleado(dni, nom, apell, telefono, f_nacim, direcc, email, privilegios, act, pass, departamento);
                a.add(empleado);              
            }
            return a;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return a;
    }


    private Connection conectar() {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:4000/tienda_ropa", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
}
