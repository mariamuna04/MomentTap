package com.application.security;

import com.application.database.Database;

public class Authentication {
    // email validation
    public static boolean isValidEmail(String email) {
        return !email.matches("^[\\w-_.+]*[\\w-_.]@(\\w+\\.)+\\w+\\w$");
    }

    // password validation
    public static boolean isValidPassword(String password) {
        return password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,}$");
    }

    public static boolean authenticate(String username, String password) {
        // Query
        Database.queryForUserSignIn(username, password);

        // return
        return Database.resultSet != null;
    }
}
