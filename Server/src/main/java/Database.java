// Created by Kishorè Shanto on Nov 20 2022 23:08

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

    /** The connection URL to the database */
    private static final String DATABASE_URL = "jdbc:mariadb://localhost:3306/cse2118";

    /** The username and password to connect to the database. Change if needed. */
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD = "";

    /** The ResultSet variable which stores only one query result at a time */
    // WARNING
    //  If ResultSet holds one query result a a time,
    //  there will be a problem if multiple queries are executed at the same time.
    // TODO: Fix this problem
    public static ResultSet resultSet;

    /** The connection object to the database */
    private static Connection connection = null;


    /**
     * Establish a connection to the database. This method is called when any query is executed.
     * If the connection is already established, it will not establish a new connection.
     * WARNING
     *  This method is not thread safe. If multiple threads try to establish a connection
     *  at the same time, there will be a problem.
     */
    private static void establishConnection() {
        try {
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
        } catch (SQLException e) {
            // FOR DEVELOPERS
            System.err.println(e.getMessage());
        }
    }

    public static void findUser(String email, String password) {
        try {
            establishConnection();
            resultSet = connection.createStatement().executeQuery("SELECT * FROM users WHERE email = '" + email + "' AND password = '" + password + "'");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void addUser(String name, String email, String password) {
        try {
            establishConnection();
            // Verify PK
            resultSet = connection.createStatement().executeQuery("SELECT * FROM users WHERE email = '" + email + "'");
            if(resultSet.next()) {
                System.out.println("Email Already Exists");
            } else {
                connection.createStatement().executeUpdate("INSERT INTO users (name, email, password) VALUES ('" + name + "', '" + email + "', '" + password + "')");
                System.out.println("User Created");
            }
        } catch (Exception e) {
            // TODO: handle exception, when insert same PK
        }
    }

    public static String queryForName(String email) {
        try {
            establishConnection();
            resultSet = connection.createStatement().executeQuery("SELECT name FROM users WHERE email = '" + email + "'");
            if(resultSet.next()) {
                return resultSet.getString("name");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }
}
