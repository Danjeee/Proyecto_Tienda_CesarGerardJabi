package panel_admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import panel_admin.Clases.*;
import panel_admin.MenuHamburguesa.MenuHamb;

public class PanelController {

    @FXML
    private AnchorPane cont;

    @FXML
    private Button Alta_productos;

    @FXML
    private Button Administrar_empleados;

    @FXML
    private Button Administrar_usuarios;

    @FXML
    private Button Administrar_productos;

    @FXML
    private VBox all;

    @FXML
    private App PantallaPrincipal = new App();

    @FXML
    public void cargarVentana_AltaProductos(ActionEvent actionEvent) {
        try {
            App.setRoot("AltaProductos_Cesar_Javi_Gerard");
        } catch (Exception e) {
        }
    }

    @FXML
    public void cargarVentana_AdministrarEmpleados(ActionEvent actionEvent) {
        try {
            App.setRoot("AdministrarEmpleados_Cesar_Javi_Gerard");
        } catch (Exception e) {
        }
    }

    @FXML
    public void cargarVentana_AltaEmpleados(ActionEvent actionEvent) {
        try {
            App.setRoot("AltaEmpleados_Cesar_Javi_Gerard");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void cargarVentana_AdministrarProductos(ActionEvent actionEvent) {
        try {
            App.setRoot("AdministrarProductos_Cesar_Javi_Gerard");
        } catch (Exception e) {
        }
    }

    @FXML
    public void cargarVentana_Logs(ActionEvent actionEvent) {
        try {
            App.setRoot("logsView");
        } catch (Exception e) {
        }
    }

    @FXML
    public void cargarVentana_AdministrarClientes(ActionEvent actionEvent) {
        try {
            App.setRoot("AdministrarClientes_Cesar_Javi_Gerard");
        } catch (Exception e) {
        }
    }

    static Alert alerta = new Alert(Alert.AlertType.NONE);

    public void privilegiosAdmin(int dpto) {

        if (dpto == 1) {
            Alta_productos.setVisible(true);
            Administrar_productos.setVisible(true);
            Administrar_empleados.setVisible(true);
            Administrar_usuarios.setVisible(true);

        } else if (dpto == 2) {
            Alta_productos.setVisible(true);
            Administrar_productos.setVisible(true);
            Administrar_empleados.setVisible(false);
            Administrar_usuarios.setVisible(false);

        } else {
            cont.setVisible(false);
            ;
            Alertas.error("No tienes permisos.");

        }
    }

    private Connection connect() {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:4000/tienda_ropa", "root", "");
        } catch (SQLException e) {
        }
        return con;
    }

    public void initialize() {
         MenuHamb.popupHambMake();
        cont.getChildren().add(MenuHamb.menuShadow);
        cont.getChildren().add(MenuHamb.popupHamb);
        cont.getChildren().add(MenuHamb.menuHamb());
        all.getChildren().add(0,ImportantGUI.generateHeader());
        all.getChildren().add(ImportantGUI.generateFooter());

        try {

            Connection connection1 = connect();
            Statement st = connection1.createStatement();

            ResultSet r = st.executeQuery(
                    "select E.dpto from empleado E inner join departamento D ON D.codigo = E.dpto where E.nombre='Ana'");

            int dpto = 0;
            if (r.next()) {
                dpto = r.getInt("dpto");
            }

            privilegiosAdmin(dpto);
        } catch (SQLException e) {

        }
    }
}
