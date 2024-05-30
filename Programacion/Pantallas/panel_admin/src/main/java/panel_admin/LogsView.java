package panel_admin;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import panel_admin.Clases.*;

public class LogsView {
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
    private void clearIOLogs() {
        Logs.clearIOLogs();
        Alert a = Alertas.alerta("INFORMATION", null, "Completado",
                "Se han limpiado los logs de ficheros");
        a.showAndWait();
    }
}
