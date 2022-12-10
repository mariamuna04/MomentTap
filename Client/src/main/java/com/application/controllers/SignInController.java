// Created by Maria Muna on Nov 16 2022 13:54

package com.application.controllers;

import com.application.client.User;
import com.application.connection.Connection;
import com.application.utility.NetworkRequestCodes;
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
        String _email_ = usernameTextField.getText();
        String _password_ = passwordTextField.getText();

        Connection.setConnection();

        Connection.sendRequestCode(NetworkRequestCodes.SIGN_IN_REQUEST);
        Connection.sendString("");
        Connection.sendString(_email_);
        Connection.sendString(_password_);

        int response = Connection.receiveRequestCode();

        if(response == NetworkRequestCodes.USER_FOUND_FROM_DATABASE) {
            String name = Connection.receiveString();
            String email = Connection.receiveString();
            User.setUser(name, email);

            Utility.changeScene(usernameTextField, "home-activity.fxml");

        } else {
//            Utility.showAlert("Error", "User Not Found");
            System.err.println("User Not Found");

        }

    }

    public void onSignIUpListener() throws Exception {
        Utility.changeScene(usernameTextField, "sign-up-activity.fxml");
    }

    public void onAboutUsListener() throws Exception {
        Utility.changeScene(usernameTextField, "about-us-activity.fxml");
    }
}