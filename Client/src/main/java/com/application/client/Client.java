package com.application.client;

import com.application.controllers.Controller;
import com.application.serialShared.Event;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Vector;

/**
 * Entry point of the Client Application. This class is responsible for loading the initial GUI
 */
public class Client extends Application {
    public static Vector<Event> alertEvent = new Vector<>();
    public static boolean alertEventAdded = false;



    /**
     * The application initialization method. This method is called automatically
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Start the GUI
     *
     * @param stage the primary stage for this application, onto which
     *              the application scene can be set.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Controller.class.getResource("sign-in-activity.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("MomentTap");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }
}
