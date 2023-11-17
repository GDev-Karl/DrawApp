package com.example.drawapp.models;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Define a Rectangle from Shape
 */
public class Rectangle extends Shape {
    private double width;
    private double height;

    /**
     * Constructor
     * @param x  x-coordonate
     * @param y  y-coordonate
     * @param width  width of the shape
     * @param height  height of the shape
     * @param color  color of the shape
     */
    public Rectangle(double x, double y, double width, double height, Color color) {
        super(x, y, color);
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(color);
        gc.fillRect(x, y, this.width, this.height);
        gc.stroke();
    }

    @Override
    public void drawBorder(GraphicsContext gc) {
        gc.strokeRect(x - BORDER_OFFSET, y - BORDER_OFFSET, this.width + 2 * BORDER_OFFSET, this.height + 2 * BORDER_OFFSET);
    }

    @Override
    public void clearBorder(GraphicsContext gc) {
        gc.clearRect(x - BORDER_OFFSET, y - BORDER_OFFSET, this.width + 2 * BORDER_OFFSET, this.height + 2 * BORDER_OFFSET);
    }

    @Override
    public double area() {
        return this.width * this.height;
    }

    @Override
    public double perimeter() {
        return 2 * (this.width + this.height);
    }

    @Override
    public boolean contains(double mouseX, double mouseY) {
        if (mouseX < this.width + this.x || mouseY < this.height + this.y){
            return true;
        }
        return false;
    }

    /**
     *
     * @return  get the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     *
     * @return  get the height of the shape
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Create a Square Object from Rectangle
     * @param x  x-coordonate
     * @param y  y-coordonate
     * @param size  size of the Square
     * @param color color of the square
     * @return  a new Rectangle with width = height = size
     */
    public static Rectangle Square(double x, double y, double size, Color color){
        return new Rectangle(x, y, size, size, color);
    }
}
