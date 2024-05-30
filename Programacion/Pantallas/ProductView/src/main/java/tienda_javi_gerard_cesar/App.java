package tienda_javi_gerard_cesar;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import tienda_javi_gerard_cesar.Clases.Logs;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * JavaFX App
 */
public class App extends Application {

    public static Scene scene;
    private static String[] last = { "Login", "0" };
    private static String currentRoot;

    public static String getLast() {
        return last[0];
    }

    public static String getCurrent() {
        return last[1];
    }

    public static String getRoot(){
        return currentRoot;
    }

    private static String user = "23456789A";

    public static void setUser(String a) {
        user = a;
    }

    public static String getUser() {
        return user;
    }
    public static String getResourcesPath(){
        File app = new File("Programacion\\Pantallas\\ProductView\\src\\main\\java\\tienda_javi_gerard_cesar\\App.java");
        String last = "java\\tienda_javi_gerard_cesar\\App.java";
        return app.getAbsolutePath().substring(0, app.getAbsolutePath().length()-last.length())+"resources\\";
    }

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("Login"), 1440, 1024);
        stage.setScene(scene);
        
        stage.setTitle("secondHand");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/tienda_javi_gerard_cesar/icon.jpg")));
        stage.show();
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

    public static boolean userIsAdmin() {
        Connection con = conenct();
        try {
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT DNI FROM empleado");
            while (rs.next()) {
                if (rs.getString("DNI").equals(App.getUser())) {
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            Logs.createSQLLog(e);
        }
        return false;
    }

    public static void setRoot(String fxml) throws IOException {
        reloadLast(fxml);
        currentRoot = fxml + ".fxml";
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

    private static void reloadLast(String current) {
        if (last[0].equals("0")) {
            last[0] = current;
        } else if (last[1].equals("0")) {
            last[1] = current;
        } else {
            last[0] = last[1];
            last[1] = current;
        }
    }

}