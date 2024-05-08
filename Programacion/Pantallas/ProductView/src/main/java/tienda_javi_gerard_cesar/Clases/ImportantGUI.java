package tienda_javi_gerard_cesar.Clases;

import java.io.IOException;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.util.Duration;
import tienda_javi_gerard_cesar.App;
import tienda_javi_gerard_cesar.Main;

public class ImportantGUI {

    private static void userMenu(){

    }

    private static void notLogged(){
        try {
            if (App.user.equals("guest")) {
                App.setRoot("login");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void searchBar(ActionEvent e){
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

    public static HBox generateHeader(){
        HBox a = new HBox();
        a.setAlignment(Pos.CENTER_LEFT);
        a.setPrefHeight(125);
        a.setPrefWidth(1440);
        a.setStyle("-fx-background-color: #fff");

        Label second = new Label("second");
        second.setAlignment(Pos.CENTER_RIGHT);
        second.setFont(new Font("Calibri", 46));
        second.setPrefWidth(720);

        Label hand = new Label("hand");
        hand.setAlignment(Pos.CENTER_LEFT);
        hand.setPrefWidth(250);
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
            App.user="guest";
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

        a.getChildren().addAll(second, hand, lupa, user);
        return a;

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
    /*public static HBox generateFooter(){

    }*/
}
