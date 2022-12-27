package com.application.serialShared;

import com.application.utility.Date;
import com.application.utility.Time;

import java.io.Serializable;

public record Event(String user_email, String event_name, String event_description, String event_category,
                    String event_date, Time event_start_time, Time event_end_time) implements Serializable {
    @Override
    public String toString() {
        return event_name + " " + event_description + " " + event_category + " " + event_date + " " + event_start_time + " " + event_end_time;
    }

}
