// Created by Maria Muna on Nov 16 2022 13:54

package com.application.controllers;

import com.application.client.Sequence;
import com.application.utility.DialogBox;
import com.application.utility.Utility;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class SignInController extends Controller {
    @FXML
    private Pane parent;
    @FXML
    private TextField emailTextField;
    @FXML
    private PasswordField passwordTextField;

    public void onSignInListener() throws Exception {
        if (Sequence.signInSequence(emailTextField.getText(), passwordTextField.getText())) {
            DialogBox.showDialogue("Success", "You have successfully signed in.", DialogBox.SUCCESS_DIALOG_BOX);
            Utility.changeScene(parent, "home-activity.fxml");
        } else
            DialogBox.showDialogue("Error", "Invalid username or password.", DialogBox.ERROR_DIALOG_BOX);
    }

    public void onSignIUpListener() throws Exception {
        Utility.changeScene(parent, "sign-up-activity.fxml");
    }

    public void onAboutUsListener() throws Exception {
        Utility.changeScene(parent, "about-us-activity.fxml");
    }
}