package tienda_javi_gerard_cesar;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
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

public class AdministrarProductosController {

    @FXML
    private AnchorPane cont;

    @FXML
    private VBox all;

    @FXML
    private App PantallaPrincipal = new App();

    @FXML
    private FlowPane fpane; 

    @FXML
    public Producto Producto;
    public ArrayList<Producto> lista_Productos;

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

    private HBox crearProducto(String nombre, String marca, Producto Producto) {

        HBox hb = new HBox();
        hb.setPrefHeight(75);
        hb.setPrefWidth(1273);
        hb.setMaxHeight(75);
        hb.setSpacing(10);
        hb.setAlignment(Pos.CENTER);

        Label infoProducto = new Label(" " +nombre+ " (" +marca+ ")");
        infoProducto.setStyle("-fx-font-size: 25px; -fx-background-color: #e1e1e1; -fx-font-weight: bold");
        infoProducto.setPrefHeight(55);
        infoProducto.setPrefWidth(1143);
        FontAwesomeIconView user = new FontAwesomeIconView();
        user.setGlyphName("TAG");
        user.setSize("35");
        infoProducto.setGraphic(user);
        infoProducto.setPadding(new Insets(0,0,0,13));
        hb.getChildren().add(infoProducto);

        Button editarProducto = new Button("");
        editarProducto.setStyle("-fx-background-color: black");
        editarProducto.setPrefSize(55, 55);
        FontAwesomeIconView editar = new FontAwesomeIconView();
        editar.setGlyphName("PENCIL");
        editar.setFill(Color.WHITE);
        editar.setSize("25");
        editarProducto.setGraphic(editar);
        editarProducto.setOnAction(i -> {
            EditarClienteController.setCurrent(Producto.getCod_art());
            try {
                App.setRoot("EditarProducto_Cesar_Javi_Gerard");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }); 
        hb.getChildren().add(editarProducto);

        Button borrarProducto = new Button("");
        borrarProducto.setStyle("-fx-background-color: black");
        borrarProducto.setPrefSize(55, 55);
        FontAwesomeIconView papelera = new FontAwesomeIconView();
        papelera.setGlyphName("TRASH");
        papelera.setFill(Color.WHITE);
        papelera.setSize("25");
        borrarProducto.setGraphic(papelera);
        borrarProducto.setOnAction(i -> desactivarCliente(Producto));
        hb.getChildren().add(borrarProducto);
        
        return hb;

    }

    private ArrayList<Producto> cargarProducto() {
        ArrayList<Producto> arrayList_Producto = new ArrayList<>();
        
        Connection con = conectar();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from articulo");

            while (rs.next()) {
                String cod_Producto = rs.getString("cod_art");
                String nom = rs.getString("nombre");
                double precio = rs.getDouble("precio");
                String marca = rs.getString("marca");
                String descripcion = rs.getString("descripcion");
                boolean act = rs.getBoolean("activo");
                String nom_imagen = rs.getString("imagen");
                Material mat = obtenerMaterialPorCodigo(rs.getInt("material"));

                if (act == true){
                    Producto Producto = new Producto(cod_Producto, nom, precio, marca, descripcion, act, nom_imagen, mat);
                    arrayList_Producto.add(Producto);             
                } 
            }
            return arrayList_Producto;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return arrayList_Producto;
    }

    private Material obtenerMaterialPorCodigo(int codigoMaterial){
    Material mat = null;

    Connection con = conectar();
    try {
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from material where codigo = " +codigoMaterial);

        if (rs.next()) {
            int codigo = rs.getInt("codigo");
            String denominacion = rs.getString("denominacion");
            mat = new Material(String.valueOf(codigo), denominacion);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return mat;
    }


    @FXML
    private void desactivarCliente(Producto Producto) {
        Connection con = conectar();
        try {
            Statement st = con.createStatement();
            st.executeUpdate("UPDATE articulo set activo='0' where cod_art='" +Producto.getCod_art()+ "'");
    
            Alertas.informacion("El articulo se ha desactivado correctamente.");
            
        } catch (SQLException sql) {
            sql.printStackTrace();
            Alertas.error("Error al desactivar el articulo");
        }
        fpane.getChildren().clear();
        all.getChildren().remove(0);
        all.getChildren().remove(1);
        initialize();
    }

    public void initialize() {
        MenuHamb.init(cont);
        all.getChildren().add(0,ImportantGUI.generateHeader());
        all.getChildren().add(ImportantGUI.generateFooter());

        lista_Productos = cargarProducto();

        for (Producto Producto : lista_Productos) {
            String nombre = Producto.getNombre();
            String marca = Producto.getMarca();
    
            fpane.getChildren().add(crearProducto(nombre, marca, Producto));
        }
    }
}
