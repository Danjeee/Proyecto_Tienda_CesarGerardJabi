package panel_admin;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
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
import panel_admin.Clases.Empleado;

public class ListaEmpleadosController {
    
    @FXML
    private FlowPane fpane;
    @FXML
    private AnchorPane cont;    
    @FXML
    public Empleado empleadoGlobal;
    
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

      private String setCant(Empleado i, String cant, int op) {
        int cantt = Integer.parseInt(cant);
        switch (op) {
            case 0:
                cantt += 1;
                i.setCant(cantt);
                return String.valueOf(cantt);
            case 1:
                if (cantt == 1) {
                    i.setCant(cantt);
                    return String.valueOf(cantt);
                } else {
                    cantt -= 1;
                    i.setCant(cantt);
                    return String.valueOf(cantt);
                }

            default:
                i.setCant(cantt);
                return String.valueOf(cantt);
        }
    }

    private HBox createItem(String img, String nombre, String precio, int cant, int cod, Empleado i) {
        HBox a = new HBox();
        a.setPrefHeight(75);
        a.setPrefWidth(725);
        a.setMaxHeight(75);
        a.setMaxWidth(725);
        a.setStyle("-fx-background-color: #000");
        a.setAlignment(Pos.CENTER);


        Label nom = new Label(nombre);
        nom.setFont(new Font("System", 25));
        nom.prefHeight(175);
        nom.setTextFill(Color.WHITE);
        nom.setStyle("-fx-background-color: #000");
        nom.setPrefWidth(225);
        nom.setAlignment(Pos.CENTER_LEFT);
        nom.setPadding(new Insets(0, 0, 0, 20));
        a.getChildren().add(nom);

        Label pr = new Label(precio + "€");
        pr.setFont(new Font("System", 30));
        pr.prefHeight(75);
        pr.setTextFill(Color.WHITE);
        pr.setStyle("-fx-background-color: #000");
        pr.setPrefWidth(150);
        pr.setAlignment(Pos.CENTER);
        a.getChildren().add(pr);

        Pane sep = new Pane();
        sep.setPrefWidth(100);
        sep.setStyle("-fx-background-color: #ecf1f3");
        a.getChildren().add(sep);

        Label cantt = new Label(String.valueOf(cant));
        cantt.setFont(new Font("System", 30));
        cantt.setTextFill(Color.WHITE);
        cantt.setPrefHeight(75);
        cantt.setPrefWidth(50);
        cantt.setAlignment(Pos.CENTER_RIGHT);
        cantt.setTextAlignment(TextAlignment.RIGHT);
        a.getChildren().add(cantt);

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
        trash.setOnAction(e -> delete(i));
        a.getChildren().add(trash);
        return a;

    }

    private ArrayList<Empleado> cargarItems() {
        ArrayList<Empleado> a = new ArrayList<>();
        Connection con = conenct();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(
                    "SELECT A.*, L.cantidad FROM articulo A, linea_pedido L, pedido P WHERE A.cod_art = L.cod_art and L.num_pedido = P.numero and P.DNI_cliente = "
                            + "\"" + App.user + "\" and P.estado = \"En proceso\"");
            while (rs.next()) {
                String nom = rs.getString("nombre");
                BigDecimal precio = rs.getBigDecimal("precio");
                int cod = rs.getInt("cod_art");
                int cant = rs.getInt("cantidad");
                String img = rs.getString("imagen");
                Empleado aa = new Empleado(cod, nom, precio, img);
                aa.setCant(cant);
                a.add(aa);
            }
            return a;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return a;
    }

    @FXML
    private void delete(Empleado i) {
        Connection con = conenct();
        try {
            Statement st = con.createStatement();
            st.executeUpdate(
                    "DELETE FROM linea_pedido WHERE num_pedido = (SELECT DISTINCT L.num_pedido from linea_pedido L, pedido P WHERE L.num_pedido = P.numero and P.DNI_cliente = \""
                    + App.user + "\" and P.estado = \"En proceso\") and cod_art = " + i.getCodigo());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        initialize();
    }

    public void initialize() {
        MenuHamb.popupHambMake();
        cont.getChildren().add(MenuHamb.menuShadow);
        cont.getChildren().add(MenuHamb.popupHamb);
        cont.getChildren().add(MenuHamb.menuHamb());
        main.getChildren().clear();
        all.getChildren().add(0, ImportantGUI.generateHeader());
        articulos = cargarItems();
        for (Empleado i : articulos) {
            String nom = i.getNombre();
            int cant = i.getCant();
            String precio = i.getPrecio().toString();
            String img = i.getImg();
            int cod = i.getCodigo();
            fpane.getChildren().add(createItem(img, nom, precio, cant, cod, i));
        }
    }

    
    public static void almacenarEmpleados() {
        empleadosArr = new ArrayList<>();

         ConexionSQL con = new ConexionSQL();

        Connection connection1 = con.conecta();
        Statement st = connection1.createStatement();

        ResultSet rs = st.executeQuery("SELECT * FROM empleado");
        String empleado;
            if (rs.next()){
                empleado = rs.getString("dpto");
            }

        for (String[] arr : rs) {
            empleadosArr.add(cargarEmpleados(arr));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cargarEmpleados();

        try {
            for (int i = 0; i < empleadosArr.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(App.class.getResource("empleadosHBOX.fxml"));
                HBox box = fxmlLoader.load();
                ListaEmpleadosController empleadosController = fxmlLoader.getController();
                empleadosController.setData(empleadosArr.get(i));
                empleadoGlobal = empleadosArr.get(i);
                tlPane.getChildren().add(box);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Empleado cargarEmpleados(String[] datos){
        String DNI = datos[0];
        String nombre = datos[1];
        String apellidos = datos[2];
        String telefono = datos[3];
        String f_nacimiento = datos[4];
        String direccion = datos[5];
        String email = datos[6];
        boolean activo = datos[7].equals("1") ? true : false;
        boolean privilegios = datos[8].equals("1") ? true : false;
        int dpto = Integer.parseInt(datos[10]);

        Empleado empleados = new Empleado(DNI,nombre,apellidos,telefono, f_nacimiento,direccion,email,activo,privilegios,dpto);

        return empleados;
    }
}
