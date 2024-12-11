import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataStore {
    public static final ObservableList<Event> events = FileUtils.loadDataFromFile("events.dat");

    static {
        if (events.isEmpty()) {
            events.add(new Event("Tree Planting", "2024-12-10", "Central Park"));
            events.add(new Event("Climate Workshop", "2024-12-15", "City Hall"));
        }
    }

    public static void saveEvents() {
        FileUtils.saveDataToFile(events, "events.dat");
    }
}
