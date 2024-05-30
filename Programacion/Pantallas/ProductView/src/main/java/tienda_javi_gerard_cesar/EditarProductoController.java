package tienda_javi_gerard_cesar;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import tienda_javi_gerard_cesar.Clases.*;

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
    private TextField talla_zapatosProd;

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

    public static int getCurrent() {
        return current;
    }

    public static void setCurrent(int current) {
        EditarProductoController.current = current;
    }

    @FXML
    private Button imagenProd;

    private String setSQL(String tab, String col, String dat) {
        if (dat == null) {
            return "UPDATE " + tab + " SET " + col + " = null WHERE cod_art = " + current;
        }
        if (col.equals("material")) {
            
            return "UPDATE "+tab+" SET "+col+" = (select codigo from material where codigo = "+dat+" ) WHERE cod_art = "+current;
        }
        if (col.equals("activo") || col.equals("precio") || col.equals("material") || col.equals("estampada")
                || col.equals("tien_bolsillos") || col.equals("personalizado") || col.equals("capacidad")
                || col.equals("talla") && dat.matches("\\d{2}")) {
            return "UPDATE " + tab + " SET " + col + " = " + dat + " WHERE cod_art = " + current;
        } else {
            return "UPDATE " + tab + " SET " + col + " = '" + dat + "' WHERE cod_art = " + current;
        }
    }

    @FXML
    private void guardarCambios() {

        ConexionSQL con = new ConexionSQL();

        int material_num = 0;

        switch (materialProd.getValue().substring(4, materialProd.getValue().length())) {
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
        }

        try {
            Connection connection1 = con.conecta();
            Statement st = connection1.createStatement();
            String[] artcols = { "nombre", "precio", "marca", "descripcion", "activo", "imagen", "material" };
            String[] datcol = { nombreProd.getText(), precioProd.getText(), marcaProd.getText(),
                    descripcionProd.getText(), String.valueOf(activoProd.isSelected()), imagenProd.getText(),
                    String.valueOf(material_num) };
            for (int i = 0; i < artcols.length; i++) {

                 st.executeUpdate(setSQL("articulo", artcols[i], datcol[i])); 

            }

            String[] ropacol = { "talla", "color", "tipo_cierre", "impermeable", "tipo_manga", "estampada",
                    "tipo_pantalon", "tien_bolsillos", "tipo_ropa" };
            String[] acccol = { "estilo", "personalizado", "tipo_cierre", "capacidad", "tipo_suela", "talla",
                    "tipo_accesorio" };
            switch (tipoArticuloProd.getSelectionModel().getSelectedItem()) {
                case "Camisa":

                    String[] specdat = { tallaProd.getText(), colorProd.getText(), tipo_cierreProd.getText(), null,
                            tipo_mangaProd.getText(), String.valueOf(estampadaProd.isSelected()), null, null,
                            tipoArticuloProd.getSelectionModel().getSelectedItem() };
                    for (int i = 0; i < ropacol.length; i++) {
                        st.executeUpdate(setSQL("ropa", ropacol[i], specdat[i]));
                    }
                    break;

                case "Chaqueta":

                    String[] specdat2 = { tallaProd.getText(), colorProd.getText(), tipo_cierreProd.getText(),
                            String.valueOf(impermeableProd.isSelected()), null, null, null, null,
                            tipoArticuloProd.getSelectionModel().getSelectedItem() };
                    for (int i = 0; i < ropacol.length; i++) {
                        st.executeUpdate(setSQL("ropa", ropacol[i], specdat2[i]));
                    }
                    break;

                case "Pantalon":

                    String[] specdat3 = { tallaProd.getText(), colorProd.getText(), tipo_cierreProd.getText(), null,
                            null, null, tipo_pantalonProd.getText(), String.valueOf(tien_bolsillosProd.isSelected()),
                            tipoArticuloProd.getSelectionModel().getSelectedItem() };
                    for (int i = 0; i < ropacol.length; i++) {
                        st.executeUpdate(setSQL("ropa", ropacol[i], specdat3[i]));
                    }
                    break;

                case "Bolso":

                    String[] specdat4 = { estiloProd.getText(), String.valueOf(personalizadoProd.isSelected()),
                            tipo_cierreProd.getText(), capacidadProd.getText(), null, null,
                            tipoArticuloProd.getSelectionModel().getSelectedItem() };
                    for (int i = 0; i < acccol.length; i++) {
                        st.executeUpdate(setSQL("ropa", ropacol[i], specdat4[i]));
                    }

                    break;

                case "Zapatos":

                    String[] specdat5 = { estiloProd.getText(), String.valueOf(personalizadoProd.isSelected()),
                            tipo_cierreProd.getText(), null, tipo_suelaProd.getText(), talla_zapatosProd.getText(),
                            tipoArticuloProd.getSelectionModel().getSelectedItem() };
                    for (int i = 0; i < acccol.length; i++) {
                        st.executeUpdate(setSQL("ropa", ropacol[i], specdat5[i]));
                    }

                    break;
            }

            Alertas.productoInsertadoCorrectamente();
            con.cerrarConexion();

        } catch (SQLException ex) {
            ex.printStackTrace();
            Logs.createSQLLog(ex);
            Alertas.errorInsertarProducto();
        }
    }

    @FXML
    private ChoiceBox<String> materialProd;
    private String[] opciones_material = { "Algodón", "Poliéster", "Seda", "Cuero", "Lana", "Pana", "Franela", "Lino",
            "Cachemira", "Terciopelo" };

    @FXML
    private ChoiceBox<String> tipoArticuloProd;

    private String[] opciones_articulo = { "Camisa", "Chaqueta", "Pantalon", "Bolso", "Zapatos" };

    public static int current = 5;

    /* */

    private boolean esRopa() {
        Connection con = conenct();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT cod_art FROM ropa");
            while (rs.next()) {
                if (rs.getInt("cod_art") == current) {
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    @FXML
    private void initialize() {

        Connection connection = conenct();

        int material_num = 0;

        /*
         * tipoArticuloProd.setOnAction(e -> {
         * try {
         * Statement st69 = connection.createStatement();
         * ResultSet rs69 =
         * st69.executeQuery("SELECT cod_art FROM ropa where nombre = '"
         * +tipoArticuloProd.getSelectionModel().getSelectedItem()+"'");
         * current = rs69.getInt("cod_art");
         * } catch (SQLException ex) {
         * ex.printStackTrace();
         * }
         * });
         */

        try {

            /* ESTO ES PARA EL CHOICE BOX DE MATERIAL */

            ArrayList<Integer> material = new ArrayList<>();
            Statement st0 = connection.createStatement();
            ResultSet rs0 = st0.executeQuery("SELECT * FROM material");
            while (rs0.next()) {
                materialProd.getItems().add(rs0.getString("codigo") + ".- " + rs0.getString("denominacion"));
            }

            /* */

            /* ESTO ES PARA EL CHOICE BOX DE TIPO DE PRODUCTO */

            /* */

            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM articulo WHERE cod_art = '" + current + "'");

            while (rs.next()) {

                nombreProd.setText(rs.getString("nombre"));
                marcaProd.setText(rs.getString("marca"));
                descripcionProd.setText(rs.getString("descripcion"));
                precioProd.setText(rs.getString("precio"));
                imagenProd.setText(rs.getString("imagen"));
                nombre_imagenProd.setText(rs.getString("imagen"));
                activoProd.selectedProperty().set(rs.getBoolean("activo"));

                Statement st1 = connection.createStatement();
                ResultSet rs1 = st1.executeQuery("SELECT * FROM material WHERE codigo = " + rs.getInt("material"));
                while (rs1.next()) {
                    materialProd.getSelectionModel()
                            .select(rs1.getString("codigo") + ".- " + rs1.getString("denominacion"));
                }

                ArrayList<Integer> dpt = new ArrayList<>();

                tipoArticuloProd.getItems().add("Camisa");
                tipoArticuloProd.getItems().add("Pantalon");
                tipoArticuloProd.getItems().add("Chaqueta");
                tipoArticuloProd.getItems().add("Bolso");
                tipoArticuloProd.getItems().add("Zapatos");
            }

            /* */

            if (esRopa()) {
                Statement st22 = connection.createStatement();
                ResultSet rs22 = st22.executeQuery("SELECT * FROM ropa WHERE cod_art = " + current);
                while (rs22.next()) {
                    switch (rs22.getString("tipo_ropa")) {
                        case "Camisa":
                            tipoArticuloProd.getSelectionModel().select(rs22.getString("tipo_ropa"));
                            tallaProd.setText(rs22.getString("talla"));
                            colorProd.setText(rs22.getString("color"));
                            tipo_cierreProd.setText(rs22.getString("tipo_cierre"));
                            tipo_mangaProd.setText(rs22.getString("tipo_manga"));
                            estampadaProd.setText(rs22.getString("estampada"));
                            break;
                        case "Chaqueta":
                            tipoArticuloProd.getSelectionModel().select(rs22.getString("tipo_ropa"));
                            tallaProd.setText(rs22.getString("talla"));
                            colorProd.setText(rs22.getString("color"));
                            tipo_cierreProd.setText(rs22.getString("tipo_cierre"));
                            impermeableProd.selectedProperty().set(rs22.getBoolean("impermeable"));
                            break;
                        case "Pantalon":
                            tipoArticuloProd.getSelectionModel().select(rs22.getString("tipo_ropa"));
                            tallaProd.setText(rs22.getString("talla"));
                            colorProd.setText(rs22.getString("color"));
                            tipo_cierreProd.setText(rs22.getString("tipo_cierre"));
                            tien_bolsillosProd.selectedProperty().set(rs22.getBoolean("tien_bolsillos"));
                            tipo_pantalonProd.setText(rs22.getString("tipo_pantalon"));
                            break;
                        default:
                            break;
                    }
                }
            } else {
                Statement st22 = connection.createStatement();
                ResultSet rs22 = st22.executeQuery("SELECT * FROM accesorio WHERE cod_art = " + current);
                while (rs22.next()) {
                    switch (rs22.getString("tipo_accesorio")) {
                        case "Bolso":
                            tipoArticuloProd.getSelectionModel().select(rs22.getString("tipo_accesorio"));
                            estiloProd.setText(rs22.getString("estilo"));
                            personalizadoProd.selectedProperty().set(rs22.getBoolean("personalizado"));
                            tipo_cierreProd.setText(rs22.getString("tipo_cierre"));
                            capacidadProd.setText(rs22.getString("capacidad"));
                            break;
                        case "Zapatos":
                            tipoArticuloProd.getSelectionModel().select(rs22.getString("tipo_accesorio"));
                            estiloProd.setText(rs22.getString("estilo"));
                            personalizadoProd.selectedProperty().set(rs22.getBoolean("personalizado"));
                            tipo_suelaProd.setText(rs22.getString("tipo_suela"));
                            tallaProd.setText(rs22.getString("talla"));
                            break;
                        default:
                            break;
                    }
                }
            }
            opcionesArticulo();

            switch (materialProd.getSelectionModel().getSelectedItem()) {
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
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            Alertas.error("ERROR AL MOSTRAR EL PRODUCTO");
        }

        tipoArticuloProd.setOnAction(e -> opcionesArticulo());

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

    @FXML
    private void opcionesArticulo() {

        /* Ropa */
        vbox_talla.setVisible(false);
        vbox_color.setVisible(false);
        vbox_tipoCierre.setVisible(false);
        vbox_impermeable.setVisible(false);
        vbox_tipoManga.setVisible(false);
        vbox_estampada.setVisible(false);
        vbox_tipoPantalon.setVisible(false);
        vbox_tieneBolsillo.setVisible(false);

        /* Accesorios */
        vbox_estilo.setVisible(false);
        vbox_personalizado.setVisible(false);
        vbox_tipoCierre.setVisible(false);
        vbox_capacidad.setVisible(false);
        vbox_tipoSuela.setVisible(false);
        vbox_tallaZapatos.setVisible(false);

        if (tipoArticuloProd.getValue() == "Camisa" || tipoArticuloProd.getValue() == "Chaqueta"
                || tipoArticuloProd.getValue() == "Pantalon") {
            vbox_talla.setVisible(true);
            vbox_color.setVisible(true);
            vbox_tipoCierre.setVisible(true);
        } else {
            vbox_estilo.setVisible(true);
            vbox_personalizado.setVisible(true);
        }

        switch (tipoArticuloProd.getValue()) {
            case "Camisa":
                vbox_tipoManga.setVisible(true);
                vbox_estampada.setVisible(true);
                break;

            case "Chaqueta":
                vbox_impermeable.setVisible(true);
                break;

            case "Pantalon":
                vbox_tipoPantalon.setVisible(true);
                vbox_tieneBolsillo.setVisible(true);
                break;

            case "Bolso":
                vbox_capacidad.setVisible(true);
                vbox_tipoCierre.setVisible(true);
                break;

            case "Zapatos":
                vbox_tipoSuela.setVisible(true);
                vbox_tallaZapatos.setVisible(true);
                break;
        }

    }

    private Connection conenct() {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:4000/tienda_ropa", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

}
