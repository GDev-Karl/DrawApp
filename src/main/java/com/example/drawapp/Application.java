package com.example.drawapp;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {

    public static int WIN_WIDHT = 1000;
    public static int WIN_HEIGHT = 600;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("draw-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), WIN_WIDHT, WIN_HEIGHT);
        stage.setTitle("Draw");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}