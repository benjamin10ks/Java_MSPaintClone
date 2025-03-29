package com.example;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.shape.StrokeLineJoin;
import javafx.scene.shape.Rectangle;
import javafx.scene.Cursor;
import javafx.stage.FileChooser;
import javafx.scene.image.WritableImage;
import java.io.File;
import java.io.IOException;
import java.awt.image.RenderedImage;
import javax.imageio.ImageIO;
import javafx.embed.swing.SwingFXUtils;



public class MainController {
    @FXML private Canvas canvas;
    @FXML private ToggleButton toolSelect;
    @FXML private ToggleButton toolBrush;
    @FXML private ComboBox<String> brushSize;
    @FXML private Rectangle colorBlack;
    @FXML private Rectangle colorWhite;
    @FXML private Rectangle colorRed;
    @FXML private Rectangle colorGreen;
    @FXML private Rectangle colorBlue;
    @FXML private Rectangle colorYellow;
    @FXML private Rectangle colorCyan;
    @FXML private Rectangle colorMagenta;
    @FXML private MenuItem menuSave;
    @FXML private MenuItem menuSaveAs;
    @FXML private Label statusLabel;
    
    private boolean isDrawing = false;
    private double lastX, lastY;
    private Color currentColor = Color.BLACK;
    private double currentSize = 2.0;
    
    @FXML
    public void initialize() {
        // Set up cursors
        setupCursors();
        
        // Set up the graphics context
        GraphicsContext gc = canvas.getGraphicsContext2D();
        setupGraphicsContext(gc);
        
        // Set up tool selection
        toolBrush.setSelected(true);
        toolSelect.selectedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                toolBrush.setSelected(false);
                statusLabel.setText("Select tool active");
            }
        });
        toolBrush.selectedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                toolSelect.setSelected(false);
                statusLabel.setText("Brush tool active");
            }
        });
        
        // Set up brush size
        brushSize.setValue("2");
        brushSize.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                currentSize = Double.parseDouble(newVal);
                setupGraphicsContext(gc);
            }
        });
        
        // Set up color selection
        setupColorSelection(gc);
        
        // Set up menu items
        menuSave.setOnAction(e -> handleSave());
        menuSaveAs.setOnAction(e -> handleSaveAs());
        
        // Add mouse event handlers
        setupMouseHandlers(gc);
    }
    
    private void setupCursors() {
        // Set cursor for buttons and interactive elements
        toolSelect.setCursor(Cursor.HAND);
        toolBrush.setCursor(Cursor.HAND);
        brushSize.setCursor(Cursor.HAND);
        colorBlack.setCursor(Cursor.HAND);
        colorWhite.setCursor(Cursor.HAND);
        colorRed.setCursor(Cursor.HAND);
        colorGreen.setCursor(Cursor.HAND);
        colorBlue.setCursor(Cursor.HAND);
        colorYellow.setCursor(Cursor.HAND);
        colorCyan.setCursor(Cursor.HAND);
        colorMagenta.setCursor(Cursor.HAND);
        canvas.setCursor(Cursor.CROSSHAIR);
    }
    
    private void setupGraphicsContext(GraphicsContext gc) {
        gc.setFill(currentColor);
        gc.setStroke(currentColor);
        gc.setLineWidth(currentSize);
        gc.setLineCap(StrokeLineCap.ROUND);
        gc.setLineJoin(StrokeLineJoin.ROUND);
    }
    
    private void setupColorSelection(GraphicsContext gc) {
        colorBlack.setOnMouseClicked(e -> setColor(Color.BLACK, gc));
        colorWhite.setOnMouseClicked(e -> setColor(Color.WHITE, gc));
        colorRed.setOnMouseClicked(e -> setColor(Color.RED, gc));
        colorGreen.setOnMouseClicked(e -> setColor(Color.GREEN, gc));
        colorBlue.setOnMouseClicked(e -> setColor(Color.BLUE, gc));
        colorYellow.setOnMouseClicked(e -> setColor(Color.YELLOW, gc));
        colorCyan.setOnMouseClicked(e -> setColor(Color.CYAN, gc));
        colorMagenta.setOnMouseClicked(e -> setColor(Color.MAGENTA, gc));
    }
    
    private void setColor(Color color, GraphicsContext gc) {
        currentColor = color;
        setupGraphicsContext(gc);
        statusLabel.setText("Color changed to: " + color.toString());
    }
    
    private void setupMouseHandlers(GraphicsContext gc) {
        canvas.setOnMousePressed(e -> {
            if (toolBrush.isSelected()) {
                isDrawing = true;
                lastX = e.getX();
                lastY = e.getY();
                statusLabel.setText("Drawing started");
            }
        });
        
        canvas.setOnMouseReleased(e -> {
            isDrawing = false;
            statusLabel.setText("Drawing ended");
        });
        
        canvas.setOnMouseDragged(e -> {
            if (isDrawing && toolBrush.isSelected()) {
                gc.strokeLine(lastX, lastY, e.getX(), e.getY());
                lastX = e.getX();
                lastY = e.getY();
                statusLabel.setText("Drawing at: " + (int)e.getX() + ", " + (int)e.getY());
            }
        });
    }
    
    private void handleSave() {
        handleSaveAs();
    }
    
    private void handleSaveAs() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Image");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png"));
        File file = fileChooser.showSaveDialog(canvas.getScene().getWindow());
        
        if (file != null) {
            try {
                WritableImage writableImage = new WritableImage((int) canvas.getWidth(), (int) canvas.getHeight());
                canvas.snapshot(null, writableImage);
                RenderedImage renderedImage = SwingFXUtils.fromFXImage(writableImage, null);
                ImageIO.write(renderedImage, "png", file);
                statusLabel.setText("Image saved to: " + file.getAbsolutePath());
            } catch (IOException e) {
                statusLabel.setText("Error saving image: " + e.getMessage());
            }
        }
    }
} 