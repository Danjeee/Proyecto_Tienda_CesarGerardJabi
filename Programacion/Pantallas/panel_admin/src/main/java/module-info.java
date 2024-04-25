module panel_admin {
    requires javafx.controls;
    requires javafx.fxml;

    opens panel_admin to javafx.fxml;
    exports panel_admin;
}
