// Created by Kishorè Shanto on Nov 20 2022 23:08


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {
    private static final String DATABASE_URL = "jdbc:mariadb://localhost:3306/cse2118";
    private static final String DATABASE_USER = "hello";
    private static final String DATABASE_PASSWORD = "12345";
    public static ResultSet resultSet;
    private static Connection connection = null;

    private static void establishConnection() {

        try {
            connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
        } catch (SQLException e) {
            // FOR DEVELOPERS
            System.err.println(e.getMessage());
        }
    }

    public static void queryForUserSignIn(String email, String password) {
        try {
            establishConnection();
            resultSet = connection.createStatement().executeQuery("SELECT * FROM users WHERE email = '" + email + "' AND password = '" + password + "'");
        } catch (Exception e) {
            // error
        }
    }



}
