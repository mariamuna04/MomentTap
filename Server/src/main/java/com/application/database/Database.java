// Created by Kishorè Shanto on Nov 20 2022 23:08
package com.application.database;

import com.application.serialShared.Event;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class is responsible for connecting to the database and executing queries.
 * Everything in this class is static because we only need one instance of the database
 * (assume it's a singleton class). This class has a result set which is used to store the
 * result of each query at a time.
 */
public class Database {

    /**
     * The connection URL to the database
     */
    private static final String DATABASE_URL = "jdbc:mariadb://localhost:3306/cse2118";

    /**
     * The username and password to connect to the database. Change if needed.
     */
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD = "";

    /**
     * The ResultSet variable which stores only one query result at a time
     */
    public static ResultSet resultSet;

    /**
     * The connection object to the database
     */
    private static Connection connection = null;


    /**
     * Establish a connection to the database. This method is called when any query is executed.
     * If the connection is already established, it will not establish a new connection.
     */
    private static void establishConnection() {
        try {
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    @DatabaseQuery
    public static void findUser(String email, String password) {
        try {
            establishConnection();
            resultSet = connection.createStatement().executeQuery("SELECT * FROM users WHERE email = '" + email + "' AND password = '" + password + "'");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @DatabaseQuery
    public static void addUser(String name, String email, String password) {
        try {
            establishConnection();
            // Verify PK
            resultSet = connection.createStatement().executeQuery("SELECT * FROM users WHERE email = '" + email + "'");
            if (resultSet.next()) {
                System.out.println("Email Already Exists");
            } else {
                connection.createStatement().executeUpdate("INSERT INTO users (name, email, password) VALUES ('" + name + "', '" + email + "', '" + password + "')");
                System.out.println("User Created");
            }
        } catch (Exception e) {
            // TODO: handle exception, when insert same PK
        }
    }

    @DatabaseQuery
    public static String queryForName(String email) {
        try {
            establishConnection();
            resultSet = connection.createStatement().executeQuery("SELECT name FROM users WHERE email = '" + email + "'");
            if (resultSet.next()) {
                return resultSet.getString("name");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }


    @DatabaseQuery
    public static void addEvent(Event event) {
        String email = event.user_email();
        String name = event.event_name();
        String description = event.event_description();
        String category = event.event_category();
        String date = event.event_date();
        int start_time = event.event_start_time();
        int end_time = event.event_end_time();

        // add event to database
        try {
            establishConnection();
            connection.createStatement().executeUpdate("INSERT INTO events (user_email, event_name, " +
                    "event_description,  event_category, event_date, event_start_time, event_end_time) VALUES " +
                    "('" + email + "', '" + name + "', '" + description + "', '" + category + "', '" + date +
                    "', '" + start_time + "', '" + end_time + "')");

            System.out.println("Event Created");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @DatabaseQuery
    public static void deleteEvent(String email, String name, String date) {
        // delete event from database
        try {
            establishConnection();
            connection.createStatement().executeUpdate("DELETE FROM events WHERE user_email = '" + email + "' AND event_name = '" + name + "' AND event_date = '" + date + "'");
            System.out.println("Event Deleted");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @DatabaseQuery
    public static void searchEvent(String keyword) {

        try {
            establishConnection();
            if (keyword.matches("[0-9]+")) {

                int integer_keyword = Integer.parseInt(keyword);

                resultSet = connection.createStatement().executeQuery("SELECT * FROM events WHERE event_name LIKE '%" + keyword + "%' OR event_description LIKE '%" + keyword + "%' OR event_category LIKE '%" + keyword + "%' OR event_date LIKE '%" + keyword + "%' OR (event_start_time <= " + integer_keyword + " AND event_end_time >= " + integer_keyword + ")");

            } else {

                resultSet = connection.createStatement().executeQuery("SELECT * FROM events WHERE event_name LIKE '%" + keyword + "%' OR event_description LIKE '%" + keyword + "%' OR event_category LIKE '%" + keyword + "%' OR event_date LIKE '%" + keyword + "%'");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

}
