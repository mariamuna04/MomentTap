package com.application.utility;

import java.io.Serializable;

public record Date (int day, int month, int year) implements Serializable {

    public static boolean isCurrentDate(Date date) {
        Date currentDate = new Date(java.time.LocalDate.now().getDayOfMonth(), java.time.LocalDate.now().getMonthValue(), java.time.LocalDate.now().getYear());
        return date.year == currentDate.year && date.month == currentDate.month && date.day == currentDate.day;
    }

    @Override
    public String toString() {
        return day + "-" + month + "-" + year;
    }

    public static Date parseDate(String date) {
        String[] dateArray = date.split("-");
        return new Date(Integer.parseInt(dateArray[2]), Integer.parseInt(dateArray[1]), Integer.parseInt(dateArray[0]));
    }

    public static boolean compareDate(Date date) {
        Date currentDate = new Date(java.time.LocalDate.now().getDayOfMonth(), java.time.LocalDate.now().getMonthValue(), java.time.LocalDate.now().getYear());
        return compare(date, currentDate);
    }

    public static boolean compareDate(Date date1, Date date2) {
        return compare(date1, date2);
    }

    private static boolean compare(Date o1, Date o2) {
        if (o1.year() > o2.year()) {
            return true;
        } else if (o1.year() == o2.year()) {
            if (o1.month() > o2.month()) {
                return true;
            } else if (o1.month() == o2.month()) {
                return o1.day() >= o2.day();
            }
        }
        return false;
    }

}


// Project Finished on Mon Jan 2 2023 15:30:00 GMT+0600 (Bangladesh Standard Time)
