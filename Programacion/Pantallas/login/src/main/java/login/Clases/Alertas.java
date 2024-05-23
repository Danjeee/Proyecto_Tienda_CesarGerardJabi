package login.Clases;

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

