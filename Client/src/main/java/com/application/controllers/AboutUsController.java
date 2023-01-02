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


// Project Finished on Mon Jan 2 2023 15:30:00 GMT+0600 (Bangladesh Standard Time)
