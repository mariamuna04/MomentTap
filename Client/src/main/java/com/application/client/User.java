package com.application.client;


import com.application.serialShared.Event;

import java.util.ArrayList;

/**
 * This class is used to store the information of a user. On a successful sign in, the user information is stored.
 * The information is used to display the user's name and email in the home-activity.fxml file.
 * The information is also used to send the user's name and email to the server when the user creates an event.
 */
public class User {

    public static ArrayList<Event> events = new ArrayList<>();


    /**
     * Name of the user
     */
    private static String name;

    /**
     * Email of the user
     */
    private static String email;


    /**
     * Set the name and email of the user on a successful sign in
     * @param name Name of the user
     * @param email Email of the user
     */
    public static void setUser(String name, String email) {
        User.name = name;
        User.email = email;
    }

    /**
     * Getter method to get the name and email of the user.
     * @return Name and email of the user
     */
    public static String getName() {
        return name;
    }

    public static String getEmail() {
        return email;
    }

}
