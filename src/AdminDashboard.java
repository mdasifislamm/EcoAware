import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class AdminDashboard extends Application {

    // Shared event data
    private final ObservableList<Event> events = FXCollections.observableArrayList(
            new Event("Tree Planting", "2024-12-10", "Central Park"),
            new Event("Climate Workshop", "2024-12-15", "City Hall")
    );

    @Override
    public void start(Stage primaryStage) {
        // Main layout
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));

        // Title
        Label title = new Label("Admin Dashboard");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold;");
        root.setTop(title);
        BorderPane.setMargin(title, new Insets(10));

        // TableView setup
        TableView<Event> eventTable = new TableView<>();
        eventTable.setItems(events);
        eventTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Define columns
        TableColumn<Event, String> titleCol = new TableColumn<>("Title");
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<Event, String> dateCol = new TableColumn<>("Date");
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));

        TableColumn<Event, String> locationCol = new TableColumn<>("Location");
        locationCol.setCellValueFactory(new PropertyValueFactory<>("location"));

        eventTable.getColumns().addAll(titleCol, dateCol, locationCol);
        root.setCenter(eventTable);

        // Bottom buttons
        HBox buttonBox = new HBox(10);
        buttonBox.setPadding(new Insets(10));
        Button addButton = new Button("Add Event");
        Button editButton = new Button("Edit Event");
        Button deleteButton = new Button("Delete Event");
        buttonBox.getChildren().addAll(addButton, editButton, deleteButton);
        root.setBottom(buttonBox);

        // Add button action
        addButton.setOnAction(e -> addEvent(eventTable));

        // Edit button action
        editButton.setOnAction(e -> editEvent(eventTable));

        // Delete button action
        deleteButton.setOnAction(e -> deleteEvent(eventTable));

        // Scene and stage setup
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Admin Dashboard");
        primaryStage.show();
    }

    // Add Event
    private void addEvent(TableView<Event> eventTable) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Add Event");
        dialog.setHeaderText("Enter Event Details");
        dialog.setContentText("Format: Title,Date,Location");

        dialog.showAndWait().ifPresent(input -> {
            String[] details = input.split(",");
            if (details.length == 3) {
                events.add(new Event(details[0], details[1], details[2]));
                eventTable.refresh();
            } else {
                showAlert("Error", "Invalid input. Use the format: Title,Date,Location");
            }
        });
    }

    // Edit Event
    private void editEvent(TableView<Event> eventTable) {
        Event selectedEvent = eventTable.getSelectionModel().getSelectedItem();
        if (selectedEvent != null) {
            TextInputDialog dialog = new TextInputDialog(
                    selectedEvent.getTitle() + "," + selectedEvent.getDate() + "," + selectedEvent.getLocation());
            dialog.setTitle("Edit Event");
            dialog.setHeaderText("Edit Event Details");
            dialog.setContentText("Format: Title,Date,Location");

            dialog.showAndWait().ifPresent(input -> {
                String[] details = input.split(",");
                if (details.length == 3) {
                    selectedEvent.setTitle(details[0]);
                    selectedEvent.setDate(details[1]);
                    selectedEvent.setLocation(details[2]);
                    eventTable.refresh();
                } else {
                    showAlert("Error", "Invalid input. Use the format: Title,Date,Location");
                }
            });
        } else {
            showAlert("Error", "No event selected. Please select an event to edit.");
        }
    }

    // Delete Event
    private void deleteEvent(TableView<Event> eventTable) {
        Event selectedEvent = eventTable.getSelectionModel().getSelectedItem();
        if (selectedEvent != null) {
            events.remove(selectedEvent);
            eventTable.refresh();
        } else {
            showAlert("Error", "No event selected. Please select an event to delete.");
        }
    }

    // Show alert
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


