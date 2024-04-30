package tienda_javi_gerard_cesar;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class ProductView {

    public static int current;
    @FXML
    private Pane imagen;
    @FXML
    private Label nom;

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }
    
    @FXML
    private Label where;
    @FXML
    private Label precio;
    @FXML
    private Text desc;
    @FXML
    private FlowPane atr;
    @FXML
    private ImageView img;

    private Connection conenct() {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:4000/tienda_ropa", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    private ArrayList<String> atributos(Connection con) {
        ArrayList<String> a = new ArrayList<>();
        try {
            Statement st = con.createStatement();
            Boolean esRopa = false;
            Boolean esArticulo = false;
            ResultSet art = st.executeQuery("SELECT cod_art FROM accesorio");
            while (art.next()) {
                if (ProductView.current == art.getInt("cod_art")) {
                    esArticulo = true;
                    break;
                }
            }
            ResultSet ropa = st.executeQuery("SELECT cod_art FROM ropa");
            while (ropa.next()) {
                if (ProductView.current == ropa.getInt("cod_art")) {
                    esRopa = true;
                    break;
                }
            }
            if (esArticulo) {
                Statement aa = con.createStatement();
                ResultSet atr1 = aa
                        .executeQuery("SELECT * FROM accesorio WHERE cod_art = " + ProductView.current);
                while (atr1.next()) {
                    String tipo = atr1.getString("tipo_accesorio");
                    a.add("Accesorio > "+tipo +" > ");
                    a.add(atr1.getString("estilo"));
                    a.add(atr1.getString("tipo_accesorio"));
                    if (tipo.equals("Zapatos")) {
                        a.add(atr1.getString("tipo_suela"));
                        a.add("Talla: " + atr1.getString("talla"));
                    }
                    if (tipo.equals("Bolso")) {
                        a.add(atr1.getString("tipo_cierre"));
                        a.add("Capacidad: " + atr1.getString("capacidad"));
                    }
                    Boolean per = atr1.getBoolean("personalizado");
                    if (per) {
                        a.add("Personalizado");
                    }
                }
            }
            if (esRopa) {
                Statement bb = con.createStatement();
                ResultSet atr1 = bb.executeQuery("SELECT * FROM ropa WHERE cod_art = " + ProductView.current);
                while (atr1.next()) {
                    String tipo = atr1.getString("tipo_ropa");
                    a.add("Ropa > "+tipo +" > ");
                    a.add(atr1.getString("color"));
                    a.add(atr1.getString("tipo_cierre"));
                    a.add("Talla: " + atr1.getString("talla"));
                    Boolean bol = atr1.getBoolean("tien_bolsillos");
                    if (tipo.equals("Pantalon")) {
                        a.add(atr1.getString("tipo_pantalon"));
                    }
                    if (tipo.equals("Camisa")) {
                        a.add(atr1.getString("tipo_manga"));
                    }
                    if (bol) {
                        a.add("Tiene bolsillos");
                    }
                    Boolean est = atr1.getBoolean("estampada");
                    if (est) {
                        a.add("Estampado");
                    }
                }
            }
            return a;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return a;
    }

    private String[] leer() {
        Connection con = conenct();
        int id = ProductView.current;
        String[] datos = new String[4];
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM articulo WHERE cod_art = " + id);
            while (rs.next()) {
                datos[0] = rs.getString("nombre");
                datos[1] = rs.getString("precio");
                datos[2] = rs.getString("descripcion");
                datos[3] = rs.getString("imagen");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return datos;
    }

    public void initialize() {
        String[] datos = leer();
        nom.setText(datos[0]);
        precio.setText(datos[1] + "€");
        desc.setText(datos[2]);
        /*img.setImage(new Image(getClass().getResourceAsStream("/tienda_javi_gerard_cesar/"+datos[3])));*/
        
        ArrayList<String> atribtxt = atributos(conenct());
        ArrayList<Label> atrib = new ArrayList<Label>();
        where.setText(atribtxt.get(0)+nom.getText());
        atribtxt.remove(0);
        for (String i : atribtxt) {
            Label c = new Label();
            c.setText(i);
            atrib.add(c);
        }

        atr.getChildren().clear();
        atr.getChildren().addAll(atrib);


    }
    @FXML
    private void back() throws IOException{
        App.setRoot("main");
    }
}
