package tienda_javi_gerard_cesar;


import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import tienda_javi_gerard_cesar.Clases.*;

public class AdministrarEmpleadosController {

    @FXML
    private App PantallaPrincipal = new App();
    @FXML
    private AnchorPane cont;
    @FXML
    private FlowPane fpane; 
    @FXML
    public Empleado empleado;
    public ArrayList<Empleado> lista_empleados;

    @FXML
    private VBox all;


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

    private HBox crearEmpleado(String nombre, String apellidos, Empleado empleado) {
        
        HBox hb = new HBox();
        hb.setPrefHeight(75);
        hb.setPrefWidth(1273);
        hb.setMaxHeight(75);
        hb.setSpacing(10);
        hb.setAlignment(Pos.CENTER);

        Label infoEmpleado = new Label("  " + nombre + " " + apellidos);
        infoEmpleado.setStyle("-fx-font-size: 25px; -fx-background-color: #e1e1e1; -fx-font-weight: bold");
        infoEmpleado.setPrefHeight(55);
        infoEmpleado.setPrefWidth(1143);
        FontAwesomeIconView user = new FontAwesomeIconView();
        user.setGlyphName("USER");
        user.setSize("35");
        infoEmpleado.setGraphic(user);
        infoEmpleado.setPadding(new Insets(0,0,0,13));
        hb.getChildren().add(infoEmpleado);

        Button editarEmpleado = new Button("");
        editarEmpleado.setStyle("-fx-background-color: black");
        editarEmpleado.setPrefSize(55, 55);
        FontAwesomeIconView editar = new FontAwesomeIconView();
        editar.setGlyphName("PENCIL");
        editar.setFill(Color.WHITE);
        editar.setSize("25");
        editarEmpleado.setGraphic(editar);
        editarEmpleado.setOnAction(i -> {
            EditarEmpleadoController.setCurrent(empleado.getDni());
            try {
                App.setRoot("pantalla3");
            } catch (Exception e) {
                Logs.createIOLog(e);
            }
        });
        hb.getChildren().add(editarEmpleado);

        Button borrarEmpleado = new Button("");
        borrarEmpleado.setStyle("-fx-background-color: black");
        borrarEmpleado.setPrefSize(55, 55);
        FontAwesomeIconView papelera = new FontAwesomeIconView();
        papelera.setGlyphName("TRASH");
        papelera.setFill(Color.WHITE);
        papelera.setSize("25");
        borrarEmpleado.setGraphic(papelera);
        borrarEmpleado.setOnAction(i -> desactivarEmpleado(empleado));
        hb.getChildren().add(borrarEmpleado);

        return hb;
    }

    private ArrayList<Empleado> cargarEmpleados() {
        ArrayList<Empleado> arrayList_Empleado = new ArrayList<>();
        
        Connection con = conectar();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from empleado");

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

                if (act == true){
                    Empleado empleado = new Empleado(dni, nom, apell, telefono, f_nacim, direcc, email, privilegios, act, pass, departamento);
                    arrayList_Empleado.add(empleado);             
                } 
            }
            return arrayList_Empleado;

        } catch (SQLException e) {
            Logs.createSQLLog(e);
        }
        return arrayList_Empleado;
    }


    private Departamento obtenerDepartamentoPorCodigo(int codigoDepartamento){
    Departamento departamento = null;

    Connection con = conectar();
    try {
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from metodo_pago where codigo = " +codigoDepartamento);

        if (rs.next()) {
            int codigo = rs.getInt("codigo");
            String nombre = rs.getString("descripcion");
            departamento = new Departamento(codigo, nombre);
        }

    } catch (SQLException e) {
        Logs.createSQLLog(e);
    }
    return departamento;
    }


    @FXML
    private void desactivarEmpleado(Empleado empleado) {
        Connection con = conectar();
        try {
            Statement st = con.createStatement();
            st.executeUpdate("UPDATE empleado set activo='0' where dni='" +empleado.getDni()+ "'");
            ArrayList<String> disableduser= new ArrayList<>();
            disableduser.add(empleado.getDni());
            disableduser.add(empleado.getNombre()+" "+empleado.getApellidos());
            Logs.createAdminLog('d', 'e', null, disableduser);

            Alertas.informacion("El empleado se ha desactivado correctamente.");

        } catch (SQLException sql) {
            Logs.createSQLLog(sql);
            Alertas.error("Error al desactivar el empleado");
        }
        fpane.getChildren().clear();
        all.getChildren().remove(0);
        initialize();
    }


    public void initialize() {
        MenuHamb.init(cont);
        all.getChildren().add(0, ImportantGUI.generateHeader());

        lista_empleados = cargarEmpleados();

        for (Empleado empleado : lista_empleados) {
            String nombre = empleado.getNombre();
            String apellidos = empleado.getApellidos();
            
            fpane.getChildren().add(crearEmpleado(nombre, apellidos, empleado));
        }
    }
}