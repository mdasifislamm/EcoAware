import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class PublicDashboard extends Application {

    // ObservableLists to store data
    private ObservableList<Event> events = FXCollections.observableArrayList();
    private ObservableList<String> resources = FXCollections.observableArrayList("Climate Change 101", "Sustainable Living Guide");

    @Override
    public void start(Stage primaryStage) {
        // Main layout with TabPane
        TabPane tabPane = new TabPane();

        // Events Tab
        Tab eventsTab = new Tab("Events");
        eventsTab.setClosable(false);
        eventsTab.setContent(createEventView());

        // Resources Tab
        Tab resourcesTab = new Tab("Resources");
        resourcesTab.setClosable(false);
        resourcesTab.setContent(createResourceView());

        // Pledges Tab
        Tab pledgesTab = new Tab("Pledges");
        pledgesTab.setClosable(false);
        pledgesTab.setContent(createPledgeView());

        tabPane.getTabs().addAll(eventsTab, resourcesTab, pledgesTab);

        // Scene setup
        Scene scene = new Scene(tabPane, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Public User Dashboard");
        primaryStage.show();
    }

    // Create Event View
    private BorderPane createEventView() {
        BorderPane eventPane = new BorderPane();
        eventPane.setPadding(new Insets(10));

        // Search Bar
        TextField searchField = new TextField();
        searchField.setPromptText("Search events...");
        Button searchButton = new Button("Search");

        HBox searchBox = new HBox(10, searchField, searchButton);
        searchBox.setPadding(new Insets(10));

        // TableView for Events
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

        // Add components to the pane
        eventPane.setTop(searchBox);
        eventPane.setCenter(eventTable);

        // Search functionality
        searchButton.setOnAction(e -> {
            String query = searchField.getText().toLowerCase();
            eventTable.setItems(events.filtered(event -> event.getTitle().toLowerCase().contains(query)));
        });

        return eventPane;
    }

    // Create Resource View
    private ListView<String> createResourceView() {
        ListView<String> resourceList = new ListView<>(resources);
        resourceList.setPrefHeight(200);

        resourceList.setOnMouseClicked(e -> {
            String selectedResource = resourceList.getSelectionModel().getSelectedItem();
            if (selectedResource != null) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Resource Details");
                alert.setHeaderText(selectedResource);
                alert.setContentText("Description for " + selectedResource);
                alert.showAndWait();
            }
        });

        return resourceList;
    }

    // Create Pledge View
    private BorderPane createPledgeView() {
        BorderPane pledgePane = new BorderPane();
        pledgePane.setPadding(new Insets(10));

        // Pledge TextField
        TextField pledgeField = new TextField();
        pledgeField.setPromptText("Enter your pledge...");

        Button submitPledge = new Button("Submit Pledge");

        HBox pledgeBox = new HBox(10, pledgeField, submitPledge);
        pledgeBox.setPadding(new Insets(10));

        // Handle Pledge Submission
        submitPledge.setOnAction(e -> {
            String pledge = pledgeField.getText();
            if (!pledge.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Pledge Submitted");
                alert.setHeaderText("Thank you for your pledge!");
                alert.setContentText("You pledged: " + pledge);
                alert.showAndWait();
                pledgeField.clear();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Empty Pledge");
                alert.setContentText("Please enter a pledge before submitting.");
                alert.showAndWait();
            }
        });

        pledgePane.setTop(new Label("Community Pledges"));
        pledgePane.setCenter(pledgeBox);

        return pledgePane;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
