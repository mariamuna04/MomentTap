package com.application.controllers;

import com.application.client.Alert;
import com.application.client.Client;
import com.application.client.Sequence;
import com.application.client.User;
import com.application.connection.Connection;
import com.application.serialShared.Event;
import com.application.utility.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * This Controller handles the Home Activity.
 */
public class HomeActivityController extends Controller {

    public Pane graphicalCalendar;
    @FXML
    private Pane parent;
    @FXML
    private TextField searchField;

    @FXML
    private VBox future_events;
    @FXML
    private VBox past_events;
    @FXML
    private Button profileButton;
    @FXML
    private Pane profileView;
    @FXML
    private Pane backgroundOverlay;
    @FXML
    private Label profileViewName;
    @FXML
    private Label profileViewEmail;
    @FXML
    private Pane profileEditView;
    @FXML
    private TextField nameField;
    @FXML
    private PasswordField oldPasswordField;
    @FXML
    private PasswordField newPasswordField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private Label editProfileMessage;

    @Override
    public void init() throws Exception {
        profileButton.setText(User.getName());
        profileViewName.setText(User.getName());
        profileViewEmail.setText(User.getEmail());

        if (future_events.getChildren().size() == 0 && past_events.getChildren().size() == 0) {
            onAllEventButtonListener();
        }

        graphicalCalendar.getChildren().removeAll(graphicalCalendar.getChildren());
        graphicalCalendar.getChildren().add(new GraphicalCalendar());

        if(!Alert.isRunning){
            new Thread(new Alert()).start();
        }
    }


    public void onCreateEventButton() {
        Utility.createStage("create-event-activity.fxml");
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


    public void onEditProfileButton() {
        profileEditView.setVisible(true);

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
        makeCardView("Others");
        System.out.println(Client.alertEvent);
    }


    private void makeCardView(String keyword) {
        User.cleanMemory();
        future_events.getChildren().clear();
        past_events.getChildren().clear();

        Sequence.searchSequence(keyword);

        if (User.events.size() == 0) {
            Label labelFuture = new Label("No Upcoming event found");
            labelFuture.setPadding(new javafx.geometry.Insets(40, 0, 0, 0));
            Label labelPast = new Label("No Completed event found");
            labelPast.setPadding(new javafx.geometry.Insets(40, 0, 0, 0));
            labelFuture.setStyle("-fx-font-size: 20px; -fx-text-fill:  #49599a; -fx-font-weight: bold; ");
            labelPast.setStyle("-fx-font-size: 20px; -fx-text-fill:  #49599a; -fx-font-weight: bold;");
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

            int sortedFutureEventsSize = User.sortedFutureEvents.size();
            int sortedPastEventsSize = User.sortedPastEvents.size();

            for (int i = 0; i < sortedFutureEventsSize; i++) {
                Event e = User.sortedFutureEvents.poll();
                ////--------------------------------------------------------------------------------------------
                if(!Client.alertEventAdded) {
                    Client.alertEvent.add(e);
                }
                if (e != null) {
                    EventCard eventCard = new EventCard();
                    VBox vBox = eventCard.makeCard(e.event_date(), e.event_name(), e.event_category(), e.event_description(), e.event_start_time(), e.event_end_time(), 1, e.isShared());
                    future_events.getChildren().add(vBox);
                } else {
                    System.err.println("Event is null [HomeActivityController.makeCardView :: 144]");
                }
            }
            Client.alertEventAdded = true;

            for (int i = 0; i < sortedPastEventsSize; i++) {
                Event e = User.sortedPastEvents.poll();
                if (e != null) {
                    EventCard eventCard = new EventCard();
                    VBox vBox = eventCard.makeCard(e.event_date(), e.event_name(), e.event_category(), e.event_description(), e.event_start_time(), e.event_end_time(), 0, e.isShared());
                    past_events.getChildren().add(vBox);
                } else {
                    System.err.println("Event is null [HomeActivityController.makeCardView :: 156]");
                }
            }
        }
    }

    public void onProfileButtonListener() {
        profileView.setVisible(!profileView.isVisible());
        if (profileEditView.isVisible()) {
            profileEditView.setVisible(false);
        }
        BoxBlur blur = new BoxBlur(3, 3, 3);
        backgroundOverlay.setEffect(profileView.isVisible() ? blur : null);
        backgroundOverlay.setVisible(!backgroundOverlay.isVisible());
    }

    public void onSaveEditProfileButton() throws Exception {
        String _currentPassword = Verify.md5(oldPasswordField.getText());
        if (nameField.getText().equals("")) {
            if (newPasswordField.getText().equals("") && confirmPasswordField.getText().equals("")) {
                editProfileMessage.setStyle("-fx-text-fill: #000000;");
                editProfileMessage.setText("Nothing has been changed");
            } else if (newPasswordField.getText().equals(confirmPasswordField.getText())) {
                assert _currentPassword != null;
                if (_currentPassword.equals(User.getPassword())) {
                    // System.out.println("CHANGE PASSWORD");
                    Connection.sendRequestCode(NetworkRequestCodes.EDIT_PROFILE_REQUEST);
                    Connection.sendPrimitiveObject("");
                    Connection.sendPrimitiveObject(Verify.md5(newPasswordField.getText()));
                    User.setUser(User.getName(), User.getEmail(), Verify.md5(newPasswordField.getText()));

                    if (Connection.receiveRequestCode() == NetworkRequestCodes.UPDATE_EVENT_SUCCESSFUL) {
                        DialogBox.showDialogue("Success", "Profile updated successfully", DialogBox.SUCCESS_DIALOG_BOX);
                        onCancelEditProfileButton();
                        this.init();
                    } else {
                        DialogBox.showDialogue("Error", "Profile update failed", DialogBox.ERROR_DIALOG_BOX);
                    }
                } else {
                    editProfileMessage.setStyle("-fx-text-fill: #ff0000;");
                    editProfileMessage.setText("Current password do not match");
                }
            } else {
                editProfileMessage.setStyle("-fx-text-fill: #ff0000;");
                editProfileMessage.setText("New passwords do not match");
            }
        } else {
            if (newPasswordField.getText().equals("") && confirmPasswordField.getText().equals("")) {
                assert _currentPassword != null;
                if (_currentPassword.equals(User.getPassword())) {
                    // System.out.println("CHANGE NAME");
                    Connection.sendRequestCode(NetworkRequestCodes.EDIT_PROFILE_REQUEST);
                    Connection.sendPrimitiveObject(nameField.getText());
                    Connection.sendPrimitiveObject(User.getPassword());
                    User.setUser(nameField.getText(), User.getEmail(), User.getPassword());

                    if (Connection.receiveRequestCode() == NetworkRequestCodes.UPDATE_EVENT_SUCCESSFUL) {
                        DialogBox.showDialogue("Success", "Profile updated successfully", DialogBox.SUCCESS_DIALOG_BOX);
                        onCancelEditProfileButton();
                        this.init();
                    } else {
                        DialogBox.showDialogue("Error", "Profile update failed", DialogBox.ERROR_DIALOG_BOX);
                    }
                } else {
                    editProfileMessage.setStyle("-fx-text-fill: #ff0000;");
                    editProfileMessage.setText("Current password do not match");
                }
            } else if (newPasswordField.getText().equals(confirmPasswordField.getText())) {
                assert _currentPassword != null;
                if (_currentPassword.equals(User.getPassword())) {
                    //System.out.println("CHANGE NAME AND PASSWORD");
                    Connection.sendRequestCode(NetworkRequestCodes.EDIT_PROFILE_REQUEST);
                    Connection.sendPrimitiveObject(nameField.getText());
                    Connection.sendPrimitiveObject(Verify.md5(newPasswordField.getText()));
                    User.setUser(nameField.getText(), User.getEmail(), Verify.md5(newPasswordField.getText()));

                    if (Connection.receiveRequestCode() == NetworkRequestCodes.UPDATE_EVENT_SUCCESSFUL) {
                        DialogBox.showDialogue("Success", "Profile updated successfully", DialogBox.SUCCESS_DIALOG_BOX);
                        onCancelEditProfileButton();
                        this.init();
                    } else {
                        DialogBox.showDialogue("Error", "Profile update failed", DialogBox.ERROR_DIALOG_BOX);
                    }
                } else {
                    editProfileMessage.setStyle("-fx-text-fill: #ff0000;");
                    editProfileMessage.setText("Current password do not match");
                }
            } else {
                editProfileMessage.setStyle("-fx-text-fill: #ff0000;");
                editProfileMessage.setText("New passwords do not match");
            }
        }
    }

    public void onCancelEditProfileButton() {
        profileEditView.setVisible(false);
        newPasswordField.setText("");
        confirmPasswordField.setText("");
        oldPasswordField.setText("");
        nameField.setText("");
        editProfileMessage.setText("");
    }

    public void onSearchFieldKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode().isLetterKey() || keyEvent.getCode().isDigitKey() || keyEvent.getCode().isWhitespaceKey() || keyEvent.getCode() == KeyCode.BACK_SPACE) {
            try {
                makeCardView(searchField.getText());
                this.init();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void onSharedEventButton() {
        makeCardView("shared");
    }
}

