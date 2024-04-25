package productview;

import java.io.IOException;
import java.util.Collection;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;

public class SecondaryController {
    @FXML
    private GridPane baseGrid;
    @FXML
    private Button baseBotonImagen;
    @FXML
    private ImageView imagenBase;
    @FXML
    private Label tituloBase;
    @FXML
    private Label precioBase;
    @FXML
    private Button addBase;
    @FXML
    private Button cartBase;
    @FXML
    private FontAwesomeIconView plus;
    @FXML
    private FontAwesomeIconView cart;
    @FXML

    private FlowPane main;
    @FXML
    private void press() throws IOException{
        /*Button botonImagen = this.baseBotonImagen;
        newItem.add(botonImagen, 0, 0);*/
        main.getChildren().add(clonar());
    }
    private GridPane clonar(){
        GridPane a = new GridPane();
        ColumnConstraints c = new ColumnConstraints();
        c.setPrefWidth(200);
        RowConstraints r = new RowConstraints();
        Label l = new Label("caca");
        Label ll = new Label("caca");
        r.setPrefHeight(250);
        a.getColumnConstraints().add(c);
        a.getRowConstraints().add(r);
        a.getChildren().add(0, l);
        return a;
    }
}
