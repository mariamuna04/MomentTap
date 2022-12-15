// Created by Maria Muna on Nov 16 2022 16:31

package com.application.controllers;

import com.application.client.Sequence;
import com.application.utility.DialogBox;
import com.application.utility.Utility;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;


public class SignUpController extends Controller {
    @FXML
    private Label signUpErrorLabel;
    @FXML
    private Pane parent;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private PasswordField passwordTextField;


    // FIXME: If server is not running, it will show email already exists
    public void onSignUpAction() throws Exception {
        if (nameTextField.getText().isEmpty() || emailTextField.getText().isEmpty() || passwordTextField.getText().isEmpty()) {
            signUpErrorLabel.setText("Please fill all the fields");
        } else if (Sequence.signUpSequence(nameTextField.getText(), emailTextField.getText(), passwordTextField.getText())) {
            DialogBox.showDialogue("Sign Up Successful", "You have successfully signed up. Please sign in to continue.", DialogBox.SUCCESS_DIALOG_BOX);
            Utility.changeScene(parent, "sign-in-activity.fxml");

        } else signUpErrorLabel.setText("Email already exists");
    }

    public void onSignInAction() throws Exception {
        Utility.changeScene(parent, "sign-in-activity.fxml");
    }
}
