// Created by Maria Muna on Nov 16 2022 13:54

package com.application.controllers;

import com.application.client.Sequence;
import com.application.utility.Utility;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignInController extends Controller {
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordTextField;

    public void onSignInListener() throws Exception {
        if(Sequence.signInSequence(usernameTextField.getText(), passwordTextField.getText())) {
            Utility.changeScene(usernameTextField, "home-activity.fxml");
        } else {
            // GUI Dialog box
        }
    }

    public void onSignIUpListener() throws Exception {
        Utility.changeScene(usernameTextField, "sign-up-activity.fxml");
    }

    public void onAboutUsListener() throws Exception {
        Utility.changeScene(usernameTextField, "about-us-activity.fxml");
    }
}