package com.application.server;

import com.application.database.Database;
import com.application.serialShared.Event;
import com.application.utility.NetworkRequestCodes;

public class Sequence {

    public static void signUpSequence(User user) {
        try {
            Database.addUser(user.getName(), user.getEmail(), user.getPassword());
            user.sendRequestCode(NetworkRequestCodes.SIGN_UP_SUCCESSFUL);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createEventSequence(User user) {
        try {
            Event event = (Event) user.getObjectInputStream().readObject();
            System.out.println(event);
            Database.addEvent(event);
            user.sendRequestCode(NetworkRequestCodes.CREATE_EVENT_SUCCESSFUL);
        } catch (Exception e) {
            user.sendRequestCode(NetworkRequestCodes.CREATE_EVENT_UNSUCCESSFUL);
            e.printStackTrace();
        }
    }

    public static void deleteEventSequence(User user) {
        String name = user.receiveString();
        String date = user.receiveString();
        Database.deleteEvent(user.getEmail(), name, date);
        user.sendRequestCode(NetworkRequestCodes.DELETE_EVENT_SUCCESSFUL);
    }

    public static void signOutSequence(User user) {
        try {
            user.sendRequestCode(NetworkRequestCodes.SIGN_OUT_SUCCESSFUL);
            user.closeConnection();
            System.out.println("Client Disconnected");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void searchEventSequence(User user) {
        try {
            String keyword = user.receiveString();
            Database.searchEvent(user.getEmail(), keyword);
            System.out.println("Searching for " + keyword);

            int size = 0;
            if (Database.resultSet != null) {
                Database.resultSet.last();
                size = Database.resultSet.getRow();
            }

            if (size > 0) {
                user.sendRequestCode(NetworkRequestCodes.SEARCH_EVENT_SUCCESSFUL);
                System.out.println("Sending size: " + size);
                user.sendData(size);
                Database.resultSet.beforeFirst();
                while (Database.resultSet.next()) {
                    user.sendData(Database.resultSet.getString("event_name"));
                    user.sendData(Database.resultSet.getString("event_description"));
                    user.sendData(Database.resultSet.getString("event_category"));
                    user.sendData(Database.resultSet.getString("event_date"));
                    user.sendData(Database.resultSet.getString("event_start_time"));
                    user.sendData(Database.resultSet.getString("event_end_time"));
                }
            } else {
                user.sendRequestCode(NetworkRequestCodes.SEARCH_EVENT_UNSUCCESSFUL);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void editProfileSequence(User user) {
        String name = user.receiveString();
        String password = user.receiveString();
        System.out.println("Updating event: " + name);
        Database.updateProfile(user.getEmail(), name, password);
        System.out.println("Event updated");
        if (Database.resultSet != null) {
            System.out.println("Sending data");
            user.sendRequestCode(NetworkRequestCodes.EDIT_PROFILE_SUCCESSFUL);
            System.out.println("Sending size: " + 1);
        } else {
            System.out.println("Sending data");
            user.sendRequestCode(NetworkRequestCodes.EDIT_PROFILE_UNSUCCESSFUL);
            System.out.println("Sending size: " + 0);
        }
    }

    public static void updateEventSequence(User user) {
        try {
            Event event = (Event) user.getObjectInputStream().readObject();
            String oldName = user.receiveString();
            String oldDate = user.receiveString();

            Database.updateEvent(event, oldName, oldDate);
            user.sendRequestCode(NetworkRequestCodes.UPDATE_EVENT_SUCCESSFUL1);
        } catch (Exception e) {
            user.sendRequestCode(NetworkRequestCodes.UPDATE_EVENT_UNSUCCESSFUL1);
            e.printStackTrace();
        }
    }

    public static void shareEventSequence(User user){
        try{
            Event event = (Event) user.getObjectInputStream().readObject();
            String toEmail = user.receiveString();
            Database.shareEvent(event, toEmail);
            user.sendRequestCode(NetworkRequestCodes.SHARE_EVENT_SUCCESSFUL);

        }
        catch (Exception e){
            user.sendRequestCode(NetworkRequestCodes.SHARE_EVENT_UNSUCCESSFUL);
            e.printStackTrace();
        }
    }
}