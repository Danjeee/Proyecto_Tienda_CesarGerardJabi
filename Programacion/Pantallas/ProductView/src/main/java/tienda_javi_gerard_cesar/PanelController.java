package tienda_javi_gerard_cesar;

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
import tienda_javi_gerard_cesar.Clases.*;

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
    public void cargarVentana_AltaProductos(ActionEvent actionEvent) throws IOException {
        App.setRoot("AltaProductos_Cesar_Javi_Gerard");
    }

    @FXML
    public void cargarVentana_AdministrarEmpleados(ActionEvent actionEvent) throws IOException {
        App.setRoot("AdministrarEmpleados_Cesar_Javi_Gerard");
    }

    @FXML
    public void cargarVentana_AdministrarUsuarios(ActionEvent actionEvent) throws IOException {
        App.setRoot("AdministrarUsuarios_Cesar_Javi_Gerard");
    }

    @FXML
    public void cargarVentana_AdministrarProductos(ActionEvent actionEvent) throws IOException {
        App.setRoot("AdministrarProductos_Cesar_Javi_Gerard");
    }
    @FXML
    public void cargarVentana_AdministrarClientes(ActionEvent actionEvent) throws IOException{
        App.setRoot("AdministrarClientes_Cesar_Javi_Gerard");
    }

    static Alert alerta = new Alert(Alert.AlertType.NONE);


    public void privilegiosAdmin(int dpto) throws IOException{

            if (dpto == 1){
                Alta_productos.setVisible(true);
                Administrar_productos.setVisible(true);
                Administrar_empleados.setVisible(true);
                Administrar_usuarios.setVisible(true);
                
            }else if (dpto == 2){
                Alta_productos.setVisible(true);
                Administrar_productos.setVisible(true);
                Administrar_empleados.setVisible(false);
                Administrar_usuarios.setVisible(false);
            
            }else{
                cont.setVisible(false);;
                alerta.setAlertType(Alert.AlertType.ERROR);
                alerta.setHeaderText(null);
                alerta.setContentText("No tienes permisos.");
                alerta.show();
            } 
    }
    private Connection connect() {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:4000/tienda_ropa", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    public void initialize() throws IOException {
        MenuHamb.init(cont);
        all.getChildren().add(0, ImportantGUI.generateHeader());

        try {

            Connection connection1 = connect();
            Statement st = connection1.createStatement();

            ResultSet r = st.executeQuery("select E.dpto from empleado E inner join departamento D ON D.codigo = E.dpto where E.nombre='Ana'");
            
            int dpto = 0;
            if (r.next()){
                dpto = r.getInt("dpto");
            }

            privilegiosAdmin(dpto);
        
        }catch(SQLException e){
            e.printStackTrace();

        }
    }
}

