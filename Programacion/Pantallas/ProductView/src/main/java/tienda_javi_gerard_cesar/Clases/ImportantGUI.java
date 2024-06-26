package tienda_javi_gerard_cesar.Clases;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Window;
import javafx.util.Duration;
import tienda_javi_gerard_cesar.App;
import tienda_javi_gerard_cesar.Cart;
import tienda_javi_gerard_cesar.Main;

public class ImportantGUI {

    private static void searchBar(ActionEvent e) {
        TextField sb = (TextField) e.getSource();
        Main.resultados.clear();
        if (sb.getText().isEmpty()) {
            try {
                App.setRoot("main");
            } catch (Exception e1) {
                Logs.createIOLog(e1);
            }
        } else {
            Main.resultados.add(sb.getText());
            try {
                App.setRoot("main");
            } catch (Exception e1) {
                Logs.createIOLog(e1);
            }
        }
    }

    public static Button defaultBack() {
        Button a = new Button();
        a.setText("");
        a.setPrefHeight(30);
        a.setPrefWidth(30);
        FontAwesomeIconView ico = new FontAwesomeIconView();
        ico.setSize("30");
        ico.setGlyphName("ARROW_LEFT");
        a.setGraphic(ico);
        a.setLayoutX(25);
        a.setLayoutY(21);
        a.setOnAction(e -> {
            try {
                App.setRoot(App.getLast());
            } catch (Exception e1) {
                Logs.createIOLog(e1);
            }
        });
        return a;
    }

    private static boolean isAdmin() {
        Connection con = conenct();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT DNI FROM empleado");
            while (rs.next()) {
                if (App.getUser().equals(rs.getString("DNI"))) {
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            Logs.createSQLLog(e);
        }
        return false;
    }

    public static HBox generateHeader() {
        HBox a = new HBox();
        a.setAlignment(Pos.CENTER_LEFT);
        a.setPrefHeight(80);
        a.setMinHeight(80);
        a.setPrefWidth(1440);
        a.setStyle("-fx-background-color: #fff");

        Label second = new Label("second");
        second.setAlignment(Pos.CENTER_RIGHT);
        second.setFont(new Font("Calibri", 46));
        second.setPrefWidth(720);

        Label hand = new Label("hand");
        hand.setAlignment(Pos.CENTER_LEFT);
        hand.setPrefWidth(450);
        hand.setFont(new Font("Calibri", 46));
        hand.setStyle("-fx-font-weight: bold");

        TextField lupa = new TextField();
        lupa.promptTextProperty().set(" 🔎 Buscar");
        lupa.setPrefWidth(150);
        lupa.setStyle("-fx-background-color: #fff; -fx-border-color: #000; -fx-border-width: 1;");
        if (!Main.resultados.isEmpty()) {
            lupa.setText(Main.resultados.get(0));
        }
        lupa.setOnAction(e -> searchBar(e));

        MenuButton user = new MenuButton();
        user.setPrefHeight(25);
        user.setPrefHeight(25);
        user.setStyle("-fx-background-color: #fff; -fx-mark-color: transparent;");
        FontAwesomeIconView userIco = new FontAwesomeIconView();
        userIco.setGlyphName("USER");
        userIco.setSize("30");
        user.setText("");
        user.setGraphic(userIco);
        

        MenuItem cuenta = new MenuItem("Cuenta");
        cuenta.setOnAction(e -> {
            try {
                App.setRoot("cuenta");
            } catch (Exception e1) {
                e1.printStackTrace();
                Logs.createIOLog(e1);
            }
        });
        MenuItem logout = new MenuItem("Cerrar sesión");
        logout.setOnAction(e -> {
            Cart.descuentoActivo = new Descuento("0", 0, false, "0");
            App.setUser("guest");
            try {
                App.setRoot("login");
            } catch (Exception e1) {
                Logs.createIOLog(e1);
            }
        });
        MenuItem login = new MenuItem("Iniciar sesión");
        login.setOnAction(e -> {
            try {
                App.setRoot("login");
            } catch (Exception e1) {
                Logs.createIOLog(e1);
            }
        });
        if (App.getUser().equals("guest")) {
            user.getItems().add(login);
        } else if (isAdmin()) {
            user.getItems().add(logout);
        } else {
            user.getItems().addAll(cuenta, new SeparatorMenuItem(), logout);
        }
        Button cart = new Button();
        cart.setPrefHeight(25);
        cart.setPrefHeight(25);
        cart.setStyle("-fx-background-color: #fff;");
        FontAwesomeIconView cartIco = new FontAwesomeIconView();
        cartIco.setGlyphName("SHOPPING_CART");
        cartIco.setSize("30");
        cart.setText("");
        cart.setGraphic(cartIco);
        if (App.getUser().equals("guest") || isAdmin()) {
            cartIco.setCursor(Cursor.CROSSHAIR);
            cart.setDisable(true);
        }
        cart.setOnAction(e -> {
            try {
                if (App.getUser().equals("guest")) {
                    App.setRoot("login");
                } else {
                    App.setRoot("cart");
                }
            } catch (Exception ee) {
                Logs.createIOLog(ee);
            }
        });

        a.getChildren().addAll(second, hand, lupa, user, cart);
        return a;

    }

    private static Connection conenct() {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:4000/tienda_ropa", "root", "");
        } catch (SQLException e) {
            Logs.createSQLLog(e);
        }
        return con;
    }

    public static Label mensaje(Double x, Double y, String texto) {
        Label a = new Label(texto);
        a.setId("mensaje");
        a.setFont(new Font("System", 12));
        a.setStyle("-fx-background-color: #fff; -fx-background-radius: 20");
        a.setPrefHeight(30);
        a.setPrefWidth(110);
        a.setAlignment(Pos.CENTER);
        a.setOpacity(1);
        a.setLayoutX(x);
        a.setDisable(true);
        a.setLayoutY(y);
        final Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        final KeyValue kv = new KeyValue(a.opacityProperty(), 0);
        final KeyFrame kf = new KeyFrame(Duration.millis(1500), kv);
        timeline.getKeyFrames().add(kf);
        timeline.play();
        return a;
    }

    public static HBox generateFooter() {
        HBox a = new HBox();
        a.setPadding(new Insets(0, 0, 20, 0));
        a.setPrefWidth(1440);
        a.setPrefHeight(200);
        a.setMinHeight(200);
        a.setStyle("-fx-background-color: #fff");

        VBox ayuda = new VBox();
        ayuda.setPrefHeight(180);
        ayuda.setPrefWidth(400);
        ayuda.setAlignment(Pos.CENTER);
        VBox mp = new VBox();
        mp.setPrefHeight(200);
        mp.setPrefWidth(600);
        mp.setAlignment(Pos.CENTER);
        VBox sigenos = new VBox();
        sigenos.setPrefHeight(180);
        sigenos.setPrefWidth(400);
        sigenos.setAlignment(Pos.CENTER);
        Font def = new Font("System", 12);
        Font tit = new Font("System", 16);
        String bold = "-fx-font-weight: bold";

        /* AYUDA */
        Label help = new Label("AYUDA");
        help.setFont(tit);
        help.setStyle(bold);

        Button pregFrec = new Button("Preguntas Frecuentes");
        pregFrec.setFont(def);

        pregFrec.setOnAction(e -> {
            try {
                App.setRoot("faq");
            } catch (Exception e1) {
                Logs.createIOLog(e1);
            }
        });

        Button estado = new Button("Estado de mi pedido");
        estado.setFont(def);

        estado.setOnAction(e -> {
            try {
                App.setRoot("pedidos");
            } catch (Exception e1) {
                Logs.createIOLog(e1);
            }
        });

        Button dev = new Button("Devoluciones");
        dev.setFont(def);
        dev.setOnAction(e -> {
            try {
                App.setRoot("devoluciones");
            } catch (Exception e1) {
                Logs.createIOLog(e1);
            }
        });

        Button envio = new Button("Envíos");
        envio.setFont(def);

        envio.setOnAction(e -> {
            try {
                App.setRoot("envios");
            } catch (Exception e1) {
                Logs.createIOLog(e1);
            }
        });

        ayuda.getChildren().addAll(help, pregFrec, estado, dev, envio);
        a.getChildren().add(ayuda);

        Connection con = conenct();
        ArrayList<String> metodos = new ArrayList<>();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM metodo_pago");
            while (rs.next()) {
                metodos.add(rs.getString("descripcion"));
            }
        } catch (SQLException e) {
            Logs.createSQLLog(e);
        } catch (NullPointerException e){
            System.out.print("");
        }
        Label mptit = new Label("MÉTODOS DE PAGO");
        mptit.setFont(tit);
        mptit.setStyle(bold);
        mptit.setAlignment(Pos.TOP_CENTER);
        mptit.setPrefHeight(50);

        FlowPane mpcont = new FlowPane();
        mpcont.setPrefWidth(480);
        mpcont.setPrefHeight(110);
        mpcont.setAlignment(Pos.TOP_CENTER);
        mpcont.setVgap(10);
        mpcont.setHgap(20);
        for (String i : metodos) {
            Label ii = new Label(i);
            ii.setPrefWidth(50);
            ii.setStyle("-fx-background-color: #cccccc; -fx-background-radius: 10;");
            ii.setAlignment(Pos.CENTER);
            mpcont.getChildren().add(ii);
        }
        FlowPane extra = new FlowPane();
        extra.setPrefWidth(520);
        extra.setPrefHeight(35);
        String[] extracont = { "Politica de privacidad", "Condiciones de compras", "Politica de cookies",
                "Gana descuentos" };
        for (int i = 0; i < extracont.length; i++) {
            Button ee = new Button(extracont[i]);
            ee.setStyle("-fx-background-color: rgba(0,0,0,0)");
            ee.setFont(def);
            extra.getChildren().add(ee);
            if (extracont[i].equals("Gana descuentos")) {
                ee.setOnAction(e -> {
                    try {
                        App.setRoot("flappy");
                    } catch (Exception e1) {
                        Logs.createIOLog(e1);
                    }
                });
            }
            if (i != extracont.length - 1) {
                extra.getChildren().add(new Separator(Orientation.VERTICAL));
            }
        }

        mp.getChildren().addAll(mptit, mpcont, extra);
        a.getChildren().add(mp);

        Label sig = new Label("¡SIGUENOS!");
        sig.setFont(tit);
        sig.setStyle(bold);
        sig.setAlignment(Pos.TOP_CENTER);
        sig.setPrefHeight(50);

        FlowPane redes = new FlowPane();
        redes.setPrefWidth(520);
        redes.setPrefHeight(35);
        redes.setVgap(10);
        redes.setHgap(20);
        redes.setAlignment(Pos.TOP_CENTER);
        HashMap<String, String> redescont = new HashMap<>();
        redescont.put("INSTAGRAM", "https://www.instagram.com/_danje69_");
        redescont.put("YOUTUBE", "https://www.youtube.com/watch?v=vXYVfk7agqU");
        redescont.put("GITHUB", "https://github.com/Danjeee/Proyecto_Tienda_CesarGerardJabi");
        redescont.put("TWITTER", "https://x.com/KFC_ES");
        for (int i = 0; i < redescont.size(); i++) {
            Button ee = new Button("");
            ee.setStyle("-fx-background-color: #fff; -fx-background-radius: 20");
            ee.setPrefSize(30, 30);
            FontAwesomeIconView rs = new FontAwesomeIconView();
            rs.setGlyphName((String) redescont.keySet().toArray()[i]);
            String url = (String) redescont.values().toArray()[i];
            rs.setSize("30");
            ee.setGraphic(rs);
            ee.setOnAction(e -> urlOpener(url));

            redes.getChildren().add(ee);
        }
        sigenos.getChildren().addAll(sig, redes);
        a.getChildren().add(sigenos);

        return a;
    }

    private static void urlOpener(String url) {
        try {
            URI uri = new URI(url);
            Desktop dt = Desktop.getDesktop();
            dt.browse(uri);
        } catch (Exception e) {
            Logs.createIOLog(e);
        }

    }

}
