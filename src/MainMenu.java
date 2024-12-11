import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class MainMenu extends Application {

    
	
	@Override
    public void start(Stage primaryStage) {
		Runtime.getRuntime().addShutdownHook(new Thread(DataStore::saveEvents));
        // Title Section
        Label appTitle = new Label("EcoAware: Climate Action Platform");
        appTitle.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        appTitle.setTextFill(Color.DARKGREEN);
        appTitle.setTextAlignment(TextAlignment.CENTER);

        Label appSubtitle = new Label("Taking steps towards a sustainable future");
        appSubtitle.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        appSubtitle.setTextFill(Color.GRAY);

        // Buttons Section
        Button loginButton = new Button("Login");
        loginButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 14px;");
        loginButton.setOnAction(e -> showLoginScreen(primaryStage));

        Button adminDashboardButton = new Button("Admin Dashboard");
        adminDashboardButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 14px;");
        adminDashboardButton.setOnAction(e -> showAdminLogin(primaryStage)); // Fixed Action

        Button publicDashboardButton = new Button("Public Dashboard");
        publicDashboardButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 14px;");
        publicDashboardButton.setOnAction(e -> new UserLogin().show(primaryStage));

        Button exitButton = new Button("Exit");
        exitButton.setStyle("-fx-background-color: #FF5722; -fx-text-fill: white; -fx-font-size: 14px;");
        exitButton.setOnAction(e -> primaryStage.close());

        // Layout Section
        VBox layout = new VBox(20);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #F0F8FF;"); // Light blue background
        layout.getChildren().addAll(appTitle, appSubtitle, loginButton, adminDashboardButton, publicDashboardButton, exitButton);

        // Scene and Stage Setup
        Scene scene = new Scene(layout, 400, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("EcoAware: Climate Action Platform");
        primaryStage.show();
    }

    // Login Button Action (Opens a general login screen)
    private void showLoginScreen(Stage primaryStage) {
        Stage loginStage = new Stage();
        VBox loginLayout = new VBox(10);
        loginLayout.setPadding(new Insets(20));
        loginLayout.setAlignment(Pos.CENTER);

        Label loginMessage = new Label("This is a placeholder for a general login screen.");
        loginMessage.setTextFill(Color.DARKGRAY);
        loginMessage.setFont(Font.font("Arial", FontWeight.NORMAL, 14));

        Button closeLoginButton = new Button("Close");
        closeLoginButton.setOnAction(e -> loginStage.close());

        loginLayout.getChildren().addAll(loginMessage, closeLoginButton);

        Scene loginScene = new Scene(loginLayout, 300, 200);
        loginStage.setScene(loginScene);
        loginStage.setTitle("Login Screen");
        loginStage.show();
    }

    // Admin Login Action (Correct Admin Login Method)
    private void showAdminLogin(Stage primaryStage) {
        // Call the admin login screen or redirect to Admin Dashboard after login
        new AdminLogin().show(primaryStage); // Assuming AdminLogin is the class for admin login
    }

    public static void main(String[] args) {
        launch(args);
    }
}
