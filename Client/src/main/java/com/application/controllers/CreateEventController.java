package com.application.controllers;

import com.application.client.Client;
import com.application.client.Sequence;
import com.application.client.User;
import com.application.serialShared.Event;
import com.application.utility.Date;
import com.application.utility.DialogBox;
import com.application.utility.Time;
import com.application.utility.Utility;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class CreateEventController extends Controller {
    @FXML
    private Label createEventMessageTextField;
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

    public void onCreateButtonListener() {
        if (event_name.getText().isEmpty() || event_category.getText().isEmpty() || event_date.getValue() == null || event_start_time.getText().isEmpty() || event_end_time.getText().isEmpty()) {
            createEventMessageTextField.setText("Please fill in all the fields");
            return;
        }

        Date date = Date.parseDate(event_date.getValue().toString());
        boolean validDate = Date.compareDate(date);

        Time startTime = Time.parseTime(event_start_time.getText());
        Time endTime = Time.parseTime(event_end_time.getText());
        boolean validTime = Time.compareTime(startTime, endTime) && startTime.hour() >= 0 && startTime.hour() <= 23 && endTime.hour() >= 0 && endTime.hour() <= 23 && startTime.minute() >= 0 && startTime.minute() <= 59 && endTime.minute() >= 0 && endTime.minute() <= 59;

        if (validDate && validTime) {
            Event event = new Event(User.getEmail(), event_name.getText(), event_description.getText(), event_category.getText(),
                    event_date.getValue().toString(), Time.parseTime(event_start_time.getText()), Time.parseTime(event_end_time.getText()), "no");

            Client.alertEvent.add(event);

            if (Sequence.createEventSequence(event)) {
                DialogBox.showDialogue("Success", "Event created successfully.", DialogBox.SUCCESS_DIALOG_BOX);
                Utility.deleteStage(parent);
            } else {
                DialogBox.showDialogue("Error", "Event creation failed.", DialogBox.ERROR_DIALOG_BOX);
                Utility.deleteStage(parent);
            }
        } else {
            if (!validDate) {
                createEventMessageTextField.setText("Please enter a valid date");
            }

            if (!validTime) {
                createEventMessageTextField.setText("Please enter a valid time");
            }
        }
    }

    public void onCancelButtonListener() {
        Utility.deleteStage(parent);
    }


}


// Project Finished on Mon Jan 2 2023 15:30:00 GMT+0600 (Bangladesh Standard Time)
