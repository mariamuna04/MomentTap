// Created by Kishorè Shanto on 12/15/22 at 15:21

package com.application.utility;

public record Date(int day, int month, int year) {

    @Override
    public String toString() {
        return "Date{" +
                "day=" + day +
                ", month=" + month +
                ", year=" + year +
                '}';
    }

    public static Date parseDate(String date) {
        String[] dateArray = date.split("-");
        return new Date(Integer.parseInt(dateArray[2]), Integer.parseInt(dateArray[1]), Integer.parseInt(dateArray[0]));
    }

    public static boolean compareDate(Date date) {
        Date currentDate = new Date(java.time.LocalDate.now().getDayOfMonth(), java.time.LocalDate.now().getMonthValue(), java.time.LocalDate.now().getYear());
        if (date.year() > currentDate.year()) {
            return true;
        } else if (date.year() == currentDate.year()) {
            if (date.month() > currentDate.month()) {
                return true;
            } else if (date.month() == currentDate.month()) {
                return date.day() >= currentDate.day();
            }
        }
        return false;
    }

    public static boolean compareDate(Date date1, Date date2) {
        if (date1.year() > date2.year()) {
            return true;
        } else if (date1.year() == date2.year()) {
            if (date1.month() > date2.month()) {
                return true;
            } else if (date1.month() == date2.month()) {
                return date1.day() >= date2.day();
            }
        }

        return false;
    }

}
