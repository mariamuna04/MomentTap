package com.application.client;

public class User {

    private static String name;
    private static String email;


    public static void setUser(String name, String email) {
        User.name = name;
        User.email = email;
    }

    public static String getName() {
        return name;
    }

    public static String getEmail() {
        return email;
    }

}
