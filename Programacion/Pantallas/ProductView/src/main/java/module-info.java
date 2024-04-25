module productview {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;
    requires de.jensd.fx.glyphs.fontawesome;

    opens productview to javafx.fxml;
    exports productview;
}
