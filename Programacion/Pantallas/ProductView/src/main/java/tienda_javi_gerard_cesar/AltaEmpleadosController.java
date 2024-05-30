package tienda_javi_gerard_cesar;

import java.sql.Statement;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import tienda_javi_gerard_cesar.Clases.*;

public class AltaEmpleadosController {

    @FXML
    private App PantallaPrincipal = new App();

    @FXML
    private AnchorPane cont;

    @FXML
    private VBox all;

    @FXML
    private TextField nombre;

    @FXML
    private TextField apellidos;

    @FXML
    private TextField email;

    @FXML
    private DatePicker fecha_nacimiento;

    @FXML
    private PasswordField contraseña;

    @FXML
    private TextField dni;

    @FXML
    private TextField telefono;

    @FXML
    private TextField direccion;

    @FXML
    private CheckBox privilegios;

    @FXML
    private CheckBox activo;

    @FXML
    private ChoiceBox<String> departamento;
    private String[] opciones_departamento = {"Administración", "Ventas", "Almacén"};


    @FXML
    public void retroceder_PanelAdmin(ActionEvent actionEvent) throws IOException {
        App.setRoot("PanelAdministracion_Cesar_Javi_Gerard");
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

    @FXML
    private void botonRegistrar(){

        int dpto_num = 0;
            switch (departamento.getValue()) {
                case "Administración":
                    dpto_num = 1;
                    break;
                case "Ventas":
                    dpto_num = 2;
                    break;
                case "Almacén":
                    dpto_num = 3;
                    break;
            }

        Connection connection = conectar();
        try {
            Statement st = connection.createStatement();
            st.executeUpdate("INSERT INTO empleado VALUES('"+dni.getText()+"','"+nombre.getText()+"','"+apellidos.getText()+"','"+telefono.getText()+"','"+fecha_nacimiento.getValue()
            +"','"+direccion.getText()+"','"+email.getText()+"'," +activo.isSelected()+ "," +privilegios.isSelected()+ ",'" +contraseña.getText()+ "'," +dpto_num+ ")");
            
            Alertas.informacion("El empleado se ha creado correctamente.");
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
            Alertas.error("Error al crear el empleado.");
        }
    }

    public void initialize() {
        MenuHamb.init(cont);
        all.getChildren().add(0,ImportantGUI.generateHeader());
        all.getChildren().add(ImportantGUI.generateFooter());

        departamento.getItems().addAll(opciones_departamento);

    }

}
