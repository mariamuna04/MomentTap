// Created by Maria Muna on Nov 16 2022 13:54

package com.application.controllers;

import com.application.client.Sequence;
import com.application.utility.Utility;
import com.application.utility.Verify;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class SignInController extends Controller {
    @FXML
    private Label signInErrorLabel;
    @FXML
    private Pane parent;
    @FXML
    private TextField emailTextField;
    @FXML
    private PasswordField passwordTextField;

    public void onSignInListener() throws Exception {
        if (emailTextField.getText().isEmpty() || passwordTextField.getText().isEmpty()) {
            signInErrorLabel.setText("Please fill all the fields");;
        } else if(!Verify.isEmailValid(emailTextField.getText()) && !Verify.isPasswordValid(passwordTextField.getText())) {
            signInErrorLabel.setText("Email Format or Password Incorrect");;
        }else if (Sequence.signInSequence(emailTextField.getText(), Verify.md5(passwordTextField.getText()))) {
            Utility.changeScene(parent, "home-activity.fxml");
            // FIXME: If server is not running, it will show email already exists
        } else signInErrorLabel.setText("Invalid Email or Password");
    }

    public void onSignIUpListener() throws Exception {
        Utility.changeScene(parent, "sign-up-activity.fxml");
    }

    public void onAboutUsListener() throws Exception {
        // FIXME: Testing purpose
//        emailTextField.setText("admin@admin.com");
//        passwordTextField.setText(".");
//        onSignInListener();
        Utility.changeScene(parent, "about-us-activity.fxml");
    }
}