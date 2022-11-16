package com.application.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class SignInController {

    public TextField usernameTextField;
    public PasswordField passwordTextField;

    public void onSignInListener(ActionEvent actionEvent) {
        System.out.println("Username: " + usernameTextField.getText());
        System.out.println("Password: " + passwordTextField.getText());
    }

    public void onSignIUpListener(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(SignIn.class.getResource("sign-up-activity.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = (Stage) usernameTextField.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void onAboutUsListener(ActionEvent actionEvent) {
    }
}