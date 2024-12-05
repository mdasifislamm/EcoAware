
import java.io.*;
import java.util.*;

public class EventStorage {
    private static final String FILE_PATH = "events.csv";

    // Load events from the file
    public static List<Event> loadEvents() {
        List<Event> events = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 3) {
                    events.add(new Event(data[0], data[1], data[2]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return events;
    }

    // Save events to the file
    public static void saveEvents(List<Event> events) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Event event : events) {
                writer.write(event.getTitle() + "," + event.getDate() + "," + event.getLocation());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
