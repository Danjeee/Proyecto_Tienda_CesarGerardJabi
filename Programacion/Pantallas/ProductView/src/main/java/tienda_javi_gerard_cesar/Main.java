package tienda_javi_gerard_cesar;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.VPos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import tienda_javi_gerard_cesar.Clases.Articulo;

public class Main {
    private ArrayList<String> resultados = new ArrayList<>();
    @FXML
    private Button camisa;
    @FXML
    private Button chaqueta;
    @FXML
    private Button pantalon;
    @FXML
    private Button bolso;
    @FXML
    private Button zapato;
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
    private void searchBar(ActionEvent e) throws IOException {
        TextField sb = (TextField) e.getSource();
        if (sb.getText().isEmpty()) {
            initialize();
        } else {
            resultados.add(sb.getText());
            buscar();
        }

    }

    @FXML
    private void filtro(ActionEvent e) throws IOException {
        Button src = (Button) e.getSource();
        String word = src.getId().toString();
        Boolean esta = false;
        for (String i : resultados) {
            if (word.equals(i)) {
                resultados.remove(i);
                esta = true;
                break;
            }
        }
        if (!esta) {
            resultados.add(word);
            src.setTextFill(Color.WHITE);
            src.setStyle("-fx-background-color: #000");
            FontAwesomeIconView ico = new FontAwesomeIconView();
            ico.setGlyphName("CLOSE");
            ico.setFill(Color.WHITE);
            src.setContentDisplay(ContentDisplay.RIGHT);
            src.setGraphic(ico);
        } else {
            src.setTextFill(Color.BLACK);
            src.setStyle("-fx-background-color: #fff");
            src.setGraphic(null);
        }
        if (resultados.isEmpty()) {
            initialize();
        } else {
            buscar();
        }
       
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
                    busq.add(new Articulo(cod, nombre, precio, img));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        for (Articulo i : busq) {
            int cod = i.getCodigo();
            String nombre = i.getNombre();
            String precio = i.getPrecio().toString();
            String img = i.getImg();
            main.getChildren().add(createItem(nombre, precio, img, cod));
        }
        if (main.getChildren().isEmpty()) {
            Alert a = alerta("Error", "No hay resultados para tu busqueda", "Error", "a");
            a.showAndWait();
            initialize();
        }
    }

    @FXML
    private void initialize() throws IOException {
        main.getChildren().clear();
        Connection con = conenct();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT nombre, precio, imagen, cod_art FROM articulo");
            while (rs.next()) {
                int cod = rs.getInt("cod_art");
                String nombre = rs.getString("nombre");
                String precio = rs.getString("precio");
                String img = rs.getString("imagen");
                main.getChildren().add(createItem(nombre, precio, img, cod));
            }
        } catch (SQLException e) {
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

    private GridPane createItem(String nombre, String precio, String img, int cod) {
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
