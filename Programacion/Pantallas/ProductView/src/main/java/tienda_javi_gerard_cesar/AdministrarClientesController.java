package tienda_javi_gerard_cesar;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import tienda_javi_gerard_cesar.Clases.*;

public class AdministrarClientesController {
    @FXML
    private App PantallaPrincipal = new App();
    @FXML
    private AnchorPane cont;
    @FXML
    private FlowPane fpane; 
    @FXML
    private VBox all;
    @FXML
    public Clientes clientes;
    public ArrayList<Clientes> lista_clientes;

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
    public void retroceder_PanelAdmin(ActionEvent actionEvent){
        try {
            App.setRoot("PanelAdministracion_Cesar_Javi_Gerard");
        } catch (Exception e) {
            Logs.createIOLog(e);
        }
    }

    private HBox crearCliente(String nombre, String apellidos, Clientes clientes) {

        HBox hb = new HBox();
        hb.setPrefHeight(75);
        hb.setPrefWidth(1273);
        hb.setMaxHeight(75);
        hb.setSpacing(10);
        hb.setAlignment(Pos.CENTER);

        Label infoCliente = new Label("  " + nombre + " " + apellidos);
        infoCliente.setStyle("-fx-font-size: 25px; -fx-background-color: #e1e1e1; -fx-font-weight: bold");
        infoCliente.setPrefHeight(55);
        infoCliente.setPrefWidth(1143);
        FontAwesomeIconView user = new FontAwesomeIconView();
        user.setGlyphName("USER_CIRCLE");
        user.setSize("35");
        infoCliente.setGraphic(user);
        infoCliente.setPadding(new Insets(0,0,0,13));
        hb.getChildren().add(infoCliente);

        Button editarCliente = new Button("");
        editarCliente.setStyle("-fx-background-color: black");
        editarCliente.setPrefSize(55, 55);
        FontAwesomeIconView editar = new FontAwesomeIconView();
        editar.setGlyphName("PENCIL");
        editar.setFill(Color.WHITE);
        editar.setSize("25");
        editarCliente.setGraphic(editar);
        editarCliente.setOnAction(i -> {
            EditarClienteController.setCurrent( clientes.getDni());
            try {
                App.setRoot("EditarCliente_Cesar_Javi_Gerard");
            } catch (Exception e) {
                Logs.createIOLog(e);
            }
        }); 
        hb.getChildren().add(editarCliente);

        Button borrarCliente = new Button("");
        borrarCliente.setStyle("-fx-background-color: black");
        borrarCliente.setPrefSize(55, 55);
        FontAwesomeIconView papelera = new FontAwesomeIconView();
        papelera.setGlyphName("TRASH");
        papelera.setFill(Color.WHITE);
        papelera.setSize("25");
        borrarCliente.setGraphic(papelera);
        borrarCliente.setOnAction(i -> desactivarCliente(clientes));
        hb.getChildren().add(borrarCliente);

        return hb;
    }

    private ArrayList<Clientes> cargarClientes() {
        ArrayList<Clientes> arrayList_Clientes = new ArrayList<>();
        
        Connection con = conectar();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from cliente");

            while (rs.next()) {
                String dni = rs.getString("dni");
                String nom = rs.getString("nombre");
                String apell = rs.getString("apellidos");
                String telefono = rs.getString("telefono");
                String f_nacim = rs.getString("f_nacimiento");
                String direcc = rs.getString("direccion");
                String email = rs.getString("email");
                String pass = rs.getString("pass");
                float saldo = rs.getFloat("saldo_cuenta");
                int num_ped = rs.getInt("num_pedidos");
                String direcc_ped = rs.getString("dir_envio");
                boolean tarj_fide = rs.getBoolean("tarjeta_fidelizacion");
                boolean act = rs.getBoolean("activo");
                MetodoPago mPago = obtenerMetodoPagoPorCodigo(rs.getInt("m_pago"));

                if (act == true){
                    Clientes clientes = new Clientes(dni, nom, apell, telefono, f_nacim, direcc, email, pass, saldo, num_ped, direcc_ped, tarj_fide, act, mPago);
                    arrayList_Clientes.add(clientes);             
                } 
            }
            return arrayList_Clientes;

        } catch (SQLException e) {
            Logs.createSQLLog(e);
        }
        return arrayList_Clientes;
    }

    private MetodoPago obtenerMetodoPagoPorCodigo(int codigoMetodoPago){
    MetodoPago m_pago = null;

    Connection con = conectar();
    try {
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from metodo_pago where codigo = " +codigoMetodoPago);

        if (rs.next()) {
            int codigo = rs.getInt("codigo");
            String descripcion = rs.getString("descripcion");
            m_pago = new MetodoPago(codigo, descripcion);
        }

    } catch (SQLException e) {
        Logs.createSQLLog(e);
    }
    return m_pago;
    }


    @FXML
    private void desactivarCliente(Clientes clientes) {
        Connection con = conectar();
        try {
            Statement st = con.createStatement();
            st.executeUpdate("UPDATE cliente set activo=0 where DNI='" +clientes.getDni()+ "'");
            ArrayList<String> disableduser = new ArrayList<>();
            disableduser.add(clientes.getDni());
            disableduser.add(clientes.getNombre() + " " +clientes.getApellidos());
            Logs.createAdminLog('d', 'c', null, disableduser);
    
            Alertas.informacion("El cliente se ha desactivado correctamente.");
            
        } catch (SQLException sql) {
            Logs.createSQLLog(sql);
            Alertas.error("Error al desactivar el cliente");
        }
        fpane.getChildren().clear();
        all.getChildren().remove(0);
        initialize();
    }
    
    public void initialize() {
        MenuHamb.init(cont);
        all.getChildren().add(0, ImportantGUI.generateHeader());

        lista_clientes = cargarClientes();

        for (Clientes clientes : lista_clientes) {
            String nombre = clientes.getNombre();
            String apellidos = clientes.getApellidos();
    
            fpane.getChildren().add(crearCliente(nombre, apellidos, clientes));
        }
    }
}

