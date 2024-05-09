module productview {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;
    requires de.jensd.fx.glyphs.fontawesome;
    requires javafx.base;
    requires jakarta.mail;
    requires java.desktop;

    opens tienda_javi_gerard_cesar to javafx.fxml;
    exports tienda_javi_gerard_cesar;
}
