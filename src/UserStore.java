import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UserStore {
    private static final String FILE_NAME = "users.dat";
    private static final ObservableList<User> users = FXCollections.observableArrayList(loadUsersFromFile());

    public static void addUser(String username, String password) {
        users.add(new User(username, password));
        saveUsersToFile();
    }

    public static boolean validateUser(String username, String password) {
        return users.stream().anyMatch(user -> user.getUsername().equals(username) && user.getPassword().equals(password));
    }

    public static ObservableList<User> getUsers() {
        return users;
    }

    private static void saveUsersToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(new ArrayList<>(users)); // Convert to serializable list
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<User> loadUsersFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (List<User>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>(); // Return empty list if file does not exist or is corrupted
        }
    }
}
