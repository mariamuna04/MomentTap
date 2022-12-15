package com.application.controllers;

import com.application.client.Sequence;
import com.application.client.User;
import com.application.serialShared.Event;
import com.application.utility.DialogBox;
import com.application.utility.Utility;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class CreateEventController extends Controller {
    @FXML
    private VBox parent;
    @FXML
    private TextField event_name;
    @FXML
    private TextField event_category;
    @FXML
    private TextArea event_description;
    @FXML
    private DatePicker event_date;
    @FXML
    private TextField event_start_time;
    @FXML
    private TextField event_end_time;

    public void onCreateButtonListener() throws Exception {
        Event event = new Event(User.getEmail(), event_name.getText(), event_description.getText(), event_category.getText(), event_date.getValue().toString(), Integer.parseInt(event_start_time.getText()), Integer.parseInt(event_end_time.getText()));
        if (Sequence.createEventSequence(event)) {
            DialogBox.showDialogue("Success", "Event created successfully.", DialogBox.SUCCESS_DIALOG_BOX);
            Utility.deleteStage(parent);
        } else {
            DialogBox.showDialogue("Error", "Event creation failed.", DialogBox.ERROR_DIALOG_BOX);
            Utility.deleteStage(event_category);
        }
    }

    public void onCancelButtonListener() {
        Utility.deleteStage(parent);
    }


}
