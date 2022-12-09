import java.io.Serializable;

public class Event implements Serializable {

    private String user_email;
    private String event_name;
    private String event_description;
    private String event_date;
    private int event_start_time;
    private int event_end_time;
    private String event_category;;

    public Event(String user_email, String event_name, String event_description,String event_date, int event_start_time, int event_end_time, String event_category) {
        this.user_email = user_email;
        this.event_name = event_name;
        this.event_description = event_description;
        this.event_date = event_date;
        this.event_start_time = event_start_time;
        this.event_end_time = event_end_time;
        this.event_category = event_category;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getEvent_name() {
        return event_name;
    }

    public void setEvent_name(String event_name) {
        this.event_name = event_name;
    }

    public String getEvent_description() {
        return event_description;
    }

    public void setEvent_description(String event_description) {
        this.event_description = event_description;
    }

    public int getEvent_start_time() {
        return event_start_time;
    }

    public void setEvent_start_time(int event_start_time) {
        this.event_start_time = event_start_time;
    }

    public int getEvent_end_time() {
        return event_end_time;
    }

    public void setEvent_end_time(int event_end_time) {
        this.event_end_time = event_end_time;
    }

    public String getEvent_category() {
        return event_category;
    }

    public void setEvent_category(String event_category) {
        this.event_category = event_category;
    }

    public String getEvent_date() {
        return event_date;
    }

    public void setEvent_date(String event_date) {
        this.event_date = event_date;
    }
}
