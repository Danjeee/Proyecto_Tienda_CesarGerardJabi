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
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
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
import tienda_javi_gerard_cesar.Clases.Descuento;
import tienda_javi_gerard_cesar.Clases.ImportantGUI;
import tienda_javi_gerard_cesar.Clases.MenuHamb;

public class Cart {
    @FXML
    private FlowPane main;
    @FXML
    private VBox all;
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
    @FXML
    private Button codButton;
    private ArrayList<Articulo> articulos;
    private ArrayList<String> descuentosUsados = new ArrayList<>();
    private Descuento descuentoActivo = new Descuento("0", 0, false);
    private Double subtotalValor;
    private Double totalValor;
    private Double impValor;
    private Double envioValor;
    private Double descuentoValor;
    
    private Connection conenct() {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:4000/tienda_ropa", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    @FXML
    private void clearCodDes(){
        codDes.setText("");
        codDes.setStyle("-fx-text-inner-color: black;");
    }

    @FXML
    private void checkCodDes(){
        if (codDes.getText().isEmpty()) {
            codButton.setStyle("-fx-background-color: #d3d3d3");
            codButton.setOnAction(null);
            codButton.setOnMouseEntered(null);
            codButton.setOnMouseExited(null);
        } else {
            codButton.setStyle("-fx-background-color: #000");
            codButton.setOnAction(e -> checkDescuento());
            codButton.setOnMouseEntered(e -> codButton.setStyle("-fx-background-color: #808080"));
            codButton.setOnMouseExited(e -> codButton.setStyle("-fx-background-color: #000"));
        }
    }

    private void pagar(){
    }

    private void checkDescuento(){
        Connection con = conenct();
        ArrayList<Descuento> descuentos = new ArrayList<>();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM descuentos");
            while (rs.next()) {
                String nombre = rs.getString("descuento");
                int cant = rs.getInt("cant");
                Boolean fs = rs.getBoolean("freeShip");
                descuentos.add(new Descuento(nombre, cant, fs));
            }
            Boolean esta = false;
            for (String i : descuentosUsados){
                if (i.equals(codDes.getText())) {
                    codDes.setText("Código ya usado");
                    codDes.setStyle("-fx-text-inner-color: red;");
                    esta = true;
                    break;
                }
            }
            if (!esta) {
                for (Descuento i : descuentos){
                    if (codDes.getText().equals(i.getNombre())){
                        descuentoActivo  = i;
                        codDes.setText("Código activado (Se canjeara al pagar)");
                        codDes.setStyle("-fx-text-inner-color: green;");
                        actualizar();
                        break;
                    }
                }
                if (descuentoActivo.getNombre().equals("0")) {
                    codDes.setText("El codigo no existe / ha expirado");
                    codDes.setStyle("-fx-text-inner-color: red;");
                } else {
                    codDes.setText("Código activo: "+descuentoActivo.getNombre());
                    codDes.setStyle("-fx-text-inner-color: green;");
                }
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String setCant(Articulo i, String cant, int op) {
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

    private HBox createItem(String img, String nombre, String precio, int cant, int cod, Articulo i) {
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
            cantt.setText(setCant(i, cantt.getText(), 0));
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
            cantt.setText(setCant(i, cantt.getText(), 1));
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
        trash.setOnAction(e -> delete(i));
        a.getChildren().add(trash);
        return a;

    }

    private String formatDouble(Double a) {
        String aa = String.valueOf(a);
        if (aa.charAt(0) == ('.')) {
            aa = "00" + aa;
        }
        if (!aa.contains(".")) {
            return aa + ".00€";
        }
        if (aa.charAt(aa.length() - 1) == ('.')) {
            return aa.substring(0, aa.length() - 2) + "€";
        }
        if (aa.charAt(1) == '.') {
            aa = "0"+ aa;
        }
        for (int i = 0; i < aa.length(); i++) {
            if (aa.charAt(i) == '.') {
                int ii = i + 2;
                if (aa.length() == ii) {
                    return aa.substring(0, ii) + "0€";
                } else {
                    return aa.substring(0, ii) +aa.charAt(ii) +"€";
                }
            }
        }
        return aa;
    }

    private ArrayList<Articulo> cargarItems() {
        ArrayList<Articulo> a = new ArrayList<>();
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

    private String envio(){
        envioValor = 0.0;
        if (descuentoActivo.getFreeShip()) {
            envioValor = 0.0;
            return "GRATIS";
        }
        if (subtotalValor>0.0) {
            envioValor = 4.99;
            return formatDouble(envioValor);
        }
        return formatDouble(envioValor);
    }

    private String imp() {
        impValor = 0.0;
        impValor = ((subtotalValor-descuentoValor)*21)/100;
        return formatDouble(impValor);
    }

    private String subtotal() {
        subtotalValor = 0.0;
        for (Articulo i : articulos) {
            subtotalValor += i.getPrecio().doubleValue() * i.getCant();
        }
        return formatDouble(subtotalValor);
    }

    private String descuento() {
        descuentoValor = 0.0;
        descuentoValor = (subtotalValor * descuentoActivo.getCantidad())/100;
        return formatDouble(descuentoValor);
    }

    private String total() {
        totalValor = subtotalValor + impValor - descuentoValor;
        if (totalValor<0) {
            totalValor = 0.0;
        }
        return formatDouble(totalValor);
    }

    @FXML
    private void actualizar() {
        Connection con = conenct();
        for (Articulo i : articulos) {
            try {
                Statement st = con.createStatement();
                st.executeUpdate(
                        "UPDATE linea_pedido SET cantidad = " + i.getCant()
                                + " WHERE num_pedido = (SELECT DISTINCT L.num_pedido from linea_pedido L, pedido P WHERE L.num_pedido = P.numero and P.DNI_cliente = \""
                                + App.user + "\" and P.estado = \"En proceso\") and cod_art = " + i.getCodigo());

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        all.getChildren().remove(all.getChildren().get(2));
        all.getChildren().remove(all.getChildren().get(0));
        initialize();
    }

    @FXML
    private void delete(Articulo i) {
        Connection con = conenct();
        try {
            Statement st = con.createStatement();
            st.executeUpdate(
                    "DELETE FROM linea_pedido WHERE num_pedido = (SELECT DISTINCT L.num_pedido from linea_pedido L, pedido P WHERE L.num_pedido = P.numero and P.DNI_cliente = \""
                    + App.user + "\" and P.estado = \"En proceso\") and cod_art = " + i.getCodigo());

        } catch (SQLException e) {
            e.printStackTrace();
        }
        all.getChildren().remove(all.getChildren().get(2));
        all.getChildren().remove(all.getChildren().get(0));
        initialize();
    }

    public void initialize() {
        MenuHamb.popupHambMake();
        cont.getChildren().add(MenuHamb.menuShadow);
        cont.getChildren().add(MenuHamb.popupHamb);
        cont.getChildren().add(MenuHamb.menuHamb());
        main.getChildren().clear();
        all.getChildren().add(0, ImportantGUI.generateHeader());
        all.getChildren().add(ImportantGUI.generateFooter());
        articulos = cargarItems();
        for (Articulo i : articulos) {
            String nom = i.getNombre();
            int cant = i.getCant();
            String precio = i.getPrecio().toString();
            String img = i.getImg();
            int cod = i.getCodigo();
            main.getChildren().add(createItem(img, nom, precio, cant, cod, i));
        }
        subtotal.setText(subtotal());
        des.setText(descuento());
        imp.setText(imp());
        envio.setText(envio());
        total.setText(total());
        imp.setText("Contiene " + imp() + " de impuestos");
    }
}
