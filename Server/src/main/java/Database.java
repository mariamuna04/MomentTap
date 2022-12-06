// Created by Kishorè Shanto on Nov 20 2022 23:08


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {
    private static final String DATABASE_URL = "jdbc:mariadb://localhost:3306/cse2118";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD = "";
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
            System.err.println(e.getMessage());
        }
    }

    public static void queryForUserSignUp(String name, String email, String password) {
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



}
