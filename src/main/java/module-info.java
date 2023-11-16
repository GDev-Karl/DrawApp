module com.example.drawapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.drawapp to javafx.fxml;
    exports com.example.drawapp;
    exports com.example.drawapp.controllers;
    opens com.example.drawapp.controllers to javafx.fxml;
}