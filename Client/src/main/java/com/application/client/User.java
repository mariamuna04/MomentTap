package com.application.client;


import com.application.serialShared.Event;
import com.application.utility.Date;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * This class is used to store the information of a user. On a successful sign in, the user information is stored.
 * The information is used to display the user's name and email in the home-activity.fxml file.
 * The information is also used to send the user's name and email to the server when the user creates an event.
 */
public class User {

    public static ArrayList<Event> events = new ArrayList<>();
    public static PriorityQueue<Event> sortedFutureEvents = new PriorityQueue<>(new Comparator<Event>() {
        @Override
        public int compare(Event o1, Event o2) {
            Date dateOne = Date.parseDate(o1.event_date());
            Date dateTwo = Date.parseDate(o2.event_date());

            if (Date.compareDate(dateOne, dateTwo)) {
                return 1;
            } else {
                return -1;
            }
        }
    });
    public static PriorityQueue<Event> sortedPastEvents = new PriorityQueue<>(new Comparator<Event>() {
        @Override
        public int compare(Event o1, Event o2) {
            Date dateOne = Date.parseDate(o1.event_date());
            Date dateTwo = Date.parseDate(o2.event_date());

            if (Date.compareDate(dateOne, dateTwo)) {
                return -1;
            } else {
                return 1;
            }
        }
    });


    /**
     * Name of the user
     */
    private static String name;

    /**
     * Email of the user
     */
    private static String email;

    private static String password;


    /**
     * Set the name and email of the user on a successful sign in
     *
     * @param name  Name of the user
     * @param email Email of the user
     */
    public static void setUser(String name, String email, String password) {
        User.name = name;
        User.email = email;
        User.password = password;
    }

    /**
     * Getter method to get the name and email of the user.
     *
     * @return Name and email of the user
     */
    public static String getName() {
        return name;
    }

    public static String getEmail() {
        return email;
    }

    public static String getPassword() {
        return password;
    }

    public static void cleanMemory(){
        // Remove all events from the user
        events.clear();
        sortedFutureEvents.clear();
        sortedPastEvents.clear();
    }
}
