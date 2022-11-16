package com.application.client;

import com.application.utils.Utility;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class SignInController {

    public TextField usernameTextField;
    public PasswordField passwordTextField;

    public void onSignInListener() {
        System.out.println("Username: " + usernameTextField.getText());
        System.out.println("Password: " + passwordTextField.getText());
    }

    public void onSignIUpListener() throws IOException {
        Utility.changeScene(usernameTextField, "sign-up-activity.fxml");
    }

    public void onAboutUsListener() {

    }
}