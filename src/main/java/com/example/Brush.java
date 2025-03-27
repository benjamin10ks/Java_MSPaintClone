package com.example;

import javafx.scene.paint.Color;

public class Brush {
    private double x;   
    private double y;
    private double size;
    private Color color;
    private String shape;

    public Brush(double x, double y, double size, Color color) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.color = color;
        this.shape = shape;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getSize() {
        return size;
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

    public void setSize(double size) {
        this.size = size;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}