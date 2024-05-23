package panel_admin;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import panel_admin.Clases.ImportantGUI;
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
    private TextField talla;

    @FXML
    private TextField talla_zapatos;

    @FXML
    private TextField color;

    @FXML
    private TextField tipo_manga;

    @FXML
    private TextField estilo;

    @FXML
    private TextField tipo_pantalon;

    @FXML
    private TextField tipo_suela;

    @FXML
    private TextField tipo_cierre;

    @FXML
    private CheckBox tien_bolsillos;

    @FXML
    private TextField capacidad;

    @FXML
    private CheckBox estampada;

    @FXML
    private CheckBox impermeable;

    @FXML
    private CheckBox personalizado;

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
    private ChoiceBox<String> material;
    private String[] opciones_material = {"Algodón","Poliéster", "Seda", "Cuero", "Lana", "Pana", "Franela", "Lino", "Cachemira", "Terciopelo"};

    @FXML
    private ChoiceBox<String> tipoArticulo;
    private String[] opciones_articulo = {"Camisa","Chaqueta", "Pantalón", "Bolso", "Zapatos"};

    @FXML
    private Button imagen;

    @FXML
    private App PantallaPrincipal = new App();

    @FXML
    public void retroceder_PanelAdmin(ActionEvent actionEvent) throws IOException {
        App.setRoot("PanelAdministracion_Cesar_Javi_Gerard");
    }

    static Alert alerta = new Alert(Alert.AlertType.NONE);

    @FXML
    private void guardarCambios() {

        ConexionSQL con = new ConexionSQL();

        int material_num = 0;

            switch (material.getValue()) {
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
                default:
                    break;
            }

            try {
                Connection connection1 = con.conecta();
                Statement st = connection1.createStatement();

                st.executeUpdate("INSERT INTO articulo(nombre, precio, marca, descripcion, activo, imagen, material) VALUES "
                + "('" + nombre.getText() + "'," + precio.getText() + ",'" + marca.getText() + "','" + descripcion.getText() + "'," + activo.isSelected() + ",'" + nombre_imagen.getText() + "'," + material_num + ")");
                
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
        
        Stage stage = (Stage) imagen.getScene().getWindow();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar una imagen");
        fileChooser.setInitialDirectory(new File("C:\\Users\\CicloM\\Downloads"));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Imágenes", "*.jpg", "*.png"));
        
        File imagenSeleccionada = fileChooser.showOpenDialog(stage);

        String nombreImag = imagenSeleccionada.getName();
        nombre_imagen.setText(nombreImag);
   
        Image imagen2 = new Image(imagenSeleccionada.toURI().toString());
        
        ImageView imageView = new ImageView(imagen2);
        imageView.setFitWidth(350);
        imageView.setFitHeight(200);

        imagen.setGraphic(imageView);

    }

    @FXML
    private void opcionesArticulo() {
        
        /*Ropa*/
        vbox_talla.setVisible(false);
        vbox_color.setVisible(false);
        vbox_tipoCierre.setVisible(false);
        vbox_impermeable.setVisible(false);
        vbox_tipoManga.setVisible(false);
        vbox_estampada.setVisible(false);
        vbox_tipoPantalon.setVisible(false);
        vbox_tieneBolsillo.setVisible(false);

        /*Accesorios*/
        vbox_estilo.setVisible(false);
        vbox_personalizado.setVisible(false);
        vbox_tipoCierre.setVisible(false);
        vbox_capacidad.setVisible(false);
        vbox_tipoSuela.setVisible(false);
        vbox_tallaZapatos.setVisible(false);

        if (tipoArticulo.getValue() == "Camisa" || tipoArticulo.getValue() == "Chaqueta" || tipoArticulo.getValue() == "Pantalón" ){
            vbox_talla.setVisible(true);
            vbox_color.setVisible(true);
            vbox_tipoCierre.setVisible(true);
        }else{
            vbox_estilo.setVisible(true);
            vbox_personalizado.setVisible(true);
            vbox_tipoCierre.setVisible(true);
        }

        switch (tipoArticulo.getValue()) {
            case "Camisa":
                vbox_tipoManga.setVisible(true);
                vbox_estampada.setVisible(true);
                break;

            case "Chaqueta":
                vbox_impermeable.setVisible(true);
                break;

            case "Pantalón":
                vbox_tipoPantalon.setVisible(true);
                vbox_tieneBolsillo.setVisible(true);
                break;

            case "Bolso":
                vbox_capacidad.setVisible(true);
                break;

            case "Zapatos":
                vbox_tipoSuela.setVisible(true);
                vbox_tallaZapatos.setVisible(true);
                break;
                
            default:
                break;
        }
    }

    public void initialize() {
        MenuHamb.popupHambMake();
        cont.getChildren().add(MenuHamb.menuShadow);
        cont.getChildren().add(MenuHamb.popupHamb);
        cont.getChildren().add(MenuHamb.menuHamb());
        all.getChildren().add(0,ImportantGUI.generateHeader());
        all.getChildren().add(ImportantGUI.generateFooter());
        tipoArticulo.getSelectionModel().select("Bolso");;
        tipoArticulo.setOnAction(e -> opcionesArticulo());

        material.getItems().addAll(opciones_material);
        tipoArticulo.getItems().addAll(opciones_articulo);

       /*Ropa*/
       vbox_talla.setVisible(false);
       vbox_color.setVisible(false);
       vbox_tipoCierre.setVisible(false);
       vbox_impermeable.setVisible(false);
       vbox_tipoManga.setVisible(false);
       vbox_estampada.setVisible(false);
       vbox_tipoPantalon.setVisible(false);
       vbox_tieneBolsillo.setVisible(false);

       /*Accesorios*/
       vbox_estilo.setVisible(false);
       vbox_personalizado.setVisible(false);
       vbox_tipoCierre.setVisible(false);
       vbox_capacidad.setVisible(false);
       vbox_tipoSuela.setVisible(false);
       vbox_tallaZapatos.setVisible(false);
    }
}