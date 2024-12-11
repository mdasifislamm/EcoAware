import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Map;

public class AdminLogin {
    // Predefined admin credentials
    private static final Map<String, String> ADMIN_CREDENTIALS = Map.of(
        "admin", "1234" // Replace with actual credentials
    );

    public void show(Stage primaryStage) {
        // Admin Login Layout
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #F0F8FF;");

        // Input fields
        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");

        // Login Button
        Button loginButton = new Button("Login");
        loginButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");

        // Feedback Message
        Label message = new Label();
        message.setStyle("-fx-text-fill: red;");

        // Layout Assembly
        layout.getChildren().addAll(new Label("Admin Login"), usernameField, passwordField, loginButton, message);

        // Login Button Action
        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            if (ADMIN_CREDENTIALS.containsKey(username) && ADMIN_CREDENTIALS.get(username).equals(password)) {
                message.setText("Login Successful!");
                new AdminDashboard().start(primaryStage); // Redirect to Admin Dashboard
                ((Stage) loginButton.getScene().getWindow()).close(); // Close Login Screen
            } else {
                message.setText("Invalid username or password!");
            }
        });

        // Set Scene
        Scene scene = new Scene(layout, 300, 200);
        Stage loginStage = new Stage();
        loginStage.setScene(scene);
        loginStage.setTitle("Admin Login");
        loginStage.show();
    }
}
