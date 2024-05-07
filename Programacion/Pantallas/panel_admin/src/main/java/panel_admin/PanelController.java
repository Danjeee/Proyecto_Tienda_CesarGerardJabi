package panel_admin;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import panel_admin.Clases.Administrador;
import panel_admin.MenuHamburguesa.MenuHamb;

public class PanelController {

    @FXML
    private AnchorPane cont;

    @FXML
    private Button Alta_productos;

    @FXML
    private Button Administrar_empleados;

    @FXML
    private Button Administrar_clientes;

    @FXML
    private Button Administrar_productos;

    @FXML
    private App PantallaPrincipal = new App();

    @FXML
    public void cargarVentana_AltaProducto(ActionEvent actionEvent) throws IOException {
        App.setRoot("AltaProductos_Cesar_Javi_Gerard");
    }

    @FXML
    public void cargarVentana_AdministrarEmpleados(ActionEvent actionEvent) throws IOException {
        App.setRoot("AdministrarEmpleados_Cesar_Javi_Gerard");
    }

    @FXML
    public void cargarVentana_AdministrarUsuarios(ActionEvent actionEvent) throws IOException {
        App.setRoot("AdministrarUsuarios_Cesar_Javi_Gerard");
    }

    @FXML
    public void cargarVentana_AdministrarProductos(ActionEvent actionEvent) throws IOException {
        App.setRoot("AdministrarProductos_Cesar_Javi_Gerard");
    }

    static Alert alerta = new Alert(Alert.AlertType.NONE);


    private boolean privilegiosPanel(Administrador admin){

        try{
            System.out.println(admin.getNombre());
            System.out.println(admin.getDepartamento());

            if (admin.isTienePrivilegios() && admin.getDepartamento() == 1){

                
            }else if (admin.isTienePrivilegios() == 1 && admin.getDepartamento() == 2){

                
            }else{
                alerta.setAlertType(Alert.AlertType.ERROR);
                alerta.setHeaderText(null);
                alerta.setContentText("No tienes privilegios.");
                alerta.show();
            }

        }catch (Exception e){
            System.out.println(e.getClass());
            alerta.setAlertType(Alert.AlertType.ERROR);
            alerta.setHeaderText(null);
            alerta.setContentText("Error");
            alerta.show();        
        }

    }

    public Administrador infoAdmin(ResultSet r){
        Administrador prueba = null;

        try{
            while (r.next()){
                String dni = r.getString("DNI");
                String nombre = r.getString("nombre");
                String apellidos = r.getString("apellidos");
                String telefono = r.getString("telefono");
                LocalDate f_nacimiento = r.getDate("f_nacimiento").toLocalDate();
                String direccion = r.getString("direccion");
                String email = r.getString("email");
                boolean activo = r.getBoolean("activo");
                boolean tiene_privilegios = r.getBoolean("tiene_privilegios");
                String pass = r.getString("pass");
                int dpto = r.getInt("dpto");


                prueba = new Administrador(dni, nombre, apellidos, telefono, f_nacimiento, email, activo, tiene_privilegios, pass, dpto);
            }

            if (r.getInt("dpto") == 1){
                Alta_productos.setVisible(true);
                Administrar_productos.setVisible(true);
                Administrar_empleados.setVisible(true);
                Administrar_clientes.setVisible(true);
                
            }else if (r.getInt("dpto") == 2){
                Alta_productos.setVisible(true);
                Administrar_productos.setVisible(true);
                Administrar_empleados.setVisible(false);
                Administrar_clientes.setVisible(false);
            
            }else{
                alerta.setAlertType(Alert.AlertType.ERROR);
                alerta.setHeaderText(null);
                alerta.setContentText("No tienes privilegios.");
                alerta.show();
            } 

        }catch (SQLException e){
            System.out.println(e.getClass());
            alerta.setAlertType(Alert.AlertType.ERROR);
            alerta.setHeaderText(null);
            alerta.setContentText("Error");
            alerta.show();
        }

        return prueba;
    }

    public void initialize() {
        MenuHamb.popupHambMake();
        cont.getChildren().add(MenuHamb.menuShadow);
        cont.getChildren().add(MenuHamb.popupHamb);
        cont.getChildren().add(MenuHamb.menuHamb());
    }
}

