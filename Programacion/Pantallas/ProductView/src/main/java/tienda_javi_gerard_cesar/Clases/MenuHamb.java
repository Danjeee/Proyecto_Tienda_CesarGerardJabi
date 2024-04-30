package tienda_javi_gerard_cesar.Clases;

import java.io.IOException;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Duration;
import tienda_javi_gerard_cesar.App;
import tienda_javi_gerard_cesar.Main;

public class MenuHamb {
    public static VBox popupHamb;
    public static AnchorPane menuShadow;
    public static Boolean menued = false;

    public static Button menuHamb() {
        Button a = new Button();
        a.setText("");
        a.setPrefHeight(30);
        a.setPrefWidth(30);
        FontAwesomeIconView ico = new FontAwesomeIconView();
        ico.setSize("30");
        ico.setGlyphName("NAVICON");
        a.setGraphic(ico);
        a.setLayoutX(25);
        a.setLayoutY(25);
        a.setOnAction(e -> popupHambShow());
        return a;
    }

    public static Button smallButton(String texto, String id) {
        Button pant = new Button(texto);
        pant.setId(id);
        pant.setAlignment(Pos.CENTER_LEFT);
        pant.setPrefWidth(500);
        pant.setFont(new Font("System", 20));
        if (id.equals("Pantalon") || id.equals("Camisa") || id.equals("Chaqueta") || id.equals("Zapatos") || id.equals("Bolso")) {
            pant.setOnAction(e -> {
                try {
                    filtrar(id, "");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            });
        } else {
            /*pant.setOnAction(e -> {
                try {
                    App.setRoot(id);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            });*/
        }
        return pant;
    }

    public static void popupHambMake() {
        /* VBOX propiedades */
        popupHamb = new VBox();
        popupHamb.setLayoutX(-500);
        popupHamb.setPrefHeight(1024);
        popupHamb.setPrefWidth(500);
        popupHamb.setDisable(false);
        popupHamb.setPadding(new Insets(80, 20, 20, 20));
        popupHamb.setStyle("-fx-background-color: #fff");
        /* Shadow */
        menuShadow = new AnchorPane();
        menuShadow.setLayoutX(0);
        menuShadow.setPrefHeight(1024);
        menuShadow.setPrefWidth(1440);
        menuShadow.setOpacity(0);
        menuShadow.setDisable(true);
        menuShadow.setStyle("-fx-background-color: #000");
        /* Botones */
        Button ropa = new Button("Ropa");
        ropa.setAlignment(Pos.CENTER_LEFT);
        ropa.setPrefWidth(500);
        ropa.setFont(new Font("System", 25));
        String[] multifiltro = { "Camisa", "Pantalon", "Chaqueta" };
        ropa.setOnAction(e -> {
            try {
                multiFiltrar(multifiltro);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        popupHamb.getChildren().add(ropa);

        popupHamb.getChildren().add(smallButton("     Chaquetas", "Chaqueta"));

        popupHamb.getChildren().add(smallButton("     Pantalones", "Pantalon"));

        popupHamb.getChildren().add(smallButton("     Camisas", "Camisa"));

        Button acc = new Button("Accesorios");
        acc.setAlignment(Pos.CENTER_LEFT);
        acc.setPrefWidth(500);
        acc.setFont(new Font("System", 25));
        String[] multifiltro2 = { "Zapatos", "Bolso" };
        acc.setOnAction(e -> {
            try {
                multiFiltrar(multifiltro2);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });
        popupHamb.getChildren().add(acc);

        popupHamb.getChildren().add(smallButton("     Zapatos", "Zapatos"));

        popupHamb.getChildren().add(smallButton("     Bolsos", "Bolso"));

        Pane separator = new Pane();
        separator.setPrefHeight(500);
        popupHamb.getChildren().add(separator);

        Button adminPanel = new Button("Acceso a panel de administración");
        adminPanel.setAlignment(Pos.CENTER_LEFT);
        adminPanel.setPrefWidth(500);
        adminPanel.setFont(new Font("System", 25));
        popupHamb.getChildren().add(adminPanel);

        popupHamb.getChildren().add(smallButton("    Preguntas frecuentes", "preguntas"));
        popupHamb.getChildren().add(smallButton("    Estado de mi pedido", "estado"));
        popupHamb.getChildren().add(smallButton("    Devoluciones", "devoluciones"));
        popupHamb.getChildren().add(smallButton("    Envios", "envios"));

    }
 public static void multiFiltrar(String[] mult) throws IOException {
        Main.filtros.clear();
        for (String i : mult) {
            filtrar(i, "a");
        }
    }

    public static void filtrar(String word, String mult) throws IOException {
        Boolean esta = false;
        if (mult.isEmpty()) {
            Main.filtros.clear();
        }
        for (String i : Main.filtros) {
            if (word.equals(i)) {
                Main.resultados.remove(i);
                esta = true;
                break;
            }
        }
        if (!esta) {
            Main.filtros.add(word);
        } else {
            Main.filtros.remove(word);
        }
        if (menued) {
            popupHambShow();
        }
        App.setRoot("main");
    }

    public static void popupHambShow() {
        if (menued) {
            final Timeline timeline = new Timeline();
            timeline.setCycleCount(1);
            final KeyValue kv = new KeyValue(popupHamb.layoutXProperty(), -500);
            final KeyFrame kf = new KeyFrame(Duration.millis(500), kv);
            timeline.getKeyFrames().add(kf);
            timeline.play();
            menuShadow.setOpacity(0);
            menued = false;
        } else {
            final Timeline timeline = new Timeline();
            timeline.setCycleCount(1);
            final KeyValue kv = new KeyValue(popupHamb.layoutXProperty(), 0);
            final KeyFrame kf = new KeyFrame(Duration.millis(500), kv);
            timeline.getKeyFrames().add(kf);
            timeline.play();
            menuShadow.setOpacity(0.2);
            popupHamb.setLayoutX(0);
            menued = true;
        }
    }

}