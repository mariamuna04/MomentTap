// Created by Kishorè Shanto on 12/23/22 at 01:21

package com.application.controllers;

import com.application.utility.Utility;
import javafx.event.ActionEvent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class UpdateEventController {

    public VBox parent  ;
    public TextField event_name ;
    public TextField event_category;
    public TextArea event_description;
    public DatePicker event_date;
    public TextField event_end_time;
    public TextField event_start_time;
    public Label createEventMessageTextField;

    public void onCreateButtonListener(ActionEvent actionEvent) {

    }

    public void onCancelButtonListener(ActionEvent actionEvent) {
        Utility.deleteStage(parent);
    }
}
