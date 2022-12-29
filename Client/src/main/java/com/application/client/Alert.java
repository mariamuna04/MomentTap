package com.application.client;

import com.application.serialShared.Event;
import com.application.utility.Date;
import com.application.utility.DialogBox;
import com.application.utility.Time;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

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

                    if(Date.isCurrentDate(date) && Time.isCurrentTime(time)) {
                        Client.alertEvent.remove(event);
                        Client.alertEventAdded = false;
                        //DialogBox.showDialogue("Alert", "Event " + event.event_name() + " is starting now", DialogBox.SUCCESS_DIALOG_BOX);
                        Platform.runLater(() -> {
                            try {
                                Stage st = new Stage();
                                HBox root = new HBox();
                                root.getChildren().add(new javafx.scene.control.Label("Event " + event.event_name() + " is starting now"));
                                Scene scene = new Scene(root);
                                st.setScene(scene);
                                st.setMaximized(true);
                                st.setTitle("load");
                                st.show();
                            } catch (Exception ex) {
                                ex.printStackTrace();
                                }
                        });
                    }
                }
            }
            try {
                System.out.println("Alert thread is running");
                sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
