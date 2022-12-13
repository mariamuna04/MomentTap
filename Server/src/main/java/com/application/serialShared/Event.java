package com.application.serialShared;

import java.io.Serializable;

public record Event(String user_email, String event_name, String event_description, String event_category,
                    String event_date, int event_start_time, int event_end_time) implements Serializable {

}
