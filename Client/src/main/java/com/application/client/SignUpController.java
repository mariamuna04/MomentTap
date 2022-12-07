// Created by Maria Muna on Nov 16 2022 16:31

package com.application.client;

import com.application.utils.NetworkRequest;
import com.application.utils.Utility;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.DataOutputStream;
import java.net.Socket;


public class SignUpController extends Controller {
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField emailTextField;
    @FXML
    private PasswordField passwordTextField;

    public void onSignUpAction() {
        // TODO: Send data to Server
        Socket socket = null;
        try {
            socket = new Socket("localhost", 8080);

            DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
            dataOutputStream.writeInt(NetworkRequest.SIGN_UP_REQUEST);
            dataOutputStream.writeUTF(nameTextField.getText());
            dataOutputStream.writeUTF(emailTextField.getText());
            dataOutputStream.writeUTF(passwordTextField.getText());

            System.out.println("Data Sent");

            Utility.changeScene(nameTextField, "sign-in-activity.fxml");


        } catch (Exception e) {
            System.err.println("Server is not running");
        }
    }

    public void onSignInAction() throws Exception {
        Utility.changeScene(nameTextField, "sign-in-activity.fxml");
    }
}
