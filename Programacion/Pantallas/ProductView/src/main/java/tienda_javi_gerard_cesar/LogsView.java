package tienda_javi_gerard_cesar;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import tienda_javi_gerard_cesar.Clases.*;

public class LogsView {
    @FXML
    private AnchorPane cont;
    @FXML
    private VBox all;

    @FXML
    private void checkSQLLog() {
        File file = new File("Programacion\\Pantallas\\ProductView\\src\\main\\logs\\SQLLogs.txt");
        if (!file.exists()) {
            Alert a = Alertas.alerta("ERROR", "ERROR", "Fichero de logs no disponible", "No se han creado logs aun");
            a.showAndWait();
        } else {
            if (Desktop.isDesktopSupported()) {
                try {
                    Desktop.getDesktop().edit(file);
                } catch (IOException e) {
                    Logs.createIOLog(e);
                }
            } else {
                Alert a = Alertas.alerta("ERROR", "ERROR", "Fichero de logs no disponible",
                        "No se han creado logs aun");
                a.showAndWait();
            }
        }
    }

    @FXML
    private void checkIOLog() {
        File file = new File("Programacion\\Pantallas\\ProductView\\src\\main\\logs\\IOLogs.txt");
        if (!file.exists()) {
            Alert a = Alertas.alerta("ERROR", "ERROR", "Fichero de logs no disponible", "No se han creado logs aun");
            a.showAndWait();
        } else {
            if (Desktop.isDesktopSupported()) {
                try {
                    Desktop.getDesktop().edit(file);
                } catch (IOException e) {
                    Logs.createIOLog(e);
                }
            } else {
                Alert a = Alertas.alerta("ERROR", "ERROR", "Fichero de logs no disponible",
                        "No se han creado logs aun");
                a.showAndWait();
            }
        }
    }

    @FXML
    private void checkAdminLog() {
        File file = new File("Programacion\\Pantallas\\ProductView\\src\\main\\logs\\AdminLogs.txt");
        if (!file.exists()) {
            Alert a = Alertas.alerta("ERROR", "ERROR", "Fichero de logs no disponible", "No se han creado logs aun");
            a.showAndWait();
        } else {
            if (Desktop.isDesktopSupported()) {
                try {
                    Desktop.getDesktop().edit(file);
                } catch (IOException e) {
                    Logs.createIOLog(e);
                }
            } else {
                Alert a = Alertas.alerta("ERROR", "ERROR", "Fichero de logs no disponible",
                        "No se han creado logs aun");
                a.showAndWait();
            }
        }
    }

    @FXML
    private void clearSQLLogs() {
        Logs.clearSQLLogs();
        Alert a = Alertas.alerta("INFORMATION", null, "Completado",
                "Se han limpiado los logs de SQL");
        a.showAndWait();
    }
    @FXML
    private void clearAdminogs() {
        Logs.clearAdminLogs();
        Alert a = Alertas.alerta("INFORMATION", null, "Completado",
                "Se han limpiado los logs de SQL");
        a.showAndWait();
    }


    @FXML
    private void clearIOLogs() {
        Logs.clearIOLogs();
        Alert a = Alertas.alerta("INFORMATION", null, "Completado",
                "Se han limpiado los logs de ficheros");
        a.showAndWait();
    }
    @FXML
    public void initialize(){
        MenuHamb.init(cont);
        all.getChildren().add(0, ImportantGUI.generateHeader());
        Button back = ImportantGUI.defaultBack();
        back.setLayoutX(200);
        back.setLayoutY(300);
        cont.getChildren().add(back);
    }
}
