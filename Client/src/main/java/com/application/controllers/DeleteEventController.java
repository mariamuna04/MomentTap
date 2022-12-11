// Created by Kishorè Shanto on 12/10/22 at 19:40

package com.application.controllers;

import com.application.connection.Connection;
import com.application.utility.DialogBox;
import com.application.utility.NetworkRequestCodes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DeleteEventController {

    @FXML
    private
    VBox parent;
    @FXML
    private TextField create_event_name;
    @FXML
    private DatePicker create_event_date;

    public void onDeleteButtonListener(ActionEvent actionEvent) throws Exception {
        String event_name = create_event_name.getText();
        String event_date = create_event_date.getValue().toString();

        Connection.sendRequestCode(NetworkRequestCodes.DELETE_EVENT);
        Connection.sendString(event_name);
        Connection.sendString(event_date);

        int response = Connection.receiveRequestCode();

        if (response == NetworkRequestCodes.DELETE_EVENT_CONFIRMATION) {
            DialogBox.showDialogue("Success", "Event deleted successfully.", DialogBox.SUCCESS_DIALOG_BOX);
            Stage s = (Stage) create_event_name.getScene().getWindow();
            s.close();

        } else {
            DialogBox.showDialogue("Error", "Event deletion failed.", DialogBox.ERROR_DIALOG_BOX);
            Stage s = (Stage) create_event_name.getScene().getWindow();
            s.close();
        }


    }

    public void onCancelButtonListener(ActionEvent actionEvent) {
        Stage s = (Stage) create_event_name.getScene().getWindow();
        s.close();
    }
}
