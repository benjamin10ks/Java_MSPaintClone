package com.example;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.image.Image;

public class WindowInit {

    public Scene initializeWindow(Stage primaryStage) {
        // Create a root pane
        Pane root = new Pane();
        
        // Create a scene with the root pane
        Scene scene = new Scene(root);

        // Set up the primary stage
        primaryStage.setTitle("MS Paint Clone");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/mordandrig.png")));
        primaryStage.setScene(scene);
        primaryStage.show();

        return scene;
    }
} 