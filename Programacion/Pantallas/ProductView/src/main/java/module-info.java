module productview {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;

    opens productview to javafx.fxml;
    exports productview;
}
