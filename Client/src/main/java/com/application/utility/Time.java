// Created by Kishorè Shanto on 12/19/22 at 11:38

package com.application.utility;

import java.io.Serializable;

public record Time(int hour, int minute) implements Serializable {

    public static boolean isCurrentTime(Time time) {
        Time currentTime = new Time(java.time.LocalTime.now().getHour(), java.time.LocalTime.now().getMinute());
        return time.hour == currentTime.hour && time.minute == currentTime.minute;
    }

    public static boolean compareTime(Time time) {
        Time currentTime = new Time(java.time.LocalTime.now().getHour(), java.time.LocalTime.now().getMinute());
        return compare(time, currentTime);
    }

    @Override
    public String toString() {
        if(hour < 10 && minute < 10) {
            return "0" + hour + ":" + "0" + minute;
        } else if(hour < 10) {
            return "0" + hour + ":" + minute;
        } else if(minute < 10) {
            return hour + ":" + "0" + minute;
        } else {
            return hour + ":" + minute;
        }
    }

    public static Time parseTime(String time) {
        if (time.contains(":")) {
            String[] timeArray = time.split(":");
            return new Time(Integer.parseInt(timeArray[0]), Integer.parseInt(timeArray[1]));
        } else if (time.contains(".")) {
            String[] timeArray = time.split("\\.");
            return new Time(Integer.parseInt(timeArray[0]), Integer.parseInt(timeArray[1]));
        } else {
            return new Time(Integer.parseInt(time), 0);
        }
    }

    public static boolean compareTime(Time time1, Time time2) {
        return compare(time1, time2);
    }

    private static boolean compare(Time o1, Time o2) {
        if (o1.hour() < o2.hour()) {
            return true;
        } else if (o1.hour() == o2.hour()) {
            return o1.minute() <= o2.minute();
        }
        return false;
    }
}


// Project Finished on Mon Jan 2 2023 15:30:00 GMT+0600 (Bangladesh Standard Time)
