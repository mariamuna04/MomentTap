package com.application.client;

import com.application.utils.Utility;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.io.IOException;

public class SignInController extends Controller {

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