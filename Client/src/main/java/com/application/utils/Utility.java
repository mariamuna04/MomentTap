package com.application.utils;

import com.application.client.Client;
import com.application.client.Controller;
import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Utility {
    public static void changeScene(Node node, String fxml) throws Exception {

        Stage currentStage = (Stage) node.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(Client.class.getResource(fxml));
        Scene scene = new Scene(fxmlLoader.load());

        Controller controller = fxmlLoader.getController();
        controller.init();

        FadeTransition fadeTransition = new FadeTransition(javafx.util.Duration.millis(300), scene.getRoot());
        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.play();

        currentStage.setScene(scene);
    }

}
