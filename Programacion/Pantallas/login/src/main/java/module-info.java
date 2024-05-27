module login {
    requires javafx.controls;
    requires javafx.fxml;
    requires de.jensd.fx.glyphs.fontawesome;
    requires java.sql;
    requires jakarta.mail;
    
    opens login to javafx.fxml;
    exports login;
}
