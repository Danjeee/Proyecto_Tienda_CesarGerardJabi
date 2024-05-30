package tienda_javi_gerard_cesar;

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
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import tienda_javi_gerard_cesar.Clases.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.CheckBox;

public class EditarEmpleadoController {

    @FXML
    private App PantallaPrincipal = new App();

    @FXML
    public void cargarVentana_registro(ActionEvent actionEvent){
        try {
            App.setRoot("Login");
        } catch (Exception e) {
            Logs.createIOLog(e);
        }
    }

    @FXML
    private void limpiarBotonE(ActionEvent event) {
        nombreEmpleado.setText(null);
    }

    @FXML
    private void limpiarBotonA(ActionEvent event) {
        apellidosEmpleado.setText(null);
    }

    @FXML
    private void limpiarBotonDirecc(ActionEvent event) {
        direccionEmpleado.setText(null);
    }

    @FXML
    private void limpiarBotonFe(ActionEvent event) {
        fechaNac.setValue(null);
    }

    @FXML
    private void limpiarBotonEmai(ActionEvent event) {
        emailEmpleado.setText(null);
    }    

    @FXML
    private void limpiarBotonTelef(ActionEvent event) {
        numtelef.setText(null);
    }    

    @FXML
    private TextField nombreEmpleado;

    @FXML
    private TextField apellidosEmpleado;

    @FXML
    private TextField DNIempleado;

    @FXML
    private TextField direccionEmpleado;

    @FXML
    private DatePicker fechaNac;

    @FXML
    private TextField emailEmpleado;

    @FXML
    private ChoiceBox<String> dpto;

    @FXML
    private CheckBox privilegiosEmpleado;

    @FXML
    private TextField numtelef;

    @FXML
    private AnchorPane cont;

    @FXML
    private VBox all;

    private static String current = "";
    private ArrayList<String> olddatos = new ArrayList<>();

    public static String getCurrent(){
        return current;
    }
    public static void setCurrent(String a){
        current = a;
    }

    public void initialize() {
        MenuHamb.init(cont);
        all.getChildren().add(0, ImportantGUI.generateHeader());
        dpto.setStyle("-fx-font-size: 14");
        fechaNac.setStyle("-fx-font-size: 14");
        DNIempleado.setEditable(false);
        Connection con = conenct();

        try {

            Statement st0 = con.createStatement();
            ResultSet rs0 = st0.executeQuery("SELECT * FROM departamento");

            while (rs0.next()) {
                dpto.getItems().add(rs0.getString("codigo") + ".- " + rs0.getString("nombre"));
            }

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM empleado WHERE DNI = '" + current + "'");
            

            while (rs.next()) {
                nombreEmpleado.setText(rs.getString("nombre"));
                apellidosEmpleado.setText(rs.getString("apellidos"));
                DNIempleado.setText(rs.getString("DNI"));
                numtelef.setText(rs.getString("telefono"));
                emailEmpleado.setText(rs.getString("email"));
                fechaNac.setValue(LocalDate.parse(rs.getString("f_nacimiento")));
                direccionEmpleado.setText(rs.getString("direccion"));
                privilegiosEmpleado.selectedProperty().set(rs.getBoolean("tiene_privilegios"));

                olddatos.add(rs.getString("dni"));
                olddatos.add(rs.getString("nombre"));
                olddatos.add(rs.getString("apellidos"));
                olddatos.add(rs.getString("telefono"));
                olddatos.add(rs.getString("f_nacimiento"));
                olddatos.add(rs.getString("direccion"));
                olddatos.add(rs.getString("email"));
                olddatos.add("******");
                olddatos.add(rs.getString("dpto"));
                olddatos.add(Boolean.toString(rs.getBoolean("tiene_privilegios")));
                olddatos.add(Boolean.toString(rs.getBoolean("activo")));

                Statement st1 = con.createStatement();
                ResultSet rs1 = st1.executeQuery("SELECT * FROM departamento WHERE codigo = "+rs.getInt("dpto"));
                while (rs1.next()) {
                    dpto.getSelectionModel().select(rs1.getString("codigo")+".- "+rs1.getString("nombre"));
                }

            }

        } catch (SQLException e) {
            Logs.createSQLLog(e);
        }
    }

    @FXML
    private void editarEmpBoton(){

        Connection connection = conenct();
        try {
       
            Statement st = connection.createStatement();

            /*st.executeUpdate("UPDATE EMPLEADO set ('"+DNIempleado.getText()+"','"+nombreEmpleado.getText()+"','"+apellidosEmpleado.getText()+"','"+numtelef.getText()+"','"+fechaNac.getValue()
            +"','"+direccionEmpleado.getText()+"','"+emailEmpleado.getText()+"',1,'"+dpto.getSelectionModel().getSelectedItem()+"',"+privilegiosEmpleado.isSelected()+"where DNI = '"+current+"'");*/

            st.executeUpdate("UPDATE EMPLEADO set DNI='"+DNIempleado.getText()+"', nombre='"+nombreEmpleado.getText()+"', apellidos='"+apellidosEmpleado.getText()+"', telefono='"+numtelef.getText()+"', f_nacimiento='"+fechaNac.getValue()
            +"', direccion='"+direccionEmpleado.getText()+"', email='"+emailEmpleado.getText()+"', activo=1, dpto='"+dpto.getSelectionModel().getSelectedItem().substring(0,1)+"', tiene_privilegios="+privilegiosEmpleado.isSelected()+" WHERE DNI = '"+current+"'");
            String[] newdatos = {DNIempleado.getText(), nombreEmpleado.getText(), apellidosEmpleado.getText(), numtelef.getText(), fechaNac.getValue().toString(), direccionEmpleado.getText(), emailEmpleado.getText(), "******", dpto.getSelectionModel().getSelectedItem().substring(0,1), Boolean.toString(privilegiosEmpleado.isSelected()), "true"};
            String [] olddatos1 = new String[olddatos.size()];
            for (int i = 0; i<olddatos1.length; i++){
                olddatos1[i] = olddatos.get(i);
            }
            Logs.createAdminLog('m', 'e', Logs.userToLogs(olddatos1), Logs.userToLogs(newdatos));
            Alertas.editarEmpleado();
            connection.close();
        } catch (SQLException e) {
            
            Logs.createSQLLog(e);
            Alertas.errorRegistrar();
        }
 
    }

    @FXML
    private void editarEmpleado() {

        Connection connection = conenct();

        try {
            Statement st = connection.createStatement();
            st.executeUpdate(
                    update("departamento", String.valueOf(dpto.getSelectionModel().getSelectedItem().charAt(0))));

            connection.close();

        } catch (SQLException e) {
            Logs.createSQLLog(e);
            Alertas.errorRegistrar();

        }
    }

    @FXML
    public void back() throws IOException{
        App.setRoot(App.getLast());
    }

    private String update(String field, String data) {
        return "UPDATE empleado SET " + field + " = '" + data + "' WHERE DNI = '" + current + "'";
    }

    private Connection conenct() {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:4000/tienda_ropa", "root", "");
        } catch (SQLException e) {
            Logs.createSQLLog(e);
        }
        return con;
    }
}
