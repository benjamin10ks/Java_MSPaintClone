package com.example;

import javafx.scene.paint.Color;

public class Brush {
    private double size;
    private Color color;
    private String shape;

        public Brush(double size, Color color, String shape) {
        this.size = size;
        this.color = color;
        this.shape = shape;
    }

    public double getSize() {
        return size;
    }
    
    public void setSize(double size) {
        this.size = size;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setShape(String shape) {
        switch (shape) {
            case "circle":
                this.shape = shape;
                break;
            case "square":
                this.shape = shape;
                break;
            case "triangle":
                this.shape = shape;
                break;
            default:
                throw new AssertionError();
        }
    }  

    public Color getColor() {
        return color;
    }

    public String getShape() {
        return shape;
    }
}