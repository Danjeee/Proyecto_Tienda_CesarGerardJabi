package tienda_javi_gerard_cesar;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.Light.Point;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;
import tienda_javi_gerard_cesar.Clases.Articulo;
import tienda_javi_gerard_cesar.Clases.ImportantGUI;
import tienda_javi_gerard_cesar.Clases.Mail;
import tienda_javi_gerard_cesar.Clases.MenuHamb;

public class Main {
    public static ArrayList<String> resultados = new ArrayList<>();
    public static ArrayList<String> filtros = new ArrayList<>();
    @FXML
    private Button Camisa;
    @FXML
    private Button Chaqueta;
    @FXML
    private Button Pantalon;
    @FXML
    private Button Bolso;
    @FXML
    private Button Zapatos;
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
    private FlowPane filtCont;
    @FXML
    private AnchorPane cont;
    @FXML
    private VBox all;
    private int pedido;

    private void nuevoPedido() {
        Connection con = conenct();
        try {
            Statement st = con.createStatement();
            ResultSet user = st.executeQuery("SELECT * FROM cliente WHERE DNI = \"" + App.getUser() + "\"");
            String dir = "";
            int newcol = 0;
            while (user.next()) {
                dir = user.getString("direccion");
            }
            ResultSet num = st.executeQuery("SELECT numero FROM pedido order by numero desc limit 1");
            while (num.next()) {
                newcol = num.getInt("numero") + 1;
            }
            st.executeUpdate("INSERT INTO pedido VALUES(" + newcol + ", \'"
                    + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "\', \"" + dir
                    + "\", \"En proceso\", \"" + App.getUser() + "\")");
            pedido = newcol;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void addWOgoing(Articulo i, Double x, Double y) {
        Connection con = conenct();
        Boolean existe = false;
        Boolean existePedido = false;
        try {
            Statement st = con.createStatement();
            try {
                Statement st2 = con.createStatement();
                ResultSet rs1 = st2.executeQuery("SELECT num_pedido FROM linea_pedido WHERE num_pedido = " + pedido);
                while (rs1.next()) {
                    if (rs1.getInt("num_pedido") == pedido) {
                        existePedido = true;
                    }
                }
                if (!existePedido) {
                    nuevoPedido();
                    st.executeUpdate("INSERT INTO linea_pedido VALUES(" + i.getCodigo() + ", " + pedido + ", 1)");
                } else {
                    ResultSet rs = st2.executeQuery("SELECT cod_art FROM linea_pedido WHERE num_pedido = " + pedido);
                    while (rs.next()) {
                        if (rs.getInt("cod_art") == i.getCodigo()) {
                            existe = true;
                        }
                    }
                    if (existe) {
                        st.executeUpdate(
                                "UPDATE linea_pedido SET cantidad = (SELECT cantidad FROM linea_pedido WHERE num_pedido = "
                                        + pedido + " and cod_art = " + i.getCodigo() + ")+1 WHERE num_pedido = "
                                        + pedido
                                        + " and cod_art = " + i.getCodigo());
                    } else {
                        st.executeUpdate("INSERT INTO linea_pedido VALUES(" + i.getCodigo() + ", " + pedido + ", 1)");
                    }
                }
            } catch (SQLException e) {
                st.executeUpdate("INSERT INTO linea_pedido VALUES(" + i.getCodigo() + ", " + pedido + ", 1)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Label a = ImportantGUI.mensaje(x, y, "Añadido al carrito");
        if (cont.getChildren().contains(a)) {
            cont.getChildren().remove(a);
        }
        cont.getChildren().add(a);
    }

    private void addGoing(Articulo i) {
        Connection con = conenct();
        Boolean existe = false;
        Boolean existePedido = false;
        try {
            Statement st = con.createStatement();
            try {
                Statement st2 = con.createStatement();
                ResultSet rs1 = st2.executeQuery("SELECT num_pedido FROM linea_pedido WHERE num_pedido = " + pedido);
                while (rs1.next()) {
                    if (rs1.getInt("num_pedido") == pedido) {
                        existePedido = true;
                    }
                }
                if (!existePedido) {
                    nuevoPedido();
                    st.executeUpdate("INSERT INTO linea_pedido VALUES(" + i.getCodigo() + ", " + pedido + ", 1)");
                } else {
                    ResultSet rs = st2.executeQuery("SELECT cod_art FROM linea_pedido WHERE num_pedido = " + pedido);
                    while (rs.next()) {
                        if (rs.getInt("cod_art") == i.getCodigo()) {
                            existe = true;
                        }
                    }
                    if (existe) {
                        st.executeUpdate(
                                "UPDATE linea_pedido SET cantidad = (SELECT cantidad FROM linea_pedido WHERE num_pedido = "
                                        + pedido + " and cod_art = " + i.getCodigo() + ")+1 WHERE num_pedido = "
                                        + pedido
                                        + " and cod_art = " + i.getCodigo());
                    } else {
                        st.executeUpdate("INSERT INTO linea_pedido VALUES(" + i.getCodigo() + ", " + pedido + ", 1)");
                    }
                }
            } catch (SQLException e) {
                st.executeUpdate("INSERT INTO linea_pedido VALUES(" + i.getCodigo() + ", " + pedido + ", 1)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            App.setRoot("cart");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void searchBar(ActionEvent e) throws IOException {
        TextField sb = (TextField) e.getSource();
        Main.resultados.clear();
        if (sb.getText().isEmpty()) {
            buscar();
        } else {
            Main.resultados.add(sb.getText());
            buscar();
        }

    }

    /*
     * private void multiFiltrar(String[] mult) throws IOException {
     * Main.filtros.clear();
     * for (String i : mult) {
     * filtrar(i, "a");
     * }
     * }
     * 
     * private void filtrar(String word, String mult) throws IOException {
     * Boolean esta = false;
     * if (mult.isEmpty()) {
     * Main.filtros.clear();
     * }
     * for (String i : filtros) {
     * if (word.equals(i)) {
     * Main.resultados.remove(i);
     * esta = true;
     * break;
     * }
     * }
     * if (!esta) {
     * Main.filtros.add(word);
     * } else {
     * Main.filtros.remove(word);
     * }
     * if (menued) {
     * popupHambShow();
     * }
     * buscar();
     * }
     */
    @FXML
    private void filtro(ActionEvent e) throws IOException {
        Button src = (Button) e.getSource();
        String word = src.getId().toString();
        Boolean esta = false;
        for (String i : filtros) {
            if (word.equals(i)) {
                resultados.remove(i);
                esta = true;
                break;
            }
        }
        if (!esta) {
            filtros.add(word);

        } else {
            filtros.remove(word);

        }
        buscar();

    }

    @FXML
    private Alert alerta(String tipo, String header, String titulo, String cont) {
        Alert a = new Alert(Alert.AlertType.valueOf(tipo));
        a.setHeaderText(header);
        a.setTitle(titulo);
        a.setContentText(cont);
        return a;
    }

    @FXML
    private void buscar() throws IOException {
        main.getChildren().clear();
        Connection con = conenct();
        HashSet<Articulo> busq = new HashSet();
        HashSet<Articulo> busqaux = new HashSet();
        HashSet<Articulo> busqaux2 = new HashSet();
        for (String i : resultados) {
            try {
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(
                        "SELECT nombre, precio, imagen, cod_art FROM articulo WHERE nombre like(\"%" + i + "%\")");
                while (rs.next()) {
                    int cod = rs.getInt("cod_art");
                    String nombre = rs.getString("nombre");
                    BigDecimal precio = rs.getBigDecimal("precio");
                    String img = rs.getString("imagen");
                    busqaux.add(new Articulo(cod, nombre, precio, img));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        for (Node i : filtCont.getChildren()) {
            if (i.getClass() == Button.class) {
                if (filtros.contains(i.getId())) {
                    ((Button) i).setTextFill(Color.WHITE);
                    ((Button) i).setStyle("-fx-background-color: #000");
                    FontAwesomeIconView ico = new FontAwesomeIconView();
                    ico.setGlyphName("CLOSE");
                    ico.setFill(Color.WHITE);
                    ((Button) i).setContentDisplay(ContentDisplay.RIGHT);
                    ((Button) i).setGraphic(ico);
                } else {
                    ((Button) i).setTextFill(Color.BLACK);
                    ((Button) i).setStyle("-fx-background-color: #fff");
                    ((Button) i).setGraphic(null);
                }
            }
        }
        for (String word : filtros) {
            try {
                Statement st = con.createStatement();
                ResultSet rs = null;
                if (word.equals("Camisa") || word.equals("Pantalon") || word.equals("Chaqueta")) {
                    rs = st.executeQuery(
                            "SELECT A.* FROM articulo A, ropa R WHERE A.cod_art = R.cod_art and R.tipo_ropa=\"" + word
                                    + "\"");
                } else if (word.equals("Bolso") || word.equals("Zapatos")) {
                    rs = st.executeQuery(
                            "SELECT A.* FROM articulo A, accesorio R WHERE A.cod_art = R.cod_art and R.tipo_accesorio=\""
                                    + word + "\"");
                }
                while (rs.next()) {
                    int cod = rs.getInt("cod_art");
                    String nombre = rs.getString("nombre");
                    BigDecimal precio = rs.getBigDecimal("precio");
                    String img = rs.getString("imagen");
                    busqaux2.add(new Articulo(cod, nombre, precio, img));
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        if (!filtros.isEmpty()) {
            if (resultados.isEmpty()) {
                busq.addAll(busqaux2);
            } else {
                for (Articulo i : busqaux) {
                    for (Articulo j : busqaux2) {
                        if (i.getCodigo() == j.getCodigo()) {
                            busq.add(j);
                        }
                    }
                }
            }
        } else {
            busq.addAll(busqaux);
            if (resultados.isEmpty()) {
                all.getChildren().remove(all.getChildren().get(3));
                all.getChildren().remove(all.getChildren().get(0));
                initialize();
            }
        }
        for (Articulo i : busq) {
            int cod = i.getCodigo();
            String nombre = i.getNombre();
            String precio = i.getPrecio().toString();
            String img = i.getImg();
            main.getChildren().add(createItem(nombre, precio, img, cod, i));
        }
        /*
         * if (main.getChildren().isEmpty()) {
         * Alert a = alerta("Error", "No hay resultados para tu busqueda", "Error",
         * "a");
         * a.showAndWait();
         * initialize();
         * }
         */
    }

    @FXML
    private void cartButton() {
        try {
            App.setRoot("cart");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void initialize() throws IOException {
        MenuHamb.init(cont);
        main.getChildren().clear();
        all.getChildren().add(0, ImportantGUI.generateHeader());
        all.getChildren().add(ImportantGUI.generateFooter());
        Connection con = conenct();
        try {
            if (!Main.resultados.isEmpty()) {
                buscar();
            } else {
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery("SELECT nombre, precio, imagen, cod_art FROM articulo");
                while (rs.next()) {
                    int cod = rs.getInt("cod_art");
                    String nombre = rs.getString("nombre");
                    BigDecimal precio = rs.getBigDecimal("precio");
                    String precioo = rs.getString("precio");
                    String img = rs.getString("imagen");
                    Articulo i = new Articulo(cod, nombre, precio, img);
                    main.getChildren().add(createItem(nombre, precioo, img, cod, i));
                }
                rs = st.executeQuery(
                        "SELECT DISTINCT L.num_pedido from linea_pedido L, pedido P WHERE L.num_pedido = P.numero and P.DNI_cliente = \""
                                + App.getUser() + "\" and P.estado = \"En proceso\"");
                while (rs.next()) {
                    pedido = rs.getInt("num_pedido");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (!resultados.isEmpty() || !filtros.isEmpty()) {
            buscar();
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

    private GridPane createItem(String nombre, String precio, String img, int cod, Articulo i) {
        /*
         *
         * Creacion de items
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
        /* Iconos */
        plusicon.glyphNameProperty().set("PLUS");
        carticon.glyphNameProperty().set("SHOPPING_CART");
        Color color;
        if (App.getUser().equals("guest")) {
            color = Color.LIGHTGRAY;
        } else {
            color = Color.WHITE;
        }
        plusicon.fillProperty().set(color);
        carticon.fillProperty().set(color);
        /*
         *
         * Estilos
         * 
         */
        /* Imagen */
        imgg.setPrefHeight(200);
        imgg.setPrefWidth(200);
        imgg.setText("");
        Random rnd = new Random();
        int imgrnd = rnd.nextInt(3);
        String caca= "/tienda_javi_gerard_cesar/imagen1.jpg";
        switch (imgrnd) {
            case 0:
            caca= "/tienda_javi_gerard_cesar/imagen0.jpg";
                break;
                case 1:
            caca= "/tienda_javi_gerard_cesar/imagen2.jpg";
                break;
                case 2:
            caca= "/tienda_javi_gerard_cesar/imagen3.jpg";
                break;
        
            default:
                break;
        }
        if (cod<40) {
            ImageView fondo = new ImageView(
                new Image(getClass().getResourceAsStream(caca)));
        fondo.setFitHeight(175);
        fondo.setFitWidth(150);
        imgg.setGraphic(fondo);
        }
        
        imgg.setOnAction(e -> {
            try {
                ProductView.setCurrentProduct(cod);
                App.setRoot("productview");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        /* Grid2 */
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
        fp.getColumnConstraints().get(0).setPrefWidth(150);
        /* Botones */
        if (App.getUser().equals("guest")) {
            plus.setStyle("-fx-background-color: #f2f2f2;-fx-background-radius: 0");
            cart.setStyle("-fx-background-color: #f2f2f2;-fx-background-radius: 0");
        } else {
            plus.setStyle("-fx-background-color: #000;-fx-background-radius: 0");
            cart.setStyle("-fx-background-color: #000;-fx-background-radius: 0");
            cart.setOnAction(e -> addGoing(i));
            plus.setOnMouseClicked(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {
                    addWOgoing(i, event.getSceneX(), event.getSceneY());
                }
            });
        }
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

        /* Label */
        ll.setFont(new Font("System", 15));
        /* FlowPane */
        fp.add(l, 0, 0);
        fp.add(ll, 0, 1);
        fp.add(botones, 1, 0);
        /* Row */
        r.setPrefHeight(185);
        rr.setPrefHeight(50);
        r.setValignment(VPos.TOP);
        a.getColumnConstraints().add(c);
        /* Añadir column y row */
        a.getRowConstraints().add(r);
        a.getRowConstraints().add(rr);
        a.add(imgg, 0, 0);
        a.add(fp, 0, 1);
        a.setStyle("-fx-background-color: #F9F9F9");
        ;
        return a;
    }

}
