import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class UserLogin {
    private static final ArrayList<User> users = new ArrayList<>();

    // Show the login and registration screen
    public void show(Stage primaryStage) {
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));

        // Login Fields
        TextField loginUsernameField = new TextField();
        loginUsernameField.setPromptText("Username");
        PasswordField loginPasswordField = new PasswordField();
        loginPasswordField.setPromptText("Password");
        Button loginButton = new Button("Login");

        // Registration Fields
        TextField registerUsernameField = new TextField();
        registerUsernameField.setPromptText("New Username");
        PasswordField registerPasswordField = new PasswordField();
        registerPasswordField.setPromptText("New Password");
        Button registerButton = new Button("Register");

        Label message = new Label();

        layout.getChildren().addAll(
                new Label("Login"),
                loginUsernameField, loginPasswordField, loginButton,
                new Label("Register"),
                registerUsernameField, registerPasswordField, registerButton,
                message
        );

        // Login Button Action
        loginButton.setOnAction(e -> {
            String username = loginUsernameField.getText();
            String password = loginPasswordField.getText();

            if (UserStore.validateUser(username, password)) {
                message.setText("Login Successful!");
                new PublicDashboard().start(primaryStage); // Open Public Dashboard
            } else {
                message.setText("Invalid username or password!");
            }
        });


        // Register Button Action
        registerButton.setOnAction(e -> {
            String username = registerUsernameField.getText();
            String password = registerPasswordField.getText();

            if (UserStore.getUsers().stream().anyMatch(user -> user.getUsername().equals(username))) {
                message.setText("Username already exists!");
            } else {
                UserStore.addUser(username, password); // Save to file
                message.setText("Registration Successful! You can now login.");
            }
        });


        Scene scene = new Scene(layout, 300, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("User Login and Registration");
    }

    // Validate login credentials
    private boolean validateLogin(String username, String password) {
        return users.stream().anyMatch(user -> user.getUsername().equals(username) && user.getPassword().equals(password));
    }

    // Register a new user
    private boolean registerUser(String username, String password) {
        if (users.stream().anyMatch(user -> user.getUsername().equals(username))) {
            return false; // Username already exists
        }
        users.add(new User(username, password));
        return true;
    }
}
