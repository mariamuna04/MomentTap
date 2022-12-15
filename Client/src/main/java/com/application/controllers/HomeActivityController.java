// Created by Kishorè Shanto on Nov 16 2022 16:33

package com.application.controllers;

import com.application.client.Sequence;
import com.application.client.User;
import com.application.serialShared.Event;
import com.application.utility.Date;
import com.application.utility.Utility;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * This Controller handles the Home Activity.
 */
public class HomeActivityController extends Controller {

    public Label usernameLabel;
    public VBox future_events;
    public VBox past_events;

    @Override
    public void init() throws Exception {
        super.init();
        if (usernameLabel.getText().equals("")) {
            usernameLabel.setText(User.getName());
        }

        Sequence.searchSequence("");
        for (Event e : User.events) {
            Date date = Date.parseDate(e.event_date());
            if (Date.compareDate(date)) {
                future_events.getChildren().add(new Label(e.event_name() + " " + e.event_date()));
                System.err.println(date);
            } else {
                past_events.getChildren().add(new Label(e.event_name() + " " + e.event_date()));
                System.out.println(date);
            }
        }
    }

    public Pane parent;
    public TextField searchField;

    public void onCreateEventButton() {
        Utility.createStage("create-event-activity.fxml");
    }

    public void onDeleteEventButton() {
        Utility.createStage("delete-event-activity.fxml");
    }

    public void onSignOutButton() {
        if (Sequence.signOutSequence()) {
            try {
                Utility.changeScene(parent, "sign-in-activity.fxml");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void onSearchButton() {
        if(Sequence.searchSequence(searchField.getText())){
            try {
                this.init();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public void onEditProfileButton(ActionEvent actionEvent) {
        Utility.createStage("edit-profile-activity.fxml");
    }
}

