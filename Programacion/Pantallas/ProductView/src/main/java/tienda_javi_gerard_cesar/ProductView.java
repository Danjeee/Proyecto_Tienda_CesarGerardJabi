package tienda_javi_gerard_cesar;

import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import tienda_javi_gerard_cesar.Clases.Articulo;
import tienda_javi_gerard_cesar.Clases.ImportantGUI;
import tienda_javi_gerard_cesar.Clases.MenuHamb;

import java.util.ArrayList;

public class ProductView {

    public static int current;
    @FXML
    private VBox all;
    @FXML
    private AnchorPane cont;
    @FXML
    private Pane imagen;
    @FXML
    private Label nom;
    @FXML
    private Button addtocart;

    private int pedido;

    public int getCurrent() {
        return current;
    }

    @FXML
    private void addGoing(Double x, Double y) {
        if (!App.user.equals("guest")) {
            Connection con = conenct();
            Boolean existe = false;
            Boolean existePedido = false;
            pedido = getPedido();

            try {
                Statement st = con.createStatement();
                try {
                    Statement st2 = con.createStatement();
                    ResultSet rs1 = st2
                            .executeQuery("SELECT num_pedido FROM linea_pedido WHERE num_pedido = " + pedido);
                    while (rs1.next()) {
                        if (rs1.getInt("num_pedido") == pedido) {
                            existePedido = true;
                        }
                    }
                    if (!existePedido) {
                        nuevoPedido();
                        st.executeUpdate(
                                "INSERT INTO linea_pedido VALUES(" + ProductView.current + ", " + pedido + ", 1)");
                    } else {
                        ResultSet rs = st2
                                .executeQuery("SELECT cod_art FROM linea_pedido WHERE num_pedido = " + pedido);
                        while (rs.next()) {
                            if (rs.getInt("cod_art") == ProductView.current) {
                                existe = true;
                            }
                        }
                        if (existe) {
                            st.executeUpdate(
                                    "UPDATE linea_pedido SET cantidad = (SELECT cantidad FROM linea_pedido WHERE num_pedido = "
                                            + pedido + " and cod_art = " + ProductView.current
                                            + ")+1 WHERE num_pedido = "
                                            + pedido
                                            + " and cod_art = " + ProductView.current);
                        } else {
                            st.executeUpdate(
                                    "INSERT INTO linea_pedido VALUES(" + ProductView.current + ", " + pedido + ", 1)");
                        }
                    }
                } catch (SQLException e) {
                    st.executeUpdate("INSERT INTO linea_pedido VALUES(" + ProductView.current + ", " + pedido + ", 1)");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                App.setRoot("cart");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Label a = ImportantGUI.mensaje(x, y, "Debes iniciar sesion");
            if (cont.getChildren().contains(a)) {
                cont.getChildren().remove(a);
            }
            cont.getChildren().add(a);
        }
    }

    private int getPedido() {
        Connection con = conenct();
        try {
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT DISTINCT P.numero FROM pedido P WHERE DNI_cliente = '" + App.user
                    + "' and estado = 'En proceso'");
            int caca = 0;
            while (rs.next()) {
             caca = rs.getInt("numero");    
            }
            return caca;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private void nuevoPedido() {
        Connection con = conenct();
        try {
            Statement st = con.createStatement();
            ResultSet user = st.executeQuery("SELECT * FROM cliente WHERE DNI = \"" + App.user + "\"");
            String dir = "";
            int newcol = 0;
            while (user.next()) {
                dir = user.getString("direccion");
            }
            ResultSet num = st.executeQuery("SELECT numero FROM pedido order by numero desc limit 1");
            while (num.next()) {
                newcol = num.getInt("numero") + 1;
            }
            st.executeUpdate("INSERT INTO pedido VALUES(" + newcol + ", \'"
                    + LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "\', \"" + dir
                    + "\", \"En proceso\", \"" + App.user + "\")");
            this.pedido = newcol;
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
                    a.add("Accesorio > " + tipo + " > ");
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
                    a.add("Ropa > " + tipo + " > ");
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
        MenuHamb.popupHambMake();
        cont.getChildren().add(MenuHamb.menuShadow);
        cont.getChildren().add(MenuHamb.popupHamb);
        cont.getChildren().add(MenuHamb.menuHamb());
        all.getChildren().add(0, ImportantGUI.generateHeader());
        all.getChildren().add(ImportantGUI.generateFooter());
        if (App.user.equals("guest")) {
            addtocart.setStyle("-fx-background-color: #888586");
            addtocart.setCursor(Cursor.DEFAULT);
        }
        addtocart.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                addGoing(event.getSceneX(), event.getSceneY());
            }
        });
        String[] datos = leer();
        nom.setText(datos[0]);
        precio.setText(datos[1] + "€");
        desc.setText(datos[2]);
        /*
         * img.setImage(new
         * Image(getClass().getResourceAsStream("/tienda_javi_gerard_cesar/"+datos[3])))
         * ;
         */

        ArrayList<String> atribtxt = atributos(conenct());
        ArrayList<Label> atrib = new ArrayList<Label>();
        where.setText(atribtxt.get(0) + nom.getText());
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
    private void back() throws IOException {
        App.setRoot(App.getLast());
    }
}
