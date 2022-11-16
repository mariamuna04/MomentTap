package com.application.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SignIn extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("sign-in-activity.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Application Name");
        stage.setScene(scene);
        stage.show();
    }

    public static void ClientInit(String[] args) {
        launch(args);
    }
}