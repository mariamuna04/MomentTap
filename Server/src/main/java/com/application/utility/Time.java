package com.application.utility;

import java.io.Serializable;

public record Time (int hour, int minute) implements Serializable {

    @Override
    public String toString() {
        return hour + ":" + minute;
    }

    public static Time parseTime(String time) {
        String[] timeArray = time.split(":");
        return new Time(Integer.parseInt(timeArray[0]), Integer.parseInt(timeArray[1]));
    }

    public static boolean compareTime(Time time) {
        Time currentTime = new Time(java.time.LocalTime.now().getHour(), java.time.LocalTime.now().getMinute());
        return compare(time, currentTime);
    }

    public static boolean compareTime(Time time1, Time time2) {
        return compare(time1, time2);
    }

    private static boolean compare(Time o1, Time o2) {
        if (o1.hour() > o2.hour()) {
            return true;
        } else if (o1.hour() == o2.hour()) {
            return o1.minute() >= o2.minute();
        }
        return false;
    }
}
