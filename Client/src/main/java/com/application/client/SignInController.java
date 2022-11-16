package com.application.client;

import com.application.utils.Utility;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class SignInController extends Controller {
    @FXML
    private TextField usernameTextField;
    @FXML private PasswordField passwordTextField;

    public void onSignInListener() {
        System.out.println("Username: " + usernameTextField.getText());
        System.out.println("Password: " + passwordTextField.getText());
    }

    public void onSignIUpListener() throws Exception {
        Utility.changeScene(usernameTextField, "sign-up-activity.fxml");
    }

    public void onAboutUsListener() throws Exception {
        Utility.changeScene(usernameTextField, "about-us-activity.fxml");
    }
}