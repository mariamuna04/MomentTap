package com.application.serialShared;

import java.io.Serializable;

/**
 * This Record used to make a record of an event.
 * The record is used to send the event to the server.
 *
 * @param user_email Email of the user who created the event
 * @param event_name Name of the event
 * @param event_description Description of the event
 * @param event_category Category of the event
 * @param event_date Date of the event
 * @param event_start_time Start time of the event
 * @param event_end_time End time of the event
 */
public record Event(String user_email, String event_name, String event_description, String event_category,
                    String event_date, int event_start_time, int event_end_time) implements Serializable {

}
