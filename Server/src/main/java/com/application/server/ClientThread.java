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

            switch (clientRequest) {
                case NetworkRequestCodes.CREATE_EVENT_REQUEST -> Sequence.createEventSequence(this.user);
                case NetworkRequestCodes.DELETE_EVENT_REQUEST -> Sequence.deleteEventSequence(this.user);
                case NetworkRequestCodes.UPDATE_EVENT_REQUEST1 -> Sequence.updateEventSequence(this.user);
                case NetworkRequestCodes.SIGN_OUT_REQUEST -> Sequence.signOutSequence(this.user);
                case NetworkRequestCodes.SEARCH_EVENT_REQUEST -> Sequence.searchEventSequence(this.user);
                case -1 -> {
                    System.out.println("Client Disconnected");
                    return;
                }
                case NetworkRequestCodes.EDIT_PROFILE_REQUEST -> Sequence.editProfileSequence(this.user);
                // case NetworkRequestCodes.SHARE_EVENT_REQUEST -> Sequence.shareEventRequest(this.user);
            }
        }
    }
}
