// Created by Kishorè Shanto on 12/11/22 at 19:59

package com.application.controllers;

import com.application.utility.Utility;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class DialogBoxController extends Controller {

    public ImageView imageViewID;
    @FXML
    private VBox parent;
    @FXML
    private Label heading;
    @FXML
    private Label subtitle;

    public void onOKButtonClick() {
        clear();
        Utility.deleteStage(parent);
    }

    private void clear() {
        heading.setText("");
        subtitle.setText("");
    }
}
