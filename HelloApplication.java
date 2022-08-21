package com.example.jpt;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class HelloApplication extends Application {
    public static String usr;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Movie ticket booking");
        stage.setScene(scene);
        stage.show();
    }
    public static void setUser(String usr1){usr =usr1; }
    public static String getUser(){return usr;}

    public static void main(String[] args) {

        launch();
    }
}