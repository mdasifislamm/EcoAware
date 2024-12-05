import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainMenu extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Buttons for navigation
        Button adminButton = new Button("Admin Dashboard");
        Button publicButton = new Button("Public Dashboard");

        // Navigate to Admin Dashboard
        adminButton.setOnAction(e -> {
            AdminDashboard adminDashboard = new AdminDashboard();
            adminDashboard.start(primaryStage); // Pass the same stage
        });

        // Navigate to Public Dashboard
        publicButton.setOnAction(e -> {
            PublicDashboard publicDashboard = new PublicDashboard();
            publicDashboard.start(primaryStage); // Pass the same stage
        });

        // Layout
        VBox root = new VBox(10, adminButton, publicButton);
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 300, 200);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Main Menu");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
