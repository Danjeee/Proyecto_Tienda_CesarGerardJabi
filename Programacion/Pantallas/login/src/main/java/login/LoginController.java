package login;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.JOptionPane;

import com.mysql.cj.protocol.a.SqlDateValueEncoder;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import login.Clases.MenuHamb;

public class LoginController {

    @FXML
    private AnchorPane cont;

    @FXML
    private App PantallaPrincipal = new App();
    @FXML
    public void initialize(){
        MenuHamb.popupHambMake();
        cont.getChildren().add(MenuHamb.menuShadow);
        cont.getChildren().add(MenuHamb.popupHamb);
        cont.getChildren().add(MenuHamb.menuHamb());
    }

    @FXML
    public void cargarVentana_olvidar(ActionEvent actionEvent) throws IOException {
        App.setRoot("OlvidarContraseña");
    }

    @FXML
    public void cargarVentana_seleccion(ActionEvent actionEvent) throws IOException {
        App.setRoot("seleccion");
    }

    @FXML
    public void cargarVentana_registro(ActionEvent actionEvent) throws IOException {
        App.setRoot("pantalla2");
    }

    @FXML
    public void flecha_volver(ActionEvent actionEvent) throws IOException{
        App.setRoot("Login");
    }

    @FXML
    private PasswordField pwdFieldContra;

    @FXML
    private TextField textFieldCorreo;

    @FXML
    private Button btnLogin;

    @FXML
    private void eventAction(ActionEvent event) throws SQLException{

        Object obj = event.getSource();
        if (obj.equals(btnLogin)){

            if (!textFieldCorreo.getText().isEmpty() && !pwdFieldContra.getText().isEmpty()) {

                String user = textFieldCorreo.getText();
                String pwd = pwdFieldContra.getText();

                int state = comprobarLog(user, pwd);    
                if (state!= 1) {
                    if (state == 1) {
                        
                        JOptionPane.showMessageDialog(null, "Ha iniciado sesion.");
                        PantallaPrincipal.cargarVentana_seleccion(event, "seleccion.fxml");
                    } else{
                        JOptionPane.showMessageDialog(null, "Los datos introducidos son incorrectos ")
                    }     
                }
            }
        }
    } 

    @FXML
    private int comprobarLog(String user, String pwd){
        int state = -1;

        try (Connection connection = BDConnector.getConnection();
            PreparedStatement pst = connection.prepareStatement("SELECT * FROM CLIENTE WHERE dni=? and pass=?")){

            pst.setString(1, user);
            pst.setString(2, user);

            try(ResultSet rs = pst.executeQuery()){
                if (rs.next()) {
                    state = 1;                        
                } else {
                    state = 0;
                }
                BDConnector.closeCon(Connection);
            } catch (SQLException e) {
                System.err.println("Error al ejecutar la consulta: " +e.getMessage());
            }
        } catch (SQLException e){
            System.err.println("Error al conectar la base de datos: " +e.getMessage());
        }
        return state;
    }
}