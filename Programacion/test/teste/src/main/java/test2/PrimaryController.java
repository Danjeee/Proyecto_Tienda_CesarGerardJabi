package test2;

import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Window;

public class PrimaryController {

    @FXML
    private TextField usuario;

    @FXML
    private TextField contra;

    @FXML
    private Connection conectar(){
        Connection con = null;
        try{
            con = DriverManager.getConnection("jbdx:mysql://127.0.0.1:4000/tienda_ropa", "root", "");
        } catch (SQLException e){
            e.printStackTrace();
        }
        return con;
    }
    @FXML
    private void login(){
        int coderr = 0;
        if (usuario.getText().isEmpty() || contra.getText().isEmpty()) {
            System.out.println("Error");
            coderr = 1;
        }
        Connection con = conectar();
        try {
            Statement e = con.createStatement();
            ResultSet rs = e.executeQuery("SELECT email from cliente");
            ArrayList<String> users = new ArrayList<String>();
            ArrayList<String> pass = new ArrayList<String>();
            while (rs.next()) {
                users.add(rs.getString("email"));
                pass.add(rs.getString("pass"));
            }
            System.out.println(users);
            System.out.println(pass);
           
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
