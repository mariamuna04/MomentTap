// Created by Kishorè Shanto on Nov 16 2022 16:33

package com.application.controllers;

import com.application.connection.Connection;
import com.application.controllers.Controller;
import com.application.serialShared.SearchResult;
import com.application.utility.DialogBox;
import com.application.utility.NetworkRequestCodes;
import com.application.utility.Utility;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.sql.ResultSet;

/**
 * This Controller handles the Home Activity.
 * @author Kishorè Shanto
 */
public class HomeActivityController extends Controller {

    public Pane parent;
    public TextField searchField;


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
        DialogBox.showDialogue("Success", "You have successfully signed out.", DialogBox.SUCCESS_DIALOG_BOX);

    }
    public void onSearchButton() throws Exception {
        Connection.sendRequestCode(NetworkRequestCodes.SEARCH_EVENT);
        Connection.sendString(searchField.getText());
        int response = Connection.receiveRequestCode();
        System.out.println(response);
        if (response == NetworkRequestCodes.SEARCH_EVENT_CONFIRMATION) {
            System.out.println("Search Event Confirmation");
            SearchResult.results = (ResultSet) Connection.receiveObject();
            assert SearchResult.results != null;
            // TODO: an error found - resultset is null
            System.out.println(SearchResult.results.getString("event_name"));

            while (SearchResult.results.next()) {
                System.out.println(SearchResult.results.getString("event_name"));
                System.out.println(SearchResult.results.getString("event_description"));
                System.out.println(SearchResult.results.getString("event_category"));
                System.out.println(SearchResult.results.getString("event_date"));
                System.out.println(SearchResult.results.getString("event_start_time"));
                System.out.println(SearchResult.results.getString("event_end_time"));
            }
        } else {

        }
    }
}

