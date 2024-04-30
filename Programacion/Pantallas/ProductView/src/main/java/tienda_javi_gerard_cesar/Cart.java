package tienda_javi_gerard_cesar;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import tienda_javi_gerard_cesar.Clases.Articulo;
import tienda_javi_gerard_cesar.Clases.MenuHamb;

public class Cart {
    @FXML
    private FlowPane main;
    @FXML
    private AnchorPane cont;
    @FXML
    private TextField codDes;
    @FXML
    private Label subtotal;
    @FXML
    private Label envio;
    @FXML
    private Label total;
    @FXML
    private Label imp;
    @FXML
    private Label des;
    private int descuento = 15;

    private Connection conenct() {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:4000/tienda_ropa", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    private String setCant(String cant, int op){
        int cantt = Integer.parseInt(cant);
        switch (op) {
            case 0:
                cantt += 1;
                return String.valueOf(cantt);
            case 1:
                if (cantt == 1) {
                    return String.valueOf(cantt);
                } else {
                    cantt -= 1;
                    return String.valueOf(cantt);
                }
        
            default:
                return String.valueOf(cantt);
        }
    }

    private HBox createItem(String img, String nombre, String precio, int cant, int cod){
        HBox a = new HBox();
        a.setPrefHeight(75);
        a.setPrefWidth(725);
        a.setMaxHeight(75);
        a.setMaxWidth(725);
        a.setStyle("-fx-background-color: #000");
        a.setAlignment(Pos.CENTER);

        Button imgg = new Button("");
        imgg.setPrefHeight(75);
        imgg.setPrefWidth(100);
        imgg.setMinWidth(100);
        imgg.setText("");
        if (img.equals("imagen1.jpg")) {
            ImageView fondo = new ImageView(
                    new Image(getClass().getResourceAsStream("/tienda_javi_gerard_cesar/" + img)));
            fondo.setFitHeight(75);
            fondo.setFitWidth(100);
            imgg.setGraphic(fondo);
        }
        imgg.setOnAction(e -> {
            try {
                ProductView.current = cod;
                App.setRoot("productview");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        a.getChildren().add(imgg);

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

        Button up = new Button();
        up.setPrefHeight(25);
        up.setPrefWidth(25);
        FontAwesomeIconView ico2 = new FontAwesomeIconView();
        ico2.setGlyphName("ANGLE_UP");
        ico2.setFill(Color.WHITE);
        ico2.setSize("15");
        up.setGraphic(ico2);
        up.setOnAction(e -> {
            cantt.setText(setCant(cantt.getText(), 0));
        });
        butCont.getChildren().add(up);

        Pane sep2 = new Pane();
        sep2.setPrefHeight(25);
        sep2.setStyle("-fx-background-color: rgba(0,0,0,0)");
        butCont.getChildren().add(sep2);

        Button down = new Button();
        down.setPrefHeight(25);
        down.setPrefWidth(25);
        FontAwesomeIconView ico3 = new FontAwesomeIconView();
        ico3.setGlyphName("ANGLE_DOWN");
        ico3.setFill(Color.WHITE);
        ico3.setSize("15");
        down.setGraphic(ico3);
        down.setOnAction(e -> {
            cantt.setText(setCant(cantt.getText(), 1));
        });
        butCont.getChildren().add(down);
        a.getChildren().add(butCont);

        
        Button trash = new Button("");
        trash.setPrefHeight(70);
        trash.setPrefWidth(125);
        FontAwesomeIconView ico = new FontAwesomeIconView();
        trash.setStyle("-fx-background-color: #000");
        ico.setGlyphName("TRASH");
        ico.setFill(Color.WHITE);
        ico.setSize("50");
        trash.setGraphic(ico);
        a.getChildren().add(trash);

        return a;

    }

    private ArrayList<Articulo> cargarItems(){
        ArrayList<Articulo> a = new ArrayList<>();
        Connection con = conenct();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT A.*, L.cantidad FROM articulo A, linea_pedido L, pedido P WHERE A.cod_art = L.cod_art and L.num_pedido = P.numero and P.DNI_cliente = "+"\""+App.user+"\" and P.estado = \"En proceso\"");
            while (rs.next()) {
                String nom = rs.getString("nombre");
                BigDecimal precio = rs.getBigDecimal("precio");
                int cod = rs.getInt("cod_art");
                int cant = rs.getInt("cantidad");
                String img = rs.getString("imagen");
                Articulo aa = new Articulo(cod, nom, precio, img);
                aa.setCant(cant);
                a.add(aa);
            }
            return a;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return a;
    }
    private String imp(){
        Long desc = Long.valueOf(0);
        desc = (Long.parseLong(subtotal.getText()) * Integer.toUnsignedLong(21)) / 100;
        return String.valueOf(desc);
    }
    private String subtotal(ArrayList<String> precios){
        Long subt = Long.valueOf(0);
        for (String i : precios){
            subt += Long.parseLong(i);
        }
        return String.valueOf(subt);
    }
    private String descuento(){
        Long desc = Long.valueOf(0);
        desc = (Long.parseLong(subtotal.getText()) * Integer.toUnsignedLong(descuento)) / 100;
        return String.valueOf(desc);
    }
    private String total(){
        Long subt = Long.parseLong(subtotal.getText());
        Long desc = Long.parseLong(des.getText());
        Long impp = Long.parseLong(imp.getText());
        Long total = subt + impp - desc;
        return String.valueOf(total);
    }

    public void initialize() {
        MenuHamb.popupHambMake();
        cont.getChildren().add(MenuHamb.menuShadow);
        cont.getChildren().add(MenuHamb.popupHamb);
        cont.getChildren().add(MenuHamb.menuHamb());
        main.getChildren().clear();
        ArrayList<String> precios = new ArrayList<>();
        for (Articulo i : cargarItems()){
            String nom = i.getNombre();
            int cant = i.getCant();
            String precio = i.getPrecio().toString();
            precios.add(precio);
            String img = i.getImg();
            int cod = i.getCodigo();
            main.getChildren().add(createItem(img, nom, precio, cant, cod));
        }
        subtotal.setText(subtotal(precios));
        imp.setText(imp());
        des.setText(descuento());
        total.setText(total());
    }
}
