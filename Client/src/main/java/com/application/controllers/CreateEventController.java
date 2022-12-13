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

public class CreateEventController {
    @FXML
    private TextField create_event_name;
    @FXML
    private TextField create_event_category;
    @FXML
    private TextArea create_event_description;
    @FXML
    private DatePicker create_event_date;
    @FXML
    private TextField create_event_start_time;
    @FXML
    private TextField create_event_end_time;

    public void onCreateButtonListener() throws Exception {

        Event event = new Event(User.getEmail(), create_event_name.getText(), create_event_description.getText(), create_event_category.getText(), create_event_date.getValue().toString(), Integer.parseInt(create_event_start_time.getText()), Integer.parseInt(create_event_end_time.getText()));

        if (Sequence.createEventSequence(event)) {
            DialogBox.showDialogue("Success", "Event created successfully.", DialogBox.SUCCESS_DIALOG_BOX);
            Utility.deleteStage(create_event_category);
        } else {
            DialogBox.showDialogue("Error", "Event creation failed.", DialogBox.ERROR_DIALOG_BOX);
            Utility.deleteStage(create_event_category);
        }
    }

    public void onCancelButtonListener() {
        Utility.deleteStage(create_event_category);
    }
}
