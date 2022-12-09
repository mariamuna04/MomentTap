package com.application.controllers;

import com.application.client.Event;
import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CreateEventController {
    public TextField create_event_name;
    public TextField create_event_category;
    public TextArea create_event_description;
    public DatePicker create_event_date;
    public TextField create_event_start_time;
    public TextField create_event_end_time;

    public void onCreateButtonListener(ActionEvent actionEvent) {
        String event_name = create_event_name.getText();
        String event_category = create_event_category.getText();
        String event_description = create_event_description.getText();
        String event_date = create_event_date.getValue().toString();
        String event_start_time = create_event_start_time.getText();
        String event_end_time = create_event_end_time.getText();

        Event event = new Event("emailnaiii", event_name, event_description,event_date,
                Integer.parseInt(event_start_time), Integer.parseInt(event_end_time), event_category);

        System.out.println(event);
    }

    public void onCancelButtonListener(ActionEvent actionEvent) {

    }
}
