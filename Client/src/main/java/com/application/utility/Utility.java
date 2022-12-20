// Created by Kishorè Shanto on Nov 16 2022 16:15

package com.application.utility;

import com.application.controllers.Controller;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * Utility class for the Client Application
 */
public class Utility {
    /**
     * Change the scene of the application
     * @param node The node from which the scene is to be changed
     * @param fxml The fxml file to be loaded
     * @throws Exception If the fxml file is not found,
     * init() method is not found in the controller or the controller is not found
     */
    public static void changeScene(Node node, String fxml) throws Exception {
        // Get Stage, Load FXML, Create Scene
        Stage currentStage = (Stage) node.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Controller.class.getResource(fxml));
        Scene scene = new Scene(fxmlLoader.load());

        // Get Controller
        Controller controller = fxmlLoader.getController();
        controller.init();

        // Add Transition
        FadeTransition fadeTransition = new FadeTransition(javafx.util.Duration.millis(300), scene.getRoot());
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();

        // Set Scene
        currentStage.setScene(scene);
    }

    /**
     * Create a new stage with the given fxml file
     * @param fxml The fxml file to be loaded
     */
    public static void createStage(String fxml) {
        try {
            Stage stage = new Stage();

            stage.onCloseRequestProperty().addListener((observable, oldValue, newValue) -> {
                try {
                    Platform.exit();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            FXMLLoader fxmlLoader = new FXMLLoader(Controller.class.getResource(fxml));
            Scene scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete the Stage with given node from the scene
     * @param node The node from which the stage is to be deleted
     */
    public static void deleteStage(Node node) {
        Stage currentStage = (Stage) node.getScene().getWindow();
        currentStage.close();
    }
}
