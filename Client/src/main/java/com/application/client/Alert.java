package com.application.client;

import com.application.serialShared.Event;
import com.application.utility.Date;
import com.application.utility.Time;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import static java.lang.Thread.sleep;

public class Alert implements Runnable {
    public static boolean isRunning = false;

    public Alert() {
        isRunning = true;
    }

    @Override
    public void run() {
        while (true) {
            if (Client.alertEventAdded) {
                for (Event event : Client.alertEvent) {
                    Date date = Date.parseDate(event.event_date());
                    Time time = event.event_start_time();

                    if (Date.isCurrentDate(date) && Time.isCurrentTime(time)) {
                        Client.alertEvent.remove(event);
                        Client.alertEventAdded = false;
                        Platform.runLater(() -> {
                            try {
                                Stage alertStage = new Stage();

                                HBox root = new HBox();
                                root.setPadding(new javafx.geometry.Insets(10));
                                root.setAlignment(javafx.geometry.Pos.CENTER);

                                ImageView imageView = new ImageView(new Image("file:Client/src/main/resources/icons/alarm.png"));
                                // Set the image view fit width and height
                                imageView.setFitWidth(96);
                                imageView.setFitHeight(96);
                                VBox information = new VBox();
                                information.setPadding(new javafx.geometry.Insets(10));

                                // Title
                                TextFlow title = new TextFlow();
                                Label t1 = new Label("Event ");
                                t1.setFont(Font.font("Arial", FontWeight.BOLD, 20));
                                Label tx = new Label(event.event_name());
                                tx.setFont(Font.font("Arial", FontWeight.BLACK, 20));
                                tx.setStyle("-fx-text-fill: #49599a");
                                Label t2 = new Label(" is starting now");
                                t2.setFont(Font.font("Arial", FontWeight.BOLD, 20));
                                title.getChildren().addAll(t1, tx, t2);

                                // Description
                                Label description = new Label(event.event_description());
                                description.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
                                description.setPadding(new javafx.geometry.Insets(10, 0, 20, 0));
                                description.setWrapText(true);

                                // Category
                                Label category = new Label(event.event_category());
                                category.setFont(Font.font("Arial", FontWeight.BOLD, 16));
                                category.setStyle("-fx-text-fill: #838383");

                                // Time and Date
                                TextFlow timeDate = new TextFlow();
                                Label _date = new Label(date.toString());
                                _date.setFont(Font.font("Monospace", FontWeight.BOLD, 14));

                                Label startTime = new Label(time.toString());
                                startTime.setFont(Font.font("Monospace", FontWeight.BOLD, 14));

                                timeDate.getChildren().addAll(_date, new Label("   |   "), startTime);


                                information.getChildren().addAll(title, category,  description, timeDate);
                                root.getChildren().addAll(imageView, information);
                                Scene scene = new Scene(root);
                                alertStage.setScene(scene);
                                alertStage.setTitle("Alert");
                                alertStage.show();
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        });
                    }
                }
            }
            try {
                sleep(3000);
            } catch (InterruptedException ignored) {

            }

        }
    }
}


// Project Finished on Mon Jan 2 2023 15:30:00 GMT+0600 (Bangladesh Standard Time)
