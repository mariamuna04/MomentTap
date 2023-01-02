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


// Project Finished on Mon Jan 2 2023 15:30:00 GMT+0600 (Bangladesh Standard Time)
