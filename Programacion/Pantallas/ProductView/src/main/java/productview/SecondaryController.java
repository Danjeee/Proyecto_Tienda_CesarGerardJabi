package productview;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SecondaryController {
    @FXML
    private GridPane grid;
    @FXML
    private HBox main;
    @FXML
    private void press() throws IOException{
        GridPane a = grid;
        main.getChildren().add(a);
        
    }
    
}
