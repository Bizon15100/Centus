package com.example.centus;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(main.class.getResource("Login.fxml"));  //tworzenie nowej formy Login
        Scene scene = new Scene(fxmlLoader.load(), 380, 525);  // ustawianie wielkości okna
        stage.setTitle(" ");    // ustawianie tytułu okna
        stage.setScene(scene);   // tworzenie nowej sceny
        stage.show();            // pokazanie sceny
    }

    public static void main(String[] args) {
        launch();
    }
}