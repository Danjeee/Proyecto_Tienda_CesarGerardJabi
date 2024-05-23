package tienda_javi_gerard_cesar;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import tienda_javi_gerard_cesar.Clases.*;

public class Pagar {
    @FXML
    private VBox all;
    @FXML
    private HBox carro;
    @FXML
    private AnchorPane cont;
    @FXML
    private Label total;
    @FXML
    private ChoiceBox<CuentaPago> metodoscliente;
    private ArrayList<CuentaPago> cuentascliente = new ArrayList<>();
    private boolean formOpened = false;

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
    private void toggleAdd() {
        if (!formOpened) {
            cont.getChildren().add(1, añadirMP());
            formOpened = true;
        }
    }

    private VBox añadirMP() {
        VBox a = new VBox();
        a.setStyle("-fx-background-color: #fff");
        a.setPadding(new Insets(20, 20, 20, 20));
        a.setPrefWidth(500);
        a.setLayoutX(700);
        a.setLayoutY(720);
        a.setSpacing(10);
        ChoiceBox<String> type = new ChoiceBox<String>();
        HBox botones = new HBox();
        botones.setPrefWidth(500);
        Button close = new Button("Cerrar");
        close.setOnAction(e -> {
            cont.getChildren().remove(1);
            formOpened = false;
        });
        Button add = new Button("Añadir");
        add.setOnAction(e -> añadirNuevaCuenta(type.getSelectionModel().getSelectedItem(), a));
        botones.getChildren().addAll(add, close);
        for (String i : metodos()) {
            type.getItems().add(i);
        }
        type.getSelectionModel().select("Tarjeta");
        a.getChildren().add(type);
        type.setOnAction(e -> {
            a.getChildren().clear();
            a.getChildren().add(type);
            updateForm(type.getSelectionModel().getSelectedItem(), a);
            a.getChildren().add(botones);
        });
        updateForm(type.getSelectionModel().getSelectedItem(), a);
        a.getChildren().add(botones);
        return a;
    }

    private void añadirNuevaCuenta(String current, VBox a) {
        ArrayList<TextField> tfs = new ArrayList<>();
        Boolean corr = true;
        switch (current) {
            case "Tarjeta":
                corr = true;
                for (Node i : a.getChildren()) {
                    if (i instanceof TextField) {
                        TextField tf = (TextField) i;
                        if (tf.getText().isEmpty()) {
                            Alert alert = Alertas.alerta("ERROR", null, "Error", "Campos incompletos");
                            alert.showAndWait();
                            corr = false;
                            break;
                        }
                        tfs.add(tf);

                    }
                }
                if (corr) {
                    if (tfs.get(0).getText().length() != 16) {
                        Alert alert = Alertas.alerta("ERROR", null, "Error", "Tarjeta incorrecta");
                        alert.showAndWait();
                        corr = false;
                        break;
                    }
                    if (!tfs.get(0).getText().matches("\\d{16}")) {
                        Alert alert = Alertas.alerta("ERROR", null, "Error", "Tarjeta incorrecta");
                        alert.showAndWait();
                        corr = false;
                        break;
                    }
                    if (!tfs.get(2).getText().matches("\\d{2}/\\d{2}")) {
                        Alert alert = Alertas.alerta("ERROR", null, "Error", "Fecha incorrecta");
                        alert.showAndWait();
                        corr = false;
                    }
                }
                if (corr) {
                    Connection con = conenct();
                    try {
                        Statement st = con.createStatement();
                        st.executeUpdate("INSERT INTO cuentas_pago(cuenta, fecha, tipo, DNI_cliente) VALUES('"
                                + tfs.get(0).getText() + "', '" + tfs.get(2).getText() + "', 'Tarjeta', '" + App.user
                                + "')");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case "Bizum":
                corr = true;
                for (Node i : a.getChildren()) {
                    if (i instanceof TextField) {
                        TextField tf = (TextField) i;
                        if (tf.getText().isEmpty()) {
                            Alert alert = Alertas.alerta("ERROR", null, "Error", "Campos incompletos");
                            alert.showAndWait();
                            corr = false;
                            break;
                        }
                        tfs.add(tf);

                    }
                }
                if (corr) {
                    if (tfs.get(0).getText().length() != 9) {
                        Alert alert = Alertas.alerta("ERROR", null, "Error", "Numero demasiado corto");
                        alert.showAndWait();
                        corr = false;
                        break;
                    }
                    if (!tfs.get(0).getText().matches("\\d{9}")) {
                        Alert alert = Alertas.alerta("ERROR", null, "Error", "Caracteres/Longitud invalidos");
                        alert.showAndWait();
                        corr = false;
                        break;
                    }
                }
                if (corr) {
                    Connection con = conenct();
                    try {
                        Statement st = con.createStatement();
                        st.executeUpdate("INSERT INTO cuentas_pago(cuenta, fecha, tipo, DNI_cliente) VALUES('"
                                + tfs.get(0).getText() + "', null, 'Bizum', '" + App.user
                                + "')");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case "PayPal":
                corr = true;
                for (Node i : a.getChildren()) {
                    if (i instanceof TextField) {
                        TextField tf = (TextField) i;
                        if (tf.getText().isEmpty()) {
                            Alert alert = Alertas.alerta("ERROR", null, "Error", "Campos incompletos");
                            alert.showAndWait();
                            corr = false;
                            break;
                        }
                        tfs.add(tf);

                    }
                }
                if (corr) {
                    if (!tfs.get(0).getText().matches(
                            "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")) {
                        Alert alert = Alertas.alerta("ERROR", null, "Error", "Mail invalido");
                        alert.showAndWait();
                        corr = false;
                    }
                }
                if (corr) {
                    Connection con = conenct();
                    try {
                        Statement st = con.createStatement();
                        st.executeUpdate("INSERT INTO cuentas_pago(cuenta, fecha, tipo, DNI_cliente) VALUES('"
                                + tfs.get(0).getText() + "', null, 'PayPal', '" + App.user
                                + "')");
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case "Efectivo":
                Connection con = conenct();
                corr = true;
                try {
                    Statement stm = con.createStatement();
                    ResultSet rs = stm
                            .executeQuery("SELECT * FROM cuentas_pago WHERE DNI_cliente = '" + App.user + "'");
                    while (rs.next()) {
                        if (rs.getString("Tipo").equals("Efectivo")) {
                            corr = false;
                        }
                    }
                    if (corr) {
                        stm.executeUpdate(
                                "INSERT INTO cuentas_pago(cuenta, fecha, tipo, DNI_cliente) VALUES('', null, 'Efectivo', '"
                                        + App.user
                                        + "')");
                    } else {
                        Alert alert = Alertas.alerta("ERROR", null, "Error",
                                "Este usuario ya tiene este metodo de pago");
                        alert.showAndWait();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;

            default:
                break;
        }
        if (corr) {
            metodoscliente.getItems().clear();
            Alert alert = Alertas.alerta("INFORMATION", null, "Error", "Metodo de pago añadido correctamente");
            alert.showAndWait();
            cont.getChildren().remove(1);
            cuentascliente.clear();
            all.getChildren().remove(all.getChildren().get(2));
            all.getChildren().remove(all.getChildren().get(0));
            initialize();
            formOpened = false;
        }
    }

    @FXML
    private void pagar() {
        if (metodoscliente.getSelectionModel().isEmpty()) {
            Alert alert = Alertas.alerta("ERROR", null, "Error", "Metodo de pago invalido");
            alert.showAndWait();
        } else {
            Connection con = conenct();
            try {
                Statement st = con.createStatement();
                int current = 0;
                ResultSet setcurrent = st.executeQuery(
                        "SELECT numero FROM pedido WHERE estado = 'En proceso' AND DNI_cliente = '" + App.user + "'");
                while (setcurrent.next()) {
                    current = setcurrent.getInt("numero");
                }
                Statement stm = con.createStatement();
                stm.executeUpdate("UPDATE pedido SET estado = 'Completado' WHERE numero = " + current);
                if (Cart.descuentoActivo.getNombre() != "0") {
                    Statement st1 = con.createStatement();
                    st1.executeUpdate("INSERT INTO descuentos_usados(descuento, usado_por) VALUES('"
                            + Cart.descuentoActivo.getNombre() + "', '" + App.user + "')");
                }
                Alert alert = Alertas.alerta("INFORMATION", null, "Gracias por comprar con nosotros",
                        "Pedido completado");
                alert.showAndWait();
                App.setRoot("seleccion");
            } catch (SQLException e) {

                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void volver() {
        try {
            System.out.println(App.getLast());
            App.setRoot(App.getLast());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void cargarItems() {
        carro.getChildren().clear();
        carro.setSpacing(150);
        carro.setMaxHeight(9999);
        VBox names = new VBox();
        names.setPrefWidth(250);
        names.setAlignment(Pos.TOP_CENTER);
        VBox cants = new VBox();
        cants.setPrefWidth(100);
        cants.setAlignment(Pos.TOP_CENTER);
        VBox precios = new VBox();
        precios.setPrefWidth(150);
        precios.setAlignment(Pos.TOP_CENTER);
        names.getChildren().add(new Label("NOMBRE"));
        cants.getChildren().add(new Label("CANT"));
        precios.getChildren().add(new Label("PRECIO"));
        names.getChildren().add(new Label(" "));
        cants.getChildren().add(new Label(" "));
        precios.getChildren().add(new Label(" "));
        for (Articulo i : Cart.articulos) {
            names.getChildren().add(new Label(i.getNombre()));
            cants.getChildren().add(new Label(String.valueOf(i.getCant())));
            precios.getChildren().add(new Label(String.valueOf(i.getPrecio().doubleValue() * i.getCant() + "€")));
        }
        total.setText("Total (incl. envío, iva, descuento): " + formatDouble(Cart.totalValor));
        for (Node i : names.getChildren()) {
            Label ii = (Label) i;
            ii.setFont(new Font("System", 20));
        }
        for (Node i : cants.getChildren()) {
            Label ii = (Label) i;
            ii.setFont(new Font("System", 20));
        }
        for (Node i : precios.getChildren()) {
            Label ii = (Label) i;
            ii.setFont(new Font("System", 20));
        }
        carro.getChildren().addAll(names, cants, precios);
    }

    private String formatDouble(Double a) {
        String aa = String.valueOf(a);
        if (aa.charAt(0) == ('.')) {
            aa = "00" + aa;
        }
        if (!aa.contains(".")) {
            return aa + ".00€";
        }
        if (aa.charAt(aa.length() - 1) == ('.')) {
            return aa.substring(0, aa.length() - 2) + "€";
        }
        if (aa.charAt(1) == '.') {
            aa = "0" + aa;
        }
        for (int i = 0; i < aa.length(); i++) {
            if (aa.charAt(i) == '.') {
                int ii = i + 2;
                if (aa.length() == ii) {
                    return aa.substring(0, ii) + "0€";
                } else {
                    return aa.substring(0, ii) + aa.charAt(ii) + "€";
                }
            }
        }
        return aa;
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
                a.getChildren().addAll(mail, mailcont, pass, passcont);
                break;
            case "Efectivo":
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
        all.getChildren().add(0, ImportantGUI.generateHeader());
        all.getChildren().add(ImportantGUI.generateFooter());
        Connection con = conenct();
        try {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM cuentas_pago WHERE DNI_cliente = '" + App.user + "'");
            while (rs.next()) {
                cuentascliente.add(new CuentaPago(rs.getInt("id"), rs.getString("cuenta"), rs.getString("fecha"),
                        rs.getString("tipo")));
            }
            cargarItems();
            for (CuentaPago i : cuentascliente) {
                metodoscliente.getItems().add(i);
            }
            metodoscliente.getSelectionModel().select(metodoscliente.getItems().size() - 1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
