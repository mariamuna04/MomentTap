// Created by Maria Muna on Nov 16 2022 13:54

package com.application.controllers;

import com.application.client.User;
import com.application.connection.Connection;
import com.application.controllers.Controller;
import com.application.utils.NetworkRequest;
import com.application.utils.Utility;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class SignInController extends Controller {
    @FXML
    private TextField usernameTextField;
    @FXML
    private PasswordField passwordTextField;

    public void onSignInListener() throws Exception {
        String _email_ = usernameTextField.getText();
        String password = passwordTextField.getText();

        Connection.setConnection();

        Connection.dataOutputStream.writeInt(NetworkRequest.SIGN_IN_REQUEST);
        Connection.dataOutputStream.writeUTF("");
        Connection.dataOutputStream.writeUTF(_email_);
        Connection.dataOutputStream.writeUTF(password);

        int response = Connection.dataInputStream.readInt();

        if(response == NetworkRequest.USER_FOUND_FROM_DATABASE) {
            String name = Connection.dataInputStream.readUTF();
            String email = Connection.dataInputStream.readUTF();
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