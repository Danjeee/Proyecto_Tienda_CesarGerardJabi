package tienda_javi_gerard_cesar;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;

public class Flappy {

    @FXML
    private ImageView ber;

    @FXML
    private ImageView pipe;
    @FXML
    private AnchorPane fondo;

    private int pipespeed = 30;
    AnimationTimer gameLoop;
    double[] postofit = { 0, 1024 };
    double yDelta = 0.2;
    double timeber = 0;
    double timepipe = 0;
    double javisegundos = 0;
    int jumpHeight = 50;
    int puntuacion = 0;
    int diff = 0;
    int pipespawn = 150;
    @FXML
    private Label puntShow;

    @FXML
    void pressed(KeyEvent event) {
        if (event.getCode() == KeyCode.SPACE) {
            fly();
        }
    }

    @FXML
    private void fly() {
        if (ber.getLayoutY() + ber.getY() <= jumpHeight) {
            moveBirdY(-(ber.getLayoutY() + ber.getY()));
            timeber = 0;
        }
        moveBirdY(-jumpHeight + (yDelta * timeber));
        timeber = 0;
    }

    @FXML
    public void initialize() {
        fondo.setOnKeyPressed(e -> pressed(e));
        ber.setStyle("-fx-background-color: #000");
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long l) {
                update();
            }
        };
        gameLoop.start();
    }

    private VBox newPipe() throws FileNotFoundException {
        VBox cont = new VBox();
        cont.setPrefWidth(100);
        cont.setPrefHeight(3072);
        FileInputStream pipedownimgfile = new FileInputStream(
                "C:\\Users\\alumnoM\\Documents\\GitHub\\Proyecto_Tienda_CesarGerardJabi\\Programacion\\Pantallas\\ProductView\\src\\main\\resources\\tienda_javi_gerard_cesar\\pipe_down.png");
        Image pipedownimg = new Image(pipedownimgfile);
        ImageView pipedown = new ImageView(pipedownimg);
        Random rnd = new Random();
        pipedown.setFitHeight(1024);
        Pane fit = new Pane();
        fit.setStyle("-fx-background-color: rgba(0,0,0,0)");
        fit.setPrefHeight(rnd.nextInt(200) + 200);
        FileInputStream pipeupimgfile = new FileInputStream(
                "C:\\Users\\alumnoM\\Documents\\GitHub\\Proyecto_Tienda_CesarGerardJabi\\Programacion\\Pantallas\\ProductView\\src\\main\\resources\\tienda_javi_gerard_cesar\\pipe_up.png");
        Image pipeupimg = new Image(pipeupimgfile);
        ImageView pipeup = new ImageView(pipeupimg);
        pipedown.setFitHeight(1024);
        cont.getChildren().add(0, pipeup);
        cont.getChildren().add(1, fit);
        cont.getChildren().add(2, pipedown);
        return cont;
    }

    private String textoPuntuacion() {
        return "Puntuación: " + puntuacion;
    }

    private double[] checkPiper() {
        double[] pos = new double[2];
        for (Node i : fondo.getChildren()) {
            if (i instanceof VBox) {
                if (i.getLayoutX() < 260) {
                    VBox ii = (VBox) i;
                    Pane fit = (Pane) ii.getChildren().get(1);
                    pos[0] = i.getLayoutY() + 1024;
                    pos[1] = pos[0] + fit.getHeight();
                }
            } else {
                pos[0] = 0;
                pos[1] = 1024;
            }
        }
        return pos;
    }

    private void update() {
        if (javisegundos == 0) {
            Scene scene = ber.getScene();
            scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

                @Override
                public void handle(KeyEvent arg0) {
                    if (arg0.getCode() == KeyCode.SPACE) {
                        fly();
                    }
                }

            });
        }
        postofit = checkPiper();
        timepipe++;
        if (timepipe >= pipespawn) {
            try {
                VBox a = newPipe();
                fondo.getChildren().add(a);
                Random rnd = new Random();
                a.setLayoutY(-(rnd.nextInt(800) + 200));
                a.setLayoutX(1340);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            timepipe = 0;
        }
        if (diff == 10){
            if (pipespeed != 80) {
                pipespeed+=10;
            }
            if (pipespawn != 90) {
                pipespawn -= 10;
            }
            diff = 0;
        }

        timeber++;
        javisegundos++;
        moveBirdY(yDelta * timeber);
        movePipesX();

        if (isBirdDead()) {
            fondo.getChildren().clear();
            gameLoop.stop();
            fondo.setStyle("-fx-background-color: #000");
            Label aña = new Label("Game Over");
            aña.setTextFill(Color.RED);
            aña.setFont(new Font("System", 72));
            fondo.getChildren().add(aña);
            aña.setLayoutX(512-aña.getWidth());
            aña.setLayoutY(412);
            Button ret = new Button("Return");
            ret.setPrefWidth(200);
            ret.setStyle("-fx-background-color: #fff");
            ret.setCursor(Cursor.HAND);
            ret.setLayoutX(450);
            ret.setLayoutY(700);
            ret.setFont(new Font("System", 32));
            ret.setOnAction(e -> {
                try {
                    App.setRoot("main");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            });
            Button trya = new Button("Try Again");
            trya.setPrefWidth(200);
            trya.setStyle("-fx-background-color: #fff");
            trya.setCursor(Cursor.HAND);
            trya.setLayoutX(750);
            trya.setLayoutY(700);
            trya.setFont(new Font("System", 32));
            trya.setOnAction(e -> {
                try {
                    App.setRoot("flappy");
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            });
            fondo.getChildren().addAll(ret, trya);
        }
    }

    private void moveBirdY(double positionChange) {
        if (ber.getY() + ber.getLayoutY() != 0) {
            ber.setY(ber.getY() + positionChange);
        } else {
            ber.setY(ber.getY()+0.1);
        }
        if (positionChange > 0) {
            if (ber.getRotate() <= 10) {
                ber.setRotate(ber.getRotate() + yDelta * timeber / 10);
            }
        } else {
            if (ber.getRotate() >= -10) {
                ber.setRotate(ber.getRotate() - 10);
            }
        }

    }

    private void movePipesX() {
        for (Node i : fondo.getChildren()) {
            if (i instanceof VBox) {
                i.setLayoutX(i.getLayoutX() - (yDelta * pipespeed));
                if (i.getLayoutX() < 0) {
                    double[] posini = { 0, 1024 };
                    postofit = posini;
                }
                if (i.getLayoutX() < -100) {
                    puntuacion++;
                    diff++;
                    puntShow.setText(textoPuntuacion());
                    fondo.getChildren().remove(3);
                }
            }
        }
    }

    private boolean isBirdDead() {
        double birdY = ber.getLayoutY() + ber.getY();
        if (ber.getBoundsInParent().getCenterY() < postofit[0] || ber.getBoundsInParent().getCenterY() > postofit[1]) {
            return true;
        }
        return birdY >= fondo.getHeight();
    }

    private void resetBird() {
        ber.setY(0);
        timeber = 0;
    }

}
