// Created by Kishorè Shanto on 12/11/22 at 19:59

package com.application.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class DialogBoxController extends Controller {

    @FXML
    private VBox parent;
    @FXML
    private ImageView imageViewID;
    @FXML
    private Label heading;
    @FXML
    private Label subtitle;

    public void onOKButtonClick() {
        heading.setText("");
        subtitle.setText("");
        Stage currentStage = (Stage) parent.getScene().getWindow();
        currentStage.close();

    }
}
