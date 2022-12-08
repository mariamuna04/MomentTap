// Created by Kishorè Shanto on Nov 16 2022 16:33

package com.application.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * This Controller handles the Home Activity.
 * @author Kishorè Shanto
 */
public class HomeActivityController extends Controller{

    public void onCreateEventButton(ActionEvent actionEvent) throws Exception {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("create-event.fxml"));

        Scene scene = new Scene(fxmlLoader.load());

        stage.setScene(scene);
        stage.show();

    }
}

