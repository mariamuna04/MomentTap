// Created by Kishorè Shanto on 12/16/22 at 00:42

package com.application.utility;

import com.application.client.Sequence;
import com.application.controllers.HomeActivityController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.IOException;

public class EventCard {
    VBox parent = new VBox();

    Label eventDate = new Label();
    Label eventName = new Label();
    Label eventCategory = new Label();
    Label eventDescription = new Label();

    HBox timeHolder = new HBox();
    Label eventStartTime = new Label();

    Line line = new Line();
    Circle circleOne = new Circle();
    Circle circleTwo = new Circle();

    HBox timeLineHolder = new HBox();
    Label eventEndTime = new Label();
    HBox buttonHolder = new HBox();
    Button shareButton = new Button();
    Button updateButton = new Button();
    Button deleteButton = new Button();

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
        this.eventStartTime.setText(startTime + ".00");

        this.eventEndTime.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        this.eventEndTime.setText(endTime + ".00");

        line.setStartX(0);
        line.setStartY(0);
        line.setEndX(60);
        line.setEndY(0);
        line.setStrokeWidth(2);
        if (type == 1) {
            line.setStroke(javafx.scene.paint.Color.rgb(197, 202, 233));
        } else {
            line.setStroke(javafx.scene.paint.Color.rgb(159, 168, 218));
        }

        circleOne.setRadius(5);
        circleOne.setStroke(javafx.scene.paint.Color.rgb(197, 202, 233));
        if (type == 1) {
            circleOne.setStyle("-fx-fill: #FFF; -fx-stroke: #C5C9E8; -fx-stroke-width: 2px");
        } else {
            circleOne.setStyle("-fx-fill: #FFF; -fx-stroke: #9fa8da; -fx-stroke-width: 2px");
        }
        circleOne.setFill(javafx.scene.paint.Color.BLACK);
        circleTwo.setRadius(5);
        circleTwo.setStroke(javafx.scene.paint.Color.rgb(197, 202, 233));
        if (type == 1) {
            circleTwo.setStyle("-fx-fill:  #FFF; -fx-stroke: #C5C9E8; -fx-stroke-width: 2px");
        } else {
            circleTwo.setStyle("-fx-fill:  #FFF; -fx-stroke: #9fa8da; -fx-stroke-width: 2px");
        }
        circleTwo.setFill(javafx.scene.paint.Color.BLACK);

        timeLineHolder.getChildren().addAll(circleOne, line, circleTwo);
        timeLineHolder.setAlignment(javafx.geometry.Pos.CENTER);


        this.timeHolder.setPadding(new javafx.geometry.Insets(15, 0, 0, 0));
        this.timeHolder.setSpacing(15);
        this.timeHolder.setAlignment(javafx.geometry.Pos.CENTER);
        this.timeHolder.getChildren().addAll(eventStartTime, timeLineHolder, eventEndTime);


        shareButton.setStyle("-fx-background-color: #9fa8da");
        updateButton.setStyle("-fx-background-color: #9fa8da");
        deleteButton.setStyle("-fx-background-color: #9fa8da");

        Image shareIcon = new Image("file:Client/src/main/resources/icons/share-icon.png");
        ImageView shareIconView = new ImageView(shareIcon);
        shareIconView.setFitHeight(25);
        shareIconView.setFitWidth(25);
        shareButton.setGraphic(shareIconView);

        Image updateIcon = new Image("file:Client/src/main/resources/icons/update-icon.png");
        ImageView updateIconView = new ImageView(updateIcon);
        updateIconView.setFitHeight(25);
        updateIconView.setFitWidth(25);
        updateButton.setGraphic(updateIconView);

        Image deleteIcon = new Image("file:Client/src/main/resources/icons/delete-icon.png");
        ImageView deleteIconView = new ImageView(deleteIcon);
        deleteIconView.setFitHeight(25);
        deleteIconView.setFitWidth(25);
        deleteButton.setGraphic(deleteIconView);

        this.buttonHolder.setSpacing(40);
        this.buttonHolder.setPadding(new javafx.geometry.Insets(10, 0, 0, 0));
        this.buttonHolder.setAlignment(javafx.geometry.Pos.CENTER);
        this.buttonHolder.getChildren().addAll(shareButton, updateButton, deleteButton);


        shareButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Share button clicked for " + name);
            }
        });

        deleteButton.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent event) {
                Sequence.deleteEventSequence(name, date);
            }
        });

        updateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Utility.createStage("updateEvent.fxml");
            }
        });


        if (type == 1)
            this.parent.getChildren().addAll(eventName, eventDate, eventCategory, eventDescription, timeHolder, buttonHolder);
        else
            this.parent.getChildren().addAll(eventName, eventDate, eventCategory, eventDescription, timeHolder);
        return parent;


    }


}
