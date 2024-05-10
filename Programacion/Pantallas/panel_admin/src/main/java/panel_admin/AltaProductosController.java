package panel_admin;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import panel_admin.ConexionSQL;
import panel_admin.Clases.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import panel_admin.Clases.Articulo;
import panel_admin.MenuHamburguesa.MenuHamb;

public class AltaProductosController {

    @FXML
    private AnchorPane cont;

    @FXML
    private TextField nombre;
    @FXML
    private TextField precio;
    @FXML
    private TextField marca;
    @FXML
    private TextArea descripcion;
    @FXML
    private CheckBox activo;
    @FXML
    private TextField nombre_imagen;
    @FXML
    private ChoiceBox<Integer> material;
    private Integer[] opciones = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    @FXML
    private Button imagen;


    @FXML
    private App PantallaPrincipal = new App();

    @FXML
    public void retroceder_PanelAdmin(ActionEvent actionEvent) throws IOException {
        App.setRoot("PanelAdministracion_Cesar_Javi_Gerard");
    }

    @FXML
    private void guardarCambios() {

        ConexionSQL con = new ConexionSQL();

        Alert alerta = new Alert(Alert.AlertType.NONE);

            try {
                Connection connection1 = con.conecta();
                Statement st = connection1.createStatement();

                st.executeUpdate("INSERT INTO articulo(nombre, precio, marca, descripcion, activo, imagen, material) VALUES "
                + "('" + nombre.getText() + "'," + precio.getText() + ",'" + marca.getText() + "','" + descripcion.getText() + "'," + activo.isSelected() + ",'" + nombre_imagen.getText() + "'," + material.getValue() + ")");
                
                alerta.setAlertType(Alert.AlertType.INFORMATION);
                alerta.setHeaderText(null);
                alerta.setContentText("El artículo se ha insertado CORRECTAMENTE.");
                alerta.show();
                
                con.cerrarConexion();

            } catch (Exception ex) {
                System.out.println(ex.getClass());
                alerta.setAlertType(Alert.AlertType.ERROR);
                alerta.setHeaderText(null);
                alerta.setContentText("Error al insertar Articulo en la BBDD.");
                alerta.show();
            }
    }

    public void abrirImagen(ActionEvent e){
        
        Stage stage = (Stage) nombre.getScene().getWindow();


        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar una imagen");
        fileChooser.setInitialDirectory(new File("C:\\Users\\CicloM\\Downloads"));

        File imagenSeleccionada = fileChooser.showOpenDialog(stage);

        String nombreImag = imagenSeleccionada.getName();
        nombre_imagen.setText(nombreImag);
        
        ImageView imageView = new ImageView(getClass().getResource("C:\\Users\\CicloM\\Downloads\\crocs.jpg").toExternalForm());
        imagen.setGraphic(imageView);

       

        }

    public void initialize() {
        MenuHamb.popupHambMake();
        cont.getChildren().add(MenuHamb.menuShadow);
        cont.getChildren().add(MenuHamb.popupHamb);
        cont.getChildren().add(MenuHamb.menuHamb());

        material.getItems().addAll(opciones);
    }
}