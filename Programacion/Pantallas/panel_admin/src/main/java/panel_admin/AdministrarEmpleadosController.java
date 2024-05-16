package panel_admin;


import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import panel_admin.MenuHamburguesa.MenuHamb;
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
import panel_admin.Clases.Departamento;
import panel_admin.Clases.Empleado;

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

    static Alert alerta = new Alert(Alert.AlertType.NONE);


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
    public void retroceder_PanelAdmin(ActionEvent actionEvent) throws IOException {
        App.setRoot("PanelAdministracion_Cesar_Javi_Gerard");
    }


    private HBox createItem(String dni, String nom, String apell, String telefono, String f_nacim, String direcc,
    String email, boolean privilegios, boolean act, String pass, Departamento dpto, Empleado e) {
        
        HBox hb = new HBox();
        hb.setPrefHeight(75);
        hb.setPrefWidth(1273);
        hb.setMaxHeight(75);
        hb.setSpacing(10);
        hb.setAlignment(Pos.CENTER);

        Label infoEmpleado = new Label("  " + nom + " " + apell);
        infoEmpleado.setStyle("-fx-background-color: #e1e1e1; -fx-font-size: 22px; -fx-font-weight: bold");
        infoEmpleado.setPrefHeight(55);
        infoEmpleado.setPrefWidth(1143);
        FontAwesomeIconView usuario = new FontAwesomeIconView();
        usuario.setGlyphName("USER");
        usuario.setSize("35");
        infoEmpleado.setGraphic(usuario);
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
        editarEmpleado.setOnAction(i -> editarEmpleado(e));
        hb.getChildren().add(editarEmpleado);

        Button borrarEmpleado = new Button("");
        borrarEmpleado.setStyle("-fx-background-color: black");
        borrarEmpleado.setPrefSize(55, 55);
        FontAwesomeIconView papelera = new FontAwesomeIconView();
        papelera.setGlyphName("TRASH");
        papelera.setFill(Color.WHITE);
        papelera.setSize("25");
        borrarEmpleado.setGraphic(papelera);
        borrarEmpleado.setOnAction(i -> desactivarEmpleado(e));
        hb.getChildren().add(borrarEmpleado);

        return hb;

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

    public Departamento obtenerDepartamentoPorCodigo(int codigoDepartamento){
    Connection con = conectar();
    Departamento departamento = null;
    try {
        PreparedStatement ps = con.prepareStatement("select * from departamento where codigo = ?");
        ps.setInt(1, codigoDepartamento);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            int codigo = rs.getInt("codigo");
            String nombre = rs.getString("nombre");
            departamento = new Departamento(codigo, nombre);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return departamento;
    }

    @FXML
    private void desactivarEmpleado(Empleado e) {
        Connection con = conectar();
        try {
            Statement st = con.createStatement();
            st.executeUpdate("UPDATE empleado set activo='0' where nombre='" +e.getNombre()+ "'");
            
            alerta.setAlertType(Alert.AlertType.INFORMATION);
            alerta.setHeaderText(null);
            alerta.setContentText("Se ha desactivado el empleado correctamente.");
            alerta.show();
            initialize();

        } catch (SQLException sql) {
            sql.printStackTrace();
            alerta.setAlertType(Alert.AlertType.ERROR);
            alerta.setHeaderText(null);
            alerta.setContentText("Error al desactivar el empleado.");
            alerta.show();
        }
    }

    @FXML
    private void editarEmpleado(Empleado e) {
        try {
            App.setRoot("pantalla3");

        } catch (Exception ex) {
            ex.printStackTrace();
            alerta.setAlertType(Alert.AlertType.ERROR);
            alerta.setHeaderText(null);
            alerta.setContentText("Error al desactivar el empleado.");
            alerta.show();
        }
    }

    public void initialize() {
        MenuHamb.popupHambMake();
        cont.getChildren().add(MenuHamb.menuShadow);
        cont.getChildren().add(MenuHamb.popupHamb);
        cont.getChildren().add(MenuHamb.menuHamb());

        lista_empleados = cargarEmpleados();

        for (Empleado i : lista_empleados) {
            String dni = i.getDni();
            String nom = i.getNombre();
            String apell = i.getApellidos();
            String telefono = i.getTelefono();
            String f_nacim = i.getFechaNacimiento();
            String direcc = i.getDireccion();
            String email = i.getEmail();
            boolean act = i.isActivo();
            boolean privilegios = i.isTienePrivilegios();
            String pass = i.getPass();
            Departamento dpto = i.getDepartamento();
            
            fpane.getChildren().add(createItem(dni, nom, apell, telefono, f_nacim, direcc, email, privilegios, act, pass, dpto, i));
        }
    }
}