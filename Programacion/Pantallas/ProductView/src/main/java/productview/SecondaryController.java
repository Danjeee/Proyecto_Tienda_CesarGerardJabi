package productview;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;

public class SecondaryController {
    @FXML
    private GridPane baseGrid;
    @FXML
    private Button baseBotonImagen;
    @FXML
    private ImageView imagenBase;
    @FXML
    private Label tituloBase;
    @FXML
    private Label precioBase;
    @FXML
    private Button addBase;
    @FXML
    private Button cartBase;
    @FXML
    private FontAwesomeIconView plus;
    @FXML
    private FontAwesomeIconView cart;
    @FXML

    private FlowPane main;
    @FXML
    private void initialize() throws IOException{
        Connection con = conenct();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT nombre, precio, imagen FROM articulo");
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                String precio = rs.getString("precio");
                String img = rs.getString("imagen");
                main.getChildren().add(createItem(nombre, precio, img));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    private Connection conenct() {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:4000/tienda_ropa", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
    
    private GridPane createItem(String nombre, String precio, String img){
        /* 
        *
        Creacion de items
        * 
        */
        GridPane a = new GridPane();
        ColumnConstraints c = new ColumnConstraints();
        c.setPrefWidth(200);
        RowConstraints r = new RowConstraints();
        RowConstraints rr = new RowConstraints();
        Label l = new Label(nombre);
        Label ll = new Label(precio);
        GridPane fp = new GridPane();
        Button plus = new Button();
        Button cart = new Button();
        FontAwesomeIconView plusicon = new FontAwesomeIconView();
        FontAwesomeIconView carticon = new FontAwesomeIconView();
        Button imgg = new Button();
        /*Iconos */
        plusicon.glyphNameProperty().set("PLUS");
        carticon.glyphNameProperty().set("SHOPPING_CART");
        Color color = Color.WHITE;
        plusicon.fillProperty().set(color);
        carticon.fillProperty().set(color);
        /*
        *
        Estilos
        * 
        */
        /*Imagen*/
        imgg.setPrefHeight(200);
        imgg.setPrefWidth(200);
        imgg.setText("");
        imgg.setStyle("-fx-background-image: url(./src/main/resources/productview/images/"+img+")");
        imgg.setOnAction(e -> {
            try {
                App.setRoot("productview");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        /*Grid2 */
        RowConstraints datos = new RowConstraints();
        datos.setPrefHeight(50);
        ColumnConstraints datosCol = new ColumnConstraints();
        datosCol.setPrefWidth(150);
        ColumnConstraints bot = new ColumnConstraints();
        datosCol.setPrefWidth(50);
        fp.getColumnConstraints().clear();
        fp.getColumnConstraints().add(datosCol);
        fp.getColumnConstraints().add(bot);
        fp.getRowConstraints().add(datos);
        fp.getColumnConstraints().get(0).setPrefWidth(140);
        /*Botones */
        plus.setStyle("-fx-background-color: #000;-fx-background-radius: 0");
        cart.setStyle("-fx-background-color: #000;-fx-background-radius: 0");
        cart.setPrefHeight(25);
        plus.setPrefWidth(25);
        plus.setPrefHeight(25);
        cart.setPrefWidth(25);
        cart.graphicProperty().set(carticon);
        plus.graphicProperty().set(plusicon);
        HBox botones = new HBox();
        botones.setPrefHeight(25);
        botones.setPrefWidth(50);
        botones.getChildren().addAll(plus, cart);
        
        /*Label */
        ll.setFont(new Font("System", 15));
        /*FlowPane */
        fp.add(l, 0, 0);
        fp.add(ll, 0, 1);
        fp.add(botones, 1, 0);
        /*Row */
        r.setPrefHeight(185);
        rr.setPrefHeight(50);
        r.setValignment(VPos.TOP);
        a.getColumnConstraints().add(c);
        /*Añadir column y row */
        a.getRowConstraints().add(r);
        a.getRowConstraints().add(rr);
        a.add(imgg, 0, 0);
        a.add(fp, 0 ,1);
        a.styleProperty().set("-fx-background-color: #fff");;
        return a;
    }

}
