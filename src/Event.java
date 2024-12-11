import javafx.beans.property.SimpleStringProperty;

import java.io.IOException;
import java.io.Serializable;

public class Event implements Serializable {
    private static final long serialVersionUID = 1L; // Optional but recommended

    private transient SimpleStringProperty title;
    private transient SimpleStringProperty date;
    private transient SimpleStringProperty location;

    // Constructor
    public Event(String title, String date, String location) {
        this.title = new SimpleStringProperty(title);
        this.date = new SimpleStringProperty(date);
        this.location = new SimpleStringProperty(location);
    }

    // Getters and Setters
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

    // Serialization Helpers
    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
        out.writeUTF(getTitle());
        out.writeUTF(getDate());
        out.writeUTF(getLocation());
    }

    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.title = new SimpleStringProperty(in.readUTF());
        this.date = new SimpleStringProperty(in.readUTF());
        this.location = new SimpleStringProperty(in.readUTF());
    }
}
