package com.example;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;

public class MainController {
    @FXML
    private Canvas canvas;
    
    @FXML
    private Button buttonCurrent;
    
    @FXML
    private Button buttonProjected;
    
    private boolean isDrawing = false;
    private double lastX, lastY;
    
    @FXML
    public void initialize() {
        GraphicsContext gc = canvas.getGraphicsContext2D();
        
        // Set up the graphics context
        gc.setFill(Color.BLACK);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2.0);
        gc.setLineCap(StrokeLineCap.ROUND);
        gc.setLineJoin(StrokeLineJoin.ROUND);
        
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
    }
} 