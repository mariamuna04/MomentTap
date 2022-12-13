// Created by Kishorè Shanto on 12/10/22 at 19:40

package com.application.controllers;

import com.application.client.Sequence;
import com.application.utility.DialogBox;
import com.application.utility.Utility;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class DeleteEventController {

    @FXML
    private VBox parent;
    @FXML
    private TextField create_event_name;
    @FXML
    private DatePicker create_event_date;

    public void onDeleteButtonListener() throws Exception {
        String event_name = create_event_name.getText();
        String event_date = create_event_date.getValue().toString();

        try {
            if (Sequence.deleteEventSequence(event_name, event_date)) {
                DialogBox.showDialogue("Success", "Event deleted successfully", DialogBox.SUCCESS_DIALOG_BOX);
                Utility.deleteStage(parent);

            } else {
                DialogBox.showDialogue("Error", "Event deletion failed", DialogBox.ERROR_DIALOG_BOX);
                Utility.deleteStage(parent);
            }
        } catch (Exception e) {
            DialogBox.showDialogue("Error", "Something went wrong", DialogBox.ERROR_DIALOG_BOX);
        }


    }

    public void onCancelButtonListener() {
        Utility.deleteStage(parent);
    }
}
