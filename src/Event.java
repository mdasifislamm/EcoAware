

import javafx.beans.property.SimpleStringProperty;

public class Event {
    private final SimpleStringProperty title;
    private final SimpleStringProperty date;
    private final SimpleStringProperty location;

    public Event(String title, String date, String location) {
        this.title = new SimpleStringProperty(title);
        this.date = new SimpleStringProperty(date);
        this.location = new SimpleStringProperty(location);
    }

    public String getTitle() {
        return title.get();
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public String getDate() {
        return date.get();
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public String getLocation() {
        return location.get();
    }

    public void setLocation(String location) {
        this.location.set(location);
    }
}
