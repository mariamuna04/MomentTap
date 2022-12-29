package com.application.client;

import com.application.connection.Connection;
import com.application.serialShared.Event;
import com.application.utility.DialogBox;
import com.application.utility.NetworkRequestCodes;
import com.application.utility.Time;

import java.io.IOException;

/**
 * This class contains each activity sequence of the client application. Each method is a sequence of activities
 * that are executed in a specific order. Each method is called from the Controller class.
 */
public class Sequence {

    /**
     * This method is called when the user clicks on the "Sign In" button in the sign-in-activity.fxml file.
     * The method first attempts to connect to the server. If the connection is successful, it sends a request
     * to the server to check if the user exists in the database. If the user exists, it will receive a response
     * from the server and the user will be redirected to the home-activity.fxml file. If the user does not exist,
     * the user will be redirected to the sign-up-activity.fxml file.
     *
     * @param _email_    user email
     * @param _password_ user password
     * @return true if the user exists in the database, false otherwise
     */
    public static boolean signInSequence(String _email_, String _password_) {
        Connection.setConnection();
        Connection.sendRequestCode(NetworkRequestCodes.SIGN_IN_REQUEST);
        Connection.sendPrimitiveObject("");
        Connection.sendPrimitiveObject(_email_);
        Connection.sendPrimitiveObject(_password_);
        int response = Connection.receiveRequestCode();
        if (response == NetworkRequestCodes.SIGN_IN_SUCCESSFUL) {
            String name = Connection.receivePrimitiveObject();
            String email = Connection.receivePrimitiveObject();
            User.setUser(name, email, _password_);
            return true;
        } else return false;
    }


    /**
     * This method is called when the user clicks on the "Sign Up" button in the sign-up-activity.fxml file.
     * The method first attempts to connect to the server. If the connection is successful, it sends a request
     * to the server to add the user to the database. If the user is successfully added to the database, it will
     * receive a response from the server and the user will be redirected to the sign-in-activity.fxml file.
     *
     * @param _name_     Name of the user
     * @param _email_    Email of the user
     * @param _password_ Password of the user
     * @return true if the user is successfully added to the database, false otherwise
     */
    public static boolean signUpSequence(String _name_, String _email_, String _password_) {
        Connection.setConnection();
        Connection.sendRequestCode(NetworkRequestCodes.SIGN_UP_REQUEST);
        Connection.sendPrimitiveObject(_name_);
        Connection.sendPrimitiveObject(_email_);
        Connection.sendPrimitiveObject(_password_);
        int response = Connection.receiveRequestCode();
        return response == NetworkRequestCodes.SIGN_UP_SUCCESSFUL;
    }

    /**
     * This method is called when the user clicks on the "Sign Out" button in the home-activity.fxml file.
     * This method send a request to the server to sign out the user. If the user is successfully signed out,
     * it will receive a response from the server and the user will be redirected to the sign-in-activity.fxml file.
     *
     * @return true if the user is successfully signed out, false otherwise
     */
    public static boolean signOutSequence() {
        Connection.sendRequestCode(NetworkRequestCodes.SIGN_OUT_REQUEST);
        int response = Connection.receiveRequestCode();
        if (response == NetworkRequestCodes.SIGN_OUT_SUCCESSFUL) {
            Connection.unsetConnection();
            return true;
        } else return false;

    }

    /**
     * This method is called when the user clicks on the "Add Event" button in the home-activity.fxml file.
     * This method send a request to the server to add an event to the database. If the event is successfully
     * added to the database, it will receive a response from the server and the user will be redirected to the
     * home-activity.fxml file.
     *
     * @param event Event (as object) to be added to the database
     * @return true if the event is successfully added to the database, false otherwise
     */
    public static boolean createEventSequence(Event event) {
        Connection.sendRequestCode(NetworkRequestCodes.CREATE_EVENT_REQUEST);
        Connection.sendObject(event);
        int response = Connection.receiveRequestCode();
        return response == NetworkRequestCodes.CREATE_EVENT_SUCCESSFUL;
    }

    /**
     * This method is called when the user clicks on the "Delete Event" button in the home-activity.fxml file.
     * This method send a request to the server to delete an event from the database. If the event is successfully
     * deleted from the database, it will receive a response from the server and the user will be redirected to the
     * home-activity.fxml file.
     *
     * @param event_name Name of the event to be deleted from the database
     * @param event_date Date of the event to be deleted from the database
     */
    public static void deleteEventSequence(String event_name, String event_date) {
        Connection.sendRequestCode(NetworkRequestCodes.DELETE_EVENT_REQUEST);
        Connection.sendPrimitiveObject(event_name);
        Connection.sendPrimitiveObject(event_date);
        int response = Connection.receiveRequestCode();
        try {
            if (response == NetworkRequestCodes.DELETE_EVENT_SUCCESSFUL) {
                DialogBox.showDialogue("Success", "Event deleted successfully", DialogBox.SUCCESS_DIALOG_BOX);
            } else {
                DialogBox.showDialogue("Failed", "Event deletion failed", DialogBox.ERROR_DIALOG_BOX);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void searchSequence(String keyword) {
        User.events.clear();
        try {
            Connection.sendRequestCode(NetworkRequestCodes.SEARCH_EVENT_REQUEST);
            Connection.sendPrimitiveObject(keyword);
            int response = Connection.receiveRequestCode();
            if (response == NetworkRequestCodes.SEARCH_EVENT_SUCCESSFUL) {
                int size = Connection.getDataInputStream().readInt();
                for (int i = 0; i < size; i++) {
                    String name = Connection.getDataInputStream().readUTF();
                    String description = Connection.getDataInputStream().readUTF();
                    String category = Connection.getDataInputStream().readUTF();
                    String date = Connection.getDataInputStream().readUTF();
                    String start_time = Connection.getDataInputStream().readUTF();
                    String end_time = Connection.getDataInputStream().readUTF();
                    String is_Shared = Connection.getDataInputStream().readUTF();
                    Event e = new Event(User.getEmail(), name, description, category, date, Time.parseTime(start_time), Time.parseTime(end_time), is_Shared);
                    User.events.add(e);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
