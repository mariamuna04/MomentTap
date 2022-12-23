package com.application.controllers;

import com.application.client.User;
import com.application.connection.Connection;
import com.application.utility.DialogBox;
import com.application.utility.NetworkRequestCodes;
import com.application.utility.Utility;
import javafx.event.ActionEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class EditProfileController {
    public TextField newNameTF;
    public PasswordField oldPassTF;
    public PasswordField newPassTf;
    public PasswordField confirmPassTF;
    public Pane parent;

    public void onConfirmButton(ActionEvent actionEvent) throws Exception {

    }

    public void onCancelButton(ActionEvent actionEvent) {
        Utility.deleteStage(parent);
    }
}
