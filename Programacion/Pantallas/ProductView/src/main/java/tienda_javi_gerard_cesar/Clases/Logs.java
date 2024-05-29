package tienda_javi_gerard_cesar.Clases;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

import tienda_javi_gerard_cesar.App;

public class Logs {
    public static void createSQLLog(SQLException e) {
        File log = new File("Programacion\\Pantallas\\ProductView\\src\\main\\logs\\SQLLogs.txt");
        String logstring = "";
        FileReader fr;
        try {
            fr = new FileReader(log);
            Scanner sc = new Scanner(fr);
            while (sc.hasNextLine()) {
                logstring += sc.nextLine();
                logstring += "\n";
            }
            sc.close();
            fr.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        if (!log.getParentFile().exists()) {
            log.getParentFile().mkdirs();
            createSQLLog(e);
        }
        try {
            FileWriter fw = new FileWriter(log);
            logstring += "---------------------------------------------------------------  \nFECHA: "
                    + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) + "\n"
                    + e.getClass().getSimpleName() + "\n" + e.getErrorCode() + ": " + e.getCause();
            logstring += "\nMetodo: " + e.getStackTrace()[0].toString() + "\nLinea: "
                    + e.getStackTrace()[0].getLineNumber();
            for (StackTraceElement i : e.getStackTrace()) {
                if (i.toString().contains("tienda_javi_gerard_cesar")) {
                    logstring += "\nMetodo: " + i.toString() + "\nLinea: " + i.getLineNumber();
                }
            }
            fw.append(logstring);
            fw.close();
        } catch (IOException ee) {
            e.printStackTrace();
        }
    }

    public static void clearSQLLogs(){
        File log = new File("Programacion\\Pantallas\\ProductView\\src\\main\\logs\\SQLLogs.txt");
        if (!log.getParentFile().exists()) {
            log.getParentFile().mkdirs();
        }
        try {
            FileWriter fw = new FileWriter(log);
            fw.write("");
            fw.close();
        } catch (IOException e) {
            Logs.createIOLog(e);
        }
    }
    public static void clearIOLogs(){
        File log = new File("Programacion\\Pantallas\\ProductView\\src\\main\\logs\\IOLogs>.txt");
        if (!log.getParentFile().exists()) {
            log.getParentFile().mkdirs();
        }
        try {
            FileWriter fw = new FileWriter(log);
            fw.write("");
            fw.close();
        } catch (IOException e) {
            System.out.print("");
        }
    }

    public static void createIOLog(Exception e) {
        File log = new File("Programacion\\Pantallas\\ProductView\\src\\main\\logs\\IOLogs.txt");
        String logstring = "";
        FileReader fr;
        try {
            fr = new FileReader(log);
            Scanner sc = new Scanner(fr);
            while (sc.hasNextLine()) {
                logstring += sc.nextLine();
                logstring += "\n";
            }
            sc.close();
            fr.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }

        if (!log.getParentFile().exists()) {
            log.getParentFile().mkdirs();
            createIOLog(e);
        }
        try {
            FileWriter fw = new FileWriter(log);
            logstring += "---------------------------------------------------------------  \nFECHA: "
                    + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) + "\nArchivo faltante: "+App.getResourcesPath()+App.getRoot()+"\nNombre archivo: "+App.getRoot()+"\n"
                    + e.getClass().getSimpleName() + "\n" + e.getClass() + ": " + e.getCause();
            logstring += "\nMetodo: " + e.getStackTrace()[0].toString() + "\nLinea: "
                    + e.getStackTrace()[0].getLineNumber();
            for (StackTraceElement i : e.getStackTrace()) {
                if (i.toString().contains("tienda_javi_gerard_cesar")) {
                    logstring += "\nMetodo: " + i.toString() + "\nLinea: " + i.getLineNumber();
                }
            }
            fw.append(logstring);
            fw.close();
        } catch (IOException ee) {
            System.out.print("");
        }
    }
}
