// Created by Kishorè Shanto on 12/15/22 at 02:17

package com.application.server;

import com.application.database.Database;
import com.application.utility.NetworkRequestCodes;

public class ClientThread implements Runnable {
    private final User user;

    public ClientThread(User user) {
        this.user = user;
    }

    @Override
    public void run() {
        System.out.println(user.getEmail() + " is connected, Thread started");
        if (user.getRequest() == NetworkRequestCodes.SIGN_UP_REQUEST) {
            Sequence.signUpSequence(user);
        } else if (user.getRequest() == NetworkRequestCodes.SIGN_IN_REQUEST) {
            Database.findUser(user.getEmail(), user.getPassword());
            try {
                if (Database.resultSet.next()) {
                    user.sendRequestCode(NetworkRequestCodes.SIGN_IN_SUCCESSFUL);
                    user.sendData(Database.resultSet.getString("name"));
                    user.sendData(Database.resultSet.getString("email"));
                    receiveClientRequestOnLoop();
                } else {
                    System.out.println("User Not Found");
                    user.sendRequestCode(NetworkRequestCodes.SIGN_IN_UNSUCCESSFUL);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void receiveClientRequestOnLoop() {
        while (true) {
            int clientRequest = this.user.receiveRequestCode();
            if (clientRequest == NetworkRequestCodes.CREATE_EVENT_REQUEST) {
                Sequence.createEventSequence(this.user);
            } else if (clientRequest == NetworkRequestCodes.DELETE_EVENT_REQUEST) {
                Sequence.deleteEventSequence(this.user);
            } else if (clientRequest == NetworkRequestCodes.SIGN_OUT_REQUEST) {
                Sequence.signOutSequence(this.user);
            } else if (clientRequest == NetworkRequestCodes.SEARCH_EVENT_REQUEST) {
                Sequence.searchEventSequence(this.user);
            } else if(clientRequest == -1) {
                System.err.println("Client Disconnected");
                break;
            } else if (clientRequest == NetworkRequestCodes.UPDATE_EVENT_REQUEST) {
                Sequence.updateEventSequence(this.user);
            }
        }
    }
}
