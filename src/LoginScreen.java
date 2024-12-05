import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LoginScreen extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Main container
        VBox root = new VBox(10);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: #f0f8ff;"); // Light blue background

        // Logo
        Image logo = new Image("file:logo.png"); // Replace with your logo path
        ImageView logoView = new ImageView(logo);
        logoView.setFitWidth(150);
        logoView.setPreserveRatio(true);

        // Title
        Label title = new Label("EcoAware Login");
        title.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");

        // Grid layout for form
        GridPane grid = new GridPane();
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setAlignment(Pos.CENTER);

        // Username label and input
        Label usernameLabel = new Label("Username:");
        usernameLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #2c3e50;");
        TextField usernameInput = new TextField();
        usernameInput.setPromptText("Enter your username");
        usernameInput.setStyle("-fx-background-radius: 5; -fx-border-color: #3498db; -fx-border-radius: 5;");

        // Password label and input
        Label passwordLabel = new Label("Password:");
        passwordLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #2c3e50;");
        PasswordField passwordInput = new PasswordField();
        passwordInput.setPromptText("Enter your password");
        passwordInput.setStyle("-fx-background-radius: 5; -fx-border-color: #3498db; -fx-border-radius: 5;");

        // Role selection
        Label roleLabel = new Label("Role:");
        roleLabel.setStyle("-fx-font-size: 14px; -fx-text-fill: #2c3e50;");
        ComboBox<String> roleDropdown = new ComboBox<>();
        roleDropdown.getItems().addAll("Admin", "Public User");
        roleDropdown.setPromptText("Select Role");
        roleDropdown.setStyle("-fx-background-radius: 5; -fx-border-color: #3498db; -fx-border-radius: 5;");

        // Login button
        Button loginButton = new Button("Login");
        loginButton.setStyle(
                "-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 5 15; -fx-background-radius: 10;");
        loginButton.setOnAction(e -> {
            String username = usernameInput.getText();
            String password = passwordInput.getText();
            String role = roleDropdown.getValue();

            if (username.isEmpty() || password.isEmpty() || role == null) {
                showAlert(Alert.AlertType.ERROR, "Error", "Please fill in all fields!");
            } else {
                // Redirect to dashboard
                if (role.equals("Admin")) {
                    System.out.println("Redirecting to Admin Dashboard...");
                } else {
                    System.out.println("Redirecting to Public User Dashboard...");
                }
            }
        });

        // Add elements to the grid
        grid.add(usernameLabel, 0, 0);
        grid.add(usernameInput, 1, 0);
        grid.add(passwordLabel, 0, 1);
        grid.add(passwordInput, 1, 1);
        grid.add(roleLabel, 0, 2);
        grid.add(roleDropdown, 1, 2);

        // Add components to the root layout
        root.getChildren().addAll(logoView, title, grid, loginButton);

        // Set up the scene
        Scene scene = new Scene(root, 400, 450);
        primaryStage.setScene(scene);
        primaryStage.setTitle("EcoAware Login");
        primaryStage.show();
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
