import java.io.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    public static <T> void saveDataToFile(ObservableList<T> data, String filename) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))) {
            out.writeObject(new ArrayList<>(data)); // Convert ObservableList to ArrayList
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <T> ObservableList<T> loadDataFromFile(String filename) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))) {
            return FXCollections.observableArrayList((ArrayList<T>) in.readObject());
        } catch (IOException | ClassNotFoundException e) {
            return FXCollections.observableArrayList(); // Return empty list on error
        }
    }
}
