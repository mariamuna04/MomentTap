package com.application.controllers;

import com.application.connection.Connection;
import com.application.utility.Utility;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ShareActivityController {
    public TextField emailTextField;
    public Label errorLabel;

    public void onShareButton(ActionEvent actionEvent) {
        String email = emailTextField.getText();
        if (email.equals("")) {
            errorLabel.setText("Please enter an email");
            return;
        }
        Connection.sendPrimitiveObject(emailTextField.getText());
    }

    public void onCancelButton(ActionEvent actionEvent) {
        Utility.deleteStage(emailTextField);
    }
}
