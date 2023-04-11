module com.example.slimechunkcalculator {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.slimechunkcalculator to javafx.fxml;
    exports com.example.slimechunkcalculator;
}