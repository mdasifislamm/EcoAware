
import java.io.*;
import java.util.*;

public class ResourceStorage {
    private static final String FILE_PATH = "resources.csv";

    // Load resources from the file
    public static List<String> loadResources() {
        List<String> resources = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                resources.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resources;
    }

    // Save resources to the file
    public static void saveResources(List<String> resources) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (String resource : resources) {
                writer.write(resource);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
