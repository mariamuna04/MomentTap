package com.application.controllers;

import com.application.utility.Utility;
import javafx.scene.layout.Pane;

/**
 * This Controller handles the About Us GUI Activity.
 */
public class AboutUsController extends Controller {


    public Pane parent;

    public void onBackButtonListener() throws Exception {
        Utility.changeScene(parent, "sign-in-activity.fxml");
    }
}
