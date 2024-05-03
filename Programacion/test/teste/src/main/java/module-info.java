module test2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires de.jensd.fx.glyphs.commons;

    opens test2 to javafx.fxml;
    exports test2;
}
