package panel_admin;

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


/**
 * JavaFX App
 */
public class App extends Application {
    private Stage stagePrincipal;
    private Parent root;



   @Override
    public void start(Stage stagePrincipal) throws Exception {
       this.stagePrincipal = stagePrincipal;
       mostrarVentana_panelAdmin();
   }

    private void mostrarVentana_panelAdmin() {
       this.stagePrincipal.setTitle("Panel de Administración");
        try {
            root = FXMLLoader.load(getClass().getResource("panel_administración_Redimensionada_Cesar_Javi_Gerard.fxml")); //carga la vista
            Scene scene = new Scene(root); //crea escena
            stagePrincipal.setScene(scene); //pone la escena en el escenario
            stagePrincipal.show(); //muestra el escenario
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }



    public void mostrarVentana_altaProductos(ActionEvent event, String ruta) {
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
        launch();
    }

}