package com.application.controllers;

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

    public void onCreateButtonListener() throws Exception {
        if(event_name.getText().isEmpty() || event_category.getText().isEmpty()  || event_date.getValue() == null || event_start_time.getText().isEmpty() || event_end_time.getText().isEmpty()) {
            createEventMessageTextField.setText("Please fill in all the fields");
            return;
        }

        Date date = Date.parseDate(event_date.getValue().toString());
        boolean validDate = Date.compareDate(date);

        // FIXME: FIX TIME VALIDATION
        // WARNING
        //   Fixing time will break sql codes


//        Time startTime = Time.parseTime(event_start_time.getText());
//        Time endTime = Time.parseTime(event_end_time.getText());
//        boolean validTime = Time.compareTime(startTime, endTime);

        if(validDate) {
            Event event = new Event(User.getEmail(), event_name.getText(), event_description.getText(), event_category.getText(), event_date.getValue().toString(), Integer.parseInt(event_start_time.getText()), Integer.parseInt(event_end_time.getText()));
            if (Sequence.createEventSequence(event)) {
                DialogBox.showDialogue("Success", "Event created successfully.", DialogBox.SUCCESS_DIALOG_BOX);
                Utility.deleteStage(parent);
            } else {
                DialogBox.showDialogue("Error", "Event creation failed.", DialogBox.ERROR_DIALOG_BOX);
                Utility.deleteStage(parent);
            }
        } else {
            createEventMessageTextField.setText("Date cannot be in the past");
        }
    }

    public void onCancelButtonListener() {
        Utility.deleteStage(parent);
    }


}
