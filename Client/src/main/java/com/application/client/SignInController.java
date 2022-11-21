// Created by Maria Muna on Nov 16 2022 13:54

package com.application.client;

import com.application.security.Authentication;
import com.application.utils.Utility;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignInController extends Controller {
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordTextField;

    public void onSignInListener() {
        String email = usernameTextField.getText();
        String password = passwordTextField.getText();
        boolean isAuth = Authentication.authenticate(email, password);

        if (isAuth) {
            System.out.println("Authenticated");
        } else {
            System.out.println("Not Authenticated");
        }


    }

    public void onSignIUpListener() throws Exception {
        Utility.changeScene(usernameTextField, "sign-up-activity.fxml");
    }

    public void onAboutUsListener() throws Exception {
        Utility.changeScene(usernameTextField, "about-us-activity.fxml");
    }
}