// Created by Kishorè Shanto on 12/10/22 at 20:15

package com.application.client;

import com.application.connection.Connection;
import com.application.serialShared.Event;
import com.application.utility.NetworkRequestCodes;

public class Sequence {

    public static boolean signInSequence(String _email_, String _password_) {
        Connection.setConnection();
        Connection.sendRequestCode(NetworkRequestCodes.SIGN_IN_REQUEST);
        Connection.sendString("");
        Connection.sendString(_email_);
        Connection.sendString(_password_);
        int response = Connection.receiveRequestCode();
        if (response == NetworkRequestCodes.USER_FOUND_FROM_DATABASE) {
            String name = Connection.receiveString();
            String email = Connection.receiveString();
            User.setUser(name, email);
            return true;
        } else return false;

    }

    public static boolean signUpSequence(String _name_, String _email_, String _password_) {
        Connection.setConnection();
        Connection.sendRequestCode(NetworkRequestCodes.SIGN_UP_REQUEST);
        Connection.sendString(_name_);
        Connection.sendString(_email_);
        Connection.sendString(_password_);
        int response = Connection.receiveRequestCode();
        return response == NetworkRequestCodes.USER_ADDED_TO_DATABASE;
    }

    public static boolean signOutSequence() {
        Connection.sendRequestCode(NetworkRequestCodes.LOG_OUT);
        Connection.unsetConnection();
        return true;
    }

    public static boolean createEventSequence(Event event) {
        Connection.sendRequestCode(NetworkRequestCodes.CREATE_EVENT);
        Connection.sendObject(event);
        int response = Connection.receiveRequestCode();
        return response == NetworkRequestCodes.CREATE_EVENT_CONFIRMATION;
    }

    public static boolean deleteEventSequence(String event_name, String event_date) {
        Connection.sendRequestCode(NetworkRequestCodes.DELETE_EVENT);
        Connection.sendString(event_name);
        Connection.sendString(event_date);
        int response = Connection.receiveRequestCode();
        return response == NetworkRequestCodes.DELETE_EVENT_CONFIRMATION;
    }


}
