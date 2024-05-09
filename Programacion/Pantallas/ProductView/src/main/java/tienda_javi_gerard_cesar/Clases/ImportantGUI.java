package tienda_javi_gerard_cesar.Clases;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Window;
import javafx.util.Duration;
import tienda_javi_gerard_cesar.App;
import tienda_javi_gerard_cesar.Main;

public class ImportantGUI {

    private static void userMenu() {

    }

    private static void notLogged() {
        try {
            if (App.user.equals("guest")) {
                App.setRoot("login");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void searchBar(ActionEvent e) {
        TextField sb = (TextField) e.getSource();
        Main.resultados.clear();
        if (sb.getText().isEmpty()) {
            try {
                App.setRoot("main");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } else {
            Main.resultados.add(sb.getText());
            try {
                App.setRoot("main");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    public static HBox generateHeader() {
        HBox a = new HBox();
        a.setAlignment(Pos.CENTER_LEFT);
        a.setPrefHeight(100);
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
                App.setRoot("cart");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        MenuItem logout = new MenuItem("Cerrar sesión");
        logout.setOnAction(e -> {
            App.user = "guest";
            try {
                App.setRoot("login");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        MenuItem login = new MenuItem("Iniciar sesión");
        login.setOnAction(e -> {
            try {
                App.setRoot("login");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        if (App.user.equals("guest")) {
            user.getItems().add(login);
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
        if (App.user.equals("guest")) {
            cartIco.setCursor(Cursor.CROSSHAIR);
            cart.setDisable(true);
        }
        cart.setOnAction(e -> {
            try {
                if (App.user.equals("guest")) {
                    App.setRoot("login");
                } else {
                    App.setRoot("cart");
                }
            } catch (IOException ee) {
                ee.printStackTrace();
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
            e.printStackTrace();
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
    
    public static HBox generateFooter(){
        HBox a = new HBox();
        a.setPrefWidth(1440);
        a.setPrefHeight(200);
        a.setStyle("-fx-background-color: #f2f2f2");

        VBox ayuda = new VBox();
        ayuda.setPrefHeight(180);
        ayuda.setPrefWidth(520);
        ayuda.setAlignment(Pos.CENTER);
        VBox mp = new VBox();
        mp.setPrefHeight(200);
        mp.setPrefWidth(460);
        mp.setAlignment(Pos.CENTER);
        VBox sigenos = new VBox();
        sigenos.setPrefHeight(180);
        sigenos.setPrefWidth(460);
        sigenos.setAlignment(Pos.CENTER);
        Font def = new Font("System", 12);
        Font tit = new Font("System", 16);
        String bold = "-fx-font-weight: bold";

        /*AYUDA*/
        Label help = new Label("AYUDA");
        help.setFont(tit);
        help.setStyle(bold);

        Button pregFrec = new Button("Preguntas Frecuentes");
        pregFrec.setFont(def);
        /*pregFrec.setOnAction(e -> {
            try {
                App.setRoot("faq");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });*/

        Button estado = new Button("Estado de mi pedido");
        estado.setFont(def);
        /*estado.setOnAction(e -> {
            try {
                App.setRoot("estado");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });*/

        Button dev = new Button("Devoluciones");
        dev.setFont(def);
        /*dev.setOnAction(e -> {
            try {
                App.setRoot("devoluciones");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });*/

        Button envio = new Button("Envíos");
        envio.setFont(def);
        /*envio.setOnAction(e -> {
            try {
                App.setRoot("infoenvios");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });*/

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
            e.printStackTrace();
        }
        Label mptit = new Label("MÉTODOS DE PAGO");
        mptit.setFont(tit);
        mptit.setStyle(bold);

        FlowPane mpcont = new FlowPane();
        mpcont.setPrefWidth(480);
        mpcont.setAlignment(Pos.CENTER);
        mpcont.setVgap(10);
        mpcont.setHgap(20);
        for (String i : metodos){
            Label ii = new Label(i);
            ii.setPrefWidth(50);
            ii.setStyle("-fx-background-color: #cccccc; -fx-background-radius: 10;");
            ii.setAlignment(Pos.CENTER);
            mpcont.getChildren().add(ii);
        }
        mp.getChildren().addAll(mptit, mpcont);
        a.getChildren().add(mp);
        return a;
    }

     
}
