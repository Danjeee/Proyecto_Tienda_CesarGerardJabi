module menuhamburguesa {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens menuhamburguesa to javafx.fxml;
    exports menuhamburguesa;
}
