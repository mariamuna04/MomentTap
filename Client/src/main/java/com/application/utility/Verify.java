// Created by Kishorè Shanto on 12/20/22 at 21:30

package com.application.utility;

public class Verify {
    // email verification
    public static boolean isEmailValid(String email) {
        if(email.equals(".")) return true;
        else return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    // password verification
    public static boolean isPasswordValid(String password) {
        if(password.equals(".")) return true;
        else return password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$");
    }

    // name verification
    public static boolean isNameValid(String name) {
        return name.matches("^[A-Za-z]+(?: [A-Za-z]+)*$");
    }

    // date verification
    // format: yyyy-mm-dd
    public static boolean isDateValid(String date) {
        return date.matches("^(19|20)\\d\\d-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$");
    }

    // time verification
    // format: hh:mm
    public static boolean isTimeValid(String time) {
        return time.matches("^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$");
    }

    // md5 convert
    public static String md5(String key) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(key.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte item : array) {
                sb.append(Integer.toHexString((item & 0xFF) | 0x100), 1, 3);
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
