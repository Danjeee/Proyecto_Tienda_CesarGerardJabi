module com.example.menuseleccion {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.menuseleccion to javafx.fxml;
    exports com.example.menuseleccion;
}