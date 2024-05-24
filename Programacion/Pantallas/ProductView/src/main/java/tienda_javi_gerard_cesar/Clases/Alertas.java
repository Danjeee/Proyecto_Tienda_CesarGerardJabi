package tienda_javi_gerard_cesar.Clases;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Alertas {

    public static Alert errorRegistrar() {
        Alert alerta = new Alert(Alert.AlertType.NONE);
        alerta.setAlertType(Alert.AlertType.ERROR);
        alerta.setHeaderText(null);
        alerta.setContentText("Error al registrar el Usuario");
        alerta.show();
        return alerta;
    }
    public static Alert alerta(String tipo, String header, String titulo, String cont){
        Alert a = new Alert(Alert.AlertType.valueOf(tipo));
        a.setHeaderText(header);
        a.setTitle(titulo);
        a.setContentText(cont);
        return a;
    }
        public static Alert registroCorrecto(){
        Alert alerta = new Alert(Alert.AlertType.NONE);
        alerta.setAlertType(AlertType.INFORMATION);
        alerta.setHeaderText(null);
        alerta.setContentText("El usuario se ha registrado correctamente.");
        alerta.show();
        return alerta;
    }

    public static Alert editarEmpleado(){
        Alert alerta = new Alert(Alert.AlertType.NONE);
        alerta.setAlertType(AlertType.INFORMATION);
        alerta.setHeaderText(null);
        alerta.setContentText("El empleado se ha actualizado correctamente.");
        alerta.show();
        return alerta;
    }    
}
