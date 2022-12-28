package com.application.controllers;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Parent class that extends the JavaFX Application class. Other controller classes inherit this class to control
 * GUI.
 */
public class Controller extends Application {

    /**
     * <strong>DO NOT EDIT THIS METHOD</strong> <br>
     * <p>
     * This method is called when the application should stop, and provides a
     * convenient place to prepare for application exit and destroy resources.
     *
     * <p>
     * The implementation of this method provided by the Application class does nothing.
     * </p>
     *
     * @param stage the primary stage for this application, onto which
     *              the application scene can be set.
     *              Applications may create other stages, if needed, but they will not be
     *              primary stages.
     */
    @Override
    public void start(Stage stage) {

    }

    /**
     * The application initialization method. This method is called immediately
     * after the Application class is loaded and constructed. An application may
     * override this method to perform initialization prior to the actual starting
     * of the application.
     *
     * @throws Exception - if something goes wrong while initializing the application
     */
    @Override
    public void init() throws Exception {
        super.init();
    }

    /**
     * The application cleanup method. This method is called after the application
     * has stopped, and its primary stage has been removed from the screen. An
     * application may override this method to perform any necessary cleanup
     * operations.
     *
     * @throws Exception - if something goes wrong while stopping the application
     */
    @Override
    public void stop() throws Exception {
        super.stop();
    }
}
