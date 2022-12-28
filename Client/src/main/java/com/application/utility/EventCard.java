package com.application.utility;

import com.application.client.Sequence;
import com.application.client.User;
import com.application.connection.Connection;
import com.application.serialShared.Event;
import javafx.scene.control.*;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.time.LocalDate;

public class EventCard {
    boolean shareButtonClicked = false;
//    boolean deleteButtonClicked = false;
//    boolean editButtonClicked = false;

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

    VBox bottomGUI = new VBox();
    HBox shareView = new HBox();

    // Update event guis----------------------------------------------------------------
    VBox updateEventView = new VBox();
    TextField updateEventName = new TextField();
    TextArea updateEventDescription = new TextArea();
    TextField updateEventCategory = new TextField();
    DatePicker updateEventDate = new DatePicker();
    TextField updateEventStartTime = new TextField();
    TextField updateEventEndTime = new TextField();
    Button updateEventButton = new Button("Update");
    Button updateEventCancelButton = new Button("Cancel");

    HBox updateEventTimeHolder = new HBox();
    HBox updateEventButtonHolder = new HBox();


    boolean cardView = true;


    public VBox makeCard(String date, String name, String category, String description, Time startTime, Time endTime, int type) {
        this.parent.setSpacing(6);
        this.parent.setEffect(new DropShadow(BlurType.THREE_PASS_BOX, Color.rgb(163, 163, 163), 10, 0, 3, 3));
        if (type == 1) {
            this.parent.setStyle("-fx-background-color: #9fa8da; -fx-background-radius: 12px;");
        } else this.parent.setStyle("-fx-background-color: #b6bbe2; -fx-background-radius: 12px;");

        this.parent.setPadding(new javafx.geometry.Insets(10, 10, 10, 10));


        // set parent width
        this.parent.setPrefWidth(288);

        // make eventDate bold with font size 13
        this.eventDate.setFont(Font.font("Arial", FontWeight.MEDIUM, 15));
        this.eventDate.setText(date);

        // Event name Configuration
        this.eventName.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        if (type == 1) {
            // Set text color to white
            this.eventName.setStyle("-fx-text-fill: #343f6b;");
        } else {
            // Set text color to white
            this.eventName.setStyle("-fx-text-fill: #676b79;");
        }
        eventName.setText(name);

        this.eventCategory.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        this.eventCategory.setText(category);

        this.eventDescription.setFont(Font.font("Arial", FontWeight.LIGHT, 12));
        this.eventDescription.setWrapText(true);
        this.eventDescription.setText(description);


        this.eventStartTime.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        this.eventStartTime.setText(startTime.toString());

        this.eventEndTime.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        this.eventEndTime.setText(endTime.toString());

        if (type == 1) {
            // Set text color to white
            this.eventStartTime.setStyle("-fx-text-fill: #343f6b;");
            this.eventEndTime.setStyle("-fx-text-fill: #343f6b;");
        } else {
            // Set text color to white
            this.eventStartTime.setStyle("-fx-text-fill: #676b79;");
            this.eventEndTime.setStyle("-fx-text-fill: #676b79;");
        }

        line.setStartX(0);
        line.setStartY(0);
        line.setEndX(120);
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


        if (type == 1) {
            shareButton.setStyle("-fx-background-color:  #c5cae9; -fx-background-radius: 12px 0 0 12px;");
            shareButton.setMinHeight(30);
            shareButton.setMinWidth(89);
            updateButton.setStyle("-fx-background-color: #c5cae9;");
            updateButton.setMinHeight(30);
            updateButton.setMinWidth(89);
            deleteButton.setStyle("-fx-background-color: #c5cae9; -fx-background-radius: 0 12px 12px 0;");
            deleteButton.setMinHeight(30);
            deleteButton.setMinWidth(89);
        } else {
            shareButton.setStyle("-fx-background-color:  #9fa8da; -fx-background-radius: 12px 0 0 12px;");
            shareButton.setMinHeight(30);
            shareButton.setMinWidth(80);
            shareButton.setDisable(true);
            updateButton.setStyle("-fx-background-color: #9fa8da; ");
            updateButton.setMinHeight(30);
            updateButton.setMinWidth(80);
            updateButton.setDisable(true);
            deleteButton.setStyle("-fx-background-color: #9fa8da; -fx-background-radius: 0 12px 12px 0;");
            deleteButton.setMinHeight(30);
            deleteButton.setMinWidth(80);
        }

        Image shareIcon = new Image("file:Client/src/main/resources/icons/share-icon.png");
        ImageView shareIconView = new ImageView(shareIcon);
        shareIconView.setFitHeight(20);
        shareIconView.setFitWidth(20);
        shareButton.setGraphic(shareIconView);

        Image updateIcon = new Image("file:Client/src/main/resources/icons/update-icon.png");
        ImageView updateIconView = new ImageView(updateIcon);
        updateIconView.setFitHeight(20);
        updateIconView.setFitWidth(20);
        updateButton.setGraphic(updateIconView);

        Image deleteIcon = new Image("file:Client/src/main/resources/icons/delete-icon.png");
        ImageView deleteIconView = new ImageView(deleteIcon);
        deleteIconView.setFitHeight(20);
        deleteIconView.setFitWidth(20);
        deleteButton.setGraphic(deleteIconView);

        this.buttonHolder.setPadding(new javafx.geometry.Insets(10, 0, 0, 0));
        this.buttonHolder.setAlignment(javafx.geometry.Pos.CENTER);
        this.buttonHolder.getChildren().addAll(shareButton, updateButton, deleteButton);
        bottomGUI.getChildren().addAll(buttonHolder);

        // Share button action GUI -------------------------------------------------------------------------

        shareView.setSpacing(6);

        shareView.setStyle("-fx-background-color: #c5cae9; -fx-background-radius: 0 0 12px 12px;");
        shareView.setPadding(new javafx.geometry.Insets(7, 10, 7, 10));
        TextField shareEmail = new TextField();

        shareEmail.setMinWidth(195);
        shareEmail.setPromptText("Enter email");
        shareEmail.setMinHeight(30);
        shareEmail.setStyle("-fx-background-color: #FFF;-fx-background-radius: 12px;");
        Button _shareButton = new Button();
        _shareButton.setStyle("-fx-background-color: #c5cae9 ;-fx-background-radius: 50px;");
        _shareButton.setMinHeight(30);
        Image _shareIcon = new Image("file:Client/src/main/resources/icons/share-enter-icon.png");
        ImageView _shareIconView = new ImageView(_shareIcon);
        _shareIconView.setFitHeight(22);
        _shareIconView.setFitWidth(22);
        _shareButton.setGraphic(_shareIconView);

        shareView.getChildren().addAll(shareEmail, _shareButton);


        // Update GUI initialization -------------------------------------------------------------------------
        updateEventView.setSpacing(8);
        updateEventView.setAlignment(javafx.geometry.Pos.CENTER);
        updateEventTimeHolder.getChildren().addAll(updateEventStartTime, updateEventEndTime);
        updateEventTimeHolder.setSpacing(10);
        updateEventButtonHolder.getChildren().addAll(updateEventButton, updateEventCancelButton);
        updateEventButtonHolder.setSpacing(10);
        updateEventButtonHolder.setAlignment(javafx.geometry.Pos.CENTER);
        updateEventView.getChildren().addAll(updateEventName, updateEventCategory, updateEventDescription, updateEventDate, updateEventTimeHolder, updateEventButtonHolder);

        updateEventDescription.setWrapText(true);
        updateEventDescription.setMinHeight(90);
        updateEventDescription.setMaxHeight(90);
        updateEventDescription.setPrefHeight(90);


        updateEventName.setPromptText(name);
        updateEventName.setMinHeight(30);
        updateEventName.setStyle("-fx-background-color: #FFF;-fx-background-radius: 8px;");
        updateEventCategory.setPromptText(category);
        updateEventCategory.setMinHeight(30);
        updateEventCategory.setStyle("-fx-background-color: #FFF;-fx-background-radius: 8px;");
        updateEventDescription.setPromptText(description);
        updateEventDescription.setStyle("-fx-background-color: #FFF;-fx-background-radius: 8px;");
        updateEventDate.setPromptText(String.valueOf(LocalDate.parse(date)));
        updateEventDate.setMinHeight(30);
        updateEventDate.setStyle("-fx-background-color: #FFF;-fx-background-radius: 8px;");
        updateEventStartTime.setPromptText(String.valueOf(startTime));
        updateEventStartTime.setMinHeight(30);
        updateEventStartTime.setStyle("-fx-background-color: #FFF;-fx-background-radius: 8px;");
        updateEventEndTime.setPromptText(String.valueOf(endTime));
        updateEventEndTime.setMinHeight(30);
        updateEventEndTime.setStyle("-fx-background-color: #FFF;-fx-background-radius: 8px;");

        updateEventButton.setMinHeight(30);
        updateEventButton.setMinWidth(80);
        updateEventButton.setStyle("-fx-background-color: #49599a ;-fx-background-radius: 10px; -fx-text-fill: #FFF;");
        updateEventCancelButton.setMinHeight(30);
        updateEventCancelButton.setStyle("-fx-background-color: #c5cae9 ;-fx-background-radius: 10px;");
        updateEventCancelButton.setMinWidth(80);


        _shareButton.setOnAction(event -> {
            if (shareEmail.getText().equals("")) {
                shareEmail.setStyle("-fx-background-color: #FFF;-fx-background-radius: 12px; -fx-border-color: #F76E64; -fx-border-width: 2px; -fx-border-radius: 12px;");
                return;
            }

            // Send data to server
            Connection.sendRequestCode(NetworkRequestCodes.SHARE_EVENT_REQUEST);
            Connection.sendPrimitiveObject(shareEmail.getText());
            Connection.sendPrimitiveObject(name);
            Connection.sendPrimitiveObject(description);
            Connection.sendPrimitiveObject(category);
            Connection.sendPrimitiveObject(date);
            Connection.sendPrimitiveObject(startTime.toString());
            Connection.sendPrimitiveObject(endTime.toString()); // hh:mm

            int responseCode = Connection.receiveRequestCode();

            if (responseCode == NetworkRequestCodes.SHARE_EVENT_SUCCESSFUL) {
                try {
                    DialogBox.showDialogue("Shared", "Event shared successfully", DialogBox.SUCCESS_DIALOG_BOX);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (responseCode == NetworkRequestCodes.USER_NOT_FOUND) {
                try {
                    DialogBox.showDialogue("Error", "User not found", DialogBox.ERROR_DIALOG_BOX);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    DialogBox.showDialogue("Error", "Event not shared", DialogBox.ERROR_DIALOG_BOX);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        });


        shareButton.setOnAction(event -> {
            if (!shareButtonClicked) {
                shareButton.setStyle("-fx-background-color:  #c5cae9; -fx-background-radius: 12px 0 0 0;");
                deleteButton.setStyle("-fx-background-color: #c5cae9; -fx-background-radius: 0 12px 0 0;");
                shareButtonClicked = true;
                bottomGUI.getChildren().add(shareView);
            } else {
                shareButton.setStyle("-fx-background-color:  #c5cae9; -fx-background-radius: 12px 0 0 12px;");
                deleteButton.setStyle("-fx-background-color: #c5cae9; -fx-background-radius: 0 12px 12px 0;");
                shareEmail.setStyle("-fx-background-color: #FFF;-fx-background-radius: 12px; -fx-border-color: #F76E6400; -fx-border-width: 2px; -fx-border-radius: 12px;");

                shareButtonClicked = false;
                bottomGUI.getChildren().remove(shareView);

            }
        });

        deleteButton.setOnAction(event -> Sequence.deleteEventSequence(name, date));

        updateButton.setOnAction(event -> {
            if (cardView) {
                parent.getChildren().removeAll(eventName,eventDate, eventCategory, eventDescription, timeHolder, bottomGUI);
                parent.getChildren().addAll(updateEventView);
                cardView = false;
            } else {
                parent.getChildren().removeAll(updateEventView);
                parent.getChildren().addAll(eventName, eventDate, eventCategory, eventDescription, timeHolder, bottomGUI);
                cardView = true;
            }

        });

        updateEventButton.setOnAction(actionEvent -> {
            if (updateEventName.getText().equals("") && updateEventCategory.getText().equals("") && updateEventDescription.getText().equals("") && updateEventDate.getValue() == null && updateEventStartTime.getText() == null && updateEventEndTime.getText() == null) {
                try {
                    DialogBox.showDialogue("Warning", "No Changes", DialogBox.WARNING_DIALOG_BOX);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return;
            }
            String _name = (updateEventName.getText().equals("")) ? name : updateEventName.getText();
            String _category = (updateEventCategory.getText().equals("")) ? category : updateEventCategory.getText();
            String _description = (updateEventDescription.getText().equals("")) ? description : updateEventDescription.getText();
            String _date = (updateEventDate.getValue() == null) ? date : updateEventDate.getValue().toString();
            String _startTime = (updateEventStartTime.getText().equals("")) ? startTime.toString() : updateEventStartTime.getText();
            String _endTime = (updateEventEndTime.getText().equals("")) ? endTime.toString() : updateEventEndTime.getText();

            Event event = new Event(User.getEmail(), _name, _category, _description, _date, Time.parseTime(_startTime), Time.parseTime(_endTime));
            Connection.sendRequestCode(NetworkRequestCodes.UPDATE_EVENT_REQUEST1);
            Connection.sendObject(event);
            Connection.sendPrimitiveObject(name);
            Connection.sendPrimitiveObject(date);
            int responseCode = Connection.receiveRequestCode();
            if (responseCode == NetworkRequestCodes.UPDATE_EVENT_SUCCESSFUL1) {
                try {
                    DialogBox.showDialogue("Updated", "Event updated successfully, Please Refresh", DialogBox.SUCCESS_DIALOG_BOX);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    DialogBox.showDialogue("Error", "Event not updated", DialogBox.ERROR_DIALOG_BOX);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        updateEventCancelButton.setOnAction(actionEvent -> {
            parent.getChildren().removeAll(updateEventView);
            parent.getChildren().addAll(eventName, eventDate, eventCategory, eventDescription, timeHolder, bottomGUI);
            cardView = true;
        });

        this.parent.getChildren().addAll(eventName, eventDate, eventCategory, eventDescription, timeHolder, bottomGUI);
        return parent;
    }
}
