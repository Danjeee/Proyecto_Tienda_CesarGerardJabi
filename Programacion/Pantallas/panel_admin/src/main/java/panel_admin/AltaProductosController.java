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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
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
    private TextField tien_bolsillos;

    @FXML
    private TextField capacidad;

    @FXML
    private CheckBox estampada;

    @FXML
    private CheckBox impermeable;

    @FXML
    private CheckBox personalizado;


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

        talla.setVisible(false);
        color.setVisible(false);
        tipo_cierre.setVisible(false);
        impermeable.setVisible(false);
        tipo_manga.setVisible(false);
        estampada.setVisible(false);
        tipo_pantalon.setVisible(false);
        tien_bolsillos.setVisible(false);

        estilo.setVisible(false);
        personalizado.setVisible(false);
        tipo_cierre.setVisible(false);
        capacidad.setVisible(false);
        tipo_suela.setVisible(false);
        talla.setVisible(false);


        if (tipoArticulo.getValue() == "Camisa" || tipoArticulo.getValue() == "Chaqueta" || tipoArticulo.getValue() == "Pantalón" ){
            talla.setVisible(true);
            color.setVisible(true);
        
        }else{
            estilo.setVisible(true);
            personalizado.setVisible(true);
        }

        switch (tipoArticulo.getValue()) {
            case "Camisa":
                tipo_manga.setVisible(true);
                estampada.setVisible(true);
                break;

            case "Chaqueta":
                impermeable.setVisible(true);
                break;

            case "Pantalón":
                tipo_pantalon.setVisible(true);
                tien_bolsillos.setVisible(true);
                break;

            case "Bolso":
                tipo_cierre.setVisible(true);
                capacidad.setVisible(true);
                break;

            case "Zapatos":
                tipo_suela.setVisible(true);
                talla.setVisible(true);
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

        material.getItems().addAll(opciones_material);
        tipoArticulo.getItems().addAll(opciones_articulo);
    }
}