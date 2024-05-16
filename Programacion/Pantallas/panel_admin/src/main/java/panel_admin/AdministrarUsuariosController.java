package panel_admin;


import java.io.IOException;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import panel_admin.Clases.Empleado;
import panel_admin.MenuHamburguesa.MenuHamb;

public class AdministrarUsuariosController {

    @FXML
    private AnchorPane cont;

    @FXML
    private App PantallaPrincipal = new App();

    @FXML
    public void retroceder_PanelAdmin(ActionEvent actionEvent) throws IOException {
        App.setRoot("PanelAdministracion_Cesar_Javi_Gerard");
    }
    
    public void initialize() {
        MenuHamb.popupHambMake();
        cont.getChildren().add(MenuHamb.menuShadow);
        cont.getChildren().add(MenuHamb.popupHamb);
        cont.getChildren().add(MenuHamb.menuHamb());
    }

    
    private HBox crearTarjetaEmpleado(Empleado empleado) {


        HBox tarjetaEmpleado = new HBox();
        tarjetaEmpleado.setPrefHeight(60.0);
        tarjetaEmpleado.setPrefWidth(677.0);
        tarjetaEmpleado.setAlignment(Pos.CENTER_LEFT);
        tarjetaEmpleado.setSpacing(10);


        FontAwesomeIconView iconoView = new FontAwesomeIconView();
        iconoView.setGlyphName("USER");
        iconoView.setSize("28");
        HBox.setMargin(iconoView, new Insets(12, 20, 40, 100)); //top, right, bottom, left

        TextField textField = new TextField(empleado.getNombre() + " " + empleado.getApellidos());
        textField.setEditable(false);
        textField.setStyle("-fx-background-color: #E9E9E9; -fx-font-size: 22px; -fx-font-weight: bold");
        textField.setPrefHeight(25.0);
        textField.setPrefWidth(946.0);
        HBox.setMargin(textField, new Insets(12, 40, 40, 20));

        FontAwesomeIconView papelera = new FontAwesomeIconView();
        papelera.setGlyphName("TRASH");
        papelera.setSize("25");
        papelera.setOnMouseClicked(mouseEvent -> mostrarAlertConfirmation(empleado));
        HBox.setMargin(papelera, new Insets(12, 10, 40, 20));

        FontAwesomeIconView lapiz = new FontAwesomeIconView();
        lapiz.setGlyphName("PENCIL");
        lapiz.setSize("25");
        HBox.setMargin(lapiz, new Insets(12, 10, 40, 20));

        tarjetaEmpleado.getChildren().addAll(iconoView, textField, lapiz, papelera);

        return tarjetaEmpleado;
    }

}

