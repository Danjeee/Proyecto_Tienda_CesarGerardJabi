
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.util.logging.Logger;


public class Ventanas extends Application{
    private Stage stagePrincipal;
    private Parent root;



   @Override
    public void start(Stage stagePrincipal) throws Exception {
       this.stagePrincipal = stagePrincipal;
       mostrarVentanaPrincipal();
   }

    private void mostrarVentanaPrincipal() {
       this.stagePrincipal.setTitle("BBDD Ver Articulos ");
        try {
            root = FXMLLoader.load(getClass().getResource("FXML_Articulos.fxml")); //carga la vista
            Scene scene = new Scene(root); //crea escena
            stagePrincipal.setScene(scene); //pone la escena en el escenario
            stagePrincipal.show(); //muestra el escenario
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }



    public void mostrarVentanaSecundaria(ActionEvent event, String ruta) {
        try {

            Object eventSource = event.getSource();
            Node sourceAsNode = (Node) eventSource;
            Scene oldScene = sourceAsNode.getScene();
            Window window = oldScene.getWindow();
            Stage stage = (Stage) window;
            stage.hide();

            Parent root;
            root = FXMLLoader.load(getClass().getResource(ruta));
            Scene scene = new Scene(root);
            Stage newStage = new Stage();
            newStage.setScene(scene);
            newStage.show();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args); //lama al método start()
    }
    
}

