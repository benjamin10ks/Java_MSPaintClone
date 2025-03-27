package com.example;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Main extends Application {

    final private int DEFAULT_WIDTH = 800;
    final private int DEFAULT_HEIGHT = 600;



    @Override
    public void start(Stage primaryStage) {
        // Create a root pane
        Pane root = new Pane();
        
        // Create a scene with the root pane
        Scene scene = new Scene(root, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        final Canvas canvas = new Canvas(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(Color.BLACK);
        gc.fillOval(0, 0, 300, 300);

        root.getChildren().add(canvas);

        // Set up the primary stage
        primaryStage.setTitle("MS Paint Clone");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/images/mordandrig.png")));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
} 