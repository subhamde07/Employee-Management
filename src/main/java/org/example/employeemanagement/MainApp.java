package org.example.employeemanagement;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Load the FXML file
            URL fxmlLocation = getClass().getResource("/main.fxml");
            if (fxmlLocation == null) {
                throw new IOException("FXML file not found!");
            }
            FXMLLoader loader = new FXMLLoader(fxmlLocation);
            Scene scene = new Scene(loader.load());

            // Set up the stage
            primaryStage.setTitle("Employee Management");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        System.out.println("Application is closing...");
        // Perform any necessary cleanup here

        // Exit the application
        System.exit(0);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
