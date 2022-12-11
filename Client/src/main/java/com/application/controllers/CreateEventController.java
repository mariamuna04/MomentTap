package com.application.controllers;

import com.application.serialShared.Event;
import com.application.client.User;
import com.application.connection.Connection;
import com.application.utility.NetworkRequestCodes;
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

    public void onCreateButtonListener(ActionEvent actionEvent) throws Exception {
        String event_name = create_event_name.getText();
        String event_category = create_event_category.getText();
        String event_description = create_event_description.getText();
        String event_date = create_event_date.getValue().toString();
        String event_start_time = create_event_start_time.getText();
        String event_end_time = create_event_end_time.getText();

        Event event = new Event(User.getEmail(), event_name, event_description,event_date,
                Integer.parseInt(event_start_time), Integer.parseInt(event_end_time), event_category);

        Connection.sendRequestCode(NetworkRequestCodes.CREATE_EVENT);
        Connection.sendObject(event);


    }

    public void onCancelButtonListener(ActionEvent actionEvent) {

    }
}
