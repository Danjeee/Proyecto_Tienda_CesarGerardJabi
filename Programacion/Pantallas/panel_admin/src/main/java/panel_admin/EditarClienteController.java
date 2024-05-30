package panel_admin;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import panel_admin.Clases.Alertas;
import panel_admin.Clases.Clientes;
import panel_admin.Clases.ImportantGUI;
import panel_admin.Clases.MetodoPago;
import panel_admin.MenuHamburguesa.MenuHamb;

public class EditarClienteController {

    @FXML
    private App PantallaPrincipal = new App();

    @FXML
    private AnchorPane cont;

    @FXML
    private VBox all;

    @FXML
    private TextField textNombre;

    @FXML
    private TextField textApellido;

    @FXML
    private TextField textDNI;

    @FXML
    private TextField textTelefono;

    @FXML
    private TextField textEmail;

    @FXML
    private TextField textNumPedidos;

    @FXML
    private TextField textFechaNacim;

    @FXML
    private TextField textDireccion;

    @FXML
    private CheckBox textTarjetaFide;

    @FXML
    private TextField textDireccionEnvio;

    

    public static String getCurrent() {
        return current;
    }

    public static void setCurrent(String current) {
        EditarClienteController.current = current;
    }

    private static String current = "";

    @FXML
    public void retroceder_ListaClientes(ActionEvent actionEvent) throws IOException {
        App.setRoot("AdministrarClientes_Cesar_Javi_Gerard");
        initialize();
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
    private void guardarCambios(){

        Connection connection = conectar();
        try {
       
            Statement st = connection.createStatement();

            st.executeUpdate("UPDATE cliente set nombre='"+textNombre.getText()+"', apellidos='"+textApellido.getText()+"', telefono='"+textTelefono.getText()+"', f_nacimiento='"+textFechaNacim.getText()
            +"', direccion='"+textDireccion.getText()+"', email='"+textEmail.getText()+"', num_pedidos='" +textNumPedidos.getText()+ "', dir_envio='" +textDireccionEnvio.getText()+ "', tarjeta_fidelizacion=" +textTarjetaFide.isSelected()
            + " where DNI = '"+current+"'");

            Alertas.informacion("Se han guardado los cambios correctamente.");
            connection.close();
        } catch (SQLException e) {
            
            e.printStackTrace();
            Alertas.error("Error al guardar los cambios.");
        }
 
    }

    public void initialize() {
        MenuHamb.popupHambMake();
        cont.getChildren().add(MenuHamb.menuShadow);
        cont.getChildren().add(MenuHamb.popupHamb);
        cont.getChildren().add(MenuHamb.menuHamb());
        all.getChildren().add(0,ImportantGUI.generateHeader());
        all.getChildren().add(ImportantGUI.generateFooter());

        textDNI.setEditable(false);
        Connection con = conectar();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from cliente where DNI='" +current+ "'");

            while (rs.next()) {

                textNombre.setText(rs.getString("nombre"));
                textApellido.setText(rs.getString("apellidos"));
                textDNI.setText(rs.getString("dni"));
                textTelefono.setText(rs.getString("telefono"));
                textEmail.setText(rs.getString("email"));
                textNumPedidos.setText(rs.getString("num_pedidos"));
                textFechaNacim.setText(rs.getString("f_nacimiento"));
                textDireccion.setText(rs.getString("direccion"));
                textTarjetaFide.selectedProperty().set(rs.getBoolean("tarjeta_fidelizacion"));
                textDireccionEnvio.setText(rs.getString("dir_envio"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
