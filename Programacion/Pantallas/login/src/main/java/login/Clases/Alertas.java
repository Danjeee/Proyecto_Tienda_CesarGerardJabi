package login.Clases;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class Alertas {

    public static Alert error(String tipoError) {
        Alert alerta = new Alert(Alert.AlertType.NONE);
        alerta.setAlertType(Alert.AlertType.ERROR);
        alerta.setHeaderText(null);
        alerta.setContentText(tipoError);
        alerta.show();
        return alerta;
    }


    public static Alert informacion(String tipoInformacion) {
        Alert alerta = new Alert(Alert.AlertType.NONE);
        alerta.setAlertType(Alert.AlertType.INFORMATION);
        alerta.setHeaderText(null);
        alerta.setContentText(tipoInformacion);
        alerta.show();
        return alerta;
    }

    public static Alert confirmacion(String tipoConfirmacion) {
        Alert alerta = new Alert(Alert.AlertType.NONE);
        alerta.setAlertType(Alert.AlertType.CONFIRMATION);
        alerta.setHeaderText(null);
        alerta.setContentText(tipoConfirmacion);
        alerta.show();
        return alerta;
    }

    public static Alert warning(String tipoWarning) {
        Alert alerta = new Alert(Alert.AlertType.NONE);
        alerta.setAlertType(Alert.AlertType.WARNING);
        alerta.setHeaderText(null);
        alerta.setContentText(tipoWarning);
        alerta.show();
        return alerta;
    }

    /*
    public static Alert malEmail() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("EMAIL INCORRECTO.");
        alert.setTitle("ERROR");
        alert.setContentText("RECUERDE RELLENAR CORRECTAMENTE LOS CAMPOS.");
        alert.showAndWait();
        return alert;
    }

    public static Alert bienMail() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText("EMAIL CORRECTO.");
        alert.setTitle("ENVIADO");
        alert.setContentText("EMAIL ENVIADO CORRECTAMENTE.");
        alert.showAndWait();
        return alert;
    }

    public static Alert rellenarCampos() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("INICIO DE SESIÓN INCORRECTO.");
        alert.setTitle("ERROR");
        alert.setContentText("RECUERDE RELLENAR CORRECTAMENTE LOS CAMPOS.");
        alert.showAndWait();
        return alert;
    }

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
        alerta.setAlertType(Alert.AlertType.INFORMATION);
        alerta.setHeaderText(null);
        alerta.setContentText("El usuario se ha registrado correctamente.");
        alerta.show();
        return alerta;
    }

    public static Alert editarEmpleado(){
        Alert alerta = new Alert(Alert.AlertType.NONE);
        alerta.setAlertType(Alert.AlertType.INFORMATION);
        alerta.setHeaderText(null);
        alerta.setContentText("El empleado se ha actualizado correctamente.");
        alerta.show();
        return alerta;
    }    
     */
}

