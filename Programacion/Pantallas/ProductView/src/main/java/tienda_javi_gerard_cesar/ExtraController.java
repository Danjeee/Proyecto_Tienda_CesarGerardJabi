package tienda_javi_gerard_cesar;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import tienda_javi_gerard_cesar.Clases.ImportantGUI;

public class ExtraController {
    @FXML
    private VBox all;
    @FXML
    private AnchorPane cont;
    public void initialize(){
        all.getChildren().add(0, ImportantGUI.generateHeader());
        all.getChildren().add(ImportantGUI.generateFooter());
        cont.getChildren().add(ImportantGUI.defaultBack());
    }
}
