// Created by Kishorè Shanto on Nov 16 2022 16:33

package com.application.controllers;

import com.application.client.Sequence;
import com.application.utility.Utility;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * This Controller handles the Home Activity.
 *
 * @author Kishorè Shanto
 */
public class HomeActivityController extends Controller {

    public Pane parent;
    public TextField searchField;

    public void onCreateEventButton() {
        Utility.createStage("create-event.fxml");
    }

    public void onDeleteEventButton() {
        Utility.createStage("delete-event.fxml");
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

    }
}

