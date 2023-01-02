package com.application.utility;

public class Verify {
    public static boolean isEmailValid(String email) {
        if (email.equals(".")) return true;
        else return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    public static boolean isPasswordValid(String password) {
        if (password.equals(".")) return true;
        else return password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$");
    }

    public static boolean isNameValid(String name) {
        return name.matches("^[A-Za-z]+(?: [A-Za-z]+)*$");
    }

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


// Project Finished on Mon Jan 2 2023 15:30:00 GMT+0600 (Bangladesh Standard Time)
