// Created by Maria Muna on Nov 16 2022 16:31

package com.application.controllers;

import com.application.client.Sequence;
import com.application.utility.DialogBox;
import com.application.utility.Utility;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;


public class SignUpController extends Controller {
    @FXML
    private Pane parent;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private PasswordField passwordTextField;

    public void onSignUpAction() throws Exception {
        if (Sequence.signUpSequence(nameTextField.getText(), emailTextField.getText(), passwordTextField.getText())) {
            Utility.changeScene(parent, "sign-in-activity.fxml");
        } else
            DialogBox.showDialogue("Error", "Email or password incorrect", DialogBox.ERROR_DIALOG_BOX);
    }

    public void onSignInAction() throws Exception {
        Utility.changeScene(parent, "sign-in-activity.fxml");
    }
}
