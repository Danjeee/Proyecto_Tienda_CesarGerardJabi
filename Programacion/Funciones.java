

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import Usuarios.*;
import Articulos.*;
import Pedido.*;

public class Funciones {
    private Connection conectar(){
        Connection con = null;
        try{
            con = DriverManager.getConnection("jbdx:mysql://127.0.0.1:4000/tienda_ropa", "root", "");
        } catch (SQLException e){
            e.printStackTrace();
        }
        return con;
    }
    private void login(){
    }
}
