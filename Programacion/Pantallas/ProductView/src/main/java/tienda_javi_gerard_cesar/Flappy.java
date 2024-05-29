package tienda_javi_gerard_cesar;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ConcurrentModificationException;
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
import tienda_javi_gerard_cesar.Clases.Descuento;

public class Flappy {

    @FXML
    private ImageView ber;

    @FXML
    private ImageView pipe;
    @FXML
    private AnchorPane fondo;

    private int pipespeed = 30;
    private String newcode;
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

    // Hacer que el bicho salte al pulsar espacio
    @FXML
    void pressed(KeyEvent event) {
        if (event.getCode() == KeyCode.SPACE) {
            fly();
        }
    }

    // Funcion para hacer que salte (Cambia su posicion en y en base a una fuerza
    // llamada jumpHeight)
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
        // Loop de juego
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long l) {
                Scene scene = ber.getScene();
                scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

                    @Override
                    public void handle(KeyEvent arg0) {
                        if (arg0.getCode() == KeyCode.SPACE) {
                            fly();
                        }
                    }

                });
                update();

            }
        };
        gameLoop.start();
    }

    /*
     * Creación de las tuberías
     * Como funciona:
     * 1.- Crea 2 VBoxes (Tuberias) y un Pane (Espacio) dentro de una VBox
     * 2.- Inserta las VBoxes y el Pane en la VBox principal y le asigna una
     * posición aleatoria en Y que a su vez está limitada para que el Pane (Espacio)
     * siempre se encuentre dentro de la pantalla
     * 3.- Devuelve la VBox principal
     */
    private VBox newPipe() throws FileNotFoundException {
        VBox cont = new VBox();
        cont.setPrefWidth(100);
        cont.setPrefHeight(3072);
        FileInputStream pipedownimgfile = new FileInputStream(
                "Programacion\\Pantallas\\ProductView\\src\\main\\resources\\tienda_javi_gerard_cesar\\pipe_down.png");
        Image pipedownimg = new Image(pipedownimgfile);
        ImageView pipedown = new ImageView(pipedownimg);
        Random rnd = new Random();
        pipedown.setFitHeight(1024);
        Pane fit = new Pane();
        fit.setStyle("-fx-background-color: rgba(0,0,0,0)");
        fit.setPrefHeight(rnd.nextInt(200) + 200);
        FileInputStream pipeupimgfile = new FileInputStream(
                "Programacion\\Pantallas\\ProductView\\src\\main\\resources\\tienda_javi_gerard_cesar\\pipe_up.png");
        Image pipeupimg = new Image(pipeupimgfile);
        ImageView pipeup = new ImageView(pipeupimg);
        pipedown.setFitHeight(1024);
        cont.getChildren().add(0, pipeup);
        cont.getChildren().add(1, fit);
        cont.getChildren().add(2, pipedown);
        return cont;
    }

    // Muestra la puntuacion
    private String textoPuntuacion() {
        return "Puntuación: " + puntuacion;
    }

    /*
     * Metodo para comprobar si te has chocado
     * Como funciona
     * 1.- Mira todos los hijos del fondo y comprueba si son VBox
     * 2.- Si son VBox comprueba su posición en X y si esta es menor a la posición
     * izquierda (Posicion del bicho + su width)
     * 3.- En caso de que esté dentro de la posición x del bicho, limita las
     * posiciones en las que puede estar el bicho a las coordenadas del pane
     * 4.- Si no hay ninguna VBox en esta posición, el bicho puede estar en toda la
     * pantalla
     */
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

    private void generarCodigo() {
        Random rnd = new Random();
        int len = rnd.nextInt(12)+6;
        String code = "";
        for (int i = 0; i < len; i++) {
            char a = (char)(rnd.nextInt(25) + 65);
            if (rnd.nextBoolean()) {
                code += (char)(a+32);
            } else {
                code += a;
            }
        }
        Connection con;
        try {
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:4000/tienda_ropa", "root", "");
            Statement stm = con.createStatement();
            stm.executeUpdate("Insert into descuentos(descuento, cant, freeShip, usable_por) values('"+code+"', 20, false, '"+App.getUser()+"')");
            newcode = code;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }

    /*
     * El metodo principal, se ejecuta cada vez que el procesador lee el game loop,
     * he denominado esta unidad de tiempo como javisegundos,
     * y se suma cada vez que se ejecuta esta funcion
     * Que hace
     * 1.- Lo primero, instancia un keyEvent en la escena que hace que el bicho
     * salte si le das al espacio, esto lo hace unicamente en el javisegundo 0
     * 2.- Después ejecuta el metodo que decide en que posiciones puede estar el
     * bicho, metodo ya visto anteriormente
     * 3.- Después suma 1 a la unidad de tiempo que se encarga de ver cuanto ha
     * pasado desde que se ha creado una tuberia
     * 4.- Si una tuberia se ha creado hace x tiempo (tiempo de spawn de tubería),
     * crea otra en el limite de X y vuelve a poner el valor a 0
     * 4.5.- La tuberia se crea con una posición Y aleatoria con limites friamente
     * calculados para que siempre haya un espacio por el que pasar
     * 5.- Aumenta la aceleración con la que cae el bicho (se resetea al saltar)
     * 6.- Mueve el bicho hacia abajo según la gravedad (yDelta) y la velocidad
     * (timeber) y las tuberias hacia la izquierda segun la velocidad de estas
     * 7.- Comprueba si el bicho va a morir en este frame o no y en caso de que si,
     * borra todo y crea el menu de muerte
     * 8.- Según la puntuación aumenta la velocidad de las tuberias y disminuye el
     * tiempo de aparicion de estas
     */
    private void update() {
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
        if (diff == 10) {
            if (pipespeed != 80) {
                pipespeed += 10;
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
            if (puntuacion >= 1) {
                generarCodigo();
            }
            fondo.getChildren().clear();
            gameLoop.stop();
            fondo.setStyle("-fx-background-color: #000");
            Label aña = new Label("Game Over");
            aña.setTextFill(Color.RED);
            aña.setFont(new Font("System", 72));
            fondo.getChildren().add(aña);
            aña.setLayoutX(512 - aña.getWidth());
            aña.setLayoutY(412);
            if (puntuacion >= 1) {
                Label cod = new Label("¡Enhorabuena, has llegado a 30 puntos, has obtenido un codigo de descuento! ("+newcode+")");
                fondo.getChildren().add(cod);
                cod.setFont(new Font("System", 40));
                cod.setTextFill(Color.WHITE);
                cod.setLayoutX(112);
                cod.setLayoutY(512);
                Cart.descuentoActivo = new Descuento(newcode, 20, false);
                Label info = new Label("Se aplicará automaticamente pero puedes guardarlo para mas tarde, es exclusivo para ti");
                info.setTextFill(Color.WHITE);
                fondo.getChildren().add(info);
                cod.setFont(new Font("System", 30));
                info.setLayoutX(462 - info.getWidth());
                info.setLayoutY(612);
            }
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

    // Cambia la posición del pajaro y la rotación
    private void moveBirdY(double positionChange) {
        if (ber.getY() + ber.getLayoutY() != 0) {
            ber.setY(ber.getY() + positionChange);
        } else {
            ber.setY(ber.getY() + 0.1);
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

    // Mueve todas las tuberias hacia la izquierda, si la tuberia ha pasado al
    // pajaro, cambia la posición en la que puede estar
    // el pajaro a toda la pantalla (en x) y si ha llegado al limite, desaparece

    private void movePipesX() {
        try {
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
        } catch (ConcurrentModificationException e) {
            System.out.print("");
        }
    }

    // Comprueba si el pajaro esta en una posicion correcta y si no lo está, muere
    private boolean isBirdDead() {
        double birdY = ber.getLayoutY() + ber.getY();
        if (ber.getBoundsInParent().getCenterY() < postofit[0] || ber.getBoundsInParent().getCenterY() > postofit[1]) {
            return true;
        }
        return birdY >= fondo.getHeight();
    }

    // Era una funcion para cuando no estaba el menú de muerte pero me hace gracia
    // que esté
    private void resetBird() {
        ber.setY(0);
        timeber = 0;
    }

}
