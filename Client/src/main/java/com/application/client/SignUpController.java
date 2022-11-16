package com.application.client;

import com.application.utils.Utility;
import javafx.event.ActionEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class SignUpController extends Controller{
    public TextField nameTextField;
    public TextField emailTextField;
    public PasswordField passwordTextField;

    public void onSignUpAction() {

    }

    public void onSignInAction() throws IOException {
        Utility.changeScene(nameTextField, "sign-in-activity.fxml");
    }
}
