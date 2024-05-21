package tienda_javi_gerard_cesar;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    public static Scene scene;
    private static String[] last = {"0", "0"};

    public static String getLast() {
        return last[0];
    }
    public static String getCurrent(){
        return last[1];
    }

    public static String user = "23456789A";

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("cart"), 1440, 1024);
        stage.setScene(scene);
        stage.setTitle("secondHand");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/tienda_javi_gerard_cesar/icon.jpg")));
        stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        reloadLast(fxml);
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

    private static void reloadLast(String current){
        if (last[0].equals("0")) {
            last[0] = current;
        } else if (last[1].equals("0")){
            last[1] = current;
        } else {
            last[0] = last[1];
            last[1] = current;
        }
    }

}