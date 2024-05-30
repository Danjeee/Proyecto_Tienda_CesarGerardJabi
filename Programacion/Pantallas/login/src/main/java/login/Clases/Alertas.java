package login.Clases;

import javafx.scene.control.Alert;

public class Alertas {

    public static Alert error(String tipoError) {
        Alert alerta = new Alert(Alert.AlertType.NONE);
        alerta.setAlertType(Alert.AlertType.ERROR);
        alerta.setHeaderText(null);
        alerta.setContentText(tipoError);
        alerta.show();
        return alerta;
    }

    
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

    
}

