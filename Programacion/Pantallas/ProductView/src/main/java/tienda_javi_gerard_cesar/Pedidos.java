package tienda_javi_gerard_cesar;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import tienda_javi_gerard_cesar.Clases.*;

public class Pedidos {
    @FXML
    private VBox all;
    @FXML
    private AnchorPane cont;
    @FXML
    private VBox pedidos;

    private Connection conenct() {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:4000/tienda_ropa", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    private ArrayList<Pedido> cargarPedidos() {
        ArrayList<Pedido> p = new ArrayList<>();
        Connection con = conenct();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM pedido WHERE DNI_cliente = '" + App.getUser() + "'");
            while (rs.next()) {
                int num = rs.getInt("numero");
                String fecha = rs.getString("fecha");
                String dir = rs.getString("dir_envio");
                String estado = rs.getString("estado");
                Statement st1 = con.createStatement();
                ResultSet rs1 = st1.executeQuery(
                        "SELECT A.*, P.cantidad FROM articulo A, linea_pedido P WHERE P.cod_art = A.cod_art and P.num_pedido = "
                                + num);
                ArrayList<Articulo> aa = new ArrayList<>();
                while (rs1.next()) {

                    Articulo a = new Articulo(rs1.getInt("cod_art"), rs1.getString("nombre"),
                            rs1.getBigDecimal("precio"), rs1.getString("imagen"));
                    a.setCant(rs1.getInt("cantidad"));
                    aa.add(a);
                }
                Pedido pp = new Pedido(num, fecha, dir, estado);
                pp.setProductos(aa);
                p.add(pp);
            }
            return p;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }

    private String descuentoUsado(int num) {
        Connection con = conenct();
        try {
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT descuento FROM descuentos_usados WHERE pedido = " + num);
            while (rs.next()) {
                return rs.getString("descuento");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "No usado";
    }

    private VBox createItem(Pedido p) {
        VBox b = new VBox();
        b.setPrefHeight(100);
        b.setPrefWidth(795);
        HBox a = new HBox();
        a.setPrefHeight(100);
        a.setPrefWidth(795);
        b.setStyle("-fx-background-color: #fff; -fx-background-radius: 10");
        a.setPadding(new Insets(10, 30, 10, 30));
        a.setAlignment(Pos.CENTER_LEFT);
        a.setSpacing(50);

        Font def = new Font("System", 25);

        VBox numcont = new VBox();
        numcont.setAlignment(Pos.CENTER);
        numcont.setPrefWidth(100);
        Label num = new Label("Cod: " + p.getNum());
        num.setFont(def);
        numcont.getChildren().add(num);
        a.getChildren().add(numcont);

        VBox fechacont = new VBox();
        fechacont.setAlignment(Pos.CENTER);
        fechacont.setPrefWidth(150);
        Label fecha = new Label(p.getFecha());
        fecha.setFont(def);
        fechacont.getChildren().add(fecha);
        a.getChildren().add(fechacont);

        VBox descuento = new VBox();
        descuento.setAlignment(Pos.CENTER);
        descuento.setPrefWidth(250);
        Label desc = new Label();
        if (p.getEstado().equals("Completado")) {
            desc = new Label("Descuento: " + descuentoUsado(p.getNum()));
            desc.setFont(def);

        } else {
            desc = new Label("No completado");
            desc.setFont(def);

        }
        descuento.getChildren().add(desc);
        a.getChildren().add(descuento);

        Descuento curr = new Descuento("0", 0, false);

        if (!descuentoUsado(p.getNum()).isBlank()) {
            Connection con = conenct();
            try {
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT * FROM descuentos");
                while (rs.next()) {
                    if (rs.getString("descuento").equals(descuentoUsado(p.getNum()))) {
                        curr = new Descuento(rs.getString("descuento"), rs.getInt("cant"), rs.getBoolean("freeShip"));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        double envio = 4.99;
        if (curr.getFreeShip()) {
            envio = 0;
        }

        VBox totalcont = new VBox();
        totalcont.setPrefWidth(200);
        totalcont.setAlignment(Pos.CENTER);

        double subtotal = calculartotal(p.getProductos());

        Label total = new Label(
                "Total: " + formatDouble(subtotal + ((subtotal - (subtotal * curr.getCantidad() / 100)) * 21 / 100)
                        - (subtotal * curr.getCantidad() / 100) + envio));
        total.setFont(def);
        totalcont.getChildren().add(total);
        a.getChildren().add(totalcont);

        Button veritems = new Button("Ver articulos ");
        FontAwesomeIconView ico = new FontAwesomeIconView();
        ico.setGlyphName("CARET_DOWN");
        ico.setSize("20");
        double totalenvio = envio;
        int desccant = curr.getCantidad();
        veritems.setGraphic(ico);
        veritems.setContentDisplay(ContentDisplay.LEFT);
        veritems.setFont(def);
        veritems.setOnAction(e -> showItems(b, p, desccant, totalenvio));
        HBox rightitems = new HBox();
        rightitems.setPrefWidth(500);
        rightitems.setAlignment(Pos.CENTER_RIGHT);
        rightitems.getChildren().add(veritems);
        VBox estadocont = new VBox();
        estadocont.setPrefWidth(200);
        TextField estado = new TextField();

        switch (p.getEstado()) {
            case "En proceso":
                Button pagar = new Button("Pagar");
                pagar.setFont(def);
                pagar.setPrefWidth(125);
                pagar.setOnAction(e -> {
                    try {
                        App.setRoot("cart");
                        App.setRoot("pagar");
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                });
                estadocont.getChildren().add(pagar);
                break;
            case "Completado":
                estado = new TextField("Completado");
                estado.setFont(def);
                estado.setStyle("-fx-text-inner-color: green; -fx-background-color: rgba(0,0,0,0);");
                estado.setEditable(false);
                estado.setCursor(Cursor.DEFAULT);
                estadocont.getChildren().add(estado);
                break;
            case "Cancelado":
                estado = new TextField("Cancelado");
                estado.setFont(def);
                estado.setStyle("-fx-text-inner-color: red; -fx-background-color: rgba(0,0,0,0);");
                estado.setEditable(false);
                estado.setCursor(Cursor.DEFAULT);
                estadocont.getChildren().add(estado);
                break;

            default:
                break;
        }
        estadocont.setAlignment(Pos.CENTER);
        rightitems.getChildren().add(estadocont);
        rightitems.setAlignment(Pos.CENTER);
        rightitems.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        a.getChildren().add(rightitems);

        b.getChildren().add(a);

        return b;

    }

    private HBox crearArticulo(Articulo art, VBox b) {
        HBox a = new HBox();
        Font def = new Font("System", 18);
        a.setPrefHeight(75);
        a.setPrefWidth(b.getWidth());
        a.setAlignment(Pos.CENTER);
        a.setSpacing(100);

        VBox codigocont = new VBox();
        codigocont.setPrefWidth(695 / 6 - 50);
        codigocont.setPrefHeight(a.getHeight());
        VBox nombrecont = new VBox();
        nombrecont.setPrefWidth(695 / 6 + 100);
        nombrecont.setPrefHeight(a.getHeight());
        VBox preciocont = new VBox();
        preciocont.setPrefWidth(695 / 6);
        preciocont.setPrefHeight(a.getHeight());
        VBox cantcont = new VBox();
        cantcont.setPrefWidth(695 / 6 - 50);
        cantcont.setPrefHeight(a.getHeight());
        VBox totalcont = new VBox();
        totalcont.setPrefWidth(695 / 6);
        totalcont.setPrefHeight(a.getHeight());
        VBox imgcont = new VBox();
        imgcont.setPrefWidth(695 / 6);
        imgcont.setPrefHeight(a.getHeight());

        Label codigo = new Label(String.valueOf(art.getCodigo()));
        codigo.setFont(def);
        Label nombre = new Label(art.getNombre());
        nombre.setFont(def);
        Label precio = new Label(formatDouble(art.getPrecio().doubleValue()));
        precio.setFont(def);
        Label cantidad = new Label(String.valueOf(art.getCant()));
        cantidad.setFont(def);
        Label total = new Label(formatDouble(art.getPrecio().doubleValue() * art.getCant()));
        total.setFont(def);
        Button producto = new Button("");
        producto.setPrefHeight(50);
        producto.setMaxHeight(50);
        producto.setPrefWidth(75);
        producto.setMaxWidth(75);
        if (art.getImg().equals("imagen1.jpg")) {
            ImageView fondo = new ImageView(
                    new Image(getClass().getResourceAsStream("/tienda_javi_gerard_cesar/" + art.getImg())));
            fondo.setFitHeight(50);
            fondo.setFitWidth(75);
            producto.setGraphic(fondo);
        }
        producto.setOnAction(e -> {
            try {
                ProductView.setCurrentProduct(art.getCodigo());
                App.setRoot("productview");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        imgcont.getChildren().add(producto);
        codigocont.getChildren().add(codigo);
        nombrecont.getChildren().add(nombre);
        preciocont.getChildren().add(precio);
        cantcont.getChildren().add(cantidad);
        totalcont.getChildren().add(total);
        a.getChildren().addAll(imgcont, codigocont, nombrecont, preciocont, cantcont, totalcont);
        for (Node i : a.getChildren()) {
            if (i instanceof VBox) {
                VBox ii = (VBox) i;
                ii.setAlignment(Pos.CENTER);
            }
        }
        return a;
    }

    private void showItems(VBox a, Pedido p, int d, double env) {
        if (a.getChildren().size() == 1) {
            a.setPrefHeight(500);
            a.setAlignment(Pos.TOP_LEFT);
            ScrollPane sp = new ScrollPane();
            VBox content = new VBox();
            content.setPrefWidth(a.getWidth() - 120);
            content.setPrefHeight(350);
            sp.setPrefWidth(a.getWidth() - 100);
            sp.setPrefHeight(350);
            content.setStyle("-fx-background-color: #fff");
            HBox info = new HBox();
            info.setPrefWidth(a.getWidth()-120);
            info.setPrefHeight(75);
            info.setSpacing(50);
            Label img = new Label("Imagen");
            Label cod= new Label("Codigo");
            Label nom = new Label("Nombre");
            Label precio = new Label("Precio");
            Label cant = new Label("Cant");
            Label tot = new Label("Total");
            info.getChildren().addAll(img,cod,nom,precio,cant,tot);
            info.setAlignment(Pos.CENTER);
            for (Node i : info.getChildren()){
                Label ii = (Label)i;
                ii.setFont(new Font("System", 18));
                ii.setPrefWidth((a.getWidth()-120)/6);
                ii.setAlignment(Pos.CENTER);
                if (ii.getText().equals("Codigo")) {
                    ii.setPrefWidth(((a.getWidth()-120)/6)-50);
                }
                if (ii.getText().equals("Nombre")) {
                    ii.setPrefWidth(((a.getWidth()-120)/6)+100);
                }
                if (ii.getText().equals("Cant")) {
                    ii.setPrefWidth(((a.getWidth()-120)/6)-50);
                }
            }
            
            Label descuento = new Label("Descuento:    "+formatDouble(calculartotal(p.getProductos())*d/100));
            descuento.setFont(new Font("System", 18));
            descuento.setPrefHeight(75);
            descuento.setPrefWidth((a.getWidth()-120)/6);
            descuento.setAlignment(Pos.CENTER);
            Label impuesto = new Label("IVA (21%):    "+formatDouble((calculartotal(p.getProductos())-(calculartotal(p.getProductos())*d/100))*21/100));
            impuesto.setFont(new Font("System", 18));
            impuesto.setPrefHeight(75);
            impuesto.setPrefWidth((a.getWidth()-120)/6);
            impuesto.setAlignment(Pos.CENTER);
            Label envio = new Label("Envio:    "+formatDouble(env));
            envio.setFont(new Font("System", 18));
            envio.setPrefHeight(75);
            envio.setPrefWidth((a.getWidth()-120)/6);
            envio.setAlignment(Pos.CENTER);
            content.getChildren().addAll(descuento, impuesto, envio, info);
            for (Articulo i : p.getProductos()) {
                content.getChildren().add(crearArticulo(i, content));
            }
            a.getChildren().add(sp);
            sp.setContent(content);
            a.setMargin(a.getChildren().get(1), new Insets(25, 50, 25, 50));
        } else {
            for (int i = 1; i < a.getChildren().size(); i++) {
                a.getChildren().remove(i);
                a.setPrefHeight(100);
                a.setAlignment(Pos.CENTER_LEFT);
            }
        }
    }

    private double calculartotal(ArrayList<Articulo> a) {
        double total = 0;
        for (Articulo i : a) {
            total += i.getPrecio().doubleValue() * i.getCant();
        }
        return total;
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
            aa = "0" + aa;
        }
        for (int i = 0; i < aa.length(); i++) {
            if (aa.charAt(i) == '.') {
                int ii = i + 2;
                if (aa.length() == ii) {
                    return aa.substring(0, ii) + "0€";
                } else {
                    return aa.substring(0, ii) + aa.charAt(ii) + "€";
                }
            }
        }
        return aa;
    }

    @FXML
    private void initialize() {
        MenuHamb.init(cont);
        all.getChildren().add(0, ImportantGUI.generateHeader());
        all.getChildren().add(ImportantGUI.generateFooter());
        for (Pedido i : cargarPedidos()) {
            pedidos.getChildren().add(createItem(i));
        }
    }
}
