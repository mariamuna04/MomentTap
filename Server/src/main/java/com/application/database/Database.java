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
    synchronized private static void establishConnection() {
        try {
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
            System.out.println("Database Server Connected");
        } catch (SQLException e) {
            System.err.println("Error while connecting to database [Database.java:49]");
            System.err.println("Possible reasons: Database is not running, database URL or Database auth is incorrect");
        }
    }

    @DatabaseQuery
    synchronized public static void findUser(String email, String password) {
        try {
            establishConnection();
            resultSet = connection.createStatement().executeQuery("SELECT * FROM users WHERE email = '" + email + "' AND password = '" + password + "'");
            System.out.println("Query Executed: findUser");
        } catch (Exception e) {
            System.err.println("Error while executing query: findUser [Database.java:61]");
            System.err.println("Possible reasons: Database is not running, or SQL syntax is incorrect");
        }
    }

    @DatabaseQuery
    synchronized public static void addUser(String name, String email, String password) {
        try {
            establishConnection();
            resultSet = connection.createStatement().executeQuery("SELECT * FROM users WHERE email = '" + email + "'");
            if (resultSet.next()) {
                System.out.println("User already exist with the email");
            } else {
                connection.createStatement().executeUpdate("INSERT INTO users (name, email, password) VALUES ('" + name + "', '" + email + "', '" + password + "')");
                System.out.println("User Created");
            }
        } catch (Exception e) {
            System.out.println("Error while executing query: addUser [Database.java:78]");
            System.err.println("Possible reason: Database is not running, or SQL syntax is incorrect");
        }
    }

    @DatabaseQuery
    synchronized public static String queryForName(String email) {
        try {
            establishConnection();
            resultSet = connection.createStatement().executeQuery("SELECT name FROM users WHERE email = '" + email + "'");
            if (resultSet.next()) {
                return resultSet.getString("name");
            } else return null;
        } catch (Exception e) {
            System.out.println("Error while executing query: queryForName [Database.java:93]");
            System.err.println("Possible reason: Database is not running, or SQL syntax is incorrect");
            return null;
        }
    }


    @DatabaseQuery
    synchronized public static void addEvent(Event event) {
        String email = event.user_email();
        String name = event.event_name();
        String description = event.event_description();
        String category = event.event_category();
        String date = event.event_date();
        String start_time = event.event_start_time().toString();
        String end_time = event.event_end_time().toString();

        // add event to database
        try {
            establishConnection();
            connection.createStatement().executeUpdate("INSERT INTO events (user_email, event_name, " + "event_description,  event_category, event_date, event_start_time, event_end_time) VALUES " + "('" + email + "', '" + name + "', '" + description + "', '" + category + "', '" + date + "', '" + start_time + "', '" + end_time + "')");
            System.out.println("Event Created for " + email);
        } catch (Exception e) {
            System.out.println("Error while executing query: addEvent [Database.java:116]");
            System.err.println("Possible reason: Database is not running, or SQL syntax is incorrect");
        }
    }

    @DatabaseQuery
    synchronized public static void deleteEvent(String email, String name, String date) {
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
    synchronized public static void searchEvent(String email, String keyword) {
        try {
            establishConnection();
            if (keyword.equals("")) {
                resultSet = connection.createStatement().executeQuery("SELECT * FROM events WHERE user_email LIKE '%" + email + "%'");
            } else if (keyword.equals("shared")) {
                resultSet = connection.createStatement().executeQuery("SELECT * FROM events WHERE user_email LIKE '%" + email + "%' AND ( event_from NOT LIKE '%no%')");
            } else {
                resultSet = connection.createStatement().executeQuery("SELECT * FROM events WHERE user_email LIKE '%" + email + "%' AND ( event_name LIKE '%" + keyword + "%' OR event_description LIKE '%" + keyword + "%' OR event_category LIKE '%" + keyword + "%' OR event_date LIKE '%" + keyword + "%' OR event_from LIKE '%" + keyword + "%')");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @DatabaseQuery
    synchronized public static void updateProfile(String email, String newName, String newPassword) {
        try {
            establishConnection();
            if (!newName.equals("")) {
                System.out.println("new name=" + newName);
                connection.createStatement().executeUpdate("UPDATE users SET name = '" + newName + "' , password = '" + newPassword + "' WHERE email = '" + email + "'");
            } else {
                connection.createStatement().executeUpdate("UPDATE users SET password = '" + newPassword + "' WHERE email = '" + email + "'");
            }
        } catch (Exception e) {
            System.out.println("Error while executing query: updateEvent [Database.java:116]");
            System.err.println("Possible reason: Database is not running, or SQL syntax is incorrect");
        }
    }


    synchronized public static void updateEvent(Event newEvent, String oldName, String oldDate) {
        String email = newEvent.user_email();
        String name = newEvent.event_name();
        String description = newEvent.event_description();
        String category = newEvent.event_category();
        String date = newEvent.event_date();
        String start_time = newEvent.event_start_time().toString();
        String end_time = newEvent.event_end_time().toString();

        // update event in database
        try {
            establishConnection();
            connection.createStatement().executeUpdate("UPDATE events SET event_name = '" + name + "', event_description = '" + description + "', event_category = '" + category + "', event_date = '" + date + "', event_start_time = '" + start_time + "', event_end_time = '" + end_time + "' WHERE user_email = '" + email + "' AND event_name = '" + oldName + "' AND event_date = '" + oldDate + "'");
            System.out.println("Event Updated");
        } catch (Exception e) {
            System.out.println("Error while executing query: updateEvent [Database.java:116]");
            System.err.println("Possible reason: Database is not running, or SQL syntax is incorrect");
        }
    }

    synchronized public static void shareEvent(Event event, String toEmail) {
        String email = event.user_email();
        String name = event.event_name();
        String description = event.event_description();
        String category = event.event_category();
        String date = event.event_date();
        String start_time = event.event_start_time().toString();
        String end_time = event.event_end_time().toString();

        try {
            establishConnection();
            connection.createStatement().executeUpdate("INSERT INTO events (user_email, event_name, event_description,  event_category, event_date, event_start_time, event_end_time, event_from) VALUES " + "('" + toEmail + "', '" + name + "', '" + description + "', '" + category + "', '" + date + "', '" + start_time + "', '" + end_time + "', '" + email + "')");
        } catch (Exception e) {
            System.out.println("Error while executing query: shareEvent [Database.java:116]");
            System.err.println("Possible reason: Database is not running, or SQL syntax is incorrect");
        }
    }
}

// Project Finished on Mon Jan 2 2023 15:30:00 GMT+0600 (Bangladesh Standard Time)

