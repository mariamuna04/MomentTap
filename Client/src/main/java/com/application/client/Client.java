// Created by Kishorè Shanto on Nov 16 2022 16:15

package com.application.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Client extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("signInActivity.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Application Name");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
