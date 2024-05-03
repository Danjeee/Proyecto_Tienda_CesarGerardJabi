module panel_admin {
    requires javafx.controls;
    requires javafx.fxml;
    requires de.jensd.fx.glyphs.fontawesome;
    requires java.sql;

    opens panel_admin to javafx.fxml;
    exports panel_admin;
}
