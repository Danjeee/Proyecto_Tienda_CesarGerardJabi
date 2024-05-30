package tienda_javi_gerard_cesar;

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
import tienda_javi_gerard_cesar.Clases.*;

public class EditarClienteController {

    @FXML
    private App PantallaPrincipal = new App();

    @FXML
    private AnchorPane cont;

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

    @FXML
    private VBox all;
    

    public static String getCurrent() {
        return current;
    }

    public static void setCurrent(String current) {
        EditarClienteController.current = current;
    }

    private static String current = "";
    private ArrayList<String> olddatos = new ArrayList<>();

    @FXML
    public void retroceder_ListaClientes(ActionEvent actionEvent){
        try {
            App.setRoot("AdministrarClientes_Cesar_Javi_Gerard");
        } catch (Exception e) {
            Logs.createIOLog(e);
        }
        initialize();
    }

    private Connection conectar() {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:4000/tienda_ropa", "root", "");
        } catch (SQLException e) {
            Logs.createSQLLog(e);
        }
        return con;
    }
    


    @FXML
    private void guardarCambios(){

        Connection connection = conectar();
        try {
       
            Statement st = connection.createStatement();

            st.executeUpdate("UPDATE cliente set DNI='"+textDNI.getText()+"', nombre='"+textNombre.getText()+"', apellidos='"+textApellido.getText()+"', telefono='"+textTelefono.getText()+"', f_nacimiento='"+textFechaNacim.getText()
            +"', direccion='"+textDireccion.getText()+"', email='"+textEmail.getText()+"', dir_envio='" +textDireccionEnvio.getText()+ "', tarjeta_fidelizacion=" +textTarjetaFide.isSelected()+ ", num_pedidos = "+textNumPedidos.getText()+" where DNI = '"+current+"'");
            String[] newdatos = {textDNI.getText(), textNombre.getText(), textApellido.getText(), textTelefono.getText(), textFechaNacim.getText(), textDireccion.getText(), textEmail.getText(), "******", olddatos.get(8), textNumPedidos.getText(), textDireccionEnvio.getText(),Boolean.toString(textTarjetaFide.isSelected()), "true"};
            String [] olddatos1 = new String[olddatos.size()];
            for (int i = 0; i<olddatos1.length; i++){
                olddatos1[i] = olddatos.get(i);
            }
            Logs.createAdminLog('m', 'c', Logs.userToLogs(olddatos1), Logs.userToLogs(newdatos));
            Alertas.editarCliente();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            Logs.createSQLLog(e);
            Alertas.errorRegistrar();
        }
 
    }

    public void initialize() {
        textDNI.setEditable(false);
        MenuHamb.init(cont);
        all.getChildren().add(0, ImportantGUI.generateHeader());
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

                olddatos.add(rs.getString("dni"));
                olddatos.add(rs.getString("nombre"));
                olddatos.add(rs.getString("apellidos"));
                olddatos.add(rs.getString("telefono"));
                olddatos.add(rs.getString("f_nacimiento"));
                olddatos.add(rs.getString("direccion"));
                olddatos.add(rs.getString("email"));
                olddatos.add("******");
                olddatos.add(rs.getString("saldo_cuenta"));
                olddatos.add(rs.getString("num_pedidos"));
                olddatos.add(rs.getString("dir_envio"));
                olddatos.add(Boolean.toString(rs.getBoolean("tarjeta_fidelizacion")));
                olddatos.add(Boolean.toString(rs.getBoolean("activo")));

            }

        } catch (SQLException e) {
            Logs.createSQLLog(e);
        }

    }
}
