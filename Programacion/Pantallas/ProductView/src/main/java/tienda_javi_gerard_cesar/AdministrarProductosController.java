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
    public Articulo articulo;
    public ArrayList<Articulo> lista_articulos;

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

    private HBox crearArticulo(String nombre, String marca, Articulo articulo) {
        HBox hb = new HBox();
        hb.setPrefHeight(75);
        hb.setPrefWidth(1273);
        hb.setMaxHeight(75);
        hb.setSpacing(10);
        hb.setAlignment(Pos.CENTER);

        Label infoArticulo = new Label(" " +nombre+ " (" +marca+ ")");
        infoArticulo.setStyle("-fx-font-size: 25px; -fx-background-color: #e1e1e1; -fx-font-weight: bold");
        infoArticulo.setPrefHeight(55);
        infoArticulo.setPrefWidth(1143);
        FontAwesomeIconView user = new FontAwesomeIconView();
        user.setGlyphName("TAG");
        user.setSize("35");
        infoArticulo.setGraphic(user);
        infoArticulo.setPadding(new Insets(0,0,0,13));
        hb.getChildren().add(infoArticulo);

        Button editarArticulo = new Button("");
        editarArticulo.setStyle("-fx-background-color: black");
        editarArticulo.setPrefSize(55, 55);
        FontAwesomeIconView editar = new FontAwesomeIconView();
        editar.setGlyphName("PENCIL");
        editar.setFill(Color.WHITE);
        editar.setSize("25");
        editarArticulo.setGraphic(editar);
        editarArticulo.setOnAction(i -> {
            EditarClienteController.setCurrent(articulo.getCod_art());
            try {
                App.setRoot("EditarProducto_Cesar_Javi_Gerard");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }); 
        hb.getChildren().add(editarArticulo);

        Button borrarArticulo = new Button("");
        borrarArticulo.setStyle("-fx-background-color: black");
        borrarArticulo.setPrefSize(55, 55);
        FontAwesomeIconView papelera = new FontAwesomeIconView();
        papelera.setGlyphName("TRASH");
        papelera.setFill(Color.WHITE);
        papelera.setSize("25");
        borrarArticulo.setGraphic(papelera);
        borrarArticulo.setOnAction(i -> desactivarCliente(articulo));
        hb.getChildren().add(borrarArticulo);
        return hb;

    }

    private ArrayList<Articulo> cargarArticulo() {
        ArrayList<Articulo> arrayList_Articulo = new ArrayList<>();
        
        Connection con = conectar();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from articulo");

            while (rs.next()) {
                String cod_articulo = rs.getString("cod_art");
                String nom = rs.getString("nombre");
                double precio = rs.getDouble("precio");
                String marca = rs.getString("marca");
                String descripcion = rs.getString("descripcion");
                boolean act = rs.getBoolean("activo");
                String nom_imagen = rs.getString("imagen");
                Material mat = obtenerMaterialPorCodigo(rs.getInt("material"));

                if (act == true){
                    Articulo articulo = new Articulo(cod_articulo, nom, precio, marca, descripcion, act, nom_imagen, mat);
                    arrayList_Articulo.add(articulo);             
                } 
            }
            return arrayList_Articulo;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return arrayList_Articulo;
    }

    public Material obtenerMaterialPorCodigo(int codigoMaterial){
    Material mat = null;

    Connection con = conectar();
    try {
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("select * from material where codigo = " +codigoMaterial);

        if (rs.next()) {
            int codigo = rs.getInt("codigo");
            String denominacion = rs.getString("denominacion");
            mat = new Material(codigo, denominacion);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
    return mat;
    }


    @FXML
    private void desactivarCliente(Articulo articulo) {
        Connection con = conectar();
        try {
            Statement st = con.createStatement();
            st.executeUpdate("UPDATE articulo set activo='0' where cod_art='" +articulo.getCod_art()+ "'");
    
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

        lista_articulos = cargarArticulo();

        for (Articulo articulo : lista_articulos) {
            String nombre = articulo.getNombre();
            String marca = articulo.getMarca();
    
            fpane.getChildren().add(crearArticulo(nombre, marca, articulo));
        }
    }
}
