package com.application.controllers;

import com.application.client.User;
import com.application.connection.Connection;
import com.application.utility.DialogBox;
import com.application.utility.NetworkRequestCodes;
import com.application.utility.Utility;
import javafx.event.ActionEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class EditProfileController {
    public TextField newNameTF;
    public PasswordField oldPassTF;
    public PasswordField newPassTf;
    public PasswordField confirmPassTF;
    public Pane parent;

    public void onConfirmButton(ActionEvent actionEvent) throws Exception {
        if(newNameTF.getText().equals("") && oldPassTF.getText().equals("") && newPassTf.getText().equals("") && confirmPassTF.getText().equals("")){
            DialogBox.showDialogue("Error","Please fill in at least one field",DialogBox.ERROR_DIALOG_BOX);
        }
        else if(!newPassTf.getText().equals(confirmPassTF.getText())){
            DialogBox.showDialogue("Error","Passwords do not match",DialogBox.ERROR_DIALOG_BOX);
        } else if (!oldPassTF.getText().equals(User.getPassword())) {
            DialogBox.showDialogue("Error","Incorrect password",DialogBox.ERROR_DIALOG_BOX);
        } else {
            Connection.sendRequestCode(NetworkRequestCodes.UPDATE_EVENT_REQUEST);
            Connection.sendPrimitiveObject(newNameTF.getText());
            Connection.sendPrimitiveObject(newPassTf.getText());
            if(Connection.receiveRequestCode() == NetworkRequestCodes.UPDATE_EVENT_SUCCESSFUL){
                DialogBox.showDialogue("Success","Profile updated successfully",DialogBox.SUCCESS_DIALOG_BOX);
                Utility.deleteStage(parent);
            } else {
                DialogBox.showDialogue("Error","Profile update failed",DialogBox.ERROR_DIALOG_BOX);
            }


        }
    }

    public void onCancelButton(ActionEvent actionEvent) {
        Utility.deleteStage(parent);
    }
}
