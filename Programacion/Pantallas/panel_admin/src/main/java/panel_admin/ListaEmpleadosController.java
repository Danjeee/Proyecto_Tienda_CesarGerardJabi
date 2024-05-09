package panel_admin;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import panel_admin.Clases.Empleado;

public class ListaEmpleadosController {
    
    @FXML
    public TilePane tlPane;
    public Empleado empleadoGlobal;
    
    public static ArrayList<Empleado> empleadosArr;

    public static void almacenarEmpleados() {
        empleadosArr = new ArrayList<>();

         ConexionSQL con = new ConexionSQL();

        Connection connection1 = con.conecta();
        Statement st = connection1.createStatement();

        ResultSet rs = st.executeQuery("SELECT * FROM empleado");
        String empleado;
            if (rs.next()){
                empleado = rs.getString("dpto");
            }

        for (String[] arr : rs) {
            empleadosArr.add(cargarEmpleados(arr));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cargarEmpleados();

        try {
            for (int i = 0; i < empleadosArr.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(App.class.getResource("empleadosHBOX.fxml"));
                HBox box = fxmlLoader.load();
                ListaEmpleadosController empleadosController = fxmlLoader.getController();
                empleadosController.setData(empleadosArr.get(i));
                empleadoGlobal = empleadosArr.get(i);
                tlPane.getChildren().add(box);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Empleado cargarEmpleados(String[] datos){
        String DNI = datos[0];
        String nombre = datos[1];
        String apellidos = datos[2];
        String telefono = datos[3];
        String f_nacimiento = datos[4];
        String direccion = datos[5];
        String email = datos[6];
        boolean activo = datos[7].equals("1") ? true : false;
        boolean privilegios = datos[8].equals("1") ? true : false;
        int dpto = Integer.parseInt(datos[10]);

        Empleado empleados = new Empleado(DNI,nombre,apellidos,telefono, f_nacimiento,direccion,email,activo,privilegios,dpto);

        return empleados;
    }
}
