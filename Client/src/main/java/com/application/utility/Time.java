// Created by Kishorè Shanto on 12/19/22 at 11:38

package com.application.utility;

public record Time (int hour, int minute) {

    @Override
    public String toString() {
        return "Time{" +
                "hour=" + hour +
                ", minute=" + minute +
                '}';
    }

    public static Time parseTime(String time) {
        String[] timeArray = time.split(":");
        return new Time(Integer.parseInt(timeArray[0]), Integer.parseInt(timeArray[1]));
    }

    public static boolean compareTime(Time time) {
        Time currentTime = new Time(java.time.LocalTime.now().getHour(), java.time.LocalTime.now().getMinute());
        if (time.hour() > currentTime.hour()) {
            return true;
        } else if (time.hour() == currentTime.hour()) {
            return time.minute() >= currentTime.minute();
        }
        return false;
    }

    public static boolean compareTime(Time time1, Time time2) {
        if (time1.hour() > time2.hour()) {
            return true;
        } else if (time1.hour() == time2.hour()) {
            return time1.minute() >= time2.minute();
        }
        return false;
    }

}
