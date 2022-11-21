// Created by Maria Muna on Nov 16 2022 16:31

package com.application.client;

import com.application.utils.Utility;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class SignUpController extends Controller {
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private PasswordField passwordTextField;

    public void onSignUpAction() {

    }

    public void onSignInAction() throws Exception {
        Utility.changeScene(nameTextField, "signInActivity.fxml");
    }
}
