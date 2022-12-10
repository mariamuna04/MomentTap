// Created by Kishorè Shanto on 12/10/22 at 20:15

package com.application.client;

import com.application.connection.Connection;
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
        } else {
            System.err.println("User Not Found");
            return false;

        }
    }

}
