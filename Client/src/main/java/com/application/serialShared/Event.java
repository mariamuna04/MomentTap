package com.application.serialShared;

import com.application.utility.Time;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * This Record used to make a record of an event.
 * The record is used to send the event to the server.
 */
public final class Event implements Serializable {
    @Serial
    private static final long serialVersionUID = 0L;
    private final String user_email;
    private final String event_name;
    private final String event_description;
    private final String event_category;
    private final String event_date;
    private final Time event_start_time;
    private final Time event_end_time;

    public boolean isShared = false;

    /**
     * @param user_email        Email of the user who created the event
     * @param event_name        Name of the event
     * @param event_description Description of the event
     * @param event_category    Category of the event
     * @param event_date        Date of the event
     * @param event_start_time  Start time of the event
     * @param event_end_time    End time of the event
     */
    public Event(String user_email, String event_name, String event_description, String event_category,
                 String event_date, Time event_start_time, Time event_end_time) {
        this.user_email = user_email;
        this.event_name = event_name;
        this.event_description = event_description;
        this.event_category = event_category;
        this.event_date = event_date;
        this.event_start_time = event_start_time;
        this.event_end_time = event_end_time;
    }

    @Override
    public String toString() {
        return event_name + " " + event_description + " " + event_category + " " + event_date + " " + event_start_time + " " + event_end_time;
    }

    public String user_email() {
        return user_email;
    }

    public String event_name() {
        return event_name;
    }

    public String event_description() {
        return event_description;
    }

    public String event_category() {
        return event_category;
    }

    public String event_date() {
        return event_date;
    }

    public Time event_start_time() {
        return event_start_time;
    }

    public Time event_end_time() {
        return event_end_time;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Event) obj;
        return Objects.equals(this.user_email, that.user_email) &&
                Objects.equals(this.event_name, that.event_name) &&
                Objects.equals(this.event_description, that.event_description) &&
                Objects.equals(this.event_category, that.event_category) &&
                Objects.equals(this.event_date, that.event_date) &&
                Objects.equals(this.event_start_time, that.event_start_time) &&
                Objects.equals(this.event_end_time, that.event_end_time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user_email, event_name, event_description, event_category, event_date, event_start_time, event_end_time);
    }


}