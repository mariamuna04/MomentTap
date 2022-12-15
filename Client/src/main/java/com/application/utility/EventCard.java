// Created by Kishorè Shanto on 12/16/22 at 00:42

package com.application.utility;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class EventCard {
    VBox parent = new VBox();

    Label eventDate = new Label();
    Label eventName = new Label();
    Label eventCategory = new Label();
    Label eventDescription = new Label();

    HBox timeHolder = new HBox();

    Label eventStartTime = new Label();
    Label eventEndTime = new Label();

    public VBox makeCard(String date, String name, String category, String description, int startTime, int endTime, int type) {
        this.parent.setSpacing(6);
        if (type == 1) {
            this.parent.setStyle("-fx-background-color: #9fa8da; -fx-background-radius: 12px;");
        } else
            this.parent.setStyle("-fx-background-color: #C5C9E8; -fx-background-radius: 12px;");

        this.parent.setPadding(new javafx.geometry.Insets(10, 10, 10, 10));

        // set parent width
        this.parent.setPrefWidth(288);

        // make eventDate bold with font size 13
        this.eventDate.setFont(Font.font("Arial", FontWeight.MEDIUM, 15));
        this.eventDate.setText(date);


        this.eventName.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        eventName.setText(name);

        this.eventCategory.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        this.eventCategory.setText(category);

        this.eventDescription.setFont(Font.font("Arial", FontWeight.LIGHT, 12));
        this.eventDescription.setWrapText(true);
        this.eventDescription.setText(description);


        this.eventStartTime.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        this.eventStartTime.setText(String.valueOf(startTime));

        this.eventEndTime.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        this.eventEndTime.setText(String.valueOf(endTime));


        this.timeHolder.setSpacing(100);
        this.timeHolder.setAlignment(javafx.geometry.Pos.CENTER);

        this.timeHolder.getChildren().addAll(eventStartTime, eventEndTime);
        this.parent.getChildren().addAll(eventName, eventDate, eventCategory, eventDescription, timeHolder);

        return parent;

    }


}
