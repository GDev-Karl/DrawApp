package com.example.drawapp.models;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import static java.lang.Math.PI;

public class Ellipse extends Shape {

    private double width;
    private double height;

    /**
     * Constructor of the shape ellipse
     *
     * @param x     x-coordonate
     * @param y     y-coordonate
     * @param color color of the shape
     */
    public Ellipse(double x, double y, double width, double height, Color color) {
        super(x, y, color);
        this.width = width;
        this.height = height;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(color);
        gc.fillOval(x, y, width * 2, height * 2);
        gc.stroke();
    }

    @Override
    public void drawBorder(GraphicsContext gc) {
        gc.strokeRect(x - BORDER_OFFSET, y - BORDER_OFFSET, (this.width + BORDER_OFFSET) * 2, (this.height + BORDER_OFFSET) * 2);
    }

    @Override
    public void clearBorder(GraphicsContext gc) {
        gc.clearRect(x - BORDER_OFFSET, y - BORDER_OFFSET, (this.width + BORDER_OFFSET) * 2, (this.height + BORDER_OFFSET) * 2);
    }

    @Override
    public double area() {
        return PI * width * height;
    }

    @Override
    public double perimeter() {
        return width * PI * height;
    }

    @Override
    public boolean contains(double mouseX, double mouseY) {
        if (mouseX <= this.x || mouseY <= this.y) {
            return true;
        }
        return false;
    }

    /**
     * the with of the ellipse
     *
     * @return
     */
    public double getWidth() {
        return width;
    }

    /**
     * change the width of the ellipse
     *
     * @param width
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * get the height of the ellipse
     *
     * @return
     */
    public double getHeight() {
        return height;
    }

    /**
     * edit the height of the ellipse
     *
     * @param height
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * Create a new Ellipse that is exactly like a cirle
     *
     * @param x
     * @param y
     * @param radius
     * @param color
     * @return
     */
    public static Ellipse Circle(double x, double y, double radius, Color color) {
        return new Ellipse(x, y, radius, radius, color);
    }
}

