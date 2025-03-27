package com.example;

import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Main extends Application {
    private boolean isDrawing = false;
    private double lastX, lastY;

    @Override
    public void start(Stage primaryStage) {
        // Initialize window
        WindowInit windowInit = new WindowInit();
        Scene scene = windowInit.initializeWindow(primaryStage);

        Brush brush = new Brush(2.0, Color.BLACK, "circle");
        // Get the root pane from the scene
        Pane root = (Pane) scene.getRoot();
        
        // Create canvas
        final Canvas canvas = new Canvas(1000, 800);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Set the cursor to crosshair when hovering over the canvas
        canvas.setCursor(Cursor.NONE);

        // Set up the graphics context
        gc.setFill(Color.BLACK);
        gc.setStroke(brush.getColor());
        gc.setLineWidth(brush.getSize());

        // Add mouse event handlers
        canvas.setOnMousePressed(e -> {
            isDrawing = true;
            lastX = e.getX();
            lastY = e.getY();
        });

        canvas.setOnMouseReleased(e -> {
            isDrawing = false;
        });

        canvas.setOnMouseDragged(e -> {
            if (isDrawing) {
                gc.strokeLine(lastX, lastY, e.getX(), e.getY());
                lastX = e.getX();
                lastY = e.getY();
            }
        });

        root.getChildren().add(canvas);
    }

    public static void main(String[] args) {
        launch(args);
    }
} 