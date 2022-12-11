// Created by Kishorè Shanto on Nov 16 2022 16:33

package com.application.controllers;

import com.application.connection.Connection;
import com.application.controllers.Controller;
import com.application.utility.NetworkRequestCodes;
import com.application.utility.Utility;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * This Controller handles the Home Activity.
 * @author Kishorè Shanto
 */
public class HomeActivityController extends Controller {

    public Pane parent;


    public void onCreateEventButton() throws Exception {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("create-event.fxml"));

        Scene scene = new Scene(fxmlLoader.load());

        stage.setScene(scene);
        stage.show();

    }

    public void onDeleteEventButton() throws Exception {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("delete-event.fxml"));

        Scene scene = new Scene(fxmlLoader.load());

        stage.setScene(scene);
        stage.show();
    }

    public void onSignOutButton() throws Exception {
        Connection.sendRequestCode(NetworkRequestCodes.LOG_OUT);
        Connection.unsetConnection();
        Utility.changeScene(parent, "sign-in-activity.fxml");

    }
}

