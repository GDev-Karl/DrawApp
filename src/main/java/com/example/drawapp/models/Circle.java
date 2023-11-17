package com.example.drawapp.models;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import static java.lang.Math.PI;

public class Circle extends Shape {

    private double radius;

    public void setRadius(double radius) {
        this.radius = radius;
    }

    /**
     *
     * @param x  x-coordonate
     * @param y  y-coordonate
     * @param radius  radius of the circle
     * @param color  color of the shape
     */
    public Circle(double x, double y, double radius, Color color) {
        super(x, y, color);
        this.radius = radius;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.setFill(color);
        gc.fillOval(x, y, radius * 2, radius * 2);
        gc.stroke();
    }

    @Override
    public void drawBorder(GraphicsContext gc) {
        gc.strokeRect(x - BORDER_OFFSET, y - BORDER_OFFSET, (this.radius + BORDER_OFFSET) * 2, (this.radius + BORDER_OFFSET) * 2);
    }

    @Override
    public void clearBorder(GraphicsContext gc) {
        gc.clearRect(x - BORDER_OFFSET, y - BORDER_OFFSET, (this.radius + BORDER_OFFSET) * 2, (this.radius + BORDER_OFFSET) * 2);
    }

    @Override
    public double area() {
        return PI * radius * radius;
    }

    @Override
    public double perimeter() {
        return 2 * PI * radius;
    }

    @Override
    public boolean contains(double mouseX, double mouseY) {
        if (mouseX <= this.x || mouseY <= this.y){
            return true;
        }
        return false;
    }

    /**
     *
     * @return: the radius of the circle
     */
    public double getRadius() {
        return this.radius;
    }
}
