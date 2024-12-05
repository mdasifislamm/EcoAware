import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataStore {
    public static final ObservableList<Event> events = FXCollections.observableArrayList();
    public static final ObservableList<String> resources = FXCollections.observableArrayList();

    static {
        events.add(new Event("Tree Planting", "2024-12-10", "Central Park"));
        events.add(new Event("Climate Workshop", "2024-12-15", "City Hall"));
        
        resources.add("Climate Change 101");
        resources.add("Sustainable Living Guide");
    }
}
	