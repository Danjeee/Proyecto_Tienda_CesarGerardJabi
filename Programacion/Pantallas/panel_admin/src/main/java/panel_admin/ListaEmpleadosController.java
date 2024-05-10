package panel_admin;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import panel_admin.Clases.Departamento;
import panel_admin.Clases.Empleado;
import panel_admin.MenuHamburguesa.MenuHamb;

public class ListaEmpleadosController {
    
    @FXML
    private FlowPane fpane;
    @FXML
    private AnchorPane cont;    
    @FXML
    public Empleado empleado;
    
    public ArrayList<Empleado> lista_empleados;


     private Connection conenct() {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:4000/tienda_ropa", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }


    private HBox createItem(String dni, String nom, String apell, String telefono, String f_nacim, String direcc,
    String email, boolean privilegios, boolean act, String pass, Departamento dpto, Empleado e) {
        HBox a = new HBox();
        a.setPrefHeight(75);
        a.setPrefWidth(725);
        a.setMaxHeight(75);
        a.setMaxWidth(725);
        a.setStyle("-fx-background-color: #000");
        a.setAlignment(Pos.CENTER);


        Label nombre = new Label(nom);
        nombre.setFont(new Font("System", 25));
        nombre.prefHeight(175);
        nombre.setTextFill(Color.WHITE);
        nombre.setStyle("-fx-background-color: #000");
        nombre.setPrefWidth(225);
        nombre.setAlignment(Pos.CENTER_LEFT);
        nombre.setPadding(new Insets(0, 0, 0, 20));
        a.getChildren().add(nombre);

        Pane sep = new Pane();
        sep.setPrefWidth(100);
        sep.setStyle("-fx-background-color: #ecf1f3");
        a.getChildren().add(sep);

        VBox butCont = new VBox();
        butCont.setPrefHeight(75);
        butCont.setPrefWidth(35);
        butCont.setStyle("-fx-background-color: #000");

        Pane sep2 = new Pane();
        sep2.setPrefHeight(25);
        sep2.setStyle("-fx-background-color: rgba(0,0,0,0)");
        butCont.getChildren().add(sep2);

        Button trash = new Button("");
        trash.setPrefHeight(70);
        trash.setPrefWidth(125);
        FontAwesomeIconView ico = new FontAwesomeIconView();
        trash.setStyle("-fx-background-color: #000");
        ico.setGlyphName("TRASH");
        ico.setFill(Color.WHITE);
        ico.setSize("50");
        trash.setGraphic(ico);
        trash.setOnAction(i -> delete(e));
        a.getChildren().add(trash);
        return a;

    }

    private ArrayList<Empleado> cargarEmpleados() {
        ArrayList<Empleado> a = new ArrayList<>();
        Connection con = conenct();
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
    Connection con = conenct();
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


    public ArrayList<Departamento> departament(){

        ArrayList<Departamento> lista_dpto = new ArrayList<>();
        Connection con = conenct();

        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from departamento");

            while (rs.next()) {
                int codig = rs.getInt("codigo");
                String nom = rs.getString("nombre");
            
                Departamento dpto = new Departamento(codig, nom);
                lista_dpto.add(dpto);
            }

            return lista_dpto;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista_dpto;
    }

    @FXML
    private void delete(Empleado e) {
        Connection con = conenct();
        try {
            Statement st = con.createStatement();
            st.executeUpdate(
                    "DELETE FROM empleados where nombre='" +e.getNombre()+ "'");

        } catch (SQLException sql) {
            sql.printStackTrace();
        }
        initialize();
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