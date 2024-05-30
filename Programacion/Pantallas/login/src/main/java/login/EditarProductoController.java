package login;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import login.Clases.Alertas;

public class EditarProductoController {

    @FXML
    private App PantallaPrincipal = new App();

    @FXML
    private AnchorPane cont;

    @FXML
    private TextField nombreProd;

    @FXML
    private TextField precioProd;

    @FXML
    private TextField marcaProd;

    @FXML
    private TextArea descripcionProd;

    @FXML
    private CheckBox activoProd;

    @FXML
    private TextField nombre_imagenProd;

    @FXML
    private TextField tallaProd;

    @FXML
    private TextField talla_zapatos;

    @FXML
    private TextField colorProd;

    @FXML
    private TextField tipo_mangaProd;

    @FXML
    private TextField estiloProd;

    @FXML
    private TextField tipo_pantalonProd;

    @FXML
    private TextField tipo_suelaProd;

    @FXML
    private TextField tipo_cierreProd;

    @FXML
    private CheckBox tien_bolsillosProd;

    @FXML
    private TextField capacidadProd;

    @FXML
    private CheckBox estampadaProd;

    @FXML
    private CheckBox impermeableProd;

    @FXML
    private CheckBox personalizadoProd;

    @FXML
    private VBox vbox_tipoCierre;

    @FXML
    private VBox vbox_tipoManga;

    @FXML
    private VBox vbox_impermeable;

    @FXML
    private VBox vbox_tipoPantalon;

    @FXML
    private VBox vbox_capacidad;

    @FXML
    private VBox vbox_tipoSuela;

    @FXML
    private VBox vbox_estampada;

    @FXML
    private VBox vbox_tieneBolsillo;

    @FXML
    private VBox vbox_talla;

    @FXML
    private VBox vbox_estilo;

    @FXML
    private VBox vbox_color;

    @FXML
    private VBox vbox_personalizado;

    @FXML
    private VBox vbox_tallaZapatos;

    @FXML
    private VBox all;

    @FXML
    private Button imagenProd;

    @FXML
    private ChoiceBox<String> materialProd;
    private String[] opciones_material = { "Algodón", "Poliéster", "Seda", "Cuero", "Lana", "Pana", "Franela", "Lino",
            "Cachemira", "Terciopelo" };

    @FXML
    private ChoiceBox<String> tipoArticuloProd;
    private String[] opciones_articulo = { "Camisa", "Chaqueta", "Pantalón", "Bolso", "Zapatos" };

    public static String current = "1";

    /* */

    @FXML
    private void initialize() {

        Connection connection = conenct();
/*
        int material_num = 0;

        switch (materialProd.getValue()) {
            case "Algodón":
                material_num = 1;
                break;
            case "Poliéster":
                material_num = 2;
                break;
            case "Seda":
                material_num = 3;
                break;
            case "Cuero":
                material_num = 4;
                break;
            case "Lana":
                material_num = 5;
                break;
            case "Pana":
                material_num = 6;
                break;
            case "Franela":
                material_num = 7;
                break;
            case "Lino":
                material_num = 8;
                break;
            case "Cachemira":
                material_num = 9;
                break;
            case "Terciopelo":
                material_num = 10;
                break;
        } */

        try {

            /*ESTO ES PARA EL CHOICE BOX DE MATERIAL */
            
            ArrayList<Integer> material = new ArrayList<>();
            Statement st0 = connection.createStatement();
            ResultSet rs0 = st0.executeQuery("SELECT * FROM material");
            while (rs0.next()) {
                materialProd.getItems().add(rs0.getString("codigo") + ".- " + rs0.getString("denominacion"));
            }

            /* */

            /*ESTO ES PARA EL CHOICE BOX DE TIPO DE PRODUCTO */
            
            ArrayList<Integer> tipoProd = new ArrayList<>();
            Statement st3 = connection.createStatement();
            ResultSet rs3 = st3.executeQuery("SELECT * FROM ropa");
            while (rs0.next()) {
                tipoArticuloProd.getItems().add(rs0.getString("cod_art") + ".- " + rs0.getString("tipo_ropa"));
            }

            /* */

            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM articulo WHERE cod_art = '" + current + "'");

            while (rs.next()) {
                while (rs.next()) {

                    nombreProd.setText(rs.getString("nombre"));
                    marcaProd.setText(rs.getString("marca"));
                    descripcionProd.setText(rs.getString("descripcion"));
                    precioProd.setText(rs.getString("precio"));
                    imagenProd.setText(rs.getString("imagen"));
                    activoProd.selectedProperty().set(rs.getBoolean("activo"));

                    Statement st1 = connection.createStatement();
                    ResultSet rs1 = st1.executeQuery("SELECT * FROM material WHERE codigo = " + rs.getInt("materialProd"));
                    while (rs1.next()) {
                        materialProd.getSelectionModel().select(rs1.getString("codigo") + ".- " + rs1.getString("denominacion"));
                    }

                    Statement st4 = connection.createStatement();
                    ResultSet rs4 = st4.executeQuery("SELECT * FROM ropa WHERE codigo = " + rs.getInt("tipoArticuloProd"));
                    while (rs1.next()) {
                        tipoArticuloProd.getSelectionModel().select(rs1.getString("codigo") + ".- " + rs1.getString("tipo_ropa"));
                    }
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            Alertas.error("ERROR AL MOSTRAR EL PRODUCTO");
        }
    }

    public void abrirImagen(ActionEvent e) {

        Stage stage = (Stage) imagenProd.getScene().getWindow();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar una imagen");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Imágenes", "*.jpg", "*.png"));

        File imagenSeleccionada = fileChooser.showOpenDialog(stage);

        String nombreImag = imagenSeleccionada.getName();
        nombre_imagenProd.setText(nombreImag);

        Image imagen2 = new Image(imagenSeleccionada.toURI().toString());

        ImageView imageView = new ImageView(imagen2);
        imageView.setFitWidth(350);
        imageView.setFitHeight(200);

        imagenProd.setGraphic(imageView);

    }

    private Connection conenct() {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/tienda_ropa", "root", "mysql23");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

}
