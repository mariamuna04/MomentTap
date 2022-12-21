// Created by Kishorè Shanto on Nov 16 2022 16:33

package com.application.controllers;

import com.application.client.Sequence;
import com.application.client.User;
import com.application.serialShared.Event;
import com.application.utility.Date;
import com.application.utility.EventCard;
import com.application.utility.Utility;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * This Controller handles the Home Activity.
 */
public class HomeActivityController extends Controller {

    public VBox future_events;
    public VBox past_events;

    public boolean primaryInitialized = false;
    public Button profileButton = new Button();
    public Pane profileView;
    public Pane backgroundOverlay;
    public Label profileViewName;
    public Label profileViewEmail;
    public Label profileViewUpcoming;
    public Label profileViewCompleted;

    public boolean toBeRefreshed = false;

    @Override
    public void init() throws Exception {
        profileButton.setText(User.getName());
        profileViewName.setText(User.getName());
        profileViewEmail.setText(User.getEmail());

        if (future_events.getChildren().size() == 0 && past_events.getChildren().size() == 0 && !primaryInitialized) {
            onAllEventButtonListener();
            profileViewUpcoming.setText(String.valueOf(User.sortedFutureEvents.size()));
            profileViewUpcoming.setText(String.valueOf(User.sortedPastEvents.size()));

            primaryInitialized = true;
        }
    }


    public Pane parent;
    public TextField searchField;

    public void onCreateEventButton() throws Exception {
        Utility.createStage("create-event-activity.fxml");
    }

    public void onDeleteEventButton() {
        Utility.createStage("delete-event-activity.fxml");
        Platform.runLater(() -> {
            try {
                init();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
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

    public void onSearchButton() throws Exception {
        makeCardView(searchField.getText());
        this.init();
    }


    public void onEditProfileButton(ActionEvent actionEvent) {
        Utility.createStage("edit-profile-activity.fxml");
    }

    public void onAllEventButtonListener() throws Exception {
        makeCardView("");
        this.init();

    }

    public void onPersonalButtonListener() throws Exception {
        makeCardView("Personal");
        this.init();

    }

    public void onWorkButtonListener() {
        makeCardView("Work");
    }

    public void onUniversityButtonListener() throws Exception {
        makeCardView("University");
        this.init();

    }

    public void onOthersButtonListener() {
        // makeCardView("Others");


    }


    private void makeCardView(String keyword) {
        User.cleanMemory();
        future_events.getChildren().clear();
        past_events.getChildren().clear();

        Sequence.searchSequence(keyword);

        if (User.events.size() == 0) {
            Label labelFuture = new Label("No Upcoming event found");
            Label labelPast = new Label("No Completed event found");
            labelFuture.setStyle("-fx-font-size: 20px; -fx-text-fill: #ffffff; -fx-font-weight: bold;");
            labelPast.setStyle("-fx-font-size: 20px; -fx-text-fill: #ffffff; -fx-font-weight: bold;");
            future_events.getChildren().add(labelFuture);
            past_events.getChildren().add(labelPast);
        } else {
            for (Event e : User.events) {
                Date date = Date.parseDate(e.event_date());
                if (Date.compareDate(date)) {
                    User.sortedFutureEvents.add(e);
                } else {
                    User.sortedPastEvents.add(e);
                }
            }

            for (Event e : User.sortedFutureEvents) {
                EventCard eventCard = new EventCard();
                VBox vBox = eventCard.makeCard(e.event_date(), e.event_name(), e.event_category(),
                        e.event_description(), e.event_start_time(), e.event_end_time(), 1);
                future_events.getChildren().add(vBox);
            }

            for (Event e : User.sortedPastEvents) {
                EventCard eventCard = new EventCard();
                VBox vBox = eventCard.makeCard(e.event_date(), e.event_name(), e.event_category(),
                        e.event_description(), e.event_start_time(), e.event_end_time(), 0);
                past_events.getChildren().add(vBox);
            }
        }

    }

    public void onProfileButtonListener() {
        profileView.setVisible(!profileView.isVisible());
        BoxBlur blur = new BoxBlur(3, 3, 3);
        backgroundOverlay.setEffect(profileView.isVisible() ? blur : null);
        backgroundOverlay.setVisible(!backgroundOverlay.isVisible());
    }
}

