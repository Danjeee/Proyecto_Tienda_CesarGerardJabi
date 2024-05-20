package tienda_javi_gerard_cesar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import tienda_javi_gerard_cesar.Clases.*;

public class Pagar {
    @FXML
    private VBox all;
    @FXML
    private VBox carro;
    @FXML
    private AnchorPane cont;
    /*
     * @FXML
     * private Label total;
     */
    @FXML
    private ChoiceBox<String> metodoscliente;
    private ArrayList<CuentaPago> cuentascliente = new ArrayList<>();

    private Connection conenct() {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:4000/tienda_ropa", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    private ArrayList<String> metodos() {
        ArrayList<String> a = new ArrayList<>();
        Connection con = conenct();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM metodo_pago");
            while (rs.next()) {
                a.add(rs.getString("descripcion"));
            }
            return a;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return a;
    }

    @FXML
    private void toggleAdd(){
        cont.getChildren().add(1, añadirMP());
    }

    private VBox añadirMP() {
        VBox a = new VBox();
        a.setStyle("-fx-background-color: #fff");
        a.setPadding(new Insets(20,20,20,20));
        a.setPrefWidth(500);
        a.setLayoutX(700);
        a.setLayoutY(720);
        a.setSpacing(10);
        ChoiceBox<String> type = new ChoiceBox<String>();
        HBox botones = new HBox();
        Button close = new Button();
        Button add = new Button("Añadir");
        for (String i : metodos()) {
            type.getItems().add(i);
        }
        type.getSelectionModel().select("Tarjeta");
        a.getChildren().add(type);
        type.setOnAction(e -> {
            a.getChildren().clear();
            a.getChildren().add(type);
            updateForm(type.getSelectionModel().getSelectedItem(), a);
            a.getChildren().add(add);
        });
        updateForm(type.getSelectionModel().getSelectedItem(), a);
        a.getChildren().add(add);
        return a;
    }

    private void updateForm(String current, VBox a) {
        switch (current) {
            case "Tarjeta":
                Label cuenta = new Label("Num. tarjeta");
                TextField cuentacont = new TextField();
                Label cvvpass = new Label("Cvv");
                PasswordField cvvpasscont = new PasswordField();
                Label fecha = new Label("Fecha");
                TextField fechacont = new TextField();
                a.getChildren().addAll(cuenta, cuentacont, cvvpass, cvvpasscont, fecha, fechacont);
                break;
            case "Bizum":
                Label num = new Label("Num. tlf");
                TextField numcont = new TextField();
                a.getChildren().addAll(num, numcont);
                break;
            case "PayPal":
                Label mail = new Label("Mail de la cuenta");
                TextField mailcont = new TextField();
                Label pass = new Label("Contraseña");
                PasswordField passcont = new PasswordField();
                a.getChildren().addAll(mail,mailcont,pass,passcont);
                break;
            case "Efectivo":
                break;

            default:
                break;
        }
    }

    public void initialize() {
        Connection con = conenct();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM cuentas_pago WHERE DNI_cliente = '" + App.user + "'");
            while (rs.next()) {
                cuentascliente.add(new CuentaPago(rs.getInt("id"), rs.getString("cuenta"), rs.getString("fecha")));
            }
            System.out.println(cuentascliente);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
