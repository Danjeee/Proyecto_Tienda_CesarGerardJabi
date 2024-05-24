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

    

    /*
     * @FXML
     * 
     * private Button menuHamb() {
     * Button a = new Button();
     * a.setText("");
     * a.setPrefHeight(30);
     * a.setPrefWidth(30);
     * FontAwesomeIconView ico = new FontAwesomeIconView();
     * ico.setSize("30");
     * ico.setGlyphName("NAVICON");
     * a.setGraphic(ico);
     * a.setLayoutX(25);
     * a.setLayoutY(25);
     * a.setOnAction(e -> popupHambShow());
     * return a;
     * }
     * 
     * private Button smallButton(String texto, String id) {
     * Button pant = new Button(texto);
     * pant.setId(id);
     * pant.setAlignment(Pos.CENTER_LEFT);
     * pant.setPrefWidth(500);
     * pant.setFont(new Font("System", 20));
     * if (id.equals("Pantalon") || id.equals("Camisa") || id.equals("Chaqueta") ||
     * id.equals("Zapatos") || id.equals("Bolso")) {
     * pant.setOnAction(e -> {
     * try {
     * filtrar(id, "");
     * } catch (IOException e1) {
     * e1.printStackTrace();
     * }
     * });
     * } else {
     * /*pant.setOnAction(e -> {
     * try {
     * App.setRoot(id);
     * } catch (IOException e1) {
     * e1.printStackTrace();
     * }
     * });
     * }
     * return pant;
     * }
     * 
     * private void popupHambMake() {
     * /* VBOX propiedades
     * popupHamb = new VBox();
     * popupHamb.setLayoutX(-500);
     * popupHamb.setPrefHeight(1024);
     * popupHamb.setPrefWidth(500);
     * popupHamb.setDisable(false);
     * popupHamb.setPadding(new Insets(80, 20, 20, 20));
     * popupHamb.setStyle("-fx-background-color: #fff");
     * /* Shadow
     * menuShadow = new AnchorPane();
     * menuShadow.setLayoutX(0);
     * menuShadow.setPrefHeight(1024);
     * menuShadow.setPrefWidth(1440);
     * menuShadow.setOpacity(0);
     * menuShadow.setDisable(true);
     * menuShadow.setStyle("-fx-background-color: #000");
     * /* Botones
     * Button ropa = new Button("Ropa");
     * ropa.setAlignment(Pos.CENTER_LEFT);
     * ropa.setPrefWidth(500);
     * ropa.setFont(new Font("System", 25));
     * String[] multifiltro = { "Camisa", "Pantalon", "Chaqueta" };
     * ropa.setOnAction(e -> {
     * try {
     * multiFiltrar(multifiltro);
     * } catch (IOException e1) {
     * e1.printStackTrace();
     * }
     * });
     * popupHamb.getChildren().add(ropa);
     * 
     * popupHamb.getChildren().add(smallButton("     Chaquetas", "Chaqueta"));
     * 
     * popupHamb.getChildren().add(smallButton("     Pantalones", "Pantalon"));
     * 
     * popupHamb.getChildren().add(smallButton("     Camisas", "Camisa"));
     * 
     * Button acc = new Button("Accesorios");
     * acc.setAlignment(Pos.CENTER_LEFT);
     * acc.setPrefWidth(500);
     * acc.setFont(new Font("System", 25));
     * String[] multifiltro2 = { "Zapatos", "Bolso" };
     * acc.setOnAction(e -> {
     * try {
     * multiFiltrar(multifiltro2);
     * } catch (IOException e1) {
     * e1.printStackTrace();
     * }
     * });
     * popupHamb.getChildren().add(acc);
     * 
     * popupHamb.getChildren().add(smallButton("     Zapatos", "Zapatos"));
     * 
     * popupHamb.getChildren().add(smallButton("     Bolsos", "Bolso"));
     * 
     * Pane separator = new Pane();
     * separator.setPrefHeight(500);
     * popupHamb.getChildren().add(separator);
     * 
     * Button adminPanel = new Button("Acceso a panel de administración");
     * adminPanel.setAlignment(Pos.CENTER_LEFT);
     * adminPanel.setPrefWidth(500);
     * adminPanel.setFont(new Font("System", 25));
     * popupHamb.getChildren().add(adminPanel);
     * 
     * popupHamb.getChildren().add(smallButton("    Preguntas frecuentes",
     * "preguntas"));
     * popupHamb.getChildren().add(smallButton("    Estado de mi pedido",
     * "estado"));
     * popupHamb.getChildren().add(smallButton("    Devoluciones", "devoluciones"));
     * popupHamb.getChildren().add(smallButton("    Envios", "envios"));
     * 
     * }
     * 
     * private void popupHambShow() {
     * if (menued) {
     * final Timeline timeline = new Timeline();
     * timeline.setCycleCount(1);
     * final KeyValue kv = new KeyValue(popupHamb.layoutXProperty(), -500);
     * final KeyFrame kf = new KeyFrame(Duration.millis(500), kv);
     * timeline.getKeyFrames().add(kf);
     * timeline.play();
     * menuShadow.setOpacity(0);
     * menued = false;
     * } else {
     * final Timeline timeline = new Timeline();
     * timeline.setCycleCount(1);
     * final KeyValue kv = new KeyValue(popupHamb.layoutXProperty(), 0);
     * final KeyFrame kf = new KeyFrame(Duration.millis(500), kv);
     * timeline.getKeyFrames().add(kf);
     * timeline.play();
     * menuShadow.setOpacity(0.2);
     * popupHamb.setLayoutX(0);
     * menued = true;
     * }
     * }
     */
    private void nuevoPedido() {
        Connection con = conenct();
        try {
            Statement st = con.createStatement();
            ResultSet user = st.executeQuery("SELECT * FROM cliente WHERE DNI = \"" + App.user + "\"");
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
                    + "\", \"En proceso\", \"" + App.user + "\")");
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
                all.getChildren().remove(0);
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
        MenuHamb.popupHambMake();
        cont.getChildren().add(MenuHamb.menuShadow);
        cont.getChildren().add(MenuHamb.popupHamb);
        cont.getChildren().add(MenuHamb.menuHamb());
        main.getChildren().clear();
        all.getChildren().add(0, ImportantGUI.generateHeader());
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
                                + App.user + "\" and P.estado = \"En proceso\"");
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
        Color color = Color.WHITE;
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
        if (img.equals("imagen1.jpg")) {
            ImageView fondo = new ImageView(
                    new Image(getClass().getResourceAsStream("/tienda_javi_gerard_cesar/" + img)));
            fondo.setFitHeight(175);
            fondo.setFitWidth(150);
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
        plus.setStyle("-fx-background-color: #000;-fx-background-radius: 0");
        cart.setStyle("-fx-background-color: #000;-fx-background-radius: 0");
        cart.setPrefHeight(25);
        plus.setPrefWidth(25);
        plus.setPrefHeight(25);
        cart.setPrefWidth(25);
        cart.graphicProperty().set(carticon);
        plus.graphicProperty().set(plusicon);
        cart.setOnAction(e -> addGoing(i));
        plus.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                addWOgoing(i, event.getSceneX(), event.getSceneY());
            }
        });
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
